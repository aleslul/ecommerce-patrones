package model;

import patrones.prototype.ProductoPrototype;

public class Producto implements ProductoPrototype{
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    private Categoria categoria;

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Producto() {
    }

    public Producto(String codigo, String nombre, double precio, int stock, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }

    @Override
    public Producto clonar() {
        Producto copia = new Producto();

        copia.setCodigo(this.codigo);
        copia.setNombre(this.nombre);
        copia.setPrecio(this.precio);
        copia.setStock(this.stock);

        return copia;
    }
}
