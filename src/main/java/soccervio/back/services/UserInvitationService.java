package soccervio.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soccervio.back.dao.UserInvitationDao;
import soccervio.back.entities.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserInvitationService {
    private final UserInvitationDao userInvitationDao;

    private final AnnonceService annonceService;

    private final ReservationService reservationService;

    @Autowired
    private InvitationService invitationService;

    public UserInvitationService(UserInvitationDao userInvitationDao, AnnonceService annonceService,
                                 ReservationService reservationService) {
        this.userInvitationDao = userInvitationDao;
        this.annonceService = annonceService;
        this.reservationService = reservationService;
    }

    public Set<UserInvitation> saveAll(Set<UserInvitation> userInvitationSet){
        return userInvitationSet
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

        return new ResponseEntity<>("invit accepted", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> deleteInvit(long idUserInvite){
        UserInvitation userInvitation = userInvitationDao.findById(idUserInvite).get();
        userInvitation.getInvitation().getInvites().remove(userInvitation);
        userInvitationDao.deleteById(idUserInvite);
        return new ResponseEntity<>("invit deleted", HttpStatus.ACCEPTED);
    }
}
