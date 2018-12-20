package gp.salles;

public enum RoomType {
	AMPHI ("Amphi"),
	COURS ("Cours"),
	LABO ("Labo"),
	SOUTENANCE ("SalleSoutenance");
	
	private String name = "";

	RoomType(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}

}
