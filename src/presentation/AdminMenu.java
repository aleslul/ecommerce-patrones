package presentation;

import model.Categoria;
import model.Producto;
import model.Usuario;
import model.types.UsuarioRoles;
import patrones.decorator.reporte.Reporte;
import patrones.decorator.reporte.ReporteLogs;
import patrones.decorator.reporte.ReportePagos;
import patrones.decorator.reporte.ReporteResumen;
import patrones.facade.SeguridadFacade;
import patrones.proxy.inventario.InventarioManager;
import repository.FacturacionRepository;
import service.*;

import java.util.Scanner;

public class AdminMenu {
    private Scanner scanner;

    private ProductoService productoService;
    private UsuarioService usuarioService;
    private InventarioManager inventarioService;
    private SeguridadFacade seguridadFacade;
    private FacturacionService facturacionService;
    private FacturacionRepository facturacionRepository;

    public AdminMenu(Scanner scanner,
            ProductoService productoService,
            UsuarioService usuarioService, InventarioManager inventarioService,
                     FacturacionService facturacionService, FacturacionRepository facturacionRepository) {

        this.scanner = scanner;

        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.inventarioService = inventarioService;
        this.seguridadFacade = new SeguridadFacade(usuarioService);
        this.facturacionService = facturacionService;
        this.facturacionRepository = facturacionRepository;
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
                3. Reportes de pago
                4. Cambiar de usuario
                0. Salir
                
                """);

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> menuProductos();

                case 2 -> menuUsuarios();

                case 3 -> menuReportes();

                case 4 -> {
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

        String password;
        do {
            System.out.print("Password: ");
            password = scanner.nextLine();
            if (password.trim().isEmpty()) {
                System.out.println("ERROR: La contraseña no puede estar vacía. Intente de nuevo");
            }
        } while (password.trim().isEmpty());

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

    private void menuReportes() {

        int opcion;

        do {

            System.out.println("""
                
                ===== REPORTES =====
                
                1. Reporte base
                2. Reporte con resumen
                3. Reporte con logs
                
                0. Volver
                
                """);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> generarReporteBase();

                case 2-> generarReporteResumen();

                case 3-> generarReporteConLogs();
            }

        } while (opcion != 0);
    }

    private void generarReporteBase() {
        Reporte reporte =
        new ReportePagos(facturacionRepository);
        reporte.generar();
    }

    private void generarReporteResumen() {
        Reporte reporte = new ReporteResumen(new ReportePagos(facturacionRepository),facturacionRepository);
        reporte.generar();
    }

    private void generarReporteConLogs() {

        Reporte reporte =new ReporteLogs(new ReportePagos(facturacionRepository));
        reporte.generar();
    }
}

