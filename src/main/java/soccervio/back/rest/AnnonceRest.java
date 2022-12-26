package soccervio.back.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soccervio.back.constants.ApiContant;
import soccervio.back.services.AnnonceService;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/annonces")
public class AnnonceRest {
    private final AnnonceService annonceService;

    public AnnonceRest(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }
}
