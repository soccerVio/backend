package soccervio.back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soccervio.back.dao.PhotoDao;
import soccervio.back.entities.Photo;

@Service
@AllArgsConstructor
public class PhotoService {

    private PhotoDao photoDao;

    public Photo save(Photo photo) {
        return photoDao.save(photo);
    }
}
