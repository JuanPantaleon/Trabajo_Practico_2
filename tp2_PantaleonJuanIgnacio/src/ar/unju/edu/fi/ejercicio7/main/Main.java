package ar.unju.edu.fi.ejercicio7.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ar.unju.edu.fi.ejercicio5.model.Producto;
import ar.unju.edu.fi.ejercicio5.model.Producto.Categoria;
import ar.unju.edu.fi.ejercicio5.model.Producto.OrigenFabricacion;

public class Main {

	public static void main(String[] args) {
		 ArrayList<Producto> productos = preCargaProductos();

	        Scanner scanner = new Scanner(System.in);
	        int opcion;
	        do {
	            System.out.println("Menú de opciones:");
	            System.out.println("1 – Mostrar productos disponibles");
	            System.out.println("2 – Mostrar productos faltantes");
	            System.out.println("3 – Incrementar precios de productos");
	            System.out.println("4 – Mostrar productos de categoría Electrohogar disponibles");
	            System.out.println("5 – Ordenar productos por precio descendente");
	            System.out.println("6 - Mostrar productos con nombres en mayúsculas");
	            System.out.println("7 - Salir");
	            System.out.println("Ingrese su opción:");

	            opcion = scanner.nextInt();
	            scanner.nextLine();

	            switch (opcion) {
	                case 1:
	                    mostrarProductos(productos, p -> p.isEstado());
	                    break;
	                case 2:
	                    mostrarProductosFaltantes(productos);
	                    break;
	                case 3:
	                    ArrayList<Producto> productosIncrementados = incrementarPrecios(productos);
	                    mostrarProductos(productosIncrementados, p -> true);
	                    break;
	                case 4:
	                	mostrarProductosElectrohogar(productos);
	                    break;
	                case 5:
	                    ordenarProductosPorPrecioDescendente(productos);
	                    mostrarProductos(productos, p -> true);
	                    break;
	                case 6:
	                    mostrarProductosEnMayusculas(productos);
	                    break;
	                case 7:
	                    System.out.println("Saliendo...");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
	                    break;
	            }
	        } while (opcion != 7);
	        
	        scanner.close();
	}

	    private static ArrayList<Producto> preCargaProductos() {
	        ArrayList<Producto> productos = new ArrayList<>();
	        productos.add(new Producto("Camiseta", "Camiseta Deportiva", 100.0 , OrigenFabricacion.ARGENTINA, Categoria.ELECTROHOGAR, true));
	        productos.add(new Producto("Libro", "Lectura Ligera", 150.0,OrigenFabricacion.BRASIL, Categoria.ELECTROHOGAR, true));
	        productos.add(new Producto("Botella de Agua", "Bebida Hidratante", 200.0, OrigenFabricacion.ARGENTINA, Categoria.ELECTROHOGAR, true));
	        productos.add(new Producto("Zapatillas", "Zapatillas Adidas Calsado 40", 120.0,OrigenFabricacion.ARGENTINA, Categoria.ELECTROHOGAR, true));
	        productos.add(new Producto("Computadora", "Computadora con componentes Basicos", 180.0,OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
	        productos.add(new Producto("Lampara", "Lampara Decorativa", 90.0,OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, true));
	        productos.add(new Producto("Martillo", "Herramienta para colocar Clavos", 110.0,OrigenFabricacion.ARGENTINA, Categoria.HERRAMIENTAS, true));
	        productos.add(new Producto("Cámara", "Dispositivo para tomar fotos", 130.0,OrigenFabricacion.ARGENTINA, Categoria.ELECTROHOGAR, true));
	        productos.add(new Producto("Mesa", "Mesa Decorativa", 160.0,OrigenFabricacion.ARGENTINA, Categoria.ELECTROHOGAR, true));
	        productos.add(new Producto("Telefono", "Dispositivo Movil Marca Samsung", 170.0,OrigenFabricacion.CHINA, Categoria.TELEFONIA, true));
	        productos.add(new Producto("Silla", "Silla Decorativa", 140.0,OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, true));
	        productos.add(new Producto("Planta", "Pequeño Helecho", 190.0,OrigenFabricacion.ARGENTINA, Categoria.ELECTROHOGAR, true));
	        productos.add(new Producto("Cepillo", "Cepillo de dientes Colgate", 210.0,OrigenFabricacion.ARGENTINA, Categoria.HERRAMIENTAS, true));
	        productos.add(new Producto("Pelota", "Pelota de Fútbol", 220.0,OrigenFabricacion.ARGENTINA, Categoria.ELECTROHOGAR, true));
	        productos.add(new Producto("Cinta", "Cinta Metrica", 230.0,OrigenFabricacion.BRASIL, Categoria.HERRAMIENTAS, true));

	        return productos;
	    }
	    
	    private static void mostrarProductos(ArrayList<Producto> productos, Predicate<Producto> predicate) {
	        System.out.println("Productos:");
	        productos.stream()
	                .filter(predicate)
	                .forEach(p -> System.out.println(p));
	    }

	    private static void mostrarProductosFaltantes(ArrayList<Producto> productos) {
	        System.out.println("Productos faltantes:");
	        productos.stream()
	                .filter(p -> !p.isEstado())
	                .forEach(p -> System.out.println(p));
	    }

	    private static ArrayList<Producto> incrementarPrecios(ArrayList<Producto> productos) {
	        System.out.println("Incrementando precios en un 20%...");
	        Function<Producto, Producto> incrementarPrecio = p -> {
	            double precioIncrementado = p.getPrecioUnitario() * 1.20;
	            p.setPrecioUnitario(precioIncrementado);
	            return p;
	        };

	        return productos.stream()
	                .map(incrementarPrecio)
	                .collect(Collectors.toCollection(ArrayList::new));
	    }
	    
	    private static void mostrarProductosElectrohogar(List<Producto> productos) {
	        productos.stream()
	                .filter(p -> p.getCategoria().equals(Categoria.ELECTROHOGAR) && p.isEstado())
	                .forEach(p -> System.out.println(p.getCodigo() + " - Precio: " + p.getPrecioUnitario()));
	    }
	    
	    

	    private static void ordenarProductosPorPrecioDescendente(ArrayList<Producto> productos) {
	        System.out.println("Ordenando productos por precio descendente...");
	        productos.sort(Comparator.comparing(Producto::getPrecioUnitario).reversed());
	    }

	    private static void mostrarProductosEnMayusculas(ArrayList<Producto> productos) {
	        System.out.println("Productos con nombres en mayúsculas:");
	        productos.stream()
	                .map(p -> new Producto(p.getCodigo(), p.getDescripcion().toUpperCase(), p.getPrecioUnitario(), p.getOrigenFabricacion(), p.getCategoria(), p.isEstado()))
	                .forEach(p -> System.out.println(p));
	    }
	    
	   
	}
