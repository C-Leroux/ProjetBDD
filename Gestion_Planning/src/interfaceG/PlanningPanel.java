package interfaceG;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlanningPanel extends JPanel {
	
	public PlanningPanel() {
		super(new GridBagLayout());
		creerPlanning();
	}
	
	public void creerPlanning() {
		String[] jours = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi" };
		
		for (int i = 1 ; i <= 6 ; ++i)
		{
			JLabel label = new JLabel(jours[i - 1]);
			add(label, new GridBagConstraints(i, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 10, 0, 10), 0, 0));
			
			for (int j = 0 ; j < 11 ; ++j)
				add (new JPanel(), new GridBagConstraints(j, i, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 0, 0), 0, 0));
		}
		revalidate();
		repaint();
	}
	
/*	public void ajoutCours(Cours c) {
		int jour = c.getJour();
	}
*/
}
