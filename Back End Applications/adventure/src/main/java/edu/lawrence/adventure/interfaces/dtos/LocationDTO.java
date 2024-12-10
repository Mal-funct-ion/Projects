package edu.lawrence.adventure.interfaces.dtos;

import java.util.UUID;

import edu.lawrence.adventure.entities.Location;


public class LocationDTO {
	private String locationName;
	private String item;
	private String north;
	private String east;
	private String south;
	private String west;
	
	public LocationDTO() {}
	public LocationDTO(Location core) {
		locationName = core.getLocationName();
		item = core.getItem();
		north = core.getNorth().toString();
		east = core.getEast().toString();
		south = core.getSouth().toString();
		west = core.getWest().toString();
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getNorth() {
		return north;
	}
	public void setNorth(String north) {
		this.north = north;
	}
	public String getEast() {
		return east;
	}
	public void setEast(String east) {
		this.east = east;
	}
	public String getSouth() {
		return south;
	}
	public void setSouth(String south) {
		this.south = south;
	}
	public String getWest() {
		return west;
	}
	public void setWest(String west) {
		this.west = west;
	}
	
	
	
}
