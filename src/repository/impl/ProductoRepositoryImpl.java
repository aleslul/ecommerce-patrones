package repository.impl;

import model.Producto;
import repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements ProductoRepository {
    private List<Producto> productos;

    public ProductoRepositoryImpl() {
        this.productos = new ArrayList<>();
    }

    public ProductoRepositoryImpl(List<Producto> productos) {
        this.productos = productos;
    }

    //IMPLEMENTA DE: ProductoRepository
    @Override
    public void guardar(Producto producto) {
        productos.add(producto);
    }

    @Override
    public Producto buscarPorCodigo(String codigo) {
        if (productos.isEmpty()) {
            return null;
        }

        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }

    @Override
    public List<Producto> listar() {
        if (productos.isEmpty()){
            return productos;
        }
        System.out.println("\n================================================================================================");
        System.out.printf("| %-10s | %-25s | %-10s | %-10s | %-20s |%n",
                "CODIGO",
                "NOMBRE",
                "PRECIO",
                "STOCK",
                "CATEGORIA");
        System.out.println("================================================================================================");

        for (Producto producto : productos) {

            System.out.printf("| %-10s | %-25s | S/. %-7.2f | %-10d | %-20s |%n",
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock(),
                    producto.getCategoria().getNombre());
        }

        System.out.println("================================================================================================");

        return productos;
    }

    @Override
    public void eliminar(String codigo) {
        Producto p = buscarPorCodigo(codigo);

        if (p != null) {
            productos.remove(p);
        }
    }
}
