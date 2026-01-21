package src;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class settings extends JFrame{
	public settings(String matr, controller cont) {
		super("Impostazioni");
		
		setLayout(new BorderLayout());
		
		JPanel opzioni=new JPanel();
		opzioni.setLayout(new BoxLayout(opzioni, BoxLayout.Y_AXIS));
		opzioni.add(Box.createRigidArea(new Dimension(0,20)));
		
		JButton esci = new JButton("esci");		
		esci.setAlignmentX(CENTER_ALIGNMENT);
		esci.setVisible(true);;
		opzioni.add(esci);
		opzioni.add(Box.createRigidArea(new Dimension(0,20)));
		
		JButton elimina = new JButton("elimina account");		
		elimina.setAlignmentX(CENTER_ALIGNMENT);
		elimina.setVisible(true);
		opzioni.add(elimina);
		
		add(opzioni);
		setSize(500,400);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
		
		esci.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cont.restart();
				dispose();
			}
		});
		elimina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cont.deleteUser(matr);
				dispose();
			}
		});
		
	}
}
