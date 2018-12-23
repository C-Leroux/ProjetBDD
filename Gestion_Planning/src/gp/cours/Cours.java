package gp.cours;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import gp.db.ICours;
import gp.utilisateur.Contact;
import gp.verification.VerificationCours;
import main.DbConnexion;

public class Cours {
	private Long id = 0L;
	private String nom = "";
	private Date dateDebut = null;
	private Date dateFin = null;
	private Long idSalle = 0L;
	private Long matricule = 0L; // le matricule du professeur
	private Long idGroupe = 0L;
	private int nbPlaceSalle = 0;
	private int numSemaine = 0;

	/**
    Constructeur de la classe Cours
    @param nom, le nom du cours
    @param debut, la date et l'heure de debut de cours
    @param fin, la date et l'heure de fin de cours
    @param idSalle, l'identifiant de la salle ou aura lieux le cours
    @param matricule, le matricule du professeur qui donnera le cours
    @param idGroupe, l'identifiant du groupe qui aura le cours
    @param numSemaine, le numero de semaine ou aura lieu le cours
    @return Cours.
    */
	public Cours(String nom, Date debut, Date fin, Long idsalle, Long matricule,
			Long idGroupe, int numSemaine) throws SQLException {
		this.nom = nom;
		this.dateDebut = debut;
		this.dateFin = fin;
		this.idSalle = idsalle;
		this.matricule = matricule;
		this.idGroupe = idGroupe;
		this.nbPlaceSalle = getNbPlacesSalle();
	}
	

	public static Cours creerCours(String nom, Date debut, Date fin, Long idsalle, Long matricule,
			Long idGroupe, int numSemaine) throws SQLException {
		Cours cours = new Cours(nom, debut, fin, idsalle, matricule, idGroupe,
				numSemaine);
		boolean verefieCoursAInserer = VerificationCours.verifieCoursAInsrer(cours);
		if(!verefieCoursAInserer)
			return null;
		String value = "INSERT INTO COURS (nom, datedebut, datefin, idSalle, matricule, idGroupe, numSemaine) VALUES( '"
				+ nom + "','" + debut + "','" + fin
				+ "','" + idsalle.toString() + "','" + matricule.toString() + "','" + idGroupe.toString() + "','" + numSemaine
				+ "');";
		DbConnexion db;
		try {
			db = new DbConnexion(value);
			int status = db.executerInsert();
			if(status == 0)
			{
				System.out.println("Le cours n'a pas pu etre rentre dans la base de donnee.");
				return null;
			}
			else {
				return cours;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int supprimerCours(Long id) {
		String value = "DELETE FROM COURS WHERE idCours = " + id.toString() + ";";
		DbConnexion db;
		try {
			db = new DbConnexion(value);
			return db.executerInsert();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	// si le cours existe deja
	// selectionne le cours avec une date de debut egale
	public boolean getCoursbyDateDebut(Date date, Long idGroupe) throws SQLException {
		java.sql.Date dateSql = new java.sql.Date(date.getTime());
		
		String str = "SELECT * FROM COURS WHERE datedebut = '" + dateSql
				+ "' AND idGroupe = '" + idGroupe.toString() + "';";
		DbConnexion db;
		try {
			db = new DbConnexion(str);
			ResultSet resultat = db.executerRequete();
			//resultat.next();
			// traite la liste de resultat
			Cours cours;
			Long idCours = -1L;
			if(resultat.next())
			  idCours = resultat.getLong("idCours");
			return (idCours == -1);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Long getIdSalle() {
		return idSalle;

	}

	public int getNbPlacesSalle() throws SQLException {
		String str = "SELECT nbPlaces FROM SALLE WHERE idSalle = '"
				+ this.idSalle + "';";
		DbConnexion db;
		try {
			db = new DbConnexion(str);
			int nbPlace = 0;
			ResultSet resultat = db.executerRequete();
			// traite la liste de resultat

			  if(resultat.next()){ 
				  return resultat.getInt("nbPlaces");
			  }
			 
			return nbPlace;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Long getIdGroup() {
		return this.idGroupe;
	}

	public Long getId() {
		return this.id;
	}

	public int getNumSemaine() {
		return this.numSemaine;
	}

	public static ArrayList<Cours> getCoursbyIdSemaine(int idSemaine, Long idGroupe) throws SQLException {
		String str = "SELECT * FROM COURS WHERE numSemaine = '" + idSemaine
				+ "' AND idGroupe = '" + idGroupe + "';";
		DbConnexion db;
		try {
			db = new DbConnexion(str);
			ResultSet resultat = db.executerRequete();
			// traite la liste de resultat
			ArrayList<Cours> listCours = new ArrayList<Cours>();
			  while(resultat.next()){ 
				  Long id = resultat.getLong("idCours");
				  String nom = resultat.getString("nom");
				  Date debut = resultat.getDate("datedebut"); 
				  Date fin = resultat.getDate("datefin"); 
				  Long idSalle = resultat.getLong("idSalle"); 
				  Long matricule = resultat.getLong("matricule"); 
				  int numSemaine = resultat.getInt("numSemaine");
			      Cours cours = new Cours(nom, debut, fin, idSalle, matricule, idGroupe, numSemaine); 
			      listCours.add(cours); 
			  }
			 
			return listCours;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Cours> getCoursbyIdDate(Date date, Long idGroupe) throws SQLException {
		java.sql.Date dateSql = new java.sql.Date(date.getTime());
		
		String str = "SELECT * FROM COURS WHERE datedebut = '" + dateSql
				+ "' AND idGroupe = '" + idGroupe.toString() + "';";
		DbConnexion db;
		try {
			db = new DbConnexion(str);
			ResultSet resultat = db.executerRequete();
			// traite la liste de resultat
			ArrayList<Cours> listCours = new ArrayList<Cours>();
			  while(resultat.next()){ 
				  Long id = resultat.getLong("idCours");
				  String nom = resultat.getString("nom");
				  Date debut = resultat.getDate("datedebut"); 
				  Date fin = resultat.getDate("datefin"); 
				  Long idSalle = resultat.getLong("idSalle"); 
				  Long matricule = resultat.getLong("matricule"); 
				  int numSemaine = resultat.getInt("numSemaine");
			      Cours cours = new Cours(nom, debut, fin, idSalle, matricule, idGroupe, numSemaine); 
			      listCours.add(cours); 
			  }
			 
			return listCours;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getJour() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateDebut);
		int jour = calendar.get(calendar.DAY_OF_WEEK);
		
		switch(jour) {
		case Calendar.MONDAY:
			return 0;
		case Calendar.TUESDAY:
			return 1;
		case Calendar.WEDNESDAY:
			return 2;
		case Calendar.THURSDAY:
			return 3;
		case Calendar.FRIDAY:
			return 4;
		case Calendar.SATURDAY:
			return 5;
		default:
			return 6;
		}
	}
	
	public String getHeure() {
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(dateDebut) + " - " + df.format(dateFin);
	}
	
	public String getNom() {
		return this.nom;
	}

}
