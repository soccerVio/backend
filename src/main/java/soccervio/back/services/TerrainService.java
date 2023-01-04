package soccervio.back.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import soccervio.back.dao.TerrainDao;
import soccervio.back.dtos.terrain.TerrainDTO;
import soccervio.back.entities.Terrain;
import soccervio.back.entities.User;
import soccervio.back.mappers.TerrainMapper;
import soccervio.back.utils.ImageUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TerrainService {

    private final TerrainDao terrainDao;
    private final UserService userService;
    private final TerrainMapper terrainMapper;
    private final ImageUtil imageUtil;

    public TerrainService(TerrainDao terrainDao, UserService userService,
                          TerrainMapper terrainMapper, ImageUtil imageUtil) {
        this.terrainDao = terrainDao;
        this.userService = userService;
        this.terrainMapper = terrainMapper;
        this.imageUtil = imageUtil;
    }

    public ResponseEntity<Object> ajoutTerrain(MultipartFile[] images, String terrain){
        TerrainDTO terrainDTO = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            terrainDTO = objectMapper.readValue(terrain, TerrainDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Terrain t = terrainMapper.fromTerrainDto(terrainDTO);
        Set<String> imagesList = Arrays.stream(images).map(image -> {
            try {
                return imageUtil.saveImage(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());
        t.setImages(imagesList);
        t.setProprietaire(userService.getUserById(terrainDTO.getProprietaire()));
        return new ResponseEntity<>(terrainDao.save(t), HttpStatus.valueOf(201));
    }

    public ResponseEntity<List<Terrain>> getTerrains(){
        return new ResponseEntity<>(terrainDao.findAll(), HttpStatus.valueOf(200));
    }

    public List<Terrain> getTerrainsByProp(long idProp){
        User proprietaire = userService.getUserById(idProp);
        return terrainDao.findByProprietaire(proprietaire);
    }

    public Terrain getTerrainById(long id){
        return terrainDao.findById(id).orElse(null);
    }

    public ResponseEntity<Object> modifierTerrain(Optional<MultipartFile[]> images, String terrain){
        Terrain t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            t = objectMapper.readValue(terrain, Terrain.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Terrain updatedTerrain = terrainDao.findById(t.getId()).get();
        updatedTerrain.setTitre(t.getTitre());
        updatedTerrain.setDescription(t.getDescription());
        updatedTerrain.setAdresse(t.getAdresse());
        updatedTerrain.setHeureO(t.getHeureO());
        updatedTerrain.setHeureF(t.getHeureF());
        updatedTerrain.setPrixHr(t.getPrixHr());
        updatedTerrain.setNbrJoueur(t.getNbrJoueur());
        updatedTerrain.setAssure(t.isAssure());
        updatedTerrain.setAvecDouche(t.isAvecDouche());
        if(images.isPresent()){
            MultipartFile[] imagesList = images.get();
            if(imagesList.length > 0){
                Set<String> imageSet = updatedTerrain.getImages();
                Arrays.stream(imagesList).forEach(image -> {
                    try {
                        imageSet.add(imageUtil.saveImage(image));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                updatedTerrain.setImages(imageSet);
            }
        }
        return new ResponseEntity<>(terrainDao.save(updatedTerrain), HttpStatus.ACCEPTED);
    }

    /*public List<Pair<Float, Float>> getPositionsNear(float latitude,float longitude){
        List<Terrain> terrains1 = terrainDao.findByLatitudeBetweenAndLongitudeBetween(
                latitude, latitude + 0.01F, longitude,longitude + 0.01F);
        List<Terrain> terrains2 = terrainDao.findByLatitudeBetweenAndLongitudeBetween(
                latitude, latitude - 0.01F, longitude,longitude - 0.01F);
        List<Terrain> terrains3 = terrainDao.findByLatitudeBetweenAndLongitudeBetween(
                latitude, latitude - 0.01F, longitude,longitude + 0.01F);
        List<Terrain> terrains4 = terrainDao.findByLatitudeBetweenAndLongitudeBetween(
                latitude, latitude + 0.01F, longitude,longitude - 0.01F);
    }*/
    
    
    
    public ResponseEntity<List<Terrain>> searchByAdresse(String adresse){
        List<Terrain> terrains = terrainDao.findByAdresseContainsIgnoreCase(adresse);
        System.out.println(adresse);
        for (Terrain t : terrains)
            System.out.println(t);
        return new ResponseEntity<>(terrains, HttpStatus.valueOf(200));
    }

    public ResponseEntity<String> deleteTerrain(long id){
        Terrain terrain = getTerrainById(id);
        terrainDao.deleteById(id);
        terrain.getImages().forEach(image ->{
            try {
                imageUtil.deleteImage(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return new ResponseEntity<>("Terrain supprimé avec succès", HttpStatus.valueOf(200));
    }
    
    
    
  /*  public  List<Terrain> searchTerrainsByPosition(double longitude1, double latitude1, double longitude2, double latitude2) {
          return terrainDao.findByPositionBetween(longitude1, latitude1, longitude2, latitude2);
        
        
    }*/
}
