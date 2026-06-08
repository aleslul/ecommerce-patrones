package patrones.proxy;

import model.Producto;
import model.Usuario;
import model.types.UsuarioRoles;
import patrones.singleton.sesion.SesionActual;

import java.util.List;

public class InventarioProxy implements InventarioManager {
    private InventarioManager inventario;

    public InventarioProxy(InventarioManager inventario) {
        this.inventario = inventario;
    }

    private boolean tienePermiso() {
        Usuario usuario = SesionActual.getInstance().getUsuario();

        if (usuario == null) {
            System.out.println("ERROR: No hay sesión activa");
            return false;
        }
        if (usuario.getRol() != UsuarioRoles.ADMINISTRADOR) {
            System.out.println("ERROR: Acceso denegado");
            return false;
        }
        return true;
    }

    @Override
    public void agregarStock(String codigo, int cantidad) {
        if (!tienePermiso()) return;
        inventario.agregarStock(codigo, cantidad);
    }

    @Override
    public boolean reducirStock(String codigo, int cantidad) {
        if (!tienePermiso()) return false;

        return inventario.reducirStock(codigo, cantidad);
    }

    @Override
    public int obtenerStock(String codigo) {
        return inventario.obtenerStock(codigo);
    }

    @Override
    public boolean verificarStock(String codigo, int cantidad) {
        return inventario.verificarStock(codigo, cantidad);
    }

    @Override
    public List<Producto> listarProductosSinStock() {
        return inventario.listarProductosSinStock();
    }

    @Override
    public List<Producto> listarProductosConStockBajo() {
        return inventario.listarProductosConStockBajo();
    }
}
