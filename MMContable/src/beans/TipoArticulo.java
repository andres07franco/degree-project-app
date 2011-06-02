package beans;

public class TipoArticulo {

    private int id;
    private String descripcion;

    public TipoArticulo() {
    }

    public TipoArticulo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        TipoArticulo other = (TipoArticulo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!this.descripcion.equals(other.descripcion)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
