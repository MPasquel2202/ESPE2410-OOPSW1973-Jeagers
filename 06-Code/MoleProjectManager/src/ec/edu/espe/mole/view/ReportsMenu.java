package ec.edu.espe.mole.view;

import ec.edu.espe.mole.model.ProjectsReport;
import java.util.Scanner;

public class ReportsMenu {

    public static void generateReportFromJSON(Scanner scanner) {
       
        String jsonFilePath = "C:\\Users\\user\\ESPE2410-OOPSW1973-Jeagers\\06-Code\\MoleProjectManager\\project.json";

        
        ProjectsReport report = new ProjectsReport();
     
        report.generateReportFromJSON(jsonFilePath);
    }
}
