package org.example;

public class Paquete implements Comparable<Paquete>, Cloneable{
    private String id; //identificador único.
    private String descripcion;
    private double peso; //en kilogramos.
    private boolean urgente; // indica si la entrega es prioritaria.

    public Paquete() {
    }

    public Paquete(String id, String descripcion, double peso, boolean urgente) {
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
        this.urgente = urgente;
    }

    public void setId(String id) {
        if (id == null) {
            this.id = null;
        } else {
            this.id = id.toLowerCase();
        }
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public double getPeso(){
        return peso;
    }

    public Paquete clone(){
        Paquete clon = new Paquete(this.id, this.descripcion, this.peso, this.urgente);
        return clon;
    }

    @Override
    public int compareTo(Paquete otro) {
        int resultado = 0;

        if (this.urgente == true && otro.urgente == false) {
            resultado = -1;
        }
        else if (this.urgente == false && otro.urgente == true) {
            resultado = 1;
        }
        else {
            if (this.peso < otro.peso) {
                resultado = -1;
            }
            else if (this.peso > otro.peso) {
                resultado = 1;
            }
            else {
                int compId = this.id.compareTo(otro.id);

                if (compId < 0) {
                    resultado = -1;
                }
                else if (compId > 0) {
                    resultado = 1;
                }
                else {
                    resultado = 0;
                }
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "Paquete n° " + (id == null ? "" : id) + " {" +
            "descripcion='" + descripcion + '\'' +
            ", peso=" + peso + "Kg" +
            ", urgente=" + urgente +
            '}';
    }
}
