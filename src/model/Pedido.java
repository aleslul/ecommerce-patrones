package model;

import model.types.PedidoEstado;

import java.util.List;

public class Pedido {
    private String codigo;
    private List<ItemCarrito> items;
    private double total;
    private PedidoEstado estado;

    public String getCodigo() {
        return codigo;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public PedidoEstado getEstado() {
        return estado;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
    }

    public Pedido() {
    }

    public Pedido(String codigo, List<ItemCarrito> items, double total, PedidoEstado estado) {
        this.codigo = codigo;
        this.items = items;
        this.total = total;
        this.estado = estado;
    }
}
