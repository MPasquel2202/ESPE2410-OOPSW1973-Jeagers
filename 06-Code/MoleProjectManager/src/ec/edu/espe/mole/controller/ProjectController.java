package ec.edu.espe.mole.controller;

import ec.edu.espe.mole.model.Project;
import ec.edu.espe.mole.model.Status;
import ec.edu.espe.mole.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marlon Pasquel
 */

public class ProjectController {
    private final List<Project> projects;

    public ProjectController() {
        this.projects = new ArrayList<>();
    }

    public void createProject(String projectId, String description, Customer customer, Date startDate, Status status) {
        Project project = new Project(projectId, description, customer, startDate, status);
        projects.add(project);
        System.out.println("Project created successfully: \n" + project);
    }

    public void listProjects() {
        System.out.println("\n--- List of Projects ---");
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
            return;
        }
        for (Project project : projects) {
            System.out.println(project);
        }
    }

    public List<Project> filterProjectsByDate(Date startDate, Date endDate) {
        List<Project> filteredProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getStartDate().after(startDate) && project.getStartDate().before(endDate)) {
                filteredProjects.add(project);
            }
        }
        return filteredProjects;
    }
    public void updateProjectStatus(String projectId, Status newStatus) {
    for (Project project : projects) {
        if (project.getProjectId().equals(projectId)) {
            project.setStatus(newStatus);
            System.out.println("Project status updated successfully to: " + newStatus);
            return;
        }
    }
    System.out.println("Error: Project with ID " + projectId + " not found.");
}

}