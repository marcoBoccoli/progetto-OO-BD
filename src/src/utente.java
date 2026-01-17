package src;
//classe che fa da pagina home per l'utente

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class utente extends JFrame {
	private String matr;
	private String[] filtered =new String[2];
	private String[] indici;
	private int in=0;
	private int times=1;
	private controller cont;
	public utente(controller controller, String matricola, String nome) {
		super("Unina Swap/pagina utente");
		matr = matricola;
		cont = controller;
		setLayout(new BorderLayout());
		JPanel page= new JPanel();
		page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));
		JPanel info_utente=new JPanel(); 
		info_utente.setLayout(new BoxLayout(info_utente, BoxLayout.X_AXIS));

		info_utente.add(Box.createRigidArea(new Dimension(1,0)));
		info_utente.add(Box.createRigidArea(new Dimension(5,0)));
		info_utente.add(new JLabel(nome));
		
		info_utente.setMaximumSize(new Dimension(100000,20));
		info_utente.add(Box.createRigidArea(new Dimension(1,0)));
		info_utente.add(Box.createHorizontalGlue());
		JButton impostazioni = new JButton("impostazioni");
		info_utente.add(impostazioni);
		
		impostazioni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cont.showSettings(matr);
			}
		});
		
		info_utente.setBorder(new JTextField().getBorder());
		page.add(info_utente);
//funzionalità fa semplicemente da sfondo definendo l'area in cui tutte le operazioni che avvengono tramite la 
//barra pulsanti e all'interno delle pagine di ogni pulsante
		JPanel funzionalità=new JPanel();
		funzionalità.setLayout(new BoxLayout(funzionalità, BoxLayout.X_AXIS));
		funzionalità.setPreferredSize(new Dimension(10000,30));
		
