import model.Usuario;
import model.types.UsuarioRoles;
import patrones.facade.SeguridadFacade;
import patrones.proxy.InventarioManager;
import patrones.proxy.InventarioProxy;
import presentation.AdminMenu;
import presentation.ClienteMenu;
import presentation.LoginMenu;
import presentation.MenuConsola;
import repository.PedidoRepository;
import repository.ProductoRepository;
import repository.UsuarioRepository;
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

        //SERVICES
        InventarioManager inventarioService = new InventarioProxy(new InventarioService(productoRepository));

        PedidoService pedidoService = new PedidoService(pedidoRepository, inventarioService);
        ProductoService productoService = new ProductoService(productoRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        CarritoService carritoService = new CarritoService(inventarioService);
        SeguridadFacade seguridadFacade = new SeguridadFacade(usuarioService);
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
                        pedidoService,
                        seguridadFacade
                );

        AdminMenu adminMenu =
                new AdminMenu(
                        scanner,
                        productoService,
                        usuarioService,
                        inventarioService
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
