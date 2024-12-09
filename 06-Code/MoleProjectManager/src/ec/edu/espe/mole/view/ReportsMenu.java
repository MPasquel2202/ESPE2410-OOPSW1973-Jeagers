package ec.edu.espe.mole.view;

import ec.edu.espe.mole.controller.ProjectController;
import ec.edu.espe.mole.model.ProjectsReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Marlon Pasquel
 */

public class ReportsMenu {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void generateAndExportReports(ProjectController projectController, Scanner scanner) {
        System.out.println("\n--- Generar y exportar reportes ---");

        System.out.print("Ingrese fecha de inicio (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine().trim();
        Date startDate = parseDate(startDateStr);
        if (startDate == null) {
            System.out.println("Error:  Formato de fecha invalido.");
            return;
        }

        System.out.print("Ingrese fecha de fin (YYYY-MM-DD): ");
        String endDateStr = scanner.nextLine().trim();
        Date endDate = parseDate(endDateStr);
        if (endDate == null) {
            System.out.println("Error: Formato de fecha invalido.");
            return;
        }

        ProjectsReport report = new ProjectsReport(projectController.filterProjectsByDate(startDate, endDate), startDate, endDate);
        System.out.println("Reporte generado. Elija el formato:");
        System.out.println("1. JSON");
        System.out.println("2. CSV");
        int exportOption = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Ingrese el nombre del archivo: ");
        String filename = scanner.nextLine().trim();

        switch (exportOption) {
            case 1:
                report.exportReportToJSON();
                System.out.println("Reporte exportado a JSON.");
                break;
            case 2:
                report.exportReportToCSV();
                System.out.println("Reporte exportado a CSV.");
                break;
            default:
                System.out.println("Error: Opcion invalida.");
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