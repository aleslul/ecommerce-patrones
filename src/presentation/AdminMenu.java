package presentation;

import model.Categoria;
import model.Producto;
import model.Usuario;
import model.types.UsuarioRoles;
import patrones.facade.SeguridadFacade;
import patrones.proxy.InventarioManager;
import service.*;

import java.util.Scanner;

public class AdminMenu {
    private Scanner scanner;

    private ProductoService productoService;
    private UsuarioService usuarioService;
    private InventarioManager inventarioService;
    private SeguridadFacade seguridadFacade;

    public AdminMenu(Scanner scanner,
            ProductoService productoService,
            UsuarioService usuarioService, InventarioManager inventarioService
    ) {

        this.scanner = scanner;

        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.inventarioService = inventarioService;
        this.seguridadFacade = new SeguridadFacade(usuarioService);
    }

    public void iniciar() {

        int opcion;

        do {

            System.out.println("""
                
                ============================
                     E-COMMERCE (ADMIN)
                ============================
                
                1. Productos
                2. Usuarios
                3. Pagos
                4. Reportes
                5. Cambiar de usuario
                0. Salir
                
                """);

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> menuProductos();

                case 2 -> menuUsuarios();

                case 3 -> menuPagos();

                case 4 -> System.out.println("FUNCION NO IMPLEMENTADA"); //TODO: IMPLEMENTAR REPORTES

                case 5 -> {
                    seguridadFacade.cerrarSesion();
                    return;
                }

                case 0 -> System.out.println("Hasta luego.");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    /* TODO: ELIMINAR FUNCION (?)
    private boolean iniciarSesion() {
        System.out.println("==== INICIO DE SESIÓN ====");
        System.out.print("Username: ");
        String username =scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        boolean loginExitoso = seguridadFacade.iniciarSesion(username, password);
        if(!loginExitoso) {
            System.out.println("Usuario o contraseña incorrectos");
            return false;
        }
        System.out.println("Bienvenido " + username);
        return true;
    }*/

    private void menuProductos() {

        int opcion;

        do {
            System.out.println("""
                
                ===== PRODUCTOS =====
                
                1. Registrar producto
                2. Buscar producto
                3. Listar productos
                4. Agregar Stock
                5. Reducir Stock
                6. Eliminar producto
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> registrarProducto();

                case 2 -> buscarProducto();

                case 3 -> productoService.listarProductos();

                case 4 -> agregarStock();

                case 5 -> reducirStock();

                case 6 -> eliminarProducto();

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

        if (productoService.registrarProducto(producto)){
            System.out.println("Producto registrado.");
        }
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

    private void agregarStock() {

        System.out.print(
                "Código del producto: "
        );

        String codigo =
                scanner.nextLine();

        System.out.print(
                "Cantidad a agregar: "
        );

        int cantidad =
                scanner.nextInt();

        scanner.nextLine();

        inventarioService.agregarStock(
                codigo,
                cantidad
        );

        System.out.println(
                "Stock actualizado."
        );
    }

    private void reducirStock() {

        System.out.print("Código del producto: ");

        String codigo =scanner.nextLine();

        System.out.print("Cantidad a reducir: ");

        int cantidad =scanner.nextInt();
        scanner.nextLine();

        boolean reducido =
                inventarioService.reducirStock(codigo,cantidad);

        if(reducido) {

            System.out.println("Stock reducido correctamente.");

        } else {

            System.out.println("No fue posible reducir el stock.");
        }
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
                3. Modificar usuario
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> registrarUsuario();

                case 2 -> usuarioService.listarUsuarios();

                case 3 -> modificarUsuario();
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

    private void modificarUsuario() {

        System.out.print("Username a buscar: ");
        String username = scanner.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nueva contraseña: ");
        String password = scanner.nextLine();

        boolean resultado = usuarioService.modificarUsuario(username, nombre, password);

        if (resultado) {
            System.out.println("Usuario modificado correctamente");
        } else {
            System.out.println("ERROR: Usuario no encontrado");
        }
    }

    private void menuPagos() {

        int opcion;

        do {

            System.out.println("""
                
                ===== PAGOS =====
                
                1. Ver lista de pagos
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> System.out.println("FUNCION NO IMPLEMENTADA");
            }

        } while (opcion != 0);
    }
}

