package soccervio.back.mappers;

import org.springframework.stereotype.Component;
import soccervio.back.dtos.terrain.TerrainDTO;
import soccervio.back.entities.Terrain;

import java.util.Date;

@Component
public class TerrainMapper {
    public Terrain fromTerrainDto(TerrainDTO terrainDTO){
        Terrain terrain = new Terrain();
        terrain.setTitre(terrainDTO.getTitre());
        terrain.setLatitude(terrainDTO.getLatitude());
        terrain.setLongitude(terrainDTO.getLongitude());
        terrain.setAdresse(terrainDTO.getAdresse());
        terrain.setDescription(terrainDTO.getDescription());
        terrain.setHeureO(terrainDTO.getHeureO());
        terrain.setHeureF(terrainDTO.getHeureF());
        terrain.setPrixHr(terrainDTO.getPrixHr());
        terrain.setNbrJoueur(terrainDTO.getNbrJoueur());
        terrain.setDateCreation(new Date());
        terrain.setAvecDouche(terrainDTO.isAvecDouche());
        terrain.setAssure(terrainDTO.isAssure());
        return terrain;
    }
}
