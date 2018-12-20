package interfaceG;

import java.util.ArrayList;

public class Planning {
	
	private ArrayList<Cours> liste_cours;
	
	public Planning() {
		ArrayList<Cours>liste_cours = new ArrayList<Cours>();
	}
	
	public void add_cours(Cours c)
	{
		liste_cours.add(c);
	}

}
