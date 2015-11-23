package integracion.cliente;

import integracion.transacciones.conexion.ExcepcionSQL;
import integracion.transacciones.conexion.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.cliente.TransferCliente;
import transacciones.transaccion.Transaccion;
import transacciones.transactioManager.TransactionManager;

public class DAOClienteImp implements DAOCliente{

	private final String altaNoSocio = "INSERT INTO `mshoes`.`Cliente` (`ID_CLIENTE`,`DNI`,`NOMBRE`,`APELLIDOS`,`ACTIVO`,"
			+ "`NEWSLETTER`) VALUES (?,?,?,?,?,?)";
	
	private final String altaSocio = "INSERT INTO `mshoes`.`Cliente` (`ID_CLIENTE`,`DNI`,`NOMBRE`,`APELLIDOS`,`ACTIVO`,"
			+ "`LIMITE_CREDITO`) VALUES (?,?,?,?,?,?)";
	
	private final String consultaCliente = "Select * from `mshoes`.`Cliente` where `ID_CLIENTE` = ? FOR UPDATE";
	
	private final String listarClientes = "Select * from `mshoes`.`Cliente` FOR UPDATE";
	
	private final String bajaCliente = "Update `mshoes`.`Cliente` set `ACTIVO`=0 WHERE `ID_CLIENTE`=? AND `ACTIVO`=1";
	
	private final String modificaNoSocio = "Update `mshoes`.`Cliente` set `DNI` = ?, `NOMBRE` = ?, `APELLIDOS` = ?, "
			+ "`NEWSLETTER` = ? WHERE `ID_CLIENTE` = ? ";
	
	private final String modificaSocio = "Update `mshoes`.`Cliente` set `DNI` = ?, `NOMBRE` = ?, `APELLIDOS` = ?, "
			+ "`LIMITE_CREDITO` = ? WHERE `ID_CLIENTE` = ? ";
	
	private final String topTenClientes = "SELECT TOP (10) *, count(ID_VENTAS) AS  `NUM_COMPRAS` FROM `mshoes`.`Cliente` INNER JOIN " 
			+ "`mshoes`.`Ventas` ON `mshoes`.`Client`.`ID_CLIENTE` = `mshoes`.`Ventas`.`ID_CLIENTE` GROUP BY `ID_CLIENTE` ORDER BY `NUM_COMPRAS` DESC ";
	
	
	public boolean altaClienteNoSocio(TransferCliente c) throws SQLException {
		
		boolean correcto = false;
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			PreparedStatement alta = conexion.prepareStatement(altaNoSocio);
			alta.setInt(1, c.getIDCliente()); //***Comprobar si la BBDD genera y aumenta solo el ID
			alta.setString(2, c.getDNI());
			alta.setString(3, c.getNombre());
			alta.setString(4, c.getApellidos());
			alta.setBoolean(5, c.getActivo());
			alta.setBoolean(6, c.getNewsletter());
		
			if(alta.executeUpdate() == 1) {
				correcto = true;
			}
			else
				throw new SQLException();
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error en alta del NoSocio");
		}
		
