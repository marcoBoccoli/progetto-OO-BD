package test.src;
//classe che fa da messaggion di errore con stringa passata che verrà mostrata nella finestra

import java.awt.Dimension;

import javax.swing.*;
public class error_window extends JFrame {
	public error_window(String message) {
		super("errore");
		JPanel temp=new JPanel();
		temp.setLayout(new BoxLayout(temp, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(30,0)));
		temp.add(new JLabel(message));
		add(temp);
		setSize(600,500);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}