package ventanas;

import beans.Estado;
import java.awt.Frame;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import beans.Funcion;
import beans.TipoUsuario;
import beans.Usuario;
import db.Model;
import interfaces.Buscadores;
import java.util.HashMap;
import java.util.Map;

public class Formulario_Usuarios extends javax.swing.JDialog {

    JTabbedPane tab;
    JDesktopPane dp;
    Usuario u;
    int funcion = interfaces.Constantes.ESTADO_CREACION;
    Buscadores b;
    List<Funcion> l = new LinkedList();
    Model m;

    public Formulario_Usuarios(Frame parent, Boolean modal, Buscadores b) {

        super(parent, modal);
        setTitle("Usuarios del Sistema y Nivel de Acceso");
        this.m = Model.getInstance();
        funcion = interfaces.Constantes.ESTADO_CREACION;
        u = new Usuario();
        this.b = b;
        initComponents();
        iniciar();
        usuario.requestFocus();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public Formulario_Usuarios(Frame parent, Boolean modal, Usuario u, Buscadores b) {

        super(parent, modal);
        this.b = b;
        this.m = Model.getInstance();
        funcion = interfaces.Constantes.ESTADO_EDICION;
        try {
            this.u = (Usuario) m.obtenerRegistro("obtenerUsuarioPorId", u.getId());
        } catch (Exception ex) {
            Logger.getLogger(Formulario_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        setTitle("Usuarios del Sistema y Nivel de Acceso");
        initComponents();
        iniciar();
        llenar();
        usuario.requestFocus();
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void llenar() {

        usuario.setText(u.getUsuario());
        clave.setText(u.getPassword());
        clave2.setText(u.getPassword());
        System.out.println(u.getTipo()+" "+u.getEstado());
        tipo.setSelectedItem(u.getTipo());
        estado.setSelectedItem(u.getEstado());
        try {
            List<Funcion> funcionesUsuario = (List<Funcion>) m.obtenerListado("obtenerFuncionesUsuario", u);
            for (Funcion fun : funcionesUsuario) {
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    Funcion fun2 = (Funcion) tabla.getValueAt(i, 1);
                    if (fun.equals(fun2)) {
                        tabla.setValueAt(new Boolean(true), i, 2);
                    }
                }
            }
        } catch (Exception er) {
            er.printStackTrace();
        }

    }

    public void iniciar() {
        DefaultTableModel dtm = new DefaultTableModel() {

            public Class getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Object.class;
                } else if (columnIndex == 1) {
                    return Object.class;
                }
                return Boolean.class;
            }
        };
        dtm.addColumn("Index");
        dtm.addColumn("Opciones");
        dtm.addColumn("Seleccione");

        try {
            List<Funcion> listaCiudades = (List<Funcion>) Model.getInstance().obtenerListado("obtenerFunciones");
            for (Funcion obj : listaCiudades) {

                Object fila[] = new Object[3];
                fila[0] = obj.getIndice() + "";
                fila[1] = obj;
                if (funcion == interfaces.Constantes.ESTADO_CREACION) {
                    fila[2] = new Boolean(true);
                } else {
                    fila[2] = new Boolean(false);
                }
                dtm.addRow(fila);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tabla.setModel(dtm);
        llenarTipos();
        llenarEstados();

        this.setResizable(false);;
        this.setSize(this.getWidth(), 300);


    }

    public void llenarTipos() {
        tipo.removeAllItems();
        try {

            List<TipoUsuario> listaTipoUsuario = (List<TipoUsuario>) Model.getInstance().obtenerListado("obtenerTipoUsuarios");

            for (TipoUsuario obj : listaTipoUsuario) {
                tipo.addItem(obj);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void llenarEstados() {
        estado.removeAllItems();
        try {

            List<Estado> listaEstado = (List<Estado>) Model.getInstance().obtenerListado("obtenerEstados");

            for (Estado obj : listaEstado) {
                estado.addItem(obj);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean validar() {
        if (usuario.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el Usuario por favor");
            usuario.requestFocus();
            return false;
        } else if (clave.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la Contraseña por favor");
            clave.requestFocus();
            return false;
        } else if (clave2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la Repetición de la Contraseña por favor");
            clave2.requestFocus();
            return false;
        } else if (!clave.getText().equals(clave2.getText())) {
            JOptionPane.showMessageDialog(null, "Las Contraseñas no coinciden");
            clave.requestFocus();
            return false;
        }if(funcion == interfaces.Constantes.ESTADO_EDICION && estado.getSelectedIndex()>0){
                    try {
                        Map<String, Object> mapa = new HashMap<String, Object>();
                        mapa.put("id", u.getId());
                        List<Usuario> listaUsuarios = (List<Usuario>) m.obtenerListado("obtenerUsuariosAct",mapa);

                        if(listaUsuarios.size()==0){
                            JOptionPane.showMessageDialog(null, "No se puede desactivar este usuario, pues no quedarán usuarios activos para iniciar sesión");
            clave.requestFocus();
            return false;
                        }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }

        return true;
    }

    public boolean verificarFunciones() {

        for (int i = 0; i < tabla.getRowCount(); i++) {
            boolean valor = ((Boolean) tabla.getValueAt(i, 2)).booleanValue();
            if (valor) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        usuario = new javax.swing.JTextField();
        clave = new javax.swing.JPasswordField();
        clave2 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btndeseleccionart = new javax.swing.JButton();
        btnselecionart = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        guardar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 51, 255));

        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });
        usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuarioKeyTyped(evt);
            }
        });

        clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveActionPerformed(evt);
            }
        });
        clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveKeyTyped(evt);
            }
        });

        clave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clave2ActionPerformed(evt);
            }
        });
        clave2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clave2KeyTyped(evt);
            }
        });

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        jLabel3.setText("Confirme Contraseña");

        estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));

        jLabel4.setText("Estado");

        tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Cajero", "Personalizado" }));

        jLabel5.setText("Tipo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(estado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clave2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clave, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addGap(69, 69, 69))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Autorizado Para Las Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 255))); // NOI18N

        jScrollPane1.setBackground(new java.awt.Color(153, 204, 255));
        jScrollPane1.setBorder(null);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Index", "Opcion", "Seleccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        btndeseleccionart.setFont(new java.awt.Font("Tahoma", 1, 11));
        btndeseleccionart.setForeground(new java.awt.Color(0, 51, 153));
        btndeseleccionart.setText("Deseleccionar Todo");
        btndeseleccionart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeseleccionartActionPerformed(evt);
            }
        });

        btnselecionart.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnselecionart.setForeground(new java.awt.Color(0, 51, 153));
        btnselecionart.setText("Seleccionar Todo");
        btnselecionart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnselecionartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnselecionart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndeseleccionart)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btndeseleccionart)
                    .addComponent(btnselecionart))
                .addContainerGap())
        );

        salir.setBackground(new java.awt.Color(0, 153, 255));
        salir.setFont(new java.awt.Font("Tahoma", 1, 11));
        salir.setForeground(new java.awt.Color(0, 51, 153));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bulletCritical.png"))); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        guardar.setBackground(new java.awt.Color(0, 153, 255));
        guardar.setFont(new java.awt.Font("Tahoma", 1, 11));
        guardar.setForeground(new java.awt.Color(0, 51, 153));
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salir)
                    .addComponent(guardar))
                .addGap(65, 65, 65)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clave2ActionPerformed
        // TODO add your handling code here:
        guardar.requestFocus();
}//GEN-LAST:event_clave2ActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_salirActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        if (validar()) {

            int cantidad = 0;
            try {
                cantidad = ((Integer) m.obtenerRegistro("existeUsuario", usuario.getText())).intValue();
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }

            if (funcion == interfaces.Constantes.ESTADO_EDICION) {
                if (u.getUsuario().equals(usuario.getText())) {
                   
                    guardar();
                } else if (cantidad > 0) {
                    JOptionPane.showMessageDialog(null, "El Usuario ya Existe");
                    usuario.requestFocus();
                }else {
                    guardar();
                }


            } else if (cantidad > 0) {
                JOptionPane.showMessageDialog(null, "El Usuario ya Existe");
                usuario.requestFocus();
            } else {

                guardar();
            }
        }
}//GEN-LAST:event_guardarActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        // TODO add your handling code here:
        clave.requestFocus();
    }//GEN-LAST:event_usuarioActionPerformed

    private void claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveActionPerformed
        // TODO add your handling code here:
        clave2.requestFocus();
    }//GEN-LAST:event_claveActionPerformed

    private void btnselecionartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnselecionartActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tabla.setValueAt(new Boolean(true), i, 2);
        }
    }//GEN-LAST:event_btnselecionartActionPerformed

    private void btndeseleccionartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeseleccionartActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tabla.setValueAt(new Boolean(false), i, 2);
        }
    }//GEN-LAST:event_btndeseleccionartActionPerformed

    private void usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyTyped
        // TODO add your handling code here:
        if ( usuario.getText().length() >= 40) {
            evt.consume();
        }
    }//GEN-LAST:event_usuarioKeyTyped

    private void claveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_claveKeyTyped
        // TODO add your handling code here:
                if ( clave.getText().length() >= 40) {
            evt.consume();
        }
    }//GEN-LAST:event_claveKeyTyped

    private void clave2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clave2KeyTyped
        // TODO add your handling code here:
                if ( clave2.getText().length() >= 40) {
            evt.consume();
        }
    }//GEN-LAST:event_clave2KeyTyped

    public void guardar() {

        u.setUsuario(usuario.getText());
        u.setPassword(clave.getText());
        u.setTipo((TipoUsuario) tipo.getSelectedItem());
        u.setEstado((Estado) estado.getSelectedItem());
        u.setEmpresa(m.getUsuario().getEmpresa());
        try {
            if (funcion == interfaces.Constantes.ESTADO_CREACION) {
                m.insertarRegistro("insertarUsuario", u);
            } else {
                m.insertarRegistro("actualizarUsuario", u);
            }
            m.borrarRegistro("borrarFuncionesUsuario", u.getId());
            for (int i = 0; i < tabla.getRowCount(); i++) {
                boolean valor = ((Boolean) tabla.getValueAt(i, 2)).booleanValue();
                Funcion fu = (Funcion) tabla.getValueAt(i, 1);
                if (valor) {
                    Map parametros = new HashMap<String, String>();
                    parametros.put("usuario", u.getId() + "");
                    parametros.put("funcion", fu.getId() + "");
                    m.insertarRegistro("insertarFuncionesDeUsuario", parametros);
                }
            }
            b.buscar();
            this.dispose();
            JOptionPane.showMessageDialog(null, "Datos guardados con exito");
        } catch (Exception ex) {
            Logger.getLogger(Formulario_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Hubo un error al guardar los datos, comuníquese con su proveedor para solucionarlo", "Error Grave", JOptionPane.ERROR_MESSAGE);
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndeseleccionart;
    private javax.swing.JButton btnselecionart;
    private javax.swing.JPasswordField clave;
    private javax.swing.JPasswordField clave2;
    private javax.swing.JComboBox estado;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton salir;
    private javax.swing.JTable tabla;
    private javax.swing.JComboBox tipo;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
