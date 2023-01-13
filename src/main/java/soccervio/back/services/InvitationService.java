package soccervio.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soccervio.back.dao.InvitationDao;
import soccervio.back.dtos.invitation.InvitationDTO;
import soccervio.back.entities.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InvitationService {

    private final InvitationDao invitationDao;
    @Autowired
    private AnnonceService annonceService;
    private final UserService userService;
    private final NotificationStorageService notificationStorageService;
    @Autowired
    private UserInvitationService userInvitationService;

    public InvitationService(InvitationDao invitationDao, UserService userService, NotificationStorageService notificationStorageService) {
        this.invitationDao = invitationDao;
        this.userService = userService;
        this.notificationStorageService = notificationStorageService;
    }

    public ResponseEntity<String> ajoutInvitation(InvitationDTO invitationDTO){
        Invitation invitation = new Invitation();

        invitation.setAnnonce(annonceService.getAnnonceById(invitationDTO.getAnnonceId()));
        invitation.setInvitant(userService.getUserById(invitationDTO.getInvitantId()));
        Invitation insertedInvit =  invitationDao.save(invitation);

        Set<UserInvitation> invites =
                invitationDTO.getInviteIds()
                .stream()
                .map(inviteId -> new UserInvitation(userService.getUserById(inviteId), insertedInvit))
                .collect(Collectors.toSet());
        userInvitationService.saveAll(invites);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(UserInvitation userInvitation : invites)
            notificationStorageService.createNotificationStorage(Notification
                    .builder()
                    .dateEnvoie(new Date())
                    .delivered(false)
                    .content("Vous ets invité à jouer un match au terrain "
                            + invitation.getAnnonce().getReservation().getTerrain().getTitre()
                            + " le "
                            + sdf.format(invitation.getAnnonce().getReservation().getDate())
                            + " à "
                            + invitation.getAnnonce().getReservation().getHeure())
                    .notificationType(NotificationType.NEW_IVITATION)
                    .userFrom(invitation.getInvitant())
                    .userTo(userInvitation.getInvite())
                    .build());

        return new ResponseEntity<>("Invitation ajouté avec succès", HttpStatus.valueOf(201));
    }


    public void deleteByAnnonce(Annonce annonce){
        invitationDao.deleteByAnnonce(annonce);
    }

    public List<Invitation> findByAnnonceIn(List<Annonce> annonceList) {
        return invitationDao.findByAnnonceIn(annonceList);
    }

    public  List<Invitation> getInvitations(){
        return invitationDao.findAll();
    }

    public List<Invitation> findByAnnonce(Annonce annonce) {
        return invitationDao.findByAnnonce(annonce);
    }
}
