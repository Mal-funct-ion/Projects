package edu.lawrence.adventure.interfaces;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.lawrence.adventure.services.*;
import edu.lawrence.adventure.services.WrongUserException;
import edu.lawrence.adventure.security.AdventureUserDetails;
import edu.lawrence.adventure.security.JwtService;
import edu.lawrence.adventure.interfaces.dtos.*;
import edu.lawrence.adventure.entities.*;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {
	private InventoryService is;
	private LocationService ls;
	private PlayerService ps;
	private JwtService jwtService;
	
	public InventoryController (InventoryService is, JwtService jwtService, LocationService ls,PlayerService ps) {
		this.is = is;
		this.ps = ps;
		this.ls = ls;
        this.jwtService = jwtService;
	}
	
	@PostMapping("/pickUp")
	public ResponseEntity<String> pickUpItem(Authentication authentication){
		AdventureUserDetails details = (AdventureUserDetails) authentication.getPrincipal();
		UUID id = UUID.fromString(details.getUsername());
		Player player = ps.playerFromUser(id);
		String position = ls.grabPos(player);
		Location location = ls.getLocByName(position);
		String item = ls.checkForItem(location);
		if(item != null) {
			try {
				is.update(id, item);
			}catch(WrongUserException ex) {
	    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no item here!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("You picked up an item!");
	}
	
	@GetMapping("/look")
	public ResponseEntity<String> lookForItem(Authentication authentication){
		AdventureUserDetails details = (AdventureUserDetails) authentication.getPrincipal();
		UUID id = UUID.fromString(details.getUsername());
		Player player = ps.playerFromUser(id);
		String position = ls.grabPos(player);
		Location location = ls.getLocByName(position);
		String item = ls.checkForItem(location);
		if(item == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no item here!");
		}
		return ResponseEntity.ok().body(item);
	}
	
	@GetMapping("/check")
	public ResponseEntity<InventoryDTO> checkInventory(Authentication authentication){
		AdventureUserDetails details = (AdventureUserDetails) authentication.getPrincipal();
		UUID id = UUID.fromString(details.getUsername());
		Player player = ps.playerFromUser(id);
		Inventory inventory =  is.checkInventory(player);
		InventoryDTO invent = new InventoryDTO(inventory);
		return ResponseEntity.ok().body(invent);
	}
}
