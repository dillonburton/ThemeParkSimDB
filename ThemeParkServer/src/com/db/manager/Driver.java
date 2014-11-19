package com.db.manager;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;

import com.db.comps.TestFrame;
import com.db.database.Database;
import com.db.tools.DateTimeCalendar;

public class Driver {

	private static Database database;
	private static ThemePark testPark;
	private static TestFrame frame;
	private static DateTimeCalendar calendar;

	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException, IOException {

		// Create the database without updating the names tables
		calendar = new DateTimeCalendar(1999, 10, 21, 7, 22, frame);
		calendar.start();
		
		database = new Database(false, calendar);
		
		testPark = new ThemePark(database);
		testPark.start();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TestFrame(database, calendar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            //database.clearTables();
	        }
	    }, "Shutdown-thread"));
	}
}