package integracion.transacciones.conexion;

public class TransaccionNoEliminadaException extends ExcepcionSQL {

	private static final long serialVersionUID = 1L;

	private String source;
	
	public TransaccionNoEliminadaException(String claseDondeOcurrioError) {
		super("La transaccion no se pudo eliminar: ");
	}
	
	@Override
	public String getMensaje() {
		
		return super.getMensaje() + source;
	}
	
	public String getOrigen() {
		
		return source;
	}
	
	public void setOrigen(String claseDondeOcurrioError) {
		
		source = claseDondeOcurrioError;
	}
}
