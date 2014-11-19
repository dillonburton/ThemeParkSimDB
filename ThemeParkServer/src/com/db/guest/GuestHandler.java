package com.db.guest;

import com.db.database.Database;
import com.db.tools.DateTimeCalendar;
import com.db.tools.Randomizer;

public class GuestHandler extends Thread{
	
	private Guest guest;
	private Database database;
	
	public GuestHandler(Guest guest, Database database){
		this.guest = guest;
		this.database = database;
	}
	
	public void run(){
		
		while(true){
			
			/* Sleep for a random time */
			try {Thread.sleep(Randomizer.randomInt(
					DateTimeCalendar.lengthOfMinute*5, 
					DateTimeCalendar.lengthOfMinute*30));} 
			catch (InterruptedException e) {e.printStackTrace();}
			
			database.addInfoToDatabase(guest.getName() + " purchased something");
		}
	}
	
	
}
