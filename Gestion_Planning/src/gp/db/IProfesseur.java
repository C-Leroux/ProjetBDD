package gp.db;

import gp.utilisateur.Professeur;

public interface IProfesseur {

	public Professeur creerProfesseur(String nom, String prenom, String login
    		, String numMaison, String numMobile, String mdp, String email, String rue, String codePostal, String ville);
	public void getProfesseur();
}