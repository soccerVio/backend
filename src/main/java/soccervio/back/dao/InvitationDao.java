package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import soccervio.back.entities.Invitation;

public interface InvitationDao extends JpaRepository<Invitation, Long> {

}
