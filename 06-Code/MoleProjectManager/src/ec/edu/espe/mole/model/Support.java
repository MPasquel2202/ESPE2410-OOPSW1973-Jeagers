package ec.edu.espe.mole.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Dennis Paucar
 */
public class Support {

    private String supportId;
    private String relatedProjectId;
    private Date startDate;
    private Date endDate;
    private String status;

    public Support(String relatedProjectId, int supportType, String format) {
        this.supportId = generateSupportId(supportType, format);
        this.relatedProjectId = relatedProjectId;
        this.startDate = new Date();
        this.endDate = calculateEndDate(supportType);
        this.status = "Active";
    }

    private String generateSupportId(int supportType, String format) {
        String idPart;
        if (supportType == 1 && format.equals("8*5")) {
            idPart = "01";
        } else if (supportType == 1 && format.equals("24*7")) {
            idPart = "02";
        } else if (supportType == 3 && format.equals("8*5")) {
            idPart = "03";
        } else if (supportType == 3 && format.equals("24*7")) {
            idPart = "04";
        } else if (supportType == 5 && format.equals("8*5")) {
            idPart = "05";
        } else if (supportType == 5 && format.equals("24*7")) {
            idPart = "06";
        } else {
            throw new IllegalArgumentException("Seleccion Invalida, vuelva a intentarlo.");
        }
        return String.format("SRV_%s (%dA %s)", idPart, supportType, format);
    }

    private Date calculateEndDate(int supportType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.startDate);
        calendar.add(Calendar.YEAR, supportType);
        return calendar.getTime();
    }

    public String getSupportId() {
        return supportId;
    }

    public String getRelatedProjectId() {
        return relatedProjectId;
    }

    public void setRelatedProjectId(String relatedProjectId) {
        this.relatedProjectId = relatedProjectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public boolean isSupportNearEnd(int daysBefore) {
        if (endDate == null) {
            return false;
        }
        long currentTime = new Date().getTime();
        long endTime = endDate.getTime();
        long daysDifference = (endTime - currentTime) / (1000 * 60 * 60 * 24);
        return daysDifference >= 0 && daysDifference <= daysBefore;
    }

    @Override
    public String toString() {
        return "Support ID: " + supportId + "\n"
                + "Related Project ID: " + relatedProjectId + "\n"
                + "Start Date: " + startDate + "\n"
                + "End Date: " + endDate + "\n"
                + "Status: " + status;
    }
}
