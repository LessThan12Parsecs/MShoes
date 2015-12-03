package negocio.producto;

import java.sql.SQLException;
import java.util.ArrayList;

import negocio.producto.TransferProducto;

public interface SAProducto {
	public boolean altaProducto(TransferProducto p) throws SQLException;
	public boolean bajaProducto(TransferProducto p) throws SQLException;
	public boolean modificarProducto(TransferProducto p) throws SQLException;
	public TransferProducto consultarUnProducto(int IDProducto) throws SQLException;
	public ArrayList<TransferProducto> listarProductos() throws SQLException;
	public TransferProducto topProducto() throws SQLException;
}
