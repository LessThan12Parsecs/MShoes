package integracion.main;


import integracion.transacciones.conexion.Conexion;
import integracion.transacciones.conexion.ExcepcionSQL;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;

public class Main {
 //***No hace falta. Se hace en transaccionMySQL.start()
	public static void main(String[] args) {
		
		try {
			start();
			
		} catch (ExcepcionSQL e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void start() throws ExcepcionSQL{
		try{
			Connection conexion = new Conexion().AbrirConexionDataSource();
			
			PreparedStatement startTransaction = (PreparedStatement)conexion.prepareStatement("START TRANSACTION");
			
			if(startTransaction.execute()){
				//fallo
			}
		}
		catch(Exception e){
			throw new ExcepcionSQL("Error al comenzar la conexion");
		}
	}

}
