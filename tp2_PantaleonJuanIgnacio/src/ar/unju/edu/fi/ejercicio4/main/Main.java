package ar.unju.edu.fi.ejercicio4.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ar.unju.edu.fi.ejercicio4.constantes.Posicion;
import ar.unju.edu.fi.ejercicio4.model.Jugador;

public class Main {

	public static void main(String[] args) {
		
		 ArrayList<Jugador> jugadores = new ArrayList<>();
	        Scanner scanner = new Scanner(System.in);

	        int opcion;
	        do {
	            System.out.println("Menú de opciones:");
	            System.out.println("1 - Alta de jugador");
	            System.out.println("2 - Mostrar todos los jugadores");
	            System.out.println("3 - Modificar la posición de un jugador");
	            System.out.println("4 - Eliminar un jugador");
	            System.out.println("5 - Salir");
	            System.out.println("Elija una opción:");

	            try {
	                opcion = scanner.nextInt();
	                scanner.nextLine(); 

	                switch (opcion) {
	                    case 1:
	                        altaJugador(jugadores, scanner);
	                        break;
	                    case 2:
	                        mostrarJugadores(jugadores);
	                        break;
	                    case 3:
	                        modificarPosicion(jugadores, scanner);
	                        break;
	                    case 4:
	                        eliminarJugador(jugadores, scanner);
	                        break;
	                    case 5:
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
	        } while (opcion != 5);
	    }

	    private static void altaJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
	        System.out.println("Ingrese el nombre del jugador:");
	        String nombre = scanner.nextLine();
	        System.out.println("Ingrese el apellido del jugador:");
	        String apellido = scanner.nextLine();
	        System.out.println("Ingrese la fecha de nacimiento del jugador (YYYY-MM-DD):");
	        LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());
	        System.out.println("Ingrese la nacionalidad del jugador:");
	        String nacionalidad = scanner.nextLine();
	        System.out.println("Ingrese la estatura del jugador:");
	        double estatura = scanner.nextDouble();
	        System.out.println("Ingrese el peso del jugador:");
	        double peso = scanner.nextDouble();
	        scanner.nextLine();
	        System.out.println("Ingrese la posición del jugador (DELANTERO, MEDIO, DEFENSA, ARQUERO):");
	        String posicionStr = scanner.nextLine();
	        Posicion posicion = Posicion.valueOf(posicionStr.toUpperCase());

	        Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, nacionalidad, estatura, peso, posicion);
	        jugadores.add(jugador);
	        System.out.println("Jugador agregado con éxito.");
	    }

	    private static void mostrarJugadores(ArrayList<Jugador> jugadores) {
	        if (jugadores.isEmpty()) {
	            System.out.println("No hay jugadores para mostrar.");
	        } else {
	            System.out.println("Listado de jugadores:");
	            for (Jugador jugador : jugadores) {
	                System.out.println("Nombre: " + jugador.getNombre());
	                System.out.println("Apellido: " + jugador.getApellido());
	                System.out.println("Edad: " + jugador.calcularEdad());
	                System.out.println("Nacionalidad: " + jugador.getNacionalidad());
	                System.out.println("Estatura: " + jugador.getEstatura());
	                System.out.println("Peso: " + jugador.getPeso());
	                System.out.println("Posición: " + jugador.getPosicion());
	                System.out.println();
	            }
	        }
	    }

	    private static void modificarPosicion(ArrayList<Jugador> jugadores, Scanner scanner) {
	        if (jugadores.isEmpty()) {
	            System.out.println("No hay jugadores para modificar.");
	        } else {
	            System.out.println("Ingrese el nombre del jugador:");
	            String nombre = scanner.nextLine();
	            System.out.println("Ingrese el apellido del jugador:");
	            String apellido = scanner.nextLine();

	            boolean encontrado = false;
	            for (Jugador jugador : jugadores) {
	                if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
	                    System.out.println("Ingrese la nueva posición del jugador (DELANTERO, MEDIO, DEFENSA, ARQUERO):");
	                    String nuevaPosicionStr = scanner.nextLine();
	                    Posicion nuevaPosicion = Posicion.valueOf(nuevaPosicionStr.toUpperCase());
	                    jugador.setPosicion(nuevaPosicion);
	                    System.out.println("Posición modificada con éxito.");
	                    encontrado = true;
	                    break;
	                }
	            }
	            if (!encontrado) {
	                System.out.println("No se encontró ningún jugador con ese nombre y apellido.");

	            }
	        }
}
	    
	    private static void eliminarJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
	        if (jugadores.isEmpty()) {
	            System.out.println("No hay jugadores para eliminar.");
	        } else {
	            System.out.println("Ingrese el nombre del jugador:");
	            String nombre = scanner.nextLine();
	            System.out.println("Ingrese el apellido del jugador:");
	            String apellido = scanner.nextLine();

	            Iterator<Jugador> iter = jugadores.iterator();
	            boolean eliminado = false;
	            while (iter.hasNext()) {
	                Jugador jugador = iter.next();
	                if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
	                    iter.remove();
	                    System.out.println("Jugador eliminado con éxito.");
	                    eliminado = true;
	                    break;
	                }
	            }
	            if (!eliminado) {
	                System.out.println("No se encontró ningún jugador con ese nombre y apellido.");
	            }
	        }
	    }
	}
