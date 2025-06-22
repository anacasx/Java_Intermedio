package Sesion_02.Reto_01;

import java.util.concurrent.Callable;

class SistemaComunicaciones implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(600);
        return "Comunicaciones: enlace con estación terrestre establecido.";
    }
}
