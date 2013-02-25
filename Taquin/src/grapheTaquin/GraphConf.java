package grapheTaquin;

import java.util.PriorityQueue;

public class GraphConf {

	Taquin configIni;
	boolean solution;
	public String cheminSolution;

	Taquin configFin;

	
	public GraphConf(Taquin _configIni) {
		configIni = _configIni;
		String S = "";
		for (int i=0; i<configIni.size*configIni.size; i++) S+=Integer.toString(i)+" ";
		configFin = new Taquin(S);
		solution = false;
	}
	
	public GraphConf(Taquin _configIni, Taquin _configFin) {
		configIni = _configIni;
		configFin = _configFin;
		solution = false;
	}

	public void IDA() {
		int [][] T = configFin.positions();
		configIni.conflit(T);
		configIni.dheur=configIni.distance(configFin) + configIni.conflit;
		if (configIni.equals(configFin)) {
			solution = true;
		}
		int limite = configIni.dheur;
		while (!solution) {
			System.out.print(limite+" ");
			limite = DFS(limite, T);
		}
		System.out.print(limite);
	}

	public int DFS(int limite, int[][] T) {
		int l = limite;
		PriorityQueue<Taquin> P = new PriorityQueue<Taquin>(1, new TaquinComparator());
		P.add(configIni);
		while (!P.isEmpty()) {
			Taquin T1 = P.poll();
			if (configFin.equals(T1)) {
				solution = true;
				cheminSolution = T1.chemin;
				return limite;
			}
			for (Arete A : T1.aretes()) {
				Taquin T2 = A.destination;
				T2.dist = T1.dist + 1;
				T2.conflit(T);
				int k1 = T1.trou[0];
				int k2 = T1.trou[1];
				int[] pos = T[T2.config[k1][k2]];
				int j1 = T2.trou[0];
				int j2 = T2.trou[1];
				if ((Math.abs(pos[0] - k1) + Math.abs(pos[1] - k2)) > (Math.abs(pos[0] - j1) + Math.abs(pos[1] - j2))) {
					T2.dheur = T1.dheur + 1 + T2.conflit - T1.conflit;
				} 
				else {
					T2.dheur = T1.dheur - 1 + T2.conflit - T1.conflit;
				}
				if ((T2.dist + T2.dheur) <= l) P.add(T2);
			}
		}
		return limite+1;
	}

}
