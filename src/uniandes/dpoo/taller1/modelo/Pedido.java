package uniandes.dpoo.taller1.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {
	private static int numeroPedidos = 0;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido = new ArrayList<Producto>();
	
	public Pedido(String nombre, String direccion) {
		nombreCliente = nombre;
		direccionCliente = direccion;
		numeroPedidos += 1;
		idPedido = numeroPedidos;
	}
	
	public String getNombreCliente() {
		return nombreCliente;
	}
	
	public String getDireccionCliente() {
		return direccionCliente;
	}
	
	public String getItemsPedido() {
		String productos = "";
		for (int index = 0; index < itemsPedido.size(); index++) {
			productos += itemsPedido.get(index).getNombre().toUpperCase() + "," + itemsPedido.get(index).getPrecio() + ":";
		}
		productos = productos.substring(0, productos.length() - 1);
		return productos;
	}
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void agregarProducto (Producto nuevoItem) {
		itemsPedido.add(nuevoItem);
	}
	
	public void generarCombo(String hamburguesa, Producto combo) {
		for (int index = 0; index < itemsPedido.size(); index++) {
			Producto item = itemsPedido.get(index);
			if (item.getNombre().equals(hamburguesa) || item.getNombre().equals("papas medianas") || item.getNombre().equals("gaseosa")) {
				itemsPedido.remove(item);
			}
		}
		itemsPedido.add(combo);
	}
	
	private int getPrecioNetoPedido() {
		int precio = 0;
		for (int ind = 0; ind < itemsPedido.size(); ind++) {
			Producto producto = itemsPedido.get(ind);
			precio += producto.getPrecio();
		}
		return precio;
	}
	
	private int getPrecioTotalPedido() {
		return getPrecioNetoPedido() + getPrecioIvaPedido();
	}
	
	private int getPrecioIvaPedido() {
		return (int) (getPrecioNetoPedido() * 0.19);
	}
	
	private String generarTextoFactura() {
		String factura = " ".repeat(7) + "Factura Id No. " + getIdPedido() + "\n\n";
		factura += "Cliente: " + getNombreCliente() + "\n";
		factura += "Dirección: " + getDireccionCliente() + "\n\n";
		factura += "Productos:" + " ".repeat(10) + "Precios:\n";
		for (int ind = 0; ind < itemsPedido.size(); ind++) {
			Producto producto = itemsPedido.get(ind);
			factura += producto.generarTextoFactura() + "\n";
		}
		String pNeto = "Precio Neto:" + "$" + getPrecioNetoPedido();
		String pIva = "Precio Iva:" + "$" + getPrecioIvaPedido();
		String pTotal = "Precio Total:" + "$" + getPrecioTotalPedido();
		factura += "\nPrecio Neto:" + " ".repeat(30 - pNeto.length()) + "$" + getPrecioNetoPedido();
		factura += "\nPrecio Iva:" + " ".repeat(30 - pIva.length()) + "$" + getPrecioIvaPedido();
		factura += "\nPrecio Total:" + " ".repeat(30 - pTotal.length()) + "$" + getPrecioTotalPedido();
		return factura;
	}
	
	public void guardarFactura(File archivo) throws IOException {
		BufferedWriter insertarFactura = new BufferedWriter(new FileWriter(archivo));
		insertarFactura.write(generarTextoFactura());
		insertarFactura.close();
	}
}
