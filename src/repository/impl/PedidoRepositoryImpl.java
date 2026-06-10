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

    @Override
    public Pedido buscarPedidoPorCodigo(String codigo) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo().equals(codigo)) return pedido;
        }
        return null;
    }
}
