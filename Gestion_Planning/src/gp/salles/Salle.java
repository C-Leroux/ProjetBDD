package gp.salles;

import gp.db.ISalle;
import main.DbConnexion;

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
	
	public static Salle creerSalle(String nom, int nbPlaces, RoomType type) {
		Salle salle = new Salle(nom, nbPlaces, type);
		String value = "INSERT INTO SALLE (nom, nbPlaces, typeSalle) VALUES( '" + nom + "','" + nbPlaces + "','" + type.toString() + "');";
		DbConnexion db;
		try {
			db = new DbConnexion(value);
			int status = db.executerInsert();
			if(status == 0)
			{
				System.out.println("L'utilisateur n'a pas pu etre rentre dans la base de donnee.");
				return null;
			}
			else {
				return salle;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
