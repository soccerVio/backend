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
    private Invitation invitation;

    private boolean accepteParInvite = false;

    private boolean accepteParProp = false;

    public UserInvitation() {
    }

    public UserInvitation(User invite, Invitation invitation) {
        this.invite = invite;
        this.invitation = invitation;
    }
    public UserInvitation(long id, User invite, Invitation invitation, boolean accepteParInvite,
                          boolean accepteParProp) {
        this.id = id;
        this.invite = invite;
        this.invitation = invitation;
        this.accepteParInvite = accepteParInvite;
        this.accepteParProp = accepteParProp;
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

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }

    public boolean isAccepteParInvite() {
        return accepteParInvite;
    }

    public void setAccepteParInvite(boolean accepteParInvite) {
        this.accepteParInvite = accepteParInvite;
    }

    public boolean isAccepteParProp() {
        return accepteParProp;
    }

    public void setAccepteParProp(boolean accepteParProp) {
        this.accepteParProp = accepteParProp;
    }
}
