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

    public Customer askForCustomerData() {
        Scanner scanner = new Scanner(System.in);
        String ruc;

        System.out.println("----------Ingrese la informacion del cliente----------");
        System.out.print("Ingrese RUC: ");
        while (true) {
            System.out.print("Ingrese RUC: ");
            ruc = scanner.nextLine();
            if (ruc.matches("\\d{13}")) {
                System.out.println("RUC válido");
                break;
            } else {
                System.out.println("El RUC no es válido. Debe contener exactamente 13 dígitos. Intente nuevamente.");
            }
        }

        System.out.print("Ingrese nombre/empresa del cliente: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese numero de contacto: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Ingrese e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese direccion: ");
        String address = scanner.nextLine();

        String customerId = String.format("%05d", ThreadLocalRandom.current().nextInt(10000, 99999));
        Customer customer = new Customer(ruc, name, phoneNumber, email, address, customerId);

        customers.add(customer);
        saveCustomersToFile();

        return customer;
    }

    public Project askForProjectData() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String title = askForProjectTitle(scanner);
        String description = askForProjectDescription(scanner);
        Customer customer = askForCustomer(scanner);
        Date startDate = new Date();
        Date closingDate = askForClosingDate(scanner, dateFormat, startDate);
        double quote = askForProjectQuote(scanner);
        ProjectStatus quoteStatus = askForQuoteStatus(scanner);
        boolean isInvoiced = askIfInvoiced(scanner);
        boolean paid = isInvoiced ? askIfPaid(scanner) : false;
        boolean isPublic = askIfPublic(scanner);

        String projectId = "Prj-" + String.format("%04d", projectCounter++);

        return new Project(title, projectId, description, customer, startDate, closingDate, quote,
                ProjectStatus.CREATED, quoteStatus, paid, isInvoiced, isPublic);
    }

    private String askForProjectTitle(Scanner scanner) {
        System.out.print("Ingrese el titulo del proyecto: ");
        return scanner.nextLine();
    }

    private String askForProjectDescription(Scanner scanner) {
        System.out.print("Ingrese la descripcion: ");
        return scanner.nextLine();
    }

    private Customer askForCustomer(Scanner scanner) {
        System.out.println("Desea usar un cliente existente o crear uno nuevo?");
        System.out.println("1. Usar cliente existente");
        System.out.println("2. Crear cliente nuevo");
        System.out.print("Seleccione una opcion: ");
        int customerOption = scanner.nextInt();
        scanner.nextLine();

        if (customerOption == 1) {
            List<Customer> customers = getCustomers();
            if (customers.isEmpty()) {
                System.out.println("No hay clientes guardados. Debe crear uno nuevo.");
                return askForCustomerData();
            } else {
                return selectExistingCustomer(scanner, customers);
            }
        } else {
            return askForCustomerData();
        }
    }

    private Customer selectExistingCustomer(Scanner scanner, List<Customer> customers) {
        System.out.println("Lista de clientes disponibles:");
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " (" + c.getRuc() + ")");
        }
        System.out.print("Seleccione el numero del cliente: ");
        int customerIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (customerIndex >= 0 && customerIndex < customers.size()) {
            return customers.get(customerIndex);
        } else {
            System.out.println("Seleccion invalida. Creando un cliente nuevo.");
            return askForCustomerData();
        }
    }

    private Date askForClosingDate(Scanner scanner, SimpleDateFormat dateFormat, Date startDate) {
        Date closingDate = null;
        boolean validDate = false;
        do {
            System.out.print("Fecha de cierre (yyyy-MM-dd): ");
            String inputDate = scanner.nextLine();

            if (inputDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                try {
                    closingDate = dateFormat.parse(inputDate);
                    if (closingDate.before(startDate)) {
                        System.out.println("La fecha de cierre no puede ser anterior a la fecha de inicio. Intenta nuevamente.");
                    } else {
                        validDate = true;
                    }
                } catch (ParseException e) {
                    System.out.println("Formato de fecha incorrecto. Intenta nuevamente.");
                }
            } else {
                System.out.println("Por favor, ingresa la fecha en el formato correcto (yyyy-MM-dd).");
            }
        } while (!validDate);
        return closingDate;
    }

    private double askForProjectQuote(Scanner scanner) {
        System.out.print("Presupuesto inicial: ");
        return scanner.nextDouble();
    }

    private ProjectStatus askForQuoteStatus(Scanner scanner) {
        System.out.println("Estado de cotizacion: ");
        System.out.println("1. Quote Sended");
        System.out.println("2. Quote Rejected");
        System.out.println("3. Quote Accepted");
        System.out.print("Seleccione el numero: ");
        int quoteStatusInput = scanner.nextInt();

        return switch (quoteStatusInput) {
            case 1 ->
                ProjectStatus.QUOTE_SEND;
            case 2 ->
                ProjectStatus.QUOTE_REJECTED;
            case 3 ->
                ProjectStatus.QUOTE_ACCEPTED;
            default ->
                ProjectStatus.QUOTE_SEND;
        };
    }

    private boolean askIfInvoiced(Scanner scanner) {
        String facturado;
        do {
            System.out.print("Esta facturado? (Si/No): ");
            facturado = scanner.nextLine().trim().toLowerCase();
            if (facturado.equals("si")) {
                return true;
            } else if (facturado.equals("no")) {
                return false;
            } else {
                System.out.println("Por favor ingrese 'Si' o 'No'.");
            }
        } while (true);
    }

    private boolean askIfPaid(Scanner scanner) {
        System.out.print("Esta pagado? (Si/No): ");
        String paidInput = scanner.nextLine().trim().toLowerCase();
        return paidInput.equals("si");
    }

    private boolean askIfPublic(Scanner scanner) {
        System.out.print("Es proyecto publico? (Si/No): ");
        String publicInput = scanner.nextLine().trim().toLowerCase();
        return publicInput.equals("si");
    }

    public void modifyCustomerByCode(String customerId) {
        Scanner scanner = new Scanner(System.in);
        Customer customer = null;

        for (Customer c : customers) {
            if (c.getCustomerId().equals(customerId)) {
                customer = c;
                break;
            }
        }

        if (customer != null) {
            System.out.println("Modificando datos del cliente: " + customer.getName());
            System.out.print("Nuevo nombre/empresa: ");
            customer.setName(scanner.nextLine());

            System.out.print("Nuevo RUC: ");
            customer.setRuc(scanner.nextLine());

            System.out.print("Nuevo numero de contacto: ");
            customer.setPhoneNumber(scanner.nextLine());

            System.out.print("Nuevo e-mail: ");
            customer.setEmail(scanner.nextLine());

            System.out.print("Nueva direccion: ");
            customer.setAddress(scanner.nextLine());

            saveCustomersToFile();
            System.out.println("Datos del cliente modificados exitosamente.");
        } else {
            System.out.println("Cliente no encontrado con el ID: " + customerId);
        }
    }

    public void updateProjectQuote(String projectId, double newQuote) {
        for (Project project : projects) {
            if (project.getProjectId().equals(projectId)) {
                double oldQuote = project.getStartquote();
                if (oldQuote != newQuote) {
                    project.setStartquote(newQuote);
                    logProjectQuoteChange(project, oldQuote, newQuote);
                    saveProjectsToFile();
                    System.out.println("Presupuesto actualizado exitosamente.");
                } else {
                    System.out.println("El presupuesto no ha cambiado.");
                }
                return;
            }
        }
        System.out.println("Proyecto no encontrado con el ID: " + projectId);
    }

    public void logProjectStatusChange(Project project, ProjectStatus oldStatus, ProjectStatus newStatus) {

        StatusChangeLog log = new StatusChangeLog(
                project.getProjectId(),
                project.getProjectTitle(),
                oldStatus.toString(),
                newStatus.toString(),
                new Date()
        );

        statusChangeLogs.add(log);

        saveStatusChangeLogsToFile();

        System.out.println("Cambio de estado registrado: " + log);
    }

    public void updateProjectStatus(String projectId) {
        Scanner scanner = new Scanner(System.in);

        for (Project project : projects) {
            if (project.getProjectId().equals(projectId)) {

                System.out.println("Estado actual: " + project.getOperationalStatus());

                System.out.println("Seleccione el nuevo estado:");
                System.out.println("1. Created");
                System.out.println("2. In Progress");
                System.out.println("3. Paused");
                System.out.println("4. Closed");

                int option;
                ProjectStatus newStatus = null;
                String description = "";

                do {
                    System.out.print("Opcion: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Entrada invalida. Intente de nuevo.");
                        scanner.next();
                    }
                    option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1 ->
                            newStatus = ProjectStatus.CREATED;
                        case 2 ->
                            newStatus = ProjectStatus.IN_PROGRESS;
                        case 3 -> {
                            newStatus = ProjectStatus.PAUSED;

                            System.out.print("Ingrese una descripcion para el estado PAUSED: ");
                            description = scanner.nextLine();
                        }
                        case 4 ->
                            newStatus = ProjectStatus.CLOSED;
                        default ->
                            System.out.println("Opcion no valida. Intente de nuevo.");
                    }
                } while (newStatus == null);

                ProjectStatus oldStatus = project.getOperationalStatus();
                if (!oldStatus.equals(newStatus)) {

                    project.setOperationalStatus(newStatus);

                    logProjectStatusChange(project, oldStatus, newStatus);

                    saveProjectsToFile();

                    System.out.println("Estado actualizado exitosamente.");
                    if (newStatus == ProjectStatus.PAUSED) {
                        System.out.println("Descripcion agregada: " + description);
                    }
                } else {
                    System.out.println("El estado no ha cambiado.");
                }
                return;
            }
        }
        System.out.println("Proyecto no encontrado con el ID: " + projectId);
    }

    public void displayProjectsWithStatus() {
        System.out.println("Listado de Proyectos:");
        System.out.printf("%-10s %-30s %-20s%n", "Codigo", "Titulo", "Estado");
        System.out.println("-------------------------------------------------------------");

        for (Project project : projects) {
            System.out.printf("%-10s %-30s %-20s%n",
                    project.getProjectId(),
                    project.getProjectTitle(),
                    project.getOperationalStatus().toString());
        }

        System.out.println("-------------------------------------------------------------");
    }

    public void logQuoteStatusChange(Project project, ProjectStatus oldStatus, ProjectStatus newStatus) {
        QuoteStatusChangeLog log = new QuoteStatusChangeLog(
                project.getProjectId(),
                project.getProjectTitle(),
                oldStatus.toString(),
                newStatus.toString(),
                new Date()
        );

        quoteStatusChangeLogs.add(log);
        saveQuoteStatusChangeLogsToFile();
        System.out.println("Cambio de estado de cotizacion registrado: " + log);
    }

    public void updateProjectQuoteStatus(String projectId) {
        Scanner scanner = new Scanner(System.in);

        for (Project project : projects) {
            if (project.getProjectId().equals(projectId)) {

                System.out.println("Estado actual de cotizacion: " + project.getQuoteStatus());

                System.out.println("Seleccione el nuevo estado de cotizacion:");
                System.out.println("1. Quote Sended");
                System.out.println("2. Quote Rejected");
                System.out.println("3. Quote Accepted");

                int option;
                ProjectStatus newQuoteStatus = null;

                do {
                    System.out.print("Opcion: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Entrada invalida. Intente de nuevo.");
                        scanner.next();
                    }
                    option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1 ->
                            newQuoteStatus = ProjectStatus.QUOTE_SEND;
                        case 2 ->
                            newQuoteStatus = ProjectStatus.QUOTE_REJECTED;
                        case 3 ->
                            newQuoteStatus = ProjectStatus.QUOTE_ACCEPTED;
                        default ->
                            System.out.println("Opcion no valida. Intente de nuevo.");
                    }
                } while (newQuoteStatus == null);

                ProjectStatus oldQuoteStatus = project.getQuoteStatus();

                if (!oldQuoteStatus.equals(newQuoteStatus)) {

                    project.setQuoteStatus(newQuoteStatus);

                    logQuoteStatusChange(project, oldQuoteStatus, newQuoteStatus);

                    saveProjectsToFile();

                    System.out.println("Estado de cotizacion actualizado exitosamente.");
                } else {
                    System.out.println("El estado de cotizacion no ha cambiado.");
                }
                return;
            }
        }
        System.out.println("Proyecto no encontrado con el ID: " + projectId);
    }

    public Date calculateEndDateOfSupport(Scanner scanner, Date startDate, int durationYears) {
        int monthsOfSupport = durationYears * 12;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, monthsOfSupport);
        return calendar.getTime();
    }

    public void generateSupport() {
        Scanner scanner = new Scanner(System.in);

        Project selectedProject = selectProject(scanner);
        if (selectedProject == null) {
            System.out.println("Proyecto no encontrado o inválido.");
            return;
        }

        if (!validateProjectStatus(selectedProject)) {
            return;
        }

        String supportDetails = getSupportDetails(scanner);

        Date startDate = new Date();

        int durationYears = getDurationYears(scanner);

        Date endDate = calculateEndDateOfSupport(scanner, startDate, durationYears);

        String scheduleType = getScheduleType(scanner);

        String supportId = generateSupportId(durationYears, scheduleType);

        Support support = new Support(
                supportId,
                selectedProject.getProjectId(),
                selectedProject.getProjectTitle(),
                supportDetails,
                startDate,
                endDate,
                "Created",
                durationYears,
                scheduleType
        );

        supports.add(support);
        saveSupportsToFile();

        System.out.println("Soporte generado con éxito:");
        support.displaySupportData();
    }

    private Project selectProject(Scanner scanner) {
        System.out.println("Proyectos disponibles:");
        for (Project project : projects) {
            System.out.println("Proyecto ID: " + project.getProjectId() + " Estado: " + project.getOperationalStatus());
        }

        System.out.print("Ingrese el ID del proyecto para generar soporte: ");
        String projectId = scanner.nextLine();

        for (Project project : projects) {
            if (project.getProjectId().equals(projectId)) {
                return project;
            }
        }

        return null;
    }

    private boolean validateProjectStatus(Project project) {
        if (!project.getOperationalStatus().getStatus().equals("Closed")) {
            System.out.println("El proyecto no está cerrado. Solo los proyectos cerrados pueden generar soporte.");
            System.out.println("Estado del proyecto: " + project.getOperationalStatus());
            return false;
        }

        if (!project.isPaid()) {
            System.out.println("El proyecto no está pagado. Solo los proyectos pagados pueden generar soporte.");
            return false;
        }

        return true;
    }

    private String generateSupportId(int durationYears, String scheduleType) {
        String durationLabel = durationYears + "A";
        String scheduleLabel = scheduleType.equals("8x5") ? "8*5" : "24*7";

        if (durationYears == 1 && scheduleType.equals("8x5")) {
            return "SRV_01 (" + durationLabel + " " + scheduleLabel + ")";
        } else if (durationYears == 1 && scheduleType.equals("24x7")) {
            return "SRV_02 (" + durationLabel + " " + scheduleLabel + ")";
        } else if (durationYears == 3 && scheduleType.equals("8x5")) {
            return "SRV_03 (" + durationLabel + " " + scheduleLabel + ")";
        } else if (durationYears == 3 && scheduleType.equals("24x7")) {
            return "SRV_04 (" + durationLabel + " " + scheduleLabel + ")";
        } else if (durationYears == 5 && scheduleType.equals("8x5")) {
            return "SRV_05 (" + durationLabel + " " + scheduleLabel + ")";
        } else if (durationYears == 5 && scheduleType.equals("24x7")) {
            return "SRV_06 (" + durationLabel + " " + scheduleLabel + ")";
        } else {
            return "SRV_UNKNOWN (" + durationLabel + " " + scheduleLabel + ")";
        }
    }

    private String getSupportDetails(Scanner scanner) {
        System.out.print("Ingrese los detalles del soporte: ");
        return scanner.nextLine();
    }

    private int getDurationYears(Scanner scanner) {
        int durationYears = 0;
        boolean validDuration = false;

        do {
            System.out.print("Ingrese la duración del soporte en años (1, 2 o 5): ");
            if (scanner.hasNextInt()) {
                durationYears = scanner.nextInt();
                if (durationYears == 1 || durationYears == 3 || durationYears == 5) {
                    validDuration = true;
                } else {
                    System.out.println("Duración inválida. Solo se permite 1, 2 o 5 años.");
                }
            } else {
                System.out.println("Entrada no válida. Debe ser un número.");
                scanner.next();
            }
        } while (!validDuration);

        return durationYears;
    }

    private String getScheduleType(Scanner scanner) {
        String scheduleType = "";
        boolean validSchedule = false;

        System.out.println("Seleccione el tipo de horario:");
        System.out.println("1. 8x5 (8 horas al día por 5 días a la semana)");
        System.out.println("2. 24x7 (24 horas al día por 7 días a la semana)");

        do {
            System.out.print("Ingrese el número de la opción: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scheduleType = "8x5";
                    validSchedule = true;
                    break;
                case 2:
                    scheduleType = "24x7";
                    validSchedule = true;
                    break;
                default:
                    System.out.println("Opción no válida. Elija 1 para 8x5 o 2 para 24x7.");
                    break;
            }
        } while (!validSchedule);

        return scheduleType;
    }

    public void closeSupport() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Lista de soportes:");
        for (Support support : supports) {
            System.out.println("ID: " + support.getSupportId() + ", Estado: " + support.getSupportStatus());
        }

        System.out.print("Ingrese el ID del soporte que desea cerrar: ");
        String supportId = scanner.nextLine();

        Support selectedSupport = null;
        for (Support support : supports) {
            if (support.getSupportId().equals(supportId)) {
                selectedSupport = support;
                break;
            }
        }

        if (selectedSupport == null) {
            System.out.println("Soporte no encontrado con el ID: " + supportId);
            return;
        }

        if (selectedSupport.getSupportStatus().equals("Closed")) {
            System.out.println("El soporte ya esta cerrado.");
            return;
        }

        selectedSupport.setSupportStatus("Closed");

        saveSupportsToFile();

        System.out.println("El soporte con ID: " + supportId + " ha sido cerrado correctamente.");
    }
}
