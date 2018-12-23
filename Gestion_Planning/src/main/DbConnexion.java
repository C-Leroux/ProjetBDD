package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnexion {
	
	// connection a la base de donnee 
    private String url = "jdbc:mysql://localhost:3306/tmp";
    private String utilisateur = "root";
    private String motDePasse = "mysql";

    private String requete = null;
    private Connection connexion = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    /**
    Constructeur 
    	Verifie si le driver MySQL est bien present dans le projet. 
    	Renvoie une exception sinon.
    @param Requete Requete a executer 
    @return Instance DbConnexion permettant de traiter la requête passée en parametre.
    */
	
	public DbConnexion(String requete) throws ClassNotFoundException {
		this.requete = requete;
		
	    try {
	    	// Verification de l'existance du driver JDBC
	        Class.forName("com.mysql.jdbc.Driver");
	    } 
	    catch (ClassNotFoundException e) {
	    	System.out.println(e.getMessage());
	        e.printStackTrace();
	        throw e;
	    } 
	}
	
	/** 
	Récupère une connexion vers la base de donnees en utilisant le driver JDBC.
    @return Instance de la connexion vers la base de donnees.
    */

    private Connection getDbConnexion() throws SQLException {
    	return DriverManager.getConnection( this.url, this.utilisateur, this.motDePasse );
    }

    /**
    Execute la requete passe en parametre du constructeur.
    @return Resultset Retourne les donnees de la requete a traiter.
    */
    
	public ResultSet executerRequete() throws SQLException {
		Connection connexion = getDbConnexion();
		this.st = connexion.createStatement();
		this.rs = st.executeQuery(requete);		
		return rs;
	}
	
   /**
    Fait la requete
    @param 
    @returnrenvoie 0 en cas d'echec de la requete d'insertion, et 1 en cas de succes ;
    */
	public int executerInsert() throws SQLException {
		Connection connexion;
		try {
			connexion = getDbConnexion();
			this.st = connexion.createStatement();
			return this.st.executeUpdate(this.requete);
 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}
	
	public ResultSet getResultSet() {
		return this.rs;
	}

	/**
	 * Ferme les instances des objets et la connexion permettant d'executer la requete.
	 */
	public void fermerConnexion() {
		try {
			this.rs.close();
			this.st.close();			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la requete a la base de donnee " + e.getMessage());
	    } finally {
			if (this.connexion != null) {
	            try {
	                this.connexion.close();
	            } catch (SQLException ignore) {
	            }				
			}
	    }
	}
}
