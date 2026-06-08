package patrones.bridge.pay;

import patrones.bridge.pay.abstraction.MetodoPago;
import patrones.bridge.pay.implementor.Moneda;

public class PagoPlin extends MetodoPago {
    private String numeroTelefonico;
    private String codigoAprobacion;

    public PagoPlin(Moneda moneda, String numeroTelefonico, String codigoAprobacion) {
        super(moneda);
        this.numeroTelefonico = numeroTelefonico;
        this.codigoAprobacion = codigoAprobacion;
    }

    @Override
    public void procesar(double monto) {
        double montoFinal = moneda.convertir(monto);
        System.out.println("Validando código Plin: " + codigoAprobacion + "...");
        System.out.println("Enviando dinero interbancario desde el número " + numeroTelefonico);
        System.out.println("Monto cobrado: " + moneda.getSimbolo() + " " + String.format("%.2f", montoFinal));
        System.out.println("¡Plin exitoso!");
    }
}
