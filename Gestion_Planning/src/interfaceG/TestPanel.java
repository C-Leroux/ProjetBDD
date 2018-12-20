package interfaceG;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestPanel extends JPanel {
	
	public TestPanel() {
		super(new GridBagLayout());
		createPlanning();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		
		for (int i = 0 ; i <= 11 ; ++i)
		{
			Component comp = getComponent(1, i);
			if (comp == null)
				continue;
			
			if (i == 0)
				g.drawLine(0, comp.getY(), getWidth(), comp.getY());
			g.drawLine(0, comp.getY() + comp.getHeight(), getWidth(), comp.getY() + comp.getHeight());
		}
		
		for (int i = 0 ; i <= 7 ; ++i)
		{
			Component comp = getComponent(i, 1);
			if (i == 0)
				g.drawLine(comp.getX(), 0, comp.getX(), getHeight());
			g.drawLine(comp.getX() + comp.getWidth(), 0, comp.getX() + comp.getWidth(), getHeight());
		}
	}
	
	public void createPlanning() {
		String[] jours = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };
		
		for (int i = 0 ; i < 7 ; ++i)
		{
			JLabel label = new JLabel(jours[i]);
			add(label, new GridBagConstraints(i, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 10, 0, 10), 0, 0));
			
			for (int j = 0 ; j < 11 ; ++j)
				add (new JPanel(), new GridBagConstraints(j, i, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 0, 0), 0, 0));
		}
		revalidate();
		repaint();
	}
	
	private Component getComponent(int colonne, int ligne) {
		for(Component component : getComponents()) {
			GridBagConstraints gbc = ((GridBagLayout)getLayout()).getConstraints(component);
			if ( gbc.gridx==colonne && gbc.gridy==ligne ) {
				return component;
			}
		}
		return null;
	}

}
