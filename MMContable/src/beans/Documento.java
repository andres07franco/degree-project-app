package beans;

import java.math.BigDecimal;
import java.util.Date;

public class Documento {

    private Integer id;
    private Date fecha;
    private TipoDocumento tipo;
    private Tercero tercero;
    private String numero;
    private BigDecimal total;
    private BigDecimal descuento;
    private String nota;
    private TipoPago tipopago;
    private Date fechavencimiento;
    private BigDecimal totalpagado;
    private TipoDescuento tipodescuento;
    private BigDecimal subtotal;
    private Documento documento;
    private String estado;

    @Override
    public String toString() {
        return "Documento{" + "id=" + id + "fecha=" + fecha + "tipo=" + tipo + "tercero=" + tercero + "numero=" + numero + "total=" + total + "descuento=" + descuento + "nota=" + nota + "tipopago=" + tipopago + "fechavencimiento=" + fechavencimiento + "totalpagado=" + totalpagado + "tipodescuento=" + tipodescuento + "subtotal=" + subtotal + "documento=" + documento + "estado=" + estado + '}';
    }




   

    public Documento() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }


    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public TipoPago getTipopago() {
        return tipopago;
    }

    public void setTipopago(TipoPago tipopago) {
        this.tipopago = tipopago;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public BigDecimal getTotalpagado() {
        return totalpagado;
    }

    public void setTotalpagado(BigDecimal totalpagado) {
        this.totalpagado = totalpagado;
    }

    public TipoDescuento getTipodescuento() {
        return tipodescuento;
    }

    public void setTipodescuento(TipoDescuento tipodescuento) {
        this.tipodescuento = tipodescuento;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the documento_id
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * @param documento_id the documento_id to set
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }




}
