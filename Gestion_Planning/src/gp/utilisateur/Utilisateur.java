package gp.utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import gp.cours.*;
import main.DbConnexion;

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
	protected char sex;
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
	
	Long idGroupe = 0L;
	
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
    
    public ArrayList<Cours> getCoursByIdSemaine(int idSemaine) throws SQLException
    {
    	System.out.println("CoUOU");
    	String str = "SELECT * FROM COURS WHERE numSemaine = " + idSemaine
				+ " AND idGroupe = " + this.idGroupe + ";";
    	DbConnexion db;
		try {
			db = new DbConnexion(str);
			ResultSet resultat = db.executerRequete();
			// traite la liste de resultat
			ArrayList<Cours> listCours = new ArrayList<Cours>();
			  while(resultat.next()){ 
				  Long id = resultat.getLong("idCours");
				  String nom = resultat.getString("nom");
				  Date debut = resultat.getDate("datedebut"); 
				  Date fin = resultat.getDate("datefin"); 
				  Long idSalle = resultat.getLong("idSalle"); 
				  Long matricule = resultat.getLong("matricule"); 
				  Long idGroupe = resultat.getLong("idGroupe"); 
				  int numSemaine = resultat.getInt("numSemaine");
			      Cours cours = new Cours(nom, debut, fin, idSalle, matricule, idGroupe, numSemaine); 
			      listCours.add(cours); 
			  }
			 
			return listCours;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

		
    }

}

