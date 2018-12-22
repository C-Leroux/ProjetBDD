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
	
	//Long idGroupe = 0L;
	//private ArrayList<Contact> contacts = new ArrayList<Contact>();
	
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
	
	public Eleve(Long matricule, String login, String mdp, Long idGroupe) {
		this.login = login;
    	this.mdp = mdp;
    	this.role = Role.ETUDIANT;
    	this.idGroupe = idGroupe;
    	this.matricule = matricule;
	}

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
	
	public void sauvegarderEleve() {
	}
	
	
	public void getEleve() {
/*		Connection connexion = null;
		try {
			connexion = MySqlProvider.getInstance().getConnexion();
			String query = "SELECT * FROM users";
			// create the java statement
			Statement st = connexion.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
		      
			// iterate through the java resultset
			while (rs.next()) {
			    long matricule = rs.getLong("matricule");
			    String nom = rs.getString("nom");
			    String prenom = rs.getString("prenom");
			    System.out.println(nom + " " + prenom);
			}
			st.close();

		} catch (SQLException e) {
			System.out.println("Erreur de connexion a la base de donn�e nooob. " + e.getMessage());
	    } finally {
			if (connexion != null) {
	            try {
	                connexion.close();
	            } catch (SQLException ignore) {
	            }				
			}
	    }*/
	}
	
	public Long getGroupbyEleve(){
		return this.idGroupe; 
	}
	
	/*public ArrayList<Cours> getCoursByIdSemaine(int idSemaine)
    {
    	String str = "SELECT * FROM COURS WHERE numSemaine = " + idSemaine
				+ " AND idGroupe = " + this.idGroupe + ";";
		// ResultSet resultat = statement.executeQuery(str);

		// traiter la liste de r�sultat
		ArrayList<Cours> listCours = new ArrayList<Cours>();
		/*
		 * while(resultat.next()){ Long id = resultat.getLong(); Date debut =
		 * resultat.getDate(); Date fin = resultat.getDate(); Long idSalle =
		 * resultat.getLong(); Long matricule = resultat.getLong(); Long
		 * idGroupe = resultat.getLong(); int numSemaine = resultat.getString();
		 * Cours cours = new Cours(id, debut, fin, idSalle, matricule, idGroupe,
		 * numSemaine); listContact.add(contact); }
		 */
		//return listCours;
    //}
	
	
}
