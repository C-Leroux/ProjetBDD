package gp.db;

import gp.salles.RoomType;
import gp.salles.Salle;

public interface ISalle {
	public Salle creerSalle(String nom, int nbPlaces, RoomType type);
	public void sauvegarderSalle();
	public int supprimerSalle(Long code);
	public int getNbPlaces();
}
