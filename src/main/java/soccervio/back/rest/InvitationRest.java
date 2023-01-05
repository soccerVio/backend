package soccervio.back.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccervio.back.constants.ApiContant;
import soccervio.back.dtos.invitation.InvitationDTO;
import soccervio.back.entities.Invitation;
import soccervio.back.services.InvitationService;

import java.util.List;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/invitations")
public class InvitationRest {

    private final InvitationService invitationService;


    public InvitationRest(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> ajoutInvitation(@RequestBody InvitationDTO invitationDTO) {
        return invitationService.ajoutInvitation(invitationDTO);
    }

    @GetMapping
    public List<Invitation> getInvitations() {
        return invitationService.getInvitations();
    }


}
