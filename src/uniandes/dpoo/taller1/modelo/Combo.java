package uniandes.dpoo.taller1.modelo;

public class Combo {
	private double descuento;
	private String nombreCombo;
	
	public Combo(String elNombre, double elDescuento){
		this.nombreCombo = elNombre;
		this.descuento = elDescuento;
	}
	
	public void agregarItemACombo(ProductoMenu producto) {
		
	}
	
	public double getPrecio() {
		return descuento;
	}
	
	public String generarFactura() {
		return nombreCombo;
	}
	
	public String getNombre(){
		return nombreCombo;
	}
}