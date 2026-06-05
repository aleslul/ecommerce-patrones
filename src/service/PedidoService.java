package service;

import model.Carrito;
import model.Pedido;
import model.types.PedidoEstado;
import repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private PedidoRepository repository;



    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    //FUNCIONES
    public Pedido crearPedido(Carrito carrito) {
        if (carrito == null || carrito.getItems().isEmpty()) {
            System.out.println("ERROR: No se puede generar un pedido de un carrito vacio");
            return null;
        }

        Pedido pedido = new Pedido();

        pedido.setItems(
                new ArrayList<>(
                        carrito.getItems()
                )
        );

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
}
