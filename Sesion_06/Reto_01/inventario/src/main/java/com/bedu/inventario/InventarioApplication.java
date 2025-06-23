package com.bedu.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository repository) {
        return (args) -> {
            // Guardar productos
            repository.save(new Producto("Laptop Lenovo", "Portátil de alto rendimiento", 12500.00));
            repository.save(new Producto("Mouse Logitech", "Ergonómico y preciso", 350.00));
            repository.save(new Producto("Teclado Mecánico", "Switch azul retroiluminado", 950.00));
            repository.save(new Producto("Monitor", "24 pulgadas FHD", 3200.00));

            System.out.println("\nProductos con precio mayor a 500:");
            repository.findByPrecioGreaterThan(500)
                    .forEach(System.out::println);

            System.out.println("\nProductos que contienen 'lap':");
            repository.findByNombreContainingIgnoreCase("lap")
                    .forEach(System.out::println);

            System.out.println("\nProductos con precio entre 400 y 1000:");
            repository.findByPrecioBetween(400, 1000)
                    .forEach(System.out::println);

            System.out.println("\nProductos cuyo nombre empieza con 'm':");
            repository.findByNombreStartingWithIgnoreCase("m")
                    .forEach(System.out::println);
        };
    }
}
