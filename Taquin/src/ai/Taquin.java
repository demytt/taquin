package ai;

public class Taquin {

	public int[][] config;
	public int[] trou = new int[2];
	int size;
	
	public Taquin (int[][] _config) {
		config = _config;
		size = config.length;
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
	
	
	public void bouge(char mvmt) {
		int a = trou[0];
		int b = trou[1];
		int[] tab = mouvement(mvmt);
		int i = tab[0];
		int j = tab[1];
		if ((a+i<size)&(b+j<size)&(a+i>=0)&(b+j>=0)) {
			config[a][b] = config[a+i][b+j];
			config[a+i][b+j] = 0;
			trou[0] = trou[0] + i;
			trou[1] = trou[1] + j;
		}
	}

	// A partir du caractere du mouvement, renvoie les coordonnees du deplacement
	public int[] mouvement(char c){
		int[] mvmt = new int[2];
		if(c=='D')
			mvmt[1]=1;
		if(c=='G')
			mvmt[1]=-1;
		if(c=='B')
			mvmt[0]=1;
		if(c=='H')
			mvmt[0]=-1;
		return mvmt;
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

		
	// Determine si une configuration est soluble ou non
	public boolean soluble (){
		int[] tab = new int[size*size];
		for(int i=0;i<size;i++){
			for(int j=0; j<size; j++){
				tab[j+size*i] = config[i][j];
			}
		}
		double sign = 1;
		for(int j=0; j<size*size; j++){
			for(int i=0; i<j; i++){
				sign = sign * (tab[j]-tab[i])/(j-i);
			}
		}
		sign = Math.round(sign);
		int paritetrou = trou[0]+trou[1];
		// Le taquin est soluble si paritetrou est pair et sign vaut 1, ou si paritetrou
		// est impair et sign vaut -1
		int n = ((int)sign+1)/2+paritetrou;
		return (n%2==1);
	}
	
		
}
	

