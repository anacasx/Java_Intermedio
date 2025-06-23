package com.bedu.inventario;

import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    protected Producto() {}

    // Constructor sin relaciones (opcional)
    public Producto(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Constructor con categoría
    public Producto(String nombre, String descripcion, double precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Constructor con categoría y marca
    public Producto(String nombre, String descripcion, double precio, Categoria categoria, Marca marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }

    public Categoria getCategoria() { return categoria; }
    public Marca getMarca() { return marca; }

    @Override
    public String toString() {
        return String.format("Producto[id=%d, nombre='%s', precio=%.2f, categoria='%s', marca='%s']",
                id, nombre, precio,
                categoria != null ? categoria.getNombre() : "Sin categoría",
                marca != null ? marca.getNombre() : "Sin marca");
    }
}
