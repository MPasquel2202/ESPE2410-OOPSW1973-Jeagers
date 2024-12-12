package ec.edu.espe.mole.model;

import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ProjectsReport {

    public void generateReportFromJSON(String jsonFilePath) {
        try (FileReader reader = new FileReader(jsonFilePath)) {
            JsonElement rootElement = JsonParser.parseReader(reader);
            JsonArray projectsArray = rootElement.getAsJsonArray();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingrese el codigo del proyecto: ");
            String inputCode = scanner.nextLine();

            boolean found = false;

            for (JsonElement projectElement : projectsArray) {
                JsonObject project = projectElement.getAsJsonObject();
                String projectId = project.get("projectId").getAsString();

                if (projectId.equals(inputCode)) {
                    found = true;
                    System.out.println("Detalles del Proyecto:");
                    System.out.println("ID del Proyecto: " + projectId);
                    System.out.println("Estado: " + project.get("status").getAsString());
                    System.out.println("Fecha de inicio: " + project.get("startDate").getAsString());

                    JsonObject customer = project.getAsJsonObject("customer");
                    if (customer != null) {
                        System.out.println("Detalles del Cliente:");
                        System.out.println("  ID Cliente: " + customer.get("customerId").getAsString());
                        System.out.println("  Nombre: " + customer.get("name").getAsString());
                        System.out.println("  Email: " + customer.get("email").getAsString());
                        System.out.println("  RUC: " + customer.get("ruc").getAsString());
                        System.out.println("  Teléfono: " + customer.get("phoneNumber").getAsString());
                    }
                    break;
                }
            }

            if (!found) {
                System.out.println("No se encontró el proyecto con el código proporcionado.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }
}
