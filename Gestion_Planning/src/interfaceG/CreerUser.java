package interfaceG;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CreerUser extends JFrame implements ActionListener{
		 
		public CreerUser () {
			super ();
			build ();
		}
		
		private void build () {
			setTitle("Creer un utilisateur");
			setSize(320,240);
			setLocationRelativeTo(null);
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 
			this.setContentPane(buildContentPane());
			this.setVisible(true);
		}
		
		private JPanel buildContentPane(){
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(2,0));
			
			// Nom de l'utilisateur
			JLabel nom = new JLabel("Nom");
			panel.add(nom);
			JTextField nomF = new JTextField();
			panel.add(nomF);
			
			// Prenom de l'utilisateur
			JLabel prenom = new JLabel("Prenom");
			panel.add(prenom);
			JTextField prenomF = new JTextField();
			panel.add(prenomF);
			
			// Login de l'utilisateur
			JLabel login = new JLabel("Login");
			panel.add(login);
			JTextField loginF = new JTextField();
			panel.add(loginF);
			
			// Mdp de l'utilisateur
			JLabel mdp = new JLabel("Mot de passe");
			panel.add(mdp);
			JPasswordField mdpF = new JPasswordField();
			panel.add(mdpF);
			
			// Date de naissance de l'utilisateur
			
			// Ville de naissance de l'utilisateur
			JLabel ville_nais = new JLabel("Ville de naissance");
			panel.add(ville_nais);
			JTextField ville_naisF = new JTextField();
			panel.add(ville_naisF);
			
			// Pays de naissance de l'utilisateur
			JLabel pays_nais = new JLabel("Pays de naissance");
			panel.add(pays_nais);
			JTextField pays_naisF = new JTextField();
			panel.add(pays_naisF);
			
			// Genre de l'utilisateur
			JLabel genre = new JLabel("Genre");
			panel.add(genre);
			Box box_genre = Box.createHorizontalBox();
			JRadioButton masc = new JRadioButton("Masculin");
			JRadioButton femi = new JRadioButton("Feminin");
			ButtonGroup groupe = new ButtonGroup();
			groupe.add(masc);
			groupe.add(femi);
			panel.add(masc);
			panel.add(femi);
			
			// Precedent etablissement de l'utilisateur
			JLabel prec_et = new JLabel("Etablissement pr�c�dent");
			panel.add(prec_et);
			JTextField prec_etF = new JTextField();
			panel.add(prec_etF);
			
			// Photo de l'utilisateur
			
			// Rue de l'utilisateur
			JLabel rue = new JLabel("Rue");
			panel.add(rue);
			JTextField rueF = new JTextField();
			panel.add(rueF);
			
			// Code postal de l'utilisateur
			JLabel code_pos = new JLabel("Code postal");
			panel.add(code_pos);
			JTextField code_posF = new JTextField();
			panel.add(code_posF);
			
			// Ville de l'utilisateur
			JLabel ville = new JLabel("Ville");
			panel.add(ville);
			JTextField villeF = new JTextField();
			panel.add(villeF);
			
			// Num�ro de rue de l'utilisateur
			JLabel n_rue = new JLabel("Numero de rue");
			panel.add(n_rue);
			JTextField n_rueF = new JTextField();
			panel.add(n_rueF);
			
			// Num�ro de portable de l'utilisateur
			JLabel num = new JLabel("Numero de portable");
			panel.add(num);
			JTextField numF = new JTextField();
			panel.add(numF);
			
			// Email de l'utilisateur
			JLabel email = new JLabel("Adresse email");
			panel.add(email);
			JTextField emailF = new JTextField();
			panel.add(emailF);
			
			// Boutons
			JButton valider = new JButton("Valider");
			valider.addActionListener(this);
			panel.add(valider);
			
			JButton annuler = new JButton("Annuler");
			annuler.addActionListener(this);
			panel.add(annuler);
			
			return panel;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

}
