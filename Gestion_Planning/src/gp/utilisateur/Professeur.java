package gp.utilisateur;


import java.util.Date;

import gp.db.IProfesseur;
import main.DbConnexion;

public class Professeur extends Utilisateur implements IProfesseur {
	public Professeur (String nom, String prenom, String login
    		, String numMaison, String numMobile,
    		String mdp, String email, String rue, String codePostal, String ville)
    {
    	this.nom = nom;
    	this.prenom = prenom;
    	this.login = login;
    	this.mdp = mdp;
    	this.role = Role.PROFESSEUR;
    	this.numMaison = numMaison;
    	this.numMobile = numMobile;
    	this.email = email;
    	this.rue = rue;
    	this.codePostal = codePostal;
    	this.ville = ville;
    }
	public Professeur(Long matricule, String login, String mdp) {
		this.login = login;
    	this.mdp = mdp;
    	this.role = Role.PROFESSEUR;
    	this.matricule = matricule;
	}
	
	public static Professeur creerProfesseur(String nom, String prenom, String login
    		, String numMaison, String numMobile, String mdp, String email, String rue, String codePostal, String ville) {
		Professeur professeur = new Professeur(nom, prenom, login, numMaison, numMobile, mdp, email, rue, codePostal, ville);
		Long id = professeur.getMatricule();
		// rajouter des null entre les champs manquqnt
		
		java.util.Date d1 = new java.util.Date();
		java.sql.Date d2 = new java.sql.Date(d1.getTime());
		
		String value = "INSERT INTO UTILISATEUR (nom, prenom, mdp , login, role, datenaissance, dateinscription, teldomicile, telmobile, email, rue, codePostal, ville) "
				+ "VALUES ('" + nom + "','" + prenom + "','" + mdp + "','" + login +  "','" + professeur.getRole().toString() + "','" + d2 + "','" + d2 + "','" + numMaison + "','" + numMobile + "','" + email + "','" + rue + "','" + codePostal + "','" + ville + "');";
		

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
				return professeur;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void sauvegarderProfesseur() {}
	public void getProfesseur() {}

}
