package gp.utilisateur;

import java.util.ArrayList;

import gp.db.IContact;
import main.DbConnexion;

public class Contact implements IContact {
	private static Long id = 0L;
	private String nom = null;
	private String prenom = null;
	private String adresse = null;
	private String telephone = null;
	private String email = null;
	private Long matricule = 0L;
	
	/**
    Constructeur de la classe Contact, qui est le responsable de l'eleve
    @param nom, le nom du contact
    @param prenom du contact
    @param adresse du contact
    @param mdp, de l'utilisateur
    @param telephone, telephone du contact
    @param email, l'emial de l'utilisateur
    @return Contact
    */
	public Contact(String nom, String prenom, String adresse, String telephone, String email, Long matricule){
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.matricule = matricule;
	}
	
	/**
    Creer le responsable en base de donnee de l'utilsateur
    @param nom, le nom du contact
    @param prenom du contact
    @param adresse du contact
    @param mdp, de l'utilisateur
    @param telephone, telephone du contact
    @param email, l'emial de l'utilisateur
    @return Contact
    */
	public static Contact creeContact(String nom, String prenom, String adresse, String telephone, String email, Long matricule) {
		Contact contact = new Contact(nom, prenom, adresse, telephone, email, matricule);
		String value = "INSERT INTO RESPONSABLE (nomResp, prenomResp, adresseResp, telResp, emailResp, matricule) VALUES( '" + nom + "','" + prenom + "','" + adresse + "','" + telephone + "','" + email + "','" + matricule + "');";
		DbConnexion db = null;
		try {
			db = new DbConnexion(value);
			int status = db.executerInsert();
			if(status == 0)
			{
				System.out.println("Le responsable n'a pas pu etre rentre dans la base de donnee.");
				return null;
			}
			else {
				return contact;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.fermerConnexion();
		}
		return null;

	}
	
	/**
    getter de l'id du responsable
    @param 
    @return id du responsable
    */
	public Long getId()
	{
		return this.id;
	}
	
}
