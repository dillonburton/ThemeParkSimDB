package com.db.comps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.JPanel;
import javax.swing.UIManager;

public abstract class ButtonMaster extends JPanel{
	private static final long serialVersionUID = 1L;

	// The method called from the buttons containing class
	public Method actionMethod, hoverMethod, exitMethod;

	// The class containing the action method
	public Object actionMethodHolder;

	// The current button
	private ButtonMaster thisButton;

	public ButtonMaster(String actionMethodName, String hoverMethodName, 
			String exitMethodName, final Object actionMethodHolder) 
			throws NoSuchMethodException, SecurityException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException{

		thisButton = this;
		this.actionMethodHolder = actionMethodHolder;

		/* Set up the action method */
		actionMethod = actionMethodHolder.getClass().
				getMethod(actionMethodName, Component.class);

		/* Set up the hover method */
		if(hoverMethodName != null){
			hoverMethod = actionMethodHolder.getClass().
					getMethod(hoverMethodName, Component.class);
		}

		/* Set up the exit method */
		if(exitMethodName != null){
			exitMethod = actionMethodHolder.getClass().
					getMethod(exitMethodName, Component.class);
		}

		this.setBorder(null);

		this.addMouseListener(new MouseAdapter() {

			/* If the mouse enters the button */
			@Override
			public void mouseEntered(MouseEvent me) {
				mEntered(me);
			}

			/* If the mouse exits the button */
			@Override
			public void mouseExited(MouseEvent me){
				mExited(me);
			}

			/* If the button is clicked */
			@Override
			public void mousePressed(MouseEvent me){
				mClicked(me);
			}
		});
	}
	
	private void mEntered(MouseEvent me){
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if(hoverMethod != null){
			try {
				
				// Call the hover method in the containing class
				hoverMethod.invoke(actionMethodHolder, thisButton);
				
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void mExited(MouseEvent me){
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if(exitMethod != null){
			try {
				
				// Call the exit method in the containing class
				exitMethod.invoke(actionMethodHolder, thisButton);
				
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void mClicked(MouseEvent me){
		try {
			
			// Call the clicked method in the containing class
			actionMethod.invoke(actionMethodHolder, thisButton);
			
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the font size of the button
	 * @param size is the font size
	 */
	public void setFontSize(int size){
		this.setFont(new Font("Aller Sans", Font.PLAIN, size));
	}

	/**
	 * Sets the default button UI
	 * @param color is the default button color
	 */
	public static void setDefaultPressedColor(Color color){
		UIManager.put("Button.select", color);
	}
}
