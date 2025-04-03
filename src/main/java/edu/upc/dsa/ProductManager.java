package edu.upc.dsa;

import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Pedido;

import java.util.List;
import java.util.Map;

public interface ProductManager {

    // Listado de productos por precio (ascendente)
    List<Producto> listarProductosPorPrecioAsc();

    // Listado de productos por número de ventas (descendente)
    List<Producto> listarProductosPorVentasDesc();

    // Realizar pedido (usuario identificado, lista de productos con cantidades)
    Pedido realizarPedido(String idUsuario, Map<Producto, Integer> productosCantidad);

    // Servir el siguiente pedido (FIFO)
    Pedido servirPedido();

    // Listar pedidos servidos de un usuario
    List<Pedido> listarPedidosServidosPorUsuario(String idUsuario);

    // Añadir producto al sistema
    void añadirProducto(Producto producto);

    // Resetear la estructura (usado para los tests)
    void reset();
}
