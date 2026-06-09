package model;

import model.types.PedidoEstado;

import java.util.List;

public class Pedido {
    private String codigo;
    private String usernameCliente;
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

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }

    public Pedido() {
    }

    public Pedido(String codigo, String usernameCliente, List<ItemCarrito> items, double total, PedidoEstado estado) {
        this.codigo = codigo;
        this.usernameCliente = usernameCliente;
        this.items = items;
        this.total = total;
        this.estado = estado;
    }
}
