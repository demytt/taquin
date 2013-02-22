package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TaquinUI extends JFrame implements KeyListener{

	Case[][] cases; //matrix of cases
	int size; //size of the taquin, number of cases = size*size
	int rowZero; //row number of the zero
	int colZero; //column number of the zero
	
	int numTrue; //number of cells that are in their good positions (to win)
	
	static String defaultFontName = "SERIF";
	static Color defaultFontColor = Color.black;
	
	JPanel mainPanel =  new JPanel(); //mainPanel in which everything is displayed

	public TaquinUI(int _size) {
		super("Jeu du taquin"); //name of the window
		size = _size;
		createNew();

		setContentPane(mainPanel);
		setJMenuBar(new MenuTaquin(this));	//create a MenuTaquin as menu bar	
		setPreferredSize(new Dimension(400, 400));
		pack(); //compute and draw everything
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sinon la JFrame ne s'arr�te pas quand elle est ferm�e
		addKeyListener(this); 
	}
	
	//create the random cells
	private void createRandomCases() {
		cases = new Case[size][size];
		numTrue = 0;
		LinkedList<Integer> bij = new LinkedList<Integer>(); 
		for (int i=0; i<size*size; i++) bij.add(i);
		java.util.Collections.shuffle(bij); //permute al�atoirement l'ensemble bij = 1:size*size
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				int number = bij.poll();
				cases[i][j] = new Case(number, defaultFontName, defaultFontColor);
				if (number == 0) {
					rowZero=i;
					colZero=j;
				}
			}
		}
	}
	
	//add the cells to the main panel
	private void addCases() {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				mainPanel.add(cases[i][j]);
			}
		}
	}
	

//	public void paint(Graphics g) {
//		super.paint(g);
//		int height = getHeight();
//		int width = getWidth();
//		int hStep = getHeight()/size;
//		int wStep = getWidth()/size;
//		for (int i=1; i<(size); i++) {
//			g.drawLine(i*wStep, 0, i*wStep, height );
//			g.drawLine(0,i*hStep, width, i*hStep);
//		}
//			
//}
	
	//create a whole new taquin
	public void createNew() {
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(size, size));
		createRandomCases();
		addCases();
		pack();
	}

	//set the size of the taquin and create a random new one
	public void setSize(int n) {
		size = n;
		createNew();
	}
	
	public void setFontName(String fontName, Color fontColor) {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				cases[i][j].updateFont(fontName, fontColor);
			}
		}
	}
	
	//swap the values of two cells and after it checks if the taquin is finished
	private void swapCase(int x1, int y1, int x2, int y2) {
		int temp = cases[x1][y1].value;
		int temp2 = cases[x2][y2].value;
		cases[x1][y1].update(cases[x2][y2].value);
		cases[x2][y2].update(temp);
		
		if (temp == x1*size + y1) numTrue--;
		else if (temp == x2*size + y2) numTrue++;

		if (temp2 == x2*size + y2) numTrue--;
		else if (temp2 == x1*size + y1) numTrue++;
	
		if (numTrue == size*size) {
			JFrame win = new JFrame("F�licitations"); //New windows to say that the user won
			JLabel label = new JLabel("Bravo, vous avez gagn� !");
			label.setPreferredSize(new Dimension(200,75));
			label.setLocation(getWidth()/2, getHeight()/2);

			win.add(label);
			win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			win.setPreferredSize(new Dimension(500, 200));
			win.setLocation(getWidth()/2, getHeight()/2);
			win.pack();
			win.setVisible(true);
			win.setAlwaysOnTop(true);
		}
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		int keyNumber = keyEvent.getKeyCode(); 
		
		// Move the hole
//		if ( (keyNumber==KeyEvent.VK_LEFT)&&(colZero>0) ) {
//			swapCase(rowZero, colZero, rowZero, colZero-1);
//			colZero--;
//		}
//		
//		else if ( (keyNumber==KeyEvent.VK_RIGHT)&&(colZero<(size-1)) ) {
//			swapCase(rowZero, colZero, rowZero, colZero+1);
//			colZero++;
//		}
//		
//		else if ( (keyNumber==KeyEvent.VK_UP)&&(rowZero>0) ) {
//			swapCase(rowZero, colZero, rowZero-1, colZero);
//			rowZero--;
//		}
//		
//		else if ( (keyNumber==KeyEvent.VK_DOWN)&&(rowZero<(size-1)) ) {
//			swapCase(rowZero, colZero, rowZero+1, colZero);
//			rowZero++;
//		}		
		
		//Move the non-empty case (real life)
		if ( (keyNumber==KeyEvent.VK_RIGHT)&&(colZero>0) ) {
			swapCase(rowZero, colZero, rowZero, colZero-1);
			colZero--;
		}
		
		else if ( (keyNumber==KeyEvent.VK_LEFT)&&(colZero<(size-1)) ) {
			swapCase(rowZero, colZero, rowZero, colZero+1);
			colZero++;
		}
		
		else if ( (keyNumber==KeyEvent.VK_DOWN)&&(rowZero>0) ) {
			swapCase(rowZero, colZero, rowZero-1, colZero);
			rowZero--;
		}
		
		else if ( (keyNumber==KeyEvent.VK_UP)&&(rowZero<(size-1)) ) {
			swapCase(rowZero, colZero, rowZero+1, colZero);
			rowZero++;
		}
	}

	
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	
	void setImage(String directory){
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				cases[i][j].updateImage(directory);
			}
		}
	}
}
