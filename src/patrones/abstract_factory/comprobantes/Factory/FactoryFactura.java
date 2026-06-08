package patrones.abstract_factory.comprobantes.Factory;

import model.types.PedidoEstado;
import patrones.abstract_factory.comprobantes.Comprobante;
import patrones.abstract_factory.comprobantes.Factura;
import patrones.bridge.pay.abstraction.MetodoPago;

public class FactoryFactura implements FactoryComprobante {
    @Override
    public Comprobante crearComprobante(String numero, String direccion, String detalle, double total, MetodoPago pago, PedidoEstado estado, String username, String ruc, String razonSocial) {
        return new Factura(numero, direccion, detalle, total, pago, estado, username, ruc, razonSocial);
    }
}
