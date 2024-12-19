package ec.edu.espe.mole.view;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.mole.controller.ProjectController;
import ec.edu.espe.mole.model.Customer;
import ec.edu.espe.mole.model.JSONFileHandler;
import ec.edu.espe.mole.model.Project;
import ec.edu.espe.mole.model.Status;
import java.lang.reflect.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
/**
 *
 * @author Marlon Pasquel
 */

public class ProjectMenu {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void createProjectMenu(ProjectController controller, Scanner scanner) {
        
        String description;

        System.out.println("\n--- Crear proyecto ---");

    
        String projectId = generateRandomId();
        System.out.println("El ID generado para el proyecto es: " + projectId);

        System.out.print("Ingrese la descripcion del proyecto: ");
        description = scanner.nextLine().trim();
        while(description.isEmpty()) {
            System.out.println("Error: La descripcion del proyecto no puede estar vacia.");
            description = scanner.nextLine().trim();
        }

      
        String customerId = generateRandomId();
        System.out.println("El ID generado para el cliente es: " + customerId);

        System.out.print("Ingrese el nombre del cliente: ");
        String customerName = scanner.nextLine().trim();

        System.out.print("Ingrese el email del cliente: ");
        String customerEmail = scanner.nextLine().trim();

        System.out.print("Ingrese el RUC del cliente: ");
        String customerRUC = scanner.nextLine().trim();

        System.out.print("Ingrese el numero de telefono del cliente: ");
        String customerPhone = scanner.nextLine().trim();

        Customer customer = new Customer(customerName, customerEmail, customerRUC, customerPhone);

      
        System.out.print("Ingrese la fecha del inicio del proyecto (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine().trim();
        Date startDate = parseDate(startDateStr);
        while (startDate == null) {
            System.out.println("Error: Formato de fecha invalido.");
            startDateStr = scanner.nextLine().trim();
            startDate = parseDate(startDateStr);
        }

      
        System.out.print("Ingrese la cuota del proyecto: ");
        float quation = scanner.nextFloat();
        while (quation <= 0) {
            System.out.println("Precio inválido para empezar un proyecto, ingrese otro valor: ");
            quation = scanner.nextFloat();
        }

      
        System.out.println("Elija el estado del proyecto:");
        for (int i = 0; i < Status.values().length; i++) {
            System.out.println((i + 1) + ". " + Status.values()[i]);
        }
        int statusOption = scanner.nextInt();
        scanner.nextLine(); 
        while (statusOption < 1 || statusOption > Status.values().length) {
            System.out.println("Error: Opcion invalida.");
            statusOption = scanner.nextInt();
            scanner.nextLine();
        }
        Status status = Status.values()[statusOption - 1];

       
        Project project = new Project(description, status, customer, quation, startDate);

        
        System.out.println("El ID generado para el proyecto es: " + project.getProjectId());

        
        controller.createProject(project);
    }

    public static void updateProjectStatusMenu(ProjectController controller, Scanner scanner) {
        System.out.println("\n--- Actualizar estado del proyecto ---");

        JSONFileHandler<Project> handler = new JSONFileHandler<>();
        List<Project> projectList = new ArrayList<>();
        String filepath = "project.json";
        Type projectListType = new TypeToken<List<Project>>() {}.getType();
        projectList = handler.readFromFile(filepath, projectListType);

        boolean wasfounded = false;

        System.out.print("Ingrese el ID del proyecto: ");
        String projectId = scanner.nextLine().trim();
        if (projectId.isEmpty()) {
            System.out.println("Error: El ID del proyecto no puede estar vacío.");
            return;
        }

        for (Project project : projectList) {
            if (project.getProjectId().equals(projectId)) {
                System.out.print("Cambie la descripción del proyecto: ");
                String description = scanner.nextLine();
                project.setDescription(description);

                System.out.println("Cambie el estado del proyecto:");
                for (int i = 0; i < Status.values().length; i++) {
                    System.out.println((i + 1) + ". " + Status.values()[i].getDescription());
                }

                int statusOption = -1;
                do {
                    try {
                        System.out.print("Seleccione el nuevo estado (1-" + Status.values().length + "): ");
                        statusOption = scanner.nextInt();
                        scanner.nextLine(); 
                        if (statusOption < 1 || statusOption > Status.values().length) {
                            System.out.println("Error: Opción inválida. Intente nuevamente.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número entero.");
                        scanner.nextLine(); 
                    }
                } while (statusOption < 1 || statusOption > Status.values().length);

                Status status = Status.values()[statusOption - 1];
                project.setStatus(status);
                wasfounded = true;

                System.out.println("El estado del proyecto se ha cambiado a: " + status.getDescription());
                break; 
            }
        }

        if (!wasfounded) {
            System.out.println("Su proyecto no existe.");
        } else {
            handler.writeToFile(projectList, "project.json");
            System.out.println("Los cambios se han guardado correctamente.");
        }
    }

    private static Date parseDate(String dateStr) {
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    
    private static String generateRandomId() {
        return String.format("%08d", (int) (Math.random() * 100000000));
    }
}