//pulsanti per le 4 sezioni utilizando la classe barra_pulsanti	
		JButton barraPuls[]= new JButton[4];
		barraPuls[0]= new JButton("inventario");
		barraPuls[1]= new JButton("inserzioni disponibili");
		barraPuls[2]= new JButton("storico offerte");
		barraPuls[3]= new JButton("informazioni");
		
		JPanel funzionalità5=new JPanel();//per il pulsante 'informazioni'
		funzionalità5.setVisible(false);
		funzionalità5.add(Box.createRigidArea(new Dimension(50,0)));
		funzionalità5.setLayout(new BoxLayout(funzionalità5, BoxLayout.Y_AXIS));
		
		JPanel funzionalità4=new JPanel();//per il pulsante 'storico offerte'
		funzionalità4.setVisible(false);
		funzionalità4.add(Box.createRigidArea(new Dimension(50,0)));
		funzionalità4.setLayout(new BoxLayout(funzionalità4, BoxLayout.Y_AXIS));
		
		JPanel funzionalità3=new JPanel();//per il pulsante 'inventario'
		funzionalità3.setVisible(false);
		funzionalità3.add(Box.createRigidArea(new Dimension(50,0)));
		funzionalità3.setLayout(new BoxLayout(funzionalità3, BoxLayout.Y_AXIS));
		
		JPanel funzionalità2=new JPanel(); // per il catalogo
		catalogue(funzionalità2);		
		
		funzionalità.add(barraPuls[0]);
		funzionalità.add(barraPuls[1]);
		funzionalità.add(barraPuls[2]);
		funzionalità.add(barraPuls[3]);
		
		barraPuls[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				in=0;
				funzionalità2.setVisible(false);

				funzionalità4.removeAll();
				funzionalità4.repaint();

				in=0;
				times=1;
				funzionalità3.removeAll();
				funzionalità3.repaint();
				inventory(funzionalità3);
				funzionalità3.revalidate();
				
				funzionalità5.removeAll();
				funzionalità5.repaint();
			}
		});
		barraPuls[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				funzionalità3.removeAll();
				funzionalità3.repaint();
				
				funzionalità4.removeAll();
				funzionalità4.repaint();

				in=0;
				times=1;
				funzionalità2.removeAll();
				funzionalità2.repaint();
				catalogue(funzionalità2);
				funzionalità2.revalidate();
				
				funzionalità5.removeAll();
				funzionalità5.repaint();


			}
		});
		barraPuls[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				funzionalità4.removeAll();
				funzionalità4.repaint();
				offers(funzionalità4);
				funzionalità4.revalidate();

				funzionalità3.removeAll();
				funzionalità3.repaint();
				
				funzionalità2.setVisible(false);
				
				funzionalità5.removeAll();
				funzionalità5.repaint();
			}
		});
		barraPuls[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				funzionalità3.removeAll();
				funzionalità3.repaint();

				funzionalità4.removeAll();
				funzionalità4.repaint();
				
				funzionalità2.setVisible(false);

				funzionalità5.removeAll();
				funzionalità5.repaint();
				graph(funzionalità5);
				funzionalità5.revalidate();
			}
		});
        
		page.add(funzionalità);
		
		page.add(funzionalità3);
		
		page.add(funzionalità4);
		offers(funzionalità4);
		funzionalità4.setVisible(false);
		
		page.add(funzionalità2);
		
		page.add(funzionalità5);
		
		add(page,BorderLayout.CENTER);
	
		setSize(800,700);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void fillcatalogue(JPanel inserzioni, boolean inv) {
		boolean continua=true;
		if(!indici[0].contains("vuoto")) {
			while( in<10*times && continua) {
				if(in>=indici.length) {
					continua=false;
				}else {			
					inserzioni.add(new oggetto(indici[in], cont, inv, matr));
					in++;
				}
			}
		}
	}
	
	private void filloffers(JPanel offerte, boolean inv) {
		for (String i:indici) {
			offerte.add(new offerta(i, cont, inv));
		}
	}
	
	private void graph(JPanel info){
		JButton opzioni[]= new JButton[3];
		opzioni[0]= new JButton("statistiche offerte accetate");
		opzioni[1]= new JButton("statistiche offerte mandate");
		opzioni[2]= new JButton("statistiche oggetti comprati");
		
		info.add(Box.createRigidArea(new Dimension(0,5)));
		
		JPanel pulsanti= new JPanel();
		pulsanti.setLayout(new BoxLayout(pulsanti, BoxLayout.X_AXIS));
		pulsanti.add(opzioni[0]);
		pulsanti.add(opzioni[1]);
		pulsanti.add(opzioni[2]);
		pulsanti.setVisible(true);
		info.add(pulsanti);
		
		String res[]= cont.getDataAsVendor(matr);
		JPanel acc_pan = new JPanel(); 
		acc_pan.setLayout(new BoxLayout(acc_pan, BoxLayout.Y_AXIS));
		DefaultPieDataset objDataset = new DefaultPieDataset();
		if(res[0]!="") {
			int sum1=0;
			String tmp1[]=res[0].split(";");
			String tmp2[]=res[1].split(";");
			for(int i=0;i<tmp1.length;i++){
				sum1+=Integer.valueOf(tmp2[i]);
				objDataset.setValue(tmp1[i]+":"+tmp2[i],Integer.valueOf(tmp2[i]));
			}
			objDataset.setValue("Totale offerte accettate:"+sum1, 0);
		}else {
			JLabel warn = new JLabel("non avete accettato offerte");
			warn.setAlignmentX(CENTER_ALIGNMENT);
			warn.setVisible(true);
			acc_pan.add(warn,CENTER_ALIGNMENT);
		}
		
		JFreeChart objChart1 = ChartFactory.createPieChart (
				"Grafico delle offerte accettate", objDataset, false, true, false);
		objChart1.setBackgroundPaint(this.getBackground());
		ChartPanel accettate = new ChartPanel(objChart1);
		accettate.setVisible(true);
		acc_pan.add(accettate,CENTER_ALIGNMENT);
		acc_pan.setVisible(true);
        info.add(acc_pan,CENTER_ALIGNMENT);
		
        
		JPanel man_pan = new JPanel(); 
		man_pan.setLayout(new BoxLayout(man_pan, BoxLayout.Y_AXIS));
		int sum2=0;
		res=cont.getDataAsBuyer(matr);
		if(res[0]!="") {
			objDataset = new DefaultPieDataset();
			String tmp1[]=res[0].split(";");
			String tmp2[]=res[1].split(";");
			for(int i=0;i<tmp1.length;i++){
				sum2+=Integer.valueOf(tmp2[i]);
				objDataset.setValue(tmp1[i]+":"+tmp2[i],Integer.valueOf(tmp2[i]));
			}
			objDataset.setValue("Totale offerte mandate:"+sum2, 0);
		}else {
			JLabel warn = new JLabel("non avete mandato offerte");
			warn.setAlignmentX(CENTER_ALIGNMENT);
			warn.setVisible(true);
			man_pan.add(warn,CENTER_ALIGNMENT);
		}
		JFreeChart objChart2 = ChartFactory.createPieChart (
				"Grafico delle offerte mandate", objDataset, false, true, false);
		objChart2.setBackgroundPaint(this.getBackground());
		ChartPanel mandate = new ChartPanel(objChart2);
		mandate.setVisible(true);
		man_pan.add(mandate,CENTER_ALIGNMENT);
		man_pan.setVisible(false);
		info.add(man_pan,CENTER_ALIGNMENT);
		
		JPanel ven_pan = new JPanel(); 
		ven_pan.setLayout(new BoxLayout(ven_pan, BoxLayout.Y_AXIS));
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		String tmp1[]=cont.getSoldData(matr).split(";");
		if(tmp1[0]!="" && !tmp1[2].contains("0")) {
		    dataset.addValue( Integer.valueOf(tmp1[0]) , "minimo" , "" );
		    dataset.addValue( Integer.valueOf(tmp1[1]) , "media" , "");
		    dataset.addValue( Integer.valueOf(tmp1[2]) , "massimo" , "" );
		}else {
			JLabel warn = new JLabel("non avete accettato offerte di vendita");
			warn.setAlignmentX(CENTER_ALIGNMENT);
			warn.setVisible(true);
			ven_pan.add(warn,CENTER_ALIGNMENT);
		}
		JFreeChart barChart = ChartFactory.createBarChart("statistiche guadagno",           
				"", "Euro", dataset, PlotOrientation.VERTICAL, true, true, false);
		barChart.setBackgroundPaint(this.getBackground());
		ChartPanel guadagno = new ChartPanel(barChart);
	    guadagno.setVisible(true);
	    ven_pan.add(guadagno,CENTER_ALIGNMENT);
	    ven_pan.setVisible(false);
	    info.add(ven_pan,CENTER_ALIGNMENT);
	    
		info.setVisible(true);
		
		opzioni[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				in=0;
				acc_pan.setVisible(true);
				man_pan.setVisible(false);
				ven_pan.setVisible(false);
			}
		});
		opzioni[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acc_pan.setVisible(false);
				man_pan.setVisible(true);
				ven_pan.setVisible(false);
			}
		});
		opzioni[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acc_pan.setVisible(false);
				man_pan.setVisible(false);
				ven_pan.setVisible(true);
			}
		});
	}
	
	private void offers(JPanel offers) {
		times=1;
		JButton opzioni[]= new JButton[3];
		opzioni[0]= new JButton("offerte mandate");
		opzioni[1]= new JButton("offerte ricevute");
		
		JPanel mandate= new JPanel();
		mandate.setLayout(new BoxLayout(mandate, BoxLayout.Y_AXIS));
		
		JPanel ricevute = new JPanel(); 
		ricevute.setLayout(new BoxLayout(ricevute, BoxLayout.Y_AXIS));
		
		indici=cont.getoffersind(matr, false);
		if(!indici[0].contains("vuoto")) {
			filloffers(mandate,false);
			in=0;
		}
		
		indici=cont.getoffersind(matr, true);
		if(!indici[0].contains("vuoto")) {
			filloffers(ricevute,true);
			in=0;
		}
		mandate.revalidate();
		offers.add(Box.createRigidArea(new Dimension(0,3)));
		
		JPanel pulsanti= new JPanel();
		pulsanti.setLayout(new BoxLayout(pulsanti, BoxLayout.X_AXIS));
		pulsanti.add(opzioni[0]);
		pulsanti.add(opzioni[1]);
		pulsanti.setVisible(true);
		offers.add(pulsanti);
		
		ricevute.setVisible(false);
		offers.add(ricevute);
		
		mandate.setVisible(true);
		offers.add(mandate);		
		
		offers.setVisible(true);
		opzioni[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				in=0;
				mandate.setVisible(true);
				ricevute.setVisible(false);
			}
		});
		opzioni[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mandate.setVisible(false);
				ricevute.setVisible(true);
			}
		});
	}
	
	private void inventory(JPanel inventory) {
		times=1;
		JButton addinser=new JButton("crea inserzione");
		addinser.setAlignmentX(CENTER_ALIGNMENT);
		addinser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cont.evokewindow(1, matr);
			}
		});
		indici=cont.makeInventory(matr);
		JPanel inserzioni = new JPanel();
		inserzioni.setLayout(new BoxLayout(inserzioni, BoxLayout.Y_AXIS));
		fillcatalogue(inserzioni, false);

		JScrollPane sp =new JScrollPane(inserzioni);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		inventory.add(sp);
		inventory.add(addinser);
		
		in=0;

		JButton carica_altro=new JButton("carica altro");
		carica_altro.setAlignmentX(CENTER_ALIGNMENT);
		inventory.add(carica_altro);
		carica_altro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inventory.setVisible(false);
				inventory.setAutoscrolls(false);
				if(10*times<indici.length) {
					times++;
					fillcatalogue(inserzioni, false);
				}
				inventory.setVisible(true);				
			}
		});

		inventory.setVisible(true);
	}
	
	
	private void catalogue(JPanel catalogue) {
		JPanel filter=new JPanel();
		JPanel inserzioni= new JPanel();
		
		filter.setLayout(new BoxLayout(filter, BoxLayout.X_AXIS));
		filter.setPreferredSize(new Dimension(10,50));
		String s1[] = {"","libro di testo","elettronica","abbigliamento","strumenti musicali","hobby","altro"};
		JComboBox<String> filtroobj= new JComboBox<String>(s1);
		filtroobj.setMaximumSize(new Dimension(115,50));
        String s2[] = { "","vendita","scambio","regalo"};
        JComboBox<String> filtrotype= new JComboBox<String>(s2);
        filtrotype.setMaximumSize(new Dimension(100,50));
        
        filtroobj.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				filtered[0]=(String)filtroobj.getSelectedItem();
				if(filtered[0]=="")
					filtered[0]=null;
			}
		});
        filtrotype.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				filtered[1]=(String)filtrotype.getSelectedItem();
				if(filtered[1]=="")
					filtered[1]=null;
			}
		});

        filter.add(filtroobj);
        filter.add(filtrotype);
        JButton search= new JButton("cerca");
		filter.add(search);
		inserzioni.setLayout(new BoxLayout(inserzioni, BoxLayout.Y_AXIS));
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indici=cont.makeCatalogue(matr, filtered[0], filtered[1]);
				inserzioni.removeAll();
				if(!indici[0].contains("vuoto")) {
					in=0;
					fillcatalogue(inserzioni, true);
				}
				inserzioni.repaint();
				inserzioni.revalidate();
		}});
		
		catalogue.setLayout(new BoxLayout(catalogue, BoxLayout.Y_AXIS));
		
		indici=cont.makeCatalogue(matr, filtered[0], filtered[1]);
		int tmp = indici.length;
		in=0;
		
		fillcatalogue(inserzioni, true);
		inserzioni.revalidate();
		
		catalogue.add(filter);
		
		JScrollPane sp =new JScrollPane(inserzioni);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		catalogue.add(sp);
        catalogue.add(Box.createRigidArea(new Dimension(50,0)));
        
		JButton carica_altro=new JButton("carica altro");
		carica_altro.setAlignmentX(CENTER_ALIGNMENT);
		catalogue.add(carica_altro);
		carica_altro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				catalogue.setVisible(false);
				catalogue.setAutoscrolls(false);
				if(10*times<tmp) {
					times++;
					fillcatalogue(inserzioni, true);
				}
				catalogue.setVisible(true);				
			}
		});
        catalogue.setVisible(true);
	}
}