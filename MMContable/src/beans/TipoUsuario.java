/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Usuario
 */
public class TipoUsuario {

    private int id;
    private String descripcion;

    @Override
    public String toString() {
        return descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        final TipoUsuario other = (TipoUsuario) obj;
        if (!this.descripcion.equals(other.descripcion)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;

        return hash;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the descrpcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descrpcion the descrpcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
