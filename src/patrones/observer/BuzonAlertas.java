package patrones.observer;

import java.util.HashMap;
import java.util.Map;

public class BuzonAlertas {
    private static BuzonAlertas instancia;
    private Map<String, String> alertas;

    private BuzonAlertas() {
        alertas = new HashMap<>();
    }

    public static BuzonAlertas getInstance() {
        if (instancia == null) {
            instancia = new BuzonAlertas();
        }
        return instancia;
    }

    public void agregarAlerta(String codigoProducto, String mensaje) {
        alertas.put(codigoProducto, mensaje);
    }

    public void removerAlerta(String codigoProducto) {
        alertas.remove(codigoProducto);
    }

    public void mostrarAlertas() {
        if (!alertas.isEmpty()) {
            System.out.println("\n*** NOTIFICACIONES DEL SISTEMA ***");
            for (String mensaje : alertas.values()) {
                System.out.println(mensaje);
            }
            System.out.println("**********************************");
        }
    }
}