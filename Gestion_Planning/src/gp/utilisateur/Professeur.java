package gp.utilisateur;


import java.util.Date;

import gp.db.IProfesseur;

public class Professeur extends Utilisateur implements IProfesseur {
	public Professeur (String nom, String prenom, String login
    		, String numMaison, String numMobile,
    		String mdp, String email, String rue, String codePostal, String ville)
    {
    	this.matricule++;
    	this.nom = nom;
    	this.prenom = prenom;
    	this.login = login;
    	this.mdp = mdp;
    	this.role = Role.PROFESSEUR;
    	this.numMaison = numMaison;
    	this.numMobile = numMobile;
    	this.email = email;
    	this.rue = rue;
    	this.codePostal = codePostal;
    	this.ville = ville;
    }
	
	public Professeur creerProfesseur(String nom, String prenom, String login
    		, String numMaison, String numMobile, String mdp, String email, String rue, String codePostal, String ville) {
		Professeur professeur = new Professeur(nom, prenom, login, numMaison, numMobile, mdp, email, rue, codePostal, ville);
		Long id = professeur.getMatricule();
		// rajouter des null entre les champs manquqnt
		String value = "INSERT INTO RESPONSABLE VALUES( '"+ id + "','" + nom + "','" + prenom + "','" + mdp + "','" + login +  "','" + professeur.getRole() +  "','" + null +  "','" + null +  "','" + null +  "','" + null +  "','" + null +  "','" + null +  "','" + numMaison + "','" + numMobile + "','" + email + "','" + rue + "','" + null + "');";
		int status = 0;
		// status = statement.executeUpdate(value);
		if(status == 0)
		{
			// msg d'erreur l'element n'a pas pu etre inserer dans a base 
			return null;
		}
		else {
			return professeur;
		}
	}
	public void sauvegarderProfesseur() {}
	public void getProfesseur() {}

}
