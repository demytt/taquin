package ui;

import grapheTaquin.Taquin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TaquinUI extends JFrame implements KeyListener, WindowListener{

	Case[][] cases; //matrix of cases
	int size; //size of the taquin, number of cases = size*size
	int rowZero; //row number of the zero
	int colZero; //column number of the zero
	
	int numTrue; //number of cells that are in their good positions (to win)
	
	static String defaultFontName = "ACaslonPro-Italic";
	static Color defaultFontColor = Color.black;
	static boolean isImage = false;
	
	int difficulty;
	int coefDifficulty;
	
	String imagePath;
	
	JPanel mainPanel =  new JPanel(); //mainPanel in which everything is displayed

	PathFrame pathFrame;
	
	public TaquinUI(int _size) {
		super("Jeu du taquin"); //name of the window
		size = _size;
		coefDifficulty = 4;
		createNew();
		setContentPane(mainPanel);
		setJMenuBar(new MenuTaquin(this));	//create a MenuTaquin as menu bar	
		setPreferredSize(new Dimension(500, 500));
		pack(); //compute and draw everything
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // To stop the JFrame when it's closed
		setLocationRelativeTo(null); //center on the screen
		addKeyListener(this); 
	}
	
	//create the random cells
	private void createRandomCases() {
		difficulty = (int) (coefDifficulty*Math.pow(size, coefDifficulty/2));
		cases = new Case[size][size];
	
		int[][] endValues = new int[size][size];
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				endValues[i][j]=i*size+j;
			}
		}
		Taquin configFin = new Taquin(endValues);
		configFin.shake(difficulty);
		
		numTrue = 0;
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				int number = configFin.config[i][j];
				if ((number!=0)&&(number == i*size + j)) numTrue++;
				if (isImage) cases[i][j] = new Case(number);
				else cases[i][j] = new Case(number, defaultFontName, defaultFontColor);
				if (number == 0) {
					rowZero=i;
					colZero=j;
				}
			}
		}
		
		//Fully random taquin
//		LinkedList<Integer> bij = new LinkedList<Integer>(); 
//		for (int i=0; i<size*size; i++) bij.add(i);
//		java.util.Collections.shuffle(bij); //permute aléatoirement l'ensemble bij = 1:size*size
//		for (int i=0; i<size; i++) {
//			for (int j=0; j<size; j++) {
//				int number = bij.poll();
//				if ((number!=0)&&(number == i*size + j)) numTrue++;
//				if (isImage) cases[i][j] = new Case(number);
//				else cases[i][j] = new Case(number, defaultFontName, defaultFontColor);
//				if (number == 0) {
//					rowZero=i;
//					colZero=j;
//				}
//			}
//		}
		
		addWindowListener(this);
	}
	
	//add the cells to the main panel
	private void addCases() {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				mainPanel.add(cases[i][j]);
			}
		}
	}
	
	
	//create a whole new taquin
	public void createNew() {
		if (pathFrame!=null) pathFrame.dispose();
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(size, size));
		createRandomCases();
		addCases();
		pack();
	}

	//set the size of the taquin and create a random new one
	public void setSize(int n) {
		size = n;
		if (isImage) SplitPicture.splitImage(size, imagePath);
		createNew();
	}
	
	public void setDifficulty(String S) {
		if (S=="Facile") coefDifficulty=2;
		if (S=="Moyen") coefDifficulty=4;
		if (S=="Difficile") coefDifficulty=6;
		createNew();
	}
	
	public void setFontName(String fontName, Color fontColor) {
		isImage = false;
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				defaultFontName = fontName;
				defaultFontColor = fontColor;
				cases[i][j].updateFont(fontName, fontColor);
			}
		}
	}
	
	void setImage(String _imagePath){
		isImage = true;
		imagePath = _imagePath;
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				cases[i][j].updateImage();
			}
		}
	}
	
	private Taquin toTaquin() {
		int[][] config = new int[size][size];
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				config[i][j] = cases[i][j].getValue();
			}
		}
		return new Taquin(config);
	}
	
	public void solve() {
		Taquin t = toTaquin();
		String chemin = t.solve();
		if (pathFrame!=null) pathFrame.dispose();
		pathFrame = new PathFrame(chemin, this);
	}
	

	//different conventions than in taquin
	public void bouge(char c) {
		int[] move = new int[2];
		if(c=='D') move[1]=1;
		else if(c=='G') move[1]=-1;
		else if(c=='B') move[0]=1;
		else if(c=='H') move[0]=-1;
		moveHoleTo(rowZero+move[0], colZero+move[1]);
	}
	
	
	//swap the values of two cells and after it checks if the taquin is finished
	private void moveHoleTo(int x, int y) {
		int val = cases[x][y].getValue();
		if (x*size + y == val) numTrue--;
		else if (rowZero*size + colZero == val) numTrue++;
		cases[rowZero][colZero].update(val);
		cases[x][y].update(0);
		rowZero = x;
		colZero = y;
		if (numTrue == size*size-1) new WinFrame();
	
	}
	
	
	public void keyPressed(KeyEvent keyEvent) {
		int keyNumber = keyEvent.getKeyCode(); 
		if (pathFrame!=null) pathFrame.dispose();
		//Move the non-empty case (real life)
		if ( (keyNumber==KeyEvent.VK_RIGHT)&&(colZero>0) ) moveHoleTo(rowZero, colZero-1);	
		else if ( (keyNumber==KeyEvent.VK_LEFT)&&(colZero<(size-1)) ) moveHoleTo(rowZero, colZero+1);
		else if ( (keyNumber==KeyEvent.VK_DOWN)&&(rowZero>0) ) moveHoleTo(rowZero-1, colZero);
		else if ( (keyNumber==KeyEvent.VK_UP)&&(rowZero<(size-1)) ) moveHoleTo(rowZero+1, colZero);
	}

	
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	
	public void windowClosing(WindowEvent e) {	
		File path = new File("Images/");
		if( path.exists()) {
			File[] files = path.listFiles();
			for( int i = 0 ; i < files.length ; i++ ){
				files[i].delete();
			}
		}
		path.delete();
	}

	public void windowActivated(WindowEvent e) {  }
	public void windowClosed(WindowEvent e) {  }
	public void windowDeactivated(WindowEvent e) {	}
	public void windowDeiconified(WindowEvent e) {	}
	public void windowIconified(WindowEvent e) {  }
	public void windowOpened(WindowEvent e) {	}


}
