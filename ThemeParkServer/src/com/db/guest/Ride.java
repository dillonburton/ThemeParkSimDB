package com.db.guest;

import com.db.tools.DateTime;

public class Ride{
	
	private int id;
	private String name;
	private DateTime buildDate;
	private int excitement;
	private float price;

	public Ride(int id, String name, DateTime buildDate, int excitement, float price) {
		this.id = id;
		this.name = name;
		this.buildDate = buildDate;
		this.excitement = excitement;
		this.price = price;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getBuildDate(){
		return buildDate.getDateTime();
	}
	
	public int getExcitement(){
		return excitement;
	}
	
	public float getPrice(){
		return price;
	}

}
