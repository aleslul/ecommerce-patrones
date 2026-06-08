package patrones.bridge.pay;

import patrones.bridge.pay.abstraction.MetodoPago;
import patrones.bridge.pay.implementor.Moneda;

public class PagoTarjeta extends MetodoPago {
    private String numeroTarjeta;
    private String fechaCaducidad;
    private String cvv;

    public PagoTarjeta(Moneda moneda, String numeroTarjeta, String fechaCaducidad, String cvv) {
        super(moneda);
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCaducidad = fechaCaducidad;
        this.cvv = cvv;
    }

    @Override
    public void procesar(double monto) {
        double montoFinal = moneda.convertir(monto);
        String ultimosDigitos = numeroTarjeta.substring(12); // Extrae los últimos 4 dígitos
        System.out.println("Procesando cobro en Tarjeta terminada en: ****" + ultimosDigitos);
        System.out.println("Monto cobrado: " + moneda.getSimbolo() + " " + String.format("%.2f", montoFinal));
        System.out.println("¡Pago con tarjeta aprobado exitosamente!");
    }
}
