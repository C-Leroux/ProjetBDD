package gp.verification;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import gp.cours.*;
import gp.groupes.Groupe;
import gp.utilisateur.Utilisateur;


public class VerificationCours {
	
	/**
         Classe qui verifie que le cours à inserer correspond aux criteres
    */
	
	/**
    Convertit une date en calendar
    @param date, la date à convertir.
    @return Le calendar correspondant à la date.
    */
	
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
    Convertit un calendar en date
    @param date, le calendar à convertir.
    @return date, la date converti.
    */
	public static Date CalendarToDate(Calendar calendar){
		Date date =  calendar.getTime();
        return date;
	}
	
	/**
    Les cours sont prevus du lundi au samedi de 8h00 a 20h00
    Verifie que les cours sont prevu sur les bonnes plages horaires
    @param cours, le cours a verifier.
    @return boolean, returne si le cours correspond aux plages horaires.
    */
	private static boolean verifieCreneau(Cours cours)
	{
		Calendar dateDebut = dateToCalendar(cours.getDateDebut());
		if(dateDebut.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			System.out.println("Dimanche pas de cours");
			return false;
		}
	
		if(dateDebut.get(Calendar.HOUR_OF_DAY) >= 8 && dateDebut.get(Calendar.HOUR_OF_DAY) <= 20)
		{
			Calendar dateFin = dateToCalendar(cours.getDateFin());
		    if(dateFin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			    System.out.println("Dimanche pas de cours");
			   return false;
		    }
		    if(dateFin.get(Calendar.HOUR_OF_DAY) >= 8 && dateFin.get(Calendar.HOUR_OF_DAY) <= 20)
			   return true;
		    if(dateFin.get(Calendar.DAY_OF_WEEK) == dateFin.get(Calendar.DAY_OF_WEEK))
			  return true;
		    System.out.println("Le creaneau horaire de fin de cours ne corresponde pas");
		    return false;
			
	}
		else {
			System.out.println("Le creaneau horaire du debut de cours ne corresponde pas");
			return false;
		}

		
	}
	
	/**
    Verifie que la duree du cours est un multiple de 2h00,
    Pour cela, on caulcul le modulo de 2h00
    @param cours, le cours a verifier.
    @return boolean, returne vrai si le cours est un multiple de 2, sinon faux.
    */
	private static boolean verifieDureeCours(Cours cours){
		Calendar dateDebut = dateToCalendar(cours.getDateDebut());
		Calendar dateFin = dateToCalendar(cours.getDateFin());
		int nbHeure = dateFin.HOUR_OF_DAY - dateDebut.HOUR_OF_DAY;
		if(nbHeure > 0 || nbHeure % 2 == 0)
			return true;
		System.out.println("Le cours n'a pas une plage horaire multiple de 2h00");
		return false;
	}
	
	
	/**
    Calcul la duree du cours, 
    l'heure de fin - l'heure de debut
    @param cours, le cours a verifier.
    @return int, l'heure de fin - l'heure de debut
    */
	private static int calculDureeCours(Cours cours){
		Calendar dateDebut = dateToCalendar(cours.getDateDebut());
		Calendar dateFin = dateToCalendar(cours.getDateFin());
		return dateFin.HOUR_OF_DAY - dateDebut.HOUR_OF_DAY;
	}
	
	
	/**
    Verifie qu'il y a pas plus de 8h00 de cours par jours 
    prend la liste de cours de la journee du groupe, et cacul pour chaque cours la duree et l'additionne
    @param idGroupe, le groupe dont on verifie le nombre d'heure pour la journée, le jour ou l'on veut rajouter le cours.
    @return boolean, si ya 8h00 retourne faux, sinon retourne vrai
    */
	private static boolean nbHeureMaxJour(Long idGroupe, Cours cours) throws SQLException{
		
		ArrayList<Cours> listCour = cours.getCoursbyIdDate(cours.getDateDebut(), idGroupe);
		int count = 0;
		for(int i = 0; i < listCour.size();i++)
		{
			count += calculDureeCours(listCour.get(i));
		}
		count += calculDureeCours(cours);
		if(count < 8)
			return true;
		System.out.println("le nombre d'heure par jour est dejà maximal");
		return false;
	}
	
	
	/**
    Verifie qu'il n'y a pas un autre cours sur la pause dejeuner
    cree une date egale au jour et a lehure de l'insertion veut savoir s'il y a un cours sur cette plage horaire
    si oui on retourne faux, sinon on verifie qu'il n'y a pas un autre cours sur l'autre plage horaire, si il 'y a pas un autre cours,
    on retourne vrai
    @param idGroupe, le groupe dont on verifie s'il y a une pause dejeuner pour la journée, le jour ou l'on veut rajouter le cours.
    @return boolean, s'il existe un autre cours retourne faux, sinon retourne vrai
    */
	private static boolean verifiePauseDej(Long idGroupe, Cours cours) throws SQLException{
		//  selectionne les cours qui debute a 12h00 et a 14h00, met a un si ya au moins un cours dedans b

		Calendar pause12 = dateToCalendar(cours.getDateDebut());
		pause12.set(Calendar.HOUR, 12);
		boolean isExiste = cours.getCoursbyDateDebut(CalendarToDate(pause12), cours.getIdGroup());
		if(isExiste)
		{
			// tester si la plage horaire est disponible a 14h 
			// si c'est la plage horaire de 14h est bonne, on insert le cours
			Calendar pause14 = dateToCalendar(cours.getDateDebut());
		    pause14.set(Calendar.HOUR, 14);
		    isExiste = cours.getCoursbyDateDebut(CalendarToDate(pause14), cours.getIdGroup());
			return isExiste;
		}
		System.out.println("La pause dejeuner est deja faite");
		return false;
	}
	
