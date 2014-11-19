package com.db.manager;

import java.util.LinkedList;
import java.util.List;

import com.db.database.Database;
import com.db.guest.Guest;
import com.db.guest.GuestHandler;
import com.db.tools.Randomizer;

public class ThemePark extends Thread{
	
	private Database database;
	private List<Guest> guestList;
	
	public ThemePark(Database database){
		this.database = database;
		guestList = new LinkedList<Guest>();
	}
	
	public void run(){
		
		int index = 0;
		
		while(true){
			index = Randomizer.randomInt(1, 100);
			
			/* Sleep for a random time */
			try {Thread.sleep(Randomizer.randomInt(1000, 5000));} 
			catch (InterruptedException e) {e.printStackTrace();}
			
			Guest guest = database.generateRandomGuest();
			System.out.println("Created guest " + guest.getName());
			GuestHandler gh = new GuestHandler(guest, database);
			gh.start();
			//guestList.add(guest);
		}
	}

}
