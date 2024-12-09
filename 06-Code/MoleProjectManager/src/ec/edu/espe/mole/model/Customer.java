
package ec.edu.espe.mole.model;

/**
 *
 * @author David Pilatasig
 */
public class Customer {
     private String customerId;
    private String name;
    private String email;
    private String ruc;
    private String phoneNumber;

    public Customer(String customerId, String name, String email, String ruc, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.ruc = ruc;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void updateContactInfo(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerInfo() {
        return "Customer ID: " + customerId + "\n" +
               "Name: " + name + "\n" +
               "Email: " + email + "\n" +
               "RUC: " + ruc + "\n" +
               "Phone Number: " + phoneNumber;
    }

    @Override
    public String toString() {
        return getCustomerInfo();
    }
}
