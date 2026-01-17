package src;

import java.sql.*;

public class catalogue_logic {
	private controller cont;
	public catalogue_logic(controller controller) {
		cont=controller;
	}

	public String[] make(String matr, String wantobj,String wanttype) {
		String tmp;
		String ret[]= {"vuoto"};
		String runFunction = "{ ? = CALL \"getInserIdforCatalogue\"( ? , ? , ? )}";
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
				CallableStatement stat=conn.prepareCall(runFunction);
				)
			{
				stat.registerOutParameter(1, Types.VARCHAR);
				stat.setString(2, matr);
				stat.setString(3, wantobj);
				stat.setString(4, wanttype);
				
				stat.executeUpdate();
				tmp = stat.getString(1);
	            if(tmp.contains("vuoto")) {
	            	return ret;
	            }
				ret = tmp.split("-");
				
	            conn.close();
	        }catch(SQLException e){
		        	e.getCause();
		        	e.printStackTrace();
		        	System.out.println("eeee");
	        }
		return ret;
	}
	
	public String[] inventory(String matr) {
		String tmp;
		String ret[]= {"vuoto"};
		String runFunction = "{ ? = CALL \"getInserIdforInventory\"( ? )}";
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
				CallableStatement stat=conn.prepareCall(runFunction);
				)
			{
				stat.registerOutParameter(1, Types.VARCHAR);
				stat.setString(2, matr);
				
				stat.executeUpdate();
				tmp = stat.getString(1);
				
	            if(tmp.contains("vuoto")) {
	            	return ret;
	            }
	            
				ret = tmp.split("-");

	            conn.close();
	        }catch(SQLException e){
	        	e.getCause();
	        	e.printStackTrace();
	        	System.out.println("eeee");
	    	}
		return ret;
	}
	
	public String[] getinfo(int index) {
		String tmp;
		String ret[]= {"vuoto"};
		String runFunction = "{ ? = CALL \"getinserinfo\"( ? )}";
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
				CallableStatement stat=conn.prepareCall(runFunction);
				Statement stat2=conn.createStatement();
				)
			{
				stat.registerOutParameter(1, Types.VARCHAR);
				stat.setInt(2, index);

				stat.executeUpdate();

	            tmp = stat.getString(1);
	            if(tmp.contains("vuoto")) {
	            	return ret;
	            }
	            
	            tmp= tmp + ";"+ index ;
	            ret = tmp.split(";");
	    		
	            ResultSet result = stat2.executeQuery("SELECT username FROM \"Utente\" WHERE matricola='"+ret[ret.length-2]+"'");
				if(result != null && !result.next()) {
					ret[ret.length-2]=result.getString(1);
				}
	            
	            conn.close();
	        }catch(SQLException e){
	        	cont.error("non esiste ");
	        	e.getCause();
	        	e.printStackTrace();
	    	}
		return ret;
	}
}
