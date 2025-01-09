package ec.edu.espe.mole.view;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ec.edu.espe.mole.controller.ProjectController;
import ec.edu.espe.mole.model.Support;

import java.io.FileReader;
import java.io.IOException;
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

        if (!authenticate()) {
            System.out.println("Autenticacion fallida, vuelva a intentarlo.");
            return;
        }

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
                System.out.println("7. Salir");
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
                        ReportsMenu.generateReportFromJSON(scanner);
                        break;

                    case 7:
                        System.out.println("Saliendo del programa. Hasta la próxima!");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }

            } while (option != 7);
        }
    }

    private static boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        String correctUsername;
        String correctPasswordEncrypted;

        try {
            FileReader reader = new FileReader("src/config/Credentials.json");
            Gson gson = new Gson();

            JsonObject credentials = JsonParser.parseReader(reader).getAsJsonObject();
            correctUsername = credentials.get("username").getAsString();
            correctPasswordEncrypted = credentials.get("password").getAsString();
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo de credenciales: " + e.getMessage());
            return false;
        }

        System.out.println("==== Autenticación Requerida ====");
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        String encryptedEnteredPassword = encryptPasswordToASCII(password);

        if (username.equals(correctUsername) && encryptedEnteredPassword.equals(correctPasswordEncrypted)) {
            System.out.println("Autenticación completada. ¡Bienvenido!");
            return true;
        } else {
            System.out.println("Usuario o contraseña inválidos.");
            return false;
        }
    }

    private static String encryptPasswordToASCII(String password) {
        StringBuilder encryptedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            encryptedPassword.append((int) password.charAt(i));
            if (i < password.length() - 1) {
                encryptedPassword.append(" ");
            }
        }
        return encryptedPassword.toString();
    }
}
