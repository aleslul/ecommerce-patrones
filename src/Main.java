import model.Usuario;
import model.types.UsuarioRoles;
import presentation.MenuConsola;
import repository.PedidoRepository;
import repository.ProductoRepository;
import repository.UsuarioRepository;
import repository.impl.PedidoRepositoryImpl;
import repository.impl.ProductoRepositoryImpl;
import repository.impl.UsuarioRepositoryImpl;
import service.*;

public class Main {

    /*
    *   Hola Enzo y Fernanda.
    * Para que el codigo que van a programar se acople bien con los demás códigos hay ciertas clases que serán nuestras bases
    * y que las usaremos los tres,
    *
    * */

    public static void main(String[] args) {
        //REPOSITORIS
        ProductoRepository productoRepository = new ProductoRepositoryImpl();
        PedidoRepository pedidoRepository = new PedidoRepositoryImpl();
        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();

        //SERVICES
        PedidoService pedidoService = new PedidoService(pedidoRepository);
        ProductoService productoService = new ProductoService(productoRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        InventarioService inventarioService = new InventarioService(productoRepository);

        CarritoService carritoService = new CarritoService();

        //DATOS INICIALES
        usuarioService.registrarUsuario(
                new Usuario(
                        "admin",
                        "1234",
                        UsuarioRoles.ADMINISTRADOR
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
