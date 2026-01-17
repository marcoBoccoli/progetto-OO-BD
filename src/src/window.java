package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class window extends JFrame{
	private String categoria;
	private String tipoinser;
	
	public window(String matricola, controller cont) {
		super("");
		
		setLayout(new BorderLayout());
		
		JPanel inserimenti=new JPanel();
		inserimenti.setLayout(new BoxLayout(inserimenti, BoxLayout.Y_AXIS));
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		JLabel title=new JLabel("inserire informazioni per la creazione di un'inserzione");
		title.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(title);
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		
		JPanel name =new JPanel();
		name.setLayout(new BoxLayout(name,BoxLayout.X_AXIS));
		
		name.add(Box.createRigidArea(new Dimension(10,0)));
		name.add(new JLabel("nome oggetto (30 caratteri mx):"));
		name.add(Box.createHorizontalGlue());
		JTextField namefield=new JTextField(30);
		namefield.setText("");
		namefield.setMaximumSize(new Dimension(100,25));
		name.add(namefield);
		name.add(Box.createRigidArea(new Dimension(10,0)));
		inserimenti.add(name);
		
		JPanel cost =new JPanel();
		cost.setLayout(new BoxLayout(cost,BoxLayout.X_AXIS));
		
		cost.add(Box.createRigidArea(new Dimension(10,0)));
		cost.add(new JLabel("costo (200 caratteri mx):"));
		cost.add(Box.createHorizontalGlue());
		JTextField costfield=new JTextField(30);
		costfield.setText("");
		costfield.setMaximumSize(new Dimension(100,25));
		cost.add(costfield);
		cost.add(Box.createRigidArea(new Dimension(10,0)));
		
		cost.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(cost);
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,10)));
		
		String s1[] = {"","libro di testo","elettronica","abbigliamento","strumenti musicali","hobby","altro"};
		JComboBox<String> categ= new JComboBox<String>(s1);
		categ.setMaximumSize(new Dimension(115,50));
        String s2[] = { "","vendita","scambio","regalo"};
        JComboBox<String> tipo= new JComboBox<String>(s2);
        tipo.setMaximumSize(new Dimension(100,50));
        
        categ.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				categoria=(String)categ.getSelectedItem();
				if(categoria=="")
					categoria=null;
			}
		});
       
        tipo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tipoinser=(String)tipo.getSelectedItem();
				if(tipoinser=="")
					tipoinser=null;
			}
		});
		
		JPanel cat_tip =new JPanel();
		cat_tip.setLayout(new BoxLayout(cat_tip,BoxLayout.X_AXIS));
		
		cat_tip.add(new JLabel("categoria:"));
		cat_tip.add(Box.createRigidArea(new Dimension(10,0)));
		categ.setMaximumSize(new Dimension(125,25));
		cat_tip.add(categ);
		
		cat_tip.add(Box.createRigidArea(new Dimension(50,0)));
		
		cat_tip.add(new JLabel("metodo pagamento:"));
		cat_tip.add(Box.createRigidArea(new Dimension(10,0)));
		tipo.setMaximumSize(new Dimension(125,25));
		cat_tip.add(tipo);
		
		cat_tip.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(cat_tip);
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,10)));
		
		JPanel conse =new JPanel();
		conse.setLayout(new BoxLayout(conse,BoxLayout.X_AXIS));
		
		conse.add(Box.createRigidArea(new Dimension(10,0)));
		conse.add(new JLabel("luogo e ora di consegna (200 caratteri max):"));
		conse.add(Box.createHorizontalGlue());
		JTextField delifield=new JTextField(30);
		delifield.setText("");
		delifield.setMaximumSize(new Dimension(500,25));
		conse.add(delifield);
		conse.add(Box.createRigidArea(new Dimension(10,0)));
		
		conse.setAlignmentX(CENTER_ALIGNMENT);
		inserimenti.add(conse);
		
		inserimenti.add(Box.createRigidArea(new Dimension(0,10)));
		
		JPanel desc =new JPanel();
		desc.setLayout(new BoxLayout(desc,BoxLayout.X_AXIS));
		
		desc.add(Box.createRigidArea(new Dimension(10,0)));
		desc.add(new JLabel("descrizione (2000 caratteri max):"));
		desc.add(Box.createRigidArea(new Dimension(10,0)));
		JTextArea descfield=new JTextArea();
		descfield.setText("");
		descfield.setColumns(10);
		descfield.setRows(1);
		descfield.setLineWrap(true);
		descfield.setWrapStyleWord(true);
		descfield.setBorder(new JTextField().getBorder());
		descfield.setVisible(true);
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
		inserimenti.add(Box.createRigidArea(new Dimension(0,20)));
		
		add(inserimenti, BorderLayout.CENTER);
		
		setSize(600,500);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
		conferma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    if(tipoinser!=null && tipoinser.contains("regalo")) {
			    	costfield.setText("0");
			    }
			    String temporaneo=namefield.getText()+";"+costfield.getText()+";"+categoria+";"
			    		+tipoinser+";"+delifield.getText()+";"+descfield.getText()+";"+matricola;
				if(!((";"+temporaneo+";").contains(";;"))) {
					cont.createinser(temporaneo);
					dispose();
				}
			}
		});
	}
}
