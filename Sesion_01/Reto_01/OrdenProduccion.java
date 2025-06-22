package Sesion_01.Reto_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class OrdenProduccion {
    String codigo;
    int cantidad;

    public OrdenProduccion(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public abstract void mostrarResumen();

    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("\nÓrdenes registradas:");
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("\nProcesando órdenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada op) {
                op.agregarCosto(costoAdicional);
            }
        }
    }

    public static void main(String[] args) {
List<OrdenMasa> listaMasa = Arrays.asList(
            new OrdenMasa("A123", 500),
            new OrdenMasa("A124", 750)
        );

        List<OrdenPersonalizada> listaPersonalizadas = Arrays.asList(
            new OrdenPersonalizada("P456", 100, "ClienteX"),
            new OrdenPersonalizada("P789", 150, "ClienteY")
        );

        List<OrdenPrototipo> listaPrototipos = Arrays.asList(
            new OrdenPrototipo("T789", 10, "Diseño"),
            new OrdenPrototipo("T790", 5, "Pruebas")
        );

        // Mostrar órdenes por tipo
        mostrarOrdenes(listaMasa);
        mostrarOrdenes(listaPersonalizadas);
        mostrarOrdenes(listaPrototipos);

        // Procesar órdenes personalizadas
        procesarPersonalizadas(new ArrayList<>(listaPersonalizadas), 200);
    }

}