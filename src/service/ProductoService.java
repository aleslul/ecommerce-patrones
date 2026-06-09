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
        return repository.listar();
    }

    public void imprimirTablaProductos(List<Producto> productos) {
        if (productos.isEmpty()){
            System.out.println("No existen productos registrados");
            return;
        }
        System.out.println("\n================================================================================================");
        System.out.printf("| %-10s | %-25s | %-10s | %-10s | %-20s |%n",
                "CODIGO", "NOMBRE", "PRECIO", "STOCK", "CATEGORIA");
        System.out.println("================================================================================================");

        for (Producto producto : productos) {
            System.out.printf("| %-10s | %-25s | S/. %-7.2f | %-10d | %-20s |%n",
                    producto.getCodigo(), producto.getNombre(),
                    producto.getPrecio(), producto.getStock(),
                    producto.getCategoria().getNombre());
        }
        System.out.println("================================================================================================");
    }

    public void eliminarProducto(Producto producto) {
        if (producto == null) {
            System.out.println("ERROR: Producto invalido");
            return;
        }
        repository.eliminar(producto.getCodigo());
    }
}
