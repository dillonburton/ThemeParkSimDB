package com.db.guest;

import com.db.tools.DateTime;
import com.db.tools.DateTimeCalendar;
import com.db.tools.Randomizer;

public class Guest {

	private int id;
	private String name;
	private char sex;
	private DateTime arrivalDate;
	
	// Percent chance that the guest will leave after a random amount of time
	// Defined in the guests GuestRunner class
	private float leaveChance;
	
	// Amount of money the guest has left to spend
	private float money;

	public Guest(int id, String name, char sex, DateTime arrivalDate, 
			float leaveChance, float money){
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.arrivalDate = arrivalDate;
		this.leaveChance = leaveChance;
		this.money = money;
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public char getSex(){
		return sex;
	}

	public String getArrivalDate(){
		return arrivalDate.getDateTime();
	}

	public float getLeaveChance(){
		return leaveChance;
	}

	public float getMoney(){
		return money;
	}
	
	/**
	 * Determines if the guest should leave or not based on the
	 * leave percentage
	 * @return if the guest should leave or not
	 */
	public boolean shouldLeave(){
		float leaveRand = Randomizer.randomFloat(0, 100, 2);
		if(leaveRand <= (leaveChance + DateTimeCalendar.extraLeaveChance)){
			return true;
		}
		return false;
	}

	/**
	 * Increase the leave chance for the guest
	 * @param addLeaveChance is the amount of leave chance to add
	 */
	public void incLeaveChance(float addLeaveChance){
		
		if(leaveChance + addLeaveChance >= 100){
			leaveChance = 100;
			return;
		}

		leaveChance += addLeaveChance;
	}

	/**
	 * attemptPurchase has the guest attempt to purchase an
	 * item.
	 * @param itemCost is how much the item costs
	 * @return if the guest had enough money to purchase the
	 * item
	 */
	public boolean attemptPurchase(float itemCost){

		if(money - itemCost <= 0){
			leaveChance += 10;
			return false;
		}

		money -= itemCost;
		return true;
	}
}
