package edu.lawrence.adventure.interfaces;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.lawrence.adventure.entities.Location;
import edu.lawrence.adventure.entities.Player;
import edu.lawrence.adventure.interfaces.dtos.LocationDTO;
import edu.lawrence.adventure.interfaces.dtos.PlayerDTO;
import edu.lawrence.adventure.security.AdventureUserDetails;
import edu.lawrence.adventure.security.JwtService;
import edu.lawrence.adventure.services.InventoryService;
import edu.lawrence.adventure.services.LocationService;
import edu.lawrence.adventure.services.PlayerService;
import edu.lawrence.adventure.services.UserService;

@RestController
@RequestMapping("/players")
@CrossOrigin(origins = "*")
public class PlayerController {
	
	private UserService us;
	private JwtService jwt;
	private PlayerService play;
	private LocationService loc;
	private InventoryService is;
    
    public PlayerController(UserService us, JwtService jwt, PlayerService play, LocationService loc, InventoryService is) {
        this.us = us;
        this.jwt = jwt;
        this.play = play;
        this.loc = loc;
        this.is = is;
    }
    
    @PostMapping("/move")
    public ResponseEntity<String> movePlayer(Authentication authentication, @RequestBody String direction) {
    	AdventureUserDetails details = (AdventureUserDetails) authentication.getPrincipal();
		UUID id = UUID.fromString(details.getUsername());
		Player player = play.playerFromUser(id);
		direction = direction.replace("\"", "");
		if(loc.isPossible(player, direction)) {
			Location newLoc = loc.getMoveLocation(player, direction);
			System.out.println(newLoc.getLocationName());
			play.move(newLoc, player);
			if(player.getAllPos()) {
				return ResponseEntity.ok().body("Congratulations, you have visited every area and beaten the game with " + is.coins(player) +" coins") ;
			}
			return ResponseEntity.ok().body("You have moved " + direction) ; 
		}
			
		return ResponseEntity.ok().body("Invalid command") ;
    }
    
    @GetMapping("/reset")
    public ResponseEntity<String> reset(Authentication authentication) {
    	AdventureUserDetails details = (AdventureUserDetails) authentication.getPrincipal();
		UUID id = UUID.fromString(details.getUsername());
		play.reset(id);
		return ResponseEntity.ok().body("Your Game has been Reset");
    }
    
    @GetMapping("/status")
    public ResponseEntity<PlayerDTO> checkStatus(Authentication authentication){
    	AdventureUserDetails details = (AdventureUserDetails) authentication.getPrincipal();
		UUID id = UUID.fromString(details.getUsername());
		PlayerDTO response = play.checkProgress(id);
		return ResponseEntity.ok().body(response);

    }
}

