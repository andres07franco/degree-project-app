package ventanas;

import beans.Documento;
import beans.Tercero;
import db.Model;
import interfaces.Buscadores;
import interfaces.Constantes;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import utilidades.ModeloTabla;

public class ListaCompras extends javax.swing.JPanel implements Buscadores {

    private JTabbedPane pestana;
    private JDesktopPane desktopPane;
    private ModeloTabla dtm;
    private List<Documento> l;
    private Model modelo;
    private Frame padre;

    public ListaCompras(JTabbedPane pestana, JDesktopPane desktopPane, Frame padre) {
        this.pestana = pestana;
        this.desktopPane = desktopPane;
        this.modelo = Model.getInstance();
        this.padre = padre;

        initComponents();
        buscar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        nuevo = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        busqueda = new javax.swing.JTextField();
        salir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(235, 241, 247));

        jScrollPane1.setBackground(new java.awt.Color(177, 214, 250));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 153));
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setFont(new java.awt.Font("Arial", 1, 11));

        tabla.setAutoCreateRowSorter(true);
        tabla.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Fecha", "Tercero", "Total"
            }
        ));
        tabla.setGridColor(new java.awt.Color(153, 204, 255));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel2.setBackground(new java.awt.Color(255, 204, 102));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 153, 255), 2, true));

        nuevo.setBackground(new java.awt.Color(121, 188, 252));
        nuevo.setFont(new java.awt.Font("Arial", 1, 11));
        nuevo.setForeground(new java.awt.Color(0, 0, 102));
        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });

        editar.setBackground(new java.awt.Color(121, 188, 252));
        editar.setFont(new java.awt.Font("Arial", 1, 11));
        editar.setForeground(new java.awt.Color(0, 0, 102));
        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                busquedaKeyTyped(evt);
            }
        });

        salir.setBackground(new java.awt.Color(121, 188, 252));
        salir.setFont(new java.awt.Font("Arial", 1, 11));
        salir.setForeground(new java.awt.Color(0, 0, 102));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setText("(Número,Fecha,Nit)");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Buscar");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nuevo)
                .addGap(10, 10, 10)
                .addComponent(editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(busqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salir)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(salir)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(nuevo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
       if (evt.getClickCount() % 2 == 0) {
            if (tabla.getSelectedRow() > -1) {
                new FormularioCompras(padre, true, this, l.get(tabla.getSelectedRow()));
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila primero");
            }
        }
}//GEN-LAST:event_tablaMouseClicked

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        new FormularioCompras(padre, true,  this,null);
}//GEN-LAST:event_nuevoActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        if (tabla.getSelectedRow() > -1) {
            new FormularioCompras(padre, true, this, l.get(tabla.getSelectedRow()));
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila primero");
        }
}//GEN-LAST:event_editarActionPerformed

    private void busquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
}//GEN-LAST:event_busquedaKeyTyped

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        pestana.remove(this);
}//GEN-LAST:event_salirActionPerformed

    private void busquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyReleased
        buscar();
    }//GEN-LAST:event_busquedaKeyReleased

    public void buscar() {
        try {
             boolean cedit[] = {false, false,false,false};

            dtm = new ModeloTabla(cedit);
            dtm.addColumn("Fecha");
            dtm.addColumn("Numero");
            dtm.addColumn("Tipo");
            dtm.addColumn("Tercero");
            dtm.addColumn("Total");
            Map<String, Object> mapa = new HashMap<String, Object>();
            mapa.put("tipo", new Integer(Constantes.DOCUMENTO_FACTURA_COMPRA));
            
            mapa.put("busqueda", "%" + busqueda.getText() + "%");
            l = (List<Documento>) modelo.obtenerListado("obtenerDocumentosCyV",mapa);
            if (l != null) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                for (int i = 0; i < l.size(); i++) {
                    Object[] fila = new Object[5];
                    fila[0] =   formato.format(l.get(i).getFecha());
                    fila[1] = l.get(i).getNumero() + "";
                    fila[2] = l.get(i).getTipo().getId() == Constantes.DOCUMENTO_COTIZACION?"Cotizacion":"Factura";
                    fila[3] = l.get(i).getTercero().getNombre();
                    fila[4] = utilidades.FormatoNumeros.formatear(l.get(i).getTotal() + "");
                    dtm.addRow(fila);
                }
            }
            tabla.setModel(dtm);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busqueda;
    private javax.swing.JButton editar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nuevo;
    private javax.swing.JButton salir;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
