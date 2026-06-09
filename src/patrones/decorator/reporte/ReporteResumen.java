package patrones.decorator.reporte;

import repository.FacturacionRepository;

public class ReporteResumen extends ReporteDecorator{
    private FacturacionRepository repository;
    public ReporteResumen(
            Reporte reporte,
            FacturacionRepository repository
    ) {
        super(reporte);
        this.repository = repository;
    }

    @Override
    public void generar() {
        super.generar();
        System.out.println(
                "\n===== RESUMEN ====="
        );
        System.out.println("Total pagos: " + repository.listar().size());
    }
}
