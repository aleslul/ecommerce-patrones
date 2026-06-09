package patrones.decorator.reporte;

import patrones.abstract_factory.comprobantes.Comprobante;
import repository.FacturacionRepository;

import java.util.List;

public class ReportePagos implements Reporte {
    private FacturacionRepository repository;

    public ReportePagos(
            FacturacionRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public void generar() {

        System.out.println(
                "\n===== REPORTE DE PAGOS ====="
        );

        List<Comprobante> comprobantes = repository.listar();

        if(comprobantes.isEmpty()) {
            System.out.println("No existen comprobantes registrados.");
            return;
        }
        int Npago = 1;
        for(Comprobante comprobante : comprobantes) {
            System.out.println("\n[ COMPROBANTE N° " + Npago + " ]");
            System.out.println(comprobante.generarImpresion());
            Npago++;
        }
    }
}
