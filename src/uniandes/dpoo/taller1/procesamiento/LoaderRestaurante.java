package uniandes.dpoo.taller1.procesamiento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uniandes.dpoo.taller1.modelo.Combo;
import uniandes.dpoo.taller1.modelo.Ingrediente;
import uniandes.dpoo.taller1.modelo.ProductoMenu;

public class LoaderRestaurante {
	public static void cargarCombos() throws FileNotFoundException, IOException {
		List<Combo> combos = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader("data\\combos.txt"));
		String linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombreCombo = partes[0];
			String porcentaje = partes[1];
			double elDescuento = (Double.parseDouble(porcentaje.replace("%", "")))/100;
			double descuento = elDescuento;
			Combo elCombo = new Combo(nombreCombo, descuento);
			combos.add(elCombo);
		}
		linea = br.readLine();
		br.close();
		System.out.println(combos);
		//return (ArrayList<Combo>) combos;
	}
	
	public static ArrayList<Ingrediente> cargarIngredientes() throws FileNotFoundException, IOException {
		List<Ingrediente> ingredientes = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader("data\\ingredientes.txt"));
		String linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precio = Integer.parseInt(partes[1]);
			Ingrediente elIngrediente = new Ingrediente(nombre, precio);
			ingredientes.add(elIngrediente);
		}
		linea = br.readLine();
		br.close();
		System.out.println(ingredientes);
		return (ArrayList<Ingrediente>) ingredientes;
	}
	
	public static ArrayList<ProductoMenu> cargarMenu() throws FileNotFoundException, IOException {
		List<ProductoMenu> menu = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader("data\\menu.txt"));
		String linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precio = Integer.parseInt(partes[1]);
			ProductoMenu elMenu = new ProductoMenu(nombre, precio);
			menu.add(elMenu);
		}
		linea = br.readLine();
		br.close();
		System.out.println(menu);
		return (ArrayList<ProductoMenu>) menu;
	}
}
