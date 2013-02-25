package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Case extends JPanel{

	private int value; //valeur de la case (0, 1, ..., size-1) 

	private Boolean isImage;
	
	private String fontName;
	private Color fontColor;
	
	//constructeur si un chemin pour les images est donné
	public Case(int _value) {
		value = _value;
		isImage = true;
	}
	
	//constructeur avec des images par défaut et un choix de police
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
		updateUI(); //tell the UI to re draw the cell
	}

	public void updateFont(String _fontName, Color _fontColor) {
		isImage = false;
		fontName = _fontName;
		fontColor = _fontColor;
		setBackground(Color.WHITE);
		updateUI();
	}
	
	public void updateImage() {
		isImage = true;
		updateUI();
	}
	
	//rédéfinition de la méthode paint
	public void paint(Graphics g) {
		super.paint(g); //paint the JPanel as an usual JPanel
		int w = getWidth();
		int h = getHeight();
		
		if (isImage) g.drawImage(SplitPicture.bufferArray[value], 0, 0, w, h, null);
		
		else {
			int sizeFont = (int) (0.5*Math.min(w, h));
			Color old = g.getColor();
			g.setColor(fontColor);
			g.setFont(new Font(fontName, Font.BOLD, sizeFont));
			if ((value>0)&&(value<10)) g.drawString(Integer.toString(value), 
					Math.max(sizeFont,w/3), Math.max(sizeFont,2*h/3));
			else if (value>=10) g.drawString(Integer.toString(value), 
					Math.min(w/3, Math.max(sizeFont,w/4)), Math.max(sizeFont,2*h/3));
			g.setColor(old);
		}
		
		g.drawLine(0,0,0,h);
		g.drawLine(0,0,w,0);
		
	}

	public int getValue() {
		return value;
	}


	
}
