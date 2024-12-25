package ec.edu.espe.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author Dennis Paucar
 */
public class DataManager {
    private ArrayList<Project> projects;
    private static int projectCounter = 1;  
    private static final String FILE_NAME = "json/projects.json"; 
    
    public DataManager() {  
        projects = new ArrayList<>();
    }

    public void addProject(Project project) {
        projects.add(project);
        System.out.println("Proyecto agregado con exito: " + project.getProjectTitle());
    }
    
    public List<Project> getProjects() {
        return projects;
    }
// no mover se utilizara despues para buscar proyectos 
//    public void listProjects() {
//        
//        if (projects.isEmpty()) {
//            System.out.println("No hay proyectos para mostrar.");
//        } else {
//            System.out.println("Lista de proyectos:");
//            
//            for (Project project : projects) {
//                System.out.println(project.getProjectId() + " - " + project.getProjectTitle());
//            }
//        }
//    }
       
    public void saveProjectsToFile() {
        
        File directory = new File("json");
        if (!directory.exists()) {
            directory.mkdir();  
        }

      
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(projects, writer);  
            System.out.println("Proyectos guardados exitosamente en " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadProjectsFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            Gson gson = new Gson();
            try (Reader reader = new FileReader(file)) {
                Type projectListType = new TypeToken<List<Project>>(){}.getType();
                projects = gson.fromJson(reader, projectListType);  
                System.out.println("Proyectos cargados exitosamente desde " + FILE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se encontro el archivo de proyectos en " + FILE_NAME);
        }
    }

    public Project askForProjectData() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Ingrese el titulo del proyecto: ");
        String title = scanner.nextLine();

        System.out.print("Ingrese la descripcion: ");
        String description = scanner.nextLine();

        

        Date startDate = new Date();  

        
        Date closingDate = null;
        boolean validDate = false;

        
        do {
        System.out.print("Fecha de cierre (yyyy-MM-dd): ");
        String inputDate = scanner.nextLine();

        
        if (inputDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            closingDate = null;
            try {
                closingDate = dateFormat.parse(inputDate);

                
                if (closingDate.before(startDate)) {
                    System.out.println("La fecha de cierre no puede ser anterior a la fecha de inicio. Intenta nuevamente.");
                } else {
                    validDate = true;  
                }
            } catch (ParseException e) {
                System.out.println("Formato de fecha incorrecto. Intenta nuevamente.");
            }
        } else {
            System.out.println("Por favor, ingresa la fecha en el formato correcto (yyyy-MM-dd).");
        }
    } while (!validDate);
        
        System.out.print("Presupuesto inicial: ");
        double quote = scanner.nextDouble();
        
        ProjectStatus operationalStatus = ProjectStatus.CREATED;

        
        System.out.println("Estado de cotizacion: ");
        System.out.println("1. Quote Sended");
        System.out.println("2. Quote Rejected");
        System.out.println("3. Quote Accepted");
        System.out.print("Seleccione el numero: ");
        int quoteStatusInput = scanner.nextInt();
        ProjectStatus quoteStatus = switch (quoteStatusInput) {
            case 1 -> ProjectStatus.QUOTE_SEND;
            case 2 -> ProjectStatus.QUOTE_REJECTED;
            case 3 -> ProjectStatus.QUOTE_ACCEPTED;
            default -> ProjectStatus.QUOTE_SEND;
        };

        scanner.nextLine();  
        String facturado;
        boolean isInvoiced = false;
        do {
            System.out.print("Esta facturado? (Si/No): ");
            facturado = scanner.nextLine().trim().toLowerCase();
            if (facturado.equals("si")) {
                isInvoiced = true;
                break;
            } else if (facturado.equals("no")) {
                isInvoiced = false;
                break;
            } else {
                System.out.println("Por favor ingrese 'Si' o 'No'.");
            }
        } while (true);

        
        boolean paid = false;
        if (isInvoiced) {
            System.out.print("Esta pagado? (Si/No): ");
            String paidInput = scanner.nextLine().trim().toLowerCase();
            if (paidInput.equals("si")) {
                paid = true;
            } else if (paidInput.equals("no")) {
                paid = false;
            } else {
                System.out.println("Por favor ingrese 'Si' o 'No'. Se asumira 'No'.");
                paid = false;  
            }
        }

        
        System.out.print("Es proyecto publico? (Si/No): ");
        String publicInput = scanner.nextLine().trim().toLowerCase();
        boolean isPublic = publicInput.equals("si");

       
        String projectId = "Prj-" + String.format("%04d", projectCounter++);
        
        
        return new Project(title, projectId, description, startDate, closingDate, quote, operationalStatus, quoteStatus, paid, isInvoiced, isPublic);
    }
    
    
}
