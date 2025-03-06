package ec.edu.espe.model;

/**
 *
 * @author Dennis Paucar
 */
public enum ProjectStatus {
    CREATED("Creado"),
    IN_PROGRESS("En Progreso"),
    PAUSED("Pausado"),
    CLOSED("Cerrado"),
    QUOTE_SEND("Enviado"),
    QUOTE_REJECTED("Rechazado"),
    QUOTE_ACCEPTED("Aceptado");

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


