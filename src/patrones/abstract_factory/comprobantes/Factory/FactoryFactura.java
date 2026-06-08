package patrones.abstract_factory.comprobantes.Factory;

import patrones.abstract_factory.comprobantes.Comprobante;
import patrones.abstract_factory.comprobantes.Factura;

public class FactoryFactura implements FactoryComprobante {
    @Override
    public Comprobante crearComprobante(String numero, String direccion, String detalle, double total, String ruc, String razonSocial) {
        return new Factura(numero, direccion, detalle, total, ruc, razonSocial);
    }
}
