package Sesion_04.Reto_02;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {

    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture<Void> allChecks = CompletableFuture.allOf(pista, clima, trafico, personal);

        allChecks.thenRun(() -> {
            try {
                boolean pistaOk = pista.get();
                boolean climaOk = clima.get();
                boolean traficoOk = trafico.get();
                boolean personalOk = personal.get();

                System.out.println("Pista disponible: " + pistaOk);
                System.out.println("Clima favorable: " + climaOk);
                System.out.println("Tráfico aéreo despejado: " + traficoOk);
                System.out.println("Personal disponible: " + personalOk);

                if (pistaOk && climaOk && traficoOk && personalOk) {
                    System.out.println("\nAterrizaje autorizado: todas las condiciones óptimas.");
                } else {
                    System.out.println("\nAterrizaje denegado: condiciones no óptimas.");
                }
            } catch (Exception e) {
                System.out.println("\nError al verificar condiciones: " + e.getMessage());
            }
        }).exceptionally(ex -> {
            System.out.println("\nAterrizaje denegado: error inesperado - " + ex.getMessage());
            return null;
        });

        allChecks.join(); // Espera a que todas las tareas terminen correctamente

    }

    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            return probabilidad(0.8);
        }).exceptionally(ex -> {
            System.out.println("Error al verificar pista.");
            return false;
        });
    }

    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            return probabilidad(0.85);
        }).exceptionally(ex -> {
            System.out.println("Error al verificar clima.");
            return false;
        });
    }

    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            return probabilidad(0.9);
        }).exceptionally(ex -> {
            System.out.println("Error al verificar tráfico aéreo.");
            return false;
        });
    }

    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            return probabilidad(0.95);
        }).exceptionally(ex -> {
            System.out.println("Error al verificar personal en tierra.");
            return false;
        });
    }

    private static void simularLatencia() {
        try {
            TimeUnit.SECONDS.sleep(2 + random.nextInt(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean probabilidad(double probabilidad) {
        return random.nextDouble() < probabilidad;
    }
}
