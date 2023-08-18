package uniandes.dpoo.taller1.modelo;

public class Ingrediente {
	private String nombre;
	private int costoAdicional;
	
	public Ingrediente(String elNombre, int elCosto){
		this.nombre = elNombre;
		this.costoAdicional = elCosto;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public int getCostoAdicional(){
		return costoAdicional;
	}
}