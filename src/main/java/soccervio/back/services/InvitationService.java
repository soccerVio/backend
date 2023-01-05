package soccervio.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soccervio.back.dao.InvitationDao;
import soccervio.back.dtos.invitation.InvitationDTO;
import soccervio.back.entities.Annonce;
import soccervio.back.entities.Invitation;
import soccervio.back.entities.UserInvitation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InvitationService {

    private final InvitationDao invitationDao;
    @Autowired
    private AnnonceService annonceService;
    private final UserService userService;
    private final UserInvitationService userInvitationService;

    public InvitationService(InvitationDao invitationDao,
                             UserService userService, UserInvitationService userInvitationService) {
        this.invitationDao = invitationDao;
        this.userService = userService;
        this.userInvitationService = userInvitationService;
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
        Set<UserInvitation> insertedInvites = userInvitationService.saveAll(invites);

        //insertedInvit.setInvites(insertedInvites);
        //invitationDao.save(insertedInvit);
        return new ResponseEntity<>("Invitation ajouté avec succès", HttpStatus.valueOf(201));
    }


    public void deleteByAnnonce(Annonce annonce){
        invitationDao.deleteByAnnonce(annonce);
    }

    /*public ResponseEntity<String> deleteInvite(long id, long idInvite){
        Invitation invitation = invitationDao.findById(id).get();
        Set<User> invites = invitation.getInvites();
        User deletedInvite = userService.getUserById(idInvite);
        invitation.setInvites();
        invites.remove(deletedInvite);

        participationDao.deleteById(id);
        return new ResponseEntity<>("participation supprimé", HttpStatus.valueOf(200));
    }*/

    public  List<Invitation> getInvitations(){
        return invitationDao.findAll();
    }
}
