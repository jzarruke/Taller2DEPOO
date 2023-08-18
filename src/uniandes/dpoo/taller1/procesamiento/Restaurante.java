package uniandes.dpoo.taller1.procesamiento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import uniandes.dpoo.taller1.modelo.Combo;
import uniandes.dpoo.taller1.modelo.Ingrediente;
import uniandes.dpoo.taller1.modelo.ProductoMenu;

public class Restaurante{
	private ArrayList<Combo> combos;
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<ProductoMenu> menu;
	
	public Restaurante(ArrayList<ProductoMenu> elMenu, ArrayList<Combo> elCombos, ArrayList<Ingrediente> elIngredientes) {
		this.combos = elCombos;
		this.ingredientes = elIngredientes;
		this.menu = elMenu;
	}
}
