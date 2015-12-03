package negocio.producto;

public class TransferProducto {
	//hay que añadir atributos??
	private int IDProducto;
	private String marca;
	private String tipo;
	private String color;
	private boolean activo;
	private int stock;
	private float precio;
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getIDProducto() {
		return IDProducto;
	}
	public void setIDProducto(int iDProducto) {
		IDProducto = iDProducto;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String toString() {
		return "TransferProducto [IDProducto=" + IDProducto + ", marca=" + marca
				+ ", tipo=" + tipo + ", color=" + color
				+ ", activo=" + activo + ", stock=" + stock
				+ "]";
	}
	
}
