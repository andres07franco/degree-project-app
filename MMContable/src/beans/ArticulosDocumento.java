package beans;

import java.math.BigDecimal;

public class ArticulosDocumento {

    private long id;
    private BigDecimal vlrunitario;
    private BigDecimal iva;
    private BigDecimal vlrparcial;
    private BigDecimal cantidad;
    private Documento documento;
    private Articulo articulo;

    public ArticulosDocumento() {
    }

    public ArticulosDocumento(long id, BigDecimal vlrunitario, BigDecimal iva, BigDecimal vlrparcial, BigDecimal cantidad, Documento documento, Articulo articulo) {
        this.id = id;
        this.vlrunitario = vlrunitario;
        this.iva = iva;
        this.vlrparcial = vlrparcial;
        this.cantidad = cantidad;
        this.documento = documento;
        this.articulo = articulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getVlrunitario() {
        return vlrunitario;
    }

    public void setVlrunitario(BigDecimal vlrunitario) {
        this.vlrunitario = vlrunitario;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getVlrparcial() {
        return vlrparcial;
    }

    public void setVlrparcial(BigDecimal vlrparcial) {
        this.vlrparcial = vlrparcial;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
