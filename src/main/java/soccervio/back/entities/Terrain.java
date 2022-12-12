package soccervio.back.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="terrains")
public class Terrain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Image image;
    private float longitude;
    private float latitude;
    @Lob
    private String description;
    private Date dateCreation;
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
