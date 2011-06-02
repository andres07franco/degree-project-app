package db;

import beans.Usuario;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Model {

    private static Model MODELO_SINGLETON = null;
    private String resources = "configuration.xml";

    /*Reader variable que se utiliza para acceder al archivo de configuracion de ibatis */
    private Reader reader;
    /*SqlSessionFactory es el constructor de la session sql esta la utilizamos para abrir una session */
    private SqlSessionFactory sqlSessionFactory;
    private SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    /*pedimos una sesion sql y la almacenamos en session */
    private SqlSession session;
    private Usuario user;

    private synchronized static void createInstance() {
        if (MODELO_SINGLETON == null) {
            MODELO_SINGLETON = new Model();
        }
    }

    public static Model getInstance() {
        if (MODELO_SINGLETON == null) {
            createInstance();
        }
        return MODELO_SINGLETON;
    }

    private Model() {
        try {
            reader = Resources.getResourceAsReader("configuration.xml");

            sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
            session = sqlSessionFactory.openSession(true);
        } catch (IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(null,"No se encuetra el archivo de configuracion","Inconveniente",javax.swing.JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.exit(0);
        }catch(org.apache.ibatis.exceptions.PersistenceException ex){
            javax.swing.JOptionPane.showMessageDialog(null,"Debe iniciar el servidro MYSQL para poder empezar","Inconveniente",javax.swing.JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.exit(0);
        }
    }

    public void setResources(String a) {
        resources = a;
    }

    public String getResources() {
        return resources;
    }

    public void desconectar() {
        try {
            if (session.getConnection() != null) {
                session.getConnection().close();
            }
        } catch (Exception ignorar) {
            ignorar.printStackTrace();
        }
    }

    public Collection obtenerListado(String sqlName) throws Exception {
        return session.selectList(sqlName);
    }

    public Collection obtenerListado(String sqlName, Object object) throws Exception {
        return session.selectList(sqlName, object);
    }

    public Object obtenerRegistro(String sqlName) throws Exception {
        return session.selectOne(sqlName);
    }

    public Object obtenerRegistro(String sqlName, Object object) throws Exception {
        return session.selectOne(sqlName, object);
    }

    public Object ejecutarProcedimiento(String sqlName) throws Exception {
        return session.update(sqlName);
    }

    public Object ejecutarProcedimiento(String sqlName, Object object) throws Exception {
        return session.update(sqlName, object);
    }

    public Object insertarRegistro(String sqlName, Object object) throws Exception {
        return session.insert(sqlName, object);
    }

    public Object actualizarRegistro(String sqlName, Object object) throws Exception {
        return session.update(sqlName, object);
    }

    public Object borrarRegistro(String sqlName, Object object) throws Exception {
        return session.delete(sqlName, object);
    }

    public Usuario getUsuario() {
        return user;
    }

    public void setUsuario(Usuario user) {
        this.user = user;
    }
}
