package ventanas;

import beans.FacturaEmpresa;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import db.Model;
import beans.FacturaEmpresa;
import beans.PapelImpresion;
import java.util.List;

public class Formulario_Edita_Factura extends javax.swing.JInternalFrame {

    JTabbedPane tab;
    JDesktopPane dp;
    FacturaEmpresa fe;
    int funcion = 0;
    Model m;

    public Formulario_Edita_Factura(JTabbedPane tab, JDesktopPane dp) {
        this.tab = tab;
        this.dp = dp;
        this.m = Model.getInstance();
        initComponents();
        iniciar();
        this.setLocation(tab.getWidth() / 2 - this.getWidth() / 2, tab.getHeight() / 2 - this.getHeight() / 2);
        try {
            fe = (FacturaEmpresa) m.obtenerRegistro("obtenerFacturaEmpresaActual");
        } catch (Exception ex) {
            Logger.getLogger(Formulario_Edita_Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fe != null) {
            funcion = interfaces.Constantes.ESTADO_EDICION;
            llenar();
        } else {
            fe = new FacturaEmpresa();
            funcion = interfaces.Constantes.ESTADO_CREACION;
        }
        encabezado1.requestFocus();
    }

    public void iniciar() {
        papel.removeAllItems();
        try {
            List<PapelImpresion> listaPapelImpresion = (List<PapelImpresion>) m.obtenerListado("obtenerPapelesImpresiones");
            for(PapelImpresion pi:listaPapelImpresion){
                papel.addItem(pi);
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    public void llenar() {

        encabezado1.setText(fe.getEncabezado1());
        encabezado2.setText(fe.getEncabezado2());
        encabezado3.setText(fe.getEncabezado3());
        encabezado4.setText(fe.getEncabezado4());
        encabezado5.setText(fe.getEncabezado5());
        pie1.setText(fe.getPie1());
        pie2.setText(fe.getPie2());
        numero.setText(fe.getNumeroActual() + "");
        papel.setSelectedItem(fe.getPapel());


    }

    public boolean validar() {
        if (encabezado1.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el Encabezado 1 por favor");
            encabezado1.requestFocus();
            return false;
        } else if (encabezado2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el Encabezado 2 por favor");
            encabezado2.requestFocus();
            return false;
        } else if (encabezado3.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el Encabezado 3 por favor");
            encabezado3.requestFocus();
            return false;
        } else if (encabezado4.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el Encabezado 4 por favor");
            encabezado4.requestFocus();
            return false;
        } else if (encabezado5.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el Encabezado 5 por favor");
            encabezado5.requestFocus();
            return false;
        } else if (pie1.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el Pie de página 1 por favor");
            pie1.requestFocus();
            return false;
        } else if (pie2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el Pie de página 2 por favor");
            pie2.requestFocus();
            return false;
        } else if (numero.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el ultimo NÚMERO por favor");
            numero.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        pie1 = new javax.swing.JTextField();
        pie2 = new javax.swing.JTextField();
        encabezado4 = new javax.swing.JTextField();
        encabezado2 = new javax.swing.JTextField();
        encabezado1 = new javax.swing.JTextField();
        encabezado3 = new javax.swing.JTextField();
        salir = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        encabezado5 = new javax.swing.JTextField();
        papel = new javax.swing.JComboBox();

        setTitle("Personalizar Factura de Ventas");

        jPanel1.setBackground(new java.awt.Color(153, 205, 255));

        area.setBackground(new java.awt.Color(237, 234, 218));
        area.setColumns(20);
        area.setEditable(false);
        area.setRows(5);
        area.setText("\n07 dic 2009\n02:35 pm    No.00001\nCant Articulos Valor\n10   Sillas    3000\n 2   mesas     1000\n\nNo Articulos:12\ntotal          4000\nefectivo       5000\ncambio         1000");
        jScrollPane1.setViewportView(area);

        pie1.setText("Gracias por su compra");
        pie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pie1ActionPerformed(evt);
            }
        });
        pie1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pie1KeyTyped(evt);
            }
        });

