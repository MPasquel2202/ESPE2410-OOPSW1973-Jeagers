package ec.edu.espe.projects.model;

/**
 *
 * @author Dennis Paucar
 */
public class Date {
    private int day;
    private int month;
    private int year;
    private String formattedDate;
    /**
     * @return Formatted Date
     */
    public String getFormattedDate() {
        return formattedDate;
    }

    /**
     * @param day the day to set
     * @param month the month to set
     * @param year the year to set
     */
    public void setDate(int day,int month,int year) {
        this.day = day;
        this.month=month;
        this.year=year;
    }

    }
    
    

