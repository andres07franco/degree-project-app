package beans;

public class Grupo {

    private int id;
    private String codigo;
    private String grupo;
    private Estado estado;

    public Grupo() {
    }

    public Grupo(int id, String codigo, String grupo, Estado estado) {
        this.id = id;
        this.codigo = codigo;
        this.grupo = grupo;
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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return grupo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Grupo other = (Grupo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!this.grupo.equals(other.grupo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
