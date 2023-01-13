package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Invitation;
import soccervio.back.entities.UserInvitation;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserInvitationDao extends JpaRepository<UserInvitation, Long> {

    List<UserInvitation> findByInvite_IdAndAccepteParInviteIsFalse(long idInvite);

    List<UserInvitation> findByInvitationInAndAccepteParInviteTrueAndAccepteParPropFalse(List<Invitation> invitations);

    void deleteByInvitationIn(List<Invitation> invitations);
}
