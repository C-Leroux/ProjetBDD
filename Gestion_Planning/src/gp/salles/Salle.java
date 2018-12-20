package gp.salles;

import gp.db.ISalle;

public class Salle implements ISalle {
	private Long code = 0L;
	private String nom = null;
	private int nbPlaces = 0;
	private RoomType type = null;
	
	public Salle(String nom, int nbPlaces, RoomType type){
		this.code++;
		this.nom = nom;
		this.nbPlaces = nbPlaces;
		this.type = type;
	}
	
	public Salle creerSalle(String nom, int nbPlaces, RoomType type) {
		Salle salle = new Salle(nom, nbPlaces, type);
		Long code = salle.getCode();
		String value = "INSERT INTO RESPONSABLE VALUES( '"+ code.toString() + "','" + nom + "','" + nbPlaces + "','" + type.toString() + "');";
		int status = 0;
		// status = statement.executeUpdate(value);
		if(status == 0)
		{
			// msg d'erreur l'element n'a pas pu etre inserer dans a base 
			return null;
		}
		else{
			return salle;
		}
		
	}
	public void sauvegarderSalle() {}
	public int supprimerSalle(Long code) {
		String value = "DELETE FROM SALLE WHERE idSalle = "+ code.toString() + ";";
		int status = 0;
		// status = statement.executeUpdate(value);
		return status;
	}
	
	public int getNbPlaces(){
		return this.nbPlaces;
	}
	public Long getCode(){
		return this.code;
	}
}
