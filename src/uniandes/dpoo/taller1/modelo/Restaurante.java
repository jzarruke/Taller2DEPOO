package uniandes.dpoo.taller1.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Restaurante{
	private static ArrayList<Combo> combos = new ArrayList<Combo>();
	private static ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	private static ArrayList<ProductoMenu> menuBase = new ArrayList<ProductoMenu>();
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	private Pedido pedidoEnCurso;
	
	public Restaurante() {
		cargarInformacionRestaurante(new File("data/ingredientes.txt"), new File("data/menu.txt"), new File("data/combos.txt"));
	}
	
	public void iniciarPedidos(String nombreCliente, String direccionCliente) {
		pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
	}
	
	public void agregarProductoAPedido(int producto) {
		pedidoEnCurso.agregarProducto(menuBase.get(producto - 1));
		verificarCombo();
	}
	
	private void verificarCombo() {
		String[] items = pedidoEnCurso.getItemsPedido().split(":");
		String hamburgesa = "";
		int esCombo = 3;
		if (items.length >= 3) {
			for (int ind = 0; ind < combos.size(); ind++) {
				Combo combo = combos.get(ind);
				for (int index = 0; index < items.length; index++) {
					String[] partes = items[index].split(",");
					String nombre = partes[0];
					if (combo.getNombre().contains(nombre)) {
						esCombo -= 1;
						hamburgesa = nombre;
					}
					else if (nombre.equals("papas medianas") || nombre.equals("gaseosa")) {
						esCombo -= 1;
					}
				}
				if (esCombo == 0) {
					pedidoEnCurso.generarCombo(hamburgesa, combo);
				}
			}
		}
	}
	
	public String cerrarYGuardarPedido() {
		int idPedido = 0;
		try {
			File archivo = new File("docs/FacturaPedido" + pedidoEnCurso.getIdPedido() + ".txt");
			if (!archivo.exists()) {
				archivo.createNewFile();
	        }
			pedidoEnCurso.guardarFactura(archivo);
			idPedido = pedidoEnCurso.getIdPedido();
			pedidos.add(pedidoEnCurso);
			pedidoEnCurso = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "La ID de su pedido es: " + idPedido;
	}
	
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}
	
	public String getPedidoPorId(int id) {
		String pedido = "";
		for (int ind = 0; ind < pedidos.size(); ind++) {
			Pedido ped = pedidos.get(ind);
			if (ped.getIdPedido() == id) {
				pedido += ped.getNombreCliente() + ";" + ped.getDireccionCliente() + ";" + ped.getItemsPedido();
			}
			else {
				pedido = "No se ha encontrado un pedido con el ID proporcionado, por favor ingrese un ID valido.\n";
			}
		}
		return pedido;
	}
	
	public ArrayList<ProductoMenu> getMenuBase() {
		return menuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombo) {
		try {
			cargarIngredientes(archivoIngredientes);
			cargarMenu(archivoMenu);
			cargarCombos(archivoCombo);
			System.out.println("\n");
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: alguno de los archivos indicados no se encontró.");
		} catch (IOException e) {
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	
	private static void cargarIngredientes(File archivo) throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precio = Integer.parseInt(partes[1]);
			Ingrediente elIngrediente = new Ingrediente(nombre, precio);
			ingredientes.add(elIngrediente);
			linea = br.readLine();
		}
		br.close();
	}
	
	private static void cargarMenu(File archivo) throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precio = Integer.parseInt(partes[1]);
			ProductoMenu elMenu = new ProductoMenu(nombre, precio);
			menuBase.add(elMenu);
			linea = br.readLine();
		}
		br.close();
	}
	
	private static void cargarCombos(File archivo) throws FileNotFoundException, IOException {		
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombreCombo = partes[0];
			double descuento = (Double.parseDouble(partes[1].replace("%", "")))/100;
			Combo elCombo = new Combo(nombreCombo, descuento);
			String hamburguesa = partes[2];
			String papas = partes[3];
			String gaseosa = partes[4];
			for (int ind = 0; ind < menuBase.size(); ind++) {
				ProductoMenu pMenu = menuBase.get(ind);
				if (pMenu.getNombre() == hamburguesa) 
					elCombo.agregarItemACombo(pMenu);
				if (pMenu.getNombre() == papas) 
					elCombo.agregarItemACombo(pMenu);
				if (pMenu.getNombre() == gaseosa) 
					elCombo.agregarItemACombo(pMenu);
			}
			combos.add(elCombo);
			linea = br.readLine();
		}
		br.close();
	}
}
