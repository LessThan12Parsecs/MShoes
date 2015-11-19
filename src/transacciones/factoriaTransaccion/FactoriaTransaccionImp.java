package transacciones.factoriaTransaccion;

import transacciones.transaccion.Transaccion;
import transacciones.transaccion.TransaccionMySQL;

public class FactoriaTransaccionImp extends FactoriaTransaccion{

	@Override
	public Transaccion nuevaTransaccion() {
		return new TransaccionMySQL();
	}
}
