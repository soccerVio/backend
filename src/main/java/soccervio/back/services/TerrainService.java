package soccervio.back.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import soccervio.back.dao.TerrainDao;
import soccervio.back.dtos.terrain.TerrainDTO;
import soccervio.back.entities.Photo;
import soccervio.back.entities.Terrain;
import soccervio.back.mappers.TerrainMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class TerrainService {

    private TerrainDao terrainDao;
    private PhotoService photoService;
    private UserService userService;
    private TerrainMapper terrainMapper;

    @SneakyThrows
    public ResponseEntity<Object> ajoutTerrain(MultipartFile image, String terrain){
        ObjectMapper mapper = new ObjectMapper();
        TerrainDTO terrainDTO;
        terrainDTO = mapper.readValue(terrain, TerrainDTO.class);
        Terrain t = terrainMapper.fromTerrainDto(terrainDTO);
        t.setImage(photoService.save(new Photo(image.getBytes(), image.getContentType())));
        t.setProprietaire(userService.getUserById(terrainDTO.getProprietaire()));
        return new ResponseEntity<>(terrainDao.save(t), HttpStatus.valueOf(201));
    }

    public ResponseEntity<List<Terrain>> getTerrains(){
        return new ResponseEntity<>(terrainDao.findAll(), HttpStatus.valueOf(200));
    }
}
