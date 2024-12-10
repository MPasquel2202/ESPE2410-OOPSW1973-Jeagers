package ec.edu.espe.mole.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Brandon Pazmino
 */
public class ProjectsReport {
        private List<Project> projects;
    private Date startDate;
    private Date endDate;

    public ProjectsReport(List<Project> projects, Date startDate, Date endDate) {
        this.projects = projects != null ? projects : new ArrayList<>();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Project> generateReport() {
        List<Project> filteredProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getStartDate().after(startDate) && project.getStartDate().before(endDate)) {
                filteredProjects.add(project);
            }
        }
        return filteredProjects;
    }


    public void exportReportToJSON() {
        System.out.println("Exporting report to JSON...");
        System.out.println("[\n");
        for (Project project : generateReport()) {
            System.out.println("  { " +
                "\"projectId\": \"" + project.getProjectId() + "\", " +
                "\"description\": \"" + project.getDescription() + "\", " +
                "\"status\": \"" + project.getStatus() + "\", " +
                "\"startDate\": \"" + project.getStartDate() + "\", " +
                (project.getEndDate() != null ? "\"endDate\": \"" + project.getEndDate() + "\", " : "") +
                "}");
        }
        System.out.println("\n]");
    }
    
}
