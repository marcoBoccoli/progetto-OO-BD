package src;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class recensione extends JFrame{
	public recensione(String index, controller cont) {
		super("Recensioni");
		setLayout(new BorderLayout());
		String[] review=cont.getReviews(index);
		
		JPanel list= new JPanel();
		list.setLayout(new BoxLayout(list,BoxLayout.Y_AXIS));
		if(!review[0].contains("vuoto")) {
			for(String i:review) {
				makespace(list,i);
			}
		}else {
			JLabel warning= new JLabel("non esistono recensioni per questo utente");
			warning.setAlignmentX(CENTER_ALIGNMENT);
			list.add(warning);
		}

		list.setVisible(true);
		JScrollPane sp =new JScrollPane(list);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setVisible(true);
		
		add(sp);
		setSize(500,400);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
	}
	
	private void makespace(JPanel panel,String message) {
		System.out.println(message);
		JPanel rec= new JPanel();
		rec.setLayout(new BoxLayout(rec,BoxLayout.Y_AXIS));
		JTextArea elem= new JTextArea(message);
		elem.setBorder(new JTextField().getBorder());
		elem.setMinimumSize(new Dimension(10000,70));
		elem.setMaximumSize(new Dimension(10000,70));

		elem.setVisible(true);
		rec.add(elem);
		rec.add(Box.createRigidArea(new Dimension(0,10)));
		rec.setVisible(true);
		panel.add(rec);
	}
}
