package gp.db;

import java.util.ArrayList;

import gp.utilisateur.Contact;

public interface IContact {
	public void supprimerContact();
	public ArrayList<Contact> getContact(Long matricule);
	public Long getId();
}
