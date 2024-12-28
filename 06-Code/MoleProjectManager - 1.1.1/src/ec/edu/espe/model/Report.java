package ec.edu.espe.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author David Pilatasig
 */
public class Report {
    private String fileName;
    
    public Report(String fileName){
        this.fileName=fileName;
    }
    
    public void createIndividualReport(Scanner scanner) {
    ArrayList<Project> projects = new ArrayList<>();
    File file = new File(fileName);
    boolean isfound=false;
    if (file.exists()) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(file)) {
            Type projectListType = new TypeToken<List<Project>>() {}.getType();
            projects = gson.fromJson(reader, projectListType);
            System.out.println("Proyectos cargados exitosamente desde " + fileName);
            
            System.out.print("Ingrese el ID del proyecto: ");
            scanner.nextLine();
            String idsearched = scanner.nextLine();

            for (Project project : projects) {
                if (idsearched.contentEquals(project.getProjectId())) {
                    writeReport(project);
                }
            }
            if(!isfound){
                System.out.println("No existe proyecto Asociado con el ID proporcionado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("No se encontró el archivo de proyectos en " + fileName);
    }
}
    
    public void writeReport(Project project){
            System.out.println("\n-----Reporte del proyecto " + project.getProjectId()+ " -----");
            System.out.println("El ID del proyecto es : " + project.getProjectId());
            System.out.println("El estado del proyecto es: " + project.getOperationalStatus());
            System.out.println("Descripción del proyecto: " + project.getProjectDescription());
            System.out.println("La cuota inicial del proyecto es: " + project.getStartquote());
            System.out.println("EL estado de la cuota es: " + project.getQuoteStatus());
            System.out.println("El proyecto inicio el día: " + project.getStartDate());
            System.out.println("El proyecto termina: "+ project.getClosingDate());
            System.out.println("EL proyecto está pagado: " + project.isPaid());
            System.out.println("El proyecto es de una entidad publica: " + project.isIsPublic());
            
}
}
