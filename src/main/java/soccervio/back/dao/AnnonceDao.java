package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Annonce;
import soccervio.back.entities.Reservation;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AnnonceDao extends JpaRepository<Annonce, Long> {

    void deleteByReservation(Reservation reservation);

    Annonce findByReservation(Reservation reservation);
}  
