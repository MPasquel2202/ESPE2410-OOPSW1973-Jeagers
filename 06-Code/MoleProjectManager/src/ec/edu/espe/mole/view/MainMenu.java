package ec.edu.espe.mole.view;

import ec.edu.espe.mole.controller.ProjectController;
import ec.edu.espe.mole.model.Support;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marlon Pasquel
 */

public class MainMenu {
    public static void main(String[] args) {
        ProjectController projectController = new ProjectController();
        List<Support> supports = new ArrayList<>(); 
        try (Scanner scanner = new Scanner(System.in)) {
            int option;
            
            do {
                System.out.println("========================");
                System.out.println("   Gestion de Proyectos   ");
                System.out.println("========================");
                System.out.println("1. Crear nuevo proyecto");
                System.out.println("2. Actualizar estado del proyecto");
                System.out.println("3. Listar todos los proyectos");
                System.out.println("4. Guardar proyectos a un archivo");
                System.out.println("5. Cargar proyectos desde un archivo");
                System.out.println("6. Gestionar soportes");
                System.out.println("7. Listar soportes");
                System.out.println("8. Generar y exportar reportes");
                System.out.println("9. Gestionar notificaciones");
                System.out.println("10. Salir");
                System.out.print("Elija una opcion: ");
                
                option = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (option) {
                    case 1:
                        ProjectMenu.createProjectMenu(projectController, scanner);
                        break;
                    case 2:
                        ProjectMenu.updateProjectStatusMenu(projectController, scanner);
                        break;
                    case 3:
                        projectController.listProjects();
                        break;
                    case 4:
                        System.out.print("Ingrese el nombre del archivo para guardar proyectos: ");
                        String saveFilename = scanner.nextLine();
                        projectController.saveProjectsToFile(saveFilename);
                        break;
                    case 5:
                        System.out.print("Ingrese el nombre del archivo para cargar proyectos: ");
                        String loadFilename = scanner.nextLine();
                        projectController.loadProjectsFromFile(loadFilename);
                        break;
                    case 6:
                        SupportMenu.manageSupportMenu(scanner);
                        break;
                    case 7:
                        SupportMenu.listSupports(supports);
                        break;
                    case 8:
                        ReportsMenu.generateAndExportReports(projectController, scanner);
                        break;
                    case 9:
                        NotificationMenu.manageNotificationsMenu(scanner);
                        break;
                    case 10:
                        System.out.println("Saliendo del programa. Hasta la proxima!");
                        break;
                    default:
                        System.out.println("Opcion invalida. Intente nuevamente.");
                }
                
            } while (option != 10);
        }
    }
}