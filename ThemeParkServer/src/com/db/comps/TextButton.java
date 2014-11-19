package com.db.comps;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.lang.reflect.InvocationTargetException;

public class TextButton extends ButtonMaster{
	private static final long serialVersionUID = 1L;
	
	private String text;

	public TextButton(String actionMethodName, String hoverMethodName,
			String exitMethodName, Object actionMethodHolder, String text)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		super(actionMethodName, hoverMethodName, exitMethodName, actionMethodHolder);
		this.text = text;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//g2d.drawString(text, 20, y);
		g2d.setColor(Color.decode("#41515C"));
		g2d.fillRoundRect(0, 0, this.getWidth()-1, this.getHeight()-1, 5, 5);
		g2d.setColor(Color.white);
		drawStringCenter(text, this.getWidth(), this.getHeight(), g2d);
	}
	
	public void drawStringCenter(String s, int compWidth, int compHeight, Graphics2D g2d){
		g2d.setFont(new Font("Gill Sans", Font.BOLD, 16));
		int strWidth = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
		int strHeight = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getHeight();
		g2d.drawString(s, compWidth/2 - strWidth/2, compHeight/2 + strHeight/4);
	}

}
