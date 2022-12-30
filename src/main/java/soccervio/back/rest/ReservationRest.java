package soccervio.back.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import soccervio.back.entities.Reservation;
import soccervio.back.services.ReservationService;

public class ReservationRest {
    private ReservationService reservationService;
    
    
    @PostMapping(value = "/ajout", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> ajoutReservation( @RequestParam String reservation) {
        try {
			return reservationService.ajoutReservation(reservation);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable long id) {
        return reservationService.deleteReservation(id);
    }
    
    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable long id) {
        return reservationService.getReservationById(id);
    }
}
