package patrones.abstract_factory.comprobantes;

import model.types.PedidoEstado;
import patrones.bridge.pay.abstraction.MetodoPago;

public class Boleta extends ComprobanteBase{
    private String dni;
    private String nombres;

    public Boleta(String numeroComprobante, String direccionEnvio, String detalleCarrito, double total, MetodoPago metodoPago, PedidoEstado estado, String usernameCliente, String dni, String nombres) {
        super(numeroComprobante, direccionEnvio, detalleCarrito, total, metodoPago, estado, usernameCliente);
        this.dni = dni;
        this.nombres = nombres;
    }

    @Override
    public String generarImpresion() {
        return "\n========================================\n" +
                "               BOLETA ELECTRÓNICA\n" +
                "========================================\n" +
                "Usuario Cuenta: " + usernameCliente + "\n" +
                "RUC Emisor: " + rucMiEmpresa + "\n" +
                "Nro Comprobante: " + numeroComprobante + "\n" +
                "Cliente: " + nombres + "\n" +
                "DNI: " + dni + "\n" +
                "Dirección Envío: " + direccionEnvio + "\n" +
                "----------------------------------------\n" +
                "DETALLE DE COMPRA:\n" + detalleCarrito +
                "----------------------------------------\n" +
                "IMPORTE TOTAL: S/ " + String.format("%.2f", total) + "\n" +
                "Estado del Pedido: " + estado + "\n" +
                "Método de Pago: " + metodoPago.getClass().getSimpleName() + "\n" +
                "========================================";
    }
}
