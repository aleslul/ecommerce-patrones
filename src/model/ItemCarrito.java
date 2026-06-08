package model;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {

        double subtotal = producto.getPrecio() * cantidad;

        return String.format(
                "%-15s | %-20s | Cantidad: %-3d | S/. %-8.2f",
                producto.getCodigo(),
                producto.getNombre(),
                cantidad,
                subtotal
        );
    }
}
