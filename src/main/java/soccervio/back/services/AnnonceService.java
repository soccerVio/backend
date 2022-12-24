package soccervio.back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soccervio.back.dao.AnnonceDao;

@Service
@AllArgsConstructor
public class AnnonceService {
    private AnnonceDao annonceDao;
}
