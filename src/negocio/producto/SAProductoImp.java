package negocio.producto;


import integracion.factoriaDAO.FactoriaDAO;
import integracion.producto.DAOProducto;
import integracion.transacciones.conexion.ExcepcionSQL;

import java.sql.SQLException;
import java.util.ArrayList;


import transacciones.transaccion.Transaccion;
import transacciones.transactioManager.TransactionManager;

public class SAProductoImp implements SAProducto {

	@Override
	public boolean altaProducto(TransferProducto p) throws SQLException {
		boolean correcto = false;
		TransactionManager t = TransactionManager.getInstance();
		Transaccion transaccion = t.nuevaTransaccion();
		DAOProducto daoProd = FactoriaDAO.getInstance().generaDAOProducto();
		
		transaccion.start();
		
		try {
			if(p == null) {
				transaccion.rollback();
				throw new ExcepcionSQL("El transfer es null");
			}
			else if((daoProd.consultarUnProducto(p.getIDProducto()) != null)) {
				transaccion.rollback();
				throw new ExcepcionSQL("Ya existe cliente");
			}
			else if(p.getTipo().equals("") || p.getMarca().equals("") || p.getColor().equals("")){
				transaccion.rollback();
				throw new ExcepcionSQL("Es necesario introducir una Marca, un Tipo y color");
			}
			else if(p.getStock() < 0 || p.getStock() > 999){
				transaccion.rollback();
				throw new ExcepcionSQL("Es necesario que el stock este entre 0 y 999");
			}
			
			
		}
		catch (ExcepcionSQL e){
			transaccion.rollback();
			throw e;
		}
		finally {
			t.eliminaTransaccion();
		}
		
		
		return correcto;
	}

	@Override
	public boolean bajaProducto(TransferProducto p) throws SQLException {
		
		return false;
	}

	@Override
	public boolean modificarProducto(TransferProducto p) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TransferProducto consultarUnProducto(int IDProducto) throws SQLException {

		TransferProducto p = null;
		TransactionManager t = TransactionManager.getInstance();
		Transaccion transaccion = t.nuevaTransaccion();
		DAOProducto daoProd = FactoriaDAO.getInstance().generaDAOProducto();
		
		transaccion.start();
		
		try {
			p = daoProd.consultarUnProducto(IDProducto);
		}
		catch (ExcepcionSQL e){
			throw e;
		}
		finally {
			t.eliminaTransaccion();
		}
		
		
		return p;
	}

	@Override
	public ArrayList<TransferProducto> listarProductos() throws SQLException {
		ArrayList<TransferProducto> listaProductos = null;
		TransactionManager t = TransactionManager.getInstance();
		Transaccion transaccion = t.nuevaTransaccion();
		DAOProducto daoProd = FactoriaDAO.getInstance().generaDAOProducto();
		
		transaccion.start();
		
		try {
			listaProductos = daoProd.listarProductos();
		}
		catch (ExcepcionSQL e){
			throw e;
		}
		finally {
			t.eliminaTransaccion();
		}
		
		
		return listaProductos;
	}

	@Override
	public ArrayList<TransferProducto> topProducto() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
