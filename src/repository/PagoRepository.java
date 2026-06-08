package repository;

import patrones.bridge.pay.abstraction.MetodoPago;

import java.util.List;

public interface PagoRepository {
    void guardar(MetodoPago pago);
    List<MetodoPago> listar();
}
