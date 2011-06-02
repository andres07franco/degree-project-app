package beans;

import java.math.BigDecimal;

public class TipoDescuento {

    private Integer id;
    private String descripcion;
    private char tipo;
    private BigDecimal valor;

    public TipoDescuento() {
    }

    public TipoDescuento(int id, String descripcion, char tipo, BigDecimal valor) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        final TipoDescuento other = (TipoDescuento) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
