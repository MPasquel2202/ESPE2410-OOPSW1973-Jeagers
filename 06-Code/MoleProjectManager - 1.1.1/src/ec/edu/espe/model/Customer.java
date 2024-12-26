package ec.edu.espe.model;

/**
 * @author Dennis Paucar
 */
public class Customer {
    private String ruc;            
    private String name;           
    private String phoneNumber;   
    private String email;          
    private String address;        

    
    public Customer(String ruc, String name, String phoneNumber, String email, String address) {
        this.ruc = ruc;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
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

    
    @Override
    public String toString() {
        return String.format("Nombre: %s | RUC: %s | Telefono: %s | Email: %s | Direccion: %s",
                name, ruc, phoneNumber, email, address);
    }
}
