package soccervio.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soccervio.back.dao.UserInvitationDao;
import soccervio.back.entities.*;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserInvitationService {
    private final UserInvitationDao userInvitationDao;

    private final AnnonceService annonceService;

    private final ReservationService reservationService;

    private final NotificationStorageService notificationStorageService;
    @Autowired
    private InvitationService invitationService;

    public UserInvitationService(UserInvitationDao userInvitationDao, AnnonceService annonceService,
                                 ReservationService reservationService, NotificationStorageService notificationStorageService) {
        this.userInvitationDao = userInvitationDao;
        this.annonceService = annonceService;
        this.reservationService = reservationService;
        this.notificationStorageService = notificationStorageService;
    }

    public void saveAll(Set<UserInvitation> userInvitationSet){
        userInvitationSet
                .stream()
                .map(userInvitationDao::save).
                collect(Collectors.toSet());
    }

    public List<UserInvitation> getInvitationByIdInvite(long idInvite){
        List<UserInvitation> invitations = userInvitationDao.findByInvite_IdAndAccepteParInviteIsFalse(idInvite);
        List<UserInvitation> returnedInvitations = new ArrayList<>();
        for (UserInvitation invitation : invitations){
            if (!invitation.getInvitation().getAnnonce().isFerme())
                returnedInvitations.add(invitation);
        }
        return returnedInvitations;
    }

    public List<UserInvitation> getInvitationsForAnnonceProp(long idPropAnnonce){
        List<Invitation> invitations =
                invitationService.findByAnnonceIn(
                        annonceService.findByReservationIn(
                                reservationService.getReservationsOfJoueur(idPropAnnonce)));
        return userInvitationDao.findByInvitationInAndAccepteParInviteTrueAndAccepteParPropFalse(invitations);
    }

    public ResponseEntity<String> accepteInvitationByInvite(long idUserInvite){
        UserInvitation userInvitation = userInvitationDao.findById(idUserInvite).get();
        userInvitation.setAccepteParInvite(true);
        userInvitationDao.save(userInvitation);

        notificationStorageService.createNotificationStorage(Notification
                .builder()
                .dateEnvoie(new Date())
                .delivered(false)
                .content(userInvitation.getInvite().getNomComplet() + " est invité à votre réservation")
                .notificationType(NotificationType.ACCEPTED_INVIT_INVITE)
                .userFrom(userInvitation.getInvite())
                .userTo(userInvitation.getInvitation().getAnnonce().getReservation().getReservePar())
                .build());
        return new ResponseEntity<>("invit accepted", HttpStatus.ACCEPTED);
    }

    @Transactional
    public ResponseEntity<String> accepterInvitationByProp(long idUserInvite){
        UserInvitation userInvitation = userInvitationDao.findById(idUserInvite).get();
        User joueur = userInvitation.getInvite();
        Reservation reservation = userInvitation.getInvitation().getAnnonce().getReservation();
        if(reservation.getNbrJoueurManq() == 0){
            Annonce annonce = annonceService.findByReservation(reservation);
            userInvitationDao.deleteByInvitationIn(invitationService.findByAnnonce(annonce));
            return new ResponseEntity<>("Nbr jr = 0", HttpStatus.valueOf(400));
        }
        userInvitation.setAccepteParProp(true);
        userInvitationDao.save(userInvitation);
        reservation.setNbrJoueurManq(reservation.getNbrJoueurManq()-1);
        reservation.getJoueurs().add(joueur);
        reservationService.saveReservation(reservation);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        notificationStorageService.createNotificationStorage(Notification
                .builder()
                .dateEnvoie(new Date())
                .delivered(false)
                .content("Vous etes approuvé pour jouer le match au terrain "
                        + reservation.getTerrain().getTitre()
                        + " le "
                        + sdf.format(reservation.getDate())
                        + " à "
                        + reservation.getHeure())
                .notificationType(NotificationType.NEW_IVITATION)
                .userFrom(reservation.getReservePar())
                .userTo(userInvitation.getInvite())
                .build());

        return new ResponseEntity<>("invit accepted", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> deleteInvit(long idUserInvite){
        UserInvitation userInvitation = userInvitationDao.findById(idUserInvite).get();
        userInvitation.getInvitation().getInvites().remove(userInvitation);
        userInvitationDao.deleteById(idUserInvite);
        return new ResponseEntity<>("invit deleted", HttpStatus.ACCEPTED);
    }
}
