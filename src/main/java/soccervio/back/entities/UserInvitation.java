package soccervio.back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "users_invitations")
public class UserInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User invite;

    @ManyToOne
    @JsonIgnore
    private Invitation invitation;

    private boolean accepteParInvite = false;

    public UserInvitation() {
    }

    public UserInvitation(long id, User invite, Invitation invitation, boolean accepteParInvite) {
        this.id = id;
        this.invite = invite;
        this.invitation = invitation;
        this.accepteParInvite = accepteParInvite;
    }

    public UserInvitation(User invite, Invitation invitation) {
        this.invite = invite;
        this.invitation = invitation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getInvite() {
        return invite;
    }

    public void setInvite(User invite) {
        this.invite = invite;
    }

    public boolean isAccepteParInvite() {
        return accepteParInvite;
    }

    public void setAccepteParInvite(boolean accepteParInvite) {
        this.accepteParInvite = accepteParInvite;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }
}
