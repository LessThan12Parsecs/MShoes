package integracion.producto;

import java.sql.SQLException;
import java.util.ArrayList;

import negocio.producto.TransferProducto;

public interface DAOProducto { //Crear, leer y actualizar del modelo CRUD.
	public boolean altaProducto(TransferProducto t) throws SQLException;
	public boolean bajaProducto(int IDProducto) throws SQLException;
	public boolean modificarProducto(TransferProducto t) throws SQLException;
	public TransferProducto consultarUnProducto(int IDProducto) throws SQLException;
	public ArrayList<TransferProducto> listarProductos() throws SQLException;
	public TransferProducto topProducto() throws SQLException;
}
