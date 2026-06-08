package service;

import model.Pedido;
import model.types.PedidoEstado;
import patrones.bridge.pay.abstraction.MetodoPago;
import patrones.bridge.pay.concrete_implementor.Dolares;
import patrones.bridge.pay.concrete_implementor.Soles;
import patrones.bridge.pay.implementor.Moneda;
import patrones.bridge.pay.types.TipoPago;
import patrones.factory.FactoryPago;

// IMPORTANTE: Asegúrate de importar tus clases Soles y Dolares según tu ruta


import java.util.Scanner;

public class PagoService {

    // ¡Adiós PagoRepository! Ya no lo necesitamos.
    private Scanner scanner;

    public PagoService() {
        this.scanner = new Scanner(System.in);
    }

    // Cambiamos "void" por "MetodoPago" para poder pasarlo a la factura
    public MetodoPago procesarCompra(Pedido pedido) {
        System.out.println("=== INICIANDO PROCESO DE PAGO ===");

        // 1. PEDIR LA MONEDA
        System.out.println("\nTotal a pagar: S/ " + pedido.getTotal());
        System.out.println("¿En qué moneda desea pagar?");
        System.out.println("1. SOLES");
        System.out.println("2. DOLARES");
        System.out.print("Opción: ");
        int opcMoneda = scanner.nextInt();
        scanner.nextLine();

        Moneda monedaElegida = (opcMoneda == 2) ? new Dolares() : new Soles();

        // 2. PEDIR EL TIPO DE PAGO
        System.out.println("\n¿Qué método de pago utilizará?");
        System.out.println("1. TARJETA");
        System.out.println("2. YAPE");
        System.out.println("3. PLIN");
        System.out.print("Opción: ");
        int opcPago = scanner.nextInt();
        scanner.nextLine();

        MetodoPago pago = null;
        TipoPago tipoPago = TipoPago.TARJETA;

        // 3. VALIDACIONES ESTRICTAS SEGÚN EL METODO ELEGIDO
        if (opcPago == 1) {
            tipoPago = TipoPago.TARJETA;
            String numero, fecha, cvv;

            do {
                System.out.print("Ingrese los 16 dígitos de su tarjeta: ");
                numero = scanner.nextLine();
            } while (!numero.matches("\\d{16}"));

            do {
                System.out.print("Ingrese fecha de caducidad (MM/yy): ");
                fecha = scanner.nextLine();
            } while (!fecha.matches("^(0[1-9]|1[0-2])/\\d{2}$"));

            do {
                System.out.print("Ingrese los 3 dígitos del CVV: ");
                cvv = scanner.nextLine();
            } while (!cvv.matches("\\d{3}"));

            pago = FactoryPago.crearPago(tipoPago, monedaElegida, numero, fecha, cvv);

        } else if (opcPago == 2 || opcPago == 3) {
            tipoPago = (opcPago == 2) ? TipoPago.YAPE : TipoPago.PLIN;
            String celular, codigo;

            do {
                System.out.print("Ingrese su número de celular (9 dígitos): ");
                celular = scanner.nextLine();
            } while (!celular.matches("\\d{9}"));

            do {
                System.out.print("Ingrese el código de aprobación (6 dígitos): ");
                codigo = scanner.nextLine();
            } while (!codigo.matches("\\d{6}"));

            pago = FactoryPago.crearPago(tipoPago, monedaElegida, celular, codigo);
        }

        // 4. EJECUTAR EL COBRO REAL
        if (pago != null) {
            pago.procesar(pedido.getTotal());

            // Cambiamos el estado del pedido a PAGADO
            pedido.setEstado(PedidoEstado.PAGADO);
            System.out.println("El pago ha sido validado correctamente en el sistema.");

            // RETORNAMOS EL OBJETO PARA QUE LA FACTURACIÓN LO USE
            return pago;
        }

        return null; // Si algo falló o se canceló, retorna null
    }
}