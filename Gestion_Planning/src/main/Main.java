package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import gp.cours.Cours;
import gp.groupes.Groupe;
import gp.groupes.GroupeType;
import gp.salles.RoomType;
import gp.salles.Salle;
import gp.utilisateur.Administrateur;
import gp.utilisateur.Contact;
import gp.utilisateur.Eleve;
import gp.utilisateur.Professeur;

public class Main {
	
	public static void main(String[] args) {
		try {

	
			Groupe groupe1 = Groupe.creerGroupe("TD A", GroupeType.TD, 30 , "L3 NEW 2021");
			
			
			 java.util.Date d1 = new java.util.Date();
			 java.sql.Date d2 = new java.sql.Date(d1.getTime());
			 
			Eleve eleve = Eleve.creerEleve("moutte", "camille", "20180005", d2 ,"Vietnam","EPITA"
		    		, d2, 'F', "Hochiminh", " ", "0698844194", "", 1L,
		    		"Camille", "mouttecam@gmail.com");
			
			
			Professeur p1 = Professeur.creerProfesseur("moutte", "jeannick", "201800099"
		    		, "0692523373", "0692523373", "Jeannick", "jj@gmail.com", "37 rue du grand hotel", "97434", "st paul");
			
			
			
			Salle salle = Salle.creerSalle("E01", 30, RoomType.COURS);
			
			 //java.util.Date d1 = new java.util.Date();
			 //java.sql.Date d2 = new java.sql.Date(d1.getTime());
			
			Cours.creerCours("cours algo", d2, d2, 1L , 1L, 1L, 1);
			Administrateur admin = Administrateur.creerAdministrateur("admin", "admin", "admin", "0698844194",  "0698844194", "Adminadmin", "admin@gmail.fr", "99", "94270", "kremlin bicetre");
			
			Contact contact = Contact.creeContact("resp","resp", "adresse", "0698844194", "resp@resp.fr", 1L);
			
			
			//Cours.supprimerCours(2L);
			  System.out.println("------------------");
		      ArrayList<Cours> listcours = eleve.getCoursByIdSemaine(1);
		      System.out.println(listcours.size());
		      for(int i = 0; i < listcours.size(); i++) {
		    	  System.out.println(listcours.get(i).getNom());
		      }
		      System.out.println("------------------");
		      
		      
			DbConnexion db = new DbConnexion("SELECT * FROM utilisateur;");
			ResultSet rs = db.executerRequete();
			while (rs.next()) {
			    long matricule = rs.getLong("matricule");
			    String nom = rs.getString("nom");
			    String prenom = rs.getString("prenom");
			    System.out.println(matricule + " " + nom + " " + prenom);
			}
			db.fermerConnexion();
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver de la base de donnee non trouve");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Erreur SQL");
			e.printStackTrace();
		}
	}

}
