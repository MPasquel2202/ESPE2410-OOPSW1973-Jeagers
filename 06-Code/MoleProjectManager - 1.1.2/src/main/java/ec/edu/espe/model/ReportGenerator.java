package ec.edu.espe.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

     public void showMonthlyReports() {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        List<Project> allProjects = dataManager.getProjects();
        List<Support> allSupports = dataManager.getSupports();
        List<QuoteChangeLog> allQuoteChangeLogs = dataManager.getQuoteChangeLogs();
        List<StatusChangeLog> allStatusChangeLogs = dataManager.getStatusChangeLogs();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("\n---------> Reporte Mensual <---------\n");

        displayCreatedProjects(allProjects, currentMonth, currentYear);
        displayClosedProjects(allProjects, currentMonth, currentYear);
        displayCreatedSupports(allSupports, currentMonth, currentYear);
        displayClosedSupports(allSupports, currentMonth, currentYear);
        displayStatusChanges(allStatusChangeLogs, currentMonth, currentYear, dateFormat);
        displayQuoteChanges(allQuoteChangeLogs, currentMonth, currentYear, dateFormat);
    }

    private void displayCreatedProjects(List<Project> projects, int month, int year) {
        int createdProjects=0;
        boolean isProjectFound= false;
        
        System.out.println("Proyectos creados este mes:");
        for (Project project : projects) {
            if (isDateInMonth(project.getStartDate(), month, year)) {
                System.out.printf("- %s (ID: %s)\n", project.getProjectTitle(), project.getProjectId());
                createdProjects++;
                isProjectFound=true;
            }
        }
        if(!isProjectFound)
            System.out.println("    Aun no se ha creado proyectos en este mes.");
        System.out.println("Número de proyectos creados este mes: " + createdProjects);
    }

    private void displayClosedProjects(List<Project> projects, int month, int year) {
        int closedProjects=0;
        boolean isProjecFound=false;
        
        System.out.println("\nProyectos cerrados este mes:");
        for (Project project : projects) {
            if (isDateInMonth(project.getClosingDate(), month, year)) {
                System.out.printf("- %s (ID: %s)\n", project.getProjectTitle(), project.getProjectId());
                closedProjects++;
                isProjecFound=true;
            }
        }
        if(!isProjecFound)
            System.out.println("    Aun no se han cerrado proyectos.");
        System.out.println("Número de proyectos cerrados este mes: " + closedProjects);
    }

    private void displayCreatedSupports(List<Support> supports, int month, int year) {
        int createdSupports=0;
        boolean isSupportFound=false;
        
        System.out.println("\nSoportes creados este mes:");
        for (Support support : supports) {
            if (isDateInMonth(support.getStartDate(), month, year)) {
                System.out.printf("- Soporte ID: %s para Proyecto ID: %s\n", support.getSupportId(), support.getProjectId());
                createdSupports++;
                isSupportFound=true;
            }
        }
       if(!isSupportFound)
            System.out.println("    Aun no se han creado soportes este mes");
        System.out.println("Número de soportes creados este mes: " + createdSupports);
    }

    private void displayClosedSupports(List<Support> supports, int month, int year) {
        int closedSupports=0;
        boolean isSupportFound=false;
        
        System.out.println("\nSoportes cerrados este mes:");
        for (Support support : supports) {
            if (isDateInMonth(support.getEndDate(), month, year)) {
                System.out.printf("- Soporte ID: %s para Proyecto ID: %s\n", support.getSupportId(), support.getProjectId());
                closedSupports++;
                isSupportFound=true;
            }
        }
        if(!isSupportFound)
            System.out.println("    Aun no se han cerrado soportes este mes");
        System.out.println("Número de soportes cerrados en el mes: " + closedSupports);
    }

    private void displayStatusChanges(List<StatusChangeLog> logs, int month, int year, SimpleDateFormat dateFormat) {
        int statusChanges=0;
        boolean isChangesFound=false;
        
        System.out.println("\nCambios de estatus este mes:");
        for (StatusChangeLog log : logs) {
            if (isDateInMonth(log.getChangeDate(), month, year)) {
                System.out.printf("- Proyecto: %s, De: %s a %s, Fecha: %s\n", log.getProjectTitle(), log.getOldStatus(), log.getNewStatus(), dateFormat.format(log.getChangeDate()));
                statusChanges++;
                isChangesFound=true;
            }
        }
        if(!isChangesFound)
            System.out.println("    No se han hecho cambios en este mes.");
        System.out.println("Número de cambios de Status en este mes: " + statusChanges);
    }

    private void displayQuoteChanges(List<QuoteChangeLog> logs, int month, int year, SimpleDateFormat dateFormat) {
        int quoteChanges=0;
        boolean isChangesFound=false;
        
        System.out.println("\nCambios de presupuesto este mes:");
        for (QuoteChangeLog log : logs) {
            if (isDateInMonth(log.getChangeDate(), month, year)) {
                System.out.printf("- Proyecto: %s, De: %.2f a %.2f, Fecha: %s\n", log.getProjectTitle(), log.getOldQuote(), log.getNewQuote(), dateFormat.format(log.getChangeDate()));
                quoteChanges++;
                isChangesFound=true;
            }
        }
        if(!isChangesFound)
            System.out.println("    No se han hecho cambios en los presupuestos de los proyectos");
        System.out.println("Número de cambios de presupuesto: " + quoteChanges);
    }


    private boolean isDateInMonth(Date date, int month, int year) {
        if (date == null) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1 == month && cal.get(Calendar.YEAR) == year;
    }
}
