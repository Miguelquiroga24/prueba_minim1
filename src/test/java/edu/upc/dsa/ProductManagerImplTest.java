package edu.upc.dsa;

import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Pedido;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ProductManagerImplTest {

    ProductManager pm;

    @Before
    public void setUp() {
        this.pm = ProductManagerImpl.getInstance();
        this.pm.reset();

        // Añadimos productos de ejemplo
        pm.añadirProducto(new Producto("1", "Café", 1.2));
        pm.añadirProducto(new Producto("2", "Bocadillo", 2.5));
        pm.añadirProducto(new Producto("3", "Agua", 1.0));
    }

    @After
    public void tearDown() {
        this.pm.reset();
    }

    @Test
    public void testRealizarPedido() {
        Map<Producto, Integer> mapaPedido = new HashMap<>();
        Producto p1 = new Producto("4", "Zumo", 1.5);
        pm.añadirProducto(p1);
        mapaPedido.put(p1, 2);

        Pedido pedido = pm.realizarPedido("usuario123", mapaPedido);
        assertNotNull(pedido);
        assertEquals("usuario123", pedido.getIdUsuario());
        assertEquals(1, pedido.getProductos().size());
        assertFalse(pedido.isServido());
    }

    @Test
    public void testServirPedido() {
        Map<Producto, Integer> pedido1 = new HashMap<>();
        pedido1.put(new Producto("5", "Té", 1.3), 1);
        pm.añadirProducto(new Producto("5", "Té", 1.3));
        pm.realizarPedido("usu1", pedido1);

        Pedido servido = pm.servirPedido();
        assertNotNull(servido);
        assertTrue(servido.isServido());
    }
}
