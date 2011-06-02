package beans;

import java.math.BigDecimal;

public class ArticuloCombo {

    private Articulo articuloPadre;
    private Articulo articuloHijo;
    private int cantidad;
    private BigDecimal precioEnCombo;

    public ArticuloCombo() {
    }

    public ArticuloCombo(Articulo articuloPadre, Articulo articuloHijo, int cantidad, BigDecimal precioEnCombo) {
        this.articuloPadre = articuloPadre;
        this.articuloHijo = articuloHijo;
        this.cantidad = cantidad;
        this.precioEnCombo = precioEnCombo;
    }

    public Articulo getArticuloHijo() {
        return articuloHijo;
    }

    public void setArticuloHijo(Articulo articuloHijo) {
        this.articuloHijo = articuloHijo;
    }

    public Articulo getArticuloPadre() {
        return articuloPadre;
    }

    public void setArticuloPadre(Articulo articuloPadre) {
        this.articuloPadre = articuloPadre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioEnCombo() {
        return precioEnCombo;
    }

    public void setPrecioEnCombo(BigDecimal precioEnCombo) {
        this.precioEnCombo = precioEnCombo;
    }
}
