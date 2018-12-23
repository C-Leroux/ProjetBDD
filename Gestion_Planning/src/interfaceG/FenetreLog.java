package interfaceG;

import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gp.cours.Cours;
import gp.utilisateur.Administrateur;
import gp.utilisateur.Eleve;
import gp.utilisateur.Professeur;
import gp.utilisateur.Role;
import gp.utilisateur.Utilisateur;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.DbConnexion;

public class FenetreLog extends JFrame {
		 
	public FenetreLog () {
		super ();
		
		build ();
	}
	
	private void build () {
		// gestion fenetre 
		setTitle("Connexion"); //On donne un titre a l'application

		 setSize(320,240); //On donne une taille a notre fenetre
		 setLocationRelativeTo(null); //On centre la fenetre sur l'ecran
		 setResizable(false); //On interdit la redimensionnement de la fenetre
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit a l'application de se fermer lors du clic sur la croix
		 
		 this.setContentPane(buildContentPane());
		 this.setVisible(true);
		 // gestion barre de menu

	}
	
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		
		JPanel pan1 = new JPanel();
		pan1.setLayout(new FlowLayout());
		JPanel pan2 = new JPanel();
		pan2.setLayout(new FlowLayout());
		JPanel pan3 = new JPanel();
		pan3.setLayout(new FlowLayout());
		JPanel pan4 = new JPanel();
		pan4.setLayout (new FlowLayout());
		
		
		JLabel lbl = new JLabel ("EFREI");
		JLabel lblLogin = new JLabel ("Login");
		JLabel lblMdp = new JLabel ("Mot de passe");
		
		JTextField txtLogin = new JTextField (25);
		//JTextField txtMdp = new JTextField (25);
		JPasswordField txtMdp = new JPasswordField(25);
		
		JButton btnCo = new JButton("Connexion");
		btnCo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        String mdp = String.valueOf(txtMdp.getPassword());
                        String username = txtLogin.getText();
                        
                        Utilisateur user = null;
                        try {
                            user = getLoginMdp(username, mdp);
                        } catch (SQLException ex) {
                            // TODO: Display Errror message
                            Logger.getLogger(FenetreLog.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (user != null) {
                            setVisible(false);
                            dispose();
                            FenetrePlanning planning = new FenetrePlanning(user);
                            planning.setVisible(true);
   
                        } else {
                            // invalid credential
                        }
                    }
                });
		pan1.add(lbl);
		pan2.add(lblLogin);
		pan2.add(txtLogin);
		pan3.add(lblMdp);
		pan3.add(txtMdp);
		pan4.add(btnCo);
		
		panel.add(pan1);
		panel.add(pan2);
		panel.add(pan3);
		panel.add(pan4);
	
		return panel;
	}
	
	
	private Utilisateur getLoginMdp(String login, String mdp) throws SQLException {
		String str = "SELECT * FROM UTILISATEUR WHERE login = '" + login
				+ "' AND mdp = '" + mdp + "';";
		DbConnexion db;
		try {
			db = new DbConnexion(str);
			ResultSet resultat = db.executerRequete();
			resultat.next();
			Long matricule = resultat.getLong("matricule");
			Long idGroupe = resultat.getLong("idGroupe");
			
	        String role = resultat.getString("role");
			Utilisateur utilisateur;
			if(role.compareTo(Role.ETUDIANT.toString()) == 0)
			{
				utilisateur = new Eleve(matricule,login,mdp,idGroupe);
			
			}
			if(role.compareTo(Role.PROFESSEUR.toString()) == 0) {
				utilisateur = new Professeur(matricule,login,mdp);
			}
			else {

				utilisateur = new Administrateur(matricule, login, mdp);
				
			}
			return utilisateur;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

		
	}
}
