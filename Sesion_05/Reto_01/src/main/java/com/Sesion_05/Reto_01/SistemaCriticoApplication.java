package com.Sesion_05.Reto_01;

public class SistemaCriticoApplication {
    public static void main(String[] args) throws InterruptedException {
        SimuladorSistemas simulador = new SimuladorSistemas();
        simulador.iniciarSimulacion();
        Thread.sleep(20000);
    }
}