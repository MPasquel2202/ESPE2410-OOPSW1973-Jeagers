package ec.edu.espe.view;

import ec.edu.espe.model.Customer;
import ec.edu.espe.model.DataManager;
import ec.edu.espe.model.Project;
import ec.edu.espe.model.ProjectStatus;
import ec.edu.espe.model.Report;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

/**
 *
 * @author Dennis Paucar }
 *
 */
public class Main {

    private static final String fileName = "json/projects.json";
    private static Report report = new Report(fileName);
    

    public static void mostrarMenu() {
        System.out.println("\n  __  __  ____  _      ______                                                                \n"
                + " |  \\/  |/ __ \\| |    |  ____|                                                               \n"
                + " | \\  / | |  | | |    | |__                                                                  \n"
                + " | |\\/| | |  | | |    |  __|                                                                 \n"
                + " | |  | | |__| | |____| |____                                                                \n"
                + " |_|__|_|\\____/|______|______|_            _                        _           _            \n"
                + "  / ____|         | | (_)    /_/          | |                      (_)         | |           \n"
                + " | |  __  ___  ___| |_ _  ___  _ __     __| | ___   _ __  _ __ ___  _  ___  ___| |_ ___  ___ \n"
                + " | | |_ |/ _ \\/ __| __| |/ _ \\| '_ \\   / _` |/ _ \\ | '_ \\| '__/ _ \\| |/ _ \\/ __| __/ _ \\/ __|\n"
                + " | |__| |  __/\\__ \\ |_| | (_) | | | | | (_| |  __/ | |_) | | | (_) | |  __/ (__| || (_) \\__ \\\n"
                + "  \\_____|\\___||___/\\__|_|\\___/|_| |_|  \\__,_|\\___| | .__/|_|  \\___/| |\\___|\\___|\\__\\___/|___/\n"
                + "                                                   | |            _/ |                       \n"
                + "                                                   |_|           |__/                        ");
        System.out.println("1. Funciones para Proyectos");
        System.out.println("2. Registrar Cambio de Presupuesto");
        System.out.println("3. Ver Fechas Restantes de Soporte de los Proyectos");
        System.out.println("4. Generar Reporte Mensual de Proyectos");
        System.out.println("5. Busqueda de Proyectos");
        System.out.println("6. Generar Reporte Individual de Proyecto");
        System.out.println("7. Gestion de clientes");
        System.out.println("8. Administrar Estatus de Proyecto");
        System.out.println("9. Registrar Actividades del Proyecto(historial)");
        System.out.println("10. Generar Recordatorio de Fechas Importantes");
        System.out.println("11. Generacion de Soporte Posventa");
        System.out.println("12. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public static void mostrarSubmenuBusqueda() {
        System.out.println("\nSubmenu: Busqueda de proyectos");
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
        System.out.println("3. Volver al Menu Principal");
        System.out.println("Selecione una opcion: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DataManager dataManager = new DataManager();
        dataManager.loadProjectsFromFile();
        dataManager.loadCustomersFromFile();
        dataManager.loadSupportsFromFile();

        int opcion;
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
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
                            break;
                        default:
                            System.out.println("Opcion invalida. Volviendo al Menu Principal...");
                            break;
                    }

                    break;
                case 2:
                    System.out.println("Opcion 2: Registrar Cambio de Presupuesto");
                    dataManager.modifyProjectBudget();

                    break;
                case 3:
                    System.out.println("Opción 3: Ver Fechas Restantes de Soporte de los Proyectos");
                    dataManager.displayRemainingSupportDates();
                    break;

                case 4:
                    System.out.println("Opción 4: Agrupación de Reportes del Mes Actual");
                    report.showMonthlyReports();
                    
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
                    Report report = new Report("json/projects.json");
                    report.createIndividualReport(scanner);
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
                            ;

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
                    mostrarSubmenuEstatus();
                    int opcionEstatus = scanner.nextInt();
                    switch (opcionEstatus) {
                        case 1:
                            System.out.println("Visualizar Estatus de los Proyectos");

                            break;
                        case 2:
                            System.out.println("Cambiar Estatus de Proyecto");

                            break;
                        case 3:
                            System.out.println("Ver Historial de Cambios de Estatus");

                            break;
                        case 4:
                            System.out.println("Volviendo al Menu Principal...");
                            break;
                        default:
                            System.out.println("Opcion invalida. Volviendo al Menu Principal...");
                            break;
                    }
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    dataManager.createSupport(scanner);
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
