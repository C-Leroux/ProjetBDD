package gp.groupes;

import gp.db.IGroupe;
import main.DbConnexion;

public class Groupe implements IGroupe {
	Long id = 0L;
	String nom = null;
	GroupeType group = null;
	int nbPlaces = 0;
	String promo = null;
	
	public Groupe(String name, GroupeType group, int nbPlaces, String promo){
		this.id++;
		this.nom = name;
		this.group = group;
		this.nbPlaces = nbPlaces;
		this.promo = promo;
	}
	
	public Groupe creerGroupe(String nom, GroupeType group, int nbPlaces, String promo) {
		Groupe groupe = new Groupe( nom, group, nbPlaces, promo);
		Long id = groupe.getId();
		String value = "INSERT INTO GROUPE VALUES( '"+ id.toString() + "','" + nom + "','" + promo + "','" + group.toString() + "','"+ nbPlaces +"');";
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
	
	public Groupe getGroupebyId(Long id){		
		String str = "SELECT nbEleves FROM GROUPE WHERE idGroupe = " + id + ";" ;
		//ResultSet resultat = statement.executeQuery(str);
		
		// traiter le resultat
		/*
		  int nbPlace = resultat.getInt();
		  String nom = resultat.getString();
		  GroupeType group = GroupeType(resultat.getString());
		  
		  int nbPlaces = resultat.getInt();
		  String promo = resultat.getString();
		  Groupe groupe = new Groupe( nom, group, nbPlaces, promo);
		  return groupe;
		  */
		return null;
		
	
	}
	
	public int getNbPlaces(){
		return this.nbPlaces;
	}
}
