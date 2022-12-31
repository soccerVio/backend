package soccervio.back.dtos.terrain;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

public class TerrainDTO {

    @NotBlank
    private String titre;

    @NotBlank
    private float longitude;

    @NotBlank
    private float latitude;

    @NotBlank
    private String adresse;

    @NotBlank
    private LocalTime heureO;

    @NotBlank
    private LocalTime heureF;

    @NotBlank
    private String description;

    @NotBlank
    private float prixHr;

    @NotBlank
    private int nbrJoueur;

    @NotBlank
    private boolean avecDouche;

    @NotBlank
    private boolean assure;

    @NotBlank
    private long proprietaire;

    public TerrainDTO() {
    }

    public String getTitre() {
        return titre;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getAdresse() {
        return adresse;
    }

    public LocalTime getHeureO() {
        return heureO;
    }

    public LocalTime getHeureF() {
        return heureF;
    }

    public String getDescription() {
        return description;
    }

    public float getPrixHr() {
        return prixHr;
    }

    public int getNbrJoueur() {
        return nbrJoueur;
    }

    public boolean isAvecDouche() {
        return avecDouche;
    }

    public boolean isAssure() {
        return assure;
    }

    public long getProprietaire() {
        return proprietaire;
    }

}
