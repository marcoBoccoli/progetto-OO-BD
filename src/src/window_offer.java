package src;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class window_offer extends JFrame{
	
	public window_offer(String[] info, controller cont) {
		super("");
		String tmp=null;
		if(info[2].contains("vendita")) {
			tmp="si vuole offrire un altro prezzo al venditore?";
		}else if(info[2].contains("scambio")) {
			tmp="si vuole scambiare con un oggetto diverso?";
		}else {
			tmp="si vuole lasciare un messaggio al venditore?";
		}
		
		setLayout(new BorderLayout());
		
		JPanel inserimenti=new JPanel();
		inserimenti.setLayout(new BoxLayout(inserimenti, BoxLayout.Y_AXIS));
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		JLabel title=new JLabel(tmp);
		title.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(title);
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		
		
		JPanel desc =new JPanel();
		desc.setLayout(new BoxLayout(desc,BoxLayout.X_AXIS));

		desc.add(Box.createRigidArea(new Dimension(10,0)));
		JTextArea descfield=new JTextArea();

		descfield.setColumns(10);
		descfield.setRows(1);
		descfield.setText("");
		descfield.setLineWrap(true);
		descfield.setWrapStyleWord(true);
		descfield.setAutoscrolls(true);
		descfield.setBorder(new JTextField().getBorder());
		JScrollPane sp= new JScrollPane(descfield);
		sp.setMaximumSize(new Dimension(10000,100));
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		desc.add(sp);
		desc.add(Box.createRigidArea(new Dimension(10,0)));
		
		desc.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(desc);
		
		
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,15)));
		JButton conferma= new JButton("Conferma informazioni");
		conferma.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(conferma);
		
		add(inserimenti, BorderLayout.CENTER);
		
		setSize(600,500);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
		conferma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    info[2]=descfield.getText();
				if(!info[2].contentEquals("")) {
					cont.createoffer(info);
				}
				dispose();
			}
		});
        
	}
}
