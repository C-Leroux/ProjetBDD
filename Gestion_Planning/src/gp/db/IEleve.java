package gp.db;

import java.util.ArrayList;
import java.util.Date;

import gp.cours.Cours;
import gp.utilisateur.Eleve;

public interface IEleve {
	public Eleve creerEleve(String nom, String prenom, String login, Date dateNaissance, String paysNaissance, String etablissementPrecedent
    		, Date inscriptionDate, char sex, String villeNaissance, String numMaison, String numMobile, String photo, Long idGroupe,
    		String mdp, String email) throws ClassNotFoundException;
	public void getEleve();
	public ArrayList<Cours> getCoursByIdSemaine(int idSemaine);
	

}

