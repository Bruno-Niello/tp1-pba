package Menu;

import java.util.Scanner;

public abstract class Menu {
    protected String[] opciones;
    protected Scanner scn;
    int seleccion;

    public abstract void mostrarMenu();

}
