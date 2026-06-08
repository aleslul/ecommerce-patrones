package repository.impl;

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

            System.out.printf("| %-10s | %-15d | S/. %-10.2f | %-15s |%n",
                    pedido.getCodigo(),
                    pedido.getItems().size(),
                    pedido.getTotal(),
                    pedido.getEstado());
        }

        System.out.println("=========================================================================");

        return pedidos;
    }
}
