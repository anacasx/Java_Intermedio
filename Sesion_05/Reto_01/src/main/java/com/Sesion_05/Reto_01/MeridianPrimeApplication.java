package com.Sesion_05.Reto_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class MeridianPrimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeridianPrimeApplication.class, args);
        setupMonitoringSystems();
    }

    private static void setupMonitoringSystems() {
        Random random = new Random();
        
        // 1. Sensores de Tráfico (con backpressure)
        Flux<Integer> trafficSensors = Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101)) // 0-100% congestión
                .onBackpressureBuffer(1000, 
                    x -> System.out.println("Buffer lleno! Descartando: " + x))
                .delayElements(Duration.ofMillis(500))
                .filter(congestion -> congestion > 70)
                .doOnNext(congestion -> 
                    System.out.println("🚗 ALERTA: Congestión crítica: " + congestion + "%"))
                .subscribeOn(Schedulers.parallel());

        // 2. Contaminación del Aire
        Flux<Integer> airPollution = Flux.interval(Duration.ofMillis(600))
                .map(i -> random.nextInt(100)) // 0-99 ug/m3
                .filter(pm25 -> pm25 > 50)
                .doOnNext(pm25 -> 
                    System.out.println("🌫️ ALERTA: Contaminación alta: " + pm25 + " ug/m3"))
                .subscribeOn(Schedulers.parallel());

        // 3. Accidentes Viales
        String[] priorities = {"Baja", "Media", "Alta"};
        Flux<String> accidents = Flux.interval(Duration.ofMillis(800))
                .map(i -> priorities[random.nextInt(3)])
                .filter(priority -> priority.equals("Alta"))
                .doOnNext(priority -> 
                    System.out.println("🚑 ALERTA: Accidente prioridad " + priority))
                .subscribeOn(Schedulers.parallel());

        // 4. Trenes Maglev (con backpressure)
        Flux<Integer> maglevTrains = Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11)) // 0-10 minutos
                .onBackpressureBuffer()
                .delayElements(Duration.ofMillis(700))
                .filter(delay -> delay > 5)
                .doOnNext(delay -> 
                    System.out.println("🚝 ALERTA: Retraso crítico: " + delay + " minutos"))
                .subscribeOn(Schedulers.parallel());

        // 5. Semáforos Inteligentes
        String[] states = {"Verde", "Amarillo", "Rojo"};
        AtomicInteger redCount = new AtomicInteger(0);
        Flux<String> smartTrafficLights = Flux.interval(Duration.ofMillis(400))
                .map(i -> states[random.nextInt(3)])
                .map(state -> {
                    if (state.equals("Rojo")) {
                        redCount.incrementAndGet();
                    } else {
                        redCount.set(0);
                    }
                    return state + ":" + redCount.get();
                })
                .filter(stateCount -> {
                    String[] parts = stateCount.split(":");
                    return parts[0].equals("Rojo") && Integer.parseInt(parts[1]) > 3;
                })
                .doOnNext(stateCount -> 
                    System.out.println("🚦 ALERTA: Semáforo en rojo " + stateCount.split(":")[1] + " veces consecutivas"))
                .subscribeOn(Schedulers.parallel());

        // Subscripciones
        trafficSensors.subscribe();
        airPollution.subscribe();
        accidents.subscribe();
        maglevTrains.subscribe();
        smartTrafficLights.subscribe();

        // Mantener la aplicación corriendo
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}