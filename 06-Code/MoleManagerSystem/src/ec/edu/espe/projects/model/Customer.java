package ec.edu.espe.projects.model;

/**
 *
 * @author Dennis Paucar
 */
public class Customer {
    private String customerName;
    private String customerEmail;
    private String customerIdCard;
    private String customerCompanyName;
    private String[] projectList;
    private String ruc;

    /**
     * @return the customerName
     */
    public String changeCustomerData() {
        return customerName;
    }

 

    /**
     * @return the customerCompanyName
     */
    public String changeCustomerCompanyName() {
        return customerCompanyName;
    }

    /**
     * @return the projectList
     */
    public String[] getProjectList() {
        return projectList;
    }

    /**
     * @return the ruc
     */
    public String changeRuc() {
        return ruc;
    }
    
}
