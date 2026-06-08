package patrones.abstract_factory.comprobantes;

import model.types.PedidoEstado;
import patrones.bridge.pay.abstraction.MetodoPago;

public abstract class ComprobanteBase implements Comprobante {
    protected String numeroComprobante;
    protected String rucMiEmpresa = "20555444333";
    protected String direccionEnvio;
    protected String detalleCarrito;
    protected double total;
    protected MetodoPago metodoPago;
    protected PedidoEstado estado;
    protected String usernameCliente;

    public ComprobanteBase(String numeroComprobante, String direccionEnvio, String detalleCarrito, double total, MetodoPago metodoPago, PedidoEstado estado, String usernameCliente) {
        this.numeroComprobante = numeroComprobante;
        this.direccionEnvio = direccionEnvio;
        this.detalleCarrito = detalleCarrito;
        this.total = total;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.usernameCliente = usernameCliente;
    }

    @Override
    public String getUsernameCliente() {
        return usernameCliente;
    }
}
