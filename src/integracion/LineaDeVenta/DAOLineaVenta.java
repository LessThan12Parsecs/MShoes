package integracion.lineaVenta;

import java.sql.SQLException;
import java.util.ArrayList;

import negocio.lineaVenta.TransferLineaVenta;

public interface DAOLineaVenta {
	public boolean altaLineaVenta(TransferLineaVenta t) throws SQLException;
	//public ArrayList<TransferLineaVenta> consultaLineaVentasPorVenta(int IDVenta) throws SQLException;
	//public ArrayList<TransferLineaVenta> consultaLineaVentasPorArticulo(int IDArticulo) throws SQLException;
	public boolean a–adirProductoCarrito(TransferLineaVenta t) throws SQLException;
	public boolean eliminarProductoCarrito(TransferLineaVenta t) throws SQLException;
	public boolean devolucion(TransferLineaVenta t) throws SQLException;
}
