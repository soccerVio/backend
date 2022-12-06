package soccervio.back.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import soccervio.back.services.TerrainService;

@RestController
@RequestMapping("/api/v1/terrains/")
@AllArgsConstructor
public class TerrainRest {

    private TerrainService terrainService;

    @PostMapping(value = "ajout", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> ajoutTerrain(@RequestParam MultipartFile image, @RequestParam String terrain
                                               ) {
        return terrainService.ajoutTerrain(image, terrain);
    }
}
