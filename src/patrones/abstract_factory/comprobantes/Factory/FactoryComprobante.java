package patrones.abstract_factory.comprobantes.Factory;

import model.types.PedidoEstado;
import patrones.abstract_factory.comprobantes.Comprobante;
import patrones.bridge.pay.abstraction.MetodoPago;

public interface FactoryComprobante {
    Comprobante crearComprobante(String numero, String direccion, String detalle, double total, MetodoPago pago, PedidoEstado estado, String username, String documento, String nombre);
}
