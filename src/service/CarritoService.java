package service;

import model.Carrito;
import model.ItemCarrito;
import model.Producto;
import patrones.proxy.inventario.InventarioManager;

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
        ItemCarrito itemExistente = null;

        for (ItemCarrito item : carrito.getItems()) {
            if (item.getProducto().getCodigo().equals(producto.getCodigo())) {
                cantidadActual += item.getCantidad();
                itemExistente = item;
            }
        }

        int cantidadTotal = cantidadActual + cantidad;

        if (!inventarioService.verificarStock(producto.getCodigo(), cantidadTotal)) {
            System.out.println("ERROR: Stock insuficiente");
            return false;
        }

        if (itemExistente != null) {
            itemExistente.setCantidad(cantidadTotal);
        } else {
            carrito.getItems().add(new ItemCarrito(producto, cantidad));
        }
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

    public void mostrarCarrito() {
        if (carrito.getItems().isEmpty()) {
            System.out.println("ERROR: El carrito está vacío.");
            return;
        }
        System.out.println("\n================================================================================================");
        System.out.printf("| %-10s | %-25s | %-10s | %-10s | %-12s |%n",
                "CODIGO",
                "PRODUCTO",
                "PRECIO",
                "CANTIDAD",
                "SUBTOTAL");
        System.out.println("================================================================================================");

        for (ItemCarrito item : carrito.getItems()) {
            double subtotal = item.getCantidad() * item.getProducto().getPrecio();
            System.out.printf("| %-10s | %-20s | %-8.2f | %-8d | %-8.2f |%n",
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    subtotal);
        }
        System.out.println("================================================================================================");
        System.out.printf("TOTAL: S/. %.2f%n", calcularTotal());
    }

    public double calcularTotal() {
        double total = 0;

        for (ItemCarrito item : carrito.getItems()) {
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
