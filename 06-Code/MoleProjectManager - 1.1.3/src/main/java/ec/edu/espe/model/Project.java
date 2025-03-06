package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author Dennis Paucar
 */
public class Project implements ProjectData {

    private String projectId;
    private String projectTitle; 
    private String projectDescription;
    private Customer customer;
    private Date startDate;
    private Date closingDate;
    private double startquote;
    private ProjectStatus operationalStatus;
    private ProjectStatus quoteStatus;
    private boolean paid;
    private boolean invoiced;
    private boolean isPublic;

    private Project(Builder builder) {
        this.projectId = builder.projectId;
        this.projectTitle = builder.projectTitle;
        this.projectDescription = builder.projectDescription;
        this.customer = builder.customer;
        this.startDate = builder.startDate;
        this.closingDate = builder.closingDate;
        this.startquote = builder.startquote;
        this.operationalStatus = builder.operationalStatus;
        this.quoteStatus = builder.quoteStatus;
        this.paid = builder.paid;
        this.invoiced = builder.invoiced;
        this.isPublic = builder.isPublic;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public String getProjectTitle() {
        return projectTitle;
    }

    @Override
    public String getProjectDescription() {
        return projectDescription;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getClosingDate() {
        return closingDate;
    }

    @Override
    public double getStartquote() {
        return startquote;
    }

    @Override
    public ProjectStatus getOperationalStatus() {
        return operationalStatus;
    }

    @Override
    public ProjectStatus getQuoteStatus() {
        return quoteStatus;
    }

    @Override
    public boolean isPaid() {
        return paid;
    }

    @Override
    public boolean isInvoiced() {
        return invoiced;
    }

    @Override
    public boolean isPublic() {
        return isPublic;
    }

    public void setStartquote(double startquote) {
        this.startquote = startquote;
    }
    
    

    public static class Builder {

        private String projectId;
        private String projectTitle;
        private String projectDescription;
        private Customer customer;
        private Date startDate;
        private Date closingDate;
        private double startquote;
        private ProjectStatus operationalStatus;
        private ProjectStatus quoteStatus;
        private boolean paid;
        private boolean invoiced;
        private boolean isPublic;

        public Builder(String projectId, String projectTitle) {
            this.projectId = projectId;
            this.projectTitle = projectTitle;
        }

        public Builder setProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setClosingDate(Date closingDate) {
            this.closingDate = closingDate;
            return this;
        }

        public Builder setStartquote(double startquote) {
            this.startquote = startquote;
            return this;
        }

        public Builder setOperationalStatus(ProjectStatus operationalStatus) {
            this.operationalStatus = operationalStatus;
            return this;
        }

        public Builder setQuoteStatus(ProjectStatus quoteStatus) {
            this.quoteStatus = quoteStatus;
            return this;
        }

        public Builder setPaid(boolean paid) {
            this.paid = paid;
            return this;
        }

        public Builder setInvoiced(boolean invoiced) {
            this.invoiced = invoiced;
            return this;
        }

        public Builder setPublic(boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }
}
