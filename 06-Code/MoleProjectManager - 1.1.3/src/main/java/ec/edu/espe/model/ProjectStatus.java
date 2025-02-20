package ec.edu.espe.model;

/**
 *
 * @author Dennis Paucar
 */
public class ProjectStatus {
    
    public static final ProjectStatus CREATED = new ProjectStatus("Creado");
    public static final ProjectStatus IN_PROGRESS = new ProjectStatus("En Progreso");
    public static final ProjectStatus PAUSED = new ProjectStatus("Pausado");
    public static final ProjectStatus CLOSED = new ProjectStatus("Cerrado");

    public static final ProjectStatus QUOTE_SEND = new ProjectStatus("Enviado");
    public static final ProjectStatus QUOTE_REJECTED = new ProjectStatus("Rechazado");
    public static final ProjectStatus QUOTE_ACCEPTED = new ProjectStatus("Aceptado");

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
        case "Creado": return CREATED;
        case "En Progreso": return IN_PROGRESS;
        case "Pausado": return PAUSED;
        case "Cerrado": return CLOSED;
        case "Enviado": return QUOTE_SEND;
        case "Rechazado": return QUOTE_REJECTED;
        case "Aceptado": return QUOTE_ACCEPTED;
        default: throw new IllegalArgumentException("Estado desconocido: " + status);
    }
}

}


