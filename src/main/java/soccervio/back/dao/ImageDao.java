package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Image;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {
}
