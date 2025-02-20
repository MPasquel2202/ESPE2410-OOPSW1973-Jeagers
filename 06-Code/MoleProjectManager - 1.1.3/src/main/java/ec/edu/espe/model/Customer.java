package ec.edu.espe.model;

import org.bson.Document;

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

    
     public Customer(String customerId, String ruc, String name, String phoneNumber, String email, String address) {
        this.customerId= customerId;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    

    @Override
    public String toString() {
       return new Document("id", customerId)
                .append("RUC", ruc)
                .append("name", name)
                .append("Phone", phoneNumber)
                .append("Email", email)
                .append("Dirección", address)
                .toJson();
    }
}
