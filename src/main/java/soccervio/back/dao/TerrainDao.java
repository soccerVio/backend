package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
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

    List<Terrain> findByProprietaire(User proprietaire);

}
