package soccervio.back.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import soccervio.back.entities.Terrain;
import soccervio.back.services.TerrainService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/terrains")
@AllArgsConstructor
public class TerrainRest {

    private TerrainService terrainService;

    @PostMapping(value = "/ajout", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> ajoutTerrain(@RequestParam MultipartFile image, @RequestParam String terrain) {
        return terrainService.ajoutTerrain(image, terrain);
    }

    @GetMapping
    public ResponseEntity<List<Terrain>> getTerrains() {
        return terrainService.getTerrains();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terrain> getTerrainById(@PathVariable long id) {
        return terrainService.getTerrainById(id);
    }
}
