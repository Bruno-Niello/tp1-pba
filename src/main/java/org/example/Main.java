package org.example;

import java.util.Scanner;

public class Main {

        private static Scanner scn = new Scanner(System.in);
        private static Paquete[] listaPaquetes = new Paquete[100];
        private static int contadorPaquetes = 0;
        private static Vehiculo[] listaVehiculos = new Vehiculo[100];
        private static int contadorVehiculos = 0;

    public static void main(String[] args) {

        menuPrincipal();

    }

    public static void menuPrincipal(){
        int seleccion;
        do {
            System.out.println("\n═══ BRIDGES DISTRIBUTION SYSTEM ═══");
            System.out.println("1. Gestión de paquetes");
            System.out.println("2. Gestión de vehículos");
            System.out.println("3. Gestión de puntos de distribución");
            System.out.println("4. Repartidor");
            System.out.println("0. Salir");

            while (!scn.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                scn.next();
            }
            seleccion = scn.nextInt();
            scn.nextLine();

            switch (seleccion) {
                case 1:
                    submenuPaquetes();
                    break;
                case 2:
                    submenuVehiculos();
                    break;
                case 3:
                    //submenuPuntosDistribucion();
                    break;
                case 4:
                    //submenuRepartidor();
                    break;
                case 0:
                    System.out.println("Fin.-");
                    break;
                default:
                    System.out.println("Opción inexistente, por favor ingrese un número válido: ");
            }
        } while (seleccion != 0);
    }

    public static void submenuPaquetes() {
        int seleccion;
        do {
            System.out.println("\n--- SUBMENÚ 1: GESTIÓN DE PAQUETES ---");
            System.out.println("1. Registrar paquete");
            System.out.println("2. Clonar paquete");
            System.out.println("3. Listar paquetes");
            System.out.println("4. Ordenar por prioridad");
            System.out.println("0. Volver al menú principal");

            while (!scn.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                scn.next();
            }
            seleccion = scn.nextInt();
            scn.nextLine();

            switch (seleccion) {
                case 1:
                    registrarPaquete();
                    break;
                case 2:
                    clonarPaquete();
                    break;
                case 3:
                    listarPaquetes();
                    break;
                case 4:
                    ordenarPaquetes();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inexistente, por favor ingrese un número válido: ");
            }
        } while (seleccion != 0);
    }

    private static void ordenarPaquetes() {
        for (int i = 0; i < contadorPaquetes - 1; i++) {
            for (int j = 0; j < contadorPaquetes - 1 - i; j++) {
                if (listaPaquetes[j].compareTo(listaPaquetes[j + 1]) > 0) {
                    Paquete aux = listaPaquetes[j];
                    listaPaquetes[j] = listaPaquetes[j + 1];
                    listaPaquetes[j + 1] = aux;
                }
            }
        }
    }

    private static void listarPaquetes() {
        if(listaPaquetes[0]==null){
            System.out.println("No hay paquetes registrados");
        }
        else{
            int i=0;
            do {
                System.out.println(listaPaquetes[i]);
                ++i;
            }while (listaPaquetes[i]!=null);

        }

    }

    private static void clonarPaquete() {
        if(listaPaquetes[0]==null){
            System.out.println("No hay paquetes registrados");
        }
        else if(listaPaquetes[listaPaquetes.length-1]!=null){
            System.out.println("No se puede clonar ningún paquete, el listado está lleno");
        }else{
            listarPaquetes();

            System.out.println("Ingrese el número del paquete que desea clonar: ");
            Paquete aClonar= listaPaquetes[scn.nextInt()-1];
            scn.nextLine();

            System.out.println("Ingrese el ID del paquete clon: ");
            String idClon= scn.nextLine();
                 //.clone devuelve el elemento clonado

            listaPaquetes[contadorPaquetes]=aClonar.clone();
            listaPaquetes[contadorPaquetes].setId(idClon);

            contadorPaquetes++;

        }

    }

