package com.db.manager;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;
import com.db.comps.TestFrame;
import com.db.database.Database;
import com.db.runners.ThemeParkRunner;
import com.db.tools.DateTimeCalendar;

public class Driver {

	// The theme park
	private static ThemeParkRunner testPark;
	
	// The main frame
	private static TestFrame frame;
	
	// The time
	private static DateTimeCalendar calendar;

	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException, IOException {
		
		Database database = new Database();
		
		// True to reset all possible names in database (Takes a while)
		database.addNamesToDatabase(false);
		database.clearTables();

		// Create the database without updating the names tables
		calendar = new DateTimeCalendar(2014, 10, 18, 1, 0, frame);
		calendar.start();
		
		testPark = new ThemeParkRunner(calendar);
		new Thread(testPark).start();
		
		/* Start the new frame on a thread */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TestFrame(calendar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// Remove everything when finished
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            //database.clearTables();
	        }
	    }, "Shutdown-thread"));
	}
}