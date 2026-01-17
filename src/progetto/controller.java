package progetto;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.*;
public class controller {
	public static String url/*=sostituire con l'url del database*/;
	public static String user/*=sostituire con l'user del database*/;
	public static String pass/*=sostituire con la password*/;
	private login tmplog=null;
	private login_logic temp=null;
	private manipolazioneDB man=null;
	private dataManipulation dat=null;
	public void progetto() {
		tmplog=new login(this, new getImage("image/logo/logo.png").pic);
		temp=new login_logic(this);
	}
	public void do_login(login info) {
		String tmp =temp.login(info.username, info.password);
		if(tmp!="") {
			tmplog.dispose();
			new utente(this,tmp, info.username);
			man = new manipolazioneDB(this);
			dat = new dataManipulation(this);
		}
	}
	public void do_register(login info) {
		temp.register(info);
	}
	
	public void error(String message) {
		new error_window(message);
	}
	
	public String[] makeCatalogue(String matr, String wantobj,String wanttype) {
		catalogue_logic tmp =new catalogue_logic();
		return tmp.make(matr, wantobj, wanttype);
	}
	
	public String[] makeInventory(String matr) {
		catalogue_logic tmp =new catalogue_logic();
		return tmp.inventory(matr);
	}
	
	public String[] getinfo(int index){
		object_logic tmp=new object_logic();
		return tmp.getinfo(index, this);
	}
	
	public String[] getoffersind(String matr, boolean isvendor) {
		offer_ids tmp=new offer_ids();
		return tmp.getofferids(matr, isvendor);
	}
	
	public String[] getofferinfo(int index) {
		offer_logic tmp=new offer_logic();
		return tmp.getofferinfo(index);
	}
	
	public void evokewindow(int type, String matricola) {
		switch(type) {
		case 1:
			new window(matricola, this);
			break;
		case 2:
			new window_offer(matricola.split(";") , this);
			break;
		case 3:
			window_confirmation window3=new window_confirmation(0);
			window3.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
			JDialog test3=new JDialog(window3, true);
			test3.setLocation(-window3.getLocation().x, -window3.getLocation().y);
			test3.setVisible(true);
			
			if(window3.ret==2) {
				deleteinser(Integer.parseInt(matricola));
			}
			
			test3.dispose();
			break;
		case 4:
			window_confirmation window4=new window_confirmation(1);
			window4.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
			JDialog test4=new JDialog(window4, true);
			test4.setLocation(-window4.getLocation().x, -window4.getLocation().y);
			test4.setVisible(true);

			if(window4.ret==2) {
				deleteoffer(Integer.parseInt(matricola));
			}
			
			test4.dispose();
			break;
		case 5:
			window_confirmation window5=new window_confirmation(2);
			window5.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
			JDialog test5=new JDialog(window5, true);
			test5.setLocation(-window5.getLocation().x, -window5.getLocation().y);
			test5.setVisible(true);
			
			if(window5.ret>=1) {
			updateoffer(Integer.parseInt(matricola), window5.ret==2);
			}
			
			test5.dispose();
			break;
		
		}
		
	}
	
	public void createinser(String info) {
		man.creainserzione(info);
	}
	
	public void deleteinser(int index) {
		man.cancellainserzione(index);
	}
	
	public void createoffer(String[] info) {
		man.creaofferta(info);
	}
	
	public void updateoffer(int index, boolean conferma) {
		man.aggiornaofferta(index, conferma);
	}
	
	public void deleteoffer(int index) {
		man.cancellaofferta(index);
	}
	
	public String[] getDataAsVendor(String matr) {
		return dat.organizeDataAsVendor(matr);
	}
	
	public String[] getDataAsBuyer(String matr) {
		return dat.organizeDataAsBuyer(matr);
	}
	
	public String getSoldData(String matr) {
		return dat.organizeSoldData(matr);
	}
	
	
}
