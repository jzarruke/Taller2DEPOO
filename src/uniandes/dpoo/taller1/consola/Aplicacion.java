package uniandes.dpoo.taller1.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uniandes.dpoo.taller1.modelo.Combo;
import uniandes.dpoo.taller1.modelo.Ingrediente;
import uniandes.dpoo.taller1.modelo.ProductoMenu;
import uniandes.dpoo.taller1.procesamiento.LoaderRestaurante;
import uniandes.dpoo.taller1.procesamiento.Restaurante;

public class Aplicacion {
	ArrayList<Combo> combos;
	ArrayList<Ingrediente> ingredientes;
	ArrayList<ProductoMenu> menu;
	private Restaurante restaurante;
	
	public void ejecutarAplicacion(){
		System.out.println("Pedidos de hamburguesas\n");

		boolean continuar = true;
		while (continuar){
			try {
				mostrarOpciones();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					cargarDatos();
				else if (opcion_seleccionada == 2 && restaurante != null)
					ejecutarMostrarMenu();
				else if (opcion_seleccionada == 3 && restaurante != null)
					System.out.println("Ha seleccionado la opción 3");
				else if (opcion_seleccionada == 4 && restaurante != null)
					System.out.println("Ha seleccionado la opción 4");
				else if (opcion_seleccionada == 5 && restaurante != null)
					System.out.println("Ha seleccionado la opción 5");
				else if (opcion_seleccionada == 6 && restaurante != null)
					System.out.println("Ha seleccionado la opción 6");
				else if (opcion_seleccionada == 7){
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (restaurante == null)
					System.out.println("Para poder ejecutar esta opción primero debe cargar los archivos.");
				else{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e){
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	public void mostrarOpciones(){
		System.out.println("1. Cargar datos");
		System.out.println("2. Mostrar el menú");
		System.out.println("3. Iniciar un nuevo pedido");
		System.out.println("4. Agregar un elemento a un pedido");
		System.out.println("5. Cerrar un pedido y guardar la factura");
		System.out.println("6. Consultar la información de un pedido dado su id");
		System.out.println("7. Salir de la aplicación");
	}
	
	public void ejecutarMostrarMenu() {
		cargarDatos();
	}
	
	public void cargarDatos() {
		System.out.println("\n" + "Cargar los archivos del restaurante" + "\n");
		try {
			LoaderRestaurante.cargarCombos();
//			ingredientes = LoaderRestaurante.cargarIngredientes();
//			menu = LoaderRestaurante.cargarMenu();
			restaurante = new Restaurante(menu, combos, ingredientes);
			System.out.println("Se han cargado exitosamente los archivos.");
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: alguno de los archivos indicados no se encontró.");
		} catch (IOException e) {
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
		System.out.println("Se terminó el proceso de carga de datos.");
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
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}
}