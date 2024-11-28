package ec.edu.espe.projects.model;



/**
 *
 * @author Dennis Paucar
 */
public class Notification {
    private int notificationId;
    private String noticeTittle;
    private Date sendDate;
    private Status projectStatus;
    private String noticeMessage;

    
    /**
     * @param noticeTittle the noticeTittle to set
     */
    public void setNoticeTittle(String noticeTittle) {
        this.noticeTittle = noticeTittle;
    }

    /**
     * @return the sendDate
     */
    public Date getSendDate() {
        return sendDate;
    }

    public Status getProjectStatus() {
        return projectStatus;
    }
    /**
     * @param noticeMessage the noticeMessage to set
     */
    public void setNoticeMessage(String noticeMessage) {
        this.noticeMessage = noticeMessage;
    }
    
    public void sendNotification() {
        
    }
    
    
}
