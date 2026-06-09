package patrones.observer;

import model.Producto;

public interface InventarioObserver {
    void actualizar(Producto producto);
}
