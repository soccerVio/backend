package soccervio.back.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccervio.back.constants.ApiContant;
import soccervio.back.dtos.user.SigninUser;
import soccervio.back.dtos.user.SignupUser;
import soccervio.back.entities.User;
import soccervio.back.dtos.user.UserDTO;
import soccervio.back.services.UserService;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiContant.BASE_URL + "/users/")
public class UserRest {

    private final UserService userService;

    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupUser signupUser) {
        return userService.signup(signupUser);
    }

    @PostMapping("sign-in")
    public ResponseEntity<Object> signin(@Valid @RequestBody SigninUser signinUser) {
        return userService.signin(signinUser);
    }

    @GetMapping("id/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }
    @GetMapping("{nomComplet}")
    public ResponseEntity<List<User>> findByNomCompletLike(@PathVariable String nomComplet) {
        return userService.findByNomCompletLike(nomComplet);
    }
    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateAccount(@PathVariable Long id ,@RequestBody UserDTO userDTO) {
        return  userService.updateAccount(id, userDTO);
    }

}
