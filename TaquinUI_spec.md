# TaquinUI SPEC


### TaquinUI extends JFrame implements KeyListener{

### Champs de classe

* Case[][] cases : matrix des cases
* int size : taille du taquin taquin, nombre de cases = size*size
* int rowZero : num�ro de la ligne du z�ro 
* int colZero : num�ro de la colonne du z�ro	
* int numTrue : nombre de cases qui sont � leurs bonnes places
* JPanel mainPanel =  new JPanel() : panel principal dans lequel tout est affich�

### Method

* public TaquinUI(int _size) : constructeur 	
	
* private void createRandomCases() : cr�ation al�atoires des cases
	
* private void addCases() : ajout des cases au mainPanel
	
* public void createNew() : cr�ation d'un nouveau taquin

* public void setSize(int n) : r�glage de la taille du taquin � n et cr�ation d'un nouveau taquin
	
* private void swapCase(int x1, int y1, int x2, int y2) : �change deux cases et v�rifie si le taquin est fini (=gagn�)
	
* public void keyPressed(KeyEvent keyEvent) : d�finit quoi faire lors d'une touche press�e 

* public void keyReleased(KeyEvent arg0) : rien � faire

* public void keyTyped(KeyEvent arg0) : rien � faire

======