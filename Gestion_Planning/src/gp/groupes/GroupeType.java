package gp.groupes;

public enum GroupeType {
	TD ("TD"),
	ELECTIF ("Electif"),
	LANGUE ("Langue");
	
	private String name = "";

	GroupeType(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}

}
