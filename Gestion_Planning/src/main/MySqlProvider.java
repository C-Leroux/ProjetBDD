package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// https://examples.javacodegeeks.com/core-java/sql/jdbc-connection-pool-example/
public class MySqlProvider {
	
	  private MySqlProvider() {}

	    private static MySqlProvider INSTANCE = null;
	     
	    public static MySqlProvider getInstance() {
		    try {
		        Class.forName("com.mysql.jdbc.Driver");
		    } 
		    catch (ClassNotFoundException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    } 
		    
	    	if (INSTANCE == null) {
	    		synchronized (MySqlProvider.class) {
	    	    	if (INSTANCE == null) {
	    	    		INSTANCE = new MySqlProvider();
	    	    	}
	    		}
	    	}
	    	
	    	return INSTANCE;
	    }

	    
	    private String url = "jdbc:mysql://localhost:3306/ru_pm";
	    private String utilisateur = "root";
	    private String motDePasse = "my_password";
//	    private Connection connexion = null;

	    public Connection getConnexion() throws SQLException {
	    	return DriverManager.getConnection( url, utilisateur, motDePasse );
// 	    	return DriverManager.getConnection("jdbc:h2:mem:test");
	    }
	    
	    /* private void createConnexion() {
		    try {
		        connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    } catch ( SQLException e ) {
		    } finally {
		        if ( connexion != null )
		            try {
		                connexion.close();
		            } catch ( SQLException ignore ) {
		            }
		    }
	    }*/
	    
	    
}