        pie2.setText("M&M Contable 3176671655");
        pie2.setToolTipText("");
        pie2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pie2KeyTyped(evt);
            }
        });

        encabezado4.setText("Nuevo Bosque, Cartagena");
        encabezado4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                encabezado4KeyTyped(evt);
            }
        });

        encabezado2.setText("1143336126");
        encabezado2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                encabezado2KeyTyped(evt);
            }
        });

        encabezado1.setText("M&M Contable ");
        encabezado1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                encabezado1KeyTyped(evt);
            }
        });

        encabezado3.setText("Factura de Venta");
        encabezado3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                encabezado3KeyTyped(evt);
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

        jLabel1.setText("¿Para imprimir la factura utiliza?");

        numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroKeyTyped(evt);
            }
        });

        jLabel2.setText("Ultimo número de factura");

        encabezado5.setText("Tel: 6510405");
        encabezado5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                encabezado5KeyTyped(evt);
            }
        });

        papel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ticket", "Media Carta" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pie2)
                    .addComponent(pie1)
                    .addComponent(encabezado1)
                    .addComponent(encabezado2)
                    .addComponent(encabezado3)
                    .addComponent(encabezado4)
                    .addComponent(encabezado5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(papel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(encabezado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encabezado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encabezado3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encabezado4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(papel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(encabezado5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pie2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guardar)
                            .addComponent(salir))))
                .addGap(28, 28, 28))
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

    private void pie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pie1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pie1ActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        if (validar()) {
            fe.setEncabezado1(encabezado1.getText());
            fe.setEncabezado2(encabezado2.getText());
            fe.setEncabezado3(encabezado3.getText());
            fe.setEncabezado4(encabezado4.getText());
            fe.setEncabezado5(encabezado5.getText());
            fe.setPie1(pie1.getText());
            fe.setPie2(pie2.getText());
            fe.setNumeroActual(Integer.parseInt(numero.getText()));
            fe.setPapel((PapelImpresion) papel.getSelectedItem());
            fe.setEmpresa(m.getUsuario().getEmpresa());

            try {
                if (funcion == interfaces.Constantes.ESTADO_CREACION) {
                    m.insertarRegistro("insertarFacturaEmpresa", fe);
                } else {
                    m.insertarRegistro("actualizarFacturaEmpresa", fe);
                }
            } catch (Exception ex) {
                Logger.getLogger(Formulario_Edita_Factura.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.dispose();
            tab.remove(dp);
            JOptionPane.showMessageDialog(null, "Datos guardados con éxito");
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void numeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar()) && numero.getText().length()>=20) {
            evt.consume();
        }
    }//GEN-LAST:event_numeroKeyTyped

    private void encabezado1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_encabezado1KeyTyped
        // TODO add your handling code here:
         if (encabezado1.getText().length()>=30) {
            evt.consume();
        }
    }//GEN-LAST:event_encabezado1KeyTyped

    private void encabezado2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_encabezado2KeyTyped
        // TODO add your handling code here:
        if (encabezado2.getText().length()>=30) {
            evt.consume();
        }
    }//GEN-LAST:event_encabezado2KeyTyped

    private void encabezado3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_encabezado3KeyTyped
        // TODO add your handling code here:
       if (encabezado3.getText().length()>=30) {
            evt.consume();
        }
    }//GEN-LAST:event_encabezado3KeyTyped

    private void encabezado4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_encabezado4KeyTyped
        // TODO add your handling code here:
        if (encabezado4.getText().length()>=30) {
            evt.consume();
        }
    }//GEN-LAST:event_encabezado4KeyTyped

    private void encabezado5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_encabezado5KeyTyped
        // TODO add your handling code here:
      if (encabezado5.getText().length()>=30) {
            evt.consume();
        }
    }//GEN-LAST:event_encabezado5KeyTyped

    private void pie1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pie1KeyTyped
        // TODO add your handling code here:
        if (pie1.getText().length()>=30) {
            evt.consume();
        }
    }//GEN-LAST:event_pie1KeyTyped

    private void pie2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pie2KeyTyped
        // TODO add your handling code here:
       if (pie1.getText().length()>=30) {
            evt.consume();
        }
    }//GEN-LAST:event_pie2KeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField encabezado1;
    private javax.swing.JTextField encabezado2;
    private javax.swing.JTextField encabezado3;
    private javax.swing.JTextField encabezado4;
    private javax.swing.JTextField encabezado5;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField numero;
    private javax.swing.JComboBox papel;
    private javax.swing.JTextField pie1;
    private javax.swing.JTextField pie2;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
