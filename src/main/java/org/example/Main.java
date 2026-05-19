package org.example;

import java.util.Scanner;

public class Main {

    private static Scanner scn = new Scanner(System.in);
    private static Paquete[] listaPaquetes = new Paquete[100];
    private static int contadorPaquetes = 0;
    private static Vehiculo[] listaVehiculos = new Vehiculo[100];
    private static int contadorVehiculos = 0;
    private static PuntoDeDistribucion[] listaPuntos = new PuntoDeDistribucion[100];
    private static int contadorPuntos = 0;
    private static Repartidor sam;

    public static void main(String[] args) {
        inicializarSistema();
        menuPrincipal();
    }

    private static void inicializarSistema() {
        PuntoDeDistribucion base = new PuntoDeDistribucion("Base Central", 0.0, 0.0);
        base.marcarVisitado();
        listaPuntos[contadorPuntos++] = base;
        sam = new Repartidor("Sam", 25.0, base);
    }

    public static void menuPrincipal() {
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
                    submenuPuntosDistribucion();
                    break;
                case 4:
                    submenuRepartidor();
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
        if (contadorPaquetes == 0) {
            System.out.println("No hay paquetes registrados");
            return;
        }
        for (int i = 0; i < contadorPaquetes; i++) {
            System.out.println((i + 1) + ". " + listaPaquetes[i]);
        }
    }

    private static void clonarPaquete() {
        if (contadorPaquetes == 0) {
            System.out.println("No hay paquetes registrados");
            return;
        }
        if (contadorPaquetes >= listaPaquetes.length) {
            System.out.println("No se puede clonar ningún paquete, el listado está lleno");
            return;
        }

        listarPaquetes();
        System.out.print("Ingrese el número del paquete que desea clonar: ");
        int indice = leerEnteroValido(1, contadorPaquetes) - 1;

        System.out.print("Ingrese el ID del paquete clon: ");
        String idClon = scn.nextLine();

        listaPaquetes[contadorPaquetes] = listaPaquetes[indice].clone();
        listaPaquetes[contadorPaquetes].setId(idClon);
        contadorPaquetes++;
        System.out.println("Paquete clonado exitosamente");
    }

    private static void registrarPaquete() {
        Paquete nuevoPaquete = new Paquete();

        System.out.println("\n--- Registrar Nuevo Paquete ---");

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
        scn.nextLine();

        listaPaquetes[contadorPaquetes] = nuevoPaquete;
        contadorPaquetes++;
        System.out.println("Paquete registrado exitosamente");
    }

