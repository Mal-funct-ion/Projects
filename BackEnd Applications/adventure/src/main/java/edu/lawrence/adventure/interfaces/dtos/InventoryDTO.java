package edu.lawrence.adventure.interfaces.dtos;

import edu.lawrence.adventure.entities.Inventory;

public class InventoryDTO {
	private boolean key1;
	private boolean key2;
	private boolean key3;
	private boolean chest1;
	private boolean chest2;
	private boolean chest3;
	private int coins;
	

	public InventoryDTO () {}
	
	public InventoryDTO(Inventory core) {
		key1 = core.isKey1();
		key2 = core.isKey2();
		key3 = core.isKey3();
		chest1 = core.isChest1();
		chest2 = core.isChest2();
		chest3 = core.isChest3();
		coins = core.getCoins();
	}

	public boolean isKey1() {
		return key1;
	}

	public void setKey1(boolean key1) {
		this.key1 = key1;
	}

	public boolean isKey2() {
		return key2;
	}

	public void setKey2(boolean key2) {
		this.key2 = key2;
	}

	public boolean isKey3() {
		return key3;
	}

	public void setKey3(boolean key3) {
		this.key3 = key3;
	}

	public boolean isChest1() {
		return chest1;
	}

	public void setChest1(boolean chest1) {
		this.chest1 = chest1;
	}

	public boolean isChest2() {
		return chest2;
	}

	public void setChest2(boolean chest2) {
		this.chest2 = chest2;
	}

	public boolean isChest3() {
		return chest3;
	}

	public void setChest3(boolean chest3) {
		this.chest3 = chest3;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	
	
}

