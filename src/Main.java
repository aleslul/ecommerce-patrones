import model.Usuario;
import model.types.UsuarioRoles;
import patrones.facade.SeguridadFacade;
import patrones.observer.InventarioConcreteObserver;
import patrones.proxy.inventario.InventarioManager;
import patrones.proxy.inventario.InventarioProxy;
import patrones.proxy.pedidos.PedidoManager;
import patrones.proxy.pedidos.PedidoProxy;
import presentation.AdminMenu;
import presentation.ClienteMenu;
import presentation.LoginMenu;
import presentation.MenuConsola;
import repository.FacturacionRepository;
import repository.PedidoRepository;
import repository.ProductoRepository;
import repository.UsuarioRepository;
import repository.impl.FacturacionRepositoryImpl;
import repository.impl.PedidoRepositoryImpl;
import repository.impl.ProductoRepositoryImpl;
import repository.impl.UsuarioRepositoryImpl;
import service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //REPOSITORIS
        ProductoRepository productoRepository = new ProductoRepositoryImpl();
        PedidoRepository pedidoRepository = new PedidoRepositoryImpl();
        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
        FacturacionRepository facturacionRepository = new FacturacionRepositoryImpl();

        //SERVICES
        InventarioService inventarioReal = new InventarioService(productoRepository);

        InventarioConcreteObserver alertaStock = new InventarioConcreteObserver();
        inventarioReal.suscribir(alertaStock);

        InventarioManager inventarioProxy = new InventarioProxy(inventarioReal);

        PedidoManager pedidoReal = new PedidoService(pedidoRepository, inventarioReal);
        PedidoManager pedidoProxy = new PedidoProxy(pedidoReal);

        ProductoService productoService = new ProductoService(productoRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        SeguridadFacade seguridadFacade = new SeguridadFacade(usuarioService);
        PagoService pagoService = new PagoService();
        FacturacionService facturacionService = new FacturacionService(facturacionRepository);


        //USANDO PROXY:
        CarritoService carritoService = new CarritoService(inventarioProxy);

        Scanner scanner = new Scanner(System.in);
        //DATOS INICIALES
        usuarioService.registrarUsuario(
                new Usuario(
                        "admin",
                        "1234",
                        UsuarioRoles.ADMINISTRADOR
                )
        );

        usuarioService.registrarUsuario(
                new Usuario(
                        "cliente",
                        "1234",
                        UsuarioRoles.CLIENTE
                )
        );

        usuarioService.registrarUsuario(
                new Usuario(
                        "aleslul",
                        "1234",
                        UsuarioRoles.CLIENTE
                )
        );


        LoginMenu loginMenu =
                new LoginMenu(
                        scanner,
                        seguridadFacade
                );

        ClienteMenu clienteMenu =
                new ClienteMenu(
                        scanner,
                        productoService,
                        carritoService,
                        pedidoProxy,
                        seguridadFacade,
                        pagoService,
                        facturacionService
                );

        AdminMenu adminMenu =
                new AdminMenu(
                        scanner,
                        productoService,
                        usuarioService,
                        inventarioProxy,
                        facturacionService,
                        facturacionRepository,
                        pedidoProxy
                );

        MenuConsola menuConsola =
                new MenuConsola(
                        loginMenu,
                        clienteMenu,
                        adminMenu
                );

        menuConsola.iniciar();
    }
}
