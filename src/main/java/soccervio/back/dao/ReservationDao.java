package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Reservation;
import soccervio.back.entities.Terrain;
import soccervio.back.entities.User;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface ReservationDao extends JpaRepository<Reservation, Long>{
    List<Reservation> findByDateAndTerrain(Date date, Terrain terrain);

    List<Reservation> findByReservePar(User joueur);

    List<Reservation> findByTerrainIn(List<Terrain> terrains);
}
