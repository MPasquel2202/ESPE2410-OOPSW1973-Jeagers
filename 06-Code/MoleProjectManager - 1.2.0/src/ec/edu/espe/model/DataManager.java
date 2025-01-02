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
    private ArrayList<Support> supports;
    private static int projectCounter = 1;
    private static final String PROJECTS_FILE_NAME = "json/projects.json";
    private static final String CUSTOMERS_FILE_NAME = "json/customers.json";
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

    public void displayRemainingSupportDates() {
        Date today = new Date();
        System.out.println("| Fechas restantes de soporte |");
        System.out.println("|----------------------------|");

        for (Support support : supports) {
            long remainingDays = (support.getEndDate().getTime() - today.getTime()) / (1000 * 60 * 60 * 24);

            if (remainingDays > 0) {
                System.out.println("Soporte ID: " + support.getIdSupport());
                System.out.println("Proyecto Asociado: " + support.getAsociatedProjectId());
                System.out.println("Días restantes: " + remainingDays + " días");
                System.out.println("----------------------------------");
            } else {
                System.out.println("Soporte ID: " + support.getIdSupport() + " ha expirado.");
                System.out.println("----------------------------------");
            }
        }
    }

    public DataManager() {
        projects = new ArrayList<>();
        customers = new ArrayList<>();
        supports = new ArrayList<>();
        loadProjectsFromFile();
        loadCustomersFromFile();
        loadSupportsFromFile();

        initializeProjectCounter();
    }

    public String getPROJECTS_FILE_NAME() {
        return PROJECTS_FILE_NAME;
    }

    public String getCUSTOMERS_FILE_NAME() {
        return CUSTOMERS_FILE_NAME;
    }

    public String getSUPPORTS_FILE_NAME() {
        return SUPPORTS_FILE_NAME;
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

    public void saveSupportsToFile() {
        File file = new File(SUPPORTS_FILE_NAME);

        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(supports, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de soportes: " + e.getMessage());
        }
    }

    public void loadSupportsFromFile() {
        File file = new File(SUPPORTS_FILE_NAME);

        if (!file.exists()) {
            System.out.println("Archivo de soportes no encontrado. Se creará un nuevo archivo.");
            return;
        }

        try (Reader reader = new FileReader(file)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Support>>() {
            }.getType();
            supports = gson.fromJson(reader, listType);

            if (supports == null) {
                supports = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de soportes: " + e.getMessage());
        }
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

    public Customer askForCustomerData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------->  Ingrese la informacion del cliente  <---------");
        System.out.print("Ingrese RUC: ");
        String ruc = scanner.nextLine();

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

        System.out.print("Ingrese el titulo del proyecto: ");
        String title = scanner.nextLine();

        System.out.print("Ingrese la descripcion: ");
        String description = scanner.nextLine();

        Customer customer;
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
                customer = askForCustomerData();
            } else {
                System.out.println("Lista de clientes disponibles:");
                for (int i = 0; i < customers.size(); i++) {
                    Customer c = customers.get(i);
                    System.out.println((i + 1) + ". " + c.getName() + " (" + c.getRuc() + ")");
                }
                System.out.print("Seleccione el numero del cliente: ");
                int customerIndex = scanner.nextInt() - 1;
                scanner.nextLine();

                if (customerIndex >= 0 && customerIndex < customers.size()) {
                    customer = customers.get(customerIndex);
                } else {
                    System.out.println("Seleccion invalida. Creando un cliente nuevo.");
                    customer = askForCustomerData();
                }
            }
        } else {
            customer = askForCustomerData();
        }

        Date startDate = new Date();

        Date closingDate = null;
        boolean validDate = false;

        do {
            System.out.print("Fecha de cierre (yyyy-MM-dd): ");
            String inputDate = scanner.nextLine();

            if (inputDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                closingDate = null;
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

        System.out.print("Presupuesto inicial: ");
        double quote = scanner.nextDouble();

        ProjectStatus operationalStatus = ProjectStatus.CREATED;

        System.out.println("Estado de cotizacion: ");
        System.out.println("1. Quote Sended");
        System.out.println("2. Quote Rejected");
        System.out.println("3. Quote Accepted");
        System.out.print("Seleccione el numero: ");
        int quoteStatusInput = scanner.nextInt();
        ProjectStatus quoteStatus = switch (quoteStatusInput) {
            case 1 ->
                ProjectStatus.QUOTE_SEND;
            case 2 ->
                ProjectStatus.QUOTE_REJECTED;
            case 3 ->
                ProjectStatus.QUOTE_ACCEPTED;
            default ->
                ProjectStatus.QUOTE_SEND;
        };

        scanner.nextLine();
        String facturado;
        boolean isInvoiced = false;
        do {
            System.out.print("Esta facturado? (Si/No): ");
            facturado = scanner.nextLine().trim().toLowerCase();
            if (facturado.equals("si")) {
                isInvoiced = true;
                break;
            } else if (facturado.equals("no")) {
                isInvoiced = false;
                break;
            } else {
                System.out.println("Por favor ingrese 'Si' o 'No'.");
            }
        } while (true);

        boolean paid = false;
        if (isInvoiced) {
            System.out.print("Esta pagado? (Si/No): ");
            String paidInput = scanner.nextLine().trim().toLowerCase();
            if (paidInput.equals("si")) {
                paid = true;
            } else if (paidInput.equals("no")) {
                paid = false;
            } else {
                System.out.println("Por favor ingrese 'Si' o 'No'. Se asumira 'No'.");
                paid = false;
            }
        }

        System.out.print("Es proyecto publico? (Si/No): ");
        String publicInput = scanner.nextLine().trim().toLowerCase();
        boolean isPublic = publicInput.equals("si");

        String projectId = "Prj-" + String.format("%04d", projectCounter++);

        return new Project(title, projectId, description, customer, startDate, closingDate, quote, operationalStatus, quoteStatus, paid, isInvoiced, isPublic);
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

    public void modifyProjectBudget() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> budgetUpdates = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Ingrese el ID del proyecto a modificar: ");
        String projectId = scanner.nextLine();

        Project project = null;
        for (Project p : projects) {
            if (p.getProjectId().equals(projectId)) {
                project = p;
                break;
            }
        }

        if (project != null) {
            System.out.println("Proyecto encontrado: " + project.getProjectTitle());
            System.out.println("Presupuesto actual: $" + project.getStartquote());

            System.out.print("Ingrese el nuevo presupuesto: $");
            double newQuote = scanner.nextDouble();
            scanner.nextLine();

            project.setStartquote(newQuote);
            saveProjectsToFile();
            System.out.println("Presupuesto actualizado exitosamente.");

            String updateRecord = String.format(
                    "Proyecto: %s, Codigo: %s, Cliente: %s, Nuevo Presupuesto: $%.2f",
                    project.getProjectTitle(), project.getProjectId(), project.getCustomer().getName(), newQuote
            );
            budgetUpdates.add(updateRecord);

            saveBudgetUpdatesToFile(budgetUpdates);
            System.out.println("Historial de actualizaciones guardado en 'json/budget_updates.json'.");

        } else {
            System.out.println("No se encontro un proyecto con el codigo: " + projectId);
        }
    }

    private void saveBudgetUpdatesToFile(ArrayList<String> updates) {
        File directory = new File("json");
        if (!directory.exists()) {
            directory.mkdir();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("json/budget_updates.json")) {
            gson.toJson(updates, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSupport(Scanner scanner) {
        if (projects == null || projects.isEmpty()) {
            System.out.println("No hay proyectos cargados.");
            return;
        }

        System.out.println("Ingrese el ID del proyecto que solicita soporte: ");
        scanner.nextLine();
        String projectId = scanner.nextLine().trim();

        boolean projectExists = projects.stream()
                .anyMatch(project -> project.getProjectId().equalsIgnoreCase(projectId));

        if (projectExists) {
            System.out.println("------> Ingrese la información del soporte <-----");
            Support support = askForSupportData(scanner, projectId);
            supports.add(support);
            saveSupportsToFile();
            System.out.println("El soporte ha sido creado con éxito.");
            support.toString();
        } else {
            System.out.println("No existe un proyecto con el ID proporcionado.");
        }
    }

    public Date calculateEndDateOfSupport(Scanner scanner, Date startDate) {
        int monthsOfSupport = 0;
        while (monthsOfSupport <= 0) {
            try {
                System.out.println("Meses de contrato de soporte: ");
                monthsOfSupport = scanner.nextInt();
                scanner.nextLine();
                if (monthsOfSupport <= 0) {
                    System.out.println("Ingrese un valor válido para los meses de contrato.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.nextLine();
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, monthsOfSupport);
        return calendar.getTime();
    }

    public Support askForSupportData(Scanner scanner, String projectId) {
        System.out.println("Ingrese el ID del soporte: ");
        String idSupport = scanner.nextLine();

        for (Support existingSupport : supports) {
            if (existingSupport.getIdSupport().equalsIgnoreCase(idSupport)) {
                System.out.println("El ID del soporte ya existe. Por favor, ingrese uno diferente.");
                return askForSupportData(scanner, projectId);
            }
        }

        System.out.println("Ingrese una descripción del soporte: ");
        String description = scanner.nextLine();
        Date startDate = new Date();
        Date endDate = calculateEndDateOfSupport(scanner, startDate);
        return new Support(idSupport, projectId, description, startDate, endDate);
    }

}
