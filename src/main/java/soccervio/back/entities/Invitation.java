package soccervio.back.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

<<<<<<< HEAD
@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User invitant;
    @OneToMany
    private List<User> invite ;
=======
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
>>>>>>> aa2f69eb2dc4412e99e77f9f23ca44764b575fe9


	public Invitation() {
	}

<<<<<<< HEAD










=======
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
>>>>>>> aa2f69eb2dc4412e99e77f9f23ca44764b575fe9
}
