package projects.service;

import java.util.List;
import java.util.NoSuchElementException;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exceptions.DbException;

public class ProjectService {
	private ProjectDao projectDao = new ProjectDao();

	
	public Project addProject(Project project) {
		return projectDao.insertProject(project);
	}


	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects();
	}


	public Project fetchProjectById(Integer projectID) {
		return projectDao.fetchProjectById(projectID).orElseThrow(
				() -> new NoSuchElementException(
						"Project with Project ID = " + projectID
						+ " does not exist.")
				);
	}


	public void modifyProjectDetails(Project newProject) {
		if(!projectDao.modifyProjectDetails(newProject)) {
			throw new DbException("UPDATE operation for Project ID: "
					+ newProject.getProjectId() + "was "
					+ "unsuccessful.");
		}
		
	}


	public void deleteProject(Integer id) {
		if(!projectDao.deleteProject(id)) {
			throw new DbException("no Project associated "
					+ "with id= " + id);
		}
		
	}

}
