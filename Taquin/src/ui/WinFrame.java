package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WinFrame extends JFrame {

	public WinFrame() {
		super("Félicitations");
		JLabel label = new JLabel("Bravo, vous avez gagné !");
		label.setPreferredSize(new Dimension(200,75));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(label.getFont().deriveFont(30f));
		getContentPane().setBackground(Color.white);
		add(label);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(500, 200));
		pack();
		setVisible(true);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("Fireworks.gif"));
		    g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		} 
		catch (IOException e) {System.out.println(e);}
	}
	
}
