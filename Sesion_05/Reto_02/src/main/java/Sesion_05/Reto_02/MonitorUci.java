package Sesion_05.Reto_02;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class MonitorUci {

    private final Random random = new Random();

    public void iniciar() {
        for (int paciente = 1; paciente <= 3; paciente++) {
            int id = paciente;

            Flux.generate(sink -> {
                    sink.next(generarEvento(id));
                })
                .cast(EventoPaciente.class)
                .delayElements(Duration.ofMillis(300)) // Simula generación cada 300ms
                .filter(this::esCritico)
                .delayElements(Duration.ofSeconds(1)) // Backpressure para procesar lento
                .subscribe(this::mostrarAlerta);
        }

        // Mantener el programa vivo
        try {
            Thread.sleep(30000); // 30 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private EventoPaciente generarEvento(int pacienteId) {
        return new EventoPaciente(
            pacienteId,
            random.nextInt(90) + 40,  // FC: 40–130
            random.nextInt(80) + 70, // Sistólica: 70–150
            random.nextInt(50) + 40, // Diastólica: 40–90
            random.nextInt(15) + 85  // SpO2: 85–100
        );
    }

    private boolean esCritico(EventoPaciente e) {
        return e.getFrecuenciaCardiaca() < 50 || e.getFrecuenciaCardiaca() > 120 ||
               e.getPresionSistolica() < 90 || e.getPresionSistolica() > 140 ||
               e.getPresionDiastolica() < 60 || e.getPresionDiastolica() > 90 ||
               e.getSpo2() < 90;
    }

    private void mostrarAlerta(EventoPaciente e) {
        if (e.getFrecuenciaCardiaca() < 50 || e.getFrecuenciaCardiaca() > 120) {
            System.out.println("Paciente " + e.getPacienteId() + " - FC crítica: " + e.getFrecuenciaCardiaca() + " bpm");
        }
        if (e.getPresionSistolica() < 90 || e.getPresionSistolica() > 140 ||
            e.getPresionDiastolica() < 60 || e.getPresionDiastolica() > 90) {
            System.out.println("Paciente " + e.getPacienteId() + " - PA crítica: " +
                    e.getPresionSistolica() + "/" + e.getPresionDiastolica() + " mmHg");
        }
        if (e.getSpo2() < 90) {
            System.out.println("Paciente " + e.getPacienteId() + " - SpO2 baja: " + e.getSpo2() + "%");
        }
    }
}
