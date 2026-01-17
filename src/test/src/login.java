package test.src;
//classe della pagina di login
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class login extends JFrame {
	private JButton login;
	private JButton want_to_register;
	private JButton register;
	public String username;
	public String password;
	public String matricola;
	public JTextArea errori = new JTextArea();
	
	public login(controller controller) {
		super("Unina Swap/pagina login");
		setLayout(new BorderLayout());
		
		JPanel logo=new JPanel();
		logo.setLayout(new BoxLayout(logo, BoxLayout.Y_AXIS));
		logo.add(Box.createRigidArea(new Dimension(0,20)));
		
		logo.add(Box.createRigidArea(new Dimension(0,5)));
		add(logo, BorderLayout.NORTH);
		
		JPanel inserimenti=new JPanel();
		inserimenti.setLayout(new BoxLayout(inserimenti, BoxLayout.Y_AXIS));
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		//inserimento username
		JPanel User =new JPanel();
		User.setLayout(new BoxLayout(User,BoxLayout.X_AXIS));
		User.add(new JLabel("username:"));
		User.add(Box.createRigidArea(new Dimension(10,0)));
		JTextField userfield=new JTextField(15);
		User.add(userfield);
		User.setMaximumSize(new Dimension(200,25));
		User.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(User);
		
	    inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		//inserimento password
		JPanel Pass =new JPanel();
		Pass.setLayout(new BoxLayout(Pass,BoxLayout.X_AXIS));
		Pass.add(new JLabel("password:"));
		Pass.add(Box.createRigidArea(new Dimension(11,0)));
		JTextField passfield=new JTextField(15);
		Pass.add(passfield);
		Pass.setMaximumSize(new Dimension(200,25));
		Pass.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(Pass);
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		//inserimento contatto in caso di creazione nuovo utente
		JPanel Matricola =new JPanel();
		Matricola.setLayout(new BoxLayout(Matricola,BoxLayout.X_AXIS));
		Matricola.add(new JLabel("matricola:"));
		Matricola.add(Box.createRigidArea(new Dimension(20,0)));
		JTextField matrfield=new JTextField(15);
		Matricola.add(matrfield);
		Matricola.setMaximumSize(new Dimension(200,25));
		Matricola.setVisible(false);//messo a invisibile perchè non è detto serva 
		//all'inizio, reso visibile premendo il puldante chiamato want_to_register
		Matricola.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(Matricola);
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		//pulsante che manda l'evento di registrazione, inizialmente invisibile
		register= new JButton("Registrati");
		JPanel Registrazione3 =new JPanel();
		Registrazione3.setLayout(new BoxLayout(Registrazione3,BoxLayout.X_AXIS));
		Registrazione3.add(register);
		Registrazione3.setAlignmentX(CENTER_ALIGNMENT);
		Registrazione3.setVisible(false);
		inserimenti.add(Registrazione3);
		
		//normale pulsante di login
		JPanel Registrazione =new JPanel();
		Registrazione.setLayout(new BoxLayout(Registrazione,BoxLayout.X_AXIS));
		login= new JButton("Login");
		Registrazione.add(login);
		Registrazione.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(Registrazione);
		inserimenti.add(Box.createRigidArea(new Dimension(0,5)));
		
        //sezione in cui inserire se ci sono stati errori di qualche tipo durante registrazione o login
		
		errori.setMaximumSize(new Dimension(200,25));
		errori.setVisible(false);
		inserimenti.add(errori);
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		//pulsante che rende visibile il campo di contatto e il pulsante per la registrazione
		JPanel Registrazione2 =new JPanel();
		Registrazione2.setLayout(new BoxLayout(Registrazione2,BoxLayout.X_AXIS));
		Registrazione2.add(new JLabel("Non sei registrato?"));
		want_to_register= new JButton("Registrati");
		Registrazione2.add(want_to_register);
		Registrazione2.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(Registrazione2);
		inserimenti.add(Box.createRigidArea(new Dimension(0,5)));
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
					username=userfield.getText();
					password=passfield.getText();
					controller.do_login(login.this);

			}
		});
		
		want_to_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*String input=text1.getText();
				if(!input.equals("")) {
					//etichetta.append(input);
				}*/
				Registrazione.setVisible(false);
				Registrazione2.setVisible(false);
				Registrazione3.setVisible(true);
				Matricola.setVisible(true);
				userfield.setText("");
				passfield.setText("");
				matrfield.setText("");
			}
		});
		
		register.addActionListener(new ActionListener() {
			@Override
			//////////////////////////////////////////////////////////////////////////////////////
			public void actionPerformed(ActionEvent e) {
				/*String input=text1.getText();
				if(!input.equals("")) {
					//etichetta.append(input);
				}*/
				Registrazione.setVisible(true);
				Registrazione2.setVisible(true);
				Registrazione3.setVisible(false);
				Matricola.setVisible(false);
				username=userfield.getText();
				password=passfield.getText();
				matricola=matrfield.getText();
				if(username.equals("") || password.equals("") || matricola.equals("")) {
					errori.setVisible(true);
					errori.setText("uno o più campi sono vuoti");
				}else {
					controller.do_register(login.this);
					userfield.setText("");
					passfield.setText("");
					matrfield.setText("");
				}
				
				/*
				try {
					username=userfield.getText();
					password=passfield.getText();
					matricola=matrfield.getText();
					if(username.equals("") || password.equals("") || matricola.equals("")) {
						NullPointerException ex = null;
						throw ex;
					}
					controller.do_register(login.this);
					userfield.setText("");
					passfield.setText("");
					matrfield.setText("");
				}catch(NullPointerException e1) {
					errori.setVisible(true);
					errori.setText("uno o più campi sono vuoti");
				}*/
				
			}
		});
		
		inserimenti.setAlignmentX(CENTER_ALIGNMENT);
		add(inserimenti, BorderLayout.CENTER);
		setSize(600,500);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}