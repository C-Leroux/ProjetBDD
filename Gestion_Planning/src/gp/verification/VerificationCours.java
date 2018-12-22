package gp.verification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import gp.cours.*;
import gp.groupes.Groupe;


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
	
	// Les cours sont prevus du lundi au samedi de 8h00 � 20h00 en creneaux de 2heures
	private static boolean verifieCreneau(Cours cours)
	{
		Calendar dateDebut = dateToCalendar(cours.getDateDebut());
		if(dateDebut.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			System.out.println("Dimanche pas de cours");
			return false;
		}
	
		if(dateDebut.get(Calendar.HOUR_OF_DAY) >= 8 && dateDebut.get(Calendar.HOUR_OF_DAY) <= 20)
			return true;
		//System.out.println(dateDebut.get(Calendar.HOUR_OF_DAY));
		
		Calendar dateFin = dateToCalendar(cours.getDateFin());
		if(dateFin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			System.out.println("Dimanche pas de cours");
			return false;
		}
		if(dateFin.get(Calendar.HOUR_OF_DAY) >= 8 && dateFin.get(Calendar.HOUR_OF_DAY) <= 20)
			return true;
		if(dateFin.get(Calendar.DAY_OF_WEEK) == dateFin.get(Calendar.DAY_OF_WEEK))
			return true;
		
		return false;
		
	}
	
	
	private boolean verifieDureeCours(Cours cours){
		Calendar dateDebut = dateToCalendar(cours.getDateDebut());
		Calendar dateFin = dateToCalendar(cours.getDateFin());
		int nbHeure = dateFin.HOUR_OF_DAY - dateDebut.HOUR_OF_DAY;
		if(nbHeure > 0 || nbHeure % 2 == 0)
			return true;
		return false;
	}
	
	private int calculDureeCours(Cours cours){
		Calendar dateDebut = dateToCalendar(cours.getDateDebut());
		Calendar dateFin = dateToCalendar(cours.getDateFin());
		return dateFin.HOUR_OF_DAY - dateDebut.HOUR_OF_DAY;
	}
	
	private boolean nbHeureMaxJour(Long idGroupe, Cours cours){
		
		ArrayList<Cours> listCour = cours.getCoursbyIdDate(cours.getDateDebut(), idGroupe);
		int count = 0;
		for(int i = 0; i < listCour.size();i++)
		{
			count += calculDureeCours(listCour.get(i));
		}
		count += calculDureeCours(cours);
		if(count < 8)
			return true;
		//affiche qu'il y a trop d'horaire dans une journ�e sinon
		return false;
	}
	
	private boolean verifiePauseDej(Long idGroupe, Cours cours){
		//  selectionne les cours qui d�bute a 12h00 et � 14h00, met � un si yaau moins un cours dedans b

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
		return false;
	}
	
	private boolean verifieNbPlacesSalle(Long idGroupe, Cours cours){

		Groupe groupe = Groupe.getGroupebyId(idGroupe);
		if(groupe.getNbPlaces() <= cours.getNbPlacesSalle())
			return true;
		return false;
	}
	
	private boolean verifieNbHeureSemaine(Cours cours)
	{
		ArrayList<Cours> listCours = cours.getCoursbyIdSemaine(cours.getNumSemaine(), cours.getIdGroup());
		return (listCours.size() < 15) ;
	}
	
	
	public boolean verifieCoursAInsrer(Cours cours)
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
	
	/*public static void main(String[] args) {
		Date date = new Date();
		//Cours cours = new Cours(date, date, 2, 1);
		//verifieCreneau(cours);
	}*/

}

