package uniandes.dpoo.taller1.modelo;

public class ProductoMenu {
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String elNombre, int elPrecio){
		this.nombre = elNombre;
		this.precioBase = elPrecio;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public int getPrecio() {
		return precioBase;
	}
	
	public String generarFactura() {
		return nombre;
	}
}