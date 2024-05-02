package ar.unju.edu.fi.ejercicio6.main;

import ar.unju.edu.fi.ejercicio6.interfaces.duncionales.Converter;
import ar.unju.edu.fi.ejercicio6.model.FelinoDomestico;
import ar.unju.edu.fi.ejercicio6.model.FelinoSalvaje;

public class Main {

	public static void main(String[] args) {
		
		FelinoDomestico garfield = new FelinoDomestico("Garfield", (byte) 45, 12f);
        Converter<FelinoDomestico, FelinoSalvaje> converter1 = x -> new FelinoSalvaje(x.getNombre(), x.getEdad(), x.getPeso());
        if (Converter.isNotNull(garfield)) {
            FelinoSalvaje felinoSalvaje = converter1.convert(garfield);
            converter1.mostrarObjeto(felinoSalvaje);
        }

        
        FelinoSalvaje tannerSalvaje = new FelinoSalvaje("Tanner", (byte) 20, 186f);
        Converter<FelinoSalvaje, FelinoDomestico> converter2 = x -> new FelinoDomestico(x.getNombre(), x.getEdad(), x.getPeso());
        if (Converter.isNotNull(tannerSalvaje)) {
            FelinoDomestico felinoDomestico = converter2.convert(tannerSalvaje);
            converter2.mostrarObjeto(felinoDomestico);
        }
    }

}
