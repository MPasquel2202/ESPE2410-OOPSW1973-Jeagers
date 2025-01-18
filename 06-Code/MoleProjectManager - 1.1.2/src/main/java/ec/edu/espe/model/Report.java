package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author David Pilatasig
 */

public class Report {

    private String reportId;
    private Project project;
    private List<QuoteChangeLog> quoteChangeLogs;
    private List<StatusChangeLog> statusChangeLogs;
    private List<QuoteStatusChangeLog> quoteStatusChangeLogs;
    private static final String fileName = "json/projects.json";
    private List<Support> supports;

    public Report(String reportId, Project project, List<QuoteChangeLog> quoteChangeLogs,
                  List<StatusChangeLog> statusChangeLogs, List<QuoteStatusChangeLog> quoteStatusChangeLogs,
                  List<Support> supports) {
        this.reportId = reportId;
        this.project = project;
        this.quoteChangeLogs = quoteChangeLogs;
        this.statusChangeLogs = statusChangeLogs;
        this.quoteStatusChangeLogs = quoteStatusChangeLogs;
        this.supports = supports;
    }

    public Report() {
        this.reportId = "";
        this.project = new Project();
        this.quoteChangeLogs = new ArrayList<>();
        this.statusChangeLogs = new ArrayList<>();
        this.quoteStatusChangeLogs = new ArrayList<>();
        this.supports = new ArrayList<>();
    }

    public void displayReport() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("+--------------------------------------------------------------+");
        System.out.printf("| %-60s |\n", "Reporte ID: " + reportId);
        System.out.printf("| %-60s |\n", "Proyecto ID: " + project.getProjectId());
        System.out.println("+--------------------------------------------------------------+");

        System.out.printf("| %-18s | %-45s |\n", "Nombre del Proyecto:", project.getProjectTitle());
        System.out.printf("| %-18s | %-45s |\n", "Descripcion:", project.getProjectDescription());
        System.out.printf("| %-18s | %-45s |\n", "Fecha de Inicio:", dateFormat.format(project.getStartDate()));
        System.out.printf("| %-18s | %-45s |\n", "Fecha de Cierre:", dateFormat.format(project.getClosingDate()));
        System.out.printf("| %-18s | %-45s |\n", "Cliente:", project.getCustomer().getName());
        System.out.printf("| %-18s | %-45.2f |\n", "Presupuesto:", project.getStartquote());
        System.out.printf("| %-18s | %-45s |\n", "Estado del Proyecto:", project.getOperationalStatus().getStatus());
        System.out.printf("| %-18s | %-45s |\n", "Estado Cotizacion:", project.getQuoteStatus().getStatus());
        System.out.printf("| %-18s | %-45s |\n", "Facturado:", project.isInvoiced() ? "Si" : "No");
        System.out.printf("| %-18s | %-45s |\n", "Pagado:", project.isPaid() ? "Si" : "No");
        System.out.printf("| %-18s | %-45s |\n", "Es Publico:", project.isIsPublic() ? "Si" : "No");
        System.out.println("+--------------------------------------------------------------+");

        System.out.println("Soportes del proyecto:");
        System.out.println("+--------------------------------------------------------------+");

        System.out.println("Soportes Asociados:");
        if (!supports.isEmpty()) {
            for (Support support : supports) {
                System.out.printf("\n - %s\n", support.getProjectDescription());
                System.out.println("Identificador numerico del proyecto: " + support.getSupportId());
                System.out.println("Detalle del soporte: " + support.getSupportDetails());
                System.out.println("Status del soporte: " + support.getSupportStatus());
            }
        } else {
            System.out.println(" - No hay soportes asociados.");
        }

        System.out.println("+--------------------------------------------------------------+");
        System.out.println("Historial de Cambios:");
        System.out.println("+--------------------------------------------------------------+");

        if (!quoteChangeLogs.isEmpty()) {
            System.out.println("[Cambios de Presupuesto]");
            for (QuoteChangeLog log : quoteChangeLogs) {
                System.out.printf("[QuoteChange] Proyecto: %s - Viejo: %.2f, Nuevo: %.2f, Fecha: %s\n",
                        log.getProjectTitle(),
                        log.getOldQuote(),
                        log.getNewQuote(),
                        dateFormat.format(log.getChangeDate()));
            }
        }

        if (!statusChangeLogs.isEmpty()) {
            System.out.println("[Cambios de Estado]");
            for (StatusChangeLog log : statusChangeLogs) {
                System.out.printf("[StatusChange] Proyecto: %s - De: %s a %s, Fecha: %s\n",
                        log.getProjectTitle(),
                        log.getOldStatus(),
                        log.getNewStatus(),
                        dateFormat.format(log.getChangeDate()));
            }
        }

        if (!quoteStatusChangeLogs.isEmpty()) {
            System.out.println("[Cambios de Estado de Cotizacion]");
            for (QuoteStatusChangeLog log : quoteStatusChangeLogs) {
                System.out.printf("[QuoteStatusChange] Proyecto: %s - De: %s a %s, Fecha: %s\n",
                        log.getProjectTitle(),
                        log.getOldQuoteStatus(),
                        log.getNewQuoteStatus(),
                        dateFormat.format(log.getChangeDate()));
            }
        }

        System.out.println("+--------------------------------------------------------------+");
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<QuoteChangeLog> getQuoteChangeLogs() {
        return quoteChangeLogs;
    }

    public void setQuoteChangeLogs(List<QuoteChangeLog> quoteChangeLogs) {
        this.quoteChangeLogs = quoteChangeLogs;
    }

    public List<StatusChangeLog> getStatusChangeLogs() {
        return statusChangeLogs;
    }

    public void setStatusChangeLogs(List<StatusChangeLog> statusChangeLogs) {
        this.statusChangeLogs = statusChangeLogs;
    }

    public List<QuoteStatusChangeLog> getQuoteStatusChangeLogs() {
        return quoteStatusChangeLogs;
    }

    public void setQuoteStatusChangeLogs(List<QuoteStatusChangeLog> quoteStatusChangeLogs) {
        this.quoteStatusChangeLogs = quoteStatusChangeLogs;
    }

    public List<Support> getSupports() {
        return supports;
    }

    public void setSupports(List<Support> supports) {
        this.supports = supports;
    }
}
