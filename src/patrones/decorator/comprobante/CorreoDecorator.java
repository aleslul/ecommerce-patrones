package patrones.decorator.comprobante;

import patrones.abstract_factory.comprobantes.Comprobante;

public class CorreoDecorator extends ComprobanteDecorator {
    private String correo;
    public CorreoDecorator(Comprobante comprobante, String correo) {
        super(comprobante);
        this.correo = correo;
    }
    @Override
    public String generarImpresion() {
        return super.generarImpresion() + "\nCopia enviada al correo: " + correo;
    }
}
