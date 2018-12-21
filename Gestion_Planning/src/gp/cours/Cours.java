package gp.cours;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import gp.db.ICours;
import gp.utilisateur.Contact;

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

	public Cours(String nom, Date debut, Date fin, Long idsalle, Long matricule,
			Long idGroupe, int numSemaine) {
		this.nom = nom;
		this.dateDebut = debut;
		this.dateFin = fin;
		this.idSalle = idsalle;
		this.matricule = matricule;
		this.idGroupe = idGroupe;
		this.nbPlaceSalle = getNbPlacesSalle();
	}

	public Cours creerCours(String nom, Date debut, Date fin, Long idsalle, Long matricule,
			Long idGroupe, int numSemaine) {
		Cours cours = new Cours(nom, debut, fin, idsalle, matricule, idGroupe,
				numSemaine);
		String value = "INSERT INTO RESPONSABLE VALUES( '"
				+ cours.getId().toString()+ "','" + nom + "','" + debut + "','" + fin
				+ "','" + idsalle + "','" + matricule + cours.getNumSemaine()
				+ "');";
		int status = 0;
		// status = statement.executeUpdate(value);
		if (status == 0) {
			// msg d'erreur l'element n'a pas pu etre inserer dans a base
			return null;
		} else {
			return cours;
		}
	}

	public int supprimerCours(Long id) {
		String value = "DELETE FROM COURS WHERE idCours = " + id.toString()
				+ ";";
		int status = 0;
		// status = statement.executeUpdate(value);
		return status;

	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	// si le cours existe deja
	// selectionne le cours avec une date de d�but �gale
	public boolean getCoursbyDateDebut(Date date, Long idGroupe) {
		// selectionne le cours avec une date de d�but �gale
		String str = "SELECT * FROM SALLE WHERE dateDebut = " + date
				+ " AND idGroupe = " + idGroupe + ";";
		// ResultSet resultat = statement.executeQuery(str);
		Cours cours;
		Long idCours = -1L;
		// idCours = resultat.getLong();
		return !(idCours == -1);
	}

	public Long getIdSalle() {
		return idSalle;

	}

	public int getNbPlacesSalle() {
		String str = "SELECT nbPlaces FROM SALLE WHERE idSalle = "
				+ this.idSalle + ";";
		// ResultSet resultat = statement.executeQuery(str);
		int nbPlace = 0;
		// nbPlace = resultat.getInt();
		return nbPlace;
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

	public static ArrayList<Cours> getCoursbyIdSemaine(int idSemaine, Long idGroupe) {
		String str = "SELECT * FROM COURS WHERE numSemaine = " + idSemaine
				+ " AND idGroupe = " + idGroupe + ";";
		// ResultSet resultat = statement.executeQuery(str);

		// traite la liste de r�sultat
		ArrayList<Cours> listCours = new ArrayList<Cours>();
		/*
		 * while(resultat.next()){ Long id = resultat.getLong(); Date debut =
		 * resultat.getDate(); Date fin = resultat.getDate(); Long idSalle =
		 * resultat.getLong(); Long matricule = resultat.getLong(); Long
		 * idGroupe = resultat.getLong(); int numSemaine = resultat.getString();
		 * Cours cours = new Cours(id, debut, fin, idSalle, matricule, idGroupe,
		 * numSemaine); listContact.add(contact); }
		 */
		return listCours;
	}

	public static ArrayList<Cours> getCoursbyIdDate(Date date, Long idGroupe) {
		String str = "SELECT * FROM COURS WHERE dateDebut = " + date
				+ " AND idGroupe = " + idGroupe + ";";
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
		return listCours;
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
