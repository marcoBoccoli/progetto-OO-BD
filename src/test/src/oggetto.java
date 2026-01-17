package test.src;
//classe che compone l'elemento che fa da inserzione da selezionare nella lista nella finestra utente
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class oggetto extends JPanel {

	private JButton accettazione;
	private String categoria;
	//private String desc;
	private String tipo_annuncio;
	private String consegna;
	
	public oggetto(String index, controller cont, boolean inv, String matr) {
		String[] tmp=cont.getinfo(Integer.valueOf(index));
		if(!tmp[0].contains("vuoto")) {
			setLayout(new BorderLayout());
		
			setBackground(Color.LIGHT_GRAY);
			JPanel temp= new JPanel();
			temp.setLayout(new BoxLayout(temp,BoxLayout.Y_AXIS));
			temp.setBackground(Color.LIGHT_GRAY);
			
			JPanel nome_tipo=new JPanel();
			nome_tipo.setLayout(new BoxLayout(nome_tipo,BoxLayout.X_AXIS));
			
			nome_tipo.add(new JLabel(tmp[0]));
			
			nome_tipo.setBackground(Color.LIGHT_GRAY);
			nome_tipo.add(Box.createRigidArea(new Dimension(1,0)));
			nome_tipo.add(Box.createHorizontalGlue());
			
			nome_tipo.add(new JLabel(tmp[3]));
			temp.add(nome_tipo);
			
			temp.add(Box.createRigidArea(new Dimension(0,2)));
			
			JPanel categoria_consegna=new JPanel();
			categoria_consegna.setLayout(new BoxLayout(categoria_consegna,BoxLayout.X_AXIS));

			categoria_consegna.add(new JLabel(tmp[1]));
			
			categoria_consegna.setBackground(Color.LIGHT_GRAY);
			categoria_consegna.add(Box.createRigidArea(new Dimension(1,0)));
			categoria_consegna.add(Box.createHorizontalGlue());
			
			categoria_consegna.add(new JLabel(tmp[4]));	
			
			temp.add(categoria_consegna);			
			temp.add(Box.createRigidArea(new Dimension(0,2)));

			JTextArea descfield=new JTextArea();
			descfield.setText(tmp[2]);
			descfield.setColumns(10);
			descfield.setRows(1);
			descfield.setLineWrap(true);
			descfield.setWrapStyleWord(true);
			descfield.setBorder(new JTextField().getBorder());
			descfield.setVisible(true);
			JScrollPane sp= new JScrollPane(descfield);
			sp.setMaximumSize(new Dimension(10000,50));
			sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			
			temp.add(Box.createRigidArea(new Dimension(10,5)));
			if(inv) {
				accettazione= new JButton("Manda offerta");
				temp.add(accettazione);
				accettazione.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cont.evokewindow(2, index+";"+matr+";"+tmp[3]);
					}
				});
				
				JPanel pulsant= new JPanel();
				pulsant.setLayout(new BoxLayout(pulsant,BoxLayout.X_AXIS));
				
				pulsant.add(sp);
				pulsant.add(Box.createHorizontalGlue());
				Integer.valueOf(index);
				pulsant.add(new JLabel(tmp[5]));
				pulsant.add(Box.createRigidArea(new Dimension(10,0)));
				pulsant.add(accettazione);
				pulsant.setBackground(Color.LIGHT_GRAY);
				
				temp.add(pulsant);
			}else {
				JButton canc=new JButton("cancella inserzione");
				canc.setAlignmentX(CENTER_ALIGNMENT);
				canc.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cont.evokewindow(3, tmp[tmp.length-1]);
						
					}
				});
				temp.add(canc);
			}

			add(temp);
			setMinimumSize(new Dimension(400,200));
			setMaximumSize(new Dimension(40000,100));
			setVisible(true);
		}
	}
}