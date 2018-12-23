package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// https://examples.javacodegeeks.com/core-java/sql/jdbc-connection-pool-example/
public class DbConnexion {
    private String url = "jdbc:mysql://localhost:3306/tmp";
    private String utilisateur = "root";
    private String motDePasse = "mysql";

    private String requete = null;
    private Connection connexion = null;
    private Statement st = null;
    private ResultSet rs = null;
    
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

    private Connection getDbConnexion() throws SQLException {
    	return DriverManager.getConnection( this.url, this.utilisateur, this.motDePasse );
    }

	public ResultSet executerRequete() throws SQLException {
		Connection connexion = getDbConnexion();
		this.st = connexion.createStatement();
		this.rs = st.executeQuery(requete);		
		return rs;
	}
	
	//renvoie 0 en cas d'echec de la requete d'insertion, et 1 en cas de succes ;
	public int executerInsert() throws SQLException {
		Connection connexion;
		try {
			connexion = getDbConnexion();
			this.st = connexion.createStatement();
			System.out.println(this.requete);
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
	
/*	public void doSomethingWithRs() throws SQLException {
		while (rs.next()) {
		    long matricule = rs.getLong("matricule");
		    String nom = rs.getString("nom");
		    String prenom = rs.getString("prenom");
		    System.out.println(nom + " " + prenom);
		}
	}
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
