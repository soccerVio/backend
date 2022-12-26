package soccervio.back.services;

import org.springframework.stereotype.Service;
import soccervio.back.dao.RoleDao;
import soccervio.back.entities.Role;

@Service
public class RoleService {

    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public boolean existsByAuthority(String authority) {
        return roleDao.existsByAuthority(authority);
    }

    public Role findByAuthority(String authority) {
        return roleDao.findByAuthority(authority);
    }
}
