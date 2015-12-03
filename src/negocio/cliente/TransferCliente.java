package negocio.cliente;

public class TransferCliente {
	
	//hay que añadir atributos para las consultas?
	private int IDCliente;
	private String DNI;
	private String nombre;
	private String apellidos;
	private boolean activo;
	private float limiteCredito;
	private boolean newsletter;
	
	
	
	public int getIDCliente() {
		return IDCliente;
	}
	public void setIDCliente(int iDCliente) {
		IDCliente = iDCliente;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public float getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public boolean getNewsletter() {
		return newsletter;
	}
	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}
	
	
	
	public String toString() {
		return "TransferCliente [IDCliente=" + IDCliente + ", DNI=" + DNI
				+ ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", activo=" + activo + ", limiteCredito=" + limiteCredito
				+ ", newsletter=" + newsletter + "]";
	}
	
	
	
}
