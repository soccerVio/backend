package soccervio.back.entities;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
/*
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor*/
@Entity
//@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Time heureDebut;
    private Date date;

    private boolean ouverte;
    private String gendreJoueurs ;
    private int nbrJoueur;
    @OneToOne
    private Terrain terrain;
    @ManyToOne
    private User reservePar;

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
	public boolean getOuverte() {
		return ouverte;
	}
	public void setOuverte(boolean ouverte) {
		this.ouverte = ouverte;
	}
	public String getGendreJoueurs() {
		return gendreJoueurs;
	}
	public void setGendreJoueurs(String gendreJoueurs) {
		this.gendreJoueurs = gendreJoueurs;
	}
	public int getNbrJoueur() {
		return nbrJoueur;
	}
	public void setNbrJoueur(int nbrJoueur) {
		this.nbrJoueur = nbrJoueur;
	}
	public Terrain getTerrain() {
		return terrain;
	}
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	public User getReservePar() {
		return reservePar;
	}
	public void setReservePar(User reservePar) {
		this.reservePar = reservePar;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
