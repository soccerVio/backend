package soccervio.back.services;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import soccervio.back.dtos.user.UserDTO;
import soccervio.back.entities.Role;
import soccervio.back.entities.User;
import soccervio.back.mappers.UserMapper;
import soccervio.back.utils.JwtUtil;
import soccervio.back.entities.Image;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserService(UserDao userDao, RoleService roleService, UserMapper userMapper,
                       PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<String> signup(SignupUser signupUser){
        if (userDao.existsByUsernameOrEmail(signupUser.getUsername(), signupUser.getEmail()))
            return new ResponseEntity<>("Username ou email existe déja", HttpStatus.valueOf(405));

        if(!signupUser.getTypeCompte().equals("ROLE_JOUEUR")
                && !signupUser.getTypeCompte().equals("ROLE_PROPRIETAIRE"))
            return new ResponseEntity<>("Le type du compte est incorrect", HttpStatus.valueOf(406));

        User user = userMapper.fromSignupUser(signupUser);
        user.setPassword(passwordEncoder.encode(signupUser.getPassword()));

        Role role;
        if(roleService.existsByAuthority(signupUser.getTypeCompte()))
            role = roleService.findByAuthority(signupUser.getTypeCompte());
        else
            role = new Role(signupUser.getTypeCompte());
        user.setAuthorities(List.of(role));

        userDao.save(user);
        return new ResponseEntity<>("Utilisateur crée avec succès", HttpStatus.valueOf(201));
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

    public ResponseEntity<List<User>> findByNomCompletLike(String nomComplet) {
        return new ResponseEntity<>(userDao.findByNomCompletContainsIgnoreCase(nomComplet), HttpStatus.valueOf(200)) ;
    }

    public User getUserById(long id){
        return userDao.findById(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("Username not found");
        return user;
    }
   
    
    
    public ResponseEntity<Object> updateAccount(long userId , UserDTO userDto){
    	User currentUser =  userDao.findById(userId);
    	if (currentUser == null) {
    		throw new UsernameNotFoundException("user not found");
    	}
    		currentUser.setEmail(userDto.getEmail());
    		currentUser.setNomComplet(userDto.getNomComplet());
    		currentUser.setUsername(userDto.getUsername());
    		currentUser.setNumTel(userDto.getNumTel());
    		currentUser. setPassword(passwordEncoder.encode(userDto.getPassword()));
    		Image image = new Image();
        	image.setContent(userDto.getImage().getContent());
        	image.setType(userDto.getImage().getType());
        	currentUser.setImage(image);
    		User updatedUser = userDao.save(currentUser);
    	return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }
    
}

