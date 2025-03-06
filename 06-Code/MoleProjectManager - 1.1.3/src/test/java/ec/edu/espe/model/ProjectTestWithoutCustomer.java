package ec.edu.espe.model;

import java.util.Date;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTestWithoutCustomer {

    private Project instance;

    @BeforeEach
    public void setUp() {
        instance = new Project.Builder("Prj-002", "Proyecto Cancelado")
                .setProjectDescription("Proyecto sin cliente y cancelado")
                .setCustomer(null)
                .setStartDate(new Date())
                .setClosingDate(new Date(System.currentTimeMillis() + 604800000)) 
                .setStartquote(2000.0)
                .setOperationalStatus(ProjectStatus.CREATED)
                .setQuoteStatus(ProjectStatus.QUOTE_REJECTED)
                .setPaid(false)
                .setInvoiced(false)
                .setPublic(false)
                .build();
    }

    @Test
    public void testProjectId() {
        assertEquals("Prj-002", instance.getProjectId(), "El ID del proyecto no coincide.");
    }

    @Test
    public void testProjectTitle() {
        assertEquals("Proyecto Pegasus", instance.getProjectTitle(), "El título del proyecto no coincide.");
    }

    @Test
    public void testCustomerIsNull() {
        assertNull(instance.getCustomer(), "El cliente debería ser nulo.");
    }

    @Test
    public void testProjectStatus() {
        assertEquals(ProjectStatus.CREATED, instance.getOperationalStatus(), "El estado del proyecto debería ser CANCELADO.");
    }

    @Test
    public void testQuoteStatus() {
        assertEquals(ProjectStatus.QUOTE_REJECTED, instance.getQuoteStatus(), "El estado de cotización debería ser RECHAZADO.");
    }

    @Test
    public void testIsNotPaid() {
        assertFalse(instance.isPaid(), "El proyecto no debería estar pagado.");
    }

    @Test
    public void testIsNotInvoiced() {
        assertFalse(instance.isInvoiced(), "El proyecto no debería estar facturado.");
    }

    @Test
    public void testIsNotPublic() {
        assertFalse(instance.isPublic(), "El proyecto no debería ser público.");
    }
}
