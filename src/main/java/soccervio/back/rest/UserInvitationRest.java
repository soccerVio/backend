package soccervio.back.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccervio.back.constants.ApiContant;
import soccervio.back.entities.UserInvitation;
import soccervio.back.services.UserInvitationService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/invitations")
public class UserInvitationRest {

    private final UserInvitationService userInvitationService;

    public UserInvitationRest(UserInvitationService userInvitationService) {
        this.userInvitationService = userInvitationService;
    }

    @GetMapping("/{idInvite}")
    public List<UserInvitation> getInvitationByIdInvite(@PathVariable long idInvite) {
        return userInvitationService.getInvitationByIdInvite(idInvite);
    }
    @GetMapping("/acceptedInivts/{idPropAnnonce}")
    public List<UserInvitation> getInvitationsForAnnonceProp(@PathVariable long idPropAnnonce) {
        return userInvitationService.getInvitationsForAnnonceProp(idPropAnnonce);
    }

    @PutMapping("/accepterByInvite/{idUserInvite}")
    public ResponseEntity<String> accepteInvitationByInvite(@PathVariable long idUserInvite) {
        return userInvitationService.accepteInvitationByInvite(idUserInvite);
    }

    @Transactional
    @PutMapping("/accepterByProp/{idUserInvite}")
    public ResponseEntity<String> accepterInvitationByProp(@PathVariable long idUserInvite) {
        return userInvitationService.accepterInvitationByProp(idUserInvite);
    }

    @DeleteMapping("/refuserInvit/{idUserInvite}")
    public ResponseEntity<String> deleteInvit(@PathVariable long idUserInvite) {
        return userInvitationService.deleteInvit(idUserInvite);
    }
}
