package soccervio.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soccervio.back.dao.AnnonceDao;
import soccervio.back.dtos.annoce.AnnonceDTO;
import soccervio.back.entities.Annonce;
import soccervio.back.entities.Reservation;
import soccervio.back.entities.User;

import java.util.List;
import java.util.Set;

@Service
public class AnnonceService {

    private final  AnnonceDao annonceDao;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private InvitationService invitationService;
    private final UserService userService;

    public AnnonceService(AnnonceDao annonceDao, UserService userService) {
        this.annonceDao = annonceDao;
        this.userService = userService;
    }

    public ResponseEntity<Annonce> ajoutAnnnonce(AnnonceDTO annonceDTO){
        Annonce annonce = new Annonce();
        annonce.setDescription(annonceDTO.getDescription());
        annonce.setReservation(reservationService.getReservationById(annonceDTO.getIdReservation()));
        return new ResponseEntity<>(annonceDao.save(annonce), HttpStatus.valueOf(201));
    }

    public ResponseEntity<List<Annonce>> getAnnonces(){
        return new ResponseEntity<>(annonceDao.findAll(), HttpStatus.valueOf(200));
    }
    public ResponseEntity<List<Annonce>> annoncesJoueur(long idUser){
    	List<Annonce> annonces ;
    	List<Reservation>reservations =reservationService.getReservationsOfJoueur(idUser);
    	for(Resevervation reservation : resevations) {
    		 Annone annonce = annonceDao.findByReservation(reservation);
    		 if(annonce.getParticipants().length>0) { annonces.add(annonce);} 
    	}
    	return new ResponseEntity<>(annonces,HttpStatus.valueOf(200));
    }
    
    public ResponseEntity<String> refuserParticipation(long idJoueur,long idAnnonce){
    	 Annone annonce = annonceDao.findById(idAnnonce);
    	 User joueur= userService.getUserById(idJoueur);
    	 annonce.getParticipants().remove(joueur);
         return new ResponseEntity<>("refus avec succès", HttpStatus.valueOf(200));
    }
    public ResponseEntity<String> accepterParticipation(long idJoueur,long idAnnonce){
   	 Annone annonce = annonceDao.findById(idAnnonce);
   	 User joueur= userService.getUserById(idJoueur);
   	 annonce.getParticipants().remove(joueur);
   	 annonce.getReservation().getJoueurs().add(joueur);
        return new ResponseEntity<>("Acceptation avec succès", HttpStatus.valueOf(200));
   }
    public ResponseEntity<String> participerAnnonce(long idParticipant, long idAnnonce){
        Annonce annonce = annonceDao.findById(idAnnonce).get();
        Set<User> participants = annonce.getParticipants();
        participants.add(userService.getUserById(idParticipant));
        annonce.setParticipants(participants);
        annonceDao.save(annonce);
        return new ResponseEntity<>("Participation ajouté avec succès", HttpStatus.valueOf(200));
    }

    public Annonce getAnnonceById(long idAnnonce){
        return annonceDao.findById(idAnnonce).get();
    }

    public void deleteAnnonceByReservation(Reservation reservation){
        invitationService.deleteByAnnonce(annonceDao.findByReservation(reservation));
        annonceDao.deleteByReservation(reservation);
    }
}

