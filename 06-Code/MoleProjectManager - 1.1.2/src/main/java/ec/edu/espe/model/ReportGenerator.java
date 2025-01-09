package ec.edu.espe.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.model.Report;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 
 * @author David Pilatasig
 */
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

    List<Support> supports = loadSupportsForProject(project.getProjectId());

    String reportId = String.format("RPT-%04d", reportCounter++);

    List<QuoteChangeLog> quoteChangeLogs = getLogsByProjectId(
        dataManager.getQuoteChangeLogs(), project.getProjectId(), QuoteChangeLog::getProjectId
    );
    List<StatusChangeLog> statusChangeLogs = getLogsByProjectId(
        dataManager.getStatusChangeLogs(), project.getProjectId(), StatusChangeLog::getProjectId
    );
    List<QuoteStatusChangeLog> quoteStatusChangeLogs = getLogsByProjectId(
        dataManager.getQuoteStatusChangeLogs(), project.getProjectId(), QuoteStatusChangeLog::getProjectId
    );

    return new Report(reportId, project, quoteChangeLogs, statusChangeLogs, quoteStatusChangeLogs, supports);
}

    private List<Support> loadSupportsForProject(String projectId) {
    String fileName = "json/supports.json"; // Ruta al archivo JSON
    File file = new File(fileName);
    List<Support> supports = new ArrayList<>();

    if (file.exists()) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            List<Support> supportEntries = gson.fromJson(reader, new TypeToken<List<Support>>() {}.getType());
            for (Support entry : supportEntries) {
                if (entry.getProjectId().equals(projectId)) {
                    supports.add(entry);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON de soportes: " + e.getMessage());
        }
    } else {
        System.err.println("El archivo JSON de soportes no existe: " + fileName);
    }

    return supports;
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
        String fileName = "json/reports.json";
        File directory = new File("json");
        if (!directory.exists()) {
            directory.mkdir();
        }

        List<Report> reports = loadReportsFromFile(fileName);
        reports.add(report);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(reports, writer);
            System.out.println("Informe guardado exitosamente en " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Report> loadReportsFromFile(String fileName) {
        File file = new File(fileName);
        List<Report> reports = new ArrayList<>();

        if (file.exists()) {
            Gson gson = new Gson();
            try (FileReader reader = new FileReader(fileName)) {
                Report[] reportArray = gson.fromJson(reader, Report[].class);
                if (reportArray != null) {
                    reports = new ArrayList<>(Arrays.asList(reportArray));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return reports;
    }

    private static class SupportEntry {
        private String projectId;
        private List<String> supports;

        public String getProjectId() {
            return projectId;
        }

        public List<String> getSupports() {
            return supports;
        }
    }
}
