package soccervio.back.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import soccervio.back.constants.ApiContant;
import soccervio.back.entities.Terrain;
import soccervio.back.services.TerrainService;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/terrains")
public class TerrainRest {

    private final TerrainService terrainService;

    public TerrainRest(TerrainService terrainService) {
        this.terrainService = terrainService;
    }

    @PostMapping(value = "/ajout", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> ajoutTerrain(@RequestParam MultipartFile[] images, @RequestParam String terrain) {
        return terrainService.ajoutTerrain(images, terrain);
    }

    @GetMapping
    public ResponseEntity<List<Terrain>> getTerrains() {
        return terrainService.getTerrains();
    }

    @GetMapping("/proprietaire/{idProp}")
    public List<Terrain> getTerrainsByProp(@PathVariable long idProp) {
        return terrainService.getTerrainsByProp(idProp);
    }

    @GetMapping("/{id}")
    public Terrain getTerrainById(@PathVariable long id) {
        return terrainService.getTerrainById(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> modifierTerrain(@RequestParam Optional<MultipartFile[]> images, @RequestParam String terrain) {
        return terrainService.modifierTerrain(images, terrain);
    }

    @GetMapping("/search/{adresse}")
    public ResponseEntity<List<Terrain>> searchByAdresse(@PathVariable String adresse) {
        return terrainService.searchByAdresse(adresse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTerrain(@PathVariable long id) {
        return terrainService.deleteTerrain(id);
    }
    @GetMapping("/searchbyPosition")
    public List<float[]> getPositionsNear(@PathVariable float latitude, @PathVariable float longitude,@PathVariable Date dateDebut,@PathVariable Date dateFin, @PathVariable LocalTime heurDebut, @PathVariable LocalTime heureFin){
        return  terrainService.getPositionsNear(latitude,longitude,dateDebut,dateFin,heurDebut,heureFin);
    }

}
