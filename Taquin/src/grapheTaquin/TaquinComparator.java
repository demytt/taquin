package grapheTaquin;

import java.util.Comparator;

public class TaquinComparator implements Comparator<Taquin>{
		
	public int compare(Taquin T1, Taquin T2) {
		int a = T1.dheur;
		int b = T2.dheur;
		if (a>b)
			return 1;
		if (a==b)
			return 0;
		return -1;	
		}
	
}
