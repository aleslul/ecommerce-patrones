package patrones.decorator.reporte;

public class ReporteDecorator implements Reporte {
        protected Reporte reporte;
    public ReporteDecorator(
                Reporte reporte
        ) {
            this.reporte = reporte;
        }

        @Override
        public void generar() {
            reporte.generar();
        }
}
