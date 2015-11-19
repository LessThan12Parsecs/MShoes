package transacciones.transactioManager;

import integracion.transacciones.conexion.ExcepcionSQL;
import transacciones.transaccion.Transaccion;

public abstract class TransactionManager {

	private static TransactionManager transactionManagerInstance;
	
	private synchronized static void createTransactionManager()
	{
		if(transactionManagerInstance == null)
			transactionManagerInstance = new TransactionManagerImp();
	}
	
	public static TransactionManager getInstance()
	{
		createTransactionManager();
		return transactionManagerInstance;
	}
	
	public abstract Transaccion nuevaTransaccion();
	public abstract Transaccion getTransaction() throws ExcepcionSQL;
	public abstract Boolean eliminaTransaccion() throws ExcepcionSQL;
}
