package gp.db;

import java.util.ArrayList;

import gp.utilisateur.Contact;

public interface IContact {
	public Contact creeContact(String nom, String prenom, String adresse, String telephone, String email, Long matricule);
	public void supprimerContact();
	public ArrayList<Contact> getContact(Long matricule);
	public Long getId();
}
