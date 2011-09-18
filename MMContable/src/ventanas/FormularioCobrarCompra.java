package ventanas;

import beans.Caja;
import java.awt.Frame;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import beans.Documento;
import beans.TipoPago;
import db.Model;
import interfaces.Constantes;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import utilidades.Calendario;
import utilidades.FormatoNumeros;

public class FormularioCobrarCompra extends javax.swing.JDialog {

    private Documento documento;
    private boolean sw = false;
    private String totalF;
    private SimpleDateFormat formatoFecha;
    Caja cajaDia;

    public FormularioCobrarCompra(Frame padre, boolean modal, Documento documento, String totalF) {
        super(padre, modal);
        this.totalF = totalF;
        this.documento = documento;

        initComponents();
        this.tot.setText("$" + totalF);
        FormatoNumeros fn = new utilidades.FormatoNumeros(vabonar);
        vabonar.addKeyListener(fn);
        vabonar.addFocusListener(fn);
        formatoFecha = new SimpleDateFormat("yyyy/mm/dd");

        this.setLocationRelativeTo(null);
        guardar.requestFocus();
    }

    public static boolean cobrar(java.awt.Frame parent, Documento d, String totall) {
        FormularioCobrarCompra formularioCobrarCompra = new FormularioCobrarCompra(parent, true, d, totall);
        formularioCobrarCompra.setVisible(true);
        return formularioCobrarCompra.sw;
    }

