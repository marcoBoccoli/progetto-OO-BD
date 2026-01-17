package test.src;

import java.sql.*;

public class login_logic {
	private String username;
	private int identifier; 
    private controller cont=null;
	public login_logic(controller controller) {
		cont=controller;
	}
	public String login(String username, String password) {
		String ret="";
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UNINA Swap", "postgres", controller.pass);
			Statement stat=conn.createStatement();
			)
		{
			ResultSet result = stat.executeQuery("SELECT matricola FROM \"Utente\" WHERE username='"+username+"' AND password='"+password+"'");
			if(result != null && !result.next()) {
				cont.error("non esiste un utente con le credenziali date, assicurasi dell'assenza di errori o registrarsi  ppppppp");
			}else{
				ret=result.getString(1);	
			}
            //callableStatement.registerOutParameter(1, Types.VARCHAR);
			//System.out.println(result.getString(1));
            conn.close();
        }catch(SQLException e){
        	 System.out.println(e.getMessage());
    	}
		return ret;
	}
	
	public void register(login info) {
		String runFunction = "{ ? = CALL getinserinfo( ? ) }";
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UNINA Swap", "postgres", controller.pass);
				Statement stat=conn.createStatement();
				)
		{
			ResultSet result = stat.executeQuery("INSERT INTO \"Utente\" VALUES('"+info.username+"','"+info.matricola+"','"+info.password+"')");
            //System.out.println(result);
            conn.close();
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	}
	

	}
}

	