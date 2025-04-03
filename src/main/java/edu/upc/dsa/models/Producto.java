package edu.upc.dsa.models;

/**
 * Representa un producto disponible en el campus.
 */
public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int numeroVentas;

    public Producto(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.numeroVentas = 0;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getNumeroVentas() { return numeroVentas; }
    public void setNumeroVentas(int numeroVentas) { this.numeroVentas = numeroVentas; }

    public void incrementarVentas(int cantidad) {
        this.numeroVentas += cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", numeroVentas=" + numeroVentas +
                '}';
    }
}
