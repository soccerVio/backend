package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.User;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String usename);
    boolean existsByUsernameOrEmail(String usename, String email);
    List<User> findByNomCompletContainsIgnoreCase(String nomComplet);
	User findById(long id);
    
}
