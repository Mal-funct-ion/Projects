package edu.lawrence.adventure.interfaces.dtos;

import java.util.UUID;

import edu.lawrence.adventure.entities.Player;

public class PlayerDTO {
	private int position;
	private boolean zero;
	private boolean one;
	private boolean two;
	private boolean three;
	private boolean four;
	private boolean five;
	private boolean six;
	private boolean seven;
	private boolean eight;
	private boolean all_pos;
	
	public PlayerDTO() {}
	
	public PlayerDTO(Player core) {
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
	
	public void setAll(boolean all) {
		this.all_pos = all;
	}
}
