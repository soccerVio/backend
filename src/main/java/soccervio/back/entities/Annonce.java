package soccervio.back.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "annonces")
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    private String description;

    private boolean ferme = false;

    @OneToOne
    private Reservation reservation;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "participants_annonces",
            joinColumns = @JoinColumn(name = "participant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "annonce_id", referencedColumnName = "id"))
    private Set<User> participants;

    public Annonce() {
    }

    public Annonce(long id, String description, boolean ferme, Reservation reservation, Set<User> participants) {
        this.id = id;
        this.description = description;
        this.ferme = ferme;
        this.reservation = reservation;
        this.participants = participants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFerme() {
        return ferme;
    }

    public void setFerme(boolean ferme) {
        this.ferme = ferme;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}
