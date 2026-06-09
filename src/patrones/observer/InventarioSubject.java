package patrones.observer;

import model.Producto;

public interface InventarioSubject {
    void suscribir(InventarioObserver observador);
    void desuscribir(InventarioObserver observador);
    void notificar(Producto producto);
}
