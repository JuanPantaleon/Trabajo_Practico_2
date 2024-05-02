package ar.unju.edu.fi.ejercicio2.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import ar.unju.edu.fi.ejercicio2.constantes.Mes;

import ar.unju.edu.fi.ejercicio2.model.Efemeride;

public class Main {

	public static void main(String[] args) {
		 ArrayList<Efemeride> efemerides = new ArrayList<>();
	        Scanner scanner = new Scanner(System.in);

	        int opcion;
	        do {
	            System.out.println("Menú de acciones:");
	            System.out.println("1 – Crear efeméride");
	            System.out.println("2 – Mostrar efemérides");
	            System.out.println("3 – Eliminar efeméride");
	            System.out.println("4 – Modificar efeméride");
	            System.out.println("5 – Salir");
	            System.out.println("Elija una opción:");

	            try {
	                opcion = scanner.nextInt();
	                scanner.nextLine();

	                switch (opcion) {
	                    case 1:
	                        crearEfemeride(efemerides, scanner);
	                        break;
	                    case 2:
	                        mostrarEfemerides(efemerides);
	                        break;
	                    case 3:
	                        eliminarEfemeride(efemerides, scanner);
	                        break;
	                    case 4:
	                        modificarEfemeride(efemerides, scanner);
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

	    private static void crearEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
	        System.out.println("Ingrese el código de la efeméride:");
	        String codigo = scanner.nextLine();
	        System.out.println("Ingrese el número del mes (1-12):");
	        int numMes = scanner.nextInt();
	        scanner.nextLine();
	        Mes mes = obtenerMesPorNumero(numMes);
	        if (mes == null) {
	            System.out.println("Mes inválido. Por favor, ingrese un número entre 1 y 12.");
	            return;
	        }
	        System.out.println("Ingrese el día:");
	        int dia = scanner.nextInt();
	        scanner.nextLine();
	        System.out.println("Ingrese el detalle:");
	        String detalle = scanner.nextLine();

	        Efemeride efemeride = new Efemeride(codigo, mes, dia, detalle);
	        efemerides.add(efemeride);
	        System.out.println("Efeméride creada con éxito.");
	    }

	    private static Mes obtenerMesPorNumero(int numMes) {
	        Mes mes = null;
	        switch (numMes) {
	            case 1:
	                mes = Mes.ENERO;
	                break;
	            case 2:
	                mes = Mes.FEBRERO;
	                break;
	            case 3:
	                mes = Mes.MARZO;
	                break;
	            case 4:
	                mes = Mes.ABRIL;
	                break;
	            case 5:
	                mes = Mes.MAYO;
	                break;
	            case 6:
	                mes = Mes.JUNIO;
	                break;
	            case 7:
	                mes = Mes.JULIO;
	                break;
	            case 8:
	                mes = Mes.AGOSTO;
	                break;
	            case 9:
	                mes = Mes.SEPTIEMBRE;
	                break;
	            case 10:
	                mes = Mes.OCTUBRE;
	                break;
	            case 11:
	                mes = Mes.NOVIEMBRE;
	                break;
	            case 12:
	                mes = Mes.DICIEMBRE;
	                break;
	        }
	        return mes;
	    }

	    private static void mostrarEfemerides(ArrayList<Efemeride> efemerides) {
	        if (efemerides.isEmpty()) {
	            System.out.println("No hay efemérides para mostrar.");
	        } else {
	            System.out.println("Listado de efemérides:");
	            for (Efemeride efemeride : efemerides) {
	                System.out.println("Código: " + efemeride.getCodigo() + " - Mes: " + efemeride.getMes() + " - Día: " + efemeride.getDia() + " - Detalle: " + efemeride.getDetalle());
	            }
	        }
	    }

	    private static void eliminarEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
	        if (efemerides.isEmpty()) {
	            System.out.println("No hay efemérides para eliminar.");
	        } else {
	            System.out.println("Ingrese el código de la efeméride a eliminar:");
	            String codigo = scanner.nextLine();

	            boolean eliminada = efemerides.removeIf(efemeride -> efemeride.getCodigo().equals(codigo));
	            if (eliminada) {
	                System.out.println("Efeméride eliminada con éxito.");
	            } else {
	                System.out.println("No se encontró ninguna efeméride con ese código.");
	            }
	        }
	    }

	    private static void modificarEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
	        if (efemerides.isEmpty()) {
	            System.out.println("No hay efemérides para modificar.");
	        } else {
	            System.out.println("Ingrese el código de la efeméride a modificar:");
	            String codigo = scanner.nextLine();

	            Efemeride efemerideAModificar = null;
	            for (Efemeride efemeride : efemerides) {
	                if (efemeride.getCodigo().equals(codigo)) {
	                    efemerideAModificar = efemeride;
	                    break;
	                }
	            }

	            if (efemerideAModificar == null) {
	                System.out.println("No se encontró ninguna efeméride con ese código.");
	            } else {
	                System.out.println("Ingrese el nuevo número del mes (1-12):");
	                int numMes = scanner.nextInt();
	                scanner.nextLine(); 
	                Mes nuevoMes = obtenerMesPorNumero(numMes);
	                if (nuevoMes == null) {
	                    System.out.println("Mes inválido. Por favor, ingrese un número entre 1 y 12.");
	                    return;
	                }
	                System.out.println("Ingrese el nuevo día:");
	                int nuevoDia = scanner.nextInt();
	                scanner.nextLine(); 
	                System.out.println("Ingrese el nuevo detalle:");
	                String nuevoDetalle = scanner.nextLine();

	                efemerideAModificar.setMes(nuevoMes);
	                efemerideAModificar.setDia(nuevoDia);
	                efemerideAModificar.setDetalle(nuevoDetalle);

	                System.out.println("Efeméride modificada con éxito.");
	            }
	        }
	    
	}
}


