package soccervio.back.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccervio.back.constants.ApiContant;
import soccervio.back.dtos.annoce.AnnonceDTO;
import soccervio.back.entities.Annonce;
import soccervio.back.services.AnnonceService;

import java.util.List;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/annonces")
public class AnnonceRest {
    private final AnnonceService annonceService;

    public AnnonceRest(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @PostMapping("/add")
    public ResponseEntity<Annonce> ajoutAnnnonce(@RequestBody AnnonceDTO annonceDTO) {
        return annonceService.ajoutAnnnonce(annonceDTO);
    }

    @GetMapping
    public ResponseEntity<List<Annonce>> getAnnonces() {
        return annonceService.getAnnonces();
    }

    @PutMapping("/participer/{idUser}/{idAnnonce}")
    public ResponseEntity<String> participerAnnonce(@PathVariable long idUser,@PathVariable long idAnnonce) {
        return annonceService.participerAnnonce(idUser, idAnnonce);
    }
}
