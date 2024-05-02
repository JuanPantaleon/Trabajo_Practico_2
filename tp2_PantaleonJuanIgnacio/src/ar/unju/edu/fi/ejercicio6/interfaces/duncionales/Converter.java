package ar.unju.edu.fi.ejercicio6.interfaces.duncionales;
@FunctionalInterface
public interface Converter <T, t1>{
	T1 convert(T t);
	
	static <T> boolean isNotNull(T t) {
        return t != null;
    }
	
	default void mostrarObjeto(T1 t1) {
        System.out.println("Objeto - " + t1.tostring());
    }
}
