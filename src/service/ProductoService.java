package service;

import model.Producto;
import repository.ProductoRepository;

import java.util.List;

public class ProductoService {
    private ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    //FUNCIONES
        public boolean registrarProducto(Producto producto) {
        if (producto == null) {
            System.out.println("ERROR: Producto invalido");
            return false;
        }
        if (repository.buscarPorCodigo(producto.getCodigo()) != null) {
            System.out.println("ERROR: Ya existe un producto con ese codigo");
            return false;
        }
        repository.guardar(producto);
        return true;
    }

    public Producto buscarProducto(String codigo) {
        Producto producto = repository.buscarPorCodigo(codigo);

        if (producto == null) {
            System.out.println("ERROR: Producto no encontrado");
        }

        return producto;
    }

    public List<Producto> listarProductos() {
        List<Producto> productos = repository.listar();

        if (productos.isEmpty()) {
            System.out.println("ERROR: No existen productos registrados");
        }

        return productos;
    }

    public void eliminarProducto(Producto producto) {
        if (producto == null) {
            System.out.println("ERROR: Producto invalido");
            return;
        }
        repository.eliminar(producto.getCodigo());
    }
}
