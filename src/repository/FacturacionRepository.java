package repository;

import patrones.abstract_factory.comprobantes.Comprobante;

import java.util.List;

public interface FacturacionRepository {
    void guardar(Comprobante comprobante);
    List<Comprobante> listar();
}
