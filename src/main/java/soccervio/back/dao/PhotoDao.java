package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Photo;

@Repository
public interface PhotoDao extends JpaRepository<Photo, Long> {
}
