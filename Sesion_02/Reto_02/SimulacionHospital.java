package Sesion_02.Reto_02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;


class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        System.out.println(profesional + " intentando acceder a " + nombre + "...");
        lock.lock(); 
        try {
            System.out.println(profesional + " ha ingresado a " + nombre);
            Thread.sleep(2000); 
            System.out.println(profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock(); 
        }
    }
}

public class SimulacionHospital {
    public static void main(String[] args) {
        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        Runnable draSanchez = () -> salaCirugia.usar("Dra. Sánchez");
        Runnable drGomez = () -> salaCirugia.usar("Dr. Gómez");
        Runnable enfLopez = () -> salaCirugia.usar("Enf. López");
        Runnable draPerez = () -> salaCirugia.usar("Dra. Pérez");
        Runnable drRamirez = () -> salaCirugia.usar("Dr. Ramírez");

        ExecutorService ejecutor = Executors.newFixedThreadPool(4);
        ejecutor.execute(draSanchez);
        ejecutor.execute(drGomez);
        ejecutor.execute(enfLopez);
        ejecutor.execute(draPerez);
        ejecutor.execute(drRamirez);

        ejecutor.shutdown();
    }
}