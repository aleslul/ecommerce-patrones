package presentation;

import model.*;
import model.types.UsuarioRoles;
import service.*;

import java.util.Scanner;

/*
* NOTE: Hola, todo lo que quieran imprimir, TODO, lo tienen que hacer aqui y despues esto se referencia en el main.
* */

public class MenuConsola {

    private Scanner scanner;

    private ProductoService productoService;
    private UsuarioService usuarioService;
    private CarritoService carritoService;
    private PedidoService pedidoService;

    public MenuConsola(
            ProductoService productoService,
            UsuarioService usuarioService,
            CarritoService carritoService,
            PedidoService pedidoService
    ) {

        this.scanner = new Scanner(System.in);

        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.carritoService = carritoService;
        this.pedidoService = pedidoService;
    }

    public void iniciar() {

        int opcion;

        do {

            System.out.println("""
                
                ============================
                     E-COMMERCE
                ============================
                
                1. Productos
                2. Usuarios
                3. Carrito
                4. Pedidos
                0. Salir
                
                """);

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> menuProductos();

                case 2 -> menuUsuarios();

                case 3 -> menuCarrito();

                case 4 -> menuPedidos();

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
                
                1. Registrar producto
                2. Buscar producto
                3. Listar productos
                4. Eliminar producto
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> registrarProducto();

                case 2 -> buscarProducto();

                case 3 -> productoService.listarProductos();

                case 4 -> eliminarProducto();

            }

        } while (opcion != 0);
    }

    private void registrarProducto() {

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();

        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Categoria: ");
        String categoriaNombre = scanner.nextLine();
        Categoria categoria = new Categoria(categoriaNombre);

        Producto producto = new Producto();

        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setCategoria(categoria);

        productoService.registrarProducto(producto);

        System.out.println("Producto registrado.");
    }

    private void buscarProducto() {

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
    }

    private void eliminarProducto() {

        System.out.print("Código: ");

        String codigo = scanner.nextLine();

        Producto producto =
                productoService.buscarProducto(
                        codigo
                );

        if (producto != null) {

            productoService.eliminarProducto(
                    producto
            );

            System.out.println(
                    "Producto eliminado."
            );
        }
    }

    private void menuUsuarios() {

        int opcion;

        do {

            System.out.println("""
                
                ===== USUARIOS =====
                
                1. Registrar usuario
                2. Listar usuarios
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> registrarUsuario();

                case 2 -> usuarioService.listarUsuarios();

            }

        } while (opcion != 0);
    }

    private void registrarUsuario() {

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        Usuario usuario =
                new Usuario(
                        username,
                        password,
                        UsuarioRoles.CLIENTE //EL ADMINISTRADOR SE CREA EN EL MAIN
                );

        usuarioService.registrarUsuario(
                usuario
        );

        System.out.println(
                "Usuario registrado."
        );
    }

    private void menuCarrito() {

        int opcion;

        do {

            System.out.println("""
                
                ===== CARRITO =====
                
                1. Agregar producto
                2. Ver carrito
                3. Vaciar carrito
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> agregarProductoCarrito();

                case 2 -> carritoService.mostrarCarrito();

                case 3 -> carritoService.vaciarCarrito();
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

        System.out.print("Cantidad: ");

        int cantidad = scanner.nextInt();
        scanner.nextLine();

        carritoService.agregarProducto(
                producto,
                cantidad
        );

        System.out.println(
                "Producto agregado."
        );
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

    //TODO: Actualizar esto despues con builder
    private void generarPedido() {

        pedidoService.crearPedido(carritoService.obtenerCarrito());

        carritoService.vaciarCarrito();

        System.out.println(
                "Pedido generado."
        );
    }


}
