package ventanas;

import beans.Estado;
import beans.Grupo;
import db.Model;
import interfaces.Buscadores;
import interfaces.Constantes;
import java.awt.Frame;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class FormularioGrupo extends JDialog {

    private Grupo grupoSeleccionado;
    private Buscadores buscador;
    private int funcion;
    private Model modelo;

    public FormularioGrupo(Frame padre, boolean modal, Buscadores buscador) {
        super(padre, modal);

        this.buscador = buscador;
        this.modelo = Model.getInstance();
        grupoSeleccionado = new Grupo();
        initComponents();
        cargarCombo();
        this.setLocationRelativeTo(padre);
        this.setVisible(true);
    }

    public FormularioGrupo(Frame padre, boolean modal, Buscadores buscador, Grupo grupoSeleccionado) {
        super(padre, modal);
        this.buscador = buscador;
        this.modelo = Model.getInstance();
        this.grupoSeleccionado = grupoSeleccionado;
        initComponents();
        cargarCombo();
        llenar();
        this.setLocationRelativeTo(padre);
        this.setVisible(true);
    }

    private void cargarCombo() {
        try {
            List<Estado> listaEstados = (List<Estado>) modelo.obtenerListado("obtenerEstados");
            this.estado.removeAllItems();

            for (int i = 0; i < listaEstados.size(); i++) {
                this.estado.addItem(listaEstados.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        codigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        grupo = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        guardar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Grupos");

        jPanel1.setBackground(new java.awt.Color(153, 205, 255));

        codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoActionPerformed(evt);
            }
        });
        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codigoKeyTyped(evt);
            }
        });

        jLabel1.setText("Código:");

        jLabel2.setText("Grupo:");

        grupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grupoActionPerformed(evt);
            }
        });
        grupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grupoKeyTyped(evt);
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

        jLabel28.setText("Estado:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(codigo, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(grupo, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salir)
                    .addComponent(guardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean validar() {
        if (codigo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el CÓDIGO por Favor");
            codigo.requestFocus();
            return false;
        } else if (grupo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el GRUPO por Favor");
            grupo.requestFocus();
            return false;
        }

        return true;
    }

    private void llenar() {
        codigo.setText(grupoSeleccionado.getCodigo());
        grupo.setText(grupoSeleccionado.getGrupo());
        estado.setSelectedItem(grupoSeleccionado.getEstado());
        funcion = Constantes.ESTADO_EDICION;
    }

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {
            if (validar()) {
                int existeGrupo = (Integer) modelo.obtenerRegistro("existeGrupo", grupo.getText());
                int existeCodigo = (Integer) modelo.obtenerRegistro("existeGrupoPorCodigo", codigo.getText());

                if (funcion == Constantes.ESTADO_EDICION) {
                    if (grupoSeleccionado.getCodigo().equals(codigo.getText())) {
                        if (grupoSeleccionado.getGrupo().equals(grupo.getText())) {
                            guardar();
                        } else {
                            if (existeGrupo == 0) {
                                guardar();
                            } else {
                                JOptionPane.showMessageDialog(null, "El GRUPO ya Existe");
                                grupo.requestFocus();
                            }
                        }
                    } else {
                        if (existeCodigo > 0) {
                            JOptionPane.showMessageDialog(null, "El CÓDIGO ya Existe");
                            codigo.requestFocus();
                        } else {
                            if (grupoSeleccionado.getGrupo().equals(grupo.getText())) {
                                guardar();
                            } else {
                                if (existeGrupo == 0) {
                                    guardar();
                                } else {
                                    JOptionPane.showMessageDialog(null, "El GRUPO ya Existe");
                                    grupo.requestFocus();
                                }
                            }
                        }
                    }
                } else {
                    if (existeGrupo > 0) {
                        JOptionPane.showMessageDialog(null, "El GRUPO ya Existe");
                        grupo.requestFocus();
                    } else if (existeCodigo > 0) {
                        JOptionPane.showMessageDialog(null, "El CÓDIGO ya Existe");
                        codigo.requestFocus();
                    } else {
                        guardar();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}//GEN-LAST:event_guardarActionPerformed

    public void guardar() {
        grupoSeleccionado.setCodigo(codigo.getText());
        grupoSeleccionado.setGrupo(grupo.getText());
        grupoSeleccionado.setEstado((Estado) estado.getSelectedItem());

        try {
            if (funcion == Constantes.ESTADO_CREACION) {
                modelo.insertarRegistro("insertarGrupo", grupoSeleccionado);
            } else {
                modelo.actualizarRegistro("actualizarGrupo", grupoSeleccionado);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        buscador.buscar();
        this.dispose();
        JOptionPane.showMessageDialog(null, "Datos Guardados con Exito");
    }

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
}//GEN-LAST:event_salirActionPerformed

    private void codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoActionPerformed
        grupo.requestFocus();
    }//GEN-LAST:event_codigoActionPerformed

    private void grupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grupoActionPerformed
        guardar.requestFocus();
    }//GEN-LAST:event_grupoActionPerformed

    private void codigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyTyped
        if (codigo.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_codigoKeyTyped

    private void grupoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grupoKeyTyped
        if (grupo.getText().length() > 44) {
            evt.consume();
        }
    }//GEN-LAST:event_grupoKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigo;
    private javax.swing.JComboBox estado;
    private javax.swing.JTextField grupo;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
