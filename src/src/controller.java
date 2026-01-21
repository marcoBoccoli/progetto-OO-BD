package src;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.*;
public class controller {
	public static String url="jdbc:postgresql://localhost:5432/UNINA Swap"/*=sostituire con l'url del database*/;
	public static String _user="postgres"/*=sostituire con l'user del database*/;
	public static String pass="Bionicle008"/*=sostituire con la password*/;
	private login log=null;
	private login_logic log_logic=null;
	private utente user=null;
	private manipolazioneDB man=null;
	private dataManipulation dat=null;
	private catalogue_logic cat = null;
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
		log=new login(this, new getImage("src\\src\\logo.png").pic);
		log_logic=new login_logic(this);
	}
	public void do_login(login info) {
		String tmp =log_logic.login(info.username, info.password);
		if(tmp!="") {
			man = new manipolazioneDB(this);
			dat = new dataManipulation();
			cat =new catalogue_logic(this);
			user= new utente(this,tmp, info.username);
			log.dispose();
		}
	}
	public void do_register(login info) {
		log_logic.register(info);
	}
	
	public void error(String message) {
		new error_window(message);
	}
	
	public String[] makeCatalogue(String matr, String wantobj,String wanttype) {
		return cat.make(matr, wantobj, wanttype);
	}
	
	public String[] makeInventory(String matr) {
		return cat.inventory(matr);
	}
	
	public String[] getinfo(int index){
		return cat.getinfo(index);
	}
	
	public String[] getoffersind(String matr, boolean isvendor) {
		offer_logic tmp=new offer_logic();
		return tmp.getofferids(matr, isvendor);
	}
	
	public String[] getofferinfo(int index) {
		offer_logic tmp=new offer_logic();
		return tmp.getofferinfo(index);
	}
	
	public void evokewindow(int type, String info) {
		switch(type) {
		case 1:
			new window(info, this);
			break;
		case 2:
			new window_offer(true,info, this);
			break;
		case 3:
			if(confirm(0)==2) {
				deleteinser(Integer.parseInt(info));
			}
			break;
		case 4:
			if(confirm(1)==2) {
				deleteoffer(Integer.parseInt(info));
			}
			break;
		case 5:
			int ret = confirm(2);
			if(ret>=1) {
				updateoffer(Integer.parseInt(info), ret==2);
			}
			break;
		case 6:
			String temp1=man.getmatr(info);
			new window_offer(false,temp1+";"+info+";"+temp1, this);
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
	
	private int confirm(int opt) {
		window_confirmation window=new window_confirmation(opt);
		window.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		JDialog test=new JDialog(window, true);
		test.setLocation(-window.getLocation().x, -window.getLocation().y);
		test.setVisible(true);
		
		test.dispose();
		return window.ret;
	}

	
	public void recensire(String[] info) {
		man.aggiungiRecensione(info);
	}
	
	public void showrev(String info) {
		new recensione(info, this);
	}
	
	public String[] getReviews(String index) {
		return man.showreviews(index);
	}
}
