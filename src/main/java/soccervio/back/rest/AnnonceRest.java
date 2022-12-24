package soccervio.back.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soccervio.back.constants.ApiContant;
import soccervio.back.services.AnnonceService;

@AllArgsConstructor
@RestController
@RequestMapping(ApiContant.BASE_URL + "/annonces")
public class AnnonceRest {
    private AnnonceService annonceService;
}
