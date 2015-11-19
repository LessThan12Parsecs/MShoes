package integracion.factoriaDAO;

import integracion.cliente.DAOCliente;
import integracion.cliente.DAOClienteImp;
import integracion.producto.DAOProducto;
import integracion.producto.DAOProductoImp;
import integracion.venta.DAOVenta;
import integracion.venta.DAOVentaImp;


public class FactoriaDAOImp extends FactoriaDAO{

	public DAOCliente generaDAOCliente(){
		return new DAOClienteImp();
	}

	@Override
	public DAOProducto generaDAOProducto() {
		return new DAOProductoImp();
	}

	@Override
	public DAOVenta generaDAOVenta() {
		return new DAOVentaImp();
	}
}
