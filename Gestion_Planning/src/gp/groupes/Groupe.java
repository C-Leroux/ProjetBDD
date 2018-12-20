package gp.groupes;

import gp.db.IGroupe;

public class Groupe implements IGroupe {
	Long id = 0L;
	String name = null;
	GroupeType group = null;
	int nbPlaces = 0;
	String promo = null;
	
	public void creerGroupe() {}
	public void sauvegarderGroupe() {}
	public void supprimerGroupe() {}
	public void getGroupe() {}
	public void rechercherParPromotion(){}
	public void rechercherParGroupe(){}
	
	
	
	public Groupe getGroupebyId(Long Id){
		Groupe groupe = new Groupe();
		return groupe;
	}
	
	public int getNbPlaces(){
		return this.nbPlaces;
	}
	
}
