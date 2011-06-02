package beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Articulo {

    private int id;
    private String codigobarras;
    private String descripcioncomercial;
    private TipoArticulo tipo;
    private byte[] imagen;
    private BigDecimal vlrcosto;
    private BigDecimal vlrpromedio;
    private BigDecimal porcentajeminimo;
    private BigDecimal porcentajesugerido;
    private BigDecimal existencia;
    private BigDecimal cantidadminima;
    private Date fechaucompra;
    private Date fechauventa;
    private String observaciones;
    private BigDecimal valormin;
    private BigDecimal valorsug;
    private BigDecimal vlrpromediov;
    private BigDecimal saldocosto;
    private BigDecimal saldoventa;
    private Grupo grupo;
    private Marca marca;
    private Estado estado;
    private List<ArticuloCombo> listaArticulos;

    public Articulo() {
    }

    public Articulo(int id, String codigobarras, String descripcioncomercial, TipoArticulo tipo, byte[] imagen, BigDecimal vlrcosto, BigDecimal vlrpromedio, BigDecimal porcentajeminimo, BigDecimal porcentajesugerido, BigDecimal existencia, BigDecimal cantidadminima, Date fechaucompra, Date fechauventa, String observaciones, BigDecimal valormin, BigDecimal valorsug, BigDecimal vlrpromediov, BigDecimal saldocosto, BigDecimal saldoventa, Grupo grupo, Marca marca, Estado estado, List<ArticuloCombo> listaArticulos) {
        this.id = id;
        this.codigobarras = codigobarras;
        this.descripcioncomercial = descripcioncomercial;
        this.tipo = tipo;
        this.imagen = imagen;
        this.vlrcosto = vlrcosto;
        this.vlrpromedio = vlrpromedio;
        this.porcentajeminimo = porcentajeminimo;
        this.porcentajesugerido = porcentajesugerido;
        this.existencia = existencia;
        this.cantidadminima = cantidadminima;
        this.fechaucompra = fechaucompra;
        this.fechauventa = fechauventa;
        this.observaciones = observaciones;
        this.valormin = valormin;
        this.valorsug = valorsug;
        this.vlrpromediov = vlrpromediov;
        this.saldocosto = saldocosto;
        this.saldoventa = saldoventa;
        this.grupo = grupo;
        this.marca = marca;
        this.estado = estado;
        this.listaArticulos = listaArticulos;
    }

    public List<ArticuloCombo> getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(List<ArticuloCombo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigobarras() {
        return codigobarras;
    }

    public void setCodigobarras(String codigobarras) {
        this.codigobarras = codigobarras;
    }

    public String getDescripcioncomercial() {
        return descripcioncomercial;
    }

    public void setDescripcioncomercial(String descripcioncomercial) {
        this.descripcioncomercial = descripcioncomercial;
    }

    public TipoArticulo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArticulo tipo) {
        this.tipo = tipo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public BigDecimal getVlrcosto() {
        return vlrcosto;
    }

    public void setVlrcosto(BigDecimal vlrcosto) {
        this.vlrcosto = vlrcosto;
    }

    public BigDecimal getVlrpromedio() {
        return vlrpromedio;
    }

    public void setVlrpromedio(BigDecimal vlrpromedio) {
        this.vlrpromedio = vlrpromedio;
    }

    public BigDecimal getPorcentajeminimo() {
        return porcentajeminimo;
    }

    public void setPorcentajeminimo(BigDecimal porcentajeminimo) {
        this.porcentajeminimo = porcentajeminimo;
    }

    public BigDecimal getPorcentajesugerido() {
        return porcentajesugerido;
    }

    public void setPorcentajesugerido(BigDecimal porcentajesugerido) {
        this.porcentajesugerido = porcentajesugerido;
    }

    public BigDecimal getExistencia() {
        return existencia;
    }

    public void setExistencia(BigDecimal existencia) {
        this.existencia = existencia;
    }

    public BigDecimal getCantidadminima() {
        return cantidadminima;
    }

    public void setCantidadminima(BigDecimal cantidadminima) {
        this.cantidadminima = cantidadminima;
    }

    public Date getFechaucompra() {
        return fechaucompra;
    }

    public void setFechaucompra(Date fechaucompra) {
        this.fechaucompra = fechaucompra;
    }

    public Date getFechauventa() {
        return fechauventa;
    }

    public void setFechauventa(Date fechauventa) {
        this.fechauventa = fechauventa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getValormin() {
        return valormin;
    }

    public void setValormin(BigDecimal valormin) {
        this.valormin = valormin;
    }

    public BigDecimal getValorsug() {
        return valorsug;
    }

    public void setValorsug(BigDecimal valorsug) {
        this.valorsug = valorsug;
    }

    public BigDecimal getVlrpromediov() {
        return vlrpromediov;
    }

    public void setVlrpromediov(BigDecimal vlrpromediov) {
        this.vlrpromediov = vlrpromediov;
    }

    public BigDecimal getSaldocosto() {
        return saldocosto;
    }

    public void setSaldocosto(BigDecimal saldocosto) {
        this.saldocosto = saldocosto;
    }

    public BigDecimal getSaldoventa() {
        return saldoventa;
    }

    public void setSaldoventa(BigDecimal saldoventa) {
        this.saldoventa = saldoventa;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return getCodigobarras();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Articulo other = (Articulo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!this.descripcioncomercial.equals(other.descripcioncomercial)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
