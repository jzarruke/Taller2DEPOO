package uniandes.dpoo.taller1.modelo;

public class Ingrediente {
	private String nombre;
	private int costoAdicional;
	
	public Ingrediente(String elNombre, int costo){
		nombre = elNombre;
		costoAdicional = costo;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public int getCostoAdicional(){
		return costoAdicional;
	}
}