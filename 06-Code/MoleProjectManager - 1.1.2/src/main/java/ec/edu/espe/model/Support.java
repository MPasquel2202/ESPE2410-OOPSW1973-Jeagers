package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author David Pilatasig
 */
public class Support {
    private String supportId;
    private String projectId;
    private String projectDescription;
    private String supportDetails;
    private Date startDate;
    private Date endDate;
    private String supportStatus;
    private int durationYears;        
    private String scheduleType;       

   
    
    public Support(String supportId, String projectId, String projectDescription, String supportDetails, 
               Date startDate, Date endDate, String supportStatus, int durationYears, String scheduleType) {
    this.supportId = supportId;
    this.projectId = projectId;
    this.projectDescription = projectDescription;
    this.supportDetails = supportDetails;
    this.startDate = startDate;
    this.endDate = endDate;
    this.supportStatus = supportStatus;
    this.durationYears = durationYears;
    this.scheduleType = scheduleType;
}


    
    
    public void displaySupportData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        
        System.out.println("+--------------------------------------------------------------+");
        System.out.printf("| %-60s |\n", "Detalles del Soporte");
        System.out.println("+--------------------------------------------------------------+");

        
        System.out.printf("| %-18s | %-45s |\n", "ID Soporte:", supportId);
        System.out.printf("| %-18s | %-45s |\n", "Proyecto:", projectId);
        System.out.printf("| %-18s | %-45s |\n", "Detalles:", supportDetails);
        System.out.println("+--------------------------------------------------------------+");

        
        System.out.printf("| %-18s | %-45s |\n", "Fecha de Inicio:", startDate != null ? dateFormat.format(startDate) : "No definida");
        System.out.printf("| %-18s | %-45s |\n", "Fecha de Fin:", endDate != null ? dateFormat.format(endDate) : "No definida");
        System.out.println("+--------------------------------------------------------------+");

        
        System.out.printf("| %-18s | %-45s |\n", "Estado del Soporte:", supportStatus != null ? supportStatus : "Sin definir");
        System.out.println("+--------------------------------------------------------------+");
        
        System.out.printf("| %-18s | %-45s |\n", "Duracion (anios):", durationYears);
        System.out.printf("| %-18s | %-45s |\n", "Horario:", scheduleType);
        System.out.println("+--------------------------------------------------------------+");


     
    }
    
    

    public String getSupportId() {
        return supportId;
    }

    public void setSupportId(String supportId) {
        this.supportId = supportId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getSupportDetails() {
        return supportDetails;
    }

    public void setSupportDetails(String supportDetails) {
        this.supportDetails = supportDetails;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSupportStatus() {
        return supportStatus;
    }

    public void setSupportStatus(String supportStatus) {
        this.supportStatus = supportStatus;
    }
    
    public int getDurationYears() {
        return durationYears;
    }

    public void setDurationYears(int durationYears) {
        this.durationYears = durationYears;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

}
