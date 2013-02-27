package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
	
	public WelcomeFrame(){
		super("Welcome"); //New windows to show the optimal way

	    //getContentPane().setLayout(new FlowLayout());
		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(boxLayout);
		
	    JPanel panel = new JPanel(new GridLayout(3,0));
	    
		JLabel label1 = new JLabel("ENSAE ParisTech 3eme année");
		JLabel label2 = new JLabel("Projet de Programmation");
		JLabel label3 = new JLabel("Arnaud de Myttenaere et Sébastien Deprez");
		
		label1.setHorizontalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label3.setHorizontalAlignment(JLabel.CENTER);
		
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.setBackground(Color.white);
		//label1.setHorizontalAlignment(SwingConstants.CENTER);
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
	    
	    JLabel label4 = new JLabel("   ");
	    add(label4);
		
		setPreferredSize(new Dimension(400,200));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		pack();
		setVisible(true);
		setAlwaysOnTop(true);
		setLocation(500, 200);
	}

}
