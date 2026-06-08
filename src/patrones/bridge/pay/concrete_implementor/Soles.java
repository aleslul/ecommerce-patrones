package patrones.bridge.pay.concrete_implementor;

import patrones.bridge.pay.implementor.Moneda;

public class Soles implements Moneda {
    @Override
    public double convertir(double monto) {
        return monto;
    }
    @Override
    public String getSimbolo() {
        return "S/";
    }
}
