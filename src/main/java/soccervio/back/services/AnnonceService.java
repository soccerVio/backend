package soccervio.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soccervio.back.dao.AnnonceDao;
import soccervio.back.dtos.annoce.AnnonceDTO;
import soccervio.back.entities.*;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class AnnonceService {

    private final  AnnonceDao annonceDao;

    @Autowired
    private ReservationService reservationService;
    private final NotificationStorageService notificationStorageService;
    @Autowired
    private InvitationService invitationService;
    private final UserService userService;

    public AnnonceService(AnnonceDao annonceDao, NotificationStorageService notificationStorageService, UserService userService) {
        this.annonceDao = annonceDao;
        this.notificationStorageService = notificationStorageService;
        this.userService = userService;
    }

    public ResponseEntity<Object> ajoutAnnnonce(AnnonceDTO annonceDTO){
        Annonce annonce = new Annonce();
        annonce.setDescription(annonceDTO.getDescription());
        annonce.setReservation(reservationService.getReservationById(annonceDTO.getIdReservation()));
        return new ResponseEntity<>(annonceDao.save(annonce), HttpStatus.valueOf(201));
    }

    public ResponseEntity<List<Annonce>> getAnnonces(){
        List<Annonce> annonces = annonceDao.findAll();
        List<Annonce> returnedAnnonces = new ArrayList<>();
        for(Annonce annonce : annonces){
            if(annonce.getReservation().getDate().after(new Date()) && !annonce.isFerme())
                returnedAnnonces.add(annonce);
            else{
                annonce.setFerme(true);
                annonceDao.save(annonce);
            }
        }
        return new ResponseEntity<>(returnedAnnonces, HttpStatus.valueOf(200));
    }
    public ResponseEntity<List<Annonce>> annoncesJoueur(long idUser){
    	List<Annonce> annonces = new ArrayList<>();
    	List<Reservation> reservations =reservationService.getReservationsOfJoueur(idUser);
    	for(Reservation reservation : reservations) {
    		 Annonce annonce = annonceDao.findByReservation(reservation);
             if(annonce.getParticipants().size() > 0 && !annonce.isFerme())
                 annonces.add(annonce);
    	}
    	return new ResponseEntity<>(annonces,HttpStatus.valueOf(200));
    }

    public ResponseEntity<String> refuserParticipation(long idJoueur,long idAnnonce){
    	 Annonce annonce = annonceDao.findById(idAnnonce).get();
    	 User joueur= userService.getUserById(idJoueur);
    	 annonce.getParticipants().remove(joueur);
         annonceDao.save(annonce);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        notificationStorageService.createNotificationStorage(Notification
                .builder()
                .dateEnvoie(new Date())
                .delivered(false)
                .content("Votre participation au reservation effecute le "
                        + sdf.format(annonce.getReservation().getDate())
                        + " à "
                        + annonce.getReservation().getHeure()
                        + "au terrain "
                        + annonce.getReservation().getTerrain().getTitre()
                        + " est refusé.")
                .notificationType(NotificationType.PARTICIATION_REFUSE)
                .userFrom(annonce.getReservation().getReservePar())
                .userTo(joueur)
                .build());
         return new ResponseEntity<>("refus avec succès", HttpStatus.valueOf(200));
    }

    @Transactional
    public ResponseEntity<String> accepterParticipation(long idJoueur,long idAnnonce){
        Annonce annonce = annonceDao.findById(idAnnonce).get();
        Reservation reservation = annonce.getReservation();
        if (reservation.getNbrJoueurManq() == 0){
            annonce.setParticipants(null);
            annonce.setFerme(true);
            annonceDao.save(annonce);
            return new ResponseEntity<>("Nombre de joueur egual a 0", HttpStatus.valueOf(400));
        }
   	    User joueur = userService.getUserById(idJoueur);
   	    annonce.getParticipants().remove(joueur);
        reservation.getJoueurs().add(joueur);
        reservation.setNbrJoueurManq(reservation.getNbrJoueurManq()-1);
        if(reservation.getNbrJoueurManq() == 0){
            annonce.setParticipants(null);
            annonce.setFerme(true);
        }
        annonceDao.save(annonce);
        reservationService.saveReservation(reservation);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        notificationStorageService.createNotificationStorage(Notification
                .builder()
                .dateEnvoie(new Date())
                .delivered(false)
                .content("Votre participation au reservation effecute le "
                        + sdf.format(annonce.getReservation().getDate())
                        + " à "
                        + annonce.getReservation().getHeure()
                        + "au terrain "
                        + annonce.getReservation().getTerrain().getTitre()
                        + " est accepté.")
                .notificationType(NotificationType.PARTICIATION_REFUSE)
                .userFrom(annonce.getReservation().getReservePar())
                .userTo(joueur)
                .build());

        return new ResponseEntity<>("Acceptation avec succès", HttpStatus.valueOf(200));
    }

    public ResponseEntity<String> participerAnnonce(long idParticipant, long idAnnonce){
        Annonce annonce = annonceDao.findById(idAnnonce).get();
        Set<User> participants = annonce.getParticipants();
        User participant = userService.getUserById(idParticipant);
        if(participants.contains(participant))
            return new ResponseEntity<>("Participant existe déja", HttpStatus.valueOf(405));
        if(annonce.getReservation().getJoueurs().contains(participant))
            return new ResponseEntity<>("Participant existe déja", HttpStatus.valueOf(406));
        participants.add(userService.getUserById(idParticipant));
        annonce.setParticipants(participants);
        annonceDao.save(annonce);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        notificationStorageService.createNotificationStorage(Notification
                .builder()
                .dateEnvoie(new Date())
                .delivered(false)
                .content(participant.getNomComplet()
                        + " a participé a votre annonce du réservation effectué le "
                        + sdf.format(annonce.getReservation().getDate())
                        + " à "
                        + annonce.getReservation().getHeure()
                        + "au terrain "
                        + annonce.getReservation().getTerrain().getTitre())
                .notificationType(NotificationType.PARTICIATION_REFUSE)
                .userFrom(participant)
                .userTo(annonce.getReservation().getReservePar())
                .build());
        return new ResponseEntity<>("Participation ajouté avec succès", HttpStatus.valueOf(200));
    }

    public Annonce getAnnonceById(long idAnnonce){
        return annonceDao.findById(idAnnonce).get();
    }

    public void deleteAnnonceByReservation(Reservation reservation){
        invitationService.deleteByAnnonce(annonceDao.findByReservation(reservation));
        annonceDao.deleteByReservation(reservation);
    }

    public List<Annonce> findByReservationIn(List<Reservation> reservations) {
        return annonceDao.findByReservationIn(reservations);
    }

    public Annonce findByReservation(Reservation reservation) {
        return annonceDao.findByReservation(reservation);
    }
}

