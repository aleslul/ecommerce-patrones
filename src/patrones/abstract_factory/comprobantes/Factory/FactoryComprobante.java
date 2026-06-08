package patrones.abstract_factory.comprobantes.Factory;

import patrones.abstract_factory.comprobantes.Comprobante;

public interface FactoryComprobante {
    Comprobante crearComprobante(String numero, String direccion, String detalle, double total, String documento, String nombre);
}
