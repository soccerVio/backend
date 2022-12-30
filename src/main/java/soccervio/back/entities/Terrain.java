package soccervio.back.entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity(name="terrains")
public class Terrain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String titre;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Image> images;
    private float longitude;
    private float latitude;
    @Lob
    private String description;
    private Date dateCreation;
    private String adresse;
    private Time heureO;
    private Time heureF;
    private float prixHr;
    private int nbrJoueur;
    private boolean avecDouche;
    private boolean assure;
    @ManyToOne
    private User proprietaire;

    public Terrain() {
    }

    public Terrain(long id, String titre, Set<Image> images, float longitude, float latitude,
                   String description, Date dateCreation, String adresse, Time heureO, Time heureF,
                   float prixHr, int nbrJoueur, boolean avecDouche, boolean assure, User proprietaire) {
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

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
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

    public Time getHeureO() {
        return heureO;
    }

    public void setHeureO(Time heureO) {
        this.heureO = heureO;
    }

    public Time getHeureF() {
        return heureF;
    }

    public void setHeureF(Time heureF) {
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
}