	/**
    Verifie qu'il y a assez de place dans la salle
    @param idGroupe, le groupe dont on verifie le nombre de personne dans le groupe, le nombre de personnes du cours a rajouter.
    @return boolean, si la salle est trop petite retourne faux, sinon retourne vrai
    */
	private static boolean verifieNbPlacesSalle(Long idGroupe, Cours cours) throws SQLException{

		Groupe groupe = Groupe.getGroupebyId(idGroupe);
		if(groupe.getNbPlaces() <= cours.getNbPlacesSalle())
			return true;
		System.out.println("Le nombre de place dans la salle n'est pas suffisante");
		return false;
	}
	
	/**
    Verifie le nombre d'heure dans la semaine,
    requete qui liste le nombre de cours dans la semaine, et verifie le nombre de cours dans la semaine et ajoute le nombre d'heure du cours
    @param cours, le cours a inserer.
    @return boolean, si le nombre d'heure est egale a 30h00 retourne faux, sinon retourne vrai
    */
	private static boolean verifieNbHeureSemaine(Cours cours) throws SQLException
	{
		ArrayList<Cours> listCours = cours.getCoursbyIdSemaine(cours.getNumSemaine(), cours.getIdGroup());
		return (listCours.size() < 15) ;
	}
	
	
	/**
    fonction principale qui verifie le cours a inserer, avec toute les sous fonctions.
    verifie les fonction un par un. si toute les fonctions sont bonnes retourner vrai,
    @param cours, le cours a inserer.
    @return boolean, si toute les sous fonctions sont bonnes retourner vrai, sinon retourner faux.
    */
	public static boolean verifieCoursAInsrer(Cours cours) throws SQLException
	{
		if(verifieCreneau(cours)){
			if(verifieDureeCours(cours)){
				if(nbHeureMaxJour(cours.getIdGroup(),cours)){
					if(verifiePauseDej(cours.getIdGroup(), cours)){
						if(verifieNbHeureSemaine(cours))
							return verifieNbPlacesSalle(cours.getIdGroup(), cours);
					}
				}
			}
		}
		return false;
	}

}

