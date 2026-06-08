package repository.impl;

import patrones.abstract_factory.comprobantes.Comprobante;
import repository.FacturacionRepository;

import java.util.ArrayList;
import java.util.List;

public class FacturacionRepositoryImpl implements FacturacionRepository {
    private List<Comprobante> comprobantes;

    public FacturacionRepositoryImpl() {
        this.comprobantes = new ArrayList<>();
    }

    public FacturacionRepositoryImpl(List<Comprobante> comprobantes) {
        this.comprobantes = comprobantes;
    }

    // IMPLEMENTA DE: IFacturacionRepository
    @Override
    public void guardar(Comprobante comprobante) {
        comprobantes.add(comprobante);
    }

    @Override
    public List<Comprobante> listar() {
        return comprobantes;
    }
}
