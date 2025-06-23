package com.Sesion_05.Reto_01;

import java.util.List;
import java.util.Random;

public record Accidente(String prioridad) {
    public static Accidente generarAleatorio() {
        List<String> niveles = List.of("Baja", "Media", "Alta");
        return new Accidente(niveles.get(new Random().nextInt(niveles.size())));
    }
}