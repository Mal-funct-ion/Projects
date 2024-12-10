package edu.lawrence.adventure.entities;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import edu.lawrence.adventure.interfaces.dtos.InventoryDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "VARCHAR(45)")
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private UUID inventoryid;
	@OneToOne
	@JoinColumn(name = "player")
	private Player player;
	private boolean key1;
	private boolean key2;
	private boolean key3;
	private boolean chest1;
	private boolean chest2;
	private boolean chest3;
	private int coins;
	
	public Inventory () {}
	
	public Inventory(InventoryDTO core) {
		key1 = core.isKey1();
		key2 = core.isKey2();
		key3 = core.isKey3();
		chest1 = core.isChest1();
		chest2 = core.isChest2();
		chest3 = core.isChest3();
		coins = core.getCoins();
	}

	public UUID getInventoryid() {
		return inventoryid;
	}

	public void setInventoryid(UUID inventoryid) {
		this.inventoryid = inventoryid;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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
