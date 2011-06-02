package beans;

public class Tercero {

    private int id;
    private Long nit;
    private String nombre;
    private String direccion;
    private Departamento departamento;
    private Ciudad ciudad;
    private String telefono;
    private String contacto;
    private String email;
    private String celular;
    private Estado estado;
    private TipoTercero tipo;

    public Tercero() {
    }

    public Tercero(int id, Long nit, TipoTercero tipo, String nombre, String direccion, Departamento departamento, Ciudad ciudad, String telefono, String contacto, String email, String celular, Estado estado) {
        this.id = id;
        this.nit = nit;
        this.tipo = tipo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.contacto = contacto;
        this.email = email;
        this.celular = celular;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public TipoTercero getTipo() {
        return tipo;
    }

    public void setTipo(TipoTercero tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
