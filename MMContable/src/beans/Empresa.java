package beans;

public class Empresa {

    private int id;
    private Ciudad ciudad;
    private String direccion;
    private String email;
    private String licencia;
    private long nit;
    private String nombre;
    private String telefono;
    private String propietario;
    private String estado;

    public Empresa() {
    }

    public Empresa(int id, Ciudad ciudad, String direccion, String email, String licencia, long nit, String nombre, String telefono, String propietario) {
        this.id = id;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.email = email;
        this.licencia = licencia;
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.propietario = propietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Empresa{" + "Nit=" + nit + "Nombre=" + nombre + '}';
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
