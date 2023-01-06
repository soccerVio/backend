package soccervio.back.dtos.reservation;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

<<<<<<< HEAD
/*import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;*/
import soccervio.back.entities.Terrain;
import soccervio.back.entities.User;
/*@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor*/
public class ReservationDTO {
    @NotBlank
	private long id;
    @NotBlank
    private Time heureDebut;
    @NotBlank
    private Date  date ;
	@NotBlank
    private String gendreJoueurs ;
    @NotBlank
    private boolean Ouverte;
    @NotBlank
    private int nbrJoueur;
    @NotBlank
    private User reservePar;
    @NotBlank
    private Terrain terrain ;
    public User getReservePar() {
 		return reservePar;
 	}
 	public void setReservePar(User reservePar) {
 		this.reservePar = reservePar;
 	}
 	public Terrain getTerrain() {
 		return terrain;
 	}
 	public void setTerrain(Terrain terrain) {
 		this.terrain = terrain;
 	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Time getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}
=======
public class ReservationDTO {
>>>>>>> aa2f69eb2dc4412e99e77f9f23ca44764b575fe9

	@NotBlank
	private Date date;

	@NotBlank
	private String genre;

    @NotBlank
    private String heure;
	@NotBlank
	private int nbrJoueurManq;

	@NotBlank
	private long idTerrain ;

	private List<Long> idJoueurs;

    @NotBlank
    private long reservePar;


	public Date getDate() {
		return date;
	}

	public String getGenre() {
		return genre;
	}
<<<<<<< HEAD



=======
>>>>>>> aa2f69eb2dc4412e99e77f9f23ca44764b575fe9

	public String getHeure() {
		return heure;
	}

	public long getIdTerrain() {
		return idTerrain;
	}

	public List<Long> getIdJoueurs() {
		return idJoueurs;
	}

	public int getNbrJoueurManq() {
		return nbrJoueurManq;
	}

	public long getReservePar() {
		return reservePar;
	}
}
