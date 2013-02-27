package grapheTaquin;

import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Taquin {

	public int[][] config;
	public int[] trou = new int[2];
	public int dist;
	public int dheur;
	public String chemin;
	public int conflit;
	
	int size;
	
	public Taquin (int[][] _config) {
		config = _config;
		size = config.length;
		dist = 0;
		dheur = 0;
		chemin = "";
		conflit = 0;
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				if (_config[i][j]==0) {
					trou[0] = i;
					trou[1] = j;
					break;
				}
			}
		}
	}
	
	// Constructeur a partir de la representation en ligne
	public Taquin (String S){
		StringTokenizer st = new StringTokenizer(S);
		size = (int) Math.sqrt(st.countTokens());
		int[][] _config=new int[size][size];
		while (st.hasMoreTokens()) {
			for(int i=0; i<size; i++){
				for(int j=0; j<size; j++){
					String s = st.nextToken();
					_config[i][j]=Integer.parseInt(s);
				}
			}
		}
		Taquin T0 = new Taquin(_config);
		config = T0.config;
		trou = T0.trou;
		dist = 0;
		dheur = 0;
		chemin = "";
	}

	
	// Permet de deplacer le "trou"
	// Valable uniquement pour move='H','B','G' ou 'D'
	public void bouge(char move) throws Exception {
		int a = trou[0];
		int b = trou[1];
		int[] tab = mouvement(move);
		int i = tab[0];
		int j = tab[1];
		if ((a+i<size)&(b+j<size)&(a+i>=0)&(b+j>=0)) {
			config[a][b] = config[a+i][b+j];
			config[a+i][b+j] = 0;
			trou[0] = trou[0] + i;
			trou[1] = trou[1] + j;
			chemin = chemin+move;
		}
		else {
			throw new RuntimeException("Impossible de deplacer le trou");
		}
	}

	// A partir du caractere du mouvement, renvoie les coordonnees du deplacement
	public int[] mouvement(char c){
		int[] move = new int[2];
		if(c=='D') move[1]=1;
		else if(c=='G') move[1]=-1;
		else if(c=='B') move[0]=1;
		else if(c=='H') move[0]=-1;
		return move;
	}
	
	// Retourne si deux mouvements sont opposés
	public boolean opposite(char c1, char c2){
		int[] move1 = mouvement(c1);
		int[] move2 = mouvement(c2);
		if ( (move1[0]+move2[0]==0)&&(move1[1]+move2[1]==0) ) return true;
		return false;
	}
	
	// Calcule la distance de Manhattan entre deux configurations
	public int distance(Taquin T) {
		int d = 0;
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				int a = config[i][j];
				if(a!=T.config[i][j] && a!=0) {
					int[] P = T.position(a);
					d = d + Math.abs(P[0]-i) + Math.abs(P[1]-j);
				}
			}
		}
		return d;
	}
	
	// Renvoie les aretes correspondant aux 4 (ou moins) mouvements possibles
	// sauf un mouvement qui ferait demi-tour sur le chemin (ex : D-G, H-B...)
	public Collection<Arete> aretes() {
		Taquin T;
		Collection<Arete> C = new LinkedList<Arete>();
		char[] choix = {'D','G','H','B'};
		for (int i=0; i<4; i++) {
			try {
				T = this.copy();
				char move = choix[i];
				if (chemin=="") {
					T.bouge(move);
					Arete A = new Arete(this, T, move);
					C.add(A);
				}
				else {
					char a = chemin.charAt(chemin.length()-1);
					if (!opposite(a, move)) {
						T.bouge(move);
						Arete A = new Arete(this, T, move);
						C.add(A);
					}
				}
			}
			catch (Exception e){
			}
		}
		return C;
	}
	
	// Teste l'egalite de deux configurations
	public boolean equals(Object O) {
		if (!(O instanceof Taquin)) {
			return false;
		}
		boolean B = true;
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				if (((Taquin) O).config[i][j]!=config[i][j]) {
					B = false;
				}
			}
		}
		return B;
	}
	
	
	// Renvoie un autre taquin duplique 
	public Taquin copy() {
		int[][] cf = new int[size][size];
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				cf[i][j]=config[i][j];
			}
		}
		Taquin T = new Taquin(cf);
		T.dist = dist;
		T.dheur = dheur;
		T.chemin = chemin;
		return T;
	}
	
	// Donne la position d'une piece donnée en argument
	public int[] position(int numero) {
		int[] T = new int[2];
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				if (config[i][j] == numero) {
					T[0] = i;
					T[1] = j;
					break;
				}
			}
		}
		return T;
	}

	// Donne les positions de toutes les pieces
	public int[][] positions() {
		int[][] T = new int[size*size][2];
		for (int i=0;i<size*size;i++) {
			T[i] = position(i);
		}
		return T;
	}
	
	// detecte les conflits lineaires et rend la somme a ajouter a dheur (T = config_fin.positions() )
	public void conflit(int[][] T) {
		int[] T1 = new int[2];
		int[] T2 = new int[2];
		int piece1;
		int piece2;
		for (int i = 0; i<size; i++) {
			for (int j = 0; j<size; j++) {
				piece1 = config[i][j];
				if(piece1!=0) {
					T1 = T[piece1];
					if (T1[0]==i) {
						for (int k = j+1; k<size; k++) {
							piece2 = config[i][k];
							if (piece2!=0) {
								T2 = T[piece2];
								if ( (T2[0]==i) && ( ((j<k)&&(T1[1]>T2[1])) || ((j>k)&&(T1[1]<T2[1])) ) ) {
									conflit = conflit + 2;
								}
							}
						}
					}
					if (T1[1]==j) {
						for (int k = i+1; k<size; k++) {
							piece2 = config[k][j];
							if (piece2!=0) {
								T2 = T[piece2];
								if ( (T2[1]==j) && ( ((i<k)&&(T1[0]>T2[0])) || ((i>k)&&(T1[0]<T2[0])) ) ) {

									conflit = conflit + 2;
								}
							}
						}
					}		
				}
			}
		}
	}
	
	public void shake(int difficulty) {
		for (int i = 0; i<((int)(2*Math.random()))+size/2; i++) {
			try{
				bouge('B');
				bouge('D');
			}	
			catch (Exception e){}	
		}
		for (int i = 0; i<difficulty; i++) {	
			char[] choix = {'D','G','H','B'};
			try {
				char move = choix[(int) (4*Math.random())];
				bouge(move);
			}	
			catch (Exception e){}	
		}
	}
	
	public String solve() {
		GraphConf g = new GraphConf(this);
		g.IDA();
		return g.cheminSolution;
	}
	
}
	
