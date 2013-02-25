package grapheTaquin;


public class Arete {
	
	// configuration de depart
	public Taquin origine;
	// configuration d'arrivee
	public Taquin destination;
	// stocke le caractere correspondant au mouvement
	public char mvmt;
	
	public Arete(Taquin _origine, Taquin _destination, char _mvmt) {
		origine = _origine;
		destination = _destination;
		mvmt = _mvmt;
	}
	
}
