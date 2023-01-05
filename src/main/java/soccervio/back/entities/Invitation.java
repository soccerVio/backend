package soccervio.back.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity(name = "invitations")
public class Invitation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private User invitant;

	@ManyToOne
	private Annonce annonce;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "invitation")
	private Set<UserInvitation> invites;


	public Invitation() {
	}

	public Invitation(long id, User invitant, Annonce annonce, Set<UserInvitation> invites) {
		this.id = id;
		this.invitant = invitant;
		this.annonce = annonce;
		this.invites = invites;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getInvitant() {
		return invitant;
	}

	public void setInvitant(User invitant) {
		this.invitant = invitant;
	}

	public Annonce getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}

	public Set<UserInvitation> getInvites() {
		return invites;
	}

	public void setInvites(Set<UserInvitation> invites) {
		this.invites = invites;
	}
}
