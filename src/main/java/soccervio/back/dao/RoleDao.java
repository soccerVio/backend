package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    boolean existsByAuthority(String authority);
    Role findByAuthority(String authority);
}
