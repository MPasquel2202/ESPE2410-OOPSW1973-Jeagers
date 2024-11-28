package ec.edu.espe.projects.model;

/**
 *
 * @author Dennis Paucar
 */
public class Developer {
    private String password;
    private String systemAccount;
    private String email;
    private String employeePosition;
    private Project project;
    private Report report;
    private DataBase dataBase;
    
    public boolean authenticate(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void modifyReports(Report report) {
       
    }
    public void createProject(Project project) {
       
    }
    public void doReportList() {
       
    }
    public void modifyProjects(Project project) {
       
    }
    
    public void connectToDataBase(DataBase dataBase) {
       
    }

    public void removeUser(String userId) {
        
    }
    
    
    
}
