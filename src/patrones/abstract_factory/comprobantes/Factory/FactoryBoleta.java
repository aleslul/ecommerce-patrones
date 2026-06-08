package patrones.abstract_factory.comprobantes.Factory;

import patrones.abstract_factory.comprobantes.Boleta;
import patrones.abstract_factory.comprobantes.Comprobante;

public class FactoryBoleta implements FactoryComprobante {
    @Override
    public Comprobante crearComprobante(String numero, String direccion, String detalle, double total, String dni, String nombres) {
        return new Boleta(numero, direccion, detalle, total, dni, nombres);
    }
}
