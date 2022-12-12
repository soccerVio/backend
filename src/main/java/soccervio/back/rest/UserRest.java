package soccervio.back.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soccervio.back.constants.ApiContant;
import soccervio.back.dtos.user.SigninUser;
import soccervio.back.dtos.user.SignupUser;
import soccervio.back.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/users/")
@AllArgsConstructor
public class UserRest {

    private UserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupUser signupUser) {
        return userService.signup(signupUser);
    }

    @PostMapping("sign-in")
    public ResponseEntity<Object> signin(@Valid @RequestBody SigninUser signinUser) {
        return userService.signin(signinUser);
    }
}
