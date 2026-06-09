package repository.impl;

import model.Usuario;
import repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public UsuarioRepositoryImpl() {
        this.usuarios = new ArrayList<>();
    }

    public UsuarioRepositoryImpl(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarios.add(usuario);
    }
    @Override
    public Usuario buscarPorUsername(
            String username
    ) {

        for (Usuario usuario : usuarios) {

            if (usuario.getUsername()
                    .equals(username)) {

                return usuario;
            }
        }

        return null;
    }

    @Override
    public List<Usuario> listar() {
        return usuarios;
    }

    @Override
    public void modificar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {

            Usuario actual = usuarios.get(i);

            if (
                    actual.getUsername()
                            .equals(usuario.getUsername())
            ) {

                usuarios.set(i, usuario);
                return;
            }
        }
    }
}
