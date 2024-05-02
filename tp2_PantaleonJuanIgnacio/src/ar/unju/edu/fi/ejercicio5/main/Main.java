package ar.unju.edu.fi.ejercicio5.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.unju.edu.fi.ejercicio5.model.Producto;
import ar.unju.edu.fi.ejercicio5.interfaces.Pago;
import ar.unju.edu.fi.ejercicio5.model.PagoEfectivo;
import ar.unju.edu.fi.ejercicio5.model.PagoTarjeta;

public class Main {

	public static void main(String[] args) {
		ArrayList<Producto> productos = preCargarProductos();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Menú de opciones:");
            System.out.println("1 - Mostrar productos");
            System.out.println("2 - Realizar compra");
            System.out.println("3 - Salir");
            System.out.println("Elija una opción:");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        mostrarProductos(productos);
                        break;
                    case 2:
                        realizarCompra(productos, scanner);
                        break;
                    case 3:
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
        } while (opcion != 3);
    }

    private static ArrayList<Producto> preCargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Camiseta", "Camiseta Deportiva", 100.0 , true));
        productos.add(new Producto("Libro", "Lectura Ligera", 150.0, true));
        productos.add(new Producto("Botella de Agua", "Bebida Hidratante", 200.0, true));
        productos.add(new Producto("Zapatillas", "Zapatillas Adidas Calsado 40", 120.0, true));
        productos.add(new Producto("Computadora", "Computadora con componentes Basicos", 180.0, true));
        productos.add(new Producto("Lampara", "Lampara Decorativa", 90.0, true));
        productos.add(new Producto("Martillo", "Herramienta para colocar Clavos", 110.0, true));
        productos.add(new Producto("Cámara", "Dispositivo para tomar fotos", 130.0, true));
        productos.add(new Producto("Mesa", "Mesa Decorativa", 160.0, true));
        productos.add(new Producto("Telefono", "Dispositivo Movil Marca Samsung", 170.0, true));
        productos.add(new Producto("Silla", "Silla Decorativa", 140.0, true));
        productos.add(new Producto("Planta", "Pequeño Helecho", 190.0, true));
        productos.add(new Producto("Cepillo", "Cepillo de dientes Colgate", 210.0, true));
        productos.add(new Producto("Pelota", "Pelota de Fútbol", 220.0, true));
        productos.add(new Producto("Cinta", "Cinta Metrica", 230.0, true));

        return productos;
    }

    private static void mostrarProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            System.out.println("Listado de productos disponibles:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    private static void realizarCompra(ArrayList<Producto> productos, Scanner scanner) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles para la compra.");
            return;
        }

        ArrayList<Producto> productosSeleccionados = new ArrayList<>();
        double totalCompra = 0;

        while (true) {
            System.out.println("Seleccione un producto para la compra (ingrese el número correspondiente):");
            mostrarProductos(productos);
            int seleccion = scanner.nextInt();
            scanner.nextLine(); 

            if (seleccion < 1 || seleccion > productos.size()) {
                System.out.println("Opción inválida. Por favor, seleccione un número válido.");
                continue;
            }

            Producto productoSeleccionado = productos.get(seleccion - 1);
            if (productoSeleccionado.isEstado()) {
                productosSeleccionados.add(productoSeleccionado);
                totalCompra += productoSeleccionado.getPrecioUnitario();
                System.out.println("Producto agregado a la compra.");
            } else {
                System.out.println("Este producto no está disponible.");
            }

            System.out.println("¿Desea agregar otro producto? (S/N)");
            String continuar = scanner.nextLine();
            if (!continuar.equalsIgnoreCase("S")) {
                break;
            }
        }

        if (totalCompra == 0) {
            System.out.println("No se ha seleccionado ningún producto para la compra.");
            return;
        }

        System.out.println("Total de la compra: " + totalCompra);

        System.out.println("Seleccione una opción de pago:");
        System.out.println("1 - Pago en efectivo");
        System.out.println("2 - Pago con tarjeta");
        int opcionPago = scanner.nextInt();
        scanner.nextLine();
        Pago pago;
        switch (opcionPago) {
            case 1:
                pago = new PagoEfectivo(LocalDate.now(), totalCompra);
                break;
            case 2:
                System.out.println("Ingrese el número de tarjeta:");
                String numeroTarjeta = scanner.nextLine();
                pago = new PagoTarjeta(numeroTarjeta, LocalDate.now(), totalCompra);
                break;
            default:
                System.out.println("Opción de pago inválida. Se cancela la compra.");
                return;
        }

        pago.realizarPago(totalCompra);
        pago.imprimirRecibo();
    }
}