package src;

import java.sql.*;

public class login_logic {
    private controller cont=null;
	public login_logic(controller controller) {
		cont=controller;
	}
	public String login(String username, String password) {
		String ret="";
		String errorString="non è stato possibile eseguire il login";
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
			Statement stat=conn.createStatement();
			)
		{
			ResultSet result = stat.executeQuery("SELECT matricola FROM \"Utente\" WHERE username='"+username+"' AND password='"+password+"'");
			if(result != null && !result.next()) {
				errorString="non esiste un utente con le credenziali date, assicurasi dell'assenza di errori o registrarsi";
				throw new SQLException();
			}else{
				ret=result.getString(1);	
			}
            conn.close();
        }catch(SQLException e){
			cont.error(errorString);
    	}
		return ret;
	}
	
	public void register(login info) {
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
				Statement stat=conn.createStatement();)
		{
			stat.executeUpdate("INSERT INTO \"Utente\" VALUES('"+info.username+"','"+info.matricola+"','"+info.password+"')");
            conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			cont.error("non è stato possibile eseguire la registrazione");
		}
	}
	
	public void deleteUser(String matr) {
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
				)
			{
			    Statement stat=conn.createStatement();
			    stat.executeUpdate("DELETE FROM \"Utente\" WHERE matricola='"+matr+"'");

	            conn.close();
	        }catch(SQLException e){
	        	cont.error("è stato impossibile cancellare questa offerta");
	    	}
	}
	
}

	