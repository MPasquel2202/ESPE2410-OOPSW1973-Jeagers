package ec.edu.espe.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Dennis Paucar
 */
public class DataManager {

    private ArrayList<Project> projects;
    private ArrayList<Customer> customers;
    private static int projectCounter = 1;
    private ArrayList<QuoteChangeLog> quoteChangeLogs = new ArrayList<>();
    private ArrayList<StatusChangeLog> statusChangeLogs = new ArrayList<>();
    private ArrayList<QuoteStatusChangeLog> quoteStatusChangeLogs = new ArrayList<>();
    private ArrayList<Support> supports = new ArrayList<>();

    private static final String PROJECTS_FILE_NAME = "json/projects.json";
    private static final String CUSTOMERS_FILE_NAME = "json/customers.json";
    private static final String CHANGE_LOGS_FILE_NAME = "json/quote_changeLogs.json";
    private static final String STATUS_CHANGE_LOGS_FILE_NAME = "json/status_changeLogs.json";
    private static final String QUOTE_STATUS_CHANGE_LOGS_FILE_NAME = "json/quote_status_changeLogs.json";
    private static final String SUPPORTS_FILE_NAME = "json/supports.json";

    private void initializeProjectCounter() {
        int maxId = 0;

        for (Project project : projects) {
            String id = project.getProjectId();

            try {
                int numericPart = Integer.parseInt(id.split("-")[1]);
                if (numericPart > maxId) {
                    maxId = numericPart;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        projectCounter = maxId + 1;
    }

    public DataManager() {
        projects = new ArrayList<>();
        customers = new ArrayList<>();
        quoteChangeLogs = new ArrayList<>();
        quoteChangeLogs = new ArrayList<>();
        quoteStatusChangeLogs = new ArrayList<>();
        statusChangeLogs = new ArrayList<>();

        loadProjectsFromFile();
        loadCustomersFromFile();
        loadChangeLogsFromFile();
        loadStatusChangeLogsFromFile();
        loadQuoteStatusChangeLogsFromFile();

        initializeProjectCounter();
    }

    public void addProject(Project project) {
        projects.add(project);
        System.out.println("Proyecto agregado con exito: " + project.getProjectTitle());

    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Support> getSupports() {
        return supports;
    }

    public List<QuoteChangeLog> getQuoteChangeLogs() {
        return quoteChangeLogs;
    }

    public List<QuoteStatusChangeLog> getQuoteStatusChangeLogs() {
        return quoteStatusChangeLogs;
    }

    public List<StatusChangeLog> getStatusChangeLogs() {
        return statusChangeLogs;
    }

    public void saveProjectsToFile() {
        saveToFile(PROJECTS_FILE_NAME, projects);
        System.out.println("Proyectos guardados exitosamente en " + PROJECTS_FILE_NAME);
    }

    public void loadProjectsFromFile() {
        projects = loadFromFile(PROJECTS_FILE_NAME, new TypeToken<List<Project>>() {
        }.getType());
        System.out.println("Proyectos cargados exitosamente desde " + PROJECTS_FILE_NAME);
    }

    public void saveCustomersToFile() {
        saveToFile(CUSTOMERS_FILE_NAME, customers);
        System.out.println("Clientes guardados exitosamente en " + CUSTOMERS_FILE_NAME);
    }

    public void loadCustomersFromFile() {
        customers = loadFromFile(CUSTOMERS_FILE_NAME, new TypeToken<List<Customer>>() {
        }.getType());
        System.out.println("Clientes cargados exitosamente desde " + CUSTOMERS_FILE_NAME);
    }

    public void saveChangeLogsToFile() {
        saveToFile(CHANGE_LOGS_FILE_NAME, quoteChangeLogs);
        System.out.println("Historial de cambios guardado exitosamente en " + CHANGE_LOGS_FILE_NAME);
    }

    public void loadChangeLogsFromFile() {
        quoteChangeLogs = loadFromFile(CHANGE_LOGS_FILE_NAME, new TypeToken<List<QuoteChangeLog>>() {
        }.getType());
        System.out.println("Historial de cambios cargado exitosamente desde " + CHANGE_LOGS_FILE_NAME);
    }

    public void saveStatusChangeLogsToFile() {
        saveToFile(STATUS_CHANGE_LOGS_FILE_NAME, statusChangeLogs);
        System.out.println("Historial de cambios de estado guardado exitosamente en " + STATUS_CHANGE_LOGS_FILE_NAME);
    }

    public void loadStatusChangeLogsFromFile() {
        statusChangeLogs = loadFromFile(STATUS_CHANGE_LOGS_FILE_NAME, new TypeToken<List<StatusChangeLog>>() {
        }.getType());
        System.out.println("Historial de cambios de estado cargado exitosamente desde " + STATUS_CHANGE_LOGS_FILE_NAME);
    }

    public void saveQuoteStatusChangeLogsToFile() {
        saveToFile(QUOTE_STATUS_CHANGE_LOGS_FILE_NAME, quoteStatusChangeLogs);
        System.out.println("Historial de cambios de estado de cotizacion guardado exitosamente en " + QUOTE_STATUS_CHANGE_LOGS_FILE_NAME);
    }

    public void loadQuoteStatusChangeLogsFromFile() {
        quoteStatusChangeLogs = loadFromFile(QUOTE_STATUS_CHANGE_LOGS_FILE_NAME, new TypeToken<List<QuoteStatusChangeLog>>() {
        }.getType());
        System.out.println("Historial de cambios de estado de cotizacion cargado exitosamente desde " + QUOTE_STATUS_CHANGE_LOGS_FILE_NAME);
    }

    public void saveSupportsToFile() {
        saveToFile(SUPPORTS_FILE_NAME, supports);
        System.out.println("Soportes guardados exitosamente en " + SUPPORTS_FILE_NAME);
    }

    public void loadSupportsFromFile() {
        supports = loadFromFile(SUPPORTS_FILE_NAME, new TypeToken<List<Support>>() {
        }.getType());
        System.out.println("Soportes cargados exitosamente desde " + SUPPORTS_FILE_NAME);
    }

    public <T> void saveToFile(String fileName, List<T> data) {
        File directory = new File("json");
        if (!directory.exists()) {
            directory.mkdir();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> ArrayList<T> loadFromFile(String fileName, Type type) {
        File file = new File(fileName);
        if (file.exists()) {
            Gson gson = new Gson();
            try (Reader reader = new FileReader(file)) {
                return gson.fromJson(reader, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    public void logProjectQuoteChange(Project project, double oldQuote, double newQuote) {
        QuoteChangeLog log = new QuoteChangeLog(
                project.getProjectId(),
                project.getProjectTitle(),
                oldQuote,
                newQuote,
                new Date()
        );
        quoteChangeLogs.add(log);
        saveChangeLogsToFile();
        System.out.println("Cambio registrado: " + log);
    }

    public Date calculateEndDateOfSupport(Scanner scanner, Date startDate, int durationYears) {
        int monthsOfSupport = durationYears * 12;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, monthsOfSupport);
        return calendar.getTime();
    }
}
