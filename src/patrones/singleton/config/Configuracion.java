package patrones.singleton.config;

public class Configuracion {
    private static Configuracion instancia;

    private double igv;
    private double tipoCambio;
    private String monedaPorDefecto;

    private Configuracion() {
        this.igv = 0.18;
        this.tipoCambio = 3.80;
        this.monedaPorDefecto = "SOLES";
    }

    public static Configuracion getInstance() {
        // Si no existe, la crea por primera y única vez. Si ya existe, te devuelve la misma.
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getMonedaPorDefecto() {
        return monedaPorDefecto;
    }

    public void setMonedaPorDefecto(String monedaPorDefecto) {
        this.monedaPorDefecto = monedaPorDefecto;
    }
}
