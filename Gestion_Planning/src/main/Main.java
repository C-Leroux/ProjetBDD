package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import gp.cours.Cours;
import gp.groupes.Groupe;
import gp.groupes.GroupeType;
import gp.salles.RoomType;
import gp.salles.Salle;
import gp.utilisateur.Administrateur;
import gp.utilisateur.Contact;
import gp.utilisateur.Eleve;
import gp.utilisateur.Professeur;
import interfaceG.FenetreLog;

public class Main {
	
	public static void main(String[] args) throws ParseException {
		try {

			// entre des donnee dans la base de donnee
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
			
			
			// Creation du cours 1
			Calendar calendar = Calendar.getInstance();
			calendar.set(2018, 12, 24, 12, 00, 00);
			Date date = calendar.getTime();
			
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:kk:ss", Locale.ENGLISH);
			String dateStr1 = format1.format(date);

			Date date12 = new SimpleDateFormat("yyyy-MM-dd HH:kk:ss", Locale.ENGLISH).parse(dateStr1);
			java.sql.Timestamp lundi2 = new java.sql.Timestamp(date12.getTime());
			
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(2018, 12, 24, 14, 00, 00);
			Date date2 = calendar2.getTime();
			
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:kk:ss", Locale.ENGLISH);
			String dateStr2 = format2.format(date2);

			Date date22 = new SimpleDateFormat("yyyy-MM-dd HH:kk:ss", Locale.ENGLISH).parse(dateStr2);
			 
			java.sql.Timestamp lundi4 = new java.sql.Timestamp(date22.getTime());
			
			Cours cours = Cours.creerCours("algorithmique", lundi2, lundi4, 1L , 1L, 1L, 1);
			
			
			// Creation du cours 2
			
			calendar = Calendar.getInstance();
			calendar.set(2018, 12, 24, 16, 00, 00);
			date = calendar.getTime();
			
			format1 = new SimpleDateFormat("yyyy-MM-dd HH:kk:ss", Locale.ENGLISH);
			dateStr1 = format1.format(date);

			date12 = new SimpleDateFormat("yyyy-MM-dd HH:kk:ss", Locale.ENGLISH).parse(dateStr1);
			lundi2 = new java.sql.Timestamp(date12.getTime());
						
			
			calendar2 = Calendar.getInstance();
			calendar2.set(2018, 12, 24, 18, 00, 00);
			date2 = calendar2.getTime();
			
			format2 = new SimpleDateFormat("yyyy-MM-dd HH:kk:ss", Locale.ENGLISH);
			dateStr2 = format2.format(date2);

			date22 = new SimpleDateFormat("yyyy-MM-dd HH:kk:ss", Locale.ENGLISH).parse(dateStr2);
			 
			lundi4 = new java.sql.Timestamp(date22.getTime());
			
			Cours cours1 = Cours.creerCours("theorie des graph", lundi2, lundi4, 1L , 1L, 1L, 1);

			
			// creation administrateur
			Administrateur admin = Administrateur.creerAdministrateur("admin", "admin", "admin", "0698844194",  "0698844194", "Adminadmin", "admin@gmail.fr", "99", "94270", "kremlin bicetre");
			Administrateur admin2 = Administrateur.creerAdministrateur("admin2", "admin2", "admin", "0698844194",  "0698844194", "Adminadmin", "admin@gmail.fr", "99", "94270", "kremlin bicetre");
			
			// creation contact
			Contact contact = Contact.creeContact("resp","resp", "adresse", "0698844194", "resp@resp.fr", 1L);
			Contact contact1 = Contact.creeContact("resp1","resp1", "adresse", "0698844194", "resp@resp.fr", 1L);
			Contact contact2 = Contact.creeContact("resp2","resp2", "adresse", "0698844194", "resp@resp.fr", 1L);
			
			
			//Cours.supprimerCours(2L);
			System.out.println("------------------");
			System.out.println("-- Liste des Cours --");
			System.out.println("------------------");
		    ArrayList<Cours> listcours = eleve.getCoursByIdSemaine(1);
		    for(int i = 0; i < listcours.size(); i++) {
		    	System.out.println(listcours.get(i).getNom() + " " + listcours.get(i).getDateDebut());
		    }
		    System.out.println("------------------");   
		    System.out.println("-- Liste des utilisateurs --");  
		    System.out.println("------------------");
			DbConnexion db = new DbConnexion("SELECT * FROM utilisateur;");
			ResultSet rs = db.executerRequete();
			while (rs.next()) {
			    long matricule = rs.getLong("matricule");
			    String nom = rs.getString("nom");
			    String prenom = rs.getString("prenom");
			    String role = rs.getString("role");
			    System.out.println(matricule + " " + nom + " " + prenom + " " + role);
			}
			
			System.out.println("------------------");   
		    System.out.println("-- Liste des salles --");  
		    System.out.println("------------------");
			DbConnexion db1 = new DbConnexion("SELECT * FROM SALLE;");
			ResultSet rs1 = db1.executerRequete();
			while (rs1.next()) {
			    String nom = rs1.getString("nom");
			    int nbPlaces = rs1.getInt("nbPlaces");
			    String typeSalle = rs1.getString("typeSalle");
			    System.out.println(nom +  " " + nbPlaces + " " + typeSalle);
			}
			
			
			System.out.println("------------------");   
		    System.out.println("-- Liste des Responsables eleves --");  
		    System.out.println("------------------");
			DbConnexion db2 = new DbConnexion("SELECT * FROM RESPONSABLE;");
			ResultSet rs2 = db2.executerRequete();
			while (rs2.next()) {
			    String nom = rs2.getString("nomResp");
			    String prenom = rs2.getString("prenomResp");
			    int matricule = rs2.getInt("matricule");
			    System.out.println(nom + " " + prenom + "  Responbla de l'eleve " + matricule);
			}
			
			
			System.out.println("------------------");   
		    System.out.println("-- Liste des Groupes --");  
		    System.out.println("------------------");
			DbConnexion db3 = new DbConnexion("SELECT * FROM GROUPE;");
			ResultSet rs3 = db3.executerRequete();
			while (rs3.next()) {
			    String nom = rs3.getString("nom");
			    String promotion = rs3.getString("promotion");
			    System.out.println(nom + " " + promotion );
			}
			
			// Ouvre la fenetre pour se connecter
			
			//FenetreLog fenetre = new FenetreLog();
			//AjoutCours fenetre = new AjoutCours();
			//fenetre.setVisible(true);
			
			// fermeture de la base de donnée
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
