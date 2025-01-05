package ec.edu.espe.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class ReportGenerator {

    private DataManager dataManager;
    private static int reportCounter = 1; 

    public ReportGenerator(DataManager dataManager) {
        if (dataManager == null) {
            throw new IllegalArgumentException("DataManager no puede ser nulo");
        }
        this.dataManager = dataManager;
    }

    public Report generateReport(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("El proyecto no puede ser nulo");
        }

        String reportId = String.format("RPT-%04d", reportCounter++);
        
        List<QuoteChangeLog> quoteChangeLogs = getLogsByProjectId(dataManager.getQuoteChangeLogs(), project.getProjectId(), QuoteChangeLog::getProjectId);
        List<StatusChangeLog> statusChangeLogs = getLogsByProjectId(dataManager.getStatusChangeLogs(), project.getProjectId(), StatusChangeLog::getProjectId);
        List<QuoteStatusChangeLog> quoteStatusChangeLogs = getLogsByProjectId(dataManager.getQuoteStatusChangeLogs(), project.getProjectId(), QuoteStatusChangeLog::getProjectId);

        return new Report(reportId, project, quoteChangeLogs, statusChangeLogs, quoteStatusChangeLogs);
    }

    
    private <T> List<T> getLogsByProjectId(List<T> logs, String projectId, Function<T, String> getProjectId) {
        List<T> filteredLogs = new ArrayList<>();
        if (logs != null) {
            for (T log : logs) {
                if (log != null && getProjectId.apply(log).equals(projectId)) {
                    filteredLogs.add(log);
                }
            }
        }
        return filteredLogs;
    }

   
    public void displayReport(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("El proyecto no puede ser nulo");
        }
        Report report = generateReport(project);
        report.displayReport();
        saveReportToFile(report);  
    }

   
    private void saveReportToFile(Report report) {
        String fileName = "json/report_" + report.getReportId() + ".json";
        File directory = new File("json");
        if (!directory.exists()) {
            directory.mkdir();  
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(report, writer);  
            System.out.println("Informe guardado exitosamente en " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
