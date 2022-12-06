package soccervio.back.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
public class Terrain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Photo image;
    private float longitude;
    private float latitude;
    @Lob
    private String description;
    private String ville;
    private Time heureO;
    private Time heureF;
    private float prixHr;
    private int nbrJoueur;
    private boolean avecDouche;
    private boolean assure;
    @ManyToOne
    private User proprietaire;
}
