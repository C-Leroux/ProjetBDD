package gp.db;

import java.util.Date;

import gp.utilisateur.Eleve;

public interface IEleve {
	public Eleve creerEleve(String nom, String prenom, String login, Date dateNaissance, String paysNaissance, String etablissementPrecedent
    		, Date inscriptionDate, String sex, String villeNaissance, String numMaison, String numMobile, String photo, Long idGroupe, String mdp, String email);
	public void getEleve();
	

}

