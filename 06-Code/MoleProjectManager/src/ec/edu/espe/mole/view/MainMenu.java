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
                System.out.println("4. Gestionar soportes");
                System.out.println("5. Listar soportes");
                System.out.println("6. Generar y exportar reportes");
                System.out.println("7. Gestionar notificaciones");
                System.out.println("8. Salir");
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
                        SupportMenu.manageSupportMenu(scanner);
                        break;
                    case 5:
                        SupportMenu.listSupports(supports);
                        break;
                    case 6:
                        ReportsMenu.generateAndExportReports(projectController, scanner);
                        break;
                    case 7:
                        NotificationMenu.manageNotificationsMenu(scanner);
                        break;
                    case 8:
                        System.out.println("Saliendo del programa. Hasta la proxima!");
                        break;
                    default:
                        System.out.println("Opcion invalida. Intente nuevamente.");
                }
                
            } while (option != 8);
        }
    }
}