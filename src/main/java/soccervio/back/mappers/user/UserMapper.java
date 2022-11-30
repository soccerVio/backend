package soccervio.back.mappers.user;

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
        user.setFullname(signupUser.getFullname());
        user.setCreatedAt(new Date());
        user.setGender(signupUser.getGender());
        return user;
    }
}
