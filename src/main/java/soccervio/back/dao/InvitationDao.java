package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import soccervio.back.entities.Annonce;
import soccervio.back.entities.Invitation;

import java.util.List;

@Repository
public interface InvitationDao extends JpaRepository<Invitation, Long> {
    void deleteByAnnonce(Annonce annonce);

    List<Invitation> findByAnnonceIn(List<Annonce> annonceList);
    List<Invitation> findByAnnonce(Annonce annonce);
}
