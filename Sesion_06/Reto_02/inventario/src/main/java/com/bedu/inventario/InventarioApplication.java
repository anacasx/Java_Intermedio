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
    public CommandLineRunner demo(ProductoRepository productoRepo,
                                  CategoriaRepository categoriaRepo,
                                  MarcaRepository marcaRepo) {
        return (args) -> {
            // Crear una categoría
            Categoria tecnologia = new Categoria("Tecnología");
            categoriaRepo.save(tecnologia);

            // Crear marcas
            Marca apple = new Marca("Apple");
            Marca samsung = new Marca("Samsung");
            marcaRepo.save(apple);
            marcaRepo.save(samsung);

            // Crear productos con categoría y marca
            productoRepo.save(new Producto("iPhone 15", "128GB", 25000.00, tecnologia, apple));
            productoRepo.save(new Producto("iPad Pro", "12.9 pulgadas", 30000.00, tecnologia, apple));
            productoRepo.save(new Producto("Galaxy S23", "256GB", 22000.00, tecnologia, samsung));
            productoRepo.save(new Producto("Smart TV Samsung", "55 pulgadas", 18000.00, tecnologia, samsung));

            // Mostrar productos por marca
            System.out.println("📚 Productos por marca:");
            marcaRepo.findAll().forEach(marca -> {
                System.out.println("🏷️ " + marca.getNombre() + ":");
                productoRepo.findAll().stream()
                        .filter(p -> p.getMarca().getId().equals(marca.getId()))
                        .forEach(p -> System.out.println("   - " + p.getNombre()));
            });
        };
    }
}
