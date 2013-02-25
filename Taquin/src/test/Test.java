package test;


import grapheTaquin.GraphConf;
import grapheTaquin.Taquin;
import ui.TaquinUI;

public class Test {

	
	public static void main(String[] args) {
//		Taquin T = new Taquin("3 6 5 2 10 0 15 14 1 4 13 12 9 8 11 7");
//		if (!T.soluble()) {
//			System.out.println("Taquin non soluble");
//			return;
//		}
//		System.out.print("OK ");
//		GraphConf g = new GraphConf(T, config_fin);
//		g.IDA();
//		System.out.println(g.cheminSolution);
		
		new TaquinUI(5);
	}
	
	
}
