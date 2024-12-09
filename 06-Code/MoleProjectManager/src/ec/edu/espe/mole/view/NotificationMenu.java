package ec.edu.espe.mole.view;

import ec.edu.espe.mole.model.Notification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Dennis Paucar
 */


public class NotificationMenu {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void manageNotificationsMenu(Scanner scanner) {
        System.out.println("\n--- Gestionar Notificaciones ---");

        System.out.print(" Ingrese el ID de la notificacion: ");
        String notificationId = scanner.nextLine().trim();
        if (notificationId.isEmpty()) {
            System.out.println("Error: El ID de la notificacion no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese un titulo para la notificacion: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Error: El titulo de la notificacion no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese el mensaje de la notificacion: ");
        String message = scanner.nextLine().trim();
        if (message.isEmpty()) {
            System.out.println("Error: El mensaje de la notificacion no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese el ID del projecto asociado a a la notificacion: ");
        String projectId = scanner.nextLine().trim();
        if (projectId.isEmpty()) {
            System.out.println("Error:El ID del projecto asociado a a la notificacion no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese la fecha de notificacion (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine().trim();
        Date notifyDate = parseDate(dateStr);
        if (notifyDate == null) {
            System.out.println("Error: Formato de fecha invalido.");
            return;
        }

        Notification notification = new Notification(notificationId, title, message, notifyDate, projectId);
        System.out.println("Notificacion creada: \n" + notification);
    }

    private static Date parseDate(String dateStr) {
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
}