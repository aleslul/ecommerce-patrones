package service;

import model.ItemCarrito;
import model.Pedido;
import model.Usuario;
import model.types.UsuarioRoles;
import patrones.abstract_factory.comprobantes.Comprobante;
import patrones.abstract_factory.comprobantes.Factory.FactoryBoleta;
import patrones.abstract_factory.comprobantes.Factory.FactoryComprobante;
import patrones.abstract_factory.comprobantes.Factory.FactoryFactura;
import patrones.abstract_factory.types.TipoComprobante;
import patrones.bridge.pay.abstraction.MetodoPago;
import patrones.decorator.comprobante.CorreoDecorator;
import patrones.decorator.comprobante.TelefonoDecorator;
import patrones.singleton.sesion.SesionActual;
import repository.FacturacionRepository;

import java.util.List;
import java.util.Scanner;

public class FacturacionService {
    private FacturacionRepository facturacionRepository;
    private Scanner scanner;

    public FacturacionService(FacturacionRepository facturacionRepository) {
        this.facturacionRepository = facturacionRepository;
        this.scanner = new Scanner(System.in);
    }

    public void generarComprobante(Pedido pedido, MetodoPago metodoPago, String username) {
        System.out.println("\n--- EMISIÓN DE COMPROBANTE ---");
        System.out.println("¿Desea emitir?");
        System.out.println("1. BOLETA");
        System.out.println("2. FACTURA");
        System.out.print("Opción: ");
        int opcComp = scanner.nextInt();
        scanner.nextLine();

        TipoComprobante tipo = (opcComp == 2) ? TipoComprobante.FACTURA : TipoComprobante.BOLETA;

        String doc, nombre, direccion;

        // VALIDACIONES Y PREGUNTAS
        if (tipo == TipoComprobante.BOLETA) {
            do {
                System.out.print("Ingrese DNI (8 dígitos obligatorios): ");
                doc = scanner.nextLine();
            } while (!doc.matches("\\d{8}"));

            System.out.print("Ingrese Nombres y Apellidos: ");
            nombre = scanner.nextLine();
        } else {
            do {
                System.out.print("Ingrese RUC (11 dígitos obligatorios): ");
                doc = scanner.nextLine();
            } while (!doc.matches("\\d{11}"));

            System.out.println("¿A nombre de quién irá la factura?");
            System.out.println("1. Mi nombre personal");
            System.out.println("2. Nombre de mi empresa");
            System.out.print("Opción: ");
            int opcNombreRuc = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Escriba el nombre/razón social: ");
            String nombreIngresado = scanner.nextLine();
            nombre = nombreIngresado + (opcNombreRuc == 1 ? " (Nombre y Apellidos)" : " (Nombre de Empresa)");
        }

        System.out.print("Ingrese Dirección de envío: ");
        direccion = scanner.nextLine();

        String correo = null, telefono = null;
        int opcContacto;
        do {
            System.out.println("\nMedio de contacto OBLIGATORIO para envío:");
            System.out.println("1. Solo Correo");
            System.out.println("2. Solo Teléfono");
            System.out.println("3. Ambos");
            System.out.print("Opción: ");
            opcContacto = scanner.nextInt();
            scanner.nextLine();
        } while (opcContacto < 1 || opcContacto > 3);

        if (opcContacto == 1 || opcContacto == 3) {
            do {
                System.out.print("Ingrese correo (obligatorio @gmail.com): ");
                correo = scanner.nextLine();
            } while(!correo.matches("^[\\w-\\.]+@gmail\\.com$"));
        }
        if (opcContacto == 2 || opcContacto == 3) {
            do {
                System.out.print("Ingrese teléfono (9 dígitos obligatorios): ");
                telefono = scanner.nextLine();
            } while (!telefono.matches("\\d{9}"));
        }

        // ==========================================
        // CREACIÓN DE LA FACTURA
        // ==========================================

        //recorre la lista de productos que están en el carrito de un pedido y utiliza
        //detalleBuilder (que es el objeto StringBuilder) para construir una lista de texto estructurada.
        StringBuilder detalleBuilder = new StringBuilder();
        for (ItemCarrito item : pedido.getItems()) {
            detalleBuilder.append(item.getCantidad()).append("x ")
                    .append(item.getProducto().getNombre()).append(" - S/ ")
                    .append(String.format("%.2f", item.getProducto().getPrecio())).append(" c/u\n");
        }

        //Convierte el String builder en un toString para que se imprima
        String detalleStr = detalleBuilder.toString();

        String numeroComprobante = pedido.getCodigo();

        FactoryComprobante fabrica = (tipo == TipoComprobante.BOLETA) ? new FactoryBoleta() : new FactoryFactura();

        Comprobante comprobanteFinal = fabrica.crearComprobante(numeroComprobante, direccion, detalleStr, pedido.getTotal(), metodoPago, pedido.getEstado(), username, doc, nombre);

        //Decorator
        if (correo != null) {
            comprobanteFinal = new CorreoDecorator(comprobanteFinal, correo);
        }
        if (telefono != null) {
            comprobanteFinal = new TelefonoDecorator(comprobanteFinal, telefono);
        }


        System.out.println(comprobanteFinal.generarImpresion());
        facturacionRepository.guardar(comprobanteFinal);
        System.out.println("\n¡Transacción completada con éxito!");
    }

    public void listarComprobantes() {
        List<Comprobante> historial = facturacionRepository.listar();
        if (historial == null || historial.isEmpty()) {
            System.out.println("No hay comprobantes emitidos en el sistema.");
            return;
        }

        Usuario usuarioActual = SesionActual.getInstance().getUsuario();

        System.out.println("\n=== HISTORIAL DE FACTURACIÓN Y PAGOS ===");
        boolean encontroComprobantes = false;

        for (Comprobante c : historial) {
            if (usuarioActual.getRol() == UsuarioRoles.ADMINISTRADOR ||
                    c.getUsernameCliente().equals(usuarioActual.getUsername())) {

                System.out.println(c.generarImpresion());
                encontroComprobantes = true;
            }
        }

        if (!encontroComprobantes) {
            System.out.println("No tienes comprobantes emitidos a tu nombre.");
        }
    }
}
