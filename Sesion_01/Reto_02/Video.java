package Sesion_01.Reto_02;

class Video extends MaterialCurso{
    int duracion;

    public Video(String titulo, String autor, int duracion){
        super(titulo, autor);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("Video: " + titulo + " - Autor: " + autor + " - Duración: " + duracion + " min");
    }
}
