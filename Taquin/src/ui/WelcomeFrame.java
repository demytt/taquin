package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WelcomeFrame extends JFrame{
	
	int height = 250;
	int width = 500;
	
	public WelcomeFrame(){
		super("Welcome"); 
		setResizable(false);
		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS);
		getContentPane().setLayout(boxLayout);
		
	    JPanel panel = new JPanel(new GridLayout(2,0));
	    
		JLabel label1 = new JLabel("ENSAE ParisTech 3eme année");
		JLabel label2 = new JLabel("Jeu de taquin");
		label2.setFont(label2.getFont().deriveFont(30f).deriveFont(Font.BOLD));
		JLabel label3 = new JLabel("Arnaud de Myttenaere et Sébastien Deprez");
		
		label1.setHorizontalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label3.setHorizontalAlignment(JLabel.CENTER);
		
		panel.add(label1);
		panel.add(label2);
		panel.setBackground(Color.white);
		add(panel);
		
	    JButton playButton = new JButton("Jouer");
	    playButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TaquinUI(4);
				setVisible(false);
			}
		});

	    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    add(playButton);
	    
	    add( new JLabel(" "));
	    add( new JLabel(" "));
	    add( new JLabel(" "));
	    add( new JLabel(" "));
	    add( new JLabel(" "));
	    
	    setPreferredSize(new Dimension(width, height));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		pack();
		setVisible(true);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Arnaud de Myttenaere et Sébastien Deprez", (int) (0.26*width), 9*height/10);
	}
	
}
