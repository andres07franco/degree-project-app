package ventanas;
/*
import beans.Alquileres;
import beans.Articulos;
import beans.Caja;
import beans.Documentos;
import beans.Facturapropiedades;
import beans.IngresosEgresos;
import beans.Movimientokardex;
import interfaces.Buscadores;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import managers.ManejadorConfiguraciones;
import managers.ManejadorDocumentos;
import managers.Model;
import utilidades.Fechavo;

public class Formulario_Multa_Conciliacion extends javax.swing.JDialog {

    /** Creates new form Formulario_Egresos_Ingresos 
    ManejadorDocumentos md;
    ManejadorConfiguraciones mc;
    Model m;
    Documentos d;
    Buscadores bu;
    Frame parent;
    int funcion = 0;
    List l;
    Vector v;

    public Formulario_Multa_Conciliacion(java.awt.Frame parent, boolean modal, Buscadores bu, Model m) {

        super(parent, modal);
        this.parent = parent;
        this.m = m;
        md = new ManejadorDocumentos(m);
        mc = new ManejadorConfiguraciones(m);
        this.bu = bu;
        //      ie=new IngresosEgresos();
        initComponents();
        utilidades.FormatoNumeros fn = new utilidades.FormatoNumeros(conciliacion);
        conciliacion.addKeyListener(fn);
        conciliacion.addFocusListener(fn);

        fn = new utilidades.FormatoNumeros(multa);
        multa.addKeyListener(fn);
        multa.addFocusListener(fn);

        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);

        fecha.setText(ano + "-" + mes + "-" + dia);
        Facturapropiedades fp = mc.getFacturaporpiedades("1");
//        numero.setText(""+(fp.getPrefacturas()+1));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public Formulario_Multa_Conciliacion(java.awt.Frame parent, boolean modal, Buscadores bu, Model m, List l, Documentos d, Vector v) {

        super(parent, modal);
        this.parent = parent;
        this.m = m;
        this.v = v;
        md = new ManejadorDocumentos(m);
        mc = new ManejadorConfiguraciones(m);
        this.bu = bu;
        this.d = d;
        this.l = l;
        initComponents();

        llenar();
        utilidades.FormatoNumeros fn = new utilidades.FormatoNumeros(conciliacion);
        conciliacion.addKeyListener(fn);
        conciliacion.addFocusListener(fn);



        fn = new utilidades.FormatoNumeros(multa);
        multa.addKeyListener(fn);
        multa.addFocusListener(fn);

        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);

        fecha.setText(ano + "-" + mes + "-" + dia);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    boolean sw = false;

    public static boolean Conciliar(java.awt.Frame parent, boolean modal, Buscadores bu, Model m, List l, Documentos d, Vector v) {
        Formulario_Multa_Conciliacion fmc = new Formulario_Multa_Conciliacion(parent, true, bu, m, l, d, v);
        return fmc.sw;
    }

    public void llenar() {
        concepto.setText("Conciliacion");
        tercero.setText(d.getTerceros().getNit() + "");
        ntercero.setText(d.getTerceros().getNombre());

        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);

        fecha.setText(ano + "-" + mes + "-" + dia);


        DefaultTableModel mt = new DefaultTableModel();
        mt.addColumn("Articulo");
        mt.addColumn("Ctd Alquilada");
        mt.addColumn("Cdt Reintegro");
        mt.addColumn("Fecha Ini");
        mt.addColumn("Fecha Fin");
        mt.addColumn("Dias");
        mt.addColumn("Dias Retraso");


        if (l != null) {
            for (int i = 0; i < l.size(); i++) {
                Alquileres al = ((Alquileres) l.get(i));
                Articulos a = al.getArticulo();
                if (al.getEstado() == 1) {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
                    int dias = new Fechavo().DiaMulta(formato.format(al.getFechaFin()));
                    int cantidades = al.getCantidad();
                    mt.addRow(new Object[]{al,
                                a.getDescripcioncomercial(),
                                cantidades,
                                v.get(i),
                                al.getFechaIni(),
                                al.getFechaFin(),
                                dias});
                }

            }
            tabla.setModel(mt);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        ntercero = new javax.swing.JTextField();
        tercero = new javax.swing.JTextField();
        et = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        conciliacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        tipoc = new javax.swing.JComboBox();
        concepto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        multa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(212, 233, 255));

        jLabel3.setText("Fecha");

        fecha.setEditable(false);

        ntercero.setEditable(false);

        tercero.setEditable(false);
        tercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terceroActionPerformed(evt);
            }
        });

        et.setText("Recibido de");

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

        conciliacion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        conciliacion.setText("0.00");
        conciliacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conciliacionActionPerformed(evt);
            }
        });
        conciliacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                conciliacionKeyReleased(evt);
            }
        });

        jLabel4.setText("Concepto");

        tipoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tipoc.setEnabled(false);

        concepto.setEditable(false);
        concepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conceptoActionPerformed(evt);
            }
        });

        jLabel5.setText("Multa");

        multa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        multa.setText("0.00");
        multa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multaActionPerformed(evt);
            }
        });
        multa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                multaKeyReleased(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion", "Ctd Alquilada", "Ctd Reintegro", "Fecha In", "Fecha Fin", "Dias", "Dias Retraso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        jLabel7.setText("Conciliacion");

        total.setFont(new java.awt.Font("Tahoma", 1, 24));
        total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total.setText("0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(279, Short.MAX_VALUE)
                .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(259, 259, 259))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(et)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                    .addComponent(tercero, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tipoc, 0, 191, Short.MAX_VALUE)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ntercero, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(concepto, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(440, 440, 440))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(multa)
                            .addComponent(conciliacion, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tercero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(et)
                    .addComponent(ntercero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tipoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(concepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(multa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(conciliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addComponent(jLabel6))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salir)
                            .addComponent(guardar))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(total)
                        .addContainerGap())))
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

    private void terceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terceroActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_terceroActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        if (validar()) {
            guardar();
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        sw = false;
        this.dispose();
        //   tab.remove(dp);
}//GEN-LAST:event_salirActionPerformed

    private void conciliacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conciliacionActionPerformed
        // TODO add your handling code here:
        if (validar()) {
            guardar();
        }
}//GEN-LAST:event_conciliacionActionPerformed

    public void guardar() {
        Facturapropiedades fp = mc.getFacturaporpiedades("1");
        IngresosEgresos ie = new IngresosEgresos();
        ie.setNumero((fp.getPrefacturas() + 1) + "");
        ie.setConcepto(concepto.getText());
        ie.setValor(Double.parseDouble(total.getText().replaceAll(",", "")));
        ie.setTipoconcepto(6);
        ie.setFecha(new Date(fecha.getText().replaceAll("-", "/")));
        md.crear(ie);


        crearKarex();

        Caja caja = md.getCaja();
        ie.setTipo(0);

        sw = true;
        bu.buscar();
        this.dispose();
        JOptionPane.showMessageDialog(null, "Conciliacion Realizada Con Exito");
    }

    public boolean validar() {
        if (multa.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la Multa porfavor");
            multa.requestFocus();
            return false;
        } else if (Double.parseDouble(multa.getText().replaceAll(",", "")) <= 0) {
            JOptionPane.showMessageDialog(null, "Digite la Multa porfavor");
            multa.requestFocus();
            return false;
        } else if (conciliacion.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la Conciliacion porfavor");
            conciliacion.requestFocus();
            return false;
        } else if (Double.parseDouble(conciliacion.getText().replaceAll(",", "")) <= 0) {
            JOptionPane.showMessageDialog(null, "Digite la Conciliacion porfavor");
            conciliacion.requestFocus();
            return false;
        }
        return true;
    }

     private void conciliacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_conciliacionKeyReleased
         // TODO add your handling code here:
         double mult = 0;
         double conc = 0;
         if (!multa.getText().trim().equals("")) {
             mult = Double.parseDouble(multa.getText().replaceAll(",", ""));
         }
         if (!conciliacion.getText().trim().equals("")) {
             conc = Double.parseDouble(conciliacion.getText().replaceAll(",", ""));
         }
         total.setText(utilidades.FormatoNumeros.formatear("" + (conc + mult)));
}//GEN-LAST:event_conciliacionKeyReleased

    private void multaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multaActionPerformed
        // TODO add your handling code here:
        conciliacion.requestFocus();
}//GEN-LAST:event_multaActionPerformed

    private void conceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conceptoActionPerformed
        // TODO add your handling code here:
        multa.requestFocus();
    }//GEN-LAST:event_conceptoActionPerformed

    private void multaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_multaKeyReleased
        // TODO add your handling code here:
        double mult = 0;
        double conc = 0;
        if (!multa.getText().trim().equals("")) {
            mult = Double.parseDouble(multa.getText().replaceAll(",", ""));
        }
        if (!conciliacion.getText().trim().equals("")) {
            conc = Double.parseDouble(conciliacion.getText().replaceAll(",", ""));
        }
        total.setText(utilidades.FormatoNumeros.formatear("" + (conc + mult)));


    }//GEN-LAST:event_multaKeyReleased

    public void crearKarex() {
        Documentos d = new Documentos();
        boolean sw = false;
        if (d != null) {

            List<Movimientokardex> lmk = new LinkedList();

            for (int i = 0; i < tabla.getRowCount(); i++) {

                Alquileres al = (Alquileres) tabla.getValueAt(i, 0);
                al.setEstado(2);
                Articulos a = al.getArticulo();
                double existencia = a.getExistencia() - (Integer.parseInt(tabla.getValueAt(i, 2) + ""));
                if (existencia != 0) {
                    sw = true;
                    Movimientokardex mk = new Movimientokardex();

                    mk.setArticulo(a);
                    mk.setDocumento(d);

                    mk.setSalida(Double.parseDouble(tabla.getValueAt(i, 2) + ""));
                    mk.setEntrada(0.0);



                    mk.setExistencia(existencia);
                    mk.setFecha(new Date());
                    mk.setTercero("Ajuste");
                    mk.setDescripcion("Ajuste de Inventerio");
                    mk.setVlrunit(a.getVlrpromedio());
                    a.setExistencia(mk.getExistencia());
                    mk.setSaldo(mk.getExistencia() * a.getVlrpromedio());
                    lmk.add(mk);

                    md.modificar(a);
                }
                md.modificar(al);
            }
            if (sw) {
                d.setTipo(4);
                d.setEstado(3);
                d.setTerceros(md.getTercero("1"));
                d.setNota("Ajuste");
                d.setMovimientokardexCollection(lmk);
                md.crear(d);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField concepto;
    private javax.swing.JTextField conciliacion;
    private javax.swing.JLabel et;
    private javax.swing.JTextField fecha;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField multa;
    private javax.swing.JTextField ntercero;
    private javax.swing.JButton salir;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField tercero;
    private javax.swing.JComboBox tipoc;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
 */