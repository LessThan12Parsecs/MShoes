package integracion.producto;

import integracion.transacciones.conexion.ExcepcionSQL;
import integracion.transacciones.conexion.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.cliente.TransferCliente;
import negocio.producto.TransferProducto;
import transacciones.transaccion.Transaccion;
import transacciones.transactioManager.TransactionManager;


public class DAOProductoImp implements DAOProducto{
	
	private final String altaProducto = "INSERT INTO `mshoes`.`Producto` (`ID_PRODUCTO`,`MARCA`,`TIPO`,`COLOR`,`STOCK`,"
			+ "`ACTIVO`) VALUES (?,?,?,?,?,?)";
	
	private final String consultaProducto = "Select * from `mshoes`.`Producto` where `ID_PRODUCTO` = ? FOR UPDATE";
	
	private final String listarProductos = "Select * from `mshoes`.`Producto` FOR UPDATE";
	
	private final String bajaProducto = "Update `mshoes`.`Producto` set `ACTIVO`=0 WHERE `ID_PRODUCTO`=? AND `ACTIVO`=1";
	
	private final String modificaProducto = "Update `mshoes`.`Producto` set `MARCA` = ?, `TIPO` = ?, `COLOR` = ?, "
			+ "`STOCK` = ? WHERE `ID_PRODUCTO` = ? ";
	
	//private final String topProducto = "" este falta.

	@Override
	public boolean altaProducto(TransferProducto t) throws SQLException {
		boolean correcto = false;
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			PreparedStatement alta = conexion.prepareStatement(altaProducto);
			alta.setInt(1, t.getIDProducto()); 
			alta.setString(2, t.getMarca());
			alta.setString(3, t.getTipo());
			alta.setString(4, t.getColor());
			alta.setInt(5, t.getStock());
			alta.setBoolean(6, t.isActivo());
			
			if(alta.executeUpdate() == 1) {
				correcto = true;
			}
			else
				throw new SQLException();
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error en alta del Producto");
		}
		
		return correcto;
	}


	@Override
	public boolean bajaProducto(int IDProducto) throws SQLException {
		boolean correcto = false;
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			PreparedStatement baja = conexion.prepareStatement(bajaProducto);
			baja.setInt(1, IDProducto);
		
			if(baja.executeUpdate() == 1) {
				correcto = true;
			}
			else
				throw new SQLException();
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error en dar de baja al Producto");
		}
		
		return correcto;
	}


	@Override
	public boolean modificarProducto(TransferProducto t) throws SQLException {
		boolean correcto = false;
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			
			PreparedStatement modifica = conexion.prepareStatement(modificaProducto);
			modifica.setInt(1, t.getIDProducto()); 
			modifica.setString(2, t.getMarca());
			modifica.setString(3, t.getTipo());
			modifica.setString(4, t.getColor());
			modifica.setInt(5, t.getStock());
			modifica.setInt(6, t.getIDProducto());
		
			
			if(modifica.executeUpdate() == 1) {
				correcto = true;
			}
			else
				throw new SQLException();
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error en modificacion del Producto");
		}
		
		return correcto;
	}


	@Override
	public TransferProducto consultarUnProducto(int IDProducto) throws SQLException {
		TransferProducto c = new TransferProducto();
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			PreparedStatement consulta = conexion.prepareStatement(consultaProducto);
			consulta.setInt(1, IDProducto);
			ResultSet resultado = consulta.executeQuery();
			
			resultado.next();
				
			c.setIDProducto(resultado.getInt("ID_Producto"));
			c.setMarca(resultado.getString("Marca"));
			c.setTipo(resultado.getString("Tipo"));
			c.setColor(resultado.getString("Color"));
			c.setActivo(resultado.getBoolean("Activo"));
			c.setStock(resultado.getInt("Stock"));

		
			
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error en la consulta del Producto");
			
		}
		
		return c;
	}


	@Override
	public ArrayList<TransferProducto> listarProductos() throws SQLException {
		TransferProducto c = null;
		ArrayList<TransferProducto> Productos = new ArrayList<TransferProducto>();
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		try {
			PreparedStatement lista = conexion.prepareStatement(listarProductos);
			ResultSet resultado = lista.executeQuery();
			
			
			while(resultado.next()) {
				
				c = new TransferProducto();
				c.setIDProducto(resultado.getInt("ID_Producto"));
				c.setMarca(resultado.getString("Marca"));
				c.setTipo(resultado.getString("Tipo"));
				c.setColor(resultado.getString("Color"));
				c.setActivo(resultado.getBoolean("Activo"));
				c.setStock(resultado.getInt("Stock"));
				
				Productos.add(c); 
			}
			
		}
		catch (ExcepcionSQL e){
			
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error al listar los clientes");
		}
		
		return Productos;
	}


	@Override
	public TransferProducto topProducto() throws SQLException { // falta
		// TODO Auto-generated method stub
		return null;
	}
}
