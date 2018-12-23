package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

	        // creation d'un groupe
			Groupe groupe1 = Groupe.creerGroupe("TD A", GroupeType.TD, 30 , "L3 NEW 2021");
			

			java.util.Date d1 = new java.util.Date();
			java.sql.Date d2 = new java.sql.Date(d1.getTime());
			
			// creation des eleves
			Eleve eleve = Eleve.creerEleve("moutte", "camille", "20180005", d2 ,"Vietnam","EPITA"
		    		, d2, 'F', "Hochiminh", " ", "0698844194", "", 1L,
		    		"Camille", "mouttecam@gmail.com");
			
			Eleve eleve2 = Eleve.creerEleve("Leroux", "Corentin", "2018010", d2 ,"France","EPITA"
		    		, d2, 'M', "Paris", " ", "0698844194", "", 1L,
		    		"Leroux", "leroux.corentin@efrei.net");
			
			Eleve eleve3 = Eleve.creerEleve("Okou", "Sydney", "2018015", d2 ,"France","EPITA"
		    		, d2, 'M', "Paris", " ", "0698844194", "", 1L,
		    		"Leroux", "oku.sydney@efrei.net");
			
			// creation de professeur
			Professeur professeur1 = Professeur.creerProfesseur("moutte", "jeannick", "201800099"
		    		, "0692523373", "0692523373", "Jeannick", "jj@gmail.com", "37 rue du grand hotel", "97434", "st paul");
			
			Professeur professeur2 = Professeur.creerProfesseur("fourdan", "", "201800099"
		    		, "0692523373", "0692523373", "Jeannick", "jj@gmail.com", "37 rue du grand hotel", "97434", "st paul");
			Professeur professeur3 = Professeur.creerProfesseur("moutte", "jeannick", "201800099"
		    		, "0692523373", "0692523373", "Jeannick", "jj@gmail.com", "37 rue du grand hotel", "97434", "st paul");
			
			// creation de salle
			Salle salle = Salle.creerSalle("E00", 30, RoomType.COURS);
			Salle salle1 = Salle.creerSalle("E01", 30, RoomType.AMPHI);
			Salle salle2 = Salle.creerSalle("E02", 30, RoomType.LABO);
			Salle salle3 = Salle.creerSalle("E03", 30, RoomType.SOUTENANCE);
			Salle salle4 = Salle.creerSalle("E04", 30, RoomType.COURS);
			
			
			// Creation d'un cours
			Calendar calendar = Calendar.getInstance();
			calendar.set(2018, 12, 24, 12, 0);
			Date date = calendar.getTime();
			 
			java.sql.Date lundi2 = new java.sql.Date(date.getTime());
			 
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(2018, 12, 24, 14, 0);
			Date date2 = calendar2.getTime();
			 
			java.sql.Date lundi4 = new java.sql.Date(date2.getTime());
			
			Cours cours = Cours.creerCours("algo", lundi2, lundi4, 1L , 1L, 1L, 1);
			
			// creation administrateur
			Administrateur admin = Administrateur.creerAdministrateur("admin", "admin", "admin", "0698844194",  "0698844194", "Adminadmin", "admin@gmail.fr", "99", "94270", "kremlin bicetre");
			Administrateur admin2 = Administrateur.creerAdministrateur("admin2", "admin2", "admin", "0698844194",  "0698844194", "Adminadmin", "admin@gmail.fr", "99", "94270", "kremlin bicetre");
			
			// creation contact
			Contact contact = Contact.creeContact("resp","resp", "adresse", "0698844194", "resp@resp.fr", 1L);
			Contact contact1 = Contact.creeContact("resp1","resp1", "adresse", "0698844194", "resp@resp.fr", 1L);
			Contact contact2 = Contact.creeContact("resp2","resp2", "adresse", "0698844194", "resp@resp.fr", 1L);
			
			
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
