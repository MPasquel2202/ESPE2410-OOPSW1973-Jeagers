
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

}

