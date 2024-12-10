package edu.lawrence.adventure.services;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.lawrence.adventure.entities.Inventory;
import edu.lawrence.adventure.entities.Location;
import edu.lawrence.adventure.entities.Player;
import edu.lawrence.adventure.entities.User;
import edu.lawrence.adventure.interfaces.dtos.PlayerDTO;
import edu.lawrence.adventure.repositories.InventoryRepository;
import edu.lawrence.adventure.repositories.PlayerRepository;
import edu.lawrence.adventure.repositories.UserRepository;

@Service
public class PlayerService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PlayerRepository play;
	
	@Autowired
	InventoryRepository inv;
	
	public Player playerFromUser(UUID userid) {
		Optional<User> maybeUser = userRepository.findById(userid);
		if(!maybeUser.isPresent()) 
			return null;
		User user = maybeUser.get();
		Optional<Player> maybePlayer = play.findById(user.getPlayer().getPlayerid());
		if(!maybePlayer.isPresent())
			return null;
		Player player = maybePlayer.get();
		return player;
	}
	
	public void move(Location location, Player player) {
		switch (location.getLocationName()) {
		case"Zero":
			player.setPosition(0);
			player.setZero(true);
			break;
		case"One":
			player.setPosition(1);
			player.setOne(true);
			break;
		case"Two":
			player.setPosition(2);
			player.setTwo(true);
			break;
		case"Three":
			player.setPosition(3);
			player.setThree(true);
			break;
		case"Four":
			player.setPosition(4);
			player.setFour(true);
			break;
		case"Five":
			player.setPosition(5);
			player.setFive(true);
			break;
		case"Six":
			player.setPosition(6);
			player.setSix(true);
			break;
		case"Seven":
			player.setPosition(7);
			player.setSeven(true);
			break;
		case"Eight":
			player.setPosition(8);
			player.setEight(true);
			break;
		}
		checkAll(player);
		play.save(player);
	}
	
	private void checkAll(Player player) {
		if (player.getAllPos()) {
			return;
		} else if(player.getZero() && player.getTwo() && player.getThree() && player.getFour() &&player.getFive() && player.getSix() &&player.getSeven() &&player.getEight()) {
			player.setAll(true);
		}
	}
	
	public PlayerDTO checkProgress(UUID userid) {
		Player player = playerFromUser(userid);
		PlayerDTO status = new PlayerDTO(player);
		return status;
	}
	
	public void reset(UUID userid) {
		Player player = playerFromUser(userid);
		player.setPosition(4);
		player.setZero(false);
		player.setOne(false);
		player.setTwo(false);
		player.setThree(false);
		player.setFour(true);
		player.setFive(false);
		player.setSix(false);
		player.setSeven(false);
		player.setEight(false);
		player.setAll(false);
		play.save(player);
		
		Inventory inventory = inv.findByPlayer(player);
		inventory.setChest1(false);
		inventory.setChest2(false);
		inventory.setChest3(false);
		inventory.setKey1(false);
		inventory.setKey2(false);
		inventory.setKey3(false);
		inventory.setCoins(0);
		inv.save(inventory);
	}
}
