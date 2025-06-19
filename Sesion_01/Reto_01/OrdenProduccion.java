package Sesion_01.Reto_01;

import java.util.ArrayList;
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
        List<OrdenProduccion> ordenes = new ArrayList<>();
        ordenes.add(new OrdenMasa("M001", 500));
        ordenes.add(new OrdenMasa("M002", 1000));
        ordenes.add(new OrdenPersonalizada("P001", 200, "Cliente A"));
        ordenes.add(new OrdenPersonalizada("P002", 150, "Cliente B"));
        ordenes.add(new OrdenPrototipo("T001", 50, "Fase inicial"));
        ordenes.add(new OrdenPrototipo("T002", 30, "Fase avanzada"));

        //System.out.println("--- Mostrando todas las órdenes ---");
        mostrarOrdenes(ordenes);

        System.out.println("\nProcesando órdenes personalizadas...");
        procesarPersonalizadas(ordenes, 300);
    }

}