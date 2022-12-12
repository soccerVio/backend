package soccervio.back.dtos.terrain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Time;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TerrainDTO {

    @NotBlank
    private float longitude;

    @NotBlank
    private float latitude;

    @NotBlank
    private String ville;

    @NotBlank
    private Time heureO;

    @NotBlank
    private Time heureF;

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

}
