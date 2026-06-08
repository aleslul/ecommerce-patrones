package presentation;

import model.Pedido;
import model.Producto;
import patrones.facade.SeguridadFacade;
import service.CarritoService;
import service.PedidoService;
import service.ProductoService;


import java.util.Scanner;

public class ClienteMenu {

    private Scanner scanner;

    private ProductoService productoService;
    private CarritoService carritoService;
    private PedidoService pedidoService;
    private SeguridadFacade seguridadFacade;

    public ClienteMenu(Scanner scanner, ProductoService productoService,
                       CarritoService carritoService, PedidoService pedidoService,
                       SeguridadFacade seguridadFacade) {
        this.scanner = scanner;
        this.productoService = productoService;
        this.carritoService = carritoService;
        this.pedidoService = pedidoService;
        this.seguridadFacade = seguridadFacade;
    }

    public void iniciar() {

        int opcion;

        do {

            System.out.println("""
                
                ============================
                     E-COMMERCE
                ============================
                
                1. Productos
                2. Carrito
                3. Pedidos
                4. Pago
                5. Cambiar de usuario
                0. Salir
                
                """);

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

                case 0 -> System.out.println("Hasta luego.");

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

                case 1 -> productoService.listarProductos();

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
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> generarPedido();

                case 2 -> pedidoService.listarPedidos();
            }

        } while (opcion != 0);
    }

    //TODO: Actualizar esto despues con patrones.builder
    private void generarPedido() {
        Pedido pedido =
                pedidoService.crearPedido(
                        carritoService.obtenerCarrito()
                );

        if (pedido != null) {

            carritoService.vaciarCarrito();

            System.out.println(
                    "Pedido generado correctamente."
            );
        }
    }

    private void menuPagos() {

        int opcion;

        do {

            System.out.println("""
                
                ===== PAGOS =====
                
                1. Pagar pedido
                2. Ver comprobante de pago
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> System.out.println("FUNCION NO IMPLEMENTADA");

                case 2 -> System.out.println("FUNCION NO IMPLEMENTADA");
            }

        } while (opcion != 0);
    }
}

