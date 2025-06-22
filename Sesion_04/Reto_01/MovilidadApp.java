package Sesion_04.Reto_01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MovilidadApp {

    public CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Calculando ruta...");
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2, 4));
                return "Centro -> Norte";
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupción al calcular ruta", e);
            }
        });
    }

    public CompletableFuture<Double> estimarTarifa(double distanciaKm) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Estimando tarifa...");
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 3));
                double tarifa = 10.00 + distanciaKm * 5.00;
                return tarifa;
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupción al estimar tarifa", e);
            }
        });
    }

    public void procesarSolicitud(double distanciaKm) {
        CompletableFuture<String> rutaFuture = calcularRuta()
            .exceptionally(e -> {
                System.out.println("Error en ruta: " + e.getMessage());
                return "Ruta no disponible";
            });

        CompletableFuture<Double> tarifaFuture = estimarTarifa(distanciaKm)
            .exceptionally(e -> {
                System.out.println("Error en tarifa: " + e.getMessage());
                return 0.0;
            });

        rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) ->
            String.format("Ruta calculada: %s | Tarifa estimada: $%.2f", ruta, tarifa)
        ).thenAccept(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException {
        MovilidadApp app = new MovilidadApp();
        
        double distanciaKm = 9.5;

        app.procesarSolicitud(distanciaKm);

        Thread.sleep(5000);
    }
}
