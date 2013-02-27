package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WinFrame extends JFrame {

	public WinFrame() {
		super("Félicitations");
		setResizable(true);
		setLayout(new GridLayout(0, 3));
		
		Icon icon = new ImageIcon("Fireworks.gif");
		add(new JLabel(icon));
		
		JLabel label = new JLabel("Gagné !");
		label.setPreferredSize(new Dimension(300,125));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(label.getFont().deriveFont(30f));
		label.setForeground(Color.WHITE);
		getContentPane().setBackground(Color.black);
		add(label);
			
		add(new JLabel(icon));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(500, 225));
		pack();
		setVisible(true);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
	}
	
}
