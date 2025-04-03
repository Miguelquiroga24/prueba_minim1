package edu.upc.dsa.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa un pedido que contiene varios productos y cantidades.
 */
public class Pedido {
    private String idPedido;
    private String idUsuario;
    private Map<Producto, Integer> productos;
    private Date fecha;
    private boolean servido;

    public Pedido(String idPedido, String idUsuario) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.productos = new HashMap<>();
        this.fecha = new Date();
        this.servido = false;
    }

    public void añadirProducto(Producto producto, int cantidad) {
        productos.merge(producto, cantidad, Integer::sum);
    }

    // Getters y Setters
    public String getIdPedido() { return idPedido; }
    public void setIdPedido(String idPedido) { this.idPedido = idPedido; }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public Map<Producto, Integer> getProductos() { return productos; }
    public void setProductos(Map<Producto, Integer> productos) { this.productos = productos; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public boolean isServido() { return servido; }
    public void setServido(boolean servido) { this.servido = servido; }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido='" + idPedido + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", productos=" + productos +
                ", fecha=" + fecha +
                ", servido=" + servido +
                '}';
    }
}
