package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto{
	private ProductoMenu base;
	private static ArrayList<Ingrediente> agregados = new ArrayList<Ingrediente>();
	private static ArrayList<Ingrediente> eliminados = new ArrayList<Ingrediente>();
	
	public ProductoAjustado(ProductoMenu pBase) {
		base = pBase;
	}
	
	public String getNombre() {
		String nombre = base.getNombre() + "\n";
		for (int ind = 0; ind < agregados.size(); ind++) {
			nombre += "+" + agregados.get(ind).getNombre() + " ";
		}
		if (!agregados.isEmpty() && !eliminados.isEmpty()) {
			nombre += "\n";
		}
		for (int ind = 0; ind < eliminados.size(); ind++) {
			nombre += "-" + eliminados.get(ind).getNombre() + " ";
		}
		return nombre;
	}
	
	public int getPrecio() {
		int precio = base.getPrecio();
		for (int ind = 0; ind < agregados.size(); ind++) {
			precio += agregados.get(ind).getCostoAdicional();
		}
		for (int ind = 0; ind < eliminados.size(); ind++) {
			precio -= eliminados.get(ind).getCostoAdicional();
		}
		return precio;
	}
	
	public String generarTextoFactura() {
		String noEspacios = base.getNombre() + getPrecio();
		return getNombre().toUpperCase() + " ".repeat(29 - (noEspacios.length())) + "$" + getPrecio();
	}
}