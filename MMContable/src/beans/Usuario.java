package beans;

public class Usuario {

    private int id;
    private String usuario;
    private String password;
    private Empresa empresa;
    private Estado estado;
    private TipoUsuario tipo;


    public Usuario(int id, String usuario, String password, TipoUsuario tipo, Estado estado) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Usuario() {
    }

    public Usuario(int id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return usuario;
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
