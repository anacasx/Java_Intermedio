package Sesion_03.Reto_02;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Encuesta> encuestasCentro = Arrays.asList(
            new Encuesta("Ana", "El tiempo de espera fue largo", 2),
            new Encuesta("Luis", null, 4),
            new Encuesta("María", "No me gustó la atención", 3)
        );

        List<Encuesta> encuestasNorte = Arrays.asList(
            new Encuesta("Carlos", "La atención fue buena, pero la limpieza puede mejorar", 3),
            new Encuesta("Sofía", null, 1),
            new Encuesta("Pedro", "Todo bien", 5)
        );

        List<Sucursal> sucursales = Arrays.asList(
            new Sucursal("Centro", encuestasCentro),
            new Sucursal("Norte", encuestasNorte)
        );

        sucursales.stream()
            .flatMap(sucursal -> 
                sucursal.getEncuestas().stream()
                    .filter(e -> e.getCalificacion() <= 3)
                    .flatMap(e -> e.getComentario()
                        .map(c -> Stream.of("Sucursal " + sucursal.getNombre() +
                            ": Seguimiento a paciente con comentario: \"" + c + "\""))
                        .orElseGet(Stream::empty))
            )
            .forEach(System.out::println);
    }
}
