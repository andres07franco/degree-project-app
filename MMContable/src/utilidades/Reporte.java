/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import db.Model;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;
import java.util.*;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author carlos
 */
public class Reporte {

    Model m;
    int funcion = 0;
    javax.swing.JFrame d;
    private transient Connection con;
    private String jdbcDriver;
    private String databaseURL;
    private String user;
    private String password;

    public Reporte() {
        
    }

    public Connection getConexion() {
        try {

            jdbcDriver = ("com.mysql.jdbc.Driver");
            user = ("root");
            databaseURL = ("jdbc:mysql://localhost:3306/m_mcontable");
            password = ("");
            connect();
            if (isConnect()) {
                System.out.println("Conectado");
                return con;

            } else {
                
            }
        } catch (Exception er) {
            return null;
        }
        return null;
    }

    public void connect() throws SQLException {
        if (this.isConnect()) {
            throw new SQLException("Ya Esta Conectado");
        }
        if (jdbcDriver == null) {
            throw new SQLException("No hay jdbcDriver cargado");
        }
        if (databaseURL == null) {
            throw new SQLException("No hay URL cargado en base de datos");
        }

        try {
            Class.forName(jdbcDriver).newInstance();
        } catch (Exception e) {
            throw new SQLException("La Clase" + jdbcDriver + "No se pudo cargar");
        }

        con = DriverManager.getConnection(this.databaseURL, this.user, this.password);
    }

    public boolean isConnect() {
        if (con == null) {
            return false;
        } else {
            return true;
        }
    }

    public Reporte(Model m) {
        this.m = m;
    }



    public Reporte( JFrame d) {
        this.d = d;
        funcion = 1;
    }


    public static void main(String ar[]){
        try {


                 Map parametro=new HashMap();
                   parametro.put("fecha","2011-06-01");
                   parametro.put("fecha2","2011-06-01");

                   new utilidades.Reporte().runReporte("Reporte de Caja.jasper", parametro);

            } catch (Exception ex) {
               ex.printStackTrace();
            }
    }

    public void runReporte(String archivo, Map parametro) {
        //this.id_contact="";
        //this.id_contact = id;

        try {

            String master = System.getProperty("user.dir")
                    + "\\" + archivo;


            if (master == null) {
                System.out.println("No encuentro el archivo del reporte maestro.");
               // System.exit(2);
            }
            System.out.println(master);
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(master);
                //masterReport  = JasperCompileManager.compileReport(master);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
              //  System.exit(3);
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, this.getConexion());

            //Se lanza el Viewer de Jasper, no termina aplicaciÃ³n al salir
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setExtendedState(jviewer.MAXIMIZED_BOTH);
            if (funcion == 1) {
                jviewer.addWindowListener(new WindowListener() {

                    public void windowOpened(WindowEvent e) {
                        //   throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void windowClosing(WindowEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        d.setVisible(true);
                    }

                    public void windowClosed(WindowEvent e) {
                        d.setVisible(true);
                        // throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void windowIconified(WindowEvent e) {
                        //  throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void windowDeiconified(WindowEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void windowActivated(WindowEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void windowDeactivated(WindowEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
            }
            jviewer.setTitle("Reporte");
            jviewer.setVisible(true);
        } catch (Exception j) {
            j.printStackTrace();
            System.out.println("Mensaje de Error:" + j.getMessage());
        }

    }
}
