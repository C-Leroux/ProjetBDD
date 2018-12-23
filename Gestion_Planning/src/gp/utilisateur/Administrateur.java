package gp.utilisateur;

import gp.db.IAdministrateur;
import main.DbConnexion;


public class Administrateur extends Utilisateur implements IAdministrateur {
	
	/**
    Constructeur de la classe Administrateur
    @param nom, le nom de l'utilisateur
    @param prenom de l'utilisateur
    @param login, de l'utilisateur
    @param mdp, de l'utilisateur
    @param role, role de l'utilisateur
    @param numMaison , le numero de telephone maison
    @param numMobile, le numero de téléphone mobile
    @param email, l'emial de l'utilisateur
    @param rue, la rue de l'adresse
    @param codePostal, le code postale de l'adresse
    @param ville, la ville de l'adresse de l'utilisateur
    @return Administrateur
    */
	
	public Administrateur (String nom, String prenom, String login
    		, String numMaison, String numMobile, String mdp, String email, String rue, String codePostal, String ville)
    {
    	this.nom = nom;
    	this.prenom = prenom;
    	this.login = login;
    	this.mdp = mdp;
    	this.role = Role.ADMINISTRATEUR;
    	this.numMaison = numMaison;
    	this.numMobile = numMobile;
    	this.email = email;
    	this.rue = rue;
    	this.codePostal = codePostal;
    	this.ville = ville;
    }
	
	/**
    Constructeur de la classe Administrateur
    @param nom, le nom de l'utilisateur
    @param prenom de l'utilisateur
    @param login, de l'utilisateur
    @param mdp, de l'utilisateur
    @param matricule, l'identifiant unique del'utilisateur
    @return Administrateur
    */
	
	public Administrateur(Long matricule, String login, String mdp) {
		this.login = login;
    	this.mdp = mdp;
    	this.role = Role.ADMINISTRATEUR;
    	this.matricule = matricule;
	}
	
	/**
    Creer l'administrateur dans la base de donnee, et le construit 
    @param nom, le nom de l'utilisateur
    @param prenom de l'utilisateur
    @param login, de l'utilisateur
    @param mdp, de l'utilisateur
    @param role, role de l'utilisateur
    @param numMaison , le numero de telephone maison
    @param numMobile, le numero de téléphone mobile
    @param email, l'emial de l'utilisateur
    @param rue, la rue de l'adresse
    @param codePostal, le code postale de l'adresse
    @param ville, la ville de l'adresse de l'utilisateur
    @return Administrateur
    */
	public static Administrateur creerAdministrateur(String nom, String prenom, String login
    		, String numMaison, String numMobile, String mdp, String email, String rue, String codePostal, String ville) {
		Administrateur admin = new Administrateur(nom, prenom, login, numMaison, numMobile, mdp, email, rue, codePostal, ville);
		java.util.Date d1 = new java.util.Date();
		java.sql.Date d2 = new java.sql.Date(d1.getTime());
		
		String value = "INSERT INTO UTILISATEUR (nom, prenom, mdp , login, role, datenaissance, dateinscription, teldomicile, telmobile, email, rue, codePostal, ville) "
				+ "VALUES ('" + nom + "','" + prenom + "','" + mdp + "','" + login +  "','" + Role.ADMINISTRATEUR.toString() + "','" + d2 + "','" + d2 + "','" + numMaison + "','" + numMobile + "','" + email + "','" + rue + "','" + codePostal + "','" + ville + "');";
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
				return admin;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
