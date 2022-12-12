package soccervio.back.mappers;

import org.springframework.stereotype.Component;
import soccervio.back.dtos.user.SignupUser;
import soccervio.back.entities.User;

import java.util.Date;

@Component
public class UserMapper {

    public User fromSignupUser(SignupUser signupUser){
        User user = new User();
        user.setUsername(signupUser.getUsername());
        user.setEmail(signupUser.getEmail());
        user.setNomComplet(signupUser.getNomComplet());
        user.setDateCreation(new Date());
        return user;
    }
}
