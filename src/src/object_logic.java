package src;

import java.sql.*;

public class object_logic {
	public String[] getinfo(int index, controller cont) {
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
