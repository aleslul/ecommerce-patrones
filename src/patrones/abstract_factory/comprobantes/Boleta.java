package patrones.abstract_factory.comprobantes;

public class Boleta extends ComprobanteBase{
    private String dni;
    private String nombres;

    public Boleta(String numeroComprobante, String direccionEnvio, String detalleCarrito, double total, String dni, String nombres) {
        super(numeroComprobante, direccionEnvio, detalleCarrito, total);
        this.dni = dni;
        this.nombres = nombres;
    }

    @Override
    public String generarImpresion() {
        return "\n========================================\n" +
                "               BOLETA ELECTRÓNICA\n" +
                "========================================\n" +
                "RUC Emisor: " + rucMiEmpresa + "\n" +
                "Nro Comprobante: " + numeroComprobante + "\n" +
                "Cliente: " + nombres + "\n" +
                "DNI: " + dni + "\n" +
                "Dirección Envío: " + direccionEnvio + "\n" +
                "----------------------------------------\n" +
                "DETALLE DE COMPRA:\n" + detalleCarrito +
                "----------------------------------------\n" +
                "IMPORTE TOTAL: S/ " + String.format("%.2f", total) + "\n" +
                "========================================";
    }
}
