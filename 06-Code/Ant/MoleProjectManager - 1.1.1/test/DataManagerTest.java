import ec.edu.espe.model.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class DataManagerTest {
    private DataManager dataManager;

    @BeforeEach
    void setUp() {
        dataManager = new DataManager();
    }

    @Test
    void testAddProject() {
        
        Project project = new Project("Test Project", "Prj-0001", "Description", null, 
                                      new Date(), new Date(), 1000.0, 
                                      ProjectStatus.CREATED, ProjectStatus.QUOTE_SEND, 
                                      false, false, true);

        
        dataManager.addProject(project);

        
        boolean isAdded = dataManager.getProjects().contains(project);
        assertTrue(isAdded, "El proyecto debería agregarse a la lista, pero no se encontró en la lista.");
    }

}