    public boolean validar() {
        try {
             cajaDia = (Caja) Model.getInstance().obtenerRegistro("obtenerCajaDia");
        } catch (Exception ex) {
            Logger.getLogger(FormularioCobrarCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cajaDia==null){
            JOptionPane.showMessageDialog(null, "No se ha iniciado la Caja del Día");
             return false;
        }

        if (credito.isSelected()) {
            try {
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                if (fechavencimiento.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Seleccione una Fecha de Vencimiento");
                    buscafecha.requestFocus();
                    return false;
                } else if (formatoDelTexto.parse(this.fechavencimiento.getText()).getTime() <= new Date().getTime()) {
                    JOptionPane.showMessageDialog(null, "Seleccione una Fecha de Vencimiento mayor al día actual");
                    buscafecha.requestFocus();
                    return false;
                } else if (!this.vabonar.getText().trim().equals("")) {
                    if (Double.parseDouble(vabonar.getText().replaceAll(",", "")) >= documento.getTotal().doubleValue()) {
                        int confirmado = JOptionPane.showConfirmDialog(this, "La cantidad Abonada cubre  el TOTAL de la COMPRA, Desea cambiar a EFECTIVO y pagar el valor digitado?", "¿Cambiar a EFECTIVO?", JOptionPane.YES_NO_OPTION);
                        if (JOptionPane.OK_OPTION == confirmado) {
                            this.efectivo.setSelected(true);
                            return true;
                        } else {
                            vabonar.requestFocus();
                            return false;
                        }
                    } else if (Double.parseDouble(vabonar.getText().replaceAll(",", "")) > cajaDia.getSaldoactual().doubleValue()) {
                        JOptionPane.showMessageDialog(this, "El saldo actual en caja ($" + FormatoNumeros.formatear(cajaDia.getSaldoactual() + "") + ") no cubre la cantidad que desea abonar", "Inconveniente", JOptionPane.ERROR_MESSAGE);
                        vabonar.requestFocus();
                        return false;
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(FormularioCobrarCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(documento.getTotal().doubleValue()>cajaDia.getSaldoactual().doubleValue() && !pagada.isSelected()){
            JOptionPane.showMessageDialog(this,"El saldo actual en caja ($"+FormatoNumeros.formatear(cajaDia.getSaldoactual()+"")+") no cubre la cantidad que desea Pagar","Inconveniente",JOptionPane.ERROR_MESSAGE);
                      
                       return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        credito = new javax.swing.JRadioButton();
        efectivo = new javax.swing.JRadioButton();
        pagada = new javax.swing.JRadioButton();
        salir = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tot = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        vabonar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fechavencimiento = new javax.swing.JTextField();
        buscafecha = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        scxc = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pago de Esta Compra");

        jPanel1.setBackground(new java.awt.Color(153, 205, 255));

        jPanel2.setBackground(new java.awt.Color(212, 233, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Forma de Pago"));

        credito.setBackground(new java.awt.Color(212, 233, 255));
        buttonGroup1.add(credito);
        credito.setText("Crédito");
        credito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                creditoMouseClicked(evt);
            }
        });
        credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditoActionPerformed(evt);
            }
        });

        efectivo.setBackground(new java.awt.Color(212, 233, 255));
        buttonGroup1.add(efectivo);
        efectivo.setSelected(true);
        efectivo.setText("Efectivo");
        efectivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                efectivoMouseClicked(evt);
            }
        });
        efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efectivoActionPerformed(evt);
            }
        });

        pagada.setBackground(new java.awt.Color(212, 233, 255));
        buttonGroup1.add(pagada);
        pagada.setText("Pagada");
        pagada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pagadaMouseClicked(evt);
            }
        });
        pagada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(credito)
                .addGap(10, 10, 10)
                .addComponent(efectivo)
                .addGap(18, 18, 18)
                .addComponent(pagada)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(credito)
                    .addComponent(efectivo)
                    .addComponent(pagada))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("TOTAL A PAGAR");

        tot.setFont(new java.awt.Font("Tahoma", 1, 24));
        tot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tot.setText("$");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setText("Valor Abonar  $");

        vabonar.setEditable(false);
        vabonar.setText("0.00");
        vabonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vabonarActionPerformed(evt);
            }
        });
        vabonar.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                vabonarInputMethodTextChanged(evt);
            }
        });
        vabonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vabonarKeyReleased(evt);
            }
        });

        jLabel4.setText("Fecha Vencimiento:");

        fechavencimiento.setEditable(false);

        buscafecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Calendar-16x16.png"))); // NOI18N
        buscafecha.setEnabled(false);
        buscafecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscafechaActionPerformed(evt);
            }
        });

        jLabel5.setText("Saldo a CxC");

        scxc.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(guardar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(fechavencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buscafecha))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(vabonar, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scxc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(fechavencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vabonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(scxc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buscafecha))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tot)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar)
                    .addComponent(salir))
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

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
}//GEN-LAST:event_salirActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {
            if (validar()) {

                if (!efectivo.isSelected() && !pagada.isSelected()) {
                    documento.setFechavencimiento(formatoFecha.parse(fechavencimiento.getText().replaceAll("-", "/")));
                }
                if (pagada.isSelected()) {
                    documento.setTipopago(new TipoPago(Constantes.TIPO_PAGO_PAGADO, null));
                    documento.setEstado(Constantes.ESTADO_DOCUMENTO_PAGADO);
                    documento.setTotalpagado(documento.getTotal());
                } else if (credito.isSelected()) {
                    documento.setTotalpagado(new BigDecimal(vabonar.getText().replaceAll(",", "")));
                    documento.setTipopago(new TipoPago(Constantes.TIPO_PAGO_CREDITO, null));
                    documento.setEstado(Constantes.ESTADO_DOCUMENTO_DEBE);
                } else if (efectivo.isSelected()) {

                    documento.setTotalpagado(documento.getTotal());
                    documento.setTipopago(new TipoPago(Constantes.TIPO_PAGO_DEBITO, null));
                    documento.setEstado(Constantes.ESTADO_DOCUMENTO_PAGADO);
                }
                sw = true;
                this.dispose();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void buscafechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscafechaActionPerformed
        fechavencimiento.setText(new Calendario((Frame) this.getParent(), true).getFecha());
        if (credito.isSelected()) {
            vabonar.requestFocus();
        } else {
            guardar.requestFocus();
        }
}//GEN-LAST:event_buscafechaActionPerformed

    private void vabonarInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_vabonarInputMethodTextChanged
        if (!vabonar.getText().trim().equals("")) {
            double t = Double.parseDouble(totalF.replaceAll(",", ""));
            double dev = t - Double.parseDouble(vabonar.getText().replaceAll(",", ""));
            scxc.setText(utilidades.FormatoNumeros.formatear(dev + ""));
        }
    }//GEN-LAST:event_vabonarInputMethodTextChanged

    private void vabonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vabonarActionPerformed
        guardar.requestFocus();
    }//GEN-LAST:event_vabonarActionPerformed

    private void creditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_creditoMouseClicked
        vabonar.setEnabled(true);
        vabonar.setEditable(true);
        vabonar.setText("0");
        scxc.setText(tot.getText());
        buscafecha.setEnabled(true);
        buscafecha.requestFocus();
    }//GEN-LAST:event_creditoMouseClicked

    private void efectivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_efectivoMouseClicked
        vabonar.setEnabled(false);
        buscafecha.setEnabled(false);

    }//GEN-LAST:event_efectivoMouseClicked

    private void pagadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pagadaMouseClicked
        vabonar.setEnabled(false);
        buscafecha.setEnabled(false);
    }//GEN-LAST:event_pagadaMouseClicked

    private void vabonarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vabonarKeyReleased
        // TODO add your handling code here:
        if (!vabonar.getText().trim().equals("")) {
            double t = Double.parseDouble(totalF.replaceAll(",", ""));
            double dev = t - Double.parseDouble(vabonar.getText().replaceAll(",", ""));
            if(dev>0)
                scxc.setText(utilidades.FormatoNumeros.formatear(dev + ""));
            else
                scxc.setText("0.0");
        }else{
           scxc.setText(vabonar.getText());
        }
    }//GEN-LAST:event_vabonarKeyReleased

    private void efectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_efectivoActionPerformed

    private void creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditoActionPerformed

    private void pagadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pagadaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscafecha;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton credito;
    private javax.swing.JRadioButton efectivo;
    private javax.swing.JTextField fechavencimiento;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton pagada;
    private javax.swing.JButton salir;
    private javax.swing.JTextField scxc;
    private javax.swing.JLabel tot;
    private javax.swing.JTextField vabonar;
    // End of variables declaration//GEN-END:variables
}
