package integracion.cliente;

import java.sql.SQLException;
import java.util.ArrayList;

import negocio.cliente.TransferCliente;

public interface DAOCliente {
	public boolean altaClienteNoSocio(TransferCliente c) throws SQLException;
	public boolean altaClienteSocio(TransferCliente c) throws SQLException;
	public boolean bajaCliente(int IDCliente) throws SQLException;
	public boolean modificarClienteNoSocio(TransferCliente c) throws SQLException;
	public boolean modificarClienteSocio(TransferCliente c) throws SQLException;
	public TransferCliente consultarUnCliente(int IDCliente) throws SQLException;
	public ArrayList<TransferCliente> listarClientes() throws SQLException;
	public ArrayList<TransferCliente> topTenClientes() throws SQLException;
}
