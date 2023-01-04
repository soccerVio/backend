package soccervio.back.entities;

import lombok.Builder;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "notifications")
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;

    private Date dateEnvoie;
    @OneToOne
    private User userTo;

    @OneToOne
    private User userFrom;

    private NotificationType notificationType;

    private boolean delivered;
    private boolean read;

    public Notification() {
    }

    public Notification(long id, String content, Date dateEnvoie, User userTo, User userFrom,
                        NotificationType notificationType, boolean delivered, boolean read) {
        this.id = id;
        this.content = content;
        this.dateEnvoie = dateEnvoie;
        this.userTo = userTo;
        this.userFrom = userFrom;
        this.notificationType = notificationType;
        this.delivered = delivered;
        this.read = read;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Date getDateEnvoie() {
        return dateEnvoie;
    }

    public void setDateEnvoie(Date dateEnvoie) {
        this.dateEnvoie = dateEnvoie;
    }
}
