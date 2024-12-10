package edu.lawrence.adventure.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.lawrence.adventure.entities.Location;
import edu.lawrence.adventure.entities.Player;
import edu.lawrence.adventure.repositories.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	LocationRepository locationRepository;

	public Boolean isPossible(Player player, String direction) {
		String location = grabPos(player);
		Optional<Location> maybeLoc = locationRepository.findByLocationName(location);
		Location loc = maybeLoc.get();
		if ( (loc.getNorth()!=null && direction.equals("North")) || (loc.getSouth()!=null && direction.equals("South"))||(loc.getEast()!=null && direction.equals("East"))||(loc.getWest()!=null && direction.equals("West"))) {
			return true;
		}else{
			return false;
		}
		
	}
	public Location getMoveLocation(Player player, String direction) {
		String location = grabPos(player);
		System.out.println(location);
		Optional<Location> maybeLoc = locationRepository.findByLocationName(location);
		Location loc = maybeLoc.get();
		String position = null;
		switch(direction) {
		case "North":
			position = loc.getNorth();
			break;
		case "East":
			position = loc.getEast();
			break;
		case "West":
			position = loc.getWest();
			break;
		case "South":
			position = loc.getSouth();
			break;
		}
		System.out.println(position);
		Location newLoc = locationRepository.findByLocationid(position);
		System.out.println(newLoc);
		return newLoc;
	}
	public String grabPos(Player player) {
		int postion = player.getPosition();
		String location = null;
		switch (postion) {
		case 0:
			location = "Zero";
			break;
		case 1:
			location = "One";
			break;
		case 2:
			location = "Two";
			break;
		case 3:
			location = "Three";
			break;
		case 4:
			location = "Four";
			break;
		case 5:
			location = "Five";
			break;
		case 6:
			location = "Six";
			break;
		case 7:
			location = "Seven";
			break;
		case 8:
			location = "Eight";
			break;
		}
		return location;
	}
	
	public String checkForItem(Location location){
		String item = location.getItem();
		if(item == null) {
			return null;
		}
		return item;
	}
	public Location getLocByName(String LocName) {
		Optional<Location> maybeLocation = locationRepository.findByLocationName(LocName);
		Location loc = maybeLocation.get();
		return loc;
		
	}
}
