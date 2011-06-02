package beans;

import java.math.BigDecimal;
import java.util.Date;

public class Kardex {

    private int id;
    private Articulo articulo;
    private Documento documento;
    private BigDecimal entradas;
    private BigDecimal salidas;
    private BigDecimal vlrunitario;
    private BigDecimal vlrtotal;
    private Date fecha;
    private Date hora;
    private BigDecimal existencia;

    public Kardex() {
    }

    public Kardex(int id) {
        this.id = id;
    }

    public Kardex(int id, Articulo articulo, Documento documento, BigDecimal entradas, BigDecimal salidas, BigDecimal vlrUnitario, BigDecimal vlrTotal, Date fecha, Date hora) {
        this.id = id;
        this.articulo = articulo;
        this.documento = documento;
        this.entradas = entradas;
        this.salidas = salidas;
        this.vlrunitario = vlrUnitario;
        this.vlrtotal = vlrTotal;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public BigDecimal getEntradas() {
        return entradas;
    }

    public void setEntradas(BigDecimal entradas) {
        this.entradas = entradas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getSalidas() {
        return salidas;
    }

    public void setSalidas(BigDecimal salidas) {
        this.salidas = salidas;
    }

    public BigDecimal getVlrtotal() {
        return vlrtotal;
    }

    public void setVlrtotal(BigDecimal vlrtotal) {
        this.vlrtotal = vlrtotal;
    }

    public BigDecimal getVlrunitario() {
        return vlrunitario;
    }

    public void setVlrunitario(BigDecimal vlrunitario) {
        this.vlrunitario = vlrunitario;
    }

    /**
     * @return the existencia
     */
    public BigDecimal getExistencia() {
        return existencia;
    }

    /**
     * @param existencia the existencia to set
     */
    public void setExistencia(BigDecimal existencia) {
        this.existencia = existencia;
    }
}
