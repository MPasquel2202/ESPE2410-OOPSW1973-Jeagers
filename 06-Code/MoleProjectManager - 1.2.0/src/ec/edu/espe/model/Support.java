package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author David Pilatasig
 */
public class Support {

    private String supportId;
    private String asociatedProjectId;
    private String Description;
    private Date startDate;
    private Date endDate;

    public Support(String idSupport, String asociatedProject, String Description, Date startDate, Date endDate) {
        this.supportId = idSupport;
        this.asociatedProjectId = asociatedProject;
        this.Description = Description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public void displaySupportData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        
        System.out.println("+--------------------------------------------------------------+");
        System.out.printf("| %-60s |\n", "Detalles del Soporte");
        System.out.println("+--------------------------------------------------------------+");

        
        System.out.printf("| %-18s | %-45s |\n", "ID Soporte:", supportId);
        System.out.printf("| %-18s | %-45s |\n", "Proyecto:", projectTitle);
        System.out.printf("| %-18s | %-45s |\n", "Detalles:", supportDetails);
        System.out.println("+--------------------------------------------------------------+");

        
        System.out.printf("| %-18s | %-45s |\n", "Fecha de Inicio:", startDate != null ? dateFormat.format(startDate) : "No definida");
        System.out.printf("| %-18s | %-45s |\n", "Fecha de Fin:", endDate != null ? dateFormat.format(endDate) : "No definida");
        System.out.println("+--------------------------------------------------------------+");

        
        System.out.printf("| %-18s | %-45s |\n", "Estado del Soporte:", supportStatus != null ? supportStatus : "Sin definir");
        System.out.println("+--------------------------------------------------------------+");

        
        System.out.printf("| %-18s | %-45s |\n", "Contacto:", contact != null ? contact : "No definido");
        System.out.printf("| %-18s | %-45s |\n", "Comentarios:", comments != null ? comments : "Ninguno");
        System.out.println("+--------------------------------------------------------------+");
    }

    public long getRemainingDays() {
        Date today = new Date();
        return (endDate.getTime() - today.getTime()) / (1000 * 60 * 60 * 24);
    }

    public String getSupportId() {
        return supportId;
    }

    public String getAsociatedProjectId() {
        return asociatedProjectId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void writeSupportOfProject() {
        long remainingDays = getRemainingDays();

        System.out.println("-------->  Soportes del proyecto  <---------");
        System.out.println("ID del soporte: " + supportId);
        System.out.println("Descripción: " + Description);
        System.out.println("Fin del soporte: " + endDate);

        if (remainingDays > 0) {
            System.out.println("Días restantes: " + remainingDays + " días");
        } else {
            System.out.println("El soporte ya ha expirado.");
        }
    }

}
