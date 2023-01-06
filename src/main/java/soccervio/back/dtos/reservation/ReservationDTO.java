package soccervio.back.dtos.reservation;

import java.sql.Time;
import java.util.Date;

import javax.validation.constraints.NotBlank;

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

	public String getGendreJoueurs() {
		return gendreJoueurs;
	}
	public void setGendreJoueurs(String gendreJoueurs) {
		this.gendreJoueurs = gendreJoueurs;
	}
	public boolean getOuverte() {
		return Ouverte;
	}
	public void setOuverte(boolean ouverte) {
		Ouverte = ouverte;
	}
	public int getNbrJoueur() {
		return nbrJoueur;
	}
	public void setNbrJoueur(int nbrJoueur) {
		this.nbrJoueur = nbrJoueur;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}








}
