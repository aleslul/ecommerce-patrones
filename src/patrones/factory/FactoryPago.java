package patrones.factory;

import patrones.bridge.pay.PagoPlin;
import patrones.bridge.pay.PagoTarjeta;
import patrones.bridge.pay.PagoYape;
import patrones.bridge.pay.abstraction.MetodoPago;
import patrones.bridge.pay.implementor.Moneda;
import patrones.bridge.pay.types.TipoPago;

public class FactoryPago {
    public static MetodoPago crearPago(TipoPago tipo, Moneda moneda, String... datos) {
        switch (tipo) {
            case TARJETA:
                // datos[0]: numero, datos[1]: fecha, datos[2]: cvv
                return new PagoTarjeta(moneda, datos[0], datos[1], datos[2]);
            case YAPE:
                // datos[0]: celular, datos[1]: codigo
                return new PagoYape(moneda, datos[0], datos[1]);
            case PLIN:
                return new PagoPlin(moneda, datos[0], datos[1]);
            default:
                throw new IllegalArgumentException("Tipo de pago no soportado");
        }
    }
}
