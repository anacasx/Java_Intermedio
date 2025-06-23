package com.Sesion_05.Reto_01;

import java.util.List;
import java.util.Random;

public class Semaforo {
    public static String estadoAleatorio() {
        List<String> estados = List.of("Verde", "Amarillo", "Rojo");
        return estados.get(new Random().nextInt(estados.size()));
    }
}