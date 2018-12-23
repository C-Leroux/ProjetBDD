package gp.utilisateur;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import gp.cours.Cours;
import gp.db.IEleve;
import main.DbConnexion;


public class Eleve extends Utilisateur implements IEleve {
	
	/**
    Constructeur de la classe Eleve
    @param nom, le nom de l'utilisateur
    @param prenom de l'utilisateur
    @param login, de l'utilisateur
    @param mdp, de l'utilisateur
    @param role, role de l'utilisateur
    @param dateNaissance, la date de naissance
    @param villeNaissance, la ville de naissance
    @param paysNaissance, le pays de naissance
    @param sexe, le sexe de l'utilisateur
    @param inscription date, la date d'inscription de l'eleve
    @param numMaison , le numero de telephone maison
    @param numMobile, le numero de téléphone mobile
    @param email, l'emial de l'utilisateur
    @param idgroupe, l'id du groupe de l'eleve
    @return eleve
    */
	
    public Eleve(String nom, String prenom, String login, Date dateNaissance, String paysNaissance, String etablissementPrecedent
    		, Date inscriptionDate, char sex, String villeNaissance, String numMaison, String numMobile, String photo, Long idGroupe,
    		String mdp, String email)
    {
    	this.nom = nom;
    	this.prenom = prenom;
    	this.login = login;
    	this.mdp = mdp;
    	this.role = Role.ETUDIANT;
    	this.dateNaissance = dateNaissance;
    	this.villeNaissance = villeNaissance;
    	this.paysNaissance = paysNaissance;
    	this.sex = sex;
    	this.inscriptionDate = inscriptionDate;
    	this.etablissementPrecedent = etablissementPrecedent;
    	this.numMaison = numMaison;
    	this.numMobile = numMobile;
    	this.email = email;
    	this.photo = photo;
    	this.idGroupe = idGroupe;
    }
    
    /**
    Constructeur de la classe Eleve
    @param login, de l'utilisateur
    @param mdp, de l'utilisateur
    @param role, role de l'utilisateur
    @param email, l'emial de l'utilisateur
    @param idGroupe, idgroupe de l'eleve
    @return eleve
    */
	public Eleve(Long matricule, String login, String mdp, Long idGroupe) {
		this.login = login;
    	this.mdp = mdp;
    	this.role = Role.ETUDIANT;
    	this.idGroupe = idGroupe;
    	this.matricule = matricule;
	}
	
	/**
    Creer l'eleve et insert l'eleve dans la base de donnee
    @param nom, le nom de l'utilisateur
    @param prenom de l'utilisateur
    @param login, de l'utilisateur
    @param mdp, de l'utilisateur
    @param role, role de l'utilisateur
    @param dateNaissance, la date de naissance
    @param villeNaissance, la ville de naissance
    @param paysNaissance, le pays de naissance
    @param sexe, le sexe de l'utilisateur
    @param inscription date, la date d'inscription de l'eleve
    @param numMaison , le numero de telephone maison
    @param numMobile, le numero de téléphone mobile
    @param email, l'emial de l'utilisateur
    @return eleve
    */
	public static Eleve creerEleve(String nom, String prenom, String login, Date dateNaissance, String paysNaissance, String etablissementPrecedent
    		, Date inscriptionDate, char sex, String villeNaissance, String numMaison, String numMobile, String photo, Long idGroupe, String mdp, String email) throws ClassNotFoundException {
		Eleve eleve = new Eleve(nom, prenom, login, dateNaissance, paysNaissance, etablissementPrecedent
	    		, inscriptionDate, sex, villeNaissance, numMaison, numMobile, photo, idGroupe, mdp, email);
		Long id = eleve.getMatricule();
		String value = "INSERT INTO UTILISATEUR (nom, prenom, mdp, login, role, datenaissance, villenaissance, paysnaissance, sexe, dateinscription, etabPrecedent, teldomicile, telmobile, email, photo, rue, codePostal, ville, idGroupe) VALUES( '" 
		+ nom + "','" + prenom + "','" + mdp + "','" + login +  "','" + eleve.getRole() +  "','" + dateNaissance + "','" + villeNaissance + "','" +
		paysNaissance + "','" + sex + "','" + inscriptionDate + "','" + etablissementPrecedent + "','" + numMaison + "','" + numMobile + "','" + email + "','" + photo + "','','','' ,'" + idGroupe.intValue()+ "');" ;
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
				return eleve;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
    Constructeur de la classe Eleve
    @param 
    @return idgroupe de l'eleve
    */
	public Long getGroupbyEleve(){
		return this.idGroupe; 
	}
	
}
