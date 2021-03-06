package com.db.comps;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.db.database.Database;
import com.db.guest.Ride;
import com.db.tools.DateTime;
import com.db.tools.DateTimeCalendar;

public class NewRideWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField name_field;
	private JLabel lblRidePrice;
	private JLabel eLabel;
	private JTextField price_field;
	private Database database;
	private DateTimeCalendar calendar;
	private int currentID = 0;
	private JTextField rideExc;


	/**
	 * Create the frame.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public NewRideWindow(Database database, DateTimeCalendar calendar) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.calendar = calendar;
		this.database = database;

		setTitle("New Ride");

		setBounds(100, 100, 231, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		name_field = new JTextField();

		name_field.setBorder(null);
		name_field.setColumns(10);

		JLabel ride_label = new JLabel("Name");

		lblRidePrice = new JLabel("Price");
		eLabel = new JLabel("Excitement (0-100%)");

		JPanel button_panel = new JPanel();
		button_panel.setBackground(new Color(176, 196, 222));

		TextButton addNewRide;

		addNewRide = new TextButton("createNewRide", null, null, this, "Create ride");

		addNewRide.setPreferredSize(new Dimension(150,50));
		button_panel.add(addNewRide);

		price_field = new JTextField();
		price_field.setColumns(10);
		price_field.setBorder(null);

		/* Start group layout */
		/* Group layouts are auto generated with eclipse window builder */
		
		rideExc = new JTextField();
		rideExc.setColumns(10);
		rideExc.setBorder(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(name_field, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
						.addComponent(ride_label, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRidePrice, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
						.addComponent(price_field, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(rideExc, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(eLabel, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(ride_label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(name_field, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRidePrice)
					.addGap(2)
					.addComponent(price_field, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(eLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rideExc, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(button_panel, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		/* End group layout */

		this.setLocationRelativeTo(null);
	}

	public void createNewRide(Component comp){

		float price = 0;
		try{
			String name = name_field.getText();
			int e = Integer.parseInt(rideExc.getText());
			price = Float.parseFloat(price_field.getText());
			
			DateTime buildDate = calendar.getDateAndTime();

			// Add ride to database
			Ride ride = new Ride(currentID, name, buildDate, e, price);
			currentID++;
			database.addRideToDatabase(ride, calendar);
			
			this.setVisible(false);
			this.dispose();
		}catch(Exception E){
			System.out.println("Check input");
		}

	}

	public void createNewFood(Component comp){
		float price = 0;
		try{
			String name = name_field.getText();
			price = Float.parseFloat(price_field.getText());
			this.setVisible(false);
			this.dispose();
		}catch(Exception E){
			System.out.println("Check input");
		}
	}
}
