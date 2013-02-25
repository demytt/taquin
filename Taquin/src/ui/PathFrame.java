package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PathFrame extends JFrame implements ActionListener{

	String chemin;
	TaquinUI taquinUI;
	JLabel label;
	
	public PathFrame(String _chemin, TaquinUI _taquinUI){
		super("Optimal way"); //New windows to show the optimal way
		
		chemin = _chemin;
		taquinUI= _taquinUI;
		
	    BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.X_AXIS);
	    getContentPane().setLayout(layout);
		
	    JButton next = new JButton("Next Move");
		next.addActionListener(this);
		add(next);

	
		label = new JLabel("    "+chemin);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
				
		setPreferredSize(new Dimension(100+chemin.length()*15,90));
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
			label.setText("    "+chemin);
			taquinUI.bouge(c);
		}
		else {
			char c = chemin.charAt(0);
			taquinUI.bouge(c);
			dispose();
		}
		
	}
	

	
}
