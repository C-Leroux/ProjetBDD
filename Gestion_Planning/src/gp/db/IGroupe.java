package gp.db;

import gp.groupes.Groupe;

public interface IGroupe {
	public void creerGroupe();
	public void sauvegarderGroupe();
	public void supprimerGroupe();
	public void getGroupe();
	public void rechercherParPromotion();
	public void rechercherParGroupe();
	public int getNbPlaces();
	public Groupe getGroupebyId(Long Id);
}
