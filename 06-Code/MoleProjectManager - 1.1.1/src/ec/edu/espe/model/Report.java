package ec.edu.espe.model;

import java.util.Date;



/**
 *
 * @author David Pilatasig
 */
public class Report {
    String reportId;
    Date generatedDate;
    Project project;
    Customer customer;
    QuoteChangeLog quoteChangelog;
    QuoteStatusChangeLog quoteStatusChangelog;
    StatusChangeLog statusChangeLog;
    
    
}
