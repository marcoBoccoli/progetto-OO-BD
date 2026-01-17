package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dataManipulation {
	private controller cont=null;
	public dataManipulation(controller con) {
		cont=con;
	}
	public String[] organizeDataAsVendor(String matr) {
		String ret[]= {"",""};
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
				Statement stat=conn.createStatement();
				)
			{
				ResultSet result = stat.executeQuery("SELECT ins.tipo_inserzione, COUNT(ins.id_inserzione) \r\n"
					+ "FROM \"Offerta\" offer INNER JOIN \"Inserzione\" ins ON offer.id_inserzione=ins.id_inserzione \r\n"
					+ "WHERE matricola_venditore='"+matr+"' AND conferma= true \r\n"
					+ "GROUP BY  ins.tipo_inserzione;");
				while(result.next())
				{
					ret[0]= ret[0] + result.getString(1) + ";";
					ret[1]= ret[1] + result.getInt(2) + ";";
				}
				if(ret[0].length()!=0) {
					ret[0] = ret[0].substring(0, ret[0].length()-1);
					ret[1] = ret[1].substring(0, ret[1].length()-1);
				}
				
	            conn.close();
	        }catch(SQLException e){
	        	cont.error("è stato impossibile completare l'operazione");
	        	e.getCause();
	        	e.printStackTrace();
	    	}
		return ret;
	}

	public String[] organizeDataAsBuyer(String matr) {
		String ret[]= {"",""};
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
				Statement stat=conn.createStatement();
				)
			{
				ResultSet result = stat.executeQuery("SELECT ins.tipo_inserzione, COUNT(ins.id_inserzione) \r\n"
					+ "FROM \"Offerta\" offer INNER JOIN \"Inserzione\" ins ON offer.id_inserzione=ins.id_inserzione \r\n"
					+ "WHERE matricola_compratore='"+matr+"'\r\n"
					+ "GROUP BY  ins.tipo_inserzione;");
				while(result.next())
				{
					ret[0]= ret[0] + result.getString(1) + ";";
					ret[1]= ret[1] + result.getInt(2) + ";";
				}
				if(ret[0].length()!=0) {
					ret[0] = ret[0].substring(0, ret[0].length()-1);
					ret[1] = ret[1].substring(0, ret[1].length()-1);
				}
				
	            conn.close();
	        }catch(SQLException e){
	        	cont.error("è stato impossibile completare l'operazione");
	        	e.getCause();
	        	e.printStackTrace();
	    	}
		return ret;
	}
	
	public String organizeSoldData(String matr) {
		String ret= "";
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
				Statement stat=conn.createStatement();
				)
			{

			    ResultSet result = stat.executeQuery("SELECT MIN(CAST(ins.costo AS integer )), AVG(CAST(ins.costo AS integer )), MAX(CAST(ins.costo AS integer ))\r\n"
			    		+ "FROM \"Offerta\" offer INNER JOIN \"Inserzione\" ins ON offer.id_inserzione=ins.id_inserzione \r\n"
			    		+ "WHERE matricola_compratore='"+matr+"' AND ins.tipo_inserzione='venduto';");
				if(result.next()){
					ret= ret + result.getInt(1) + ";" + result.getInt(2) + ";" + result.getInt(3);
				}
				
	            conn.close();
	        }catch(SQLException e){
	        	cont.error("è stato impossibile completare l'operazione");
	        	e.getCause();
	        	e.printStackTrace();
	    	}
		return ret;
	}
}
