package soccervio.back.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

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
    private String titre;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
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
}
