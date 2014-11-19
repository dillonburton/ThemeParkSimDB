package com.db.comps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;

public class ImageButton extends JButton{
	private static final long serialVersionUID = 1L;

	// The method called from the buttons containing class
	private Method actionMethod, hoverMethod, exitMethod;

	// The class containing the action method
	public Object actionMethodHolder;

	private ImageButton thisButton;

	private ImageIcon image;
	private String text;
	
	private int width, height;
	private boolean outline;

	@Override
	public void paintComponent(Graphics g) {

		// redVersion is now a red version of original
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


		//g.setColor(Color.red);

		// paint original with composite
		//((Graphics2D) g).setComposite(AlphaComposite.DstIn);

		if(outline){
			g2d.setColor(Color.decode("#41515C"));
			g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 5, 5);
			
			g2d.setColor(Color.decode("#1E262B"));
			g2d.setStroke(new BasicStroke(2));
			g2d.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 5, 5);
		}
		

		g2d.drawImage (image.getImage(), this.getWidth()/2 - width/2, this.getHeight()/2 - height/2, width, height, null);

		g2d.setColor(Color.white);
		g2d.setFont(new Font("Gill Sans", Font.BOLD, 12));
		g2d.drawString(text, 7, 28);
		//g2d.drawString("Example class", 12, 64);
	}
	

	public ImageButton(String actionMethodName, String hoverMethodName, 
			String exitMethodName, final Object actionMethodHolder, String imageFileName, int width, int height, String text, boolean outline) 
					throws NoSuchMethodException, SecurityException, IllegalAccessException, 
					IllegalArgumentException, InvocationTargetException{
		this.outline = outline;
		this.text = text;
		image = new ImageIcon(ImageFile.class.getResource("/com/db/res/" + imageFileName));
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));

		thisButton = this;
		this.actionMethodHolder = actionMethodHolder;
		//ImageFile image = new ImageFile(imageFileName);
		this.setBorder(null);
		//this.add(image);

		/* Set up the action method */
		actionMethod = actionMethodHolder.getClass().getMethod(actionMethodName, Component.class);

		/* Set up the hover method */
		if(hoverMethodName != null){
			hoverMethod = actionMethodHolder.getClass().getMethod(hoverMethodName, Component.class);
		}

		/* Set up the exit method */
		if(exitMethodName != null){
			exitMethod = actionMethodHolder.getClass().getMethod(exitMethodName, Component.class);
		}

		/* Set up the button */
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		// Gets rid of a little left dot in the corner
		this.setFocusPainted(false);

		/* Call the action method if the button is pressed */
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					actionMethod.invoke(actionMethodHolder, thisButton);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		});

		/* Change the color of the button depending on the mouse position */
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent me) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				if(hoverMethod != null){
					try {
						hoverMethod.invoke(actionMethodHolder, thisButton);
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent me){
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if(exitMethod != null){
					try {
						exitMethod.invoke(actionMethodHolder, thisButton);
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public void setSize(int size){
		this.setPreferredSize(new Dimension(size, size));
	}

	public void setFontSize(int size){
		this.setFont(new Font("Aller Sans", Font.PLAIN, size));
	}

	public static void setDefaultPressedColor(Color color){
		// Set the pressed color for all buttons
		UIManager.put("Button.select", color);
	}
	
	public void setImage(String imageName){
		image = new ImageIcon(ImageFile.class.getResource("/com/db/res/" + imageName));
		this.repaint();
	}
	
	public void setString(String s){
		text = s;
	}

	public void drawStringCenter(String s, int x, int y, int compSize, Graphics2D g2d){
		g2d.setFont(new Font("Gill Sans", Font.BOLD, 16));
		int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
		int off = (compSize - stringLen)/2;
		g2d.drawString(s, off + x, y);
	}
}
