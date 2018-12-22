package interfaceG;

import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FenetreLog extends JFrame implements ActionListener{
		 
	public FenetreLog () {
		super ();
		
		build ();
	}
	
	private void build () {
		// gestion fenetre 
		setTitle("Gestion Planning"); //On donne un titre a l'application
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
		btnCo.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		FenetrePlanning planning = new FenetrePlanning();
		planning.setVisible(true);
	}
	
}
