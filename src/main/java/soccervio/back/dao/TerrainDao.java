package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Terrain;
import soccervio.back.entities.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TerrainDao extends JpaRepository<Terrain, Long> {
    List<Terrain> findByLatitudeBetweenAndLongitudeBetween(
            float latitiude1, float latitiude2, float longitude1, float longitude2);

    List<Terrain> findByAdresseContainsIgnoreCase(String adresse);
<<<<<<< HEAD
    @Query("SELECT t FROM terrains t WHERE t.longitude BETWEEN :longitude1 AND :longitude2 AND t.latitude BETWEEN :latitude1 AND :latitude2"
        )
    List<Terrain> findByPositionBetween(float longitude1, float latitude1, float longitude2, float latitude2);
=======

    List<Terrain> findByProprietaire(User proprietaire);

>>>>>>> aa2f69eb2dc4412e99e77f9f23ca44764b575fe9
}
