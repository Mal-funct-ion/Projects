package edu.lawrence.adventure.entities;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import edu.lawrence.adventure.interfaces.dtos.PlayerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="players")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "VARCHAR(45)")
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private UUID playerid;
	private int position;
	/*
	 * Booleans 0 through 8 represent a 3x3 grid that players can travel to. Players may only move between
	 * adjacent grids
	 * [0, 1, 2]
	 * [3, 4, 5]
	 * [6, 7, 8]
	 */
	private boolean zero;
	private boolean one;
	private boolean two;
	private boolean three;
	private boolean four;
	private boolean five;
	private boolean six;
	private boolean seven;
	private boolean eight;
	//A boolean that confirms whether or not all 9 locations have been visited
	private boolean all_pos;
	
	public Player() {}
	
	public Player(PlayerDTO core) {
		position = core.getPosition();
		zero = core.getZero();
		one = core.getOne();
		two = core.getTwo();
		three = core.getThree();
		four = core.getFour();
		five = core.getFive();
		six = core.getSix();
		seven = core.getSeven();
		eight = core.getEight();
		all_pos = core.getAllPos();
	}
	
	public UUID getPlayerid() {
		return playerid;
	}
	
	public void setPlayerid(UUID playerid) {
		this.playerid = playerid;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public boolean getZero() {
		return zero;
	}
	
	public void setZero(boolean zero) {
		this.zero = zero;
	}
	
	public boolean getOne() {
		return one;
	}
	
	public void setOne(boolean one) {
		this.one = one;
	}
	
	public boolean getTwo() {
		return two;
	}
	
	public void setTwo(boolean two) {
		this.two = two;
	}
	
	public boolean getThree() {
		return three;
	}
	
	public void setThree(boolean three) {
		this.three = three;
	}
	
	public boolean getFour() {
		return four;
	}
	
	public void setFour(boolean four) {
		this.four = four;
	}
	
	public boolean getFive() {
		return five;
	}
	
	public void setFive(boolean five) {
		this.five = five;
	}
	
	public boolean getSix() {
		return six;
	}
	
	public void setSix(boolean six) {
		this.six = six;
	}
	
	public boolean getSeven() {
		return seven;
	}
	
	public void setSeven(boolean seven) {
		this.seven = seven;
	}
	
	public boolean getEight() {
		return eight;
	}
	
	public void setEight(boolean eight) {
		this.eight = eight;
	}
	
	public boolean getAllPos() {
		return all_pos;
	}
	
	public void setAll(boolean allPos) {
		this.all_pos = allPos;
	}
	
}
