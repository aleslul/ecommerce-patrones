package presentation;

import model.Usuario;
import patrones.facade.SeguridadFacade;

import java.util.Scanner;

public class LoginMenu {
    private Scanner scanner;
    private SeguridadFacade seguridadFacade;

    public LoginMenu(
            Scanner scanner,
            SeguridadFacade seguridadFacade
    ) {
        this.scanner = scanner;
        this.seguridadFacade = seguridadFacade;
    }

    public Usuario iniciarSesion() {

        while (true) {

            System.out.println(
                    "\n==== INICIO DE SESIÓN ===="
            );

            System.out.print(
                    "Username: "
            );

            String username =
                    scanner.nextLine();

            System.out.print(
                    "Password: "
            );

            String password =
                    scanner.nextLine();

            boolean loginExitoso = seguridadFacade.iniciarSesion(username, password);

            if (!loginExitoso) {

                System.out.println(
                        "Usuario o contraseña incorrectos"
                );

                continue;
            }

            Usuario usuario = seguridadFacade.obtenerUsuarioActual();

            System.out.println( "Bienvenido " + usuario.getUsername());

            return usuario;
        }
    }
}