    private static void registrarPaquete() {
        Paquete nuevoPaquete = new Paquete();

        System.out.println("\n--- Registrar Nuevo Paquete---");

        if (contadorPaquetes >= listaPaquetes.length) {
            System.out.println("Error: no es posible agregar un nuevo paquete, ya que el límite de carga fue alcanzado.");
            return;
        }

        System.out.print("Ingrese el ID: ");
        nuevoPaquete.setId(scn.nextLine());

        System.out.print("Ingrese la descripción: ");
        nuevoPaquete.setDescripcion(scn.nextLine());

        System.out.print("Ingrese el peso en kg: ");
        while (!scn.hasNextDouble()) {
            System.out.print("Peso inválido. Ingrese un número: ");
            scn.next();
        }

        nuevoPaquete.setPeso(scn.nextDouble());
        scn.nextLine();

        System.out.print("El paquete es urgente? (true/false): ");
        while (!scn.hasNextBoolean()) {
            System.out.print("Urgencia inválida, por favor ingrese 'true' si es urgente o 'false' si no lo es: ");
            scn.next();
        }
        nuevoPaquete.setUrgente(scn.nextBoolean());

        listaPaquetes[contadorPaquetes] = nuevoPaquete;
        contadorPaquetes++;
            System.out.println("Paquete registrado exitosamente");
        }

    public static void submenuVehiculos() {
        int seleccion;
        do {
            System.out.println("\n--- SUBMENÚ 1: GESTIÓN DE VEHÍCULOS ---");
            System.out.println("1. Registrar vehículo");
            System.out.println("2. Listar vehículos");           
            System.out.println("0. Volver al menú principal");

            while (!scn.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                scn.next();
            }
            seleccion = scn.nextInt();
            scn.nextLine();

            switch (seleccion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:                    
                    listarVehículo();
                    break;                
                case 0:
                    break;
                default:
                    System.out.println("Opción inexistente, por favor ingrese un número válido: ");
            }
        } while (seleccion != 0);
    }

    private static void listarVehículo() {
        if(listaVehiculos[0]==null){
            System.out.println("No hay vehículos registrados");
        }
        else{
            int i=0;
            do {
                System.out.println(listaVehiculos[i]);
                ++i;
            }while (listaVehiculos[i]!=null);

        }
    }

    private static void registrarVehiculo() {
        if (contadorVehiculos >= listaVehiculos.length) {
            System.out.println("Error: no es posible agregar un nuevo paquete, ya que el límite de carga fue alcanzado.");
            return;
        }

        System.out.println("\nIngrese el tipo de vehículo que desea registrar: ");
        System.out.println("1. Moto");
        System.out.println("2. Camión");

        int tipo;

        do {
            while (!scn.hasNextInt()) {
                System.out.print("Por favor, ingrese 1 o 2: ");
                scn.next();
            }

            tipo = scn.nextInt();
            scn.nextLine();

            if (tipo != 1 && tipo != 2) {
                System.out.print("Tipo inválido. Ingrese 1 o 2: ");
            }

        } while (tipo != 1 && tipo != 2);

        System.out.print("Ingrese el Id del vehículo: ");
        String id = scn.nextLine();

        System.out.print("Ingrese capacidad de carga en Kg: ");
        double capacidad = scn.nextDouble();
        scn.nextLine();

        System.out.print("Ingrese la batería máxima del vehículo: ");
        double bateriaMaxima = scn.nextDouble();
        scn.nextLine();

        if (tipo == 1) {
            listaVehiculos[contadorVehiculos] = new Moto(id, bateriaMaxima, capacidad);
            System.out.println("Moto registrada exitosamente");

        } else {

            System.out.print("Tiene remolque? (true/false): ");

            while (!scn.hasNextBoolean()) {
                System.out.print("Por favor, ingrese true o false: ");
                scn.next();
            }

            boolean remolque = scn.nextBoolean();
            scn.nextLine();

            listaVehiculos[contadorVehiculos] = new Camion(id, bateriaMaxima, capacidad, remolque);
            System.out.println("Camión registrado exitosamente");
        }

        contadorVehiculos++;
    }


}
