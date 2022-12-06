package soccervio.back.dtos.terrain;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;

@NoArgsConstructor
@Data
public class TerrainDTO {
    private float longitude;
    private float latitude;
    private String ville;
    private Time heureO;
    private Time heureF;
    private float prixHr;
    private int nbrJoueur;
    private boolean avecDouche;
    private boolean assure;
    private long proprietaire;
}
