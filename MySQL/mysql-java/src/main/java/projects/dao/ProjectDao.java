package projects.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Project;
import projects.entity.Step;
import projects.exceptions.DbException;
import provided.util.DaoBase;

public class ProjectDao extends DaoBase {

	private static final String CATEGORY_TABLE = "category";
	private static final String MATERIAL_TABLE = "material";
	private static final String PROJECT_TABLE = "project";
	private static final String PROJECT_CATEGORY_TABLE = "project_category";
	private static final String STEP_TABLE = "step";
	
	public Project insertProject(Project project) {
		//@formatter:off
		String sql = ""
				+ "INSERT INTO " + PROJECT_TABLE + ""
				+ "(project_name, estimated_hours, actual_hours, difficulty, notes) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		//@formatter:on
		
		try(Connection conn = DbConnection.getConnection()){
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				
				setParameter(stmt, 1, project.getProjectName(), String.class);
				setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
				setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
				setParameter(stmt, 4, project.getDifficulty(), Integer.class);
				setParameter(stmt, 5, project.getNotes(), String.class);
				
				stmt.executeUpdate();
				
				Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
				
				commitTransaction(conn);
				
				project.setProjectId(projectId);
				
				return project;
			}catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch(SQLException e) {
			throw new DbException(e);
		}
	}

	public List<Project> fetchAllProjects() {
		String sql = "SELECT * FROM project\n";
//				+ "LEFT JOIN(material) USING(project_id)\n"
//				+ "LEFT JOIN(project_category) USING(project_id)\n"
//				+ "LEFT JOIN(step) USING(project_id)\n"
//				+ "WHERE material_id IS NULL\n"
//				+ "	AND category_id IS NULL\n"
//				+ " AND step_id IS NULL\n"
//				+ "ORDER BY project_name DESC;";
		
		try(Connection conn = DbConnection.getConnection()){
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				
				try(ResultSet res = stmt.executeQuery()){
					List<Project> projects = new ArrayList<Project>();
					while(res.next()) {
						projects.add(extract(res, Project.class));
					}
					return projects;
				}
				
			}catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		}catch(SQLException e) {
			throw new DbException(e);
		}
	}

	public Optional<Project> fetchProjectById(Integer projectID) {
		String query = "SELECT * FROM " + PROJECT_TABLE
				+ "\nWHERE project_id = ?;";
		try(Connection conn = DbConnection.getConnection()){
			startTransaction(conn);
			
			try {
				Project project = null;
				
				try(PreparedStatement stmt = conn.prepareStatement(query)){
					
					setParameter(stmt, 1, projectID, Integer.class);
					
					try(ResultSet res = stmt.executeQuery()){
						
						if(res.next()) {
							project = extract(res, Project.class);
						}
					}
				}
				
				if(Objects.nonNull(project)) {
					project.getMaterials().addAll(fetchMaterialsForProject(conn, projectID));
					project.getSteps().addAll(fetchStepsForProject(conn, projectID));
					project.getCategories().addAll(fetchCategoriesForProject(conn, projectID));
				}
				
				commitTransaction(conn);
				
				return Optional.ofNullable(project);
				
			}catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		}catch(SQLException e) {
			throw new DbException(e);
		}
	}

	private List<Category> fetchCategoriesForProject(Connection conn, Integer projectID) throws SQLException {
		String query = "SELECT * FROM category\n"
				+ "JOIN(project_category) USING(category_id)\n"
				+ "WHERE project_id = ?;";
		
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			
			setParameter(stmt, 1, projectID, Integer.class);
			
			try(ResultSet rs = stmt.executeQuery()){
				
				List<Category> categories = new LinkedList<>();
				
				while(rs.next()) {
					categories.add(extract(rs, Category.class));
				}
				
				return categories;
			}
		}
	}

	private List<Step> fetchStepsForProject(Connection conn, Integer projectID) throws SQLException{
		String query = "SELECT * FROM step\n"
				+ "WHERE project_id = ?;";
		
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			
			setParameter(stmt, 1, projectID, Integer.class);
			
			try(ResultSet rs = stmt.executeQuery()){
				
				List<Step> steps = new LinkedList<>();
				
				while(rs.next()) {
					steps.add(extract(rs, Step.class));
				}
				
				return steps;
			}
		}
	}

	private List<Material> fetchMaterialsForProject(Connection conn, Integer projectID) throws SQLException{
		String query = "SELECT * FROM material\n"
				+ "WHERE project_id = ?;";
		
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			
			setParameter(stmt, 1, projectID, Integer.class);
			
			try(ResultSet rs = stmt.executeQuery()){
				
				List<Material> materials = new LinkedList<>();
				
				while(rs.next()) {
					materials.add(extract(rs, Material.class));
				}
				
				return materials;
			}
		}
	}

	public boolean modifyProjectDetails(Project newProject) {
		//@formatter: off
		String sql = "UPDATE " + PROJECT_TABLE + " SET "
				+ "project_name = ? ,"
				+ "estimated_hours = ? ,"
				+ "actual_hours = ? ,"
				+ "difficulty = ? ,"
				+ "notes = ? "
				+ "WHERE project_id = ?";
		
		try(Connection conn = DbConnection.getConnection()){
			startTransaction(conn);
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				
				setParameter(stmt, 1, newProject.getProjectName(), String.class);
				setParameter(stmt, 2, newProject.getEstimatedHours(), BigDecimal.class);
				setParameter(stmt, 3, newProject.getActualHours(), BigDecimal.class);
				setParameter(stmt, 4, newProject.getDifficulty(), Integer.class);
				setParameter(stmt, 5, newProject.getNotes(), String.class);
				setParameter(stmt, 6, newProject.getProjectId(), Integer.class);
				
				int x = stmt.executeUpdate();
				
				commitTransaction(conn);
				
				return (x==1? true: false);
				
				
			}catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		}catch(SQLException e) {
			throw new DbException(e);
		}
	}

	public boolean deleteProject(Integer id) {
		String sql = "DELETE FROM " + PROJECT_TABLE
				+ " WHERE project_id = ?";
		
		try(Connection conn = DbConnection.getConnection()){
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				setParameter(stmt, 1, id, Integer.class);
				
				int x = stmt.executeUpdate();
				
				commitTransaction(conn);
				
				return(x==1? true : false);
				
			}catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		}catch(SQLException e) {
			throw new DbException(e);
		}
	}

}
