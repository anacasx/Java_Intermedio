package Sesion_02.Reto_01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MisionEspacial {
    public static void main(String[] args) {
        System.out.println("Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> soporte = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());
        Future<String> comunicaciones = executor.submit(new SistemaComunicaciones());

        try {
            System.out.println(comunicaciones.get());
            System.out.println(soporte.get());
            System.out.println(termico.get());
            System.out.println(nav.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("Todos los sistemas reportan estado operativo.");
    }
}
