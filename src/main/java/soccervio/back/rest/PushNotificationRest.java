package soccervio.back.rest;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import soccervio.back.constants.ApiContant;
import soccervio.back.entities.Notification;
import soccervio.back.services.PushNotificationService;

import java.util.List;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/notifications-push")
public class PushNotificationRest {

    private final PushNotificationService service;

    public PushNotificationRest(PushNotificationService service) {
        this.service = service;
    }

    @GetMapping("/{userID}")
    public Flux<ServerSentEvent<List<Notification>>> streamLastMessage(@PathVariable long userID) {
        return service.getNotificationsByUserToID(userID);
    }
}
