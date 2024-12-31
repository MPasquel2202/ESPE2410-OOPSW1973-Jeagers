package ec.edu.espe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author David Pilatasig
 */
public class Support {

    private String idSupport;
    private String asociatedProjectId;
    private String Description;
    private Date startDate;
    private Date endDate;

    public Support(String idSupport, String asociatedProject, String Description, Date startDate, Date endDate) {
        this.idSupport = idSupport;
        this.asociatedProjectId = asociatedProject;
        this.Description = Description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getRemainingDays() {
        Date today = new Date();
        return (endDate.getTime() - today.getTime()) / (1000 * 60 * 60 * 24);
    }

    public String getIdSupport() {
        return idSupport;
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

        System.out.println("+----------Soportes del proyecto----------+");
        System.out.println("ID del soporte: " + idSupport);
        System.out.println("Descripción: " + Description);
        System.out.println("Fin del soporte: " + endDate);

        if (remainingDays > 0) {
            System.out.println("Días restantes: " + remainingDays + " días");
        } else {
            System.out.println("El soporte ya ha expirado.");
        }
    }

}
