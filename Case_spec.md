# Case SPEC

Specification de la classe Case

Case extends JPanel

### Champs de classe

* int value; valeur de la case (0, 1, ..., size-1) 

* ImageIcon image; image de la case
	

	
### Method
* public Case(int _value) : constructeur 
	
* public void update(int _value) : change la valeur et l'image de la case

* public void paint(Graphics g) : rédéfinition de la méthode paint pour dessiner la case

======