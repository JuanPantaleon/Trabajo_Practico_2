package ar.unju.edu.fi.ejercicio6.main;

import ar.unju.edu.fi.ejercicio6.interfaces.duncionales.Converter;
import ar.unju.edu.fi.ejercicio6.model.FelinoDomestico;
import ar.unju.edu.fi.ejercicio6.model.FelinoSalvaje;

public class Main {

	public static void main(String[] args) {
		
		
        FelinoDomestico garfield = new FelinoDomestico("Garfield", (byte) 45, 12f);

        Converter<FelinoDomestico, FelinoSalvaje> converter = x -> new FelinoSalvaje(x.getNombre(), x.getEdad(), x.getPeso());

        FelinoSalvaje felino1 = converter.convert(garfield);


        converter.mostrarObjeto(felino1);


        FelinoSalvaje tannerSalvaje = new FelinoSalvaje("Tanner", (byte) 20, 186f);


        if (Converter.isNotNull(tannerSalvaje)) {
            
            Converter<FelinoSalvaje, FelinoDomestico> converterSalvajeADomestico = x -> new FelinoDomestico(x.getNombre(), x.getEdad(), x.getPeso());

           
            FelinoDomestico tannerDomestico = converterSalvajeADomestico.convert(tannerSalvaje);

            
            converterSalvajeADomestico.mostrarObjeto(tannerDomestico);
        }
    }
}