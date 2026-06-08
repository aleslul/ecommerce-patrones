import model.Usuario;
import model.types.UsuarioRoles;
import patrones.proxy.InventarioManager;
import patrones.proxy.InventarioProxy;
import presentation.MenuConsola;
import repository.PedidoRepository;
import repository.ProductoRepository;
import repository.UsuarioRepository;
import repository.impl.PedidoRepositoryImpl;
import repository.impl.ProductoRepositoryImpl;
import repository.impl.UsuarioRepositoryImpl;
import service.*;

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


        //MENU
        MenuConsola menu = new MenuConsola(
                productoService,
                usuarioService,
                carritoService,
                pedidoService
        );
        menu.iniciar();
    }
}
