package integracion.lineaVenta;

import integracion.transacciones.conexion.ExcepcionSQL;
import integracion.transacciones.conexion.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import transacciones.transaccion.Transaccion;
import transacciones.transactioManager.TransactionManager;
import negocio.lineaVenta.TransferLineaVenta;

public class DAOLineaVentaImp implements DAOLineaVenta{

	private final String queryAlta = "INSERT INTO `MShoes`.`LINEA_DE_VENTA` (`ID_Producto`,`ID_Ventas`,`Cantidad`,`Precio`) VALUES(?,?,?,?)";
	private final String queryDevolucion="UPDATE `MShoes`.`LINEA_DE_VENTA` SET `Cantidad` = ? WHERE `ID_Producto`= ? AND `ID_Ventas`= ?";

	
	@Override
	public boolean altaLineaVenta(TransferLineaVenta c) throws SQLException {
		
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		
		boolean correcto = false;
		
		try {
			
			PreparedStatement alta = conexion.prepareStatement(queryAlta);
			alta.setInt(1, c.getIDVenta());
			alta.setInt(2, c.getIDProducto());
			alta.setInt(3, c.getCantidad());
			alta.setFloat(4, 0);
			
			
			int rs = alta.executeUpdate();
			
//			if(rs==1 && calculoCosteLineaVenta())
//				resultado=true;
//			else
//				throw new SQLException();
			
		} catch (ExcepcionSQL e)
		{
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error al dar de alta la linea de venta");
		}
		
		return correcto;

	}

	@Override
	public boolean anadirProductoCarrito(TransferLineaVenta t)
			throws SQLException {

		return false;
	}

	@Override
	public boolean eliminarProductoCarrito(TransferLineaVenta t)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean devolucion(TransferLineaVenta c) throws SQLException {
		
		Transaccion transaccion = TransactionManager.getInstance().getTransaction();
		Connection conexion = transaccion.getResource();
		boolean correcto = false;
		
		try
		{
			PreparedStatement actualizar = conexion.prepareStatement(queryDevolucion);
			
			actualizar.setInt(1, c.getCantidad());
			actualizar.setInt(2, c.getIDProducto());
			actualizar.setInt(3, c.getIDVenta());
			
			int rs = actualizar.executeUpdate();
			System.out.println("res rs:"+rs);
			
//			if(rs!=0 && calculoCosteLineaVenta())
//			{
//				resultado=true;
//			}
//			else
//				throw new SQLException();
			
			
		}catch (ExcepcionSQL e) {
			JdbcUtils.printSQLException(e);
			throw new ExcepcionSQL("Error al realizar la devolucion");
		}
		
		return correcto;
	}

}




