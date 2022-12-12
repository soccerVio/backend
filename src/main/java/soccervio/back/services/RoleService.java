package soccervio.back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import soccervio.back.dao.RoleDao;
import soccervio.back.entities.Role;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleDao roleDao;

    public boolean existsByAuthority(String authority) {
        return roleDao.existsByAuthority(authority);
    }

    public Role findByAuthority(String authority) {
        return roleDao.findByAuthority(authority);
    }
}
