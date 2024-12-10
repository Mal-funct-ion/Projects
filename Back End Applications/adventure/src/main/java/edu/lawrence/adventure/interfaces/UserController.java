package edu.lawrence.adventure.interfaces;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.lawrence.adventure.services.UserService;
import edu.lawrence.adventure.services.DuplicateException;
import edu.lawrence.adventure.services.PlayerService;
import edu.lawrence.adventure.interfaces.dtos.UserDTO;
import edu.lawrence.adventure.entities.User;
import edu.lawrence.adventure.entities.Player;
import edu.lawrence.adventure.security.JwtService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

	private UserService us;
	private JwtService jwt;
	private PlayerService play;
    
    public UserController(UserService us, JwtService jwt, PlayerService play) {
        this.us = us;
        this.jwt = jwt;
        this.play = play;
    }

    @PostMapping("/login")
    public ResponseEntity<String> checkLogin(@RequestBody UserDTO user) {
        User result = us.findByNameAndPassword(user.getUsername(), user.getPassword());
        if (result == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user name or password");
        }
        String token = jwt.makeJwt(result.getUserid().toString());
        return ResponseEntity.ok().body(token);
    }
    
    @PostMapping
    public ResponseEntity<String> save(@RequestBody UserDTO user) throws DuplicateException {
        if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty user name or password");
        }
        String key;
        key = us.save(user); 
        String token = jwt.makeJwt(key);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
    
}