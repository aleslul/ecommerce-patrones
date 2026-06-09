package patrones.decorator.reporte;

public class ReporteLogs extends ReporteDecorator{
    public ReporteLogs(
            Reporte reporte
    ) {
        super(reporte);
    }
    @Override
    public void generar() {
        System.out.println(
                "[LOG] Iniciando generación de reporte..."
        );
        super.generar();
        System.out.println(
                "[LOG] Reporte generado correctamente."
        );
    }
}
