package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Case extends JPanel{

	int value; //valeur de la case (0, 1, ..., size-1) 
	ImageIcon image; //image to be displayed
	Boolean isImage;
	String directory;
	
	String fontName;
	Color fontColor;
	
	//constructeur si un chemin pour les images est donn�
	public Case(int _value, String _directory) {
		value = _value;
		directory = _directory;
		image = new ImageIcon(directory+"/"+Integer.toString(value)+".jpg");
		isImage = true;
		setBackground(Color.WHITE);
	}
	
	//constructeur avec des images par d�faut et un choix de police
	public Case(int _value, String _fontName, Color _fontColor) {
		value = _value;		
		isImage = false;			
		fontName = _fontName; 
		fontColor = _fontColor;
		setBackground(Color.WHITE);
	}
		
	//change the value
	public void update(int _value) {
		value = _value;
		if (isImage) {
			if (value==0) image = new ImageIcon(); //"void" image = no image
			else image = new ImageIcon(directory+Integer.toString(value)+".png");
		}
		updateUI(); //tell the UI to re draw the cell
	}

	public void updateFont(String _fontName, Color _fontColor) {
		fontName = _fontName;
		fontColor = _fontColor;
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
			
			Color old = g.getColor();
			g.setColor(fontColor);
			g.setFont(new Font(fontName, Font.BOLD, sizeFont));
			if ((value>0)&&(value<10)) g.drawString(Integer.toString(value), 
					Math.min(sizeFont,w/3), Math.max(sizeFont,2*h/3));
			else if (value>=10) g.drawString(Integer.toString(value), 
					Math.min(sizeFont,w/4), Math.max(sizeFont,2*h/3));
			g.setColor(old);
		}
		
		//TO BE CHANGED -> draw the lines in the TaquinUI (less lines), or include the lines in the image?
		g.drawLine(0,0,0,h);
		g.drawLine(0,0,w,0);
		
	}

	public void updateImage(String directory2) {
		directory = directory2;
		if (value==0) image = new ImageIcon(); //"void" image = no image
		else image = new ImageIcon(directory+Integer.toString(value)+".png");
		
		isImage = true;
		updateUI();
	}
	
}
