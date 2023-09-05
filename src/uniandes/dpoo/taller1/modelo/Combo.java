package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;

public class Combo implements Producto{
	private double descuento;
	private String nombreCombo;
	private static ArrayList<ProductoMenu> itemsCombo = new ArrayList<ProductoMenu>();
	
	public Combo(String elNombre, double elDescuento){
		nombreCombo = elNombre;
		descuento = elDescuento;
	}
	
	public void agregarItemACombo(ProductoMenu itemCombo) {
		itemsCombo.add(itemCombo);
	}
	
	public int getPrecio() {
		int precio = 0;
		for (int ind = 0; ind < itemsCombo.size(); ind++) {
			ProductoMenu producto = itemsCombo.get(ind);
			precio += producto.getPrecio();
		}
		precio *= descuento;
		return precio;
	}
	
	public String getNombre(){
		return nombreCombo;
	}
	
	public String generarTextoFactura() {
		String noEspacios = nombreCombo + getPrecio();
		return nombreCombo.toUpperCase() + " ".repeat(29 - (noEspacios.length())) + "$" + getPrecio();
	}
}