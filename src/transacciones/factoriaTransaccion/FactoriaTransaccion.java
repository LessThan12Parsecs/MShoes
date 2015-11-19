package transacciones.factoriaTransaccion;

import transacciones.transaccion.Transaccion;


public abstract class FactoriaTransaccion {
	
	private static FactoriaTransaccion instance;
	
	public abstract Transaccion nuevaTransaccion();
	
	private synchronized static void createTransactionFactory()
	{
		if(instance == null)
			instance = new FactoriaTransaccionImp();
	}
	
	public static FactoriaTransaccion getInstance()
	{
		createTransactionFactory();
		return instance;
	}
}
