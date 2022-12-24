package soccervio.back.entities;


import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    @JoinTable(name = "joueurs_annoces",
            joinColumns = @JoinColumn(name = "annonce_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "joueur_id", referencedColumnName = "id"))
    private List<User> joueurs;
}
