package src;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.*;
public class controller {
	public static String url/*=sostituire con l'url del database*/;
	public static String _user/*=sostituire con l'user del database*/;
	public static String pass/*= sostituire con la password*/;
	private login log=null;
	private login_logic log_logic=null;
	private utente user=null;
	private manipolazioneDB man=null;
	private dataManipulation dat=null;
	public void showSettings(String matr) {
		new settings(matr,this);
	}
	
	public void deleteUser(String matr) {
		if(confirm(4)==2) {
			log_logic.deleteUser(matr);
			user.dispose();
		}
	}
	
	public void restart() {
		if(confirm(4)==2) {
			log=null;
			log_logic=null;
			user.dispose();
			user=null;
			start();
		}
	}
	
	public void start() {
		log=new login(this, new getImage("image/logo/logo.png").pic);
		log_logic=new login_logic(this);
	}
	public void do_login(login info) {
		String tmp =log_logic.login(info.username, info.password);
		if(tmp!="") {
			log.dispose();
			user= new utente(this,tmp, info.username);
			if(man==null) {
				man = new manipolazioneDB(this);
			}
			if(dat==null) {
				dat = new dataManipulation();
			}
		}
	}
	public void do_register(login info) {
		log_logic.register(info);
	}
	
	public void error(String message) {
		new error_window(message);
	}
	
	public String[] makeCatalogue(String matr, String wantobj,String wanttype) {
		catalogue_logic tmp =new catalogue_logic();
		return tmp.make(matr, wantobj, wanttype, this);
	}
	
	public String[] makeInventory(String matr) {
		catalogue_logic tmp =new catalogue_logic();
		return tmp.inventory(matr, this);
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
			if(confirm(0)==2) {
				deleteinser(Integer.parseInt(matricola));
			}
			break;
		case 4:
			if(confirm(1)==2) {
				deleteoffer(Integer.parseInt(matricola));
			}
			break;
		case 5:
			int ret = confirm(2);
			if(ret>=1) {
				updateoffer(Integer.parseInt(matricola), ret==2);
			}
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
	
	public String getBoughtData(String matr) {
		return dat.organizeBoughtData(matr);
	}
	
	private int confirm(int opt) {
		window_confirmation window=new window_confirmation(opt);
		window.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		JDialog test=new JDialog(window, true);
		test.setLocation(-window.getLocation().x, -window.getLocation().y);
		test.setVisible(true);
		
		test.dispose();
		return window.ret;
	}
}
