package integracion.factoriaDAO;

import integracion.cliente.DAOCliente;
import integracion.producto.DAOProducto;
import integracion.venta.DAOVenta;


public abstract class FactoriaDAO {
	
	private static FactoriaDAO instancia;
	
	private static synchronized void createFactoriaDAO() {
		
		if (instancia == null) {
			instancia = new FactoriaDAOImp();
		}
	}
	
	public static FactoriaDAO getInstance(){

		createFactoriaDAO();

		return instancia;
	}
	
	public abstract DAOProducto generaDAOProducto();
	
	public abstract DAOCliente generaDAOCliente();

	public abstract DAOVenta generaDAOVenta();
}
