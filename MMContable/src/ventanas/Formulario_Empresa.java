package ventanas;

import beans.Ciudad;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import beans.Empresa;
import db.Model;
import interfaces.Buscadores;
import java.util.List;
import utilidades.Validaciones;

public class Formulario_Empresa extends javax.swing.JInternalFrame {

    JTabbedPane  tab;
    JDesktopPane  dp;
    Empresa em;
    int funcion = interfaces.Constantes.ESTADO_CREACION;
    Buscadores buscador;
    Model m;

    public Formulario_Empresa(JTabbedPane tab, JDesktopPane dp, Buscadores buscador) {

        this.tab = tab;
        this.dp = dp;
        this.buscador = buscador;
        this.m = Model.getInstance();
        initComponents();
        iniciar();

    }

    public void iniciar() {

        funcion = interfaces.Constantes.ESTADO_EDICION;
        setTitle("Datos de la Empresa Propietaria de la licencia del Software");
        this.setLocation(tab.getWidth() / 2 - this.getWidth() / 2, tab.getHeight() / 2 - this.getHeight() / 2);

        ciudad.removeAllItems();
        try {

            List<Ciudad> listaCiudades = (List<Ciudad>) Model.getInstance().obtenerListado("obtenerCiudades", "%");

            for (Ciudad obj : listaCiudades) {
                ciudad.addItem(obj);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        nit.requestFocus();
        try {
            em = (Empresa) m.obtenerRegistro("obtenerEmpresaActual");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (em != null) {
            llenar();
        } else {
            em = new Empresa();
        }
    }

    public void llenar() {
        nit.setText((em.getNit() + "").replaceAll(".00", ""));
        nombre.setText(em.getNombre());
        direccion.setText(em.getDireccion());
        telefono.setText(em.getTelefono());
        mail.setText(em.getEmail());
        ciudad.setSelectedItem(em.getCiudad());
        licencia.setText(em.getLicencia());
        propietario.setText(em.getPropietario());
        funcion = interfaces.Constantes.ESTADO_EDICION;
    }



    public boolean validar() {
        if (nit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el NIT por favor");
            nit.requestFocus();
            return false;
        } else if (nombre.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el NOMBRE por favor");
            nombre.requestFocus();
            return false;
        } else if (direccion.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la DIRECCIÓN por favor");
            direccion.requestFocus();
            return false;
        } else if (telefono.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el TELÉFONO por favor");
            telefono.requestFocus();
            return false;
        } else if (mail.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la E-MAIL por favor");
            mail.requestFocus();
            return false;
        } else if (!Validaciones.esEmail(mail.getText())) {
            JOptionPane.showMessageDialog(null, "Digite un E-MAIL válido por favor");
            mail.requestFocus();
            return false;
        } else if (licencia.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la LICENCIA por favor");
            licencia.requestFocus();
            return false;
        } else if (propietario.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el PROPIETARIO por favor");
            propietario.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        licencia = new javax.swing.JTextField();
        guardar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        propietario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ciudad = new javax.swing.JComboBox();

        setResizable(true);
        setTitle("Empresa");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });

        jLabel3.setText("Nombre");

        nit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nitActionPerformed(evt);
            }
        });
        nit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nitKeyTyped(evt);
            }
        });

        jLabel2.setText("Nit/CC");

        jLabel4.setText("Dirección");

        direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionActionPerformed(evt);
            }
        });
        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direccionKeyTyped(evt);
            }
        });

        jLabel5.setText("Teléfono");

        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });
        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });

        mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailActionPerformed(evt);
            }
        });
        mail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mailKeyTyped(evt);
            }
        });

        jLabel7.setText("Ciudad");

        jLabel6.setText("E-Mail");

        jLabel8.setText("Licencia");

        licencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                licenciaActionPerformed(evt);
            }
        });
        licencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                licenciaKeyTyped(evt);
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

        propietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propietarioActionPerformed(evt);
            }
        });
        propietario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                propietarioKeyTyped(evt);
            }
        });

        jLabel9.setText("Propietario");

        ciudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(9, 9, 9)
                        .addComponent(licencia, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(propietario, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nit, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                            .addComponent(direccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mail, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(propietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(licencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guardar)
                            .addComponent(salir)))
                    .addComponent(jLabel8))
                .addGap(21, 21, 21))
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

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        tab.remove(dp);
}//GEN-LAST:event_salirActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        if (validar()) {
            em.setNit(Long.parseLong(nit.getText()));
            em.setNombre(nombre.getText());
            em.setDireccion(direccion.getText());
            em.setTelefono(telefono.getText());
            em.setEmail(mail.getText());
            em.setCiudad((Ciudad) ciudad.getSelectedItem());
            em.setLicencia(licencia.getText());
            em.setPropietario(propietario.getText());
            em.setEstado(interfaces.Constantes.ESTADO_EMPRESA_ACTIVO + "");

            try {
                if (funcion == interfaces.Constantes.ESTADO_CREACION) {
                    m.insertarRegistro("insertarEmpresa", em);
                } else {
                    m.actualizarRegistro("actualizarEmpresa", em);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            this.dispose();
            tab.remove(dp);
            buscador.buscar();
            JOptionPane.showMessageDialog(null, "Datos guardados con éxito");
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void nitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nitKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar()) || nit.getText().length() >= 17) {
            evt.consume();
        }
    }//GEN-LAST:event_nitKeyTyped

    private void nitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nitActionPerformed
        // TODO add your handling code here:
        nombre.requestFocus();
    }//GEN-LAST:event_nitActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
        propietario.requestFocus();
    }//GEN-LAST:event_nombreActionPerformed

    private void direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionActionPerformed
        // TODO add your handling code here:
        telefono.requestFocus();
    }//GEN-LAST:event_direccionActionPerformed

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        // TODO add your handling code here:
        mail.requestFocus();
    }//GEN-LAST:event_telefonoActionPerformed

    private void mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailActionPerformed
        // TODO add your handling code here:
        ciudad.requestFocus();
    }//GEN-LAST:event_mailActionPerformed

    private void licenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_licenciaActionPerformed
        // TODO add your handling code here:
        guardar.requestFocus();
    }//GEN-LAST:event_licenciaActionPerformed

    private void propietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propietarioActionPerformed
        // TODO add your handling code here:
        direccion.requestFocus();
}//GEN-LAST:event_propietarioActionPerformed

    private void licenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_licenciaKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar()) || licencia.getText().length() >= 40) {
            evt.consume();
        }
    }//GEN-LAST:event_licenciaKeyTyped

    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar()) || telefono.getText().length() >= 12) {
            evt.consume();
        }
    }//GEN-LAST:event_telefonoKeyTyped

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        // TODO add your handling code here:
         if ( nombre.getText().length() >= 45) {
            evt.consume();
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void propietarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_propietarioKeyTyped
        // TODO add your handling code here:
        if ( propietario.getText().length() >= 45) {
            evt.consume();
        }
    }//GEN-LAST:event_propietarioKeyTyped

    private void mailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mailKeyTyped
        // TODO add your handling code here:
        if ( mail.getText().length() >= 45) {
            evt.consume();
        }
    }//GEN-LAST:event_mailKeyTyped

    private void direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyTyped
        // TODO add your handling code here:
       if ( direccion.getText().length() >= 45) {
            evt.consume();
        }
    }//GEN-LAST:event_direccionKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ciudad;
    private javax.swing.JTextField direccion;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField licencia;
    private javax.swing.JTextField mail;
    private javax.swing.JTextField nit;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField propietario;
    private javax.swing.JButton salir;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables
}
