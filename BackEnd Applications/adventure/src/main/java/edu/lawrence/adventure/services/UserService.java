package edu.lawrence.adventure.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.lawrence.adventure.entities.Inventory;
import edu.lawrence.adventure.entities.Player;
import edu.lawrence.adventure.entities.User;
import edu.lawrence.adventure.interfaces.dtos.UserDTO;
import edu.lawrence.adventure.services.InventoryService;
import edu.lawrence.adventure.services.PlayerService;
import edu.lawrence.adventure.repositories.InventoryRepository;
import edu.lawrence.adventure.repositories.PlayerRepository;
import edu.lawrence.adventure.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired 
    PasswordService passwordService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PlayerRepository play;
	
	@Autowired
	InventoryRepository invRepo;
	
	public String save(UserDTO user) {
		List<User> existing = userRepository.findByUsername(user.getUsername());
		if(existing.size() > 0)
			return "Duplicate";
		
		Player newPlayer = new Player();
		newPlayer.setPosition(4);
		newPlayer.setZero(false);
		newPlayer.setOne(false);
		newPlayer.setTwo(false);
		newPlayer.setThree(false);
		newPlayer.setFour(true);
		newPlayer.setFive(false);
		newPlayer.setSix(false);
		newPlayer.setSeven(false);
		newPlayer.setEight(false);
		newPlayer.setAll(false);
		play.save(newPlayer);
        
        Inventory newInvent = new Inventory();
		newInvent.setPlayer(newPlayer);
		newInvent.setChest1(false);
		newInvent.setChest2(false);
		newInvent.setChest3(false);
		newInvent.setKey1(false);
		newInvent.setKey2(false);
		newInvent.setKey3(false);
		newInvent.setCoins(0);
		invRepo.save(newInvent);
		
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		String hash = passwordService.hashPassword(user.getPassword());
	    newUser.setPassword(hash);
		newUser.setPlayer(newPlayer);
		userRepository.save(newUser);
		return newUser.getUserid().toString();
	}
	
	public User findByNameAndPassword(String name,String password) {
		List<User> existing = userRepository.findByUsername(name);
		if(existing.size() != 1)
			return null;
		User u = existing.get(0);
		if(passwordService.verifyHash(password, u.getPassword())) {
        	u.setPassword("Undisclosed");
        } else {
        	u = null;
        }
        return u;	
	}

}
