package patrones.observer;

import model.Producto;

public class InventarioConcreteObserver implements InventarioObserver {

    @Override
    public void actualizar(Producto producto) {
        BuzonAlertas buzon = BuzonAlertas.getInstance();

        if (producto.getStock() == 0) {
            String mensaje = "[ALERTA] El producto '" + producto.getNombre() +
                    "' (Cód: " + producto.getCodigo() + ") se ha quedado sin stock. ¡Por favor reabastecer!";
            buzon.agregarAlerta(producto.getCodigo(), mensaje);
        } else if (producto.getStock() > 0) {
            buzon.removerAlerta(producto.getCodigo());
        }
    }
}
