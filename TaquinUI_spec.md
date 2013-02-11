# TaquinUI SPEC


### TaquinUI extends JFrame implements KeyListener{

### Champs de classe

* Case[][] cases : matrix des cases
* int size : taille du taquin taquin, nombre de cases = size*size
* int rowZero : numéro de la ligne du zéro 
* int colZero : numéro de la colonne du zéro	
* int numTrue : nombre de cases qui sont à leurs bonnes places
* JPanel mainPanel =  new JPanel() : panel principal dans lequel tout est affiché

### Method

* public TaquinUI(int _size) : constructeur 	
	
* private void createRandomCases() : création aléatoires des cases
	
* private void addCases() : ajout des cases au mainPanel
	
* public void createNew() : création d'un nouveau taquin

* public void setSize(int n) : réglage de la taille du taquin à n et création d'un nouveau taquin
	
* private void swapCase(int x1, int y1, int x2, int y2) : échange deux cases et vérifie si le taquin est fini (=gagné)
	
* public void keyPressed(KeyEvent keyEvent) : définit quoi faire lors d'une touche pressée 

* public void keyReleased(KeyEvent arg0) : rien à faire

* public void keyTyped(KeyEvent arg0) : rien à faire

======