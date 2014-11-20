package com.db.runners;

import com.db.database.Database;
import com.db.guest.Guest;
import com.db.tools.DateTimeCalendar;
import com.db.tools.Randomizer;

public class GuestRunner extends Runner{

	private Guest guest;
	private Database database;
	private DateTimeCalendar calendar;

	public GuestRunner(Guest guest, Database database, DateTimeCalendar calendar){
		super();
		this.guest = guest;
		this.database = database;
		this.calendar = calendar;
	}

	@Override
	public void act() {

		/* Sleep for a random time */
		try {Thread.sleep(Randomizer.randomInt(
				DateTimeCalendar.lengthOfMinute*5, 
				DateTimeCalendar.lengthOfMinute*30));} 
		catch (InterruptedException e) {e.printStackTrace();}

		if(guest.shouldLeave()){
			super.stop();
			String infoText = guest.getName() + " is leaving the park with a " 
			+ guest.getLeaveChance() + "% leave chance!";
			
			System.out.println(infoText);
			database.addInfoToDatabase(infoText, calendar);
			database.removeIDFromTable(guest.getId(), "guests");
			guest = null;
		}

		else{

			boolean couldAfford = guest.attemptPurchase(
					Randomizer.randomFloat(3.0f, 10f, 2));

			guest.incLeaveChance(Randomizer.randomFloat(.05f, .2f, 2));

			if(couldAfford){

				String infoText = guest.getName() + 
						" purchased something for 10 dollars.";

				System.out.println(infoText);
				database.addInfoToDatabase(infoText, calendar);
			}

			else{

				String infoText = guest.getName() + 
						" could not afford something for 10 dollars!";

				System.out.println(infoText);
				database.addInfoToDatabase(infoText, calendar);
			}
		}
	}
}
