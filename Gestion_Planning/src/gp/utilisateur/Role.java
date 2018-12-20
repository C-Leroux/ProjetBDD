package gp.utilisateur;

public enum Role {
	ETUDIANT ("etudiant"),
	PROFESSEUR ("Professeur"),
	ADMINISTRATEUR ("Admisnistrateur");
	
	private String name = "";

	Role(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
