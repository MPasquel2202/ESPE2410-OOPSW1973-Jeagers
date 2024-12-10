package ec.edu.espe.mole.model;

import java.util.Date;



/**
 *
 * @author Brandon Pazmino
 */
public class Notification {
    private String notificationId;
    private String noticeTitle;
    private String noticeMessage;
    private Date sendDate;
    private String relatedProjectId;

    public Notification(String notificationId, String noticeTitle, String noticeMessage, Date sendDate, String relatedProjectId) {
        this.notificationId = notificationId;
        this.noticeTitle = noticeTitle;
        this.noticeMessage = noticeMessage;
        this.sendDate = sendDate;
        this.relatedProjectId = relatedProjectId;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeMessage() {
        return noticeMessage;
    }

    public void setNoticeMessage(String noticeMessage) {
        this.noticeMessage = noticeMessage;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getRelatedProjectId() {
        return relatedProjectId;
    }

    public void setRelatedProjectId(String relatedProjectId) {
        this.relatedProjectId = relatedProjectId;
    }

    public void sendNotification() {
        System.out.println("Notification sent: \n" +
                           "Title: " + noticeTitle + "\n" +
                           "Message: " + noticeMessage + "\n" +
                           "Related Project ID: " + relatedProjectId + "\n" +
                           "Send Date: " + sendDate);
    }

    @Override
    public String toString() {
        return "Notification ID: " + notificationId + "\n" +
               "Title: " + noticeTitle + "\n" +
               "Message: " + noticeMessage + "\n" +
               "Send Date: " + sendDate + "\n" +
               "Related Project ID: " + relatedProjectId;
    }
}
