package ec.edu.espe.model;

import java.util.Date;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTestWithACustomer {

    private Project instance;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer("1545757556", "1748236697001", "Empresa XYZ", 
                "0998765432", "Sabanero@example.com", "Av. Siempre Viva 456");

        instance = new Project.Builder("Prj-003", "Proyecto Activo")
                .setProjectDescription("Proyecto con cliente y cotización aprobada")
                .setCustomer(customer)
                .setStartDate(new Date())
                .setClosingDate(new Date(System.currentTimeMillis() + 1209600000))
                .setStartquote(5000.0)
                .setOperationalStatus(ProjectStatus.CREATED)
                .setQuoteStatus(ProjectStatus.QUOTE_ACCEPTED)
                .setPaid(true)
                .setInvoiced(true)
                .setPublic(true)
                .build();
    }

    @Test
    public void testProjectId() {
        assertEquals("Prj-003", instance.getProjectId(), "El ID del proyecto no coincide.");
    }

    @Test
    public void testCustomerIsNotNull() {
        assertNotNull(instance.getCustomer(), "El cliente no debería ser nulo.");
    }

    @Test
    public void testCustomerData() {
        assertEquals("CUST-002", instance.getCustomer().getCustomerId(), "El ID del cliente no coincide.");
        assertEquals("Empresa XYZ", instance.getCustomer().getName(), "El nombre del cliente no coincide.");
        assertEquals("0998765432", instance.getCustomer().getPhoneNumber(), "El teléfono del cliente no coincide.");
    }

    @Test
    public void testProjectStatus() {
        assertEquals(ProjectStatus.PAUSED, instance.getOperationalStatus(), "El estado del proyecto debería ser ACTIVO.");
    }

    @Test
    public void testQuoteStatus() {
        assertEquals(ProjectStatus.QUOTE_ACCEPTED, instance.getQuoteStatus(), "El estado de cotización debería ser APROBADO.");
    }

    @Test
    public void testIsPaid() {
        assertTrue(instance.isPaid(), "El proyecto debería estar pagado.");
    }

    @Test
    public void testIsInvoiced() {
        assertFalse(instance.isInvoiced(), "El proyecto debería estar facturado.");
    }

    @Test
    public void testIsPublic() {
        assertTrue(instance.isPublic(), "El proyecto debería ser público.");
    }
}
