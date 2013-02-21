package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MenuTaquin extends JMenuBar implements ActionListener{

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
		
		
		add(menu);
	}

	//Define what to do when something is clicked in the menu
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand()=="createNew") taquinUI.createNew();
		else if (arg0.getActionCommand()=="setSize")  setSize(0);
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
			if (numTimes==0) S = "Premier avertissement : format de données non valides. " +
					"\nVeuillez entrer un entier supérieur ou égal à 1";
			else if (numTimes==1) S = "Veuillez respecter les instructions données";
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
}

