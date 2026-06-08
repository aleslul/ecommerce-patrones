package patrones.bridge.pay.abstraction;

import patrones.bridge.pay.implementor.Moneda;

public abstract class MetodoPago {
    protected Moneda moneda;

    public MetodoPago(Moneda moneda) {
        this.moneda = moneda;
    }

    // Metodo que las clases hijas ejecutarán
    public abstract void procesar(double monto);
}