package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dataManipulation {
	public String[] organizeDataAsVendor(String matr) {
		String ret[]= {"",""};
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
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
				ret[0] = ret[0].substring(0, ret[0].length()-1);
				ret[1] = ret[1].substring(0, ret[1].length()-1);
				
	            conn.close();
	        }catch(Exception e){
	    	}
		return ret;
	}

	public String[] organizeDataAsBuyer(String matr) {
		String ret[]= {"",""};
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
				Statement stat=conn.createStatement();
				)
			{
				ResultSet result = stat.executeQuery("SELECT ins.tipo_inserzione, COUNT(ins.id_inserzione) \r\n"
					+ "FROM \"Offerta\" offer INNER JOIN \"Inserzione\" ins ON offer.id_inserzione=ins.id_inserzione \r\n"
					+ "WHERE matricola_compratore='"+matr+"'\r\n"
					+ "GROUP BY  ins.tipo_inserzione;");
				while(result.next())
				{
					String temp=null;
					switch(result.getString(1)) {
						case "venduto":
							temp="vendita";
							break;
						case "scambiato":
							temp="scambio";
							break;
						case "regalato":
							temp="regalo";
							break;
						default:
							temp = result.getString(1);
							break;
					}
					ret[0]= ret[0] + temp + ";";
					ret[1]= ret[1] + result.getInt(2) + ";";
				}
				ret[0] = ret[0].substring(0, ret[0].length()-1);
				ret[1] = ret[1].substring(0, ret[1].length()-1);
				
	            conn.close();
	        }catch(Exception e){
	    	}
		return ret;
	}
	
	public String organizeSoldData(String matr) {
		String ret= "";
		try (Connection conn = DriverManager.getConnection(controller.url, controller._user, controller.pass);
				Statement stat=conn.createStatement();
				)
			{

			    ResultSet result = stat.executeQuery("SELECT MIN(CAST(ins.costo AS integer )), AVG(CAST(ins.costo AS integer )), MAX(CAST(ins.costo AS integer ))\r\n"
			    		+ "FROM \"Offerta\" offer INNER JOIN \"Inserzione\" ins ON offer.id_inserzione=ins.id_inserzione \r\n"
			    		+ "WHERE matricola_venditore='"+matr+"' AND ins.tipo_inserzione='venduto' AND conferma=true;");
				result.next();
				ret= ret + result.getInt(1) + ";" + result.getInt(2) + ";" + result.getInt(3);
				
	            conn.close();
	        }catch(Exception e){
	    	}
		return ret;
	}
}
