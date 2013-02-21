package ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Case extends JPanel{

	int value; //valeur de la case (0, 1, ..., size-1) 
	ImageIcon image; //image to be displayed
	
	//constructeur
	public Case(int _value) {
		value = _value;
		image = new ImageIcon(Integer.toString(value)+".jpg");
		setBackground(Color.WHITE);
	}
	
	//change the value
	public void update(int _value) {
		value = _value;
		if (value==0) image = new ImageIcon(); //"void" image = no image
		else image = new ImageIcon(Integer.toString(value)+".jpg");
		updateUI(); //tell the UI to re draw the cell
	}

	//rédéfinition de la méthode paint
	public void paint(Graphics g) {
		super.paint(g); //paint the JPanel as an usual JPanel
		int w = getWidth();
		int h = getHeight();
		g.drawImage(image.getImage(), 0, 0, w, h, image.getImageObserver());
		
		//TO BE CHANGED -> draw the lines in the TaquinUI (less lines), or include the lines in the image?
		g.drawLine(0,0,0,h);
		g.drawLine(0,0,w,0);
	}
	
}
