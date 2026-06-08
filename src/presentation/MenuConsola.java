package presentation;

import model.Usuario;
import model.types.UsuarioRoles;
import patrones.singleton.sesion.SesionActual;

public class MenuConsola {

    private LoginMenu loginMenu;
    private ClienteMenu clienteMenu;
    private AdminMenu adminMenu;

    public MenuConsola(
            LoginMenu loginMenu,
            ClienteMenu clienteMenu,
            AdminMenu adminMenu
    ) {

        this.loginMenu = loginMenu;
        this.clienteMenu = clienteMenu;
        this.adminMenu = adminMenu;
    }

    public void iniciar() {

        while (true) {

            loginMenu.iniciarSesion();

            Usuario usuario =
                    SesionActual
                            .getInstance()
                            .getUsuario();

            if (usuario == null) {
                continue;
            }

            if (usuario.getRol()
                    == UsuarioRoles.ADMINISTRADOR) {

                adminMenu.iniciar();

            } else {

                clienteMenu.iniciar();
            }
        }
    }
}
