package ui;

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

	Case[][] cases; //matrice des cases
	int size; //taille du taquin, nombre de cases = size*size
	int rowZero; //num�ro de la ligne du z�ro
	int colZero; //num�ro de la colonne du z�ro
	
	int numTrue; //nombre de cases dans la bonne position
	
	JPanel mainPanel =  new JPanel(); //mainPanel dans lequel tout est affich�

	
	public TaquinUI(int _size) {
		super("Jeu du taquin"); //nom de la fen�tre
		size = _size;
		createNew();

		setContentPane(mainPanel);
		setPreferredSize(new Dimension(400, 400));
		pack(); //compute and draw everything
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sinon la JFrame ne s'arr�te quand elle est ferm�e
		addKeyListener(this); 
	}
	
	//cr�e les cases al�atoirement
	private void createRandomCases() {

		cases = new Case[size][size];
		numTrue = 0;
		LinkedList<Integer> bij = new LinkedList<Integer>(); 
		for (int i=0; i<size*size; i++) bij.add(i);
		
		int[][] config = new int[size][size];
		java.util.Collections.shuffle(bij); //permute al�atoirement l'ensemble bij = 1:size*size
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				int number = bij.poll();
				config[i][j] = number;
				cases[i][j] = new Case(number);
				if (number == 0) {
					rowZero=i;
					colZero=j;
				}
			}
		}
	}
	
	//Ajoute les cases au panneau principal mainPanel
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
	
	//cr�e un nouveau taquin
	public void createNew() {
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(size, size));
		createRandomCases();
		addCases();
		pack();
	}

	//r�gle la taille du taquin et cr�e un nouveau taquin
	public void setSize(int n) {
		size = n;
		createNew();
	}
	
	//�change deux cases et v�rifie si le taquin est termin�
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
			JFrame win = new JFrame("F�licitations"); 
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
	
	
}