package edu.upc.dsa;

import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Pedido;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerImpl implements ProductManager {

    private static ProductManagerImpl instance;

    public static ProductManagerImpl getInstance() {
        if (instance == null) instance = new ProductManagerImpl();
        return instance;
    }

    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    private List<Producto> productos;
    private List<Pedido> pedidos;
    private Queue<Pedido> colaPedidos;

    private ProductManagerImpl() {
        this.productos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.colaPedidos = new LinkedList<>();
    }

    @Override
    public List<Producto> listarProductosPorPrecioAsc() {
        logger.info("Inicio listarProductosPorPrecioAsc()");
        List<Producto> ordenados = new ArrayList<>(this.productos);
        ordenados.sort(Comparator.comparingDouble(Producto::getPrecio));
        logger.info("Fin listarProductosPorPrecioAsc() => " + ordenados);
        return ordenados;
    }

    @Override
    public List<Producto> listarProductosPorVentasDesc() {
        logger.info("Inicio listarProductosPorVentasDesc()");
        List<Producto> ordenados = new ArrayList<>(this.productos);
        ordenados.sort((p1, p2) -> Integer.compare(p2.getNumeroVentas(), p1.getNumeroVentas()));
        logger.info("Fin listarProductosPorVentasDesc() => " + ordenados);
        return ordenados;
    }

    @Override
    public Pedido realizarPedido(String idUsuario, Map<Producto, Integer> productosCantidad) {
        logger.info("Inicio realizarPedido() => usuario: " + idUsuario + ", productos: " + productosCantidad);
        String idPedido = UUID.randomUUID().toString();
        Pedido pedido = new Pedido(idPedido, idUsuario);

        for (Map.Entry<Producto, Integer> entry : productosCantidad.entrySet()) {
            Producto p = entry.getKey();
            int cantidad = entry.getValue();
            pedido.añadirProducto(p, cantidad);
            p.incrementarVentas(cantidad);
        }

        this.pedidos.add(pedido);
        this.colaPedidos.offer(pedido);
        logger.info("Fin realizarPedido() => Pedido: " + pedido);
        return pedido;
    }

    @Override
    public Pedido servirPedido() {
        logger.info("Inicio servirPedido()");
        Pedido pedido = this.colaPedidos.poll();

        if (pedido != null) {
            pedido.setServido(true);
            logger.info("Fin servirPedido() => Pedido servido: " + pedido);
        } else {
            logger.error("servirPedido() => No hay pedidos en cola");
        }

        return pedido;
    }

    @Override
    public List<Pedido> listarPedidosServidosPorUsuario(String idUsuario) {
        logger.info("Inicio listarPedidosServidosPorUsuario(" + idUsuario + ")");
        List<Pedido> lista = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getIdUsuario().equals(idUsuario) && p.isServido()) {
                lista.add(p);
            }
        }
        logger.info("Fin listarPedidosServidosPorUsuario() => " + lista.size() + " pedidos encontrados");
        return lista;
    }

    @Override
    public void añadirProducto(Producto producto) {
        logger.info("Inicio añadirProducto(): " + producto);
        this.productos.add(producto);
        logger.info("Fin añadirProducto()");
    }

    @Override
    public void reset() {
        logger.info("Reset: limpiando estructuras de datos internas");
        this.productos.clear();
        this.pedidos.clear();
        this.colaPedidos.clear();
    }
}
