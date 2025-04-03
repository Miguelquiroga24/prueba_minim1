package edu.upc.dsa.services;

import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Map;

@Api(value = "/products", description = "Operaciones de productos y pedidos")
@Path("/products")
public class Service {

    private final ProductManager pm;

    public Service() {
        this.pm = ProductManagerImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Listar productos por precio ascendente", response = Producto.class, responseContainer = "List")
    @Path("/precio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosPrecioAsc() {
        List<Producto> productos = this.pm.listarProductosPorPrecioAsc();
        return Response.ok(new GenericEntity<List<Producto>>(productos) {}).build();
    }

    @GET
    @ApiOperation(value = "Listar productos por número de ventas descendente", response = Producto.class, responseContainer = "List")
    @Path("/ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosVentasDesc() {
        List<Producto> productos = this.pm.listarProductosPorVentasDesc();
        return Response.ok(new GenericEntity<List<Producto>>(productos) {}).build();
    }

    @POST
    @ApiOperation(value = "Realizar pedido", response = Pedido.class)
    @Path("/pedido/{idUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarPedido(@PathParam("idUsuario") String idUsuario, Map<Producto, Integer> productos) {
        Pedido p = this.pm.realizarPedido(idUsuario, productos);
        return Response.status(201).entity(p).build();
    }

    @PUT
    @ApiOperation(value = "Servir el siguiente pedido", response = Pedido.class)
    @Path("/pedido/servir")
    @Produces(MediaType.APPLICATION_JSON)
    public Response servirPedido() {
        Pedido p = this.pm.servirPedido();
        if (p != null) return Response.ok(p).build();
        else return Response.status(404).entity("No hay pedidos por servir").build();
    }

    @GET
    @ApiOperation(value = "Listar pedidos servidos de un usuario", response = Pedido.class, responseContainer = "List")
    @Path("/pedidos/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pedidosPorUsuario(@PathParam("idUsuario") String idUsuario) {
        List<Pedido> pedidos = this.pm.listarPedidosServidosPorUsuario(idUsuario);
        return Response.ok(new GenericEntity<List<Pedido>>(pedidos) {}).build();
    }
}
