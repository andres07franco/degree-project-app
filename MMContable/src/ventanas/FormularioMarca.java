package ventanas;

import beans.Estado;
import beans.Marca;
import db.Model;
import javax.swing.JOptionPane;
import interfaces.Buscadores;
import interfaces.Constantes;
import java.awt.Frame;
import java.util.List;

public class FormularioMarca extends javax.swing.JDialog {

    private Marca marcaSeleccionada;
    private Buscadores buscador;
    private int funcion;
    private Model modelo;

    public FormularioMarca(Frame padre, boolean modal, Buscadores buscador) {

        super(padre, modal);
        this.buscador = buscador;
        this.modelo = Model.getInstance();
        marcaSeleccionada = new Marca();
        initComponents();
        cargarCombo();
        this.setLocationRelativeTo(padre);
        this.setVisible(true);
    }

    public FormularioMarca(Frame padre, boolean modal, Buscadores buscador, Marca marcaSeleccionada) {
        super(padre, modal);
        this.buscador = buscador;
        this.modelo = Model.getInstance();
        this.marcaSeleccionada = marcaSeleccionada;
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
        jSeparator1 = new javax.swing.JSeparator();
        guardar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        marca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Marcas");

        jPanel1.setBackground(new java.awt.Color(153, 205, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(313, 130));

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

        marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcaActionPerformed(evt);
            }
        });
        marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                marcaKeyTyped(evt);
            }
        });

        jLabel2.setText("Marca:");

        jLabel1.setText("Código:");

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
                                .addComponent(codigo, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(marca, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
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
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {
            if (validar()) {
                int existeMarca = (Integer) modelo.obtenerRegistro("existeMarca", marca.getText());
                int existeCodigo = (Integer) modelo.obtenerRegistro("existeMarcaPorCodigo", codigo.getText());

                if (funcion == Constantes.ESTADO_EDICION) {
                    if (marcaSeleccionada.getCodigo().equals(codigo.getText())) {
                        if (marcaSeleccionada.getMarca().equals(marca.getText())) {
                            guardar();
                        } else {
                            if (existeMarca == 0) {
                                guardar();
                            } else {
                                JOptionPane.showMessageDialog(null, "La MARCA ya Existe");
                                marca.requestFocus();
                            }
                        }
                    } else {
                        if (existeCodigo > 0) {
                            JOptionPane.showMessageDialog(null, "El CÓDIGO ya Existe");
                            codigo.requestFocus();
                        } else {
                            if (marcaSeleccionada.getMarca().equals(marca.getText())) {
                                guardar();
                            } else {
                                if (existeMarca == 0) {
                                    guardar();
                                } else {
                                    JOptionPane.showMessageDialog(null, "La MARCA ya Existe");
                                    marca.requestFocus();
                                }
                            }
                        }
                    }
                } else {
                    if (existeMarca > 0) {
                        JOptionPane.showMessageDialog(null, "La MARCA ya Existe");
                        marca.requestFocus();
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

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
}//GEN-LAST:event_salirActionPerformed

    private void marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaActionPerformed
        guardar.requestFocus();
}//GEN-LAST:event_marcaActionPerformed

    private void codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoActionPerformed
        marca.requestFocus();
}//GEN-LAST:event_codigoActionPerformed

    private void codigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyTyped
        if (codigo.getText().length() > 9) {
            evt.consume();
        }
    }//GEN-LAST:event_codigoKeyTyped

    private void marcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_marcaKeyTyped
        if (codigo.getText().length() > 44) {
            evt.consume();
        }
    }//GEN-LAST:event_marcaKeyTyped

    public boolean validar() {
        if (codigo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el CÓDIGO porfavor");
            codigo.requestFocus();
            return false;
        } else if (marca.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la MARCA porfavor");
            marca.requestFocus();
            return false;
        }
        return true;
    }

    private void llenar() {
        codigo.setText(marcaSeleccionada.getCodigo());
        marca.setText(marcaSeleccionada.getMarca());
        estado.setSelectedItem(marcaSeleccionada.getEstado());
        funcion = Constantes.ESTADO_EDICION;
    }

    public void guardar() {
        marcaSeleccionada.setCodigo(codigo.getText());
        marcaSeleccionada.setMarca(marca.getText());
        marcaSeleccionada.setEstado((Estado) estado.getSelectedItem());

        try {
            if (funcion == Constantes.ESTADO_CREACION) {
                modelo.insertarRegistro("insertarMarca", marcaSeleccionada);
            } else {
                modelo.actualizarRegistro("actualizarMarca", marcaSeleccionada);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        buscador.buscar();
        this.dispose();
        JOptionPane.showMessageDialog(null, "Datos guardados con éxito");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigo;
    private javax.swing.JComboBox estado;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField marca;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
