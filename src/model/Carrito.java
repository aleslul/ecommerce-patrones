package model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> items;

    public List<ItemCarrito> getItems() {
        return items;
    }

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public Carrito(List<ItemCarrito> items) {
        this.items = items;
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getCantidad() * item.getProducto().getPrecio();
        }
        return total;
    }
}
