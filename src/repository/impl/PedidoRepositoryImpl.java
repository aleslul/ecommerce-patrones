package repository.impl;

import model.ItemCarrito;
import model.Pedido;
import repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;

public class PedidoRepositoryImpl implements PedidoRepository {
    private List<Pedido> pedidos;

    public PedidoRepositoryImpl() {
        this.pedidos = new ArrayList<>();
    }

    public PedidoRepositoryImpl(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public void guardar(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public List<Pedido> listar() {
        return pedidos;
    }

    /*
    @Override
    public List<Pedido> listar() {

        if (pedidos.isEmpty()) {
            return pedidos;
        }

        System.out.println("\n=========================================================================");
        System.out.printf("| %-10s | %-15s | %-15s | %-15s |%n",
                "CODIGO",
                "PRODUCTOS",
                "TOTAL",
                "ESTADO");

        System.out.println("=========================================================================");

        for (Pedido pedido : pedidos) {

            int totalProductos = 0;

            for (ItemCarrito item : pedido.getItems()) {
                totalProductos += item.getCantidad();
            }

            System.out.printf("| %-10s | %-15d | S/. %-10.2f | %-15s |%n",
                    pedido.getCodigo(),
                    totalProductos,
                    pedido.getTotal(),
                    pedido.getEstado());
        }

        System.out.println("=========================================================================");

        return pedidos;
    } */

    @Override
    public Pedido buscarPedidoPorCodigo(String codigo) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo().equals(codigo)) return pedido;
        }
        return null;
    }
}
