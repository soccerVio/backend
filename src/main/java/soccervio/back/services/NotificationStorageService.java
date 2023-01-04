package soccervio.back.services;

import org.springframework.stereotype.Service;
import soccervio.back.dao.NotificationDao;
import soccervio.back.entities.Notification;

import java.util.List;

@Service
public class NotificationStorageService {

    private final NotificationDao notificationDao;

    public NotificationStorageService(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public Notification createNotificationStorage(Notification notificationStorage) {
        return notificationDao.save(notificationStorage);
    }


    public Notification getNotificationsByID(long id) {
        return notificationDao.findById(id).orElseThrow(() -> new RuntimeException("notification not found!"));
    }

    public List<Notification> getNotificationsByUserIDNotRead(long userID) {
        return notificationDao.findByUserTo_IdAndDeliveredFalse(userID);
    }


    public List<Notification> getNotificationsByUserID(long userID) {
        return notificationDao.findByUserTo_Id(userID);
    }

    public Notification changeNotifStatusToRead(long notifID) {
        Notification notif = notificationDao.findById(notifID) .orElseThrow(null);

        notif.setRead(true);
        return notificationDao.save(notif);
    }

    public void clear() {
        notificationDao.deleteAll();
    }
}
