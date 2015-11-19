package transacciones.transactioManager;

import integracion.transacciones.conexion.ExcepcionSQL;

import java.util.concurrent.ConcurrentHashMap;

import transacciones.factoriaTransaccion.FactoriaTransaccion;
import transacciones.transaccion.Transaccion;

public class TransactionManagerImp extends TransactionManager{
	
	private ConcurrentHashMap<Long, Transaccion> mapaConcurrencia = new ConcurrentHashMap<Long, Transaccion>();

	@Override
	public Transaccion nuevaTransaccion()
	{
		Transaccion t = null;
		Long idHilo = Thread.currentThread().getId();
		if(mapaConcurrencia.containsKey(idHilo))
			t = mapaConcurrencia.get(idHilo);
		else{
			t = FactoriaTransaccion.getInstance().nuevaTransaccion();
			mapaConcurrencia.put(idHilo,t);
		}
		return t;
	}

	public Transaccion getTransaction() throws ExcepcionSQL
	{
		Transaccion t = null;
		Long idHilo = Thread.currentThread().getId();
		if(mapaConcurrencia.containsKey(idHilo))
			t = mapaConcurrencia.get(idHilo);
		else{
			throw new ExcepcionSQL("Error al obtener transaccion");
		}
		return t;
		
	}
	
	@Override
	public Boolean eliminaTransaccion() throws ExcepcionSQL
	{
		Long threadId = Thread.currentThread().getId();
		boolean resultado;
		
		if (mapaConcurrencia.containsKey(threadId))
		{
			Transaccion t = mapaConcurrencia.get(threadId);
			
			t.commit();
			mapaConcurrencia.remove(threadId);
			
			resultado = true;
		}
		else
			resultado = false;
		
		return resultado;
	}
}
