package service;

import model.Usuario;
import repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = repository.listar();

        if (usuarios.isEmpty()) {
            System.out.println("ERROR: No se han encontrado usuarios registrados");
        }

        return usuarios;
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario == null) {
            System.out.println("ERROR: Usuario invalido");
            return;
        }

        if (repository.buscarPorUsername(usuario.getUsername()) != null) {
            System.out.println("ERROR: El usuario ya existe");
            return;
        }
        repository.guardar(usuario);
    }

    public Usuario buscarUsuarioPorUsername(String username) {
        return repository.buscarPorUsername(username);
    }

    public boolean modificarUsuario(String username, String nuevoUsername, String nuevaPassword) {
        Usuario usuario = repository.buscarPorUsername(username);
        if (usuario == null) {
            return false;
        }
        usuario.setUsername(nuevoUsername);
        usuario.setPassword(nuevaPassword);
        repository.modificar(usuario);
        return true;
    }
}
