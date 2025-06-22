package Sesion_03.Reto_01;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Pedido {
    private String cliente;
    private String tipoEntrega;
    private String telefono; 

    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono);
    }

    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
            new Pedido("Mario", "domicilio", "555-1234"),
            new Pedido("Ana", "local", "555-9999"),
            new Pedido("Jorge", "domicilio", null),
            new Pedido("Lili", "domicilio", "555-5678")
        );

        pedidos.stream()
            .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
            .map(Pedido::getTelefono)
            .flatMap(Optional::stream)
            .map(telefono -> "Confirmación enviada al número: " + telefono)
            .forEach(System.out::println);
    }
}

