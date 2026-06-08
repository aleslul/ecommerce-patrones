package service;

import model.Carrito;
import model.ItemCarrito;
import model.Producto;
import patrones.proxy.InventarioManager;

//TODO: LOS ITEMCARRITO NO SE CONCATENAN SI SE AGREGAN UNO POR UNO
//TODO:

public class CarritoService {
    private Carrito carrito;
    private InventarioManager inventarioService;

    public CarritoService(InventarioManager inventarioService) {
        this.carrito = new Carrito();
        this.inventarioService = inventarioService;
    }

    public boolean agregarProducto(Producto producto, int cantidad) {

        if (producto == null) {
            System.out.println("ERROR: Producto inválido");
            return false;
        }

        if (cantidad <= 0) {
            System.out.println("ERROR: Cantidad inválida");
            return false;
        }

        int cantidadActual = 0;

        for (ItemCarrito item : carrito.getItems()) {

            if (item.getProducto().getCodigo().equals(producto.getCodigo())) {
                cantidadActual += item.getCantidad();
            }
        }

        int cantidadTotal = cantidadActual + cantidad;

        if (!inventarioService.verificarStock(producto.getCodigo(), cantidadTotal)) {
            System.out.println("ERROR: Stock insuficiente");
            return false;
        }
        carrito.getItems().add(new ItemCarrito(producto, cantidad));
        return true;
    }

    public boolean quitarProducto(String codigo) {

        ItemCarrito encontrado = null;

        for(ItemCarrito ic :carrito.getItems()) {
            if(ic.getProducto().getCodigo().equals(codigo)) {
                encontrado = ic;
                break;
            }
        }
        if(encontrado == null) {
            return false;
        }
        carrito.getItems().remove(encontrado);

        return true;
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
        System.out.println("\nCarrito vaciado");
    }

    public Carrito obtenerCarrito() {
        return carrito;
    }

    public boolean estaVacio() {
        return carrito.getItems().isEmpty();
    }
}
