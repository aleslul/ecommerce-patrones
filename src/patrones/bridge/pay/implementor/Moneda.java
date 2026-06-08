package patrones.bridge.pay.implementor;

public interface Moneda {
    double convertir(double monto);
    String getSimbolo();
}
