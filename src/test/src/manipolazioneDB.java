package test.src;

import java.sql.*;
public class manipolazioneDB {
	controller cont=null;
	
	public manipolazioneDB(controller tmp) {
		cont=tmp;
	}
	public void creainserzione(String dati) {
		String info[]= {"vuoto"};
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
				)
			{
			    PreparedStatement stat=conn.prepareStatement("CALL \"insertInsertion\"( ? , ? , ? , ? , ? , ? , ? )");
				
			    info = dati.split(";");
			    
			    stat.setString(1, info[0]);
			    stat.setString(2, info[2]);
			    stat.setString(3, info[5]);
			    stat.setString(4, info[3]);
			    stat.setString(5, info[4]);
			    stat.setString(6, info[1]);
			    stat.setString(7, info[6]);

			    stat.executeUpdate();

	            conn.close();
	        }catch(SQLException e){
	        	if(e.getMessage().contains("il valore è troppo lungo")) {
	        		cont.error("un valore troppo lungo è stato messo in uno dei campi");
	        	}else {
	        	cont.error("è stato impossibile creare questa offerta");
	        	}
	    	}
	}
	
	public void cancellainserzione(int index) {
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
				)
			{
			    Statement stat=conn.createStatement();
			    stat.executeUpdate("DELETE FROM \"Inserzione\" WHERE id_inserzione="+index);
	            
	            conn.close();
	        }catch(SQLException e){
	        	cont.error("è stato impossibile cancellare questa inserzione");
	    	}
	}
	
	public void creaofferta(String[] dati) {
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
				)
			{
			    PreparedStatement stat=conn.prepareStatement("CALL \"insertOffer\"( ? , ? , ? )");
			
				stat.setInt(1, Integer.valueOf(dati[0]));
				stat.setString(2, dati[2]);
				stat.setString(3, dati[1]);
	            stat.executeUpdate();

	            conn.close();
	        }catch(SQLException e){
	        	if(e.getMessage().contains("un valore chiave duplicato viola il vincolo univoco \"unicità\"")) {
	        		cont.error("avete già mandato in precedenza una offerta per questa stessa inserzione");
	        	}else {
	        	cont.error("è stato impossibile creare questa offerta");
	        	}
	    	}
	}
	
	public void aggiornaofferta(int index, boolean conferma) {
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
				)
			{
				PreparedStatement stat=conn.prepareStatement("UPDATE \"Offerta\" SET conferma= ? WHERE id_offerta= ?");
			
				stat.setBoolean(1, conferma);
				stat.setInt(2, index);
	            stat.executeUpdate();

	            conn.close();
	        }catch(SQLException e){
	        	cont.error("è stato impossibile aggiornare lo stato di questa offerta");
	    	}
	}
	
	public void cancellaofferta(int index) {
		try (Connection conn = DriverManager.getConnection(controller.url, controller.user, controller.pass);
				)
			{
			    Statement stat=conn.createStatement();
			    stat.executeUpdate("DELETE FROM \"Offerta\" WHERE id_offerta="+index);

	            conn.close();
	        }catch(SQLException e){
	        	cont.error("è stato impossibile cancellare questa offerta");
	    	}
	}
}
