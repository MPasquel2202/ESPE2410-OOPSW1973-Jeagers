package ec.edu.espe.model;

import ec.edu.espe.model.Customer;
import ec.edu.espe.model.Project;
import ec.edu.espe.model.ProjectStatus;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Brandon Pazmino
 */
public class ProjectTestDateManage {

    private Project instance;

    public ProjectTestDateManage() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Project.Builder("Prj-0050", "HermanosJO")
                .setProjectDescription("Proyecto de prueba")
                .setCustomer(null)
                .setStartDate(new Date())
                .setClosingDate(new Date(System.currentTimeMillis() + 86400000)) // 1 día después
                .setStartquote(1500.0)
                .setOperationalStatus(ProjectStatus.PAUSED)
                .setQuoteStatus(ProjectStatus.CLOSED)
                .setPaid(true)
                .setInvoiced(true)
                .setPublic(true)
                .build();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testProjectStartDateBeforeClosingDate() {
        System.out.println("testProjectStartDateBeforeClosingDate...");
        // Verifica que la fecha de inicio del proyecto sea anterior a la fecha de cierre
        assertTrue(instance.getStartDate().before(instance.getClosingDate()), 
               "La fecha de inicio no puede ser posterior a la fecha de cierre.");
    }

    // Otros métodos de prueba que ya mencionamos anteriormente
    @Test
    public void testGetProjectId() {
        System.out.println("getProjectID...");
        String expResult = "Prj-001";
        assertEquals(expResult, instance.getProjectId(), "El título del proyecto no coincide.");
    }

    @Test
    public void testGetProjectTitle() {
        System.out.println("getProjectTitle...");
        assertEquals("Hola Mundo", instance.getProjectTitle(), "El título del proyecto no coincide.");
    }

    @Test
    public void testGetProjectDescription() {
        System.out.println("getProjectDescription...");
        assertEquals("Proyecto de prueba", instance.getProjectDescription(), "La descripción del proyecto no coincide.");
    }

    @Test
    public void testGetCustomer() {
        System.out.println("getCustomer");
        Project instance = null;
        Customer expResult = null;
        Customer result = instance.getCustomer();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate...");
        assertNotNull(instance.getStartDate(), "La fecha de inicio no debería ser nula.");
    }

    @Test
    public void testGetClosingDate() {
        System.out.println("getClosingDate...");
        assertNotNull(instance.getClosingDate(), "La fecha de cierre no debería ser nula.");
    }

    @Test
    public void testGetStartquote() {
        System.out.println("getStartquote...");
        assertEquals(1500.0, instance.getStartquote(), 0.01, "El valor de la cotización inicial no coincide.");
    }

    @Test
    public void testGetOperationalStatus() {
        System.out.println("getOperationalStatus...");
        assertEquals(ProjectStatus.PAUSED, instance.getOperationalStatus(), "El estado operativo no coincide.");
    }

    @Test
    public void testGetQuoteStatus() {
        System.out.println("getQuoteStatus...");
        assertEquals(ProjectStatus.CLOSED, instance.getQuoteStatus(), "El estado de cotización no coincide.");
    }

    @Test
    public void testIsPaid() {
        System.out.println("getisPaid...");
        assertTrue(instance.isPaid(), "El proyecto debería estar pagado.");
    }

    @Test
    public void testIsInvoiced() {
        System.out.println("getisInvoiced...");
        assertTrue(instance.isInvoiced(), "El proyecto debería estar facturado.");
    }

    @Test
    public void testIsPublic() {
        System.out.println("getisPublic...");
        assertTrue(instance.isPublic(), "El proyecto debería ser público.");
    }
}
