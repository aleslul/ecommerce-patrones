package patrones.proxy.pedidos;

import model.Carrito;
import model.Pedido;
import model.Usuario;
import model.types.UsuarioRoles;
import patrones.singleton.sesion.SesionActual;

import java.util.ArrayList;
import java.util.List;

public class PedidoProxy implements PedidoManager{
    private PedidoManager pedidoService;

    public PedidoProxy(PedidoManager pedidoServiceReal) {
        this.pedidoService = pedidoServiceReal;
    }

    //TODO: ARREGLAR ESTO
    @Override
    public List<Pedido> listarPedidos() {
        Usuario usuarioActual = SesionActual.getInstance().getUsuario();
        List<Pedido> todosLosPedidos = pedidoService.listarPedidos();

        if (usuarioActual.getRol() == UsuarioRoles.ADMINISTRADOR) {
            return todosLosPedidos;
        }

        List<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido p : todosLosPedidos) {
            if (p.getUsernameCliente() != null && p.getUsernameCliente().equals(usuarioActual.getUsername())) {
                pedidosFiltrados.add(p);
            }
        }

        if (pedidosFiltrados.isEmpty()) {
            System.out.println("No tienes pedidos registrados en el sistema.");
        }
        return pedidosFiltrados;
    }

    @Override
    public Pedido crearPedido(Carrito carrito) {
        return pedidoService.crearPedido(carrito);
    }

    @Override
    public Pedido buscarPedido(String codigo) {
        Pedido pedido = pedidoService.buscarPedido(codigo);
        Usuario usuarioActual = SesionActual.getInstance().getUsuario();

        boolean esAdmin = usuarioActual.getRol() == UsuarioRoles.ADMINISTRADOR;
        boolean esPropietario = pedido != null && pedido.getUsernameCliente() != null && pedido.getUsernameCliente().equals(usuarioActual.getUsername());

        if (pedido != null && !esAdmin && !esPropietario) {
            return null;
        }
        return pedido;
    }

    @Override
    public void mostrarDetallePedido(String codigo) {
        Pedido pedido = this.buscarPedido(codigo);

        if (pedido == null) {
            System.out.println("ERROR: El pedido no se ha encontrado o no tiene permisos para verlo");
            return;
        }
        pedidoService.mostrarDetallePedido(codigo);
    }

    @Override
    public void imprimirTablaPedidos(List<Pedido> pedidos) {
        pedidoService.imprimirTablaPedidos(pedidos);
    }

    private boolean esPropietarioOAdmin(Pedido pedido) {
        Usuario usuarioActual = patrones.singleton.sesion.SesionActual.getInstance().getUsuario();
        if (usuarioActual.getRol() == model.types.UsuarioRoles.ADMINISTRADOR) {
            return true;
        }
        return pedido.getUsernameCliente() != null &&
                pedido.getUsernameCliente().equals(usuarioActual.getUsername());
    }
}
