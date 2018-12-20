package gp.utilisateur;

import java.util.ArrayList;
import java.util.Date;

// protected ?
public class Utilisateur {
	protected static Long matricule = 0L;
	protected String nom = null;
	protected String prenom = null;
	protected String login = null;
	protected String mdp = null;
	protected Enum role;

	// Identity
	protected Date dateNaissance = null;
	protected String villeNaissance = null;
	protected String paysNaissance = null;
	protected String sex = null;
	protected Date inscriptionDate = null;
	protected String etablissementPrecedent = null;
	protected String photo = null;

	// Coordonnee
	protected String rue = null;
	protected String codePostal = null;
	protected String ville = null;
	protected String numMaison = null;
	protected String numMobile = null;
	protected String email = null;
	
	public Long getMatricule() {
    	return this.matricule;
    }
    public String getRole(){
    	return this.role.toString();
    }
    
    public int supprimerUtilisateur(Long matricule) {
		String value = "DELETE FROM UTILISATEUR WHERE matricule = "+ matricule.toString() + ";";
		int status = 0;
		// status = statement.executeUpdate(value);
		return status;
	}

}

