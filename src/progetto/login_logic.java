package progetto;

import java.sql.*;


public class login_logic {
    private controller cont=null;
	public login_logic(controller controller) {
		cont=controller;
	}
	public String login(String username, String password) {
		String ret="";
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
			Statement stat=conn.createStatement();
			)
		{
			ResultSet result = stat.executeQuery("SELECT matricola FROM \"Utente\" WHERE username='"+username+"' AND password='"+password+"'");
			if(result != null && !result.next()) {
				cont.error("non esiste un utente con le credenziali date, assicurasi dell'assenza di errori o registrarsi");
			}else{
				ret=result.getString(1);	
			}
            conn.close();
        }catch(SQLException e){
			cont.error("non è stato possibile eseguire il login");
    	}
		return ret;
	}
	
	public void register(login info) {
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
				Statement stat=conn.createStatement();)
		{
			stat.executeQuery("INSERT INTO \"Utente\" VALUES('"+info.username+"','"+info.matricola+"','"+info.password+"')");
            conn.close();
		} catch (SQLException e) {
			cont.error("non è stato possibile eseguire la registrazione");
		}
	}
}

	