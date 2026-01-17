package src;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class offerta extends JPanel {
	
	public offerta(String index, controller cont, boolean isvendor) {
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		String[] tmp=cont.getofferinfo(Integer.valueOf(index));
		
		JPanel comp_vend=new JPanel();
		comp_vend.setLayout(new BoxLayout(comp_vend,BoxLayout.X_AXIS));
		comp_vend.add(new JLabel(tmp[3]));
		comp_vend.add(Box.createRigidArea(new Dimension(1,0)));
		comp_vend.add(Box.createHorizontalGlue());
		comp_vend.add(new JLabel(tmp[4]));
		add(comp_vend);
		comp_vend.setVisible(true);
		
		JPanel ogg_stat=new JPanel();
		ogg_stat.setLayout(new BoxLayout(ogg_stat,BoxLayout.X_AXIS));
		ogg_stat.add(new JLabel(tmp[0]));
		ogg_stat.add(Box.createRigidArea(new Dimension(1,0)));
		ogg_stat.add(Box.createHorizontalGlue());
		ogg_stat.add(new JLabel(tmp[1]));
		add(ogg_stat);
		ogg_stat.setVisible(true);
		
		JPanel message=new JPanel();
		message.setLayout(new BoxLayout(message,BoxLayout.X_AXIS));
		
		JTextArea descfield=new JTextArea();
		descfield.setText(tmp[2]);
		descfield.setColumns(10);
		descfield.setRows(1);
		descfield.setLineWrap(true);
		descfield.setWrapStyleWord(true);
		descfield.setBorder(new JTextField().getBorder());
		descfield.setVisible(true);
		JScrollPane sp= new JScrollPane(descfield);
		sp.setMaximumSize(new Dimension(5000,50));
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
		message.add(sp);
		message.add(Box.createHorizontalGlue());
		message.add(Box.createRigidArea(new Dimension(10,0)));
		
		String aaa="offerta già rifiutata";
		if(isvendor) {
			if(tmp[tmp.length-1].contains("true")) {
				aaa="offerta già accettata";
			}
		}else {
			aaa="in attesa";
			if(tmp[tmp.length-1].contains("false")) {
				aaa="rifiutata";
			}else if(tmp[tmp.length-1].contains("true")) {
				aaa="accettata";
			}
		}

		JLabel stato=new JLabel(aaa);
		message.add(stato);
		message.add(Box.createRigidArea(new Dimension(10,0)));
		
		JButton accettazione= new JButton("Sei interessato?");
		accettazione.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cont.evokewindow(5, index);
				repaint();
				revalidate();
			}
		});
		System.out.println(tmp[tmp.length-1]);
	    accettazione.setVisible(isvendor && tmp[tmp.length-2].contains("attesa"));
		message.add(accettazione);
		
		JButton cancellazione= new JButton("cancella offerta");
		cancellazione.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cont.evokewindow(4, index);
				repaint();
				revalidate();
			}
		});
		cancellazione.setVisible(!isvendor && aaa=="in attesa");
		message.add(cancellazione);
	    
		add(message);
		message.setVisible(true);
		
		setVisible(true);
	}
}
