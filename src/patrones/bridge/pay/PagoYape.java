package patrones.bridge.pay;

import patrones.bridge.pay.abstraction.MetodoPago;
import patrones.bridge.pay.implementor.Moneda;

public class PagoYape extends MetodoPago {
    private String numeroTelefonico;
    private String codigoAprobacion;

    public PagoYape(Moneda moneda, String numeroTelefonico, String codigoAprobacion) {
        super(moneda);
        this.numeroTelefonico = numeroTelefonico;
        this.codigoAprobacion = codigoAprobacion;
    }

    @Override
    public void procesar(double monto) {
        double montoFinal = moneda.convertir(monto);
        System.out.println("Validando código de aprobación Yape: " + codigoAprobacion + "...");
        System.out.println("Conectando con el servidor de Yape para el número " + numeroTelefonico);
        System.out.println("Monto cobrado: " + moneda.getSimbolo() + " " + String.format("%.2f", montoFinal));
        System.out.println("¡Yapeo realizado con éxito!");
    }
}
