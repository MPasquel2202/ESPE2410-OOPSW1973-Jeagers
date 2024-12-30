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
import java.util.Date;
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
    private ArrayList<ProjectChangeLog> quoteChangeLogs = new ArrayList<>();
    private static final String PROJECTS_FILE_NAME = "json/projects.json";
    private static final String CUSTOMERS_FILE_NAME = "json/customers.json";
    private static final String CHANGE_LOGS_FILE_NAME = "json/quote_changeLogs.json";

    
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
        loadProjectsFromFile();
        loadCustomersFromFile();
        loadChangeLogsFromFile();
        
        initializeProjectCounter();
    }

    public void addProject(Project project) {
        projects.add(project);
        System.out.println("Proyecto agregado con exito: " + project.getProjectTitle());
        
    }
    
    public List<Project> getProjects() {
        return projects;
    }
    
    public List<Customer> getCustomers(){
        return customers;
    }
    
    public void saveProjectsToFile() {
        saveToFile(PROJECTS_FILE_NAME, projects);
        System.out.println("Proyectos guardados exitosamente en " + PROJECTS_FILE_NAME);
    }

    public void loadProjectsFromFile() {
        projects = loadFromFile(PROJECTS_FILE_NAME, new TypeToken<List<Project>>(){}.getType());
        System.out.println("Proyectos cargados exitosamente desde " + PROJECTS_FILE_NAME);
    }

    public void saveCustomersToFile() {
        saveToFile(CUSTOMERS_FILE_NAME, customers);
        System.out.println("Clientes guardados exitosamente en " + CUSTOMERS_FILE_NAME);
    }

    public void loadCustomersFromFile() {
        customers = loadFromFile(CUSTOMERS_FILE_NAME, new TypeToken<List<Customer>>(){}.getType());
        System.out.println("Clientes cargados exitosamente desde " + CUSTOMERS_FILE_NAME);
    }
    
    public void saveChangeLogsToFile() {
        saveToFile(CHANGE_LOGS_FILE_NAME, quoteChangeLogs);
        System.out.println("Historial de cambios guardado exitosamente en " + CHANGE_LOGS_FILE_NAME);
    }

    public void loadChangeLogsFromFile() {
        quoteChangeLogs = loadFromFile(CHANGE_LOGS_FILE_NAME, new TypeToken<List<ProjectChangeLog>>(){}.getType());
        System.out.println("Historial de cambios cargado exitosamente desde " + CHANGE_LOGS_FILE_NAME);
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
        ProjectChangeLog log = new ProjectChangeLog(
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

        System.out.println("----------Ingrese la informacion del cliente----------");
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
            case 1 -> ProjectStatus.QUOTE_SEND;
            case 2 -> ProjectStatus.QUOTE_REJECTED;
            case 3 -> ProjectStatus.QUOTE_ACCEPTED;
            default -> ProjectStatus.QUOTE_SEND;
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
    
    public void displayChangeLogs() {
        System.out.println("Historial de Cambios:");
        for (ProjectChangeLog log : quoteChangeLogs) {
            System.out.println(log);
        }
    }


    
}


