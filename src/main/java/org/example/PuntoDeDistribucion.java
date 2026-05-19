package org.example;

public class PuntoDeDistribucion implements Conectable{
    private String nombre;
    private double coordenadaX;
    private double coordenadaY;
    private boolean conectado;
    private boolean visitado;

    // Constructor
    public PuntoDeDistribucion(String nombre, double coordenadaX, double coordenadaY) {
        this.nombre = nombre;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.conectado = false;
        this.visitado = false;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public void marcarVisitado() {
        this.visitado = true;
        this.conectado = true;
    }

    /**
     * Calcula la distancia euclidiana entre este punto y otro punto de distribución.
     * Usa la fórmula: d = sqrt((x2 - x1)^2 + (y2 - y1)^2)
     */
    public double distanciaA(PuntoDeDistribucion otro) {
        if (otro == null) {
            throw new IllegalArgumentException("El otro punto de distribución no puede ser nulo.");
        }
        double deltaX = otro.getCoordenadaX() - this.coordenadaX;
        double deltaY = otro.getCoordenadaY() - this.coordenadaY;

        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    @Override
    public String toString() {
        return "PuntoDeDistribucion{" +
                "nombre='" + nombre + '\'' +
                ", coordenadaX=" + coordenadaX +
                ", coordenadaY=" + coordenadaY +
                ", conectado=" + conectado +
                ", visitado=" + visitado +
                '}';
    }

    @Override
    public boolean conectarALaRed() {
        return visitado;
    }

    @Override
    public boolean estaConectado() {
        return conectado;
    }

    @Override
    public String getNombreNodo() {
        return nombre;
    }
}
