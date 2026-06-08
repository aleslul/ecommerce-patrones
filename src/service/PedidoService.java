package service;

import model.Carrito;
import model.ItemCarrito;
import model.Pedido;
import model.types.PedidoEstado;
import patrones.proxy.InventarioManager;
import repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private PedidoRepository repository;
    private InventarioManager inventarioService;

    public PedidoService(PedidoRepository repository, InventarioManager inventarioService) {
        this.repository = repository;
        this.inventarioService = inventarioService;
    }

    public Pedido crearPedido(Carrito carrito) {

        if (carrito == null || carrito.getItems().isEmpty()) {
            System.out.println("ERROR: No se puede generar un pedido de un carrito vacío");
            return null;
        }

        for (ItemCarrito item : carrito.getItems()) {
            if (!inventarioService.verificarStock(item.getProducto().getCodigo(), item.getCantidad())) {
                System.out.println("ERROR: Stock insuficiente para " + item.getProducto().getNombre());
                return null;
            }
        }

        for (ItemCarrito item : carrito.getItems()) {
            inventarioService.reducirStock(item.getProducto().getCodigo(), item.getCantidad());
        }

        Pedido pedido = new Pedido();

        pedido.setCodigo("PED-" + (repository.listar().size() + 1));

        pedido.setItems(new ArrayList<>(carrito.getItems()));

        double total = 0;

        for (ItemCarrito item : carrito.getItems()) {
            total += item.getCantidad() * item.getProducto().getPrecio();
        }

        pedido.setTotal(total);

        pedido.setEstado(PedidoEstado.PENDIENTE);

        repository.guardar(pedido);

        return pedido;
    }

    public List<Pedido> listarPedidos() {
        List<Pedido>  pedidos = repository.listar();

        if (pedidos.isEmpty()) {
            System.out.println("ERROR: No se han encontrado pedidos registrados");
        }

        return pedidos;
    }

    public Pedido buscarPedido(String codigo) {
        return repository.buscarPedidoPorCodigo(codigo);
    }

    public void mostrarDetallePedido(String codigo) {

        Pedido pedido = repository.buscarPedidoPorCodigo(codigo);

        if (pedido == null) {
            System.out.println("ERROR: Pedido no encontrado");
            return;
        }

        System.out.println("\n==================================================");
        System.out.println("PEDIDO: " + pedido.getCodigo());
        System.out.println("ESTADO: " + pedido.getEstado());
        System.out.printf("TOTAL: S/. %.2f%n", pedido.getTotal());

        System.out.println("\nPRODUCTOS:");

        System.out.printf("| %-10s | %-25s | %-10s |%n",
                "CODIGO",
                "PRODUCTO",
                "CANTIDAD");

        System.out.println("--------------------------------------------------------------");

        for (ItemCarrito item : pedido.getItems()) {

            System.out.printf("| %-10s | %-25s | %-10d |%n",
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getCantidad());
        }

        System.out.println("==================================================");
    }
}
