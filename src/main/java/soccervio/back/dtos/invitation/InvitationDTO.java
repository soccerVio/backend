package soccervio.back.dtos.invitation;

import java.util.List;

import javax.validation.constraints.NotBlank;

import soccervio.back.entities.User;

public class InvitationDTO {
    @NotBlank
	private Long id;
    @NotBlank
	private User invitant;
    @NotBlank
	private List<User> invite ;
	 
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getInvitant() {
		return invitant;
	}
	public void setInvitant(User invitant) {
		this.invitant = invitant;
	}
	public List<User> getInvite() {
		return invite;
	}
	public void setInvite(List<User> invite) {
		this.invite = invite;
	}
	
   








}