		return correcto;
	}

	@Override
	public boolean altaClienteSocio(TransferCliente c) throws SQLException {
		
		boolean correcto = false;
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			PreparedStatement alta = conexion.prepareStatement(altaSocio);
			alta.setInt(1, c.getIDCliente());
			alta.setString(2, c.getDNI());
			alta.setString(3, c.getNombre());
			alta.setString(4, c.getApellidos());
			alta.setBoolean(5, c.getActivo());
			alta.setFloat(6, c.getLimiteCredito());
		
			if(alta.executeUpdate() == 1) {
				correcto = true;
			}
			else
				throw new SQLException();
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error en alta del Socio");
		}
		
		return correcto;
	}

	@Override
	public boolean bajaCliente(int IDCliente) throws SQLException {
		
		boolean correcto = false;
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			PreparedStatement baja = conexion.prepareStatement(bajaCliente);
			baja.setInt(1, IDCliente);
			//alta.setString(2, c.getDNI());
			//alta.setString(3, c.getNombre());
			//alta.setString(4, c.getApellidos());
			//baja.setBoolean(2, false);
			//alta.setBoolean(6, c.getNewsletter());
		
			if(baja.executeUpdate() == 1) {
				correcto = true;
			}
			else
				throw new SQLException();
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error en baja cliente");
		}
		
		return correcto;
	}

	
	
	
	public boolean modificarClienteNoSocio(TransferCliente c) throws SQLException {
		
		boolean correcto = false;
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			
			PreparedStatement modifica = conexion.prepareStatement(modificaNoSocio);
			modifica.setString(1, c.getDNI());
			modifica.setString(2, c.getNombre());
			modifica.setString(3, c.getApellidos());
			modifica.setBoolean(4, c.getNewsletter());
			modifica.setInt(5, c.getIDCliente());
		
			
			if(modifica.executeUpdate() == 1) {
				correcto = true;
			}
			else
				throw new SQLException();
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error en modificacion del NoSocio");
		}
		
		return correcto;
	}
	
	
	

	@Override
	public boolean modificarClienteSocio(TransferCliente c) throws SQLException {
		
		boolean correcto = false;
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			
			PreparedStatement modifica = conexion.prepareStatement(modificaSocio);
			modifica.setString(1, c.getDNI());
			modifica.setString(2, c.getNombre());
			modifica.setString(3, c.getApellidos());
			modifica.setFloat(4, c.getLimiteCredito());
			modifica.setInt(5, c.getIDCliente());
		
			
			if(modifica.executeUpdate() == 1) {
				correcto = true;
			}
			else
				throw new SQLException();
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error en modificacion del Socio");
		}
		
		return correcto;
	}

	@Override
	public TransferCliente consultarUnCliente(int IDCliente) throws SQLException {
		
		TransferCliente c = new TransferCliente();
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			PreparedStatement consulta = conexion.prepareStatement(consultaCliente);
			consulta.setInt(1, IDCliente);
			ResultSet resultado = consulta.executeQuery();
			
			resultado.next();
				
			c.setIDCliente(resultado.getInt("ID_Cliente"));
			c.setDNI(resultado.getString("DNI"));
			c.setNombre(resultado.getString("Nombre"));
			c.setApellidos(resultado.getString("Apellidos"));
			c.setActivo(resultado.getBoolean("Activo"));
			c.setLimiteCredito(resultado.getFloat("Limite_Credito"));
			c.setNewsletter(resultado.getBoolean("Newsletter"));			
		
			
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error en la consulta del cliente");
			
		}
		
		return c;
	}

	@Override
	public ArrayList<TransferCliente> listarClientes() throws SQLException {
		
		TransferCliente c = null;
		ArrayList<TransferCliente> clientes = new ArrayList<TransferCliente>();
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			PreparedStatement lista = conexion.prepareStatement(listarClientes);
			ResultSet resultado = lista.executeQuery();
			
			
			while(resultado.next()) {
				
				c = new TransferCliente(); //Crea un objeto por cliente
				c.setIDCliente(resultado.getInt("ID_Cliente"));
				c.setDNI(resultado.getString("DNI"));
				c.setNombre(resultado.getString("Nombre"));
				c.setApellidos(resultado.getString("Apellidos"));
				c.setActivo(resultado.getBoolean("Activo"));
				c.setLimiteCredito(resultado.getFloat("Limite_Credito"));
				c.setNewsletter(resultado.getBoolean("Newsletter"));	
				clientes.add(c); //A�adimos el cliente a la lista
			}
			
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error al listar los clientes");
		}
		
		return clientes;
	}

	@Override
	public ArrayList<TransferCliente> topTenClientes() throws SQLException {
		
		TransferCliente c = null;
		ArrayList<TransferCliente> clientes = new ArrayList<TransferCliente>();
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			PreparedStatement lista = conexion.prepareStatement(topTenClientes);
			ResultSet resultado = lista.executeQuery();
			
			
			while(resultado.next()) {
				
				c = new TransferCliente(); //Crea un objeto por cliente
				c.setIDCliente(resultado.getInt("ID_Cliente"));
				c.setDNI(resultado.getString("DNI"));
				c.setNombre(resultado.getString("Nombre"));
				c.setApellidos(resultado.getString("Apellidos"));
				c.setActivo(resultado.getBoolean("Activo"));
				c.setLimiteCredito(resultado.getFloat("Limite_Credito"));
				c.setNewsletter(resultado.getBoolean("Newsletter"));
				// como hacemos para mostrar el num de ventas? creando un nuevo atributo?? 
				clientes.add(c); //A�adimos el cliente a la lista
			}
			
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error al listar los clientes");
		}
		
		return clientes;
	}

}