    public static void submenuVehiculos() {
        int seleccion;
        do {
            System.out.println("\n--- SUBMENÚ 2: GESTIÓN DE VEHÍCULOS ---");
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
                    listarVehiculos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inexistente, por favor ingrese un número válido: ");
            }
        } while (seleccion != 0);
    }

    private static void listarVehiculos() {
        if (contadorVehiculos == 0) {
            System.out.println("No hay vehículos registrados");
            return;
        }
        for (int i = 0; i < contadorVehiculos; i++) {
            System.out.println((i + 1) + ". " + listaVehiculos[i]);
        }
    }

    private static void registrarVehiculo() {
        if (contadorVehiculos >= listaVehiculos.length) {
            System.out.println("Error: no es posible agregar un nuevo vehículo, ya que el límite fue alcanzado.");
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
        while (!scn.hasNextDouble()) {
            System.out.print("Capacidad inválida. Ingrese un número: ");
            scn.next();
        }
        double capacidad = scn.nextDouble();
        scn.nextLine();

        System.out.print("Ingrese la batería máxima del vehículo: ");
        while (!scn.hasNextDouble()) {
            System.out.print("Batería inválida. Ingrese un número: ");
            scn.next();
        }
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
            System.out.println("Camión registrada exitosamente");
        }

        contadorVehiculos++;
    }

    public static void submenuPuntosDistribucion() {
        int seleccion;
        do {
            System.out.println("\n--- SUBMENÚ 3: GESTIÓN DE PUNTOS DE DISTRIBUCIÓN ---");
            System.out.println("1. Registrar punto de distribución");
            System.out.println("2. Listar puntos de distribución");
            System.out.println("0. Volver al menú principal");

            while (!scn.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                scn.next();
            }
            seleccion = scn.nextInt();
            scn.nextLine();

            switch (seleccion) {
                case 1:
                    registrarPuntoDistribucion();
                    break;
                case 2:
                    listarPuntosDistribucion();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inexistente, por favor ingrese un número válido: ");
            }
        } while (seleccion != 0);
    }

    private static void registrarPuntoDistribucion() {
        if (contadorPuntos >= listaPuntos.length) {
            System.out.println("Error: no es posible agregar más puntos de distribución, el límite fue alcanzado.");
            return;
        }

        System.out.print("Ingrese el nombre del punto de distribución: ");
        String nombre = scn.nextLine();

        System.out.print("Ingrese la coordenada X: ");
        while (!scn.hasNextDouble()) {
            System.out.print("Coordenada inválida. Ingrese un número: ");
            scn.next();
        }
        double x = scn.nextDouble();
        scn.nextLine();

        System.out.print("Ingrese la coordenada Y: ");
        while (!scn.hasNextDouble()) {
            System.out.print("Coordenada inválida. Ingrese un número: ");
            scn.next();
        }
        double y = scn.nextDouble();
        scn.nextLine();

        listaPuntos[contadorPuntos++] = new PuntoDeDistribucion(nombre, x, y);
        System.out.println("Punto de distribución registrado exitosamente");
    }

    private static void listarPuntosDistribucion() {
        if (contadorPuntos == 0) {
            System.out.println("No hay puntos de distribución registrados");
            return;
        }
        for (int i = 0; i < contadorPuntos; i++) {
            System.out.println((i + 1) + ". " + listaPuntos[i]);
        }
    }

    public static void submenuRepartidor() {
        int seleccion;
        do {
            System.out.println("\n--- SUBMENÚ 4: REPARTIDOR ---");
            System.out.println("1. Equipar vehículo");
            System.out.println("2. Viajar a un destino");
            System.out.println("3. Ver estado del repartidor");
            System.out.println("4. Descansar");
            System.out.println("5. Cargar batería");
            System.out.println("6. Simular carga");
            System.out.println("0. Volver al menú principal");

            while (!scn.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                scn.next();
            }
            seleccion = scn.nextInt();
            scn.nextLine();

            switch (seleccion) {
                case 1:
                    equiparVehiculo();
                    break;
                case 2:
                    viajarADestino();
                    break;
                case 3:
                    mostrarEstadoRepartidor();
                    break;
                case 4:
                    descansarRepartidor();
                    break;
                case 5:
                    cargarBateriaVehiculo();
                    break;
                case 6:
                    simularCarga();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inexistente, por favor ingrese un número válido: ");
            }
        } while (seleccion != 0);
    }

    private static void equiparVehiculo() {
        if (contadorVehiculos == 0) {
            System.out.println("No hay vehículos registrados para equipar.");
            return;
        }
        listarVehiculos();
        System.out.print("Seleccione el número del vehículo que desea equipar: ");
        int indice = leerEnteroValido(1, contadorVehiculos) - 1;
        sam.equiparVehiculo(listaVehiculos[indice]);
        System.out.println("Sam ahora está equipado con el vehículo: " + listaVehiculos[indice]);
    }

    private static void viajarADestino() {
        if (contadorPuntos == 0) {
            System.out.println("No hay puntos de distribución registrados.");
            return;
        }

        System.out.println("Ubicación actual de Sam: " + sam.getUbicacionActual());
        listarPuntosDistribucion();
        System.out.print("Seleccione el número del punto de destino: ");
        int indice = leerEnteroValido(1, contadorPuntos) - 1;
        PuntoDeDistribucion destino = listaPuntos[indice];

        if (destino == sam.getUbicacionActual()) {
            System.out.println("Ya estás en ese punto de distribución.");
            return;
        }

        if (!sam.puedeViajarA(destino)) {
            System.out.println("Sam no tiene suficientes recursos para llegar a ese destino.");
            return;
        }

        double consumo = sam.energiaNecesariaParaViajar(destino);
        sam.viajarA(destino);
        destino.marcarVisitado();

        if (sam.getVehiculoActual() != null) {
            System.out.printf("Sam viajó en vehículo. Batería consumida: %.2f%n", consumo);
        } else {
            System.out.printf("Sam viajó a pie. Resistencia consumida: %.2f%n", consumo);
        }
    }

    private static void mostrarEstadoRepartidor() {
        System.out.println(sam);
    }

    private static void descansarRepartidor() {
        sam.descansar();
        System.out.println("Sam descansó. Estado actual: " + sam);
    }

    private static void cargarBateriaVehiculo() {
        if (sam.getVehiculoActual() == null) {
            System.out.println("Sam no tiene un vehículo equipado.");
            return;
        }

        System.out.print("Ingrese la cantidad de energía a recargar: ");
        while (!scn.hasNextDouble()) {
            System.out.print("Valor inválido. Ingrese un número: ");
            scn.next();
        }
        double energia = scn.nextDouble();
        scn.nextLine();

        if (energia <= 0) {
            System.out.println("La energía a recargar debe ser mayor que cero.");
            return;
        }

        sam.getVehiculoActual().cargarBateria(energia);
        System.out.println("Carga realizada. Vehículo ahora: " + sam.getVehiculoActual());
    }

    private static void simularCarga() {
        if (contadorPaquetes == 0) {
            System.out.println("No hay paquetes registrados para simular carga.");
            return;
        }

        listarPaquetes();
        System.out.print("Seleccione el número del paquete para simular la carga: ");
        int indice = leerEnteroValido(1, contadorPaquetes) - 1;
        Paquete paquete = listaPaquetes[indice];

        if (sam.puedeCargar(paquete)) {
            System.out.println("Sam puede transportar este paquete con su equipamiento y estado actual.");
        } else {
            System.out.println("Sam NO puede transportar este paquete con su equipamiento y estado actual.");
        }
    }

    private static int leerEnteroValido(int minimo, int maximo) {
        while (true) {
            String linea = scn.nextLine();
            try {
                int valor = Integer.parseInt(linea.trim());
                if (valor < minimo || valor > maximo) {
                    System.out.printf("Ingrese un valor entre %d y %d: ", minimo, maximo);
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número válido: ");
            }
        }
    }
}
