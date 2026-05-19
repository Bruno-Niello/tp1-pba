package org.example;

public class Camion extends Vehiculo {
    private boolean tieneRemolque;

    public Camion(String id, double bateriaMaxima, double capacidadCargaKg, boolean tieneRemolque) {
        super(id, bateriaMaxima, capacidadCargaKg);
        this.tieneRemolque = tieneRemolque;
    }

    @Override
    public void desplazarse(double distanciaKm) {
        if(tieneRemolque){
            bateriaActual=bateriaActual-(distanciaKm*2.5);
        }else{
            bateriaActual=bateriaActual-(distanciaKm*1.8);
        }

        if(bateriaActual<0){
            bateriaActual=0.0;
        }
    }

    @Override
    public double getConsumoPorKm() {
        return tieneRemolque ? 2.5 : 1.8;
    }

    @Override
    public String descripcionTipo() {
        return "camion";
    }

    @Override
    public String toString() {
        return "Camion{ " +
                "id='" + id + '\'' +
                ", bateriaActual=" + bateriaActual +
                ", bateriaMaxima=" + bateriaMaxima +
                ", capacidadCargaKg=" + capacidadCargaKg +
                ", tieneRemolque=" + tieneRemolque +
                '}';
    }
}
