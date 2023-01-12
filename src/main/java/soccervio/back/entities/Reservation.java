package soccervio.back.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalTime heure;

    private Date date;


    private String genre;

    private int nbrJoueurManq;

    @ManyToOne
    private Terrain terrain;

    @ManyToOne
    private User reservePar;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "joueurs_match",
			joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "joueur_id", referencedColumnName = "id"))
	private Set<User> joueurs;

	public Reservation() {
	}

	public Reservation(long id, LocalTime heure, Date date, String genre,
					   int nbrJoueurManq, Terrain terrain, User reservePar, Set<User> joueurs) {
		this.id = id;
		this.heure = heure;
		this.date = date;
		this.genre = genre;
		this.nbrJoueurManq = nbrJoueurManq;
		this.terrain = terrain;
		this.reservePar = reservePar;
		this.joueurs = joueurs;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalTime getHeure() {
		return heure;
	}

	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNbrJoueurManq() {
		return nbrJoueurManq;
	}

	public void setNbrJoueurManq(int nbrJoueurManq) {
		this.nbrJoueurManq = nbrJoueurManq;
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

	public Set<User> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(Set<User> joueurs) {
		this.joueurs = joueurs;
	}
}
