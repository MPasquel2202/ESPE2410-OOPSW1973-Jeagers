package ec.edu.espe.model;

import ec.edu.espe.model.Customer;
import ec.edu.espe.model.ProjectStatus;
import java.util.Date;

/**
 *
 * @author Brandon Pazmino
 */
interface ProjectData {

    String getProjectId();

    String getProjectTitle();

    String getProjectDescription();

    Customer getCustomer();

    Date getStartDate();

    Date getClosingDate();

    double getStartquote();

    ProjectStatus getOperationalStatus();

    ProjectStatus getQuoteStatus();

    boolean isPaid();

    boolean isInvoiced();

    boolean isPublic();

}
