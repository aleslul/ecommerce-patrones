package service;

import model.Producto;
import org.w3c.dom.ls.LSInput;
import patrones.proxy.InventarioManager;
import repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;

public class InventarioService implements InventarioManager{
    private ProductoRepository repository;

    public InventarioService(ProductoRepository repository) {
        this.repository = repository;
    }

    public boolean verificarStock(String codigo, int cantidad) {
        Producto producto = repository.buscarPorCodigo(codigo);

        if (producto == null) {
            System.out.println("ERROR: Producto no encontrado");
            return false;
        };

        if (producto.getStock() < cantidad) {
            System.out.println("ERROR: Stock insuficiente");
            return false;
        }

        return true;
    }

    public void agregarStock(String codigo, int cantidad) {
        Producto producto = repository.buscarPorCodigo(codigo);
        if (producto == null) {
            System.out.println("ERROR: Producto no encontrado");
            return;
        }

        producto.setStock(producto.getStock() + cantidad);
    }

    public boolean reducirStock(String codigo, int cantidad) {
        Producto producto = repository.buscarPorCodigo(codigo);
        if (producto == null) {
            System.out.println("ERROR: Producto no encontrado");
            return false;
        };
        if (producto.getStock() < cantidad) {
            System.out.println("ERROR: Stock insuficiente");
            return false;
        };

        producto.setStock(producto.getStock() - cantidad);
        return true;
    }

    public int obtenerStock(String coidgo) {
        Producto producto = repository.buscarPorCodigo(coidgo);
        if (producto == null) return -1;

        return producto.getStock();
    }

    //TODO: METODO A REVISAR
    public List<Producto> listarProductosSinStock() {
        List<Producto> resultado = new ArrayList<>();

        for (Producto p : repository.listar()) {
            if (p.getStock() == 0) resultado.add(p);
        }
        return resultado;
    }

    public List<Producto> listarProductosConStockBajo() {
        List<Producto> resultado = new ArrayList<>();

        for (Producto p : repository.listar()) {
            if (p.getStock() < 5) resultado.add(p);
        }
        return resultado;
    }
}
