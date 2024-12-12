package ec.edu.espe.mole.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.mole.model.Project;
import ec.edu.espe.mole.model.Status;
import ec.edu.espe.mole.model.Customer;
import ec.edu.espe.mole.model.JSONFileHandler;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;

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

    public void createProject(Project project) {
        JSONFileHandler<Project> JSONHandler=new JSONFileHandler();
        List<Project> projectList= new ArrayList<>();
        String filepath="project.json";
        Type projectListType=new TypeToken<List<Project>>() {}.getType();
        projectList=JSONHandler.readFromFile(filepath, projectListType);
        
        projectList.add(project);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(projectList);
        System.out.println(json);
        JSONHandler.writeToFile(projectList,"project.json" );
        
        try (Writer writer = new FileWriter("project.json")) {
            gson.toJson(projectList, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listProjects() {
        System.out.println("\n--- List of Projects ---");
         JSONFileHandler<Project> handler=new JSONFileHandler<>();
        List<Project> projectlist= new ArrayList<>();
        String filepath="project.json";
        Type projectListType=new TypeToken<List<Project>>() {}.getType();
        projectlist=handler.readFromFile(filepath, projectListType);
        
        if (projectlist.isEmpty()) {
            System.out.println("No projects found.");
            return;
        }
        for (Project project : projectlist) {
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