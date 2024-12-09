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
import java.util.Scanner;
import java.util.List;
/**
 *
 * @author Marlon Pasquel
 */

public class ProjectMenu {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void createProjectMenu(ProjectController controller, Scanner scanner) {
        
        JSONFileHandler<Project> handler=new JSONFileHandler<>();
        List<Project> projectlist= new ArrayList<>();
        String filepath="projects.json";
        
        Type projectListType=new TypeToken<List<Project>>() {}.getType();
        projectlist=handler.readFromFile(filepath, projectListType);
        
        System.out.println("\n--- Crear proyecto ---");

        System.out.print("Ingrese el ID del proyecto: ");
        String projectId = scanner.nextLine().trim();
        if (projectId.isEmpty()) {
            System.out.println("Error: El ID del proyecto no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese la descripcion del proyecto: ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            System.out.println("Error: La descripcion del proyecto no puede estar vacia.");
            return;
        }

        System.out.print("Ingrese el ID del cliente: ");
        String customerId = scanner.nextLine().trim();

        System.out.print("Ingrese el nombre del cliente: ");
        String customerName = scanner.nextLine().trim();

        System.out.print("Ingrese el email del cliente: ");
        String customerEmail = scanner.nextLine().trim();

        System.out.print("Ingrese el RUC del cliente: ");
        String customerRUC = scanner.nextLine().trim();

        System.out.print("Ingrese el numero de telefono del cliente: ");
        String customerPhone = scanner.nextLine().trim();

        Customer customer = new Customer(customerId, customerName, customerEmail, customerRUC, customerPhone);

        System.out.print("Ingrese la fecha del inicio del proyecto (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine().trim();
        Date startDate = parseDate(startDateStr);
        if (startDate == null) {
            System.out.println("Error: Formato de fecha invalido.");
            return;
        }

        System.out.println("Elija el estado del proyecto:");
        for (int i = 0; i < Status.values().length; i++) {
            System.out.println((i + 1) + ". " + Status.values()[i]);
        }
        int statusOption = scanner.nextInt();
        scanner.nextLine(); 
        if (statusOption < 1 || statusOption > Status.values().length) {
            System.out.println("Error: Opcion invalida.");
            return;
        }
        Status status = Status.values()[statusOption - 1];

        controller.createProject(projectId, description, customer, startDate, status);
        
        handler.writeToFile(projectlist, filepath);
    }

    public static void updateProjectStatusMenu(ProjectController controller, Scanner scanner) {
        System.out.println("\n--- Actualizar estado del proyecto ---");
        
        String filename="projects.json";
        JSONFileHandler JSONlist= new JSONFileHandler();
        Type projectListType=new TypeToken<List<Project>>() {}.getType();
        JSONlist.readFromFile(filename, projectListType);
        
        
        System.out.print("Ingrese el ID del proyecto: ");
        String projectId = scanner.nextLine().trim();
        if (projectId.isEmpty()) {
            System.out.println("Error: El ID del proyecto no puede estar vacio.");
            return;
        }

        System.out.println("Ingrese un nuevo estado:");
        for (int i = 0; i < Status.values().length; i++) {
            System.out.println((i + 1) + ". " + Status.values()[i]);
        }
        int statusOption = scanner.nextInt();
        scanner.nextLine(); 
        if (statusOption < 1 || statusOption > Status.values().length) {
            System.out.println("Error: Opcion invalida.");
            return;
        }

        Status newStatus = Status.values()[statusOption - 1];
        controller.updateProjectStatus(projectId, newStatus);
    }

    private static Date parseDate(String dateStr) {
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
}
