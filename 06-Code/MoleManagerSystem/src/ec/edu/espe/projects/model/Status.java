package ec.edu.espe.projects.model;

/**
 *
 * @author Dennis Paucar
 */
public class Status {
    private boolean isCancelled;
    private boolean isInProgress;
    private boolean isApproved;
    private boolean isFinished;


    

    /**
     * @param isCancelled 
     * @param isInProgress
     * @param isApproved
     */
    public void setStauts(boolean isCancelled,boolean isInProgress,boolean isApproved,boolean isFinished) {
        this.isCancelled = isCancelled;
    }

}
