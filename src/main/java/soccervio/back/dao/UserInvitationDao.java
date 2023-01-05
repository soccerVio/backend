package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.UserInvitation;

@Repository
public interface UserInvitationDao extends JpaRepository<UserInvitation, Long> {
}
