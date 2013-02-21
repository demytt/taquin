package ui;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.html.HTMLEditorKit.Parser;

@SuppressWarnings("serial")
public class MenuTaquin extends JMenuBar implements ActionListener{

    static GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
    static Font[] fonts = e.getAllFonts();  

    
	JMenu menu; //The hierarchy: is JMenuBar contains JMenu contains JMenuItem
	TaquinUI taquinUI; //taquinUI in which the MenuTaquin is displayed
	
	//Constructeur du menu 
	public MenuTaquin(TaquinUI _taquinUI) {
		taquinUI = _taquinUI;
		menu = new JMenu("Options");
		
		JMenuItem createNew = new JMenuItem("Nouveau taquin");
		createNew.addActionListener(this);
		createNew.setActionCommand("createNew");
		menu.add(createNew);
		
		JMenuItem setSize = new JMenuItem("Choix de la taille du taquin");
		setSize.addActionListener(this);
		setSize.setActionCommand("setSize");
		menu.add(setSize);
		
		JMenuItem setFont = new JMenuItem("Choix de la police");
		setFont.addActionListener(this);
		setFont.setActionCommand("setFont");
		menu.add(setFont);
		
		add(menu);
	}

	//Define what to do when something is clicked in the menu
	public void actionPerformed(ActionEvent actionEvent) {
		String S = actionEvent.getActionCommand();
		if (S=="createNew") taquinUI.createNew();
		else if (S=="setSize")  setSize(0);
		else if (S=="setFont") setFont();
	}
	
	//Set the size of the taquin, numTimes indicates how many times the user inputs a wrong data format
	private void setSize(int numTimes) {
		try {
			double size = Double.valueOf(JOptionPane.showInputDialog(this, "Choix de la taille:"));
			if ( (size == Math.ceil(size)) && (size >= 1)) taquinUI.setSize((int) size);
			else throw new Exception();
		}
		
		catch(Exception e) {
			String S;
			if (numTimes==0) S = "Premier avertissement : format de donn�es non valides. " +
					"\nVeuillez entrer un entier sup�rieur ou �gal � 1";
			else if (numTimes==1) S = "Veuillez respecter les instructions donn�es";
			else if (numTimes==2) S = "Ceci est le dernier avertissement, "
					+"veuillez vous conformer aux instructions";
			else {
				S = "Tant pis pour vous, au revoir !";
				JOptionPane.showMessageDialog(this, S);
				System.exit(0);
			}
			JOptionPane.showMessageDialog(this, S);
			setSize(numTimes+1);
		}
		
	}
	
	private void setFont() {    
	    final JFrame choiceFrame = new JFrame("Choix de la police");
	    
	    BoxLayout layout = new BoxLayout( choiceFrame.getContentPane(), BoxLayout.X_AXIS);
	    choiceFrame.getContentPane().setLayout(layout);
	    
	    final Choice choiceList = new Choice();
	    for (Font f : fonts) choiceList.addItem(f.getName());
	    choiceFrame.add(choiceList);
	    
	    JButton ok = new JButton("OK");
	    choiceFrame.add(ok);
	    ok.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		taquinUI.setFontName(choiceList.getSelectedItem());
	    		choiceFrame.dispose();
	        }
	    });
	    
	    choiceFrame.setPreferredSize(new Dimension(300, 100));
	    choiceFrame.setLocation(getWidth()/2, getHeight()/2);
	    choiceFrame.pack();
	    choiceFrame.setVisible(true);
	    choiceFrame.setAlwaysOnTop(true);
	}
	
	
}
