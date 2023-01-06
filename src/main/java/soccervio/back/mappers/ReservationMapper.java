package soccervio.back.mappers;

import org.springframework.stereotype.Component;
import soccervio.back.dtos.reservation.ReservationDTO;
import soccervio.back.entities.Reservation;

import java.time.LocalTime;

@Component
public class ReservationMapper {

	  public Reservation fromReservationDto(ReservationDTO reservationDTO){
		  Reservation reservation = new Reservation();
		  reservation.setGenre(reservationDTO.getGenre());
		  reservation.setNbrJoueurManq(reservationDTO.getNbrJoueurManq());
		  reservation.setHeure(LocalTime.parse(reservationDTO.getHeure()));
		  reservation.setDate(reservationDTO.getDate());
		  return reservation;
	  }
}