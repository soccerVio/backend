package soccervio.back.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soccervio.back.dao.UserDao;
import soccervio.back.dtos.user.SigninUser;
import soccervio.back.dtos.user.SignupUser;
import soccervio.back.entities.Role;
import soccervio.back.entities.User;
import soccervio.back.mappers.user.UserMapper;
import soccervio.back.utils.JwtUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public ResponseEntity<String> signup(SignupUser signupUser){
        if (userDao.existsByUsernameOrEmail(signupUser.getUsername(), signupUser.getEmail()))
            return new ResponseEntity("Username or email already used", HttpStatus.valueOf(400));

        if(!signupUser.getType().equals("ROLE_JOUEUR") && !signupUser.getType().equals("ROLE_PROPRIETAIRE"))
            return new ResponseEntity("Type field is invalid", HttpStatus.valueOf(400));

        User user = userMapper.fromSignupUser(signupUser);
        user.setPassword(passwordEncoder.encode(signupUser.getPassword()));
        Role role;
        if(roleService.existsByAuthority(signupUser.getType()))
            role = roleService.findByAuthority(String.valueOf(signupUser.getType()));
        else
            role = roleService.save(new Role(String.valueOf(signupUser.getType())));
        user.setAuthorities(List.of(role));
        userDao.save(user);
        return new ResponseEntity<>("User created", HttpStatus.valueOf(201));
    }

    public ResponseEntity<Object> signin(SigninUser signinUser){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinUser.getUsername(), signinUser.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>("Bad credentials", HttpStatus.valueOf(403));
        }
        User user = loadUserByUsername(signinUser.getUsername());
        String token = jwtUtil.generateKey(user);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        return new ResponseEntity<>(response, HttpStatus.valueOf(200));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("Username not found");
        return user;
    }
}
