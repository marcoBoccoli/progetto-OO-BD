package test.src;

import java.sql.*;

public class catalogue_logic {

	public String[] make(String matr, String wantobj,String wanttype) {
		String tmp;
		String ret[]= {"vuoto"};
		String runFunction = "{ ? = CALL \"getInserIdforCatalogue\"( ? , ? , ? )}";
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
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
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
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
	
	
}
