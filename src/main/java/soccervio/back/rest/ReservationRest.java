package soccervio.back.rest;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import soccervio.back.constants.ApiContant;
import soccervio.back.dtos.reservation.ReservationDTO;
import soccervio.back.entities.Reservation;
import soccervio.back.services.ReservationService;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/reservations")
public class ReservationRest {
    private final ReservationService reservationService;

    public ReservationRest(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{date}/{idTerrain}")
    public List<LocalTime> getByDateAndTerrain(@PathVariable Date date, @PathVariable long idTerrain) {
        return reservationService.getByDateAndTerrain(date, idTerrain);
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> ajoutReservation(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.ajoutReservation(reservationDTO);
    }

    @GetMapping("/joueur/{idJoueur}")
    public ResponseEntity<List<Reservation>> getReservationsOfJoueur(@PathVariable long idJoueur) {
        return reservationService.getReservationsOfJoueur(idJoueur);
    }

    @GetMapping("/proprietaire/{idProp}")
    public ResponseEntity<List<Reservation>> getReservationsOfProprietaire(@PathVariable long idProp) {
        return reservationService.getReservationsOfProprietaire(idProp);
    }

    @DeleteMapping("/delete/{idResrvation}")
    public ResponseEntity<String> annulerReservation(@PathVariable long idResrvation) {
        return reservationService.annulerReservation(idResrvation);
    }
}
