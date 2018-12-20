package gp.utilisateur;

import gp.db.IAdministrateur;


public class Administrateur extends Utilisateur implements IAdministrateur {
	
	public Administrateur (String nom, String prenom, String login
    		, String numMaison, String numMobile, String mdp, String email, String rue, String codePostal, String ville)
    {
    	this.matricule++;
    	this.nom = nom;
    	this.prenom = prenom;
    	this.login = login;
    	this.mdp = mdp;
    	this.role = Role.ADMINISTRATEUR;
    	this.numMaison = numMaison;
    	this.numMobile = numMobile;
    	this.email = email;
    	this.rue = rue;
    	this.codePostal = codePostal;
    	this.ville = ville;
    }
	public Administrateur creerAdministrateur(String nom, String prenom, String login
    		, String numMaison, String numMobile, String mdp, String email, String rue, String codePostal, String ville) {
		Administrateur admin = new Administrateur(nom, prenom, login, numMaison, numMobile, mdp, email, rue, codePostal, ville);
		Long id = admin.getMatricule();
		// rajouter des null entre les champs manquqnt
		String value = "INSERT INTO RESPONSABLE VALUES( '"+ id + "','" + nom + "','" + prenom + "','" + mdp + "','" + login +  "','" + admin.getRole() +  "','" + null +  "','" + null +  "','" + null +  "','" + null +  "','" + null +  "','" + null +  "','" + numMaison + "','" + numMobile + "','" + email + "','" + rue + "','" + null + "');";
		int status = 0;
		// status = statement.executeUpdate(value);
		if(status == 0)
		{
			// msg d'erreur l'element n'a pas pu etre inserer dans a base 
			return null;
		}
		else {
			return admin;
		}
	}
	public void sauvegarderAdministrateur() {}
	
	public void getEdminiastrateur() {}

}
