package soccervio.back.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import soccervio.back.dao.TerrainDao;
import soccervio.back.dtos.terrain.TerrainDTO;
import soccervio.back.entities.Image;
import soccervio.back.entities.Terrain;
import soccervio.back.mappers.TerrainMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class TerrainService {

    private TerrainDao terrainDao;
    private UserService userService;
    private TerrainMapper terrainMapper;

    @SneakyThrows
    public ResponseEntity<Object> ajoutTerrain(MultipartFile image, String terrain){
        TerrainDTO terrainDTO = new ObjectMapper().readValue(terrain, TerrainDTO.class);
        Terrain t = terrainMapper.fromTerrainDto(terrainDTO);
        t.setImage(new Image(image.getBytes(), image.getContentType()));
        t.setProprietaire(userService.getUserById(terrainDTO.getProprietaire()));
        return new ResponseEntity<>(terrainDao.save(t), HttpStatus.valueOf(201));
    }

    public ResponseEntity<List<Terrain>> getTerrains(){
        return new ResponseEntity<>(terrainDao.findAll(), HttpStatus.valueOf(200));
    }

    public ResponseEntity<Terrain> getTerrainById(long id){
        return new ResponseEntity<>(terrainDao.findById(id).orElse(null), HttpStatus.valueOf(200));
    }
}
