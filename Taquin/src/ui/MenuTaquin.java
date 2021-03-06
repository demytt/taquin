package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class MenuTaquin extends JMenuBar implements ActionListener{

    static GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
    static Font[] fonts = e.getAllFonts();  
    
    static JList<String> fontList = new JList<String>();
    static DefaultListModel<String> currentFontList = new DefaultListModel<String>();
    static JScrollPane scrollFont = new JScrollPane(fontList);
	
    static JList<Color> colorList = new JList<Color>();
    static DefaultListModel<Color> currentColorList = new DefaultListModel<Color>();
    static JScrollPane scrollColor = new JScrollPane(colorList);
	
    
	JMenu menu; //The hierarchy: is JMenuBar contains JMenu contains JMenuItem
	TaquinUI taquinUI; //taquinUI in which the MenuTaquin is displayed
	
	//Constructor 
	public MenuTaquin(TaquinUI _taquinUI) {

	    for (Font f : fonts) currentFontList.addElement(f.getName());
	    fontList.setModel(currentFontList);
	    fontList.setVisibleRowCount(10);
	    
	    Field[] fields = java.awt.Color.class.getDeclaredFields();
	    for (Field f : fields) {
	    	try {
	    		if (! java.lang.reflect.Modifier.isPublic(f.getModifiers())) break;
	    		if (! java.lang.reflect.Modifier.isStatic(f.getModifiers())) break;
	    		if (! java.lang.reflect.Modifier.isFinal(f.getModifiers())) break;
	    		Object o = f.get(null);
	    		if (o instanceof java.awt.Color) { 
	    			String name = f.getName();
	    			if (name == name.toLowerCase()&&name!="white") currentColorList.addElement((Color) o);
	    		}
	    	} 
	    	catch (Exception e) {e.printStackTrace();}  
	    } 
	    colorList.setFixedCellWidth(65);
	    colorList.setModel(currentColorList);
	    colorList.setVisibleRowCount(10);
	    
	    ListCellRenderer<Color> renderer = new ColorListRenderer();
	    colorList.setCellRenderer(renderer);
	    
		taquinUI = _taquinUI;
		menu = new JMenu("Options");
		
	    BoxLayout layout = new BoxLayout( menu, BoxLayout.Y_AXIS);
	    menu.setLayout(layout);
		
		JMenuItem setSize = new JMenuItem("Choix de la taille du taquin");
		setSize.addActionListener(this);
		setSize.setActionCommand("setSize");
		menu.add(setSize);
		
		JMenuItem setFont = new JMenuItem("Choix de la police");
		setFont.addActionListener(this);
		setFont.setActionCommand("setFont");
		menu.add(setFont);
		
		JMenuItem setImage= new JMenuItem("Choix de l'image");
		setImage.addActionListener(this);
		setImage.setActionCommand("setImage");
		menu.add(setImage);
		
		JMenu setDifficulty= new JMenu("Choix de la difficult�");
		JMenuItem easy = new JMenuItem("Facile");
		easy.addActionListener(this);
		easy.setActionCommand("Facile");
		setDifficulty.add(easy);
		
		JMenuItem medium = new JMenuItem("Moyen");
		medium.addActionListener(this);
		medium.setActionCommand("Moyen");
		setDifficulty.add(medium);
		
		JMenuItem hard = new JMenuItem("Difficile");
		hard.addActionListener(this);
		hard.setActionCommand("Difficile");
		setDifficulty.add(hard);
		
		menu.add(setDifficulty);
		add(menu);
		
		JMenuItem createNew = new JMenuItem("Nouveau taquin");
		createNew.addActionListener(this);
		createNew.setActionCommand("createNew");
		createNew.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_DOWN_MASK));
		createNew.setMaximumSize( createNew.getPreferredSize() );
		add(createNew);
		
		JMenuItem solve= new JMenuItem("R�soudre le taquin");
		solve.addActionListener(this);
		solve.setActionCommand("solve");
		solve.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
		solve.setMaximumSize( solve.getPreferredSize() );
		add(solve);
	}

	//Define what to do when something is clicked in the menu
	public void actionPerformed(ActionEvent actionEvent) {
		String S = actionEvent.getActionCommand();
		if (S=="createNew") taquinUI.createNew();
		else if (S=="setSize")  setSize(0);
		else if (S=="setFont") setFont();
		else if (S=="setImage") setImage();
		else if (S=="solve") taquinUI.solve();
		else taquinUI.setDifficulty(S);
	}
	
	//Set the size of the taquin, numTimes indicates how many times the user inputs a wrong data format
	private void setSize(int numTimes) {
		try {
			String answer = JOptionPane.showInputDialog(this, "Choix de la taille:");
			if (answer==null) return;
			double size = Double.valueOf(answer);
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
	    choiceFrame.setResizable(false);
	    
	    BoxLayout layout = new BoxLayout( choiceFrame.getContentPane(), BoxLayout.X_AXIS);
	    choiceFrame.getContentPane().setLayout(layout);
	    
	    choiceFrame.add(scrollFont);
	    choiceFrame.add(scrollColor);
	    
	    JButton ok = new JButton("OK");
	    choiceFrame.add(ok);
	    ok.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		taquinUI.setFontName(fontList.getSelectedValue(), colorList.getSelectedValue());
	    		choiceFrame.dispose();
	        }
	    });

	    choiceFrame.setLocation(getWidth()/2, getHeight()/2);
	    choiceFrame.pack();
	    choiceFrame.setVisible(true);
	    choiceFrame.setAlwaysOnTop(true);
	    choiceFrame.setLocationRelativeTo(null);
	}
	
	private void setImage(){
		String S = SplitPicture.splitImage(taquinUI.size);
		if(S!=null) taquinUI.setImage(S);
	}	
	
}
