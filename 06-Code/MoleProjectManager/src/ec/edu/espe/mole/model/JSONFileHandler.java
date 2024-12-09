
package ec.edu.espe.mole.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Pilatasig
 * @param <T>
 */
public class JSONFileHandler<T> {
   private final Gson gson;

    public JSONFileHandler() {
        this.gson = new Gson();
    }

    public void writeToFile(List<T> data, String filename) {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(data, writer);
            System.out.println("Data written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public List<T> readFromFile(String filename, Type typeOfT) {
        try (Reader reader = new FileReader(filename)) {
            return gson.fromJson(reader, typeOfT);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        JSONFileHandler<Project> handler = new JSONFileHandler<>();
        List<Project> projects = new ArrayList<>();
        
        Project project1 = new Project("P001", "Proyecto 1", null, new java.util.Date(), Status.IN_PROGRESS);
        projects.add(project1);

        handler.writeToFile(projects, "projects.json");

        Type projectListType = new TypeToken<List<Project>>() {}.getType();
        List<Project> readProjects = handler.readFromFile("projects.json", projectListType);
        
        for (Project project : readProjects) {
            System.out.println(project);
        }
    }
}

