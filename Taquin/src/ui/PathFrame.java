package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PathFrame extends JFrame implements ActionListener{

	String chemin;
	TaquinUI taquinUI;
	IconArrow label;
	
	public PathFrame(String _chemin, TaquinUI _taquinUI){
		super("Optimal way"); //New windows to show the optimal way
		
		chemin = _chemin;
		taquinUI= _taquinUI;
		
	    BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.X_AXIS);
	    getContentPane().setLayout(layout);
		
	    JButton next = new JButton("Next Move");
		next.addActionListener(this);
		add(next);
		label = new IconArrow(chemin);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
				
		setPreferredSize(new Dimension(100+chemin.length()*20,90));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		pack();
		setVisible(true);
		setAlwaysOnTop(true);
		setLocation(500, 200);
	}

	public void actionPerformed(ActionEvent e) {
		if (chemin.length()>1) {
			char c = chemin.charAt(0);
			chemin = chemin.substring(1);
			label.update(chemin);
			
			taquinUI.bouge(c);
		}
		else {
			char c = chemin.charAt(0);
			taquinUI.bouge(c);
			dispose();
		}
		
	}
	
}

@SuppressWarnings("serial")
class IconArrow extends JLabel {
	String chemin;
	
	IconArrow(String _chemin) {
		chemin = _chemin ;
		if (chemin == null) chemin = "";
		String empty = "     ";
		for(int i = 0; i<chemin.length();i++){
			empty += "         ";
		}
		setText(empty);
	}
	
	public void update(String _chemin) {
		chemin = _chemin;
		updateUI();
	}	

	public void paint(Graphics g) {
		super.paint(g);
		char[] cheminArray = chemin.toCharArray();
		
		for (int i = 0; i<cheminArray.length; i++) {
			char c = cheminArray[i];
			int x = 17*i + 10;
			if(c == 'B'){// up arrow
				g.setColor(Color.BLUE);
				g.drawLine(x, 5, x + 5, 0); 
				g.drawLine(x + 5, 0, x + 10, 5); 
				g.drawLine(x + 5, 0, x + 5, 10);
			}
			else if(c == 'H'){// down arrow 
				g.setColor(Color.GREEN);
				g.drawLine(x, 5, x + 5, 10);
				g.drawLine(x + 5, 10, x + 10, 5); 
				g.drawLine(x + 5, 0, x + 5, 10);
			}
			else if(c == 'G'){// left arrow 
				g.setColor(Color.RED);
				g.drawLine(x + 5, 0, x + 10, 5);
				g.drawLine(x + 10, 5, x + 5, 10); 
				g.drawLine(x, 5, x + 10, 5);
			}	
			else if(c == 'D'){// right arrow
				g.setColor(Color.BLACK);
				g.drawLine(x + 5, 0, x, 5);
				g.drawLine(x, 5, x + 5, 10); 
				g.drawLine(x, 5, x + 10, 5);
			}
		}
	}
}
