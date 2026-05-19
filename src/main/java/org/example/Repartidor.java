package org.example;

public class Repartidor {
    private String nombre;
    private double resistencia; //entre 0.0 y 100.0. Inicializa en 100.0.
    private double pesoMaximoCarga;
    private PuntoDeDistribucion ubicacionActual;
    private Vehiculo vehiculoActual;


    public Repartidor(String nombre, double pesoMaximoCarga, PuntoDeDistribucion ubicacionActual) {
        this.nombre = nombre;
        this.pesoMaximoCarga = pesoMaximoCarga;
        this.resistencia = 100;
        this.ubicacionActual= ubicacionActual;
    }

    public void viajarA(PuntoDeDistribucion destino){
        double distancia = this.ubicacionActual.distanciaA(destino);
        //si tiene vehículo, llama a desplazarse() del vehículo con la distancia calculada.
        if(this.vehiculoActual!=null){
            this.vehiculoActual.desplazarse(distancia);
        }else{
            double resistenciaRequerida = distancia * 0.8;
            if(this.resistencia>resistenciaRequerida){
                this.resistencia-=distancia*0.8;
            }
        }
        this.ubicacionActual=destino;
        //Si va a pie, reduce su resistencia (resistencia -= distancia * 0.8). Actualiza ubicacionActual. Dentro de este viaje, primero se debe validar que se pueda realizar.

    }

    public boolean puedeCargar(Paquete p){
        if(this.vehiculoActual!=null){
            if(p.getPeso()>this.vehiculoActual.getCapacidadCargaKg()){
                return false;
            }
        }else{
            if(p.getPeso()>this.pesoMaximoCarga && this.resistencia > 20.0){
                return false;
            }
        }
        return true;
    }

    public void descansar(){
        this.resistencia+=30.0;
        if(this.resistencia>100){
            this.resistencia=100.0;
        }
    }

    public void equiparVehiculo(Vehiculo v){
        this.vehiculoActual=v;
    }

    public void desequiparVehiculo(){
        this.vehiculoActual=null;
    }

    public Vehiculo getVehiculoActual(){
        return this.vehiculoActual;
    }

    public PuntoDeDistribucion getUbicacionActual(){
        return this.ubicacionActual;
    }

    public boolean puedeViajarA(PuntoDeDistribucion destino){
        if(destino == null){
            return false;
        }
        double distancia = this.ubicacionActual.distanciaA(destino);
        if(this.vehiculoActual!=null){
            return this.vehiculoActual.getBateriaActual() >= distancia * this.vehiculoActual.getConsumoPorKm();
        }
        return this.resistencia >= distancia * 0.8;
    }

    public double energiaNecesariaParaViajar(PuntoDeDistribucion destino){
        if(destino == null){
            return 0.0;
        }
        double distancia = this.ubicacionActual.distanciaA(destino);
        if(this.vehiculoActual!=null){
            return distancia * this.vehiculoActual.getConsumoPorKm();
        }
        return distancia * 0.8;
    }

    @Override
    public String toString() {
        return "Repartidor{" +
                "nombre='" + nombre + '\'' +
                ", resistencia=" + resistencia +
                ", pesoMaximoCarga=" + pesoMaximoCarga +
                ", ubicacionActual=" + ubicacionActual +
                ", vehiculoActual=" + vehiculoActual +
                '}';
    }
}


/*



        equiparVehiculo(Vehiculo v) y desequiparVehiculo()
        toString()

*/