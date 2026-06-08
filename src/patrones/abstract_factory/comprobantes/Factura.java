package patrones.abstract_factory.comprobantes;


import model.types.PedidoEstado;
import patrones.bridge.pay.abstraction.MetodoPago;
import patrones.singleton.config.Configuracion;

public class Factura extends ComprobanteBase {
    private String rucCliente;
    private String razonSocial;
    private double subtotal;
    private double igvCalculado;

    public Factura(String numeroComprobante, String direccionEnvio, String detalleCarrito, double total, MetodoPago metodoPago, PedidoEstado estado, String usernameCliente, String rucCliente, String razonSocial) {
        super(numeroComprobante, direccionEnvio, detalleCarrito, total, metodoPago, estado, usernameCliente);
        this.rucCliente = rucCliente;
        this.razonSocial = razonSocial;

        // Matemática del IGV (asumiendo que Configuracion devuelve 0.18)
        double igvPorcentaje = Configuracion.getInstance().getIgv();
        this.subtotal = total / (1 + igvPorcentaje);
        this.igvCalculado = total - this.subtotal;
    }

    @Override
    public String generarImpresion() {
        return "\n========================================\n" +
                "              FACTURA ELECTRÓNICA\n" +
                "========================================\n" +
                "Usuario Cuenta: " + usernameCliente + "\n" +
                "RUC Emisor: " + rucMiEmpresa + "\n" +
                "Nro Comprobante: " + numeroComprobante + "\n" +
                "Razón Social: " + razonSocial + "\n" +
                "RUC Cliente: " + rucCliente + "\n" +
                "Dirección Envío: " + direccionEnvio + "\n" +
                "----------------------------------------\n" +
                "DETALLE DE COMPRA:\n" + detalleCarrito +
                "----------------------------------------\n" +
                "Op. Gravada (Subtotal): S/ " + String.format("%.2f", subtotal) + "\n" +
                "IGV (" + (Configuracion.getInstance().getIgv() * 100) + "%): S/ " + String.format("%.2f", igvCalculado) + "\n" +
                "IMPORTE TOTAL: S/ " + String.format("%.2f", total) + "\n" +
                "Estado del Pedido: " + estado + "\n" +
                "Método de Pago: " + metodoPago.getClass().getSimpleName() + "\n" +
                "========================================";
    }
}
