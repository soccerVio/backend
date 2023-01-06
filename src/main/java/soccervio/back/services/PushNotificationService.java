package soccervio.back.services;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import soccervio.back.dao.NotificationDao;
import soccervio.back.entities.Notification;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class PushNotificationService {

    private final NotificationDao notificationDao;

    public PushNotificationService(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public Flux<ServerSentEvent<List<Notification>>> getNotificationsByUserToID(long userID) {

        if (userID != 0) {
            return Flux.interval(Duration.ofSeconds(1))
                    .publishOn(Schedulers.boundedElastic())
                    .map(sequence -> ServerSentEvent.<List<Notification>>builder().id(String.valueOf(sequence))
                            .event("user-list-event").data(getNotifs(userID))
                            .build());
        }

        return Flux.interval(Duration.ofSeconds(1)).map(sequence -> ServerSentEvent.<List<Notification>>builder()
                .id(String.valueOf(sequence)).event("user-list-event").data(new ArrayList<>()).build());
    }

    private List<Notification> getNotifs(long userID) {
        var notifs = notificationDao.findByUserTo_IdAndDeliveredFalse(userID);
        notifs.forEach(x -> x.setDelivered(true));
        notificationDao.saveAll(notifs);
        return notifs;
    }
}
