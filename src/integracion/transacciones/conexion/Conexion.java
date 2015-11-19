package integracion.transacciones.conexion;



import java.sql.*;

public class Conexion {
	public Connection AbrirConexionDataSource() throws SQLException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
		
		}

		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/MShoes?" + "user=mshoes&password=");
		System.out.println("sadfsa");
		conexion.setAutoCommit(false);
		conexion.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
		//Consulta
//		Statement s = conexion.createStatement();
//		ResultSet rs = s.executeQuery("select * from cliente");
//		
//		while(rs.next()) {
//			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +rs.getString(3));
//		}
		return conexion;
	}
}
