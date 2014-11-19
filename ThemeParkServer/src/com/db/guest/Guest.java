package com.db.guest;

import com.db.tools.DateTime;

public class Guest {
	
	private int id;
	private String name;
	private char sex;
	private DateTime arrivalDate;
	private float leaveChance;
	private float money;
	
	public Guest(int id, String name, char sex, DateTime arrivalDate, float leaveChance, float money){
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
}