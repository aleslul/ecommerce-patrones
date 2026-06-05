package repository;

import model.Pedido;

import java.util.List;

public interface PedidoRepository {
    void guardar(Pedido pedido);
    List<Pedido> listar();
}
