package gp.db;

import gp.groupes.Groupe;
import gp.groupes.GroupeType;

public interface IGroupe {
	public Groupe creerGroupe(String nom, GroupeType group, int nbPlaces, String promo);
	public void supprimerGroupe();
	public void getGroupe();
	public int getNbPlaces();
	public Groupe getGroupebyId(Long Id);
}
