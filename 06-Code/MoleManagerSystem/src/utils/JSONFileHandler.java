
package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author David Pilatasig
 */
public class JSONFileHandler {

    private final String filepath;
    private final Gson gson;

    public JSONHandler(String filepath) {
        this.filepath = filepath;
        this.gson = new Gson();
        initializeFile();
    }

    private void initializeFile() {
        File file = new File(filepath);
        if (!file.exists()) {
            try (Writer writer = new FileWriter(filepath)) {
                gson.toJson(new ArrayList<Person>(), writer); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public ArrayList<Person> readFromJSON() {
        try (Reader reader = new FileReader(filepath)) {
            Type listType = new TypeToken<ArrayList<Person>>() {}.getType();
            ArrayList<Person> people = gson.fromJson(reader, listType);
            return people != null ? people : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void writeToJSON(ArrayList<Person> people) {
        try (Writer writer = new FileWriter(filepath)) {
            gson.toJson(people, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
