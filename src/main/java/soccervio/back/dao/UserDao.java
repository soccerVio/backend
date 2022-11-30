package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String usename);
    boolean existsByUsernameOrEmail(String usename, String email);
}
