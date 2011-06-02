package beans;

public class Marca {

    private int id;
    private String codigo;
    private String marca;
    private Estado estado;

    public Marca() {
    }

    public Marca(int id, String codigo, String marca, Estado estado) {
        this.id = id;
        this.codigo = codigo;
        this.marca = marca;
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return marca;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Marca other = (Marca) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!this.marca.equals(other.marca)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
