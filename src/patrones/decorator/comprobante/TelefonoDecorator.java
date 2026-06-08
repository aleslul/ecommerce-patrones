package patrones.decorator.comprobante;

import patrones.abstract_factory.comprobantes.Comprobante;

public class TelefonoDecorator extends ComprobanteDecorator {
    private String telefono;

    public TelefonoDecorator(Comprobante comprobante, String telefono) {
        super(comprobante);
        this.telefono = telefono;
    }

    @Override
    public String generarImpresion() {
        return super.generarImpresion() + "\nSMS de confirmación al: " + telefono;
    }
}
