package ventanas;

import java.util.Date;
import javax.swing.JOptionPane;
import beans.Caja;
import db.Model;
import interfaces.Constantes;
import java.awt.Frame;
import java.math.BigDecimal;
import javax.swing.JDialog;
import utilidades.FormatoNumeros;

public class FormularioInicioCaja extends JDialog {

    Caja cajaDia;
    int funcion = Constantes.SE_INICIO_CAJA;
    Model modelo;

    public FormularioInicioCaja(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.modelo = Model.getInstance();

        saldo.requestFocus();
        try {
            cajaDia = (Caja) modelo.obtenerRegistro("obtenerCajaDia");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (cajaDia == null) {
            funcion = Constantes.NO_SE_INICIO_CAJA;
            et.setText("¿con qué cantidad Desea Iniciar?");
        } else {
            saldo.setText(FormatoNumeros.formatear(cajaDia.getSaldoactual() + ""));
            saldo.setEditable(false);
            guardar.setText("Continuar");
        }

        this.setLocationRelativeTo(parent);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        salir = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        saldo = new javax.swing.JTextField();
        et = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inicio de Caja");

        jPanel1.setBackground(new java.awt.Color(153, 205, 255));

        salir.setBackground(new java.awt.Color(0, 153, 255));
        salir.setFont(new java.awt.Font("Tahoma", 1, 11));
        salir.setForeground(new java.awt.Color(0, 51, 204));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bulletCritical.png"))); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        guardar.setBackground(new java.awt.Color(0, 153, 255));
        guardar.setFont(new java.awt.Font("Tahoma", 1, 11));
        guardar.setForeground(new java.awt.Color(0, 51, 204));
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        saldo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        saldo.setText("0");
        saldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldoActionPerformed(evt);
            }
        });
        saldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                saldoKeyTyped(evt);
            }
        });

        et.setFont(new java.awt.Font("Tahoma", 1, 14));
        et.setForeground(new java.awt.Color(0, 51, 153));
        et.setText("Cantidad actual de dinero en caja");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(et)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(et)
                .addGap(18, 18, 18)
                .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar)
                    .addComponent(salir))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        if (funcion == 1) {
            System.exit(0);
        }
        this.dispose();
}//GEN-LAST:event_salirActionPerformed

    public boolean validar() {
        if (saldo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite un Saldo");
            saldo.requestFocus();
            return false;
        } else if (Double.parseDouble(saldo.getText().replaceAll(",", "")) <= 0) {
            JOptionPane.showMessageDialog(null, "Digite un Saldo");
            saldo.requestFocus();
            return false;
        }
        return true;
    }

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        guardar();
}//GEN-LAST:event_guardarActionPerformed

    public void guardar() {
        if (funcion == Constantes.NO_SE_INICIO_CAJA) {
            if (validar()) {
                BigDecimal value = new BigDecimal(saldo.getText());
                Caja nuevaCaja = new Caja();
                nuevaCaja.setSaldoactual(value);
                nuevaCaja.setIniciodia(value);
                nuevaCaja.setFechaabre(new Date());
                nuevaCaja.setHoraabre(new Date());
                nuevaCaja.setEstado(Constantes.ESTADO_ACTIVO);
                
                try {
                    modelo.insertarRegistro("insertarCajaDia", nuevaCaja);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                JOptionPane.showMessageDialog(null, "Caja Inciada");
                this.dispose();
            }
        } else {
            this.dispose();
        }
    }

    private void saldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldoActionPerformed
        guardar();
}//GEN-LAST:event_saldoActionPerformed

    private void saldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saldoKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_saldoKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel et;
    private javax.swing.JButton guardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField saldo;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
