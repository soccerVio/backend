package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import soccervio.back.entities.Annonce;
import soccervio.back.entities.Invitation;

@Repository
public interface InvitationDao extends JpaRepository<Invitation, Long> {
    void deleteByAnnonce(Annonce annonce);
}
