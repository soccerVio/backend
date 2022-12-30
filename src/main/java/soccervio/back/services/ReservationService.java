package soccervio.back.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import soccervio.back.dao.ReservationDao;
import soccervio.back.dtos.reservation.ReservationDTO;
import soccervio.back.dtos.terrain.TerrainDTO;
import soccervio.back.entities.Reservation;
import soccervio.back.entities.Terrain;
import soccervio.back.mappers.ReservationMapper;

@Service
public class ReservationService {
	    private ReservationDao reservationDao;
	    private TerrainService terrainService;
	    private UserService userService;
	    private ReservationMapper reservationMapper;
	    
	   
	    
	    public ResponseEntity<Object> ajoutReservation(String reservation) throws JsonMappingException, JsonProcessingException{
	        ReservationDTO reservationDTO;
			reservationDTO = new ObjectMapper().readValue(reservation, ReservationDTO.class);
	    	Reservation r = reservationMapper.fromReservationDto(reservationDTO);
	    	r.setGendreJoueurs(reservationDTO.getGendreJoueurs());
	    	r.setNbrJoueur( reservationDTO.getNbrJoueur());
	        r.setHeureDebut(reservationDTO.getHeureDebut());
            r.setOuverte(reservationDTO.getOuverte());
            r.setTerrain(reservationDTO.getTerrain());
            r.setReservePar(reservationDTO.getReservePar());
	        return new ResponseEntity<>(reservationDao.save(r), HttpStatus.valueOf(200));
	    }
	    		
	    
	    public ResponseEntity<Reservation> getReservationById(long id){
	        return new ResponseEntity<>(reservationDao.findById(id).orElse(null), HttpStatus.valueOf(200));
	    }
	
	    
	    public ResponseEntity<List<Reservation>> getReservations(){
	        return new ResponseEntity<>(reservationDao.findAll(), HttpStatus.valueOf(200));
	    }
	    
	    public ResponseEntity<String> deleteReservation(long id){
	    	reservationDao.deleteById(id);
	        return new ResponseEntity<>("Reservation supprimée avec succès", HttpStatus.valueOf(200));
	    }
	    
	    
	    
}
