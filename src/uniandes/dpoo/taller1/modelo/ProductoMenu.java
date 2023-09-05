package uniandes.dpoo.taller1.modelo;

public class ProductoMenu implements Producto{
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String elNombre, int precio){
		nombre = elNombre;
		precioBase = precio;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public int getPrecio() {
		return precioBase;
	}
	
	public String generarTextoFactura() {
		String noEspacios = nombre + precioBase;
		return nombre.toUpperCase() + " ".repeat(29 - (noEspacios.length())) + "$" + precioBase;
	}
}