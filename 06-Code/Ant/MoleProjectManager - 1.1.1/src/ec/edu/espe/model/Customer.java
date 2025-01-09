package ec.edu.espe.model;

/**
 * @author David Pilatasig
 */
public class Customer {
    private String ruc;            
    private String name;           
    private String phoneNumber;   
    private String email;          
    private String address; 
    private String customerId;

    
    public Customer(String ruc, String name, String phoneNumber, String email, String address, String customerId) {
        this.ruc = ruc;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.customerId = customerId;
    }

    public void displayCustomerDetails() {
        System.out.println("+---------------------+---------------------+");
        System.out.printf("| %-20s | %-20s |%n", "Campo", "Valor");
        System.out.println("+---------------------+---------------------+");
        System.out.printf("| %-20s | %-20s |%n", "ID del Cliente", customerId);
        System.out.printf("| %-20s | %-20s |%n", "Nombre/Empresa", name);
        System.out.printf("| %-20s | %-20s |%n", "RUC", ruc);
        System.out.printf("| %-20s | %-20s |%n", "Telefono", phoneNumber);
        System.out.printf("| %-20s | %-20s |%n", "Correo Electronico", email);
        System.out.printf("| %-20s | %-20s |%n", "Direccion", address);
        System.out.println("+---------------------+---------------------+");
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    

    
    @Override
    public String toString() {
        return String.format("Nombre: %s | RUC: %s | Telefono: %s | Email: %s | Direccion: %s",
                name, ruc, phoneNumber, email, address);
    }
}
