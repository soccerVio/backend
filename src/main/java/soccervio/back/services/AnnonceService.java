package soccervio.back.services;

import org.springframework.stereotype.Service;
import soccervio.back.dao.AnnonceDao;

@Service
public class AnnonceService {
    private final AnnonceDao annonceDao;

    public AnnonceService(AnnonceDao annonceDao) {
        this.annonceDao = annonceDao;
    }
}
