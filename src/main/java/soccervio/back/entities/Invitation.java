package soccervio.back.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User invitant;
    @OneToMany
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
