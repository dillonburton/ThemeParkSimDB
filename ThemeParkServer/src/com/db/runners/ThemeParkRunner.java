package com.db.runners;

import java.util.LinkedList;
import java.util.List;
import com.db.database.Database;
import com.db.guest.Guest;
import com.db.tools.DateTimeCalendar;
import com.db.tools.Randomizer;

public class ThemeParkRunner extends Runner{

	private Database database;
	private Database guestDatabase;
	private List<Guest> guestList;
	private DateTimeCalendar calendar;

	public ThemeParkRunner(DateTimeCalendar calendar){
		super();
		this.calendar = calendar;
		database = new Database();
		guestDatabase = new Database();
		guestList = new LinkedList<Guest>();
	}

	@Override
	public void act() {

		/* Sleep for a random time */
		try {Thread.sleep(Randomizer.randomInt((int)(DateTimeCalendar.lengthOfMinute*.5f), 
				DateTimeCalendar.lengthOfMinute*2));} 
		catch (InterruptedException e) {e.printStackTrace();}

		if(!DateTimeCalendar.parkClosed){
			System.out.println("PARK IS NOT CLOSED, IT'S " + calendar.getDateAndTime().getDateTime());
			Guest guest = database.generateRandomGuest(calendar);
			database.addInfoToDatabase(guest.getName() + " has entered the park.", calendar);
			
			System.out.println("Created guest " + guest.getName());
			GuestRunner gh = new GuestRunner(guest, guestDatabase, calendar);
			new Thread(gh).start();
		}
		
	}
}