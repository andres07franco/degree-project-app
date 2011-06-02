package ventanas;

import beans.*;
import db.Model;
import interfaces.Constantes;
import java.awt.Frame;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utilidades.Calendario;

public class Formulario_Cobrar_Ventas extends javax.swing.JDialog {

     Documento d;     
     public static final int COBRADO = 1;
     public static final int CANCELADO = 2;
     public static int estado  = CANCELADO;

    public Formulario_Cobrar_Ventas(java.awt.Frame parent, Documento d) {
        super(parent, true);
        this.d = d;
        
        initComponents();
        total.setText("$ " + utilidades.FormatoNumeros.formatear(d.getTotal().toString()));
        tab.remove(1);

        utilidades.FormatoNumeros fn = new utilidades.FormatoNumeros(pagacon);
        pagacon.addKeyListener(fn);
        pagacon.addFocusListener(fn);

        fn = new utilidades.FormatoNumeros(devuelve);
        devuelve.addKeyListener(fn);
        devuelve.addFocusListener(fn);

        fn = new utilidades.FormatoNumeros(vabonar);
        vabonar.addKeyListener(fn);
        vabonar.addFocusListener(fn);

        pagacon.setText(utilidades.FormatoNumeros.formatear(d.getTotal().toString()));
       this.setLocationRelativeTo(null);
       pagacon.requestFocus();
        if(pagacon.getText().length()>0){
                    this.pagacon.setSelectionStart(0);
                    this.pagacon.setSelectionEnd(pagacon.getText().length()-1);
                }
    }


    public static int cobrar(java.awt.Frame parent, Documento d) {
        Formulario_Cobrar_Ventas fcc = new Formulario_Cobrar_Ventas(parent, d);
        fcc.setVisible(true);
        return estado;
    }


    public boolean validar() {
        if (credito.isSelected()) {
            if (fechavencimiento.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una Fecha de Vencimiento");
                buscafecha.requestFocus();
                return false;
            } else  if(!this.vabonar.getText().trim().equals("")){
                
                if (Double.parseDouble(vabonar.getText().replaceAll(",", "")) >= d.getTotal().doubleValue()) {
                    
                   int confirmado = JOptionPane.showConfirmDialog(this,"La cantidad Abonada es cubre  el TOTAL de la Venta, Desea cambiar a DE CONTADO y pagar el valor digitado?","¿Cambiar a DE CONTADO?",JOptionPane.YES_NO_OPTION);

                    if (JOptionPane.OK_OPTION == confirmado){
                       tab.remove(0);
                       tab.add("De Contado", this.panelca);
                       this.efectivo.setSelected(true);
                       this.pagacon.setText(vabonar.getText());
                       calcularCambio();
                       this.guardar.requestFocus();
                       return false;
                    } else {
                       vabonar.requestFocus();
                       return false;
                    }
                }
   
           }          

        } else if(efectivo.isSelected()){

             if (pagacon.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite el Valor a Pagar");
                pagacon.requestFocus();
                return false;
            } else if (Double.parseDouble(pagacon.getText().replaceAll(",", "")) < d.getTotal().doubleValue()) {
               int confirmado = JOptionPane.showConfirmDialog(this,"La cantidad a pagar es menor que el TOTAL de la Venta, Desea cambiar a CREDITO y abonar el valor digitado?","¿Cambiar a CREDITO?",JOptionPane.YES_NO_OPTION);

                if (JOptionPane.OK_OPTION == confirmado){
                   tab.remove(0);
                   tab.add("Credito", this.panelca);
                   this.credito.setSelected(true);
                   this.vabonar.setText(pagacon.getText());
                   calcularCxC();
                   this.guardar.requestFocus();
                   return false;
                } else {                   
                   pagacon.requestFocus();
                   return false;
                }
            }
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
        jLabel1 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tab = new javax.swing.JTabbedPane();
        panelefectivo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pagacon = new javax.swing.JTextField();
        etiquetaCambio = new javax.swing.JLabel();
        devuelve = new javax.swing.JTextField();
        panelca = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fechavencimiento = new javax.swing.JTextField();
        buscafecha = new javax.swing.JButton();
        vabonar = new javax.swing.JTextField();
        scxc = new javax.swing.JTextField();
        salir = new javax.swing.JButton();
        guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pago de Esta Compra");

        jPanel1.setBackground(new java.awt.Color(153, 205, 255));

        jPanel2.setBackground(new java.awt.Color(212, 233, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Forma de Pago"));

        credito.setBackground(new java.awt.Color(212, 233, 255));
        buttonGroup1.add(credito);
        credito.setText("Credito");
        credito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                creditoMouseClicked(evt);
            }
        });
        credito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                creditoItemStateChanged(evt);
            }
        });

        efectivo.setBackground(new java.awt.Color(212, 233, 255));
        buttonGroup1.add(efectivo);
        efectivo.setSelected(true);
        efectivo.setText("De Contado");
        efectivo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                efectivoItemStateChanged(evt);
            }
        });
        efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efectivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(efectivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(credito)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(efectivo)
                    .addComponent(credito))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("TOTAL A PAGAR");

        total.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("$");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        panelefectivo.setBackground(new java.awt.Color(212, 233, 255));

        jLabel6.setText("Efectivo");

        pagacon.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pagacon.setText("0.00");
        pagacon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagaconActionPerformed(evt);
            }
        });
        pagacon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pagaconKeyReleased(evt);
            }
        });

        etiquetaCambio.setText("Cambio");

        devuelve.setEditable(false);
        devuelve.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        devuelve.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        devuelve.setText("0.00");

        javax.swing.GroupLayout panelefectivoLayout = new javax.swing.GroupLayout(panelefectivo);
        panelefectivo.setLayout(panelefectivoLayout);
        panelefectivoLayout.setHorizontalGroup(
            panelefectivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelefectivoLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(panelefectivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelefectivoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pagacon, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelefectivoLayout.createSequentialGroup()
                        .addComponent(etiquetaCambio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(devuelve, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                .addGap(131, 131, 131))
        );
        panelefectivoLayout.setVerticalGroup(
            panelefectivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelefectivoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelefectivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pagacon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelefectivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaCambio)
                    .addComponent(devuelve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        tab.addTab("De Contado", panelefectivo);

        panelca.setBackground(new java.awt.Color(212, 233, 255));

        jLabel4.setText("Fecha Vencimiento");

        jLabel3.setText("Valor Abonar  $");

        jLabel5.setText("Saldo a CxC");

        fechavencimiento.setEditable(false);

        buscafecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Calendar-16x16.png"))); // NOI18N
        buscafecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscafechaActionPerformed(evt);
            }
        });

        vabonar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        vabonar.setText("0.00");
        vabonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vabonarActionPerformed(evt);
            }
        });
        vabonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vabonarKeyReleased(evt);
            }
        });

        scxc.setEditable(false);
        scxc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        scxc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scxcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelcaLayout = new javax.swing.GroupLayout(panelca);
        panelca.setLayout(panelcaLayout);
        panelcaLayout.setHorizontalGroup(
            panelcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcaLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(panelcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelcaLayout.createSequentialGroup()
                        .addComponent(fechavencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscafecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(vabonar, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(scxc))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        panelcaLayout.setVerticalGroup(
            panelcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fechavencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buscafecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vabonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scxc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tab.addTab("Credito", panelca);

        salir.setBackground(new java.awt.Color(0, 153, 255));
        salir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        salir.setForeground(new java.awt.Color(0, 51, 153));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bulletCritical.png"))); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        guardar.setBackground(new java.awt.Color(0, 153, 255));
        guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(total)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salir)
                    .addComponent(guardar))
                .addContainerGap(18, Short.MAX_VALUE))
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
            guardar();
        } catch (Exception ex) {
            Logger.getLogger(Formulario_Cobrar_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void buscafechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscafechaActionPerformed
        fechavencimiento.setText(new Calendario((Frame) this.getParent(), true).getFecha());
        vabonar.requestFocus();
}//GEN-LAST:event_buscafechaActionPerformed

    private void scxcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scxcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scxcActionPerformed

    private void vabonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vabonarActionPerformed
        try {
            // TODO add your handling code here:
            guardar();
        } catch (Exception ex) {
            Logger.getLogger(Formulario_Cobrar_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_vabonarActionPerformed

    private void efectivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_efectivoItemStateChanged
        // TODO add your handling code here:
        tab.remove(0);
        tab.add("De Contado", panelefectivo);
        pagacon.requestFocus();
        if(pagacon.getText().length()>0){
                    this.pagacon.setSelectionStart(0);
                    this.pagacon.setSelectionEnd(pagacon.getText().length()-1);
       }
    }//GEN-LAST:event_efectivoItemStateChanged

    private void creditoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_creditoItemStateChanged
        // TODO add your handling code here:
        tab.remove(0);
        tab.add("Credito", this.panelca);
        this.vabonar.requestFocus();
        if(vabonar.getText().length()>0){
                    this.vabonar.setSelectionStart(0);
                    this.vabonar.setSelectionEnd(vabonar.getText().length()-1);
       }
    }//GEN-LAST:event_creditoItemStateChanged

    private void pagaconKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pagaconKeyReleased
        // TODO add your handling code here:
        calcularCambio();
    }//GEN-LAST:event_pagaconKeyReleased

    public void calcularCambio(){
         if (!pagacon.getText().trim().equals("")) {
            BigDecimal dev = new BigDecimal(pagacon.getText().replaceAll(",", ""));
            dev = dev.subtract(d.getTotal());
            if(dev.compareTo(BigDecimal.ZERO) >= 0){
                etiquetaCambio.setText("Cambio");
                devuelve.setText(utilidades.FormatoNumeros.formatear(dev+ ""));
            } else {
                etiquetaCambio.setText("Faltan");
                devuelve.setText(utilidades.FormatoNumeros.formatear(dev.multiply(new BigDecimal("-1"))+ ""));
            }
        }       
    }

    private void pagaconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagaconActionPerformed
        try {
            // TODO add your handling code here:
            guardar();
        } catch (Exception ex) {
            Logger.getLogger(Formulario_Cobrar_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pagaconActionPerformed

    private void vabonarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vabonarKeyReleased
        // TODO add your handling code here:
            calcularCxC();
    }//GEN-LAST:event_vabonarKeyReleased

    public void calcularCxC(){
          if (!vabonar.getText().trim().equals("")) {
            BigDecimal tot = d.getTotal();
            BigDecimal debe = tot.subtract(new BigDecimal(this.vabonar.getText().replaceAll(",","")));
            scxc.setText(utilidades.FormatoNumeros.formatear(debe.toString()));
        }
    }
    
    private void creditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_creditoMouseClicked
        // TODO add your handling code here:
        if (d.getTercero().getId() != Constantes.TERCERO_POR_DEFECTO) {
            tab.remove(0);
            fechavencimiento.requestFocus();
            tab.add("Credito", panelca);
          
        } else {
            efectivo.setSelected(true);
            JOptionPane.showMessageDialog(null, "Seleccione un CLIENTE Distinto a CLIENTE por mostrador");
        }
    }//GEN-LAST:event_creditoMouseClicked

    private void efectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_efectivoActionPerformed

    public void guardar() throws Exception {
        if (validar()) {
            if (credito.isSelected()) {
                d.setFechavencimiento(new java.util.Date(fechavencimiento.getText().replaceAll("-", "/")));
                d.setTipopago((TipoPago) Model.getInstance().obtenerRegistro("obtenerTipoPago", Constantes.TIPO_PAGO_CREDITO));
                d.setEstado(Constantes.ESTADO_DOCUMENTO_DEBE);
                  if(!this.vabonar.getText().trim().equals("")){
                   d.setTotalpagado(new BigDecimal(vabonar.getText().replaceAll(",", "")));
               }else
                   d.setTotalpagado(BigDecimal.ZERO);
            } else
            if (efectivo.isSelected()) {
               d.setFechavencimiento(new Date());
               d.setTipopago((TipoPago) Model.getInstance().obtenerRegistro("obtenerTipoPago", Constantes.TIPO_PAGO_DEBITO));
               d.setEstado(Constantes.ESTADO_DOCUMENTO_PAGADO);
               d.setTotalpagado(d.getTotal());
            }
            estado  = COBRADO;
            this.dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscafecha;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton credito;
    private javax.swing.JTextField devuelve;
    private javax.swing.JRadioButton efectivo;
    private javax.swing.JLabel etiquetaCambio;
    private javax.swing.JTextField fechavencimiento;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField pagacon;
    private javax.swing.JPanel panelca;
    private javax.swing.JPanel panelefectivo;
    private javax.swing.JButton salir;
    private javax.swing.JTextField scxc;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JLabel total;
    private javax.swing.JTextField vabonar;
    // End of variables declaration//GEN-END:variables
}
