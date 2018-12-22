package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import gp.groupes.Groupe;
import gp.groupes.GroupeType;
import gp.utilisateur.Eleve;

public class Main {
	
	public static void main(String[] args) {
		try {

			/*Groupe groupe = new Groupe("TD A", GroupeType.TD, 30 , "L3 NEW 2021");
			Groupe groupe1 = groupe.creerGroupe("TD A", GroupeType.TD, 30 , "L3 NEW 2021");
			
			
			 java.util.Date d1 = new java.util.Date();
			 java.sql.Date d2 = new java.sql.Date(d1.getTime());
			 
			Eleve eleve = new Eleve("moutte", "camille", "20180005", d1 ,"Vietnam","EPITA"
		    		, d1, 'F', "Hochiminh", " ", "0698844194", "", 1L,
		    		"Camille", "mouttecam@gmail.com");
			eleve.creerEleve("moutte", "camille", "20180005", d2 ,"Vietnam","EPITA"
		    		, d2, 'F', "Hochiminh", " ", "0698844194", "", 1L,
		    		"Camille", "mouttecam@gmail.com");*/
			
			
			
			
			DbConnexion db = new DbConnexion("SELECT * FROM utilisateur;");
			ResultSet rs = db.executerRequete();
			while (rs.next()) {
			    long matricule = rs.getLong("matricule");
			    String nom = rs.getString("nom");
			    String prenom = rs.getString("prenom");
			    System.out.println(matricule + " " + nom + " " + prenom);
			}
			db.fermerConnexion();
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver de la base de donnee non trouve");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Erreur SQL");
			e.printStackTrace();
		}
	}

}
