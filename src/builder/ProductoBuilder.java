package builder;

import model.Categoria;
import model.Producto;

/*
    Esto es para crear nuevos productos:
    ANTES:
        Producto producto = new Producto();
        ...

    AHORA:
        Producto producto =
        new ProductoBuilder()
                .codigo(codigo)
                .nombre(nombre)
                .precio(precio)
                .stock(stock)
                .categoria(categoria)
                .build();
*/

public class ProductoBuilder {
    private Producto producto;

    public ProductoBuilder() {
        this.producto = new Producto();
    }

    public ProductoBuilder codigo(String codigo) {
        producto.setCodigo(codigo);
        return this;
    }

    public ProductoBuilder nombre(String nombre) {
        producto.setNombre(nombre);
        return this;
    }

    public ProductoBuilder precio(double precio) {
        producto.setPrecio(precio);
        return this;
    }

    public ProductoBuilder stock(int stock) {
        producto.setStock(stock);
        return this;
    }

    public ProductoBuilder categoria(Categoria categoria) {
        producto.setCategoria(categoria);
        return this;
    }

    public Producto build() {
        return producto;
    }
}
