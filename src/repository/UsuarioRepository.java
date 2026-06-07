package repository;

import model.Usuario;

import java.util.List;

public interface UsuarioRepository {
    void guardar(Usuario usuario);
    Usuario buscarPorUsername(String unsername);
    List<Usuario> listar();
}
