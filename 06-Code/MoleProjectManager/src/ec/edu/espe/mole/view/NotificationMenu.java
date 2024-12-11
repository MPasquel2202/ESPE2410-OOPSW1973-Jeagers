package ec.edu.espe.mole.view;

import ec.edu.espe.mole.model.Notification;
import ec.edu.espe.mole.model.Project; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Dennnis Paucar
 */

public class NotificationMenu {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final List<Notification> notifications = new ArrayList<>();

 
    private static final List<Project> projects = new ArrayList<>();

    public static void manageNotificationsMenu(Scanner scanner) {
        System.out.println("\n--- Gestionar Notificaciones ---");

        System.out.print("Ingrese el ID de la notificación: ");
        String notificationId = scanner.nextLine().trim();
        if (notificationId.isEmpty()) {
            System.out.println("Error: El ID de la notificación no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese un título para la notificación: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Error: El título de la notificación no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese el mensaje de la notificación: ");
        String message = scanner.nextLine().trim();
        if (message.isEmpty()) {
            System.out.println("Error: El mensaje de la notificación no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese el ID del proyecto asociado a la notificación: ");
        String projectId = scanner.nextLine().trim();
        if (projectId.isEmpty()) {
            System.out.println("Error: El ID del proyecto asociado no puede estar vacío.");
            return;
        }

        
        Project project = findProjectById(projectId);
        if (project == null) {
            System.out.println("Error: Proyecto no encontrado.");
            return;
        }

        System.out.print("Ingrese la fecha de notificación (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine().trim();
        Date notifyDate = parseDate(dateStr);
        if (notifyDate == null) {
            System.out.println("Error: Formato de fecha inválido.");
            return;
        }

       
        Notification notification = new Notification(notificationId, title, message, notifyDate, projectId);
        notification.setProject(project); 
        notifications.add(notification);
        System.out.println("Notificación creada: \n" + notification);
    }

    private static Project findProjectById(String projectId) {
        for (Project project : projects) {
            if (project.getProjectId().equals(projectId)) {
                return project;
            }
        }
        return null; 
    }

    private static Date parseDate(String dateStr) {
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static List<Notification> getNotifications() {
        return notifications;
    }

   
    public static void addProject(Project project) {
        projects.add(project);
    }

    public static List<Project> getProjects() {
        return projects;
    }
}

