package ec.edu.espe.mole.view;

import ec.edu.espe.mole.model.Support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marlon Pasquel
 */

public class SupportMenu {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void manageSupportMenu(Scanner scanner) {
        System.out.println("\n--- Gestionar soportes ---");

        System.out.print("Ingrese ID del soporte: ");
        String supportId = scanner.nextLine().trim();
        if (supportId.isEmpty()) {
            System.out.println("Error: El ID del soporte no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese el ID del proyecto asociado al soporte: ");
        String relatedProjectId = scanner.nextLine().trim();
        if (relatedProjectId.isEmpty()) {
            System.out.println("Error: Este campo no puede estar vacio.");
            return;
        }

        System.out.print("Ingrese fecha de inicio del soporte (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine().trim();
        Date startDate = parseDate(startDateStr);
        if (startDate == null) {
            System.out.println("Error: Formato invalido.");
            return;
        }

        System.out.print("Ingrese la fecha de fin de soporte (YYYY-MM-DD): ");
        String endDateStr = scanner.nextLine().trim();
        Date endDate = parseDate(endDateStr);
        if (endDate == null) {
            System.out.println("Error: Formato de fecha invalido.");
            return;
        }

        System.out.print("Ingrese estado del soporte: ");
        String status = scanner.nextLine().trim();
        if (status.isEmpty()) {
            System.out.println("Error: Este campo no puede estar vacio.");
            return;
        }

        Support support = new Support(supportId, relatedProjectId, startDate, endDate, status);
        System.out.println("Soporte generado: \n" + support);
    }

    public static void listSupports(List<Support> supports) {
        System.out.println("\n--- Lista de soportes ---");
        if (supports.isEmpty()) {
            System.out.println("No se encontraron soportes.");
            return;
        }

        for (Support support : supports) {
            System.out.println(support);
        }
    }

    private static Date parseDate(String dateStr) {
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
}