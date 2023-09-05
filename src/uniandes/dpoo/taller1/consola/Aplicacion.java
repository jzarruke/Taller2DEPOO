package uniandes.dpoo.taller1.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import uniandes.dpoo.taller1.modelo.ProductoMenu;
import uniandes.dpoo.taller1.modelo.Restaurante;

public class Aplicacion {
	private Restaurante restaurante = new Restaurante();
	private ArrayList<Integer> opciones = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
	private String[] data = {"Cliente", "Dirección", "Productos"};
	
	public void ejecutarAplicacion(){
		System.out.println("Bienvenido/a al restaurante...");

		boolean continuar = true;
		while (continuar){
			try {
				mostrarMenu();
				int opcionSeleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcionSeleccionada == 6) {
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (restaurante.getPedidoEnCurso() != null && opcionSeleccionada == 2)
					System.out.println("Ya tiene un pedido en curso, si quiere crear uno nuevo\nelija la opción 4. de cerrar y guardar el pedido");
				else if (opciones.contains(opcionSeleccionada))
					ejecutarOpcion(opcionSeleccionada);
				else if (restaurante == null)
					System.out.println("\nPara poder ejecutar esta opción primero debe cargar los archivos.");
				else{
					System.out.println("\nPor favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e){
				System.out.println("\nDebe seleccionar uno de los números de las opciones.");
			}
		}
	}

	public void mostrarMenu(){
		System.out.println("1. Mostrar el menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar la factura");
		System.out.println("5. Consultar la información de un pedido dado su id");
		System.out.println("6. Salir de la aplicación");
	}
	
	public void ejecutarOpcion(int opcion) {
		if (opcion == 1) {
			System.out.println("\nImprimiendo el menú...\n");
			ArrayList<ProductoMenu> menuRestaurante = restaurante.getMenuBase();
			String menu = "";
			for (int ind = 0; ind < menuRestaurante.size(); ind++) {
				ProductoMenu pMenu = menuRestaurante.get(ind);
				String nombre = pMenu.getNombre();
				String precio = ",$" + pMenu.getPrecio();
				menu += nombre + precio;
				if (ind != menuRestaurante.size() - 1) {
					menu += ";";
				}
			}
			String[] partes = menu.split(";");
			String prntMenu = "+" + "-".repeat(25) + "+" + "-".repeat(8) + "+\n|" + " ".repeat(9) + "Platos"+ " ".repeat(10) + "| Precios|\n" + "+" + "-".repeat(25) + "+" + "-".repeat(8) + "+\n";
			for (int i = 0; i < partes.length; i++) {
				String[] sub = partes[i].split(",");
				String plato = " ".repeat((25 - sub[0].length())/2) + sub[0] + " ".repeat((25 - sub[0].length())/2);
				if (plato.length() < 25)
					plato += " ";
				String precio = " ".repeat((8 - sub[1].length())/2) + sub[1] + " ".repeat((8 - sub[1].length())/2);
				if (precio.length() < 8)
					precio += " ";
				prntMenu += "|" + plato + "|" + precio + "|\n";
			}
			prntMenu += "+" + "-".repeat(25) + "+" + "-".repeat(8) + "+\n";
			System.out.println(prntMenu);
		}
		else if (opcion == 2) {
			String nombre = input("Escriba el nombre del cliente");
			String direccion = input("Escriba la dirección del cliente");
			restaurante.iniciarPedidos(nombre, direccion);
			System.out.println("\n");
		}
		else if (opcion == 3) {
			String prntProductos = "";
			ArrayList<ProductoMenu> productos = restaurante.getMenuBase();
			for (int ind = 0; ind < productos.size(); ind++) {
				ProductoMenu producto = productos.get(ind);
				prntProductos += (ind + 1) + ". " + producto.getNombre() + "\n";
			}
			System.out.println(prntProductos);
			int producto = Integer.parseInt(input("Elija el producto que va a agregar"));
			restaurante.agregarProductoAPedido(producto);
			System.out.println("\n");
		}
		else if (opcion == 4) {
			System.out.println(restaurante.cerrarYGuardarPedido() + "\n");
			System.out.println("\nSe ha guardado exitosamente la factura.\n");
		}
		else if (opcion == 5) {
			int id = Integer.parseInt(input("Digite la ID del pedido"));
			String pedido = restaurante.getPedidoPorId(id);
			if (pedido.contains("No se ha encontrado")) {
				System.out.println(pedido);
			}
			else {
				String[] datos = pedido.split(";");
				String prntDatos = "\n+" + "-".repeat(38) + "+\n|" + " ".repeat(13) + "INFORMACIÓN" + " ".repeat(14) + "|";
				for (int ind = 0; ind < datos.length; ind++) {
					if (ind == datos.length - 1) {
						prntDatos += "\n|" + data[ind] + ":" + " ".repeat(16) + "Precios:" + " ".repeat(4) + "|";
						String[] producto = datos[ind].split(":");
						for (int index = 0; index < producto.length; index++) {
							String[] parte = producto[index].split(",");
							prntDatos += "\n| " + parte[0] + " ".repeat(36 - (parte[0].length() + parte[1].length())) + "$" + parte[1] + "|";
						}
					}
					else {
						String dato = "|" + data[ind] + ": " + datos[ind];
						prntDatos += "\n|" + data[ind] + ": " + " ".repeat(39 - dato.length()) + datos[ind] + "|";
					}
				}
				prntDatos += "\n+" + "-".repeat(38) + "+\n";
				System.out.println(prntDatos + "\n");
			}
		}
	}
	
	public String input(String mensaje){
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e){
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		Aplicacion restaurante = new Aplicacion();
		restaurante.ejecutarAplicacion();
	}
}