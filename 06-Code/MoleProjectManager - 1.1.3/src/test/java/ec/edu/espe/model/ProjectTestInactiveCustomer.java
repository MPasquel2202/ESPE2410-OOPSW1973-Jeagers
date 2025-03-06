package ec.edu.espe.model;

import java.util.Date;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTestInactiveCustomer {

    private Project instance;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer("CUST-003", "1234567890", "Empresa ABC", 
                "0981234567", "contacto@abc.com", "Calle Falsa 123");

        instance = new Project.Builder("Prj-004", "Proyecto Suspendido")
                .setProjectDescription("Proyecto con cliente inactivo y cotización pendiente")
                .setCustomer(customer)  
                .setStartDate(new Date())
                .setClosingDate(new Date(System.currentTimeMillis() + 2592000000L))
                .setStartquote(3000.0)
                .setOperationalStatus(ProjectStatus.PAUSED)
                .setQuoteStatus(ProjectStatus.QUOTE_ACCEPTED)
                .setPaid(false)
                .setInvoiced(false)
                .setPublic(false)
                .build();
    }

    @Test
    public void testProjectId() {
        assertEquals("Prj-010", instance.getProjectId(), "El ID del proyecto no coincide.");
    }

    @Test
    public void testCustomerIsNotNull() {
        assertNotNull(instance.getCustomer(), "El cliente no debería ser nulo.");
    }

    @Test
    public void testCustomerInactiveStatus() {
        assertEquals("CUST-003", instance.getCustomer().getCustomerId(), "El ID del cliente no coincide.");
        assertEquals("Empresa ABC", instance.getCustomer().getName(), "El nombre del cliente no coincide.");
        assertEquals("contacto@abc.com", instance.getCustomer().getEmail(), "El correo electrónico del cliente no coincide.");
        assertEquals("4", instance.getCustomer().getAddress(), "La dirección del cliente no coincide.");
    }

    @Test
    public void testProjectStatus() {
        assertEquals(ProjectStatus.PAUSED, instance.getOperationalStatus(), "El estado del proyecto debería ser SUSPENDIDO.");
    }

    @Test
    public void testQuoteStatus() {
        assertEquals(ProjectStatus.QUOTE_REJECTED, instance.getQuoteStatus(), "El estado de cotización debería ser PENDIENTE.");
    }

    @Test
    public void testIsNotPaid() {
        assertTrue(instance.isPaid(), "El proyecto no debería estar pagado.");
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
