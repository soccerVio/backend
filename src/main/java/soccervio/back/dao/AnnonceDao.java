package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Annonce;

@Repository
public interface AnnonceDao extends JpaRepository<Annonce, Long> {
}
