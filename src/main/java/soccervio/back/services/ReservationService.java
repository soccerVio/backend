package soccervio.back.services;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import soccervio.back.dao.ReservationDao;
import soccervio.back.dtos.reservation.ReservationDTO;
import soccervio.back.entities.*;
import soccervio.back.mappers.ReservationMapper;

import javax.transaction.Transactional;

@Service
public class ReservationService {
	private final ReservationDao reservationDao;
	private final ReservationMapper reservationMapper;
	private final TerrainService terrainService;
	private final NotificationStorageService notificationStorageService;
	@Autowired
	private AnnonceService annonceService;
	private final UserService userService;

	public ReservationService(ReservationDao reservationDao, TerrainService terrainService,
							  ReservationMapper reservationMapper, UserService userService,
							  NotificationStorageService notificationStorageService) {
		this.reservationDao = reservationDao;
		this.terrainService = terrainService;
		this.reservationMapper = reservationMapper;
		this.notificationStorageService = notificationStorageService;
		this.userService = userService;
	}

	public List<LocalTime> getByDateAndTerrain(Date date, long idTerrain) {
		Terrain terrain = terrainService.getTerrainById(idTerrain);
		List<Reservation> reservations = reservationDao.findByDateAndTerrain(date, terrain);
		List<LocalTime> reserverdHours = reservations.stream().map(Reservation::getHeure).toList();

		List<LocalTime> heuresDispo = new ArrayList<>();
		for (LocalTime heure = terrain.getHeureO();
			 heure.isBefore(terrain.getHeureF());
			 heure = heure.plusHours(1)){

			if (!reserverdHours.contains(heure))
				heuresDispo.add(heure);
		}

		return heuresDispo;
	}

	public ResponseEntity<Reservation> ajoutReservation(ReservationDTO reservationDTO) {
		Reservation reservation = reservationMapper.fromReservationDto(reservationDTO);
		User reservePar = userService.getUserById(reservationDTO.getReservePar());
		Terrain terrain = terrainService.getTerrainById(reservationDTO.getIdTerrain());
		reservation.setReservePar(reservePar);
		reservation.setTerrain(terrain);
		Set<User> joueurs =
				reservationDTO
						.getIdJoueurs()
						.stream()
						.map(userService::getUserById)
						.collect(Collectors.toSet());
		reservation.setJoueurs(joueurs);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		notificationStorageService.createNotificationStorage(Notification
				.builder()
				.dateEnvoie(new Date())
				.delivered(false)
				.content(reservePar.getNomComplet()
						+ " a reservé votre terrain "
						+ terrain.getTitre()
						+ " le "
						+ sdf.format(reservation.getDate())
						+ " à "
						+ reservation.getHeure())
				.notificationType(NotificationType.NEW_RESERVATION)
				.userFrom(reservePar)
				.userTo(terrain.getProprietaire())
				.build());

		return new ResponseEntity<>(reservationDao.save(reservation), HttpStatus.valueOf(201));
	}

	public Reservation getReservationById(long id){
		return reservationDao.findById(id).orElse(null);
	}

	public ResponseEntity<List<Reservation>> getReservationsOfJoueur(long idJoueur){
		User joueur = userService.getUserById(idJoueur);
		return new ResponseEntity<>(reservationDao.findByReservePar(joueur), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<List<Reservation>> getReservationsOfProprietaire(long idProp){
		List<Terrain> terrains = terrainService.getTerrainsByProp(idProp);
		return new ResponseEntity<>(reservationDao.findByTerrainIn(terrains), HttpStatus.ACCEPTED);
	}

	@Transactional
	public ResponseEntity<String> annulerReservation(long idResrvation){
		Reservation reservation = getReservationById(idResrvation);
		annonceService.deleteAnnonceByReservation(reservation);
		reservationDao.deleteById(idResrvation);
		return new ResponseEntity<>("reservation annulé ave succès", HttpStatus.ACCEPTED);
	}
}
