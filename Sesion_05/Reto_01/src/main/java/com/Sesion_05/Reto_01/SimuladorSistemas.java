package com.Sesion_05.Reto_01;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class SimuladorSistemas {

    private final Sinks.Many<EventoCritico> alertaGlobal = Sinks.many().multicast().onBackpressureBuffer();

    public void iniciarSimulacion() {
        var flujoCongestion = Flux.interval(Duration.ofMillis(500))
                .map(i -> (int) (Math.random() * 101))
                .filter(nivel -> nivel > 70)
                .map(nivel -> new EventoCritico("🚗 Alerta: Congestión del " + nivel + "%"))
                .onBackpressureBuffer();

        var flujoContaminacion = Flux.interval(Duration.ofMillis(600))
                .map(i -> (int) (Math.random() * 101))
                .filter(pm -> pm > 50)
                .map(pm -> new EventoCritico("🌫️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)"));

        var flujoAccidentes = Flux.interval(Duration.ofMillis(800))
                .map(i -> Accidente.generarAleatorio())
                .filter(a -> a.prioridad().equals("Alta"))
                .map(a -> new EventoCritico("🚑 Emergencia vial: Accidente con prioridad Alta"));

        var flujoTrenes = Flux.interval(Duration.ofMillis(700))
                .map(i -> (int) (Math.random() * 11))
                .filter(min -> min > 5)
                .map(min -> new EventoCritico("🚝 Tren maglev con retraso crítico: " + min + " minutos"))
                .delayElements(Duration.ofMillis(100));

        AtomicInteger contadorRojo = new AtomicInteger(0);
        var flujoSemaforos = Flux.interval(Duration.ofMillis(400))
                .map(i -> Semaforo.estadoAleatorio())
                .map(estado -> {
                    if (estado.equals("Rojo")) {
                        int cuenta = contadorRojo.incrementAndGet();
                        if (cuenta >= 3) {
                            contadorRojo.set(0);
                            return new EventoCritico("🚦 Semáforo en Rojo detectado 3 veces seguidas");
                        }
                    } else {
                        contadorRojo.set(0);
                    }
                    return null;
                })
                .filter(e -> e != null);

        Flux<EventoCritico> todos = Flux.merge(
                flujoCongestion, flujoContaminacion, flujoAccidentes, flujoTrenes, flujoSemaforos
        );

        todos.doOnNext(evento -> {
                    System.out.println(evento.mensaje());
                    alertaGlobal.tryEmitNext(evento);
                })
                .subscribe();

        alertaGlobal.asFlux()
                .buffer(Duration.ofSeconds(1))
                .filter(lista -> lista.size() >= 3)
                .map(lista -> "🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime")
                .distinctUntilChanged()
                .subscribe(System.out::println);
    }
}