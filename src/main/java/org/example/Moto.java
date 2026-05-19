package org.example;

public class Moto extends Vehiculo{

    public Moto(String id, double bateriaMaxima, double capacidadCargaKg) {
        super(id, bateriaMaxima, capacidadCargaKg);
    }

    @Override
    public void desplazarse(double distanciaKm) {
        bateriaActual=bateriaActual-(distanciaKm*0.5);
        if(bateriaActual<0){
            bateriaActual=0.0;
        }
    }

    @Override
    public String descripcionTipo() {
        return "moto";
    }

    @Override
    public String toString() {
        return "Moto{" +
                "id='" + id + '\'' +
                ", bateriaActual=" + bateriaActual +
                ", bateriaMaxima=" + bateriaMaxima +
                ", capacidadCargaKg=" + capacidadCargaKg +
                '}';
    }
}
