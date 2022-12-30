package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Reservation;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Long>{

}
