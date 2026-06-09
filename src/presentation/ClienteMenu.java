package presentation;

import model.Pedido;
import model.Producto;
import model.types.PedidoEstado;
import patrones.bridge.pay.abstraction.MetodoPago;
import patrones.facade.SeguridadFacade;
import patrones.proxy.pedidos.PedidoManager;
import patrones.singleton.sesion.SesionActual;
import service.*;


import java.util.List;
import java.util.Scanner;

public class ClienteMenu {

    private Scanner scanner;

    private ProductoService productoService;
    private CarritoService carritoService;
    private PedidoManager pedidoService;
    private SeguridadFacade seguridadFacade;
    private PagoService pagoService;
    private FacturacionService facturacionService;

    public ClienteMenu(Scanner scanner, ProductoService productoService,
                       CarritoService carritoService, PedidoManager pedidoService,
                       SeguridadFacade seguridadFacade, PagoService pagoService, FacturacionService facturacionService) {
        this.scanner = scanner;
        this.productoService = productoService;
        this.carritoService = carritoService;
        this.pedidoService = pedidoService;
        this.seguridadFacade = seguridadFacade;
        this.pagoService = pagoService;
        this.facturacionService = facturacionService;
    }

    public void iniciar() {

        int opcion;

        do {
            String username = SesionActual.getInstance().getUsuario().getUsername();

            System.out.printf("""
                
                ============================
                     E-COMMERCE (%s)
                ============================
                
                1. Productos
                2. Carrito
                3. Pedidos
                4. Pago
                5. Cambiar de usuario
                0. Salir
                
                """, username);

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> menuProductos();

                case 2 -> menuCarrito();

                case 3 -> menuPedidos();

                case 4 -> menuPagos();

                case 5 -> {
                    seguridadFacade.cerrarSesion();
                    return;
                }

                case 0 -> {
                    System.out.println("Hasta luego.");
                    System.exit(0);
                }

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private void menuProductos() {

        int opcion;

        do {
            System.out.println("""
                
                ===== PRODUCTOS =====
                
                1. Listar productos
                2. Buscar producto
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> {
                    List<Producto> listaProductos = productoService.listarProductos();
                    productoService.imprimirTablaProductos(listaProductos);
                }

                case 2 -> buscarProducto();


            }

        } while (opcion != 0);
    }

    private void buscarProducto() {
        int opcion;
        System.out.print("Código: ");

        String codigo = scanner.nextLine();

        Producto producto =
                productoService.buscarProducto(
                        codigo
                );

        if (producto == null) {

            System.out.println(
                    "Producto no encontrado."
            );

            return;
        }

        System.out.println(producto);
        System.out.println(
                "\n¿Desea agregar este producto al carrito?"
        );

        System.out.println("1. Sí");
        System.out.println("2. No");
        opcion = scanner.nextInt();
        scanner.nextLine();
        switch(opcion) {

            case 1 -> agregarCarrito(producto);

            case 2 -> {}

            default -> System.out.println("Opción inválida.");
        }
    }


    private void menuCarrito() {

        int opcion;

        do {

            System.out.println("""
                
                ===== CARRITO =====
                
                1. Agregar producto
                2. Quitar producto
                3. Ver carrito
                4. Vaciar carrito
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> agregarProductoCarrito();

                case 2 -> quitarProductoCarrito();

                case 3 -> carritoService.mostrarCarrito();

                case 4 -> carritoService.vaciarCarrito();

            }

        } while (opcion != 0);
    }

    private void agregarProductoCarrito() {

        System.out.print("Código producto: ");

        String codigo = scanner.nextLine();

        Producto producto =
                productoService.buscarProducto(codigo);

        if (producto == null) {

            System.out.println(
                    "Producto no encontrado."
            );

            return;
        }

        agregarCarrito(producto);
    }

      private void agregarCarrito(Producto producto) {
        System.out.print("Cantidad: ");

        int cantidad = scanner.nextInt();
        scanner.nextLine();

        boolean agregado = carritoService.agregarProducto(
                producto,
                cantidad
        );

        if (agregado) {
            System.out.println("Producto agregado al carrito");
        }
    }

    private void quitarProductoCarrito() {
        if(carritoService.estaVacio()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        carritoService.mostrarCarrito();
        System.out.print(
                "\nCódigo del producto a eliminar: "
        );
        String codigo =scanner.nextLine();
        boolean eliminado =carritoService.quitarProducto(codigo);

        if(eliminado) {

            System.out.println(
                    "Producto eliminado del carrito."
            );

        } else {

            System.out.println("Producto no encontrado en el carrito.");
        }
    }

    private void menuPedidos() {

        int opcion;

        do {

            System.out.println("""
                
                ===== PEDIDOS =====
                
                1. Generar pedido
                2. Ver pedidos
                3. Ver detalle de pedido
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> generarPedido();

                case 2 -> {
                        List<Pedido> pedidos = pedidoService.listarPedidos();
                        pedidoService.imprimirTablaPedidos(pedidos);
                }

                case 3 -> verDetallePedido();
            }

        } while (opcion != 0);
    }

    private void verDetallePedido() {
        System.out.println("Codigo del pedido: ");
        String codigo = scanner.nextLine();
        pedidoService.mostrarDetallePedido(codigo);
    }

    //TODO: Actualizar esto despues con patrones builder
    private void generarPedido() {
        Pedido pedido = pedidoService.crearPedido(carritoService.obtenerCarrito());

        if (pedido != null) {
            carritoService.vaciarCarrito();
            System.out.println("Pedido generado correctamente.");
        }
    }

    private void menuPagos() {
        int opcion;
        do {
            System.out.println("""
                
                ===== CAJA REGISTRADORA =====
                
                1. Pagar pedido y emitir comprobante
                2. Ver mis comprobantes de pago
                0. Volver
                
                """);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> procesarCompraCompleta();
                case 2 -> facturacionService.listarComprobantes(); // El servicio filtra para mostrar SOLO los suyos
            }
        } while (opcion != 0);
    }

    private void procesarCompraCompleta() {
        System.out.println("\n--- PROCESO DE PAGO Y FACTURACIÓN ---");
        System.out.print("Ingrese el código del pedido a pagar (Ej: PED-1): ");
        String codigoPedido = scanner.nextLine();

        Pedido pedido = pedidoService.buscarPedido(codigoPedido);
        if (pedido == null) {
            System.out.println("Error: Pedido no encontrado.");
            return;
        }

        if (pedido.getEstado() == PedidoEstado.PAGADO) {
            System.out.println("Aviso: Este pedido ya ha sido pagado.");
            return;
        }

        // 1. LLAMAMOS AL SERVICIO DE PAGOS (El Scanner está adentro)
        MetodoPago pagoExitosoObject = pagoService.procesarCompra(pedido);

        if (pagoExitosoObject == null) {
            System.out.println("El pago falló o fue cancelado.");
            return;
        }

        // 2. LLAMAMOS AL SERVICIO DE FACTURACIÓN (El Scanner está adentro)
        String usernameCliente = SesionActual.getInstance().getUsuario().getUsername();
        facturacionService.generarComprobante(pedido, pagoExitosoObject, usernameCliente);
    }
}

