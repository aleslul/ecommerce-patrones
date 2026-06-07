package repository;

import model.Producto;

import java.util.List;

public interface ProductoRepository {
    void guardar(Producto producto);
    Producto buscarPorCodigo(String codigo);
    List<Producto> listar();
    void eliminar(String codigo);
}
