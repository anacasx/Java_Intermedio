package Sesion_05.Reto_02;

public class EventoPaciente {
    private int pacienteId;
    private int frecuenciaCardiaca;
    private int presionSistolica;
    private int presionDiastolica;
    private int spo2;

    public EventoPaciente(int pacienteId, int fc, int sist, int diast, int spo2) {
        this.pacienteId = pacienteId;
        this.frecuenciaCardiaca = fc;
        this.presionSistolica = sist;
        this.presionDiastolica = diast;
        this.spo2 = spo2;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public int getPresionSistolica() {
        return presionSistolica;
    }

    public int getPresionDiastolica() {
        return presionDiastolica;
    }

    public int getSpo2() {
        return spo2;
    }
}
