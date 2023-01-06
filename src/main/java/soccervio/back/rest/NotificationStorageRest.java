package soccervio.back.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccervio.back.constants.ApiContant;
import soccervio.back.entities.Notification;
import soccervio.back.services.NotificationStorageService;

import java.util.List;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/notifications-storage")
public class NotificationStorageRest {

    private final NotificationStorageService notifService;


    public NotificationStorageRest(NotificationStorageService notifService) {
        this.notifService = notifService;
    }

    @GetMapping("/{userID}")
    public ResponseEntity<List<Notification>> getNotificationsByUserID(@PathVariable long userID) {
        return ResponseEntity.ok(notifService.getNotificationsByUserID(userID));
    }

    @PutMapping("/read/{notifID}")
    public ResponseEntity changeNotifStatusToRead(@PathVariable long notifID) {
        return ResponseEntity.ok(notifService.changeNotifStatusToRead(notifID));
    }


}
