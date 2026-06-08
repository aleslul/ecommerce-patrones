package patrones.proxy;

import model.Producto;

import java.util.List;

public interface InventarioManager {
    void agregarStock(String codigo, int cantidad);
    boolean reducirStock(String codigo, int cantidad);
    int obtenerStock(String codigo);
    boolean verificarStock(String codigo, int cantidad);
    List<Producto> listarProductosSinStock();
    List<Producto> listarProductosConStockBajo();
}
