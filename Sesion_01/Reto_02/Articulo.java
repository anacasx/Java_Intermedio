package Sesion_01.Reto_02;

class Articulo extends MaterialCurso{
    int palabras;

    public Articulo(String titulo, String autor, int palabras){
        super(titulo, autor);
        this.palabras = palabras;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("Artículo: " + titulo + " - Autor: " + autor + " - Palabras: " + palabras);
    }
}