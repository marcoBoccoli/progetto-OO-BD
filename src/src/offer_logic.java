package src;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class offer_logic {
	public String[] getofferinfo(int index) {
		String tmp;
		String ret[]= {"vuoto"};
		String runFunction;
		runFunction = "{ ? = CALL \"getofferinfo\"( ? )}";
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
				CallableStatement stat=conn.prepareCall(runFunction);
				)
			{
				stat.registerOutParameter(1, Types.VARCHAR);
				stat.setInt(2, index);
				
				stat.executeUpdate();
				tmp = stat.getString(1);
				ret = tmp.split(";");
				
	            conn.close();
	        }catch(SQLException e){
		        	e.getCause();
		        	e.printStackTrace();
	        }
	    	
		return ret;
	}
	
	public String[] getofferids(String matr, boolean isvendor) {
		String tmp;
		String ret[]= {"vuoto"};
		String runFunction;
		if(isvendor) {
			runFunction = "{ ? = CALL \"getofferreceived\"( ? )}";
		}else {
			runFunction = "{ ? = CALL \"getoffersent\"( ? )}";
		}
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
				CallableStatement stat=conn.prepareCall(runFunction);
				)
			{
				stat.registerOutParameter(1, Types.VARCHAR);
				stat.setString(2, matr);
				
				stat.executeUpdate();
				tmp = stat.getString(1);
				ret = tmp.split("-");

	            conn.close();
	        }catch(SQLException e){
		        	e.getCause();
		        	e.printStackTrace();
	        }
		return ret;
	}
}

