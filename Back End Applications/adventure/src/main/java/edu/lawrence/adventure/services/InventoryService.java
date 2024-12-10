package edu.lawrence.adventure.services;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.lawrence.adventure.entities.*;
import edu.lawrence.adventure.repositories.*;
import edu.lawrence.adventure.services.WrongUserException;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository invRepo;
	@Autowired
	UserRepository userRepo;
	
	public void update(UUID playerid, String item) throws WrongUserException {
		Optional<User> maybeUser = userRepo.findById(playerid);
		if(!maybeUser.isPresent())
			throw new WrongUserException();
		
		User user = maybeUser.get();
		Player player = user.getPlayer();
		Inventory inventory = invRepo.findByPlayer(player);
		switch(item) {
		case "key1":
			inventory.setKey1(true);
			break;
		case "key2":
			inventory.setKey2(true);
			break;
		case "key3":
			inventory.setKey3(true);
			break;
		case "chest1":
			if(inventory.isKey1()) {
				inventory.setChest1(true);
				int coins = inventory.getCoins();
				inventory.setCoins(coins+1);
			}
			break;
		case "chest2": 
			if(inventory.isKey2()) {
				inventory.setChest2(true);
				int coins = inventory.getCoins();
				inventory.setCoins(coins+2);
			}
			
			break;
		case "chest3": 
			if(inventory.isKey3()) {
				inventory.setChest3(true);
				int coins = inventory.getCoins();
				inventory.setCoins(coins+3);
			}
			break;
		}
		invRepo.save(inventory);
	}
	
	public Inventory checkInventory(Player player) {
		Inventory inventory = invRepo.findByPlayer(player);
		return inventory;
	}

	public int coins(Player player) {
		Inventory inventory = invRepo.findByPlayer(player);
		return inventory.getCoins();
	}
}
