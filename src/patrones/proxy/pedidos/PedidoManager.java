package patrones.proxy.pedidos;

import model.Carrito;
import model.Pedido;

import java.util.List;

public interface PedidoManager {
    List<Pedido> listarPedidos();
    Pedido crearPedido(Carrito carrito);
    Pedido buscarPedido(String codigo);
    void mostrarDetallePedido(String codigo);
    void imprimirTablaPedidos(List<Pedido> pedidos);
}
