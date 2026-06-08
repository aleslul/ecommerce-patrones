package patrones.bridge.pay.concrete_implementor;

import patrones.bridge.pay.implementor.Moneda;
import patrones.singleton.config.Configuracion;

public class Dolares implements Moneda {
    @Override
    public double convertir(double monto) {
        double tipoCambio = Configuracion.getInstance().getTipoCambio();
        return monto / tipoCambio;
    }
    @Override
    public String getSimbolo() {
        return "$";
    }
}
