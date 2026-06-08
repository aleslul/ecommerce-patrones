package patrones.abstract_factory.comprobantes.Factory;

import model.types.PedidoEstado;
import patrones.abstract_factory.comprobantes.Boleta;
import patrones.abstract_factory.comprobantes.Comprobante;
import patrones.bridge.pay.abstraction.MetodoPago;

public class FactoryBoleta implements FactoryComprobante {
    @Override
    public Comprobante crearComprobante(String numero, String direccion, String detalle, double total, MetodoPago pago, PedidoEstado estado, String username, String dni, String nombres) {
        return new Boleta(numero, direccion, detalle, total, pago, estado, username, dni, nombres);
    }
}
