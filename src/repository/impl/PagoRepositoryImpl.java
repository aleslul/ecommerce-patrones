package repository.impl;

import patrones.bridge.pay.abstraction.MetodoPago;
import repository.PagoRepository;
import java.util.ArrayList;
import java.util.List;

public class PagoRepositoryImpl implements PagoRepository {
    private List<MetodoPago> pagos;

    public PagoRepositoryImpl() {
        this.pagos = new ArrayList<>();
    }

    // Constructor que recibe una lista ya hecha
    public PagoRepositoryImpl(List<MetodoPago> pagos) {
        this.pagos = pagos;
    }

    @Override
    public void guardar(MetodoPago pago) {
        pagos.add(pago);
    }

    @Override
    public List<MetodoPago> listar() {
        if (pagos.isEmpty()) {
            return null;
        }

        System.out.println("\n=========================================================================");
        System.out.printf("| %-25s | %-40s |%n",
                "METODO DE PAGO",
                "ESTADO DEL REGISTRO");
        System.out.println("=========================================================================");

        for (MetodoPago pago : pagos) {
            System.out.printf("| %-25s | %-40s |%n",
                    pago.getClass().getSimpleName(),
                    "Pago procesado y guardado con éxito");
        }

        System.out.println("=========================================================================");

        return pagos;
    }
}
