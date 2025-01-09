package ec.edu.espe.model;

/**
 *
 * @author Dennis Paucar
 */
public class ProjectStatus {
    
    public static final ProjectStatus CREATED = new ProjectStatus("Created");
    public static final ProjectStatus IN_PROGRESS = new ProjectStatus("In Progress");
    public static final ProjectStatus PAUSED = new ProjectStatus("Paused");
    public static final ProjectStatus CLOSED = new ProjectStatus("Closed");

    public static final ProjectStatus QUOTE_SEND = new ProjectStatus("Quote Sended");
    public static final ProjectStatus QUOTE_REJECTED = new ProjectStatus("Quote Rejected");
    public static final ProjectStatus QUOTE_ACCEPTED = new ProjectStatus("Quote Accepted");

    private final String status;

    
    ProjectStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
    
    public static ProjectStatus fromString(String status) {
    switch (status) {
        case "Created": return CREATED;
        case "In Progress": return IN_PROGRESS;
        case "Paused": return PAUSED;
        case "Closed": return CLOSED;
        case "Quote Sended": return QUOTE_SEND;
        case "Quote Rejected": return QUOTE_REJECTED;
        case "Quote Accepted": return QUOTE_ACCEPTED;
        default: throw new IllegalArgumentException("Estado desconocido: " + status);
    }
}

}


