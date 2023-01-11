package soccervio.back.dtos.reservation;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class ReservationDTO {

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
