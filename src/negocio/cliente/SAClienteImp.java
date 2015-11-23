package negocio.cliente;

import integracion.cliente.DAOCliente;
import integracion.factoriaDAO.FactoriaDAO;
import integracion.transacciones.conexion.ExcepcionSQL;

import java.sql.SQLException;
import java.util.ArrayList;

import transacciones.transaccion.Transaccion;
import transacciones.transactioManager.TransactionManager;

public class SAClienteImp implements SACliente{

	@Override
	public boolean altaCliente(TransferCliente c) throws SQLException {
	
		boolean correcto = false;
		TransactionManager t = TransactionManager.getInstance();
		Transaccion transaccion = t.nuevaTransaccion();
		DAOCliente daoCli = FactoriaDAO.getInstance().generaDAOCliente();
		
		transaccion.start();
		
		try {
			if(c == null) {
				transaccion.rollback();
				throw new ExcepcionSQL("El transfer es null");
			}
			else if((daoCli.consultarUnCliente(c.getIDCliente()) != null)) { //*** Lo hacemos con ID o DNI?
				transaccion.rollback();
				throw new ExcepcionSQL("Ya existe cliente");
			}
			else if(c.getNombre().equals("") || c.getApellidos().equals("")){
				transaccion.rollback();
				throw new ExcepcionSQL("Es necesario introducir Nombre y Apellidos");
			}
			else if (!c.getNewsletter() && c.getLimiteCredito() == 0) {
				transaccion.rollback();
				throw new ExcepcionSQL("Es necesario especificar el limite de credito o el newsletter");
			}
			else if(c.getNewsletter()){
				
				if(c.getLimiteCredito() != 0) {
					transaccion.rollback();
					throw new ExcepcionSQL("Un noSocio no puede tener limite de credito");
				}
				else{
					correcto = daoCli.altaClienteNoSocio(c);
					transaccion.commit();
				}
			}
			else if (c.getLimiteCredito() > 0){
				
				if(!c.getNewsletter()) {
					transaccion.rollback();
					throw new ExcepcionSQL("Un Socio no puede tener Newsletter");
				}
				
				else {
					correcto = daoCli.altaClienteSocio(c);
					transaccion.commit();
				}
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
	public boolean bajaCliente(TransferCliente c) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarCliente(TransferCliente c) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TransferCliente consultarUnCliente(int IDCliente) throws SQLException {
		
		TransferCliente c = null;
		TransactionManager t = TransactionManager.getInstance();
		Transaccion transaccion = t.nuevaTransaccion();
		DAOCliente daoCli = FactoriaDAO.getInstance().generaDAOCliente();
		
		transaccion.start();
		
		try {
			c = daoCli.consultarUnCliente(IDCliente);
		}
		catch (ExcepcionSQL e){
			throw e;
		}
		finally {
			t.eliminaTransaccion();
		}
		
		
		return c;
	}

	@Override
	public ArrayList<TransferCliente> listarClientes() throws SQLException {

		ArrayList<TransferCliente> listaClientes = null;
		TransactionManager t = TransactionManager.getInstance();
		Transaccion transaccion = t.nuevaTransaccion();
		DAOCliente daoCli = FactoriaDAO.getInstance().generaDAOCliente();
		
		transaccion.start();
		
		try {
			listaClientes = daoCli.listarClientes();
		}
		catch (ExcepcionSQL e){
			throw e;
		}
		finally {
			t.eliminaTransaccion();
		}
		
		
		return listaClientes;
	}

	@Override
	public ArrayList<TransferCliente> topTenClientes() throws SQLException {

		ArrayList<TransferCliente> listaTopClientes = null;
		TransactionManager t = TransactionManager.getInstance();
		Transaccion transaccion = t.nuevaTransaccion();
		DAOCliente daoCli = FactoriaDAO.getInstance().generaDAOCliente();
		
		transaccion.start();
		
		try {
			listaTopClientes = daoCli.topTenClientes();
		}
		catch (ExcepcionSQL e){
			throw e;
		}
		finally {
			t.eliminaTransaccion();
		}
		
		
		return listaTopClientes;
	}

}
