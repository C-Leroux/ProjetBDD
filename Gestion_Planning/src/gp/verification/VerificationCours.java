package gp.verification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import gp.cours.*;
import gp.groupes.Groupe;
import gp.utilisateur.Utilisateur;


public class VerificationCours {
	
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static Date CalendarToDate(Calendar calendar){
		Date date =  calendar.getTime();
        return date;
	}
	
	// Les cours sont prevus du lundi au samedi de 8h00 a 20h00 en creneaux de 2heures
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
		//System.out.println(dateDebut.get(Calendar.HOUR_OF_DAY));
		

		
	}
	
	
	private static boolean verifieDureeCours(Cours cours){
		Calendar dateDebut = dateToCalendar(cours.getDateDebut());
		Calendar dateFin = dateToCalendar(cours.getDateFin());
		int nbHeure = dateFin.HOUR_OF_DAY - dateDebut.HOUR_OF_DAY;
		if(nbHeure > 0 || nbHeure % 2 == 0)
			return true;
		System.out.println("Le cours n'a pas une plage horaire multiple de 2h00");
		return false;
	}
	
	private static int calculDureeCours(Cours cours){
		Calendar dateDebut = dateToCalendar(cours.getDateDebut());
		Calendar dateFin = dateToCalendar(cours.getDateFin());
		return dateFin.HOUR_OF_DAY - dateDebut.HOUR_OF_DAY;
	}
	
	private static boolean nbHeureMaxJour(Long idGroupe, Cours cours){
		
		ArrayList<Cours> listCour = cours.getCoursbyIdDate(cours.getDateDebut(), idGroupe);
		int count = 0;
		for(int i = 0; i < listCour.size();i++)
		{
			count += calculDureeCours(listCour.get(i));
		}
		count += calculDureeCours(cours);
		if(count < 8)
			return true;
		//affiche qu'il y a trop d'horaire dans une journee sinon
		System.out.println("le nombre d'heure par jour est dejà maximal");
		return false;
	}
	
	private static boolean verifiePauseDej(Long idGroupe, Cours cours){
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
	
	private boolean verifieNbPlacesSalle(Long idGroupe, Cours cours){

		Groupe groupe = Groupe.getGroupebyId(idGroupe);
		if(groupe.getNbPlaces() <= cours.getNbPlacesSalle())
			return true;
		System.out.println("Le nombre de place dans la salle n'est pas suffisante");
		return false;
	}
	
	private static boolean verifieNbHeureSemaine(Cours cours)
	{
		ArrayList<Cours> listCours = cours.getCoursbyIdSemaine(cours.getNumSemaine(), cours.getIdGroup());
		return (listCours.size() < 15) ;
	}
	
	
	public static boolean verifieCoursAInsrer(Cours cours)
	{
		if(verifieCreneau(cours)){
			if(verifieDureeCours(cours)){
				if(nbHeureMaxJour(cours.getIdGroup(),cours)){
					if(verifiePauseDej(cours.getIdGroup(), cours)){
						return verifieNbHeureSemaine(cours);
					}
				}
			}
		}
		return false;
	}

}

