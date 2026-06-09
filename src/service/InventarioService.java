package service;

import model.Producto;
import patrones.observer.InventarioObserver;
import patrones.observer.InventarioSubject;
import patrones.proxy.inventario.InventarioManager;
import repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;

public class InventarioService implements InventarioManager, InventarioSubject {
    private ProductoRepository repository;
    private List<InventarioObserver> observadores;

    public InventarioService(ProductoRepository repository) {
        this.repository = repository;
        this.observadores = new ArrayList<>();
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
        notificar(producto);
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

        if (producto.getStock() == 0) {
            notificar(producto);
        }
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

    @Override
    public void suscribir(InventarioObserver observador) {
        observadores.add(observador);
    }

    @Override
    public void desuscribir(InventarioObserver observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificar(Producto producto) {
        for (InventarioObserver observador : observadores) {
            observador.actualizar(producto);
        }
    }
}
