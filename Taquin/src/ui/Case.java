package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Case extends JPanel{

	int value; //valeur de la case (0, 1, ..., size-1) 
	ImageIcon image; //image to be displayed
	Boolean isImage;
	String directory;
	
	String fontName;
	
	//constructeur si un chemin pour les images est donn�
	public Case(int _value, String _directory, boolean t) {
		value = _value;
		directory = _directory;
		image = new ImageIcon(directory+"/"+Integer.toString(value)+".jpg");
		isImage = true;
		setBackground(Color.WHITE);
	}
	
	//constructeur avec des images par d�faut et un choix de police
	public Case(int _value, String _fontName) {
		value = _value;		
		isImage = false;			
		fontName = _fontName; 
		setBackground(Color.WHITE);
	}
		
	//change the value
	public void update(int _value) {
		value = _value;
		if (isImage) {
			if (value==0) image = new ImageIcon(); //"void" image = no image
			else image = new ImageIcon(directory+"/"+Integer.toString(value)+".jpg");
		}
		updateUI(); //tell the UI to re draw the cell
	}

	public void updateFont(String _fontName) {
		fontName = _fontName;
		updateUI();
	}
	
	//r�d�finition de la m�thode paint
	public void paint(Graphics g) {
		super.paint(g); //paint the JPanel as an usual JPanel
		int w = getWidth();
		int h = getHeight();
		
		if (isImage) g.drawImage(image.getImage(), 0, 0, w, h, image.getImageObserver());
		
		else {
			int sizeFont = (int) (0.5*Math.min(w, h));
			g.setFont(new Font(fontName, Font.BOLD, sizeFont));
			if (value!=0) g.drawString(Integer.toString(value), 
					Math.max(sizeFont,w/2), Math.max(sizeFont,2*h/3));
		}
		
		//TO BE CHANGED -> draw the lines in the TaquinUI (less lines), or include the lines in the image?
		g.drawLine(0,0,0,h);
		g.drawLine(0,0,w,0);
		
	}
	
}
