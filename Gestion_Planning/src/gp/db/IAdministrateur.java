package gp.db;
import gp.utilisateur.Administrateur;

public interface IAdministrateur {

	public Administrateur creerAdministrateur(String nom, String prenom, String login
    		, String numMaison, String numMobile, String mdp, String email, String rue, String codePostal, String ville);
}