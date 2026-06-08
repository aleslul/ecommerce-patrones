package patrones.abstract_factory.comprobantes;

public abstract class ComprobanteBase implements Comprobante {
    protected String numeroComprobante;
    protected String rucMiEmpresa = "20555444333"; // El RUC de la tienda de ustedes
    protected String direccionEnvio;
    protected String detalleCarrito;
    protected double total;

    public ComprobanteBase(String numeroComprobante, String direccionEnvio, String detalleCarrito, double total) {
        this.numeroComprobante = numeroComprobante;
        this.direccionEnvio = direccionEnvio;
        this.detalleCarrito = detalleCarrito;
        this.total = total;
    }

}
