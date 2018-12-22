package gp.db;

import gp.salles.RoomType;
import gp.salles.Salle;

public interface ISalle {
	public int supprimerSalle(Long code);
	public int getNbPlaces();
}
