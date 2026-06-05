package service;

import model.Carrito;
import model.ItemCarrito;
import model.Producto;

import java.util.List;

public class CarritoService {
    private Carrito carrito;

    public CarritoService() {
        this.carrito = new Carrito();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        //TODO: por el momento funciona pero se puede mejorar (creo) - aleslul
        if (producto == null) {
            System.out.println("ERROR: Producto invalido");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("ERROR: Cantidad invalida");
            return;
        }

        ItemCarrito item = new ItemCarrito(producto, cantidad);
        carrito.getItems().add(item);
    }

    public void quitarProducto(String codigo) {
        ItemCarrito encontrado = null;

        for (ItemCarrito ic : carrito.getItems()) {
            if (ic.getProducto().getCodigo().equals(codigo)) {
                encontrado = ic;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("ERROR: Producto no encontrado en el carrito");
            return;
        }

        carrito.getItems().remove(encontrado);
    }

    //TODO: ARRREGLAR LOS TOSTRING PQ ESTAN BIEN PINCHES FEOS
    public void mostrarCarrito() {
        if (carrito.getItems().isEmpty()) {
            System.out.println("ERROR: El carrito está vacio.");
            return;
        };

        System.out.println("\n===== CARRITO =====");

        for (ItemCarrito item : carrito.getItems()) {
            System.out.println(item);
        }

        System.out.println("-------------------");
        System.out.println("Total: S/. " + calcularTotal());
    }

    public double calcularTotal() {
        double total = 0;

        for (ItemCarrito item :
                carrito.getItems()) {

            total += item.getCantidad() * item.getProducto().getPrecio();
        }

        return total;
    }

    public void vaciarCarrito() {
        carrito.getItems().clear();
    }

    public Carrito obtenerCarrito() {
        return carrito;
    }

    public boolean estaVacio() {
        return carrito.getItems().isEmpty();
    }
}
