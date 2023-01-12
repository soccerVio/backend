package soccervio.back.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name="terrains")
public class Terrain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String titre;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "path")
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "terrain_id"))
    private Set<String> images = new java.util.LinkedHashSet<>();
    private float longitude;
    private float latitude;
    @Lob
    private String description;
    private Date dateCreation;
    private String adresse;
    private LocalTime heureO;
    private LocalTime heureF;
    private float prixHr;
    private int nbrJoueur;
    private boolean avecDouche;
    private boolean assure;
    @ManyToOne
    private User proprietaire;
    @OneToMany(mappedBy = "terrain")
    private Collection<Reservation> reservations;

    public Terrain() {
    }

    public Terrain(long id, String titre, Set<String> images, float longitude,
                   float latitude, String description, Date dateCreation, String adresse,
                   LocalTime heureO, LocalTime heureF, float prixHr, int nbrJoueur,
                   boolean avecDouche, boolean assure, User proprietaire) {
        this.id = id;
        this.titre = titre;
        this.images = images;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.dateCreation = dateCreation;
        this.adresse = adresse;
        this.heureO = heureO;
        this.heureF = heureF;
        this.prixHr = prixHr;
        this.nbrJoueur = nbrJoueur;
        this.avecDouche = avecDouche;
        this.assure = assure;
        this.proprietaire = proprietaire;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalTime getHeureO() {
        return heureO;
    }

    public void setHeureO(LocalTime heureO) {
        this.heureO = heureO;
    }

    public LocalTime getHeureF() {
        return heureF;
    }

    public void setHeureF(LocalTime heureF) {
        this.heureF = heureF;
    }

    public float getPrixHr() {
        return prixHr;
    }

    public void setPrixHr(float prixHr) {
        this.prixHr = prixHr;
    }

    public int getNbrJoueur() {
        return nbrJoueur;
    }

    public void setNbrJoueur(int nbrJoueur) {
        this.nbrJoueur = nbrJoueur;
    }

    public boolean isAvecDouche() {
        return avecDouche;
    }

    public void setAvecDouche(boolean avecDouche) {
        this.avecDouche = avecDouche;
    }

    public boolean isAssure() {
        return assure;
    }

    public void setAssure(boolean assure) {
        this.assure = assure;
    }

    public User getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Collection<Reservation> getReservations() {
       return reservations;
    }
}
