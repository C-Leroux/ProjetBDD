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
	
	/**
    Creer un cours, verifie que le cours correspond aux criteres, l'insere dans la base de donnees,
    @param nom, le nom du cours
    @param debut, la date et l'heure de debut de cours
    @param fin, la date et l'heure de fin de cours
    @param idSalle, l'identifiant de la salle ou aura lieux le cours
    @param matricule, le matricule du professeur qui donnera le cours
    @param idGroupe, l'identifiant du groupe qui aura le cours
    @param numSemaine, le numero de semaine ou aura lieu le cours
    @return Cours.
    */
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

	/**
    supprime le cours en selectionnant l'id du cours
    @param id, l'identifiant unique des cours
    @return Cours.
    */
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

	/**
    si le cours existe, selection le cours avec la date et l'id du groupe passer en parametre
    @param date du cours correspondant, l'id du groupe
    @return boolean, retourne vrai si l'id du cours existe.
    */
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

	/**
    retourn l'identifiant unique de la salle
    @param 
    @return id de la salle.
    */
	public Long getIdSalle() {
		return idSalle;

	}

	/**
    retourn le nombre de place disponible dans la salle
    @param 
    @return le nombre de place dans la salle
    */
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
	
	/**
    getter de l'idGroupe. retourne l'identifiant unique du groupe
    @param 
    @return retourne l'identifiant unique du groupe
    */
	public Long getIdGroup() {
		return this.idGroupe;
	}

	/**
    getter de l'id du cours. retourne l'identifiant unique du cours
    @param 
    @return retourne l'identifiant unique du cours
    */
	public Long getId() {
		return this.id;
	}

	/**
    getter du numero de semaine.
    @param 
    @return retourne le numero de semaine
    */
	public int getNumSemaine() {
		return this.numSemaine;
	}

	/**
    retourne la liste des cours, correspondant au numero de semaine, avec l'idgroupe
    @param le numero de la semaine, pour selectionner la semaine a afficher
    @param idgroupe, pour savoir qu'elle groupe selectionner
    @return retourne la liste des cours
    */
	public static ArrayList<Cours> getCoursbyIdSemaine(int idSemaine, Long idGroupe) throws SQLException {
		String str = "SELECT * FROM COURS WHERE numSemaine = '" + idSemaine
				+ "' AND idGroupe = '" + idGroupe + "';";
		DbConnexion db;
		try {
			db = new DbConnexion(str);
			ResultSet resultat = db.executerRequete();
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

	/**
    retourne la liste des cours, correspondant a la date , avec l'idgroupe
    @param la date, pour les cours a la date correspondante
    @param idgroupe, pour savoir qu'elle groupe selectionner
    @return retourne la liste des cours
    */
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
	/**
    getter jour
    @param 
    @return retourne le numero de jour
    */
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
	
	/**
    getter heure
    @param
    @return String, retourne l'heure d'un cours en string formatter
    */
	public String getHeure() {
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(dateDebut) + " - " + df.format(dateFin);
	}
	
	/**
    getter nm du cours
    @param 
    @return retourne le nom
    */
	public String getNom() {
		return this.nom;
	}

}
