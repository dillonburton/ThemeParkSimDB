package com.db.comps;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageFile extends JLabel{
	private static final long serialVersionUID = 1L;

	private ImageIcon image;

	public ImageFile(String imageName){
		image = new ImageIcon(ImageFile.class.getResource("/com/db/res/" + imageName));
		this.setMaximumSize(new Dimension(5000, 5000));
		this.setMinimumSize(new Dimension(1, 1));
		this.setPreferredSize(new Dimension(1,1));
		this.setIcon(image);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage (image.getImage(), 0, 0, getWidth(), getHeight(), null);
	}

}
