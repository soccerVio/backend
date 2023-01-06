package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Terrain;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TerrainDao extends JpaRepository<Terrain, Long> {
    List<Terrain> findByLatitudeBetweenAndLongitudeBetween(
            float latitiude1, float latitiude2, float longitude1, float longitude2);

    List<Terrain> findByAdresseContainsIgnoreCase(String adresse);
    @Query("SELECT t FROM terrains t WHERE t.longitude BETWEEN :longitude1 AND :longitude2 AND t.latitude BETWEEN :latitude1 AND :latitude2"
        )
    List<Terrain> findByPositionBetween(float longitude1, float latitude1, float longitude2, float latitude2);
}
