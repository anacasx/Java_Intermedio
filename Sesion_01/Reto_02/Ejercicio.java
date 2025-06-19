package Sesion_01.Reto_02;

class Ejercicio extends MaterialCurso{
    boolean revisado;

    public Ejercicio(String titulo, String autor) {
        super(titulo, autor);
        this.revisado = false;
    }

    public void marcarRevisado() {
        revisado = true;
    }

    public boolean isRevisado() {
        return revisado;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("Ejercicio: " + titulo + " - Autor: " + autor + " - Revisado: " + revisado);
    }

    public String getTitulo() {
        return titulo;
    }
}