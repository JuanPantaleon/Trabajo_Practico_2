package ar.unju.edu.fi.ejercicio3.constantes;

public enum Provincia {
	//Poblaciones y superficies
	
	    JUJUY(779212, 53219), 
	    SALTA(1214441, 155488), 
	    TUCUMAN(1687305, 22524), 
	    CATAMARCA(367820, 102602), 
	    LA_RIOJA(393531, 89680), 
	    SANTIAGO_DEL_ESTERO(874006, 136351); 

	    private int poblacion;
	    private int superficie;

	    
	    private Provincia(int poblacion, int superficie) {
	        this.poblacion = poblacion;
	        this.superficie = superficie;
	    }

	    

	    public int getPoblacion() {
	        return poblacion;
	    }

	    public void setPoblacion(int poblacion) {
	        this.poblacion = poblacion;
	    }

	    public int getSuperficie() {
	        return superficie;
	    }

	    public void setSuperficie(int superficie) {
	        this.superficie = superficie;
	    }

	 
	    public double calcularDensidadPoblacional() {
	        return (double) poblacion / superficie;
	    }
}
