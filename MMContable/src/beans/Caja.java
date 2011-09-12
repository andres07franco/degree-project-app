package beans;

import java.math.BigDecimal;
import java.util.Date;

public class Caja {

    private int id;
    private BigDecimal ventasefectivo;
    private BigDecimal ventascredito;
    private BigDecimal pagosdecliente;
    private BigDecimal anticipos;
    private BigDecimal otrosingresos;
    private BigDecimal gastosvarios;
    private BigDecimal pagoaproveedor;
    private BigDecimal devolucionesv;
    private BigDecimal devolucionesa;
    private BigDecimal retirocaja;
    private BigDecimal iniciodia;
    private BigDecimal saldoactual;
    private BigDecimal ganancia;
    private BigDecimal cajaprincipal;
    private Date fechaabre;
    private Date fechacierre;
    private Date horaabre;
    private Date horacierre;
    private int estado;
    private BigDecimal comprasefectivo;
    private BigDecimal comprascredito;

    public Caja() {
    }

    public Caja(int id, BigDecimal ventasefectivo, BigDecimal ventascredito, BigDecimal pagosdecliente, BigDecimal anticipos, BigDecimal otrosingresos, BigDecimal gastosvarios, BigDecimal pagoaproveedor, BigDecimal devolucionesv, BigDecimal devolucionesa, BigDecimal retirocaja, BigDecimal iniciodia, BigDecimal saldoactual, BigDecimal ganancia, BigDecimal cajaprincipal, Date fechaabre, Date fechacierre, Date horaabre, Date horacierre, int estado) {
        this.id = id;
        this.ventasefectivo = ventasefectivo;
        this.ventascredito = ventascredito;
        this.pagosdecliente = pagosdecliente;
        this.anticipos = anticipos;
        this.otrosingresos = otrosingresos;
        this.gastosvarios = gastosvarios;
        this.pagoaproveedor = pagoaproveedor;
        this.devolucionesv = devolucionesv;
        this.devolucionesa = devolucionesa;
        this.retirocaja = retirocaja;
        this.iniciodia = iniciodia;
        this.saldoactual = saldoactual;
        this.ganancia = ganancia;
        this.cajaprincipal = cajaprincipal;
        this.fechaabre = fechaabre;
        this.fechacierre = fechacierre;
        this.horaabre = horaabre;
        this.horacierre = horacierre;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getVentasefectivo() {
        return ventasefectivo;
    }

    public void setVentasefectivo(BigDecimal ventasefectivo) {
        this.ventasefectivo = ventasefectivo;
    }

    public BigDecimal getVentascredito() {
        return ventascredito;
    }

    public void setVentascredito(BigDecimal ventascredito) {
        this.ventascredito = ventascredito;
    }

    public BigDecimal getPagosdecliente() {
        return pagosdecliente;
    }

    public void setPagosdecliente(BigDecimal pagosdecliente) {
        this.pagosdecliente = pagosdecliente;
    }

    public BigDecimal getAnticipos() {
        return anticipos;
    }

    public void setAnticipos(BigDecimal anticipos) {
        this.anticipos = anticipos;
    }

    public BigDecimal getOtrosingresos() {
        return otrosingresos;
    }

    public void setOtrosingresos(BigDecimal otrosingresos) {
        this.otrosingresos = otrosingresos;
    }

    public BigDecimal getGastosvarios() {
        return gastosvarios;
    }

    public void setGastosvarios(BigDecimal gastosvarios) {
        this.gastosvarios = gastosvarios;
    }

    public BigDecimal getPagoaproveedor() {
        return pagoaproveedor;
    }

    public void setPagoaproveedor(BigDecimal pagoaproveedor) {
        this.pagoaproveedor = pagoaproveedor;
    }

    public BigDecimal getDevolucionesv() {
        return devolucionesv;
    }

    public void setDevolucionesv(BigDecimal devolucionesv) {
        this.devolucionesv = devolucionesv;
    }

    public BigDecimal getDevolucionesa() {
        return devolucionesa;
    }

    public void setDevolucionesa(BigDecimal devolucionesa) {
        this.devolucionesa = devolucionesa;
    }

    public BigDecimal getRetirocaja() {
        return retirocaja;
    }

    public void setRetirocaja(BigDecimal retirocaja) {
        this.retirocaja = retirocaja;
    }

    public BigDecimal getIniciodia() {
        return iniciodia;
    }

    public void setIniciodia(BigDecimal iniciodia) {
        this.iniciodia = iniciodia;
    }

    public BigDecimal getSaldoactual() {
        return saldoactual;
    }

    public void setSaldoactual(BigDecimal saldoactual) {
        this.saldoactual = saldoactual;
    }

    public BigDecimal getGanancia() {
        return ganancia;
    }

    public void setGanancia(BigDecimal ganancia) {
        this.ganancia = ganancia;
    }

    public BigDecimal getCajaprincipal() {
        return cajaprincipal;
    }

    public void setCajaprincipal(BigDecimal cajaprincipal) {
        this.cajaprincipal = cajaprincipal;
    }

    public Date getFechaabre() {
        return fechaabre;
    }

    public void setFechaabre(Date fechaabre) {
        this.fechaabre = fechaabre;
    }

    public Date getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Date fechacierre) {
        this.fechacierre = fechacierre;
    }

    public Date getHoraabre() {
        return horaabre;
    }

    public void setHoraabre(Date horaabre) {
        this.horaabre = horaabre;
    }

    public Date getHoracierre() {
        return horacierre;
    }

    public void setHoracierre(Date horacierre) {
        this.horacierre = horacierre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the comprasefectivo
     */
    public BigDecimal getComprasefectivo() {
        return comprasefectivo;
    }

    /**
     * @param comprasefectivo the comprasefectivo to set
     */
    public void setComprasefectivo(BigDecimal comprasefectivo) {
        this.comprasefectivo = comprasefectivo;
    }

    /**
     * @return the comprascredito
     */
    public BigDecimal getComprascredito() {
        return comprascredito;
    }

    /**
     * @param comprascredito the comprascredito to set
     */
    public void setComprascredito(BigDecimal comprascredito) {
        this.comprascredito = comprascredito;
    }
}
