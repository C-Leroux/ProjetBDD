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
	
	
	public Contact(String nom, String prenom, String adresse, String telephone, String email, Long matricule){
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.matricule = matricule;
		this.id++;
	}
	
	public static Contact creeContact(String nom, String prenom, String adresse, String telephone, String email, Long matricule) {
		Contact contact = new Contact(nom, prenom, adresse, telephone, email, matricule);
		String value = "INSERT INTO RESPONSABLE (nomResp, prenomResp, adresseResp, telResp, emailResp, matricule) VALUES( '" + nom + "','" + prenom + "','" + adresse + "','" + telephone + "','" + email + "','" + matricule + "');";
		DbConnexion db;
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
		}
		return null;

	}
	public void sauvegarderContact() {}
	
	public void supprimerContact() {
		
	}
	
	public ArrayList<Contact> getContact(Long matricule) {
		String str = "SELECT * FROM RESPONSABLE WHERE matricule = " + matricule + ";" ;
		//ResultSet resultat = statement.executeQuery(str);
		
		// traiter le resultat
		ArrayList<Contact> listContact = new ArrayList<Contact>();
	    /*while(resultat.next()){
		  Long id = resultat.getLong();
		  String nom = resultat.getString();
		  String prenom = resultat.getString();
		  String adresse = resultat.getString();
		  String telephone = resultat.getString();
		  String email = resultat.getString();
		  Long matricule = resultat.getLong();
		  Contact contact = new Contact(id, nom, prenom, adresse, telephone, email, matricule);
		  listContact.add(contact);
	}*/
		return listContact;
		
	}
	
	public Long getId()
	{
		return this.id;
	}
	
}
