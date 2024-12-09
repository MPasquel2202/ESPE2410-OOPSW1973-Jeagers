package ec.edu.espe.mole.model;

/**
 *
 * @author Brandon Pazmino
 */
public enum Status {
    QUOTATION_SENT("Quotation Sent"),
    QUOTATION_ACCEPTED("Quotation Accepted"),
    QUOTATION_REJECTED("Quotation Rejected"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    BILLED("Billed"),
    NOT_BILLED("Not Billed"),
    PAID("Paid"),
    NOT_PAID("Not Paid");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
