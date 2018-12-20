package gp.db;

import java.util.ArrayList;
import java.util.Date;

import gp.cours.Cours;

public interface ICours {

	public Cours creerCours(Date debut, Date fin, Long idsalle, Long matricule, Long idGroupe, int numSemaine);
	public int supprimerCours(Long id);
	public Date getDateDebut();
	public Date getDateFin();
	public boolean getCoursbyDateDebut(Date date, Long idGroupe);
	public Long getIdSalle();
	public ArrayList<Cours> getCoursbyIdDate(Date date, Long idGroupe);
	public ArrayList<Cours> getCoursbyIdSemaine(int idSemaine, Long idGroupe);
	public int getJour(); // renvoie un int correspondant au jour de la semaine (Lundi = 0, Mardi = 1, ...)
	public String getHeure();

}
