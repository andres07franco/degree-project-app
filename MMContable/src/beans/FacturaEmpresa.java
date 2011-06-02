package beans;

public class FacturaEmpresa {

    private int id;
    private String encabezado1;
    private String encabezado2;
    private String encabezado3;
    private String encabezado4;
    private String encabezado5;
    private int numeroActual;
    private PapelImpresion papel;
    private String pie1;
    private String pie2;
    private long cotizaciones;
    private long prefacturas;
    private long ingresos;
    private long egresos;
    private Empresa empresa;

    public FacturaEmpresa() {
    }

    public FacturaEmpresa(int id, String encabezado1, String encabezado2, String encabezado3, String encabezado4, String encabezado5, int numeroActual, PapelImpresion papel, String pie1, String pie2, long cotizaciones, long prefacturas, long ingresos, long egresos, Empresa empresa) {
        this.id = id;
        this.encabezado1 = encabezado1;
        this.encabezado2 = encabezado2;
        this.encabezado3 = encabezado3;
        this.encabezado4 = encabezado4;
        this.encabezado5 = encabezado5;
        this.numeroActual = numeroActual;
        this.papel = papel;
        this.pie1 = pie1;
        this.pie2 = pie2;
        this.cotizaciones = cotizaciones;
        this.prefacturas = prefacturas;
        this.ingresos = ingresos;
        this.egresos = egresos;
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEncabezado1() {
        return encabezado1;
    }

    public void setEncabezado1(String encabezado1) {
        this.encabezado1 = encabezado1;
    }

    public String getEncabezado2() {
        return encabezado2;
    }

    public void setEncabezado2(String encabezado2) {
        this.encabezado2 = encabezado2;
    }

    public String getEncabezado3() {
        return encabezado3;
    }

    public void setEncabezado3(String encabezado3) {
        this.encabezado3 = encabezado3;
    }

    public String getEncabezado4() {
        return encabezado4;
    }

    public void setEncabezado4(String encabezado4) {
        this.encabezado4 = encabezado4;
    }

    public String getEncabezado5() {
        return encabezado5;
    }

    public void setEncabezado5(String encabezado5) {
        this.encabezado5 = encabezado5;
    }

    public int getNumeroActual() {
        return numeroActual;
    }

    public void setNumeroActual(int numeroActual) {
        this.numeroActual = numeroActual;
    }

    public PapelImpresion getPapel() {
        return papel;
    }

    public void setPapel(PapelImpresion papel) {
        this.papel = papel;
    }

    public String getPie1() {
        return pie1;
    }

    public void setPie1(String pie1) {
        this.pie1 = pie1;
    }

    public String getPie2() {
        return pie2;
    }

    public void setPie2(String pie2) {
        this.pie2 = pie2;
    }

    public long getCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(long cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    public long getPrefacturas() {
        return prefacturas;
    }

    public void setPrefacturas(long prefacturas) {
        this.prefacturas = prefacturas;
    }

    public long getIngresos() {
        return ingresos;
    }

    public void setIngresos(long ingresos) {
        this.ingresos = ingresos;
    }

    public long getEgresos() {
        return egresos;
    }

    public void setEgresos(long egresos) {
        this.egresos = egresos;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
