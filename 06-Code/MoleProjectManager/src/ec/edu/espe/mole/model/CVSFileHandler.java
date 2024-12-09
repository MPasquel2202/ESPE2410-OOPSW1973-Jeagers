
package ec.edu.espe.mole.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Pilatasig
 */
public class CVSFileHandler {
    public <T> void writeToFile(List<T> data, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (T record : data) {
                writer.write(record.toString().replaceAll("\n", ","));
                writer.newLine();
            }
            System.out.println("Data written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

     public List<String[]> readFromFile(String filename) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                records.add(fields);
            }
            System.out.println("Data read from " + filename);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return records;
    }

      public static void main(String[] args) {
        CVSFileHandler handler = new CVSFileHandler();
        List<String> data = new ArrayList<>();
        data.add("ID,Description,Status");
        data.add("P001,Proyecto 1,In Progress");

        handler.writeToFile(data, "projects.csv");

        List<String[]> readData = handler.readFromFile("projects.csv");
        for (String[] record : readData) {
            for (String field : record) {
                System.out.print(field + " ");
            }
            System.out.println();}}
}
