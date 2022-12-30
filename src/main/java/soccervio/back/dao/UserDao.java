package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.User;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String usename);
    boolean existsByUsernameOrEmail(String usename, String email);
<<<<<<< HEAD
    List<User> findByNomCompletContainsIgnoreCase(String nomComplet);
=======
	User findById(long id);
    
>>>>>>> ca552d4c61ff1e571ed8fca5f5ff49ab2d6d0d4d
}
