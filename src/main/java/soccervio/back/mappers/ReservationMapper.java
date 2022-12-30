package soccervio.back.mappers;

import soccervio.back.dtos.reservation.ReservationDTO;
import soccervio.back.entities.Reservation;

public class ReservationMapper {
	  public Reservation fromReservationDto(ReservationDTO reservationDTO){
		  Reservation reservation = new Reservation();
		  reservation.setGendreJoueurs(reservationDTO.getGendreJoueurs());
		  reservation.setNbrJoueur( reservationDTO.getNbrJoueur());
		  reservation.setHeureDebut(reservationDTO.getHeureDebut());
		  reservation.setOuverte(reservationDTO.getOuverte());
		  reservation.setTerrain(reservationDTO.getTerrain());
		  reservation.setReservePar(reservationDTO.getReservePar());
		  reservation.setDate(reservationDTO.getDate());
		  return reservation;
	  }}