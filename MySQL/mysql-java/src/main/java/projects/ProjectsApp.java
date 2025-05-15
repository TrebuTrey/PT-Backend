package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exceptions.DbException;
import projects.service.ProjectService;

public class ProjectsApp {
	private ProjectService projectService = new ProjectService();
	
	//@formatter:off
	private List<String> operations = List.of(
			"1) add a project",
			"2) list projects",
			"3) select a project",
			"4) update project details",
			"5) delete a project"
			);
	//@formatter:on
	
	private Scanner scanner = new Scanner(System.in);
	
	private Project curProject;
	
	public static void main(String[] args) {
		
		new ProjectsApp().processUserSelections();
		
	}

	private void processUserSelections() {
		boolean done = false;
		
		while(!done) {
			try {
				int selection = getUserSelection();
				
				switch(selection){
				case -1:
					done = exitMenu();
					break;
				case 1:
					createProject();
					break;
				case 2:
					listProjects();
					break;
				case 3:
					selectProject();
					break;
				case 4:
					updateProjectDetails();
					break;
				case 5:
					deleteProject();
					break;
				default:
					System.out.println("\n" + selection + 
							" is not a valid selection. Try again.");
				}
			}catch(Exception e) {
				System.out.println(e.toString());
			}
				
			
		}
		
	}

	private void deleteProject() {
		listProjects();
		Integer id = getIntInput("Please select a project to delete"
				+ " using its project_id");
		projectService.deleteProject(id);
		
		System.out.println("Project successfully deleted.");
		
		try {
			curProject = null;
		} catch(NullPointerException e) {
			throw new DbException(e);
		}
	}

	private void updateProjectDetails() {
		if(Objects.isNull(curProject)) {
			System.out.println("\nPlease select a Project.");
			return;
		}
		
		String projectName = getStringInput("Enter new Project "
				+ "name for [" + curProject.getProjectName() + "]");
		BigDecimal estimatedHours = getDecimalInput("Enter updated estimate"
				+ " hours to replace [" + curProject.getEstimatedHours() + "]");
		BigDecimal actualHours = getDecimalInput("Enter updated acutal"
				+ " hours to replace [" + curProject.getActualHours() + "]");
		Integer difficulty = getIntInput("Enter updated difficulty"
				+ " to replace [" + curProject.getDifficulty() + "]");
		String notes = getStringInput("Enter new notes "
				+ "to replace [" + curProject.getNotes() + "]");
		
		Project newProject = new Project();
		
		newProject.setProjectId(curProject.getProjectId());
		newProject.setProjectName(Objects.isNull(projectName)? curProject.getProjectName(): projectName);
		newProject.setEstimatedHours(Objects.isNull(estimatedHours)? curProject.getEstimatedHours(): estimatedHours);
		newProject.setActualHours(Objects.isNull(actualHours)? curProject.getActualHours(): actualHours);
		newProject.setDifficulty(Objects.isNull(difficulty)? curProject.getDifficulty(): difficulty);
		newProject.setNotes(Objects.isNull(notes)? curProject.getNotes(): notes);
		
		projectService.modifyProjectDetails(newProject);
		curProject = projectService.fetchProjectById(curProject.getProjectId());
		
	}

	private void selectProject() {
		listProjects();
		Integer projectID = getIntInput("Enter a project ID to select a project");
		curProject = null;
		curProject = projectService.fetchProjectById(projectID);
		
		if(Objects.isNull(curProject)) {
			System.out.println("Invalid project ID selected.");
		}
		
	}

	private void listProjects() {
		List<Project> projects = projectService.fetchAllProjects();
		
		System.out.println("\nProjects:");
		
		projects.forEach(project -> 
			System.out.println(project.getProjectId() + " : " 
					+ project.getProjectName()));
		
	}

	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
	
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created: " + 
				dbProject);
		
	}

	private int getUserSelection() {
		printOperations();
		
		Integer input = getIntInput("Enter Menu Selection");
		
		return Objects.isNull(input) ? 1: input;
	}
	
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);
		if(Objects.isNull(input)) {
			return null;
		}
		
		try {
			return new BigDecimal(input).setScale(2);
		}catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid decimal number."
					+ " Please try again.");
		}
	}

	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		if(Objects.isNull(input) == true) {
			return null;
		}
		
		try {
			return Integer.valueOf(input);
		}catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid number."
					+ " Please try again.");
		}
	}

	private String getStringInput(String prompt) {
		System.out.print(prompt + ": ");
		String input = scanner.nextLine();
		
		return (Objects.isNull(input) || input.isBlank()) ? null: input.trim();
	}

	private void printOperations() {
		System.out.println("\nThese are the available selections. "
				+ "Press the Enter key to quit.");
		operations.forEach(line -> System.out.println(" " + line));
		
		String selProject = Objects.isNull(curProject) ? 
				"\nYou are not working with a project." :
				"\nYou are working with project: " + curProject;
		
		System.out.println(selProject);
	}
	
	private boolean exitMenu() {
		System.out.println("Exiting the menu.");
		return true;
	}
}
