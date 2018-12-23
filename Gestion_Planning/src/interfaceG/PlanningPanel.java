package interfaceG;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gp.cours.Cours;
import gp.utilisateur.Utilisateur;

public class PlanningPanel extends JPanel {
	
	Utilisateur utilisateur;
	Box liste_jours[];
	
	public PlanningPanel(Utilisateur utilisateur) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.utilisateur = utilisateur;
		liste_jours = new Box[6];
		for (int i = 0 ; i < 6 ; ++i)
		{
			liste_jours[i] = Box.createVerticalBox();
			liste_jours[i].setAlignmentY(TOP_ALIGNMENT);
			add(liste_jours[i]);
			add(Box.createRigidArea(new Dimension(50,0)));
		}
		
		creerPlanning();
	}
	
	public void creerPlanning() {
		String[] jours = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi" };
		
		for (int i = 0 ; i < 6 ; ++i)
		{
			JLabel label = new JLabel(jours[i]);
			liste_jours[i].add(label);
			
			/*for (int j = 0 ; j < 11 ; ++j)
				add (new JPanel(), new GridBagConstraints(j, i, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 0, 0), 0, 0));*/
		}
		
		creerCours();
		
		revalidate();
		repaint();
	}
	
	public void creerCours() {
		Calendar calendar = new GregorianCalendar();
		ArrayList<Cours> cours;
		try {
			//calendar.getWeekYear()
			cours = this.utilisateur.getCoursByIdSemaine(1);
			for (Cours c : cours)
				ajoutCours(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void ajoutCours(Cours c) {
		int jour = c.getJour();
		
		JLabel nomC = new JLabel(c.getNom());
		JLabel heureC = new JLabel(c.getHeure());
		
		liste_jours[jour].add(Box.createRigidArea(new Dimension(0,20)));
		liste_jours[jour].add(nomC);
		liste_jours[jour].add(heureC);
	}

}
