package gp.groupes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import gp.cours.Cours;
import gp.db.IGroupe;
import main.DbConnexion;

public class Groupe implements IGroupe {
	Long id = 0L;
	String nom = null;
	GroupeType group = null;
	int nbPlaces = 0;
	String promo = null;
	
	public Groupe(String name, GroupeType group, int nbPlaces, String promo){
		this.nom = name;
		this.group = group;
		this.nbPlaces = nbPlaces;
		this.promo = promo;
	}
	
	public Groupe(Long id, String name, GroupeType group, int nbPlaces, String promo){
		this.id = id;
		this.nom = name;
		this.group = group;
		this.nbPlaces = nbPlaces;
		this.promo = promo;
	}
	
	public static Groupe creerGroupe(String nom, GroupeType group, int nbPlaces, String promo) {
		Groupe groupe = new Groupe( nom, group, nbPlaces, promo);
		String value = "INSERT INTO GROUPE  (nom, promotion, type, nbeleves) VALUES( '" + nom + "','" + promo + "','" + group.toString() + "','"+ nbPlaces +"');";
		DbConnexion db;
		try {
			db = new DbConnexion(value);
			int status = db.executerInsert();
			if(status == 0)
			{
				System.out.println("Le groupe n'a pas pu etre rentre dans la base de donnee.");
				return null;
			}
			else {
				return groupe;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void supprimerGroupe() {}
	public void getGroupe() {}
//	public void rechercherParPromotion(){}
//	public void rechercherParGroupe(){}
	
	public Long getId(){
		return this.id;
	}
	
	public static Groupe getGroupebyId(Long id) throws SQLException{		
		String str = "SELECT * FROM GROUPE WHERE idGroupe = '" + id.toString() + "';" ;
		DbConnexion db;
		try {
			db = new DbConnexion(str);
			ResultSet resultat = db.executerRequete();
			// traite la liste de resultat
			  if(resultat.next()){ 
				  int nbPlaces = resultat.getInt("nbeleves");
				  String nom = resultat.getString("nom");
				  GroupeType group = GroupeType.valueOf(resultat.getString("type"));
				  
				  String promo = resultat.getString("promotion");
				  Groupe groupe = new Groupe( id,nom, group, nbPlaces, promo);
				  return groupe;
			  }
			 
			  return null; 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
		
	
	}
	
	public int getNbPlaces(){
		return this.nbPlaces;
	}
}
