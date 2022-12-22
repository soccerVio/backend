package soccervio.back.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import soccervio.back.constants.ApiContant;
import soccervio.back.entities.Terrain;
import soccervio.back.services.TerrainService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/terrains")
@AllArgsConstructor
public class TerrainRest {

    private TerrainService terrainService;

    @PostMapping(value = "/ajout", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> ajoutTerrain(@RequestParam MultipartFile[] images, @RequestParam String terrain) {
        return terrainService.ajoutTerrain(images, terrain);
    }

    @GetMapping
    public ResponseEntity<List<Terrain>> getTerrains() {
        return terrainService.getTerrains();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terrain> getTerrainById(@PathVariable long id) {
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
}
