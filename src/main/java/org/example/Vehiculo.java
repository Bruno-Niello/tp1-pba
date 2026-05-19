package org.example;

public abstract class  Vehiculo {
    protected String id;
    protected double bateriaActual;
    protected double bateriaMaxima; //añadir validacion es entre 0.0 y 100
    protected double capacidadCargaKg;

    public Vehiculo(String id, double bateriaMaxima, double capacidadCargaKg) {
        this.id = id;
        this.bateriaMaxima = bateriaMaxima;
        this.bateriaActual = bateriaMaxima;
        this.capacidadCargaKg = capacidadCargaKg;
    }

    public void setBateriaActual(double bateria) {
        this.bateriaActual+=bateria;
        if(bateriaActual>bateriaMaxima){
            this.bateriaActual= this.bateriaMaxima;
        };
        if(bateriaActual<0){
            this.bateriaActual=0.0;
        }
    }

    public double getCapacidadCargaKg() {
        return capacidadCargaKg;
    }

    public void cargarBateria(double cantidad) {
        setBateriaActual(cantidad);
    };

    public double bateriaDisponible(){ //puede ser 0 la bateria maxima?
        return (bateriaActual/bateriaMaxima)*100;
    }

    public abstract void desplazarse(double distanciaKm); //consume batería según la distancia.

    public abstract String descripcionTipo();

    @Override
    public abstract String toString();


}
