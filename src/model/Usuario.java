package model;

import model.types.UsuarioRoles;
import repository.UsuarioRepository;

public class Usuario {
    private String username;
    private String password;
    private UsuarioRoles rol;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UsuarioRoles getRol() {
        return rol;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(UsuarioRoles rol) {
        this.rol = rol;
    }

    public Usuario(String username, String password, UsuarioRoles rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }
}
