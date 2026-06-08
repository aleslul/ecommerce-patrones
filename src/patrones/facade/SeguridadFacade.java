package patrones.facade;

import model.Usuario;
import model.types.UsuarioRoles;
import patrones.singleton.sesion.SesionActual;
import service.UsuarioService;

public class SeguridadFacade {

    private final UsuarioService usuarioService;

    public SeguridadFacade(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public boolean iniciarSesion(String username, String password) {

        Usuario usuario =usuarioService.buscarUsuarioPorUsername(username);

        if(usuario == null) {
            return false;
        }
        if(!usuario.getPassword().equals(password)) {
            return false;
        }
        SesionActual.getInstance().setUsuario(usuario);
        return true;
    }

    public void cerrarSesion() {
        SesionActual.getInstance().setUsuario(null);
    }

    public Usuario obtenerUsuarioActual() {
        return SesionActual.getInstance()
                .getUsuario();
    }

    public boolean esAdministrador() {
        Usuario usuario =SesionActual.getInstance().getUsuario();
        return usuario != null && usuario.getRol() == UsuarioRoles.ADMINISTRADOR;
    }
}