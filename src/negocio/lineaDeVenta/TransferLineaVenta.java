package negocio.lineaVenta;

public class TransferLineaVenta {
	private int IDVenta;
	private int IDProducto;
	private float coste;
	private int cantidad;
	
	
	public int getIDVenta() {
		return IDVenta;
	}
	public void setIDVenta(int IDVenta) {
		this.IDVenta = IDVenta;
	}
	public int getIDProducto() {
		return IDProducto;
	}
	public void setIDProducto(int IDArticulo) {
		this.IDProducto = IDArticulo;
	}
	public float getCoste() {
		return coste;
	}
	public void setCoste(float coste) {
		this.coste = coste;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String toString()
	{
		String salida = IDVenta + "/t" + IDProducto + "/t" + coste + "/t" + cantidad + "/t";
		return salida; 
	}
}
