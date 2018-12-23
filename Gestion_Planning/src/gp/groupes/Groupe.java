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
	
	/**
    Constructeur du groupe
    @param nom, le nom de l'utilisateur
    @param group, le type de groupe (TD, ELECTIF, LANGUE)
    @param nbPlaces, le nombre d'eleve dans le groupe
    @param promo, l'annee de la promotion
    @return Groupe
    */
	
	public Groupe(String name, GroupeType group, int nbPlaces, String promo){
		this.nom = name;
		this.group = group;
		this.nbPlaces = nbPlaces;
		this.promo = promo;
	}
	
	/**
    Constructeur du groupe
    @param id, l'id d'un groupe
    @param nom, le nom de l'utilisateur
    @param group, le type de groupe (TD, ELECTIF, LANGUE)
    @param nbPlaces, le nombre d'eleve dans le groupe
    @param promo, l'annee de la promotion
    @return Groupe
    */
	public Groupe(Long id, String name, GroupeType group, int nbPlaces, String promo){
		this.id = id;
		this.nom = name;
		this.group = group;
		this.nbPlaces = nbPlaces;
		this.promo = promo;
	}
	
	/**
    Creer un groupe et insert dans la base de donnee
    @param nom, le nom de l'utilisateur
    @param group, le type de groupe (TD, ELECTIF, LANGUE)
    @param nbPlaces, le nombre d'eleve dans le groupe
    @param promo, l'annee de la promotion
    @return Groupe
    */
	public static Groupe creerGroupe(String nom, GroupeType group, int nbPlaces, String promo) {
		Groupe groupe = new Groupe( nom, group, nbPlaces, promo);
		String value = "INSERT INTO GROUPE  (nom, promotion, type, nbeleves) VALUES( '" + nom + "','" + promo + "','" + group.toString() + "','"+ nbPlaces +"');";
		DbConnexion db = null;
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
		} finally {
			db.fermerConnexion();
		}
		return null;
	}
	
	
	/**
    Creer un groupe et insert dans la base de donnee
    @param 
    @return id du groupe
    */
	public Long getId(){
		return this.id;
	}
	
	/**
    selection le groupe avec l'id passer en parametre
    @param id du groupe
    @return groupe
    */
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
	/**
    getter nombre d'eleve dans le groupe
    @param
    @return nombre d'eleve dans le groupe
    */
	public int getNbPlaces(){
		return this.nbPlaces;
	}
}
