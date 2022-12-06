package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Terrain;

@Repository
public interface TerrainDao extends JpaRepository<Terrain, Long> {
}
