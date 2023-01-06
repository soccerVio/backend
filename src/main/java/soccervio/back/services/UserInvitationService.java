package soccervio.back.services;

import org.springframework.stereotype.Service;
import soccervio.back.dao.UserInvitationDao;
import soccervio.back.entities.UserInvitation;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserInvitationService {
    private final UserInvitationDao userInvitationDao;

    public UserInvitationService(UserInvitationDao userInvitationDao) {
        this.userInvitationDao = userInvitationDao;
    }

    public Set<UserInvitation> saveAll(Set<UserInvitation> userInvitationSet){
        return userInvitationSet
                .stream()
                .map(userInvitationDao::save).
                collect(Collectors.toSet());
    }
}
