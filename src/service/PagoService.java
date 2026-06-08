package service;

import model.Pedido;
import model.types.PedidoEstado;
import patrones.bridge.pay.abstraction.MetodoPago;
import patrones.bridge.pay.implementor.Moneda;
import patrones.bridge.pay.types.TipoPago;
import patrones.factory.FactoryPago;
import repository.PagoRepository;

import java.util.Scanner;

public class PagoService {
    private PagoRepository pagoRepository;
    private Scanner scanner;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
        this.scanner = new Scanner(System.in);
    }

    public void procesarCompra(Pedido pedido, TipoPago tipoPago, Moneda monedaElegida) {
        System.out.println("=== INICIANDO PROCESO DE PAGO ===");
        MetodoPago pago = null;

        if (tipoPago == TipoPago.TARJETA) {
            String numero, fecha, cvv;

            // Validación de Tarjeta (16 dígitos)
            do {
                System.out.print("Ingrese los 16 dígitos de su tarjeta: ");
                numero = scanner.nextLine();
            } while (!numero.matches("\\d{16}"));

            // Validación de Fecha (MM/yy)
            do {
                System.out.print("Ingrese fecha de caducidad (MM/yy): ");
                fecha = scanner.nextLine();
            } while (!fecha.matches("^(0[1-9]|1[0-2])/\\d{2}$"));

            // Validación de CVV (3 dígitos)
            do {
                System.out.print("Ingrese los 3 dígitos del CVV: ");
                cvv = scanner.nextLine();
            } while (!cvv.matches("\\d{3}"));

            // Si pasa las validaciones, creamos el pago con la fábrica
            pago = FactoryPago.crearPago(TipoPago.TARJETA, monedaElegida, numero, fecha, cvv);

        } else if (tipoPago == TipoPago.YAPE || tipoPago == TipoPago.PLIN) {
            String celular, codigo;

            // Validación de Celular (9 dígitos)
            do {
                System.out.print("Ingrese su número de celular (9 dígitos): ");
                celular = scanner.nextLine();
            } while (!celular.matches("\\d{9}"));

            // Validación de Código (6 dígitos)
            do {
                System.out.print("Ingrese el código de aprobación (6 dígitos): ");
                codigo = scanner.nextLine();
            } while (!codigo.matches("\\d{6}"));

            // Creamos el pago con la fábrica
            pago = FactoryPago.crearPago(tipoPago, monedaElegida, celular, codigo);
        }

        // Ejecutar el cobro real usando el objeto creado
        if (pago != null) {
            pago.procesar(pedido.getTotal()); // Extraemos el billete del pedido

            // Cambiamos el estado del pedido de tu amigo a PAGADO
            pedido.setEstado(PedidoEstado.PAGADO);

            // Lo guardamos en nuestra base de datos
            pagoRepository.guardar(pago);
            System.out.println("El pago ha sido registrado correctamente en el sistema.");
        }
    }
}
