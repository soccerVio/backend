package soccervio.back.entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity(name = "annonces")
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    private String description;
    private Date dateFermeture;
    private Time heureFermeture;
    private boolean ferme;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "joueurs_annonces",
            joinColumns = @JoinColumn(name = "annonce_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "joueur_id", referencedColumnName = "id"))
    private List<User> joueurs;

    public Annonce() {
    }

    public Annonce(long id, String description, Date dateFermeture, Time heureFermeture, boolean ferme, List<User> joueurs) {
        this.id = id;
        this.description = description;
        this.dateFermeture = dateFermeture;
        this.heureFermeture = heureFermeture;
        this.ferme = ferme;
        this.joueurs = joueurs;
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

    public Date getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public Time getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(Time heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public boolean isFerme() {
        return ferme;
    }

    public void setFerme(boolean ferme) {
        this.ferme = ferme;
    }

    public List<User> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<User> joueurs) {
        this.joueurs = joueurs;
    }
}
