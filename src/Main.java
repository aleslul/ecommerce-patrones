import model.Usuario;
import model.types.UsuarioRoles;
import presentation.MenuConsola;
import repository.PagoRepository;
import repository.PedidoRepository;
import repository.ProductoRepository;
import repository.UsuarioRepository;
import repository.impl.PagoRepositoryImpl;
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
        ProductoRepository productoRepository = new ProductoRepositoryImpl();
        PedidoRepository pedidoRepository = new PedidoRepositoryImpl();
        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();

        // AÑADIDO: Tu repositorio simulado (la base de datos para los pagos)
        PagoRepository pagoRepository = new PagoRepositoryImpl();

        // SERVICES
        // Arreglé el orden aquí: InventarioService debe crearse ANTES que PedidoService para poder pasárselo
        InventarioService inventarioService = new InventarioService(productoRepository);
        PedidoService pedidoService = new PedidoService(pedidoRepository, inventarioService);

        ProductoService productoService = new ProductoService(productoRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        CarritoService carritoService = new CarritoService();

        // AÑADIDO: Tu servicio de pagos que controla la lógica
        PagoService pagoService = new PagoService(pagoRepository);

        // DATOS INICIALES
        usuarioService.registrarUsuario(
                new Usuario(
                        "admin",
                        "1234",
                        UsuarioRoles.ADMINISTRADOR
                )
        );

        // MENU
        MenuConsola menu = new MenuConsola(
                productoService,
                usuarioService,
                carritoService,
                pedidoService,
                pagoService // AÑADIDO: ¡El 5to parámetro que pedía la consola para que no marque rojo!
        );
        menu.iniciar();
    }
}
