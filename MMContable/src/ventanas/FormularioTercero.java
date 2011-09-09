package ventanas;

import beans.Ciudad;
import beans.Departamento;
import beans.Estado;
import beans.Tercero;
import beans.TipoTercero;
import db.Model;
import javax.swing.JOptionPane;
import interfaces.Buscadores;
import interfaces.Constantes;
import java.awt.Frame;
import java.util.List;
import javax.swing.JDialog;
import utilidades.Validaciones;

public class FormularioTercero extends JDialog {

    private Buscadores buscador;
    private Tercero tercero;
    private int funcion;
    private Model modelo;

    public FormularioTercero(Frame padre, boolean modal, Buscadores buscador) {
        super(padre, modal);
        this.modelo = Model.getInstance();
        setTitle("Terceros (Clientes, Proveedores y Afines)");
        this.buscador = buscador;
        tercero = new Tercero();
        initComponents();
        llenarCombos();
        nit.requestFocus();

        funcion = Constantes.ESTADO_CREACION;

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public FormularioTercero(Frame padre, boolean modal, Buscadores buscador, Tercero tercero) {
        super(padre, modal);
        this.modelo = Model.getInstance();
        setTitle("Terceros (Clientes, Proveedores y Afines)");
        this.buscador = buscador;
        this.tercero = tercero;

        initComponents();
        llenarCombos();
        llenar();
        nit.requestFocus();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void llenarCombos() {
        try {
            List<Departamento> listaDepartamentos = (List<Departamento>) modelo.obtenerListado("obtenerDepartamentos", null);
            this.departamento.removeAllItems();

            for (int i = 0; i < listaDepartamentos.size(); i++) {
                this.departamento.addItem(listaDepartamentos.get(i));
            }

            departamento.setSelectedIndex(0);
            Departamento departamentoSeleccionado = listaDepartamentos.get(0);
            this.ciudad.removeAllItems();
            List<Ciudad> listaCiudades = (List<Ciudad>) modelo.obtenerListado("obtenerCiudadesDepartamento", departamentoSeleccionado.getId());

            for (Ciudad city : listaCiudades) {
                this.ciudad.addItem(city);
            }

            List<Estado> listaEstados = (List<Estado>) modelo.obtenerListado("obtenerEstados");
            this.estado.removeAllItems();

            for (int i = 0; i < listaEstados.size(); i++) {
                this.estado.addItem(listaEstados.get(i));
            }

            List<TipoTercero> listaTipos = (List<TipoTercero>) modelo.obtenerListado("obtenerTiposTercero");
            this.tipo.removeAllItems();

            for (int i = 0; i < listaTipos.size(); i++) {
                this.tipo.addItem(listaTipos.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        } else if (telefono.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el TELÉFONO  por favor");
            telefono.requestFocus();
            return false;
        }else if (!Validaciones.esEmail(email.getText())) {
            JOptionPane.showMessageDialog(null, "Digite un E-MAIL valido por favor");
            email.requestFocus();
            return false;
        }else if(contacto.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite un CONTACTO valido por favor");
            email.requestFocus();
            return false;
        }

        return true;
    }

    public void llenar() {
        funcion = Constantes.ESTADO_EDICION;
        nit.setText(tercero.getNit() + "");
        nombre.setText(tercero.getNombre());
        telefono.setText(tercero.getTelefono());
        email.setText(tercero.getEmail());
        contacto.setText(tercero.getContacto());
        cel.setText(tercero.getCelular());
        estado.setSelectedItem(tercero.getEstado());
        try {
            departamento.setSelectedItem(tercero.getDepartamento());
            ciudad.setSelectedItem(tercero.getCiudad());
            tipo.setSelectedItem(tercero.getTipo());
        } catch (Exception er) {
            er.printStackTrace();
        }

        direccion.setText(tercero.getDireccion());
        dv.setText(utilidades.FormatoNumeros.ValidarDigitoVerificacion(tercero.getNit() + ""));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dv = new javax.swing.JTextField();
        tipo = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        departamento = new javax.swing.JComboBox();
        ciudad = new javax.swing.JComboBox();
        telefono = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        contacto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        cel = new javax.swing.JTextField();
        guardar = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

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

        jLabel4.setText("Nombre:");

        nit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nitActionPerformed(evt);
            }
        });
        nit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nitKeyTyped(evt);
            }
        });

        jLabel2.setText("Identificación:");

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

        jLabel1.setBackground(new java.awt.Color(0, 153, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));

        jLabel3.setText("DV:");

        dv.setEditable(false);

        tipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoItemStateChanged(evt);
            }
        });

        jLabel10.setText("Tipo:");

        jPanel1.setBackground(new java.awt.Color(212, 233, 255));

        jLabel6.setText("Teléfono:");

        jLabel5.setText("Dirección:");

        jLabel7.setText("Ciudad:");

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

        jLabel9.setText("Departamento:");

        departamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                departamentoItemStateChanged(evt);
            }
        });

        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(212, 233, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Información del Contacto"));

        jLabel11.setText("Contacto:");

        contacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contactoKeyTyped(evt);
            }
        });

        jLabel8.setText("E-Mail:");

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel27.setText("Celular:");

        cel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celActionPerformed(evt);
            }
        });
        cel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                celKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cel, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                    .addComponent(contacto, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(cel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(direccion))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Básicos", jPanel1);

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

        jLabel28.setText("Estado:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(nit, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addGap(2, 2, 2)
                                        .addComponent(dv, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(estado, 0, 85, Short.MAX_VALUE))
                                    .addComponent(nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(dv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salir)
                    .addComponent(guardar))
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Datos Básicos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nitKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        } else if (nit.getText().length() == 15) {
            evt.consume();
        }
}//GEN-LAST:event_nitKeyTyped

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {
            if (validar()) {
                long nitEscrito = Long.parseLong(nit.getText());
                if (funcion == Constantes.ESTADO_EDICION) {
                    if (tercero.getNit() == nitEscrito) {
                        guardar();
                    } else if (modelo.obtenerRegistro("existeTercero", nitEscrito) != null) {
                        JOptionPane.showMessageDialog(null, "El Tercero ya Existe");
                        nit.requestFocus();
                    }
                } else if (modelo.obtenerRegistro("existeTercero", nitEscrito) != null) {
                    JOptionPane.showMessageDialog(null, "El Tercero ya Existe");
                    nit.requestFocus();
                } else {
                    guardar();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}//GEN-LAST:event_guardarActionPerformed

    public void guardar() {
        try {
            tercero.setNit(Long.parseLong(nit.getText()));
            tercero.setNombre(nombre.getText());
            tercero.setTipo((TipoTercero) tipo.getSelectedItem());
            tercero.setDireccion(direccion.getText());
            tercero.setDepartamento((Departamento) departamento.getSelectedItem());
            tercero.setCiudad((Ciudad) ciudad.getSelectedItem());
            tercero.setTelefono(telefono.getText());
            tercero.setEmail(email.getText());
            tercero.setContacto(contacto.getText());
            tercero.setCelular(cel.getText());
            tercero.setEstado((Estado) estado.getSelectedItem());

            if (funcion == Constantes.ESTADO_CREACION) {
                modelo.insertarRegistro("insertarTercero", tercero);
            } else {
                modelo.actualizarRegistro("actualizarTercero", tercero);
            }

            buscador.buscar();
            this.dispose();
            JOptionPane.showMessageDialog(null, "Datos guardados con éxito");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
}//GEN-LAST:event_salirActionPerformed

    private void nitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nitKeyReleased
        dv.setText(utilidades.FormatoNumeros.ValidarDigitoVerificacion(nit.getText()));
    }//GEN-LAST:event_nitKeyReleased

    private void tipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoItemStateChanged
        direccion.requestFocus();
    }//GEN-LAST:event_tipoItemStateChanged

    private void direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionActionPerformed
        departamento.requestFocus();
    }//GEN-LAST:event_direccionActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        contacto.requestFocus();
    }//GEN-LAST:event_emailActionPerformed

    private void celActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celActionPerformed
        guardar.requestFocus();
    }//GEN-LAST:event_celActionPerformed

    private void nitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nitActionPerformed
        nombre.requestFocus();
    }//GEN-LAST:event_nitActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        direccion.requestFocus();
    }//GEN-LAST:event_nombreActionPerformed

    private void departamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_departamentoItemStateChanged
        try {
            Departamento departamentoSeleccionado = (Departamento) departamento.getSelectedItem();
            this.ciudad.removeAllItems();
            List<Ciudad> listaCiudades = (List<Ciudad>) modelo.obtenerListado("obtenerCiudadesDepartamento", departamentoSeleccionado.getId());
            for (Ciudad city : listaCiudades) {
                this.ciudad.addItem(city);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_departamentoItemStateChanged

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        if (Character.isLetterOrDigit(evt.getKeyChar())) {
            if (nombre.getText().length() == 45) {
                evt.consume();
            } else {
                String letra = ("" + evt.getKeyChar()).toUpperCase();

                evt.consume();

                nombre.setText(nombre.getText().concat(letra));
            }
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyTyped
        if (direccion.getText().length() == 200) {
            evt.consume();
        } else {
            String letra = ("" + evt.getKeyChar()).toUpperCase();

            evt.consume();

            direccion.setText(direccion.getText().concat(letra));
        }
    }//GEN-LAST:event_direccionKeyTyped

    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        if (!Character.isDigit(evt.getKeyChar()) || telefono.getText().length() >= 12) {
            evt.consume();
        }
    }//GEN-LAST:event_telefonoKeyTyped

    private void celKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_celKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar()) || cel.getText().length() >= 12) {
            evt.consume();
        }
    }//GEN-LAST:event_celKeyTyped

    private void contactoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactoKeyTyped
        // TODO add your handling code here:
                if (Character.isLetterOrDigit(evt.getKeyChar())) {
            if (contacto.getText().length() == 45) {
                evt.consume();
            } else {
                String letra = ("" + evt.getKeyChar()).toUpperCase();

                evt.consume();

                contacto.setText(contacto.getText().concat(letra));
            }
        }
    }//GEN-LAST:event_contactoKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cel;
    private javax.swing.JComboBox ciudad;
    private javax.swing.JTextField contacto;
    private javax.swing.JComboBox departamento;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField dv;
    private javax.swing.JTextField email;
    private javax.swing.JComboBox estado;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nit;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton salir;
    private javax.swing.JTextField telefono;
    private javax.swing.JComboBox tipo;
    // End of variables declaration//GEN-END:variables
}
