package ec.edu.espe.view;

import ec.edu.espe.model.ChangeLogManager;
import ec.edu.espe.model.Customer;
import ec.edu.espe.model.DataManager;
import ec.edu.espe.model.Project;
import ec.edu.espe.model.ProjectStatus;
import ec.edu.espe.model.QuoteChangeLog;
import ec.edu.espe.model.QuoteStatusChangeLog;
import ec.edu.espe.model.Report;
import ec.edu.espe.model.ReportGenerator;
import ec.edu.espe.model.Support;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

/**
 *
 * @author Dennis Paucar
 */
public class Main {

    public String fileName = "json/projects.json";

    public static void mostrarMenu() {
        System.out.println("  __  __  ____  _      ______                                                                    \n"
                + " |  \\/  |/ __ \\| |    |  ____|                                                                   \n"
                + " | \\  / | |  | | |    | |__                                                                      \n"
                + " | |\\/| | |  | | |    |  __|                                                                     \n"
                + " | |  | | |__| | |____| |____                                                                    \n"
                + " |_|__|_|\\____/|______|______|             _                                        _            \n"
                + "  / ____|         | | (_) /_/             | |                                      | |           \n"
                + " | |  __  ___  ___| |_ _  ___  _ __     __| | ___   _ __  _ __ ___  _   _  ___  ___| |_ ___  ___ \n"
                + " | | |_ |/ _ \\/ __| __| |/ _ \\| '_ \\   / _` |/ _ \\ | '_ \\| '__/ _ \\| | | |/ _ \\/ __| __/ _ \\/ __|\n"
                + " | |__| |  __/\\__ \\ |_| | (_) | | | | | (_| |  __/ | |_) | | | (_) | |_| |  __/ (__| || (_) \\__ \\\n"
                + "  \\_____|\\___||___/\\__|_|\\___/|_| |_|  \\__,_|\\___| | .__/|_|  \\___/ \\__, |\\___|\\___|\\__\\___/|___/\n"
                + "                                                   | |               __/ |                       \n"
                + "                                                   |_|              |___/                        ");

        System.out.println("1. Funciones para Proyectos");
        System.out.println("2. Administrar Presupuestos");
        System.out.println("3. Administrar Estatus de Proyectos");
        System.out.println("4. Generacion de Soporte Posventa");
        System.out.println("5. Busqueda de Proyectos");
        System.out.println("6. Vista de historial de cambios");
        System.out.println("7. Gestion de clientes");
        System.out.println("8. Generar Reporte Individual de Proyecto");
        System.out.println("9. Generar Reporte Mensual de Proyectos");
        System.out.println("10. Ver Fechas Restantes de Soporte de los Proyectos");
        System.out.println("11. Generar Recordatorio de Fechas Importantes");
        System.out.println("12. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public static void mostrarSubmenuBusqueda() {
        System.out.println("\nSubmenu: Busqueda de Proyectos");
        System.out.println("1. Busqueda por Fechas");
        System.out.println("2. Busqueda por RUC");
        System.out.println("3. Busqueda por Estado de Proyecto");
        System.out.println("4. Volver al Menu Principal");
        System.out.print("Seleccione una opcion: ");
    }

    public static void mostrarSubmenuEstatus() {
        System.out.println("\nSubmenu: Administrar Estatus de Proyecto");
        System.out.println("1. Visualizar Estatus de los Proyectos");
        System.out.println("2. Cambiar Estatus de Proyecto");
        System.out.println("3. Ver Historial de Cambios de Estatus");
        System.out.println("4. Volver al Menu Principal");
        System.out.print("Seleccione una opcion: ");
    }

    public static void showProjectSubmenu() {
        System.out.println("\nSubmenu: Funciones de proyecto");
        System.out.println("1. Crear un nuevo proyecto");
        System.out.println("2. Visualizar lista de proyectos");
        System.out.println("3. Volver al Menu Principal");
        System.out.println("Selecione una opcion: ");
    }

    public static void showClientSubmenu() {
        System.out.println("\nSubmenu: Funciones de cliente");
        System.out.println("1. Anadir un nuevo cliente");
        System.out.println("2. Visualizar lista de clientes");
        System.out.println("3. Modificar datos de clientes");
        System.out.println("4. Volver al Menu Principal");
        System.out.println("Selecione una opcion: ");
    }

    public static void showQuoteSubmenu() {
        System.out.println("\nSubmenu: Funciones de presupuesto");
        System.out.println("1. Cambio de presupuesto");
        System.out.println("2. Visualizar lista de cambios");
        System.out.println("3. Cambiar status de presupuesto");
        System.out.println("4. Volver al Menu Principal");
        System.out.print("Selecione una opcion: ");
    }

    public static void showSupportSubmenu() {
        System.out.println("\nSubmenu: Funciones de soporte");
        System.out.println("1. Generar Soporte");
        System.out.println("2. Visualizar lista de Soporte");
        System.out.println("3. Cambiar estado de Soporte");
        System.out.println("4. Volver al Menu Principal");
        System.out.print("Selecione una opcion: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DataManager dataManager = new DataManager();
        ChangeLogManager logManager = new ChangeLogManager(dataManager);
        ReportGenerator reportGenerator = new ReportGenerator(dataManager);

        dataManager.loadProjectsFromFile();
        dataManager.loadCustomersFromFile();
        dataManager.loadChangeLogsFromFile();
        dataManager.loadQuoteStatusChangeLogsFromFile();
        dataManager.loadSupportsFromFile();

        int opcion;
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    boolean backToMenu = false;
                    while (!backToMenu) {
                        showProjectSubmenu();
                        int projectOptions = scanner.nextInt();
                        switch (projectOptions) {
                            case 1:
                                System.out.println("Opcion 1: Crear Proyecto");
                                Project newProject = dataManager.askForProjectData();
                                dataManager.addProject(newProject);
                                dataManager.saveProjectsToFile();
                                System.out.println("Proyectos guardados.");
                                break;
                            case 2:
                                System.out.println("Opcion 2: Visualizar Proyectos");

                                System.out.println("\n--- Detalles de los Proyectos ---");

                                for (Project project : dataManager.getProjects()) {
                                    project.displayProjectData();
                                }
                                break;
                            case 3:
                                System.out.println("Volviendo al Menu Principal...");
                                backToMenu = true;
                                break;
                            default:
                                System.out.println("Opcion invalida. Volviendo al Menu Principal...");
                                backToMenu = true;
                                break;
                        }

                    }

                    break;
                case 2:
                    showQuoteSubmenu();
                    int quoteoption = scanner.nextInt();

                    switch (quoteoption) {
                        case 1:
                            System.out.println("Registrar Cambio de Presupuesto");
                            System.out.print("Ingrese el ID del proyecto a modificar: ");
                            scanner.nextLine();
                            String projectId = scanner.nextLine();

                            System.out.print("Ingrese el nuevo presupuesto: ");
                            double newQuote = scanner.nextDouble();

                            dataManager.updateProjectQuote(projectId, newQuote);
                            break;
                        case 2:
                            System.out.println("Visualizar Lista de cambios");
                            for (QuoteChangeLog quoteChangeLog : dataManager.getQuoteChangeLogs()) {
                                quoteChangeLog.displayChangeLog();
                            }
                            break;
                        case 3:
                            System.out.println("Cambiar Status de Presupuesto");
                            System.out.print("Ingrese el ID del proyecto para cambiar el estado de cotizacion: ");
                            scanner.nextLine();
                            String quoteStatusProjectId = scanner.nextLine();

                            dataManager.updateProjectQuoteStatus(quoteStatusProjectId);
                            break;

                        case 4:
                            System.out.println("Volviendo al Menu Principal...");
                            break;
                        default:
                            System.out.println("Opcion invalida. Volviendo al Menu Principal...");
                            break;
                    }

                    break;
                case 3:
                    mostrarSubmenuEstatus();
                    int opcionEstatus = scanner.nextInt();
                    switch (opcionEstatus) {
                        case 1:
                            System.out.println("Visualizar Estatus de los Proyectos");
                            dataManager.displayProjectsWithStatus();
                            break;
                        case 2:
                            System.out.println("Cambiar Estatus de Proyecto");
                            System.out.print("Ingrese el ID del proyecto a modificar status: ");
                            scanner.nextLine();
                            String statusProjectId = scanner.nextLine();
                            dataManager.updateProjectStatus(statusProjectId);
                            break;
                        case 3:
                            System.out.println("Ver Historial de Cambios de Estatus");
                            for (QuoteStatusChangeLog quoteStatusChangeLog : dataManager.getQuoteStatusChangeLogs()) {
                                quoteStatusChangeLog.displayStatusChangeLog();
                            }
                            break;

                        case 4:
                            System.out.println("Volviendo al Menu Principal...");
                            break;
                        default:
                            System.out.println("Opcion invalida. Volviendo al Menu Principal...");
                            break;
                    }
                    break;
                case 4:

                    boolean backToMenu2 = false;
                    while (!backToMenu2) {
                        showSupportSubmenu();
                        int supportOption = scanner.nextInt();
                        switch (supportOption) {
                            case 1:
                                dataManager.loadProjectsFromFile();
                                System.out.println("Generar un soporte");
                                dataManager.generateSupport();
                                break;
                            case 2:
                                System.out.println("Visualizar Soportes");
                                System.out.println("\n--- Detalles de los Soportes ---");

                                for (Support support : dataManager.getSupports()) {
                                    support.displaySupportData();
                                }
                                break;
                            case 3:
                                System.out.println("Cambiar estado de soporte");
                                dataManager.closeSupport();
                                break;

                            case 4:
                                System.out.println("Volviendo al Menu Principal...");
                                backToMenu2 = true;
                                break;
                            default:
                                System.out.println("Opcion invalida. Volviendo al Menu Principal...");
                                backToMenu2 = true;
                                break;
                        }
                    }
                    break;
                case 5:
                    mostrarSubmenuBusqueda();
                    int opcionBusqueda = scanner.nextInt();
                    switch (opcionBusqueda) {
                        case 1:
                            System.out.println("Busqueda por Fechas");

                            break;
                        case 2:
                            System.out.println("Busqueda por RUC");

                            break;
                        case 3:
                            System.out.println("Busqueda por Estado de Proyecto");

                            break;
                        case 4:
                            System.out.println("Volviendo al Menu Principal...");
                            break;
                        default:
                            System.out.println("Opcion invalida. Volviendo al Menu Principal...");
                            break;
                    }
                    break;
                case 6:
                    System.out.println("Opcion 6: Historial de cambios");
                    logManager.displayGroupedLogs();

                    break;
                case 7:
                    showClientSubmenu();
                    int clientOption = scanner.nextInt();
                    scanner.nextLine();
                    switch (clientOption) {
                        case 1:
                            System.out.println("Opcion 1: Anadir nuevo cliente");
                            dataManager.askForCustomerData();
                            break;
                        case 2:
                            System.out.println("Opcion 2: Mostrar lista de clientes");
                            for (Customer customer : dataManager.getCustomers()) {
                                customer.displayCustomerDetails();
                            }
                            break;
                        case 3:
                            System.out.println("Opcion 3: Modificar datos de un cliente");
                            System.out.print("Ingrese el ID del cliente a modificar: ");
                            String customerId = scanner.nextLine();
                            dataManager.modifyCustomerByCode(customerId);
                            break;
                        case 4:
                            System.out.println("Volviendo al Menu Principal...");
                            break;
                        default:
                            System.out.println("Opcion invalida. Volviendo al Menu Principal...");
                            break;
                    }
                    break;
                case 8:
                    System.out.println("Opcion 8: Generacion individial de reportes");
                    System.out.println("Generar Informe de Proyecto");
                    System.out.print("Ingrese el ID del proyecto para generar el informe: ");
                    scanner.nextLine();
                    String projectIdForReport = scanner.nextLine();

                    Project projectForReport = null;
                    for (Project project : dataManager.getProjects()) {
                        if (project.getProjectId().equals(projectIdForReport)) {
                            projectForReport = project;
                            break;
                        }
                    }

                    if (projectForReport != null) {

                        reportGenerator.displayReport(projectForReport);
                        System.out.println("Informe generado y guardado en formato JSON.");
                    } else {
                        System.out.println("Proyecto no encontrado.");
                    }
                    break;

                case 9:
                    System.out.println("Opción 4: Agrupación de Reportes del Mes Actual");
                    reportGenerator.showMonthlyReports();
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, seleccione una opcion valida.");
                    break;
            }
        }
        scanner.close();
    }
}
