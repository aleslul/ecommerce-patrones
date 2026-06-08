package patrones.singleton.sesion;

import model.Usuario;

public class SesionActual {
    private static SesionActual instancia;
    private Usuario usuario;

    private SesionActual() {
    }

    public static SesionActual getInstance() {
        if (instancia == null) {
            instancia = new SesionActual();
        }
        return instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean haySesion() {
        return usuario != null;
    }

    public void cerrarSesion() {
        usuario = null;
    }
}
