package Sesion_01.Reto_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class MaterialCurso {

    String titulo;
    String autor;

    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public abstract void mostrarDetalle();

    public String getAutor() {
        return autor;
    }

    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        //(Muestra el detalle de todos los materiales).
        System.out.println("\nMateriales del curso:");
        for (MaterialCurso m : lista) {
            m.mostrarDetalle();
        }
    }

    public static void contarDuracionVideos(List<? extends Video> lista) {
        //(Suma y muestra la duración total de los videos).
        int total = 0;
        for (Video v : lista) {
            total += v.getDuracion();
        }
        System.out.println("\nDuración total de videos: " + total + " minutos\n");
    }

    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        //(Actualiza el estado de los ejercicios a revisado = true y muestra un mensaje por cada uno).
        for (Object obj : lista) {
            if (obj instanceof Ejercicio ejercicio) {
                ejercicio.marcarRevisado();
                System.out.println("Ejercicio '" + ejercicio.getTitulo() + "' marcado como revisado.");
            }
        }
    }

    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();
        Video v1 = new Video("Introducción a Java", "Mario", 15);
        Video v2 = new Video("POO en Java", "Carlos", 20);
        Articulo a1 = new Articulo("Historia de Java", "Ana", 1200);
        Articulo a2 = new Articulo("Tipos de datos", "Luis", 800);
        Ejercicio e1 = new Ejercicio("Variables y tipos", "Luis");
        Ejercicio e2 = new Ejercicio("Condicionales", "Mario");

        materiales.add(v1);
        materiales.add(v2);
        materiales.add(a1);
        materiales.add(a2);
        materiales.add(e1);
        materiales.add(e2);

        MaterialCurso.mostrarMateriales(materiales);
        MaterialCurso.contarDuracionVideos(Arrays.asList(v1, v2));
        MaterialCurso.marcarEjerciciosRevisados(materiales);


    }
}
