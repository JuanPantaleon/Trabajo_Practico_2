package ar.unju.edu.fi.ejercicio1.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.unju.edu.fi.ejercicio1.model.Producto;

public class Main {

	public static void main(String[] args) {
		
		 ArrayList<Producto> productos = new ArrayList<>();
	        Scanner scanner = new Scanner(System.in);

	        int opcion;
	        do {
	            System.out.println("Menú de opciones:");
	            System.out.println("1 - Crear Producto");
	            System.out.println("2 - Mostrar productos");
	            System.out.println("3 - Modificar producto");
	            System.out.println("4 - Salir");
	            System.out.println("Elija una opción:");

	            try {
	                opcion = scanner.nextInt();
	                scanner.nextLine(); 

	                switch (opcion) {
	                    case 1:
	                        crearProducto(productos, scanner);
	                        break;
	                    case 2:
	                        mostrarProductos(productos);
	                        break;
	                    case 3:
	                        modificarProducto(productos, scanner);
	                        break;
	                    case 4:
	                        System.out.println("Saliendo...");
	                        break;
	                    default:
	                        System.out.println("Opción inválida. Por favor, elija una opción válida.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Por favor, ingrese un número válido.");
	                scanner.nextLine();
	                opcion = 0;
	            }
	        } while (opcion != 4);
	    }

	    private static void crearProducto(ArrayList<Producto> productos, Scanner scanner) {
	        System.out.println("Ingrese el código del producto:");
	        String codigo = scanner.nextLine();
	        System.out.println("Ingrese la descripción del producto:");
	        String descripcion = scanner.nextLine();
	        System.out.println("Ingrese el precio unitario del producto:");
	        double precioUnitario = scanner.nextDouble();
	        scanner.nextLine(); 

	        // Solicitar y validar el origen de fabricación
	        Producto.OrigenFabricacion origenFabricacion = solicitarOrigenFabricacion(scanner);

	        // Solicitar y validar la categoría
	        Producto.Categoria categoria = solicitarCategoria(scanner);

	        // Crear el producto y agregarlo a la lista
	        Producto producto = new Producto(codigo, descripcion, precioUnitario, origenFabricacion, categoria);
	        productos.add(producto);
	        System.out.println("Producto creado con éxito.");
	    }

	    private static void mostrarProductos(ArrayList<Producto> productos) {
	        if (productos.isEmpty()) {
	            System.out.println("No hay productos para mostrar.");
	        } else {
	            System.out.println("Listado de productos:");
	            for (Producto producto : productos) {
	                System.out.println(producto.getCodigo() + " - " + producto.getDescripcion() + " - $" + producto.getPrecioUnitario() + " - Fabricado en " + producto.getOrigenFabricacion() + " - Categoría: " + producto.getCategoria());
	            }
	        }
	    }

	    private static void modificarProducto(ArrayList<Producto> productos, Scanner scanner) {
	        if (productos.isEmpty()) {
	            System.out.println("No hay productos para modificar.");
	        } else {
	            System.out.println("Ingrese el código del producto a modificar:");
	            String codigo = scanner.nextLine();

	            Producto productoAModificar = null;
	            for (Producto producto : productos) {
	                if (producto.getCodigo().equals(codigo)) {
	                    productoAModificar = producto;
	                    break;
	                }
	            }

	            if (productoAModificar == null) {
	                System.out.println("No se encontró ningún producto con ese código.");
	            } else {
	                System.out.println("Seleccione el atributo a modificar:");
	                System.out.println("1 - Descripción");
	                System.out.println("2 - Precio unitario");
	                System.out.println("3 - Origen de fabricación");
	                System.out.println("4 - Categoría");

	                int opcion = scanner.nextInt();
	                scanner.nextLine(); 
	                switch (opcion) {
	                    case 1:
	                        System.out.println("Ingrese la nueva descripción:");
	                        String nuevaDescripcion = scanner.nextLine();
	                        productoAModificar.setDescripcion(nuevaDescripcion);
	                        System.out.println("Descripción modificada con éxito.");
	                        break;
	                    case 2:
	                        System.out.println("Ingrese el nuevo precio unitario:");
	                        double nuevoPrecio = scanner.nextDouble();
	                        productoAModificar.setPrecioUnitario(nuevoPrecio);
	                        System.out.println("Precio unitario modificado con éxito.");
	                        break;
	                    case 3:
	                        Producto.OrigenFabricacion nuevoOrigen = solicitarOrigenFabricacion(scanner);
	                        productoAModificar.setOrigenFabricacion(nuevoOrigen);
	                        System.out.println("Origen de fabricación modificado con éxito.");
	                        break;
	                    case 4:
	                        Producto.Categoria nuevaCategoria = solicitarCategoria(scanner);
	                        productoAModificar.setCategoria(nuevaCategoria);
	                        System.out.println("Categoría modificada con éxito.");
	                        break;
	                    default:
	                        System.out.println("Opción inválida. No se realizó ninguna modificación.");
	                }
	            }
	        }
	    }
		
	    private static Producto.OrigenFabricacion solicitarOrigenFabricacion(Scanner scanner) {
	        Producto.OrigenFabricacion origenFabricacion = null;
	        do {
	            System.out.println("------ Origen de fabricación ------");
	            System.out.println("1 - Argentina");
	            System.out.println("2 - China");
	            System.out.println("3 - Brasil");
	            System.out.println("4 - Uruguay");
	            System.out.println("Elija una opción:");
	            
	            int opcion = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch (opcion) {
                case 1:
                    origenFabricacion = Producto.OrigenFabricacion.ARGENTINA;
                    break;
                case 2:
                    origenFabricacion = Producto.OrigenFabricacion.CHINA;
                    break;
                case 3:
                    origenFabricacion = Producto.OrigenFabricacion.BRASIL;
                    break;
                case 4:
                    origenFabricacion = Producto.OrigenFabricacion.URUGUAY;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elija una opción válida.");
            }
        } while (origenFabricacion == null);

        return origenFabricacion;
    }

    private static Producto.Categoria solicitarCategoria(Scanner scanner) {
        Producto.Categoria categoria = null;
        do {
            System.out.println("------ Categoría ------");
            System.out.println("1 - Telefonía");
            System.out.println("2 - Informática");
            System.out.println("3 - Electro hogar");
            System.out.println("4 - Herramientas");
            System.out.println("Elija una opción:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    categoria = Producto.Categoria.TELEFONIA;
                    break;
                case 2:
                    categoria = Producto.Categoria.INFORMATICA;
                    break;
                case 3:
                    categoria = Producto.Categoria.ELECTROHOGAR;
                    break;
                case 4:
                    categoria = Producto.Categoria.HERRAMIENTAS;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elija una opción válida.");
            }
        } while (categoria == null);

        return categoria;
    }

	    
	}


