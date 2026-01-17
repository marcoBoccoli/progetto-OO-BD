package src;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class window_confirmation extends JFrame{
	public int ret=1;
	public window_confirmation(int isdelete) {
		super("");
		String tmp=null;
		String tmp2="Conferma";
		switch (isdelete) {
			case 0:
				tmp="si è sicuri di voler cancellare l'inserzione?";
				break;
			case 1:
				tmp="si è sicuri di voler cancellare l'offerta?";
				break;
			case 2:
				tmp="si è sicuri di voler accettare l'offerta?";
				tmp2="Accetta offerta";
				break;
			default:
				tmp="sicuro di voler continuare?";
				break;
		}
		
		setLayout(new BorderLayout());
		
		JPanel inserimenti=new JPanel();
		inserimenti.setLayout(new BoxLayout(inserimenti, BoxLayout.Y_AXIS));
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		JLabel title=new JLabel(tmp);
		title.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(title);
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,15)));
		JPanel opzioni=new JPanel();
		JButton annulla= new JButton("Annulla");
		opzioni.add(annulla);
		opzioni.add(Box.createRigidArea(new Dimension(10,0)));
		JButton rifiuta= new JButton("Rifiuta offerta");
		opzioni.add(rifiuta);
		rifiuta.setVisible(isdelete==2);
		opzioni.add(Box.createRigidArea(new Dimension(10,0)));
		JButton accetta= new JButton(tmp2);
		opzioni.add(accetta);
		opzioni.setAlignmentX(CENTER_ALIGNMENT);
		opzioni.setVisible(true);
		
		
		
		inserimenti.add(opzioni);
		add(inserimenti, BorderLayout.CENTER);
		
		setSize(600,500);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        annulla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ret=0;
			    dispose();
			}
		});
        
        rifiuta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
                dispose();
			}
		});
        
        accetta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ret=2;
			    dispose();
			}
		});
	}
}
