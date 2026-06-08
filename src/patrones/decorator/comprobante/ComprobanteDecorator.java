package patrones.decorator.comprobante;

import patrones.abstract_factory.comprobantes.Comprobante;

public class ComprobanteDecorator implements Comprobante {
    protected Comprobante comprobanteEnvoltorio;

    public ComprobanteDecorator(Comprobante comprobante) {
        this.comprobanteEnvoltorio = comprobante;
    }

    @Override
    public String generarImpresion() {
        return comprobanteEnvoltorio.generarImpresion();
    }
}
