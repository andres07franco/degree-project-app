package ventanas;

import beans.Caja;
import beans.Documento;
import beans.FacturaEmpresa;
import beans.Tercero;
import beans.TipoDocumento;
import beans.TipoPago;
import interfaces.Buscadores;
import java.awt.Frame;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import db.Model;
import interfaces.Constantes;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Formulario_Egresos_Ingresos extends javax.swing.JDialog {

    /** Creates new form Formulario_Egresos_Ingresos */

    Model m;
    Buscadores bu;
    Frame parent;
    int funcion = 0;
    Documento d,doc;
    Tercero t;

    public Formulario_Egresos_Ingresos(java.awt.Frame parent, boolean modal, Buscadores bu) {

        super(parent, modal);
        this.parent = parent;
        this.m = m;
        this.bu = bu;
        initComponents();
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);
         m = m.getInstance();
        obtentenerConsecutivo();
        funcion = interfaces.Constantes.ESTADO_CREACION;
        fecha.setText(ano + "-" + mes + "-" + dia);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public Formulario_Egresos_Ingresos(java.awt.Frame parent, boolean modal, Buscadores bu,Documento d) {

        super(parent, modal);
        this.parent = parent;
        this.m = m;
        this.bu = bu;
        this.d = d;
        initComponents();
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);
        m = m.getInstance();
        obtentenerConsecutivo();
        fecha.setText(ano + "-" + mes + "-" + dia);
        
        if(d!=null)
            llenar();
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void llenar() {

        funcion = interfaces.Constantes.ESTADO_EDICION;

 
         if (d.getTipo().getId() == Constantes.DOCUMENTO_ABONO_A_FACTURA ){

            this.tipoc.setSelectedIndex(0);
            this.tipo.setSelectedIndex(1);
            
            try {
                
                 List<Documento> l = (List<Documento>) Model.getInstance().obtenerListado("obtenerUnDocumento",d.getDocumento().getId());
             
                    if(l.size()> 0)
                          if (l.get(0).getTipo().getId() == Constantes.DOCUMENTO_FACTURA_VENTA)
                                 this.tipo.setSelectedIndex(0);
            } catch (Exception ex) {
                Logger.getLogger(Formulario_Egresos_Ingresos.class.getName()).log(Level.SEVERE, null, ex);
            }
  
         }else if (d.getTipo().getId() == Constantes.DOCUMENTO_INGRESO){
            this.tipo.setSelectedIndex(0);
            this.tipoc.setSelectedIndex(1);
         } else if (d.getTipo().getId() == Constantes.DOCUMENTO_EGRESO){
            this.tipo.setSelectedIndex(1);
            this.tipo.setSelectedIndex(1);
         }

        this.tipoc.setEditable(false);
        this.tipo.setEditable(false);
        tercero.setText(d.getTercero().getNombre());
        ntercero.setText(d.getTercero().getNit().toString());
      /*  tipoc.setSelectedIndex(d.getTipoconcepto());*/
        valor.setText(utilidades.FormatoNumeros.formatear(d.getTotal() + ""));
        numero.setText(d.getNumero());
        fecha.setText(new java.text.SimpleDateFormat("yyyy-MM-dd").format(d.getFecha()));

        valor.setEnabled(false);
        this.guardar.setEnabled(false);
        this.imprimir.setEnabled(true);
        this.buscaFactrura.setEnabled(false);
        this.buscaproveedor.setEnabled(false);
    }

    public void obtentenerConsecutivo(){
                FacturaEmpresa fe = null;
        try {
            fe = (FacturaEmpresa) m.obtenerRegistro("obtenerFacturaEmpresaActual");
        } catch (Exception ex) {
            Logger.getLogger(FormularioPunoVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fe != null) {
            long consecutivo = 0;
            if(tipo.getSelectedIndex()==0)
                consecutivo = fe.getIngresos() + 1;
            else{
                 consecutivo = fe.getEgresos() + 1;
            }
            numero.setText(consecutivo + "");
        } else {
            JOptionPane.showMessageDialog(null, "No se han configurado las propiedades de la Factura,  no podrá  vender. \nDiríjase al Modulo de configuración y a continuación haga click en Personalizar Factura");
            this.dispose();
            
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.*/
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        buscaproveedor = new javax.swing.JButton();
        ntercero = new javax.swing.JTextField();
        tercero = new javax.swing.JTextField();
        et = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        imprimir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        tipoc = new javax.swing.JComboBox();
        concepto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        valor = new javax.swing.JTextField();
        buscaFactrura = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comprobantes de Ingresos y Egresos");

        jPanel1.setBackground(new java.awt.Color(212, 233, 255));

        jLabel1.setText("Comprobante de ");

        tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ingreso", "Egreso" }));
        tipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoItemStateChanged(evt);
            }
        });

        jLabel2.setText("Numero");

        numero.setEditable(false);
        numero.setText("0");

        jLabel3.setText("Fecha");

        fecha.setEditable(false);

        buscaproveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-16x16.png"))); // NOI18N
        buscaproveedor.setEnabled(false);
        buscaproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaproveedorActionPerformed(evt);
            }
        });

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

        imprimir.setBackground(new java.awt.Color(0, 153, 255));
        imprimir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        imprimir.setForeground(new java.awt.Color(0, 51, 153));
        imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print-16x16.png"))); // NOI18N
        imprimir.setText("Imprimir");
        imprimir.setEnabled(false);
        imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirActionPerformed(evt);
            }
        });

        jLabel4.setText("Concepto");

        tipoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Abono a Factura", "Otros Ingresos" }));
        tipoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipocItemStateChanged(evt);
            }
        });

        concepto.setEnabled(false);
        concepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conceptoActionPerformed(evt);
            }
        });

        jLabel5.setText("Valor a pagar");

        valor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        valor.setText("0.00");
        valor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorActionPerformed(evt);
            }
        });

        buscaFactrura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-16x16.png"))); // NOI18N
        buscaFactrura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaFactruraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(imprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(guardar, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(et)
                                    .addComponent(jLabel4))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tercero)
                                    .addComponent(tipoc, 0, 116, Short.MAX_VALUE)
                                    .addComponent(valor, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(concepto, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buscaFactrura, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(ntercero, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buscaproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(12, 12, 12)
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(concepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tipoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(buscaFactrura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ntercero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tercero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(et))
                    .addComponent(buscaproveedor, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar)
                    .addComponent(imprimir)
                    .addComponent(salir))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscaproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaproveedorActionPerformed
        // TODO add your handling code here:
       adicionarTercero();
}//GEN-LAST:event_buscaproveedorActionPerformed

  public void adicionarTercero(){
        t = (new BuscaTerceros(parent, true, true).getTercero());
        if (t != null) {
            tercero.setText(t.getNit() + "");
            ntercero.setText(t.getNombre());
        }
    }

    public void adicionarDocumento(){
        if(tipo.getSelectedIndex() == 0)
            d = (new BuscarFactura(parent, true, Constantes.DOCUMENTO_FACTURA_VENTA).getDocumento());
        else
            d = (new BuscarFactura(parent, true, Constantes.DOCUMENTO_FACTURA_COMPRA).getDocumento());
        if (d != null) {
            concepto.setText(d.getNumero() + "");
            t=d.getTercero();
            tercero.setText(t.getNit() + "");
            ntercero.setText(t.getNombre());
            
        }
    }

    private void terceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terceroActionPerformed
        // TODO add your handling code here:
      /*  if (tercero.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el nit del Tercero ");
        } else if (md.getTercero((Object) tercero.getText()) != null) {
            ie.setTerceros(md.getTercero((Object) tercero.getText()));
            tercero.setText(ie.getTerceros().getNit() + "");
            ntercero.setText(ie.getTerceros().getNombre());

            concepto.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Tercero no existe");
        }*/
}//GEN-LAST:event_terceroActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        if(validar())
            guardar();
    }//GEN-LAST:event_guardarActionPerformed



    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
      //     tab.remove(dp);
}//GEN-LAST:event_salirActionPerformed

    private void imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirActionPerformed
                Map parametro = new HashMap();
                if (d.getTipo().getId() == Constantes.DOCUMENTO_ABONO_A_FACTURA ){

                    this.tipoc.setSelectedIndex(0);

                    parametro.put("tiporecibo", "EGRESO" );
                    parametro.put("tipotercero", "Pagado a:" );
                    try {

                         List<Documento> l = (List<Documento>) Model.getInstance().obtenerListado("obtenerUnDocumento",d.getDocumento().getId());
                            parametro.put("concepto", "ABONO A FACTURA #" + l.get(0).getNumero());
                            if(l.size()> 0)
                                  if (l.get(0).getTipo().getId() == Constantes.DOCUMENTO_FACTURA_VENTA){
                                         parametro.put("tipo", "INGRESO" );
                                         parametro.put("tipotercero", "Recibi de:" );
                                }
                    } catch (Exception ex) {
                        Logger.getLogger(Formulario_Egresos_Ingresos.class.getName()).log(Level.SEVERE, null, ex);
                    }

                 }else if (d.getTipo().getId() == Constantes.DOCUMENTO_INGRESO){
                    parametro.put("tiporecibo", "INFRESO" );
                    parametro.put("concepto", d.getNota());
                    parametro.put("tipotercero", "Recibi de:" );
                 } else if (d.getTipo().getId() == Constantes.DOCUMENTO_EGRESO){
                    parametro.put("tiporecibo", "EGRESO" );
                    parametro.put("concepto", d.getNota());
                    parametro.put("tiportercerotipotercero", "Pagado a:" );
                 }

                        parametro.put("numero", "" + d.getNumero());
                        parametro.put("tipo", d.getTipo().getId() );
                        parametro.put("valorletras", new utilidades.CnvNumsLets().toLetras(d.getTotal().longValue()));

                        this.dispose();
                        new utilidades.Reporte().runReporte("reportes/ReciboCaja.jasper", parametro);
                        
}//GEN-LAST:event_imprimirActionPerformed

    public void guardar() {
        try {
            obtentenerConsecutivo();
            Documento doc = new Documento();
            doc.setNumero(numero.getText());
            doc.setNota(concepto.getText());

            /*SELECCIONAMOS EL SITIO*/
            if (tipoc.getSelectedIndex() == 0) {
                doc.setTipo((TipoDocumento) m.obtenerRegistro("obtenerTipoDocumento",Constantes.DOCUMENTO_ABONO_A_FACTURA));
                doc.setDocumento(d);
            } else if (tipoc.getSelectedIndex() == 1) {
                 if (tipo.getSelectedIndex() == 1)
                        doc.setTipo((TipoDocumento) m.obtenerRegistro("obtenerTipoDocumento",Constantes.DOCUMENTO_INGRESO));
                 else
                   doc.setTipo((TipoDocumento) m.obtenerRegistro("obtenerTipoDocumento",Constantes.DOCUMENTO_EGRESO));
            }

            doc.setTotal(new BigDecimal(valor.getText()));
            doc.setTotalpagado(new BigDecimal(valor.getText()));
            doc.setFecha(new Date());
            doc.setTercero(t);
            doc.setDescuento(BigDecimal.ZERO);
            doc.setFechavencimiento(new Date());
            doc.setTipopago((TipoPago) m.obtenerRegistro("obtenerTipoPago", Constantes.TIPO_PAGO_PAGADO));
            doc.setEstado(Constantes.ESTADO_DOCUMENTO_PAGADO);
            doc.setSubtotal(doc.getTotal());
            try {
                /*si se ha seleccioado una factura y el concepto es abono a factura*/
                if(d!=null && tipoc.getSelectedIndex() == 0){
                   /*disminuimos saldo*/
                    d.setTotalpagado(d.getTotalpagado().add(doc.getTotal()));
                    if(d.getTotalpagado().compareTo(d.getTotal())==0)
                        d.setEstado(Constantes.ESTADO_DOCUMENTO_PAGADO);
                    m.actualizarRegistro("actualizarDocumento", d);
                    m.insertarRegistro("insertarDocumento", doc);                 
                }

                /*Actualizamos caja*/
                Caja cajaDia = (Caja) m.obtenerRegistro("obtenerCajaDia");
                if(tipo.getSelectedIndex()==0){
                   cajaDia.setSaldoactual(cajaDia.getSaldoactual().add(doc.getTotal()));
                  /* cajaDia.setVentasefectivo(cajaDia.getVentasefectivo().add(doc.getTotal()));*/
                   if(tipoc.getSelectedIndex() == 0){
                       // abono a ventas por cobrar
                       cajaDia.setPagosdecliente(cajaDia.getPagosdecliente().add(doc.getTotal()));
                   } else
                       cajaDia.setOtrosingresos(cajaDia.getOtrosingresos().add(doc.getTotal()));
                }                    
                else{
                    cajaDia.setSaldoactual(cajaDia.getSaldoactual().subtract(doc.getTotal()));
                   /* cajaDia.setComprasefectivo(cajaDia.getVentasefectivo().add(doc.getTotal()));*/
                    if(tipoc.getSelectedIndex() == 0){
                        //abono a compras por pagar
                        cajaDia.setPagoaproveedor(cajaDia.getPagoaproveedor().add(doc.getTotal()));
                        
                    } else
                        cajaDia.setGastosvarios(cajaDia.getGastosvarios().add(doc.getTotal()));
                }
                    
              
                m.actualizarRegistro("actualizarCajaDia", cajaDia);
                /* int confirmado = JOptionPane.showConfirmDialog(this,"¿Desea imprimir la Factura?","¿Imprimir?",JOptionPane.YES_NO_OPTION);
                if (JOptionPane.OK_OPTION == confirmado)
                imprimir(d.getNumero());*/
                  bu.buscar();
                 this.dispose();
                 JOptionPane.showMessageDialog(null, "Registro Exitoso");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            Logger.getLogger(Formulario_Egresos_Ingresos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validar() {
        if(tipoc.getSelectedIndex()==0){
            if(d==null){
              JOptionPane.showMessageDialog(null, "Seleccione  una factura por favor");
              buscaFactrura.requestFocus();
              return false;
            }else if(valor.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null, "Escriba un valor por favor");
                return false;

            }else if(new BigDecimal(valor.getText()).compareTo(BigDecimal.ZERO)==0){
                JOptionPane.showMessageDialog(null, "Escriba un valor mayor que 0 por favor");
                return false;
            }else if(new BigDecimal(valor.getText()).compareTo(d.getTotal().subtract(d.getTotalpagado()))==1){
                JOptionPane.showMessageDialog(null, "La cantidad digitada supera el saldo a pagar ("+d.getTotal().subtract(d.getTotalpagado())+")");
                return false;
            }
        }else{
         if (t==null) {
             JOptionPane.showMessageDialog(null, "Seleccione el Tercero por favor");
             concepto.requestFocus();
            return false;
        } else if (concepto.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el Concepto por favor");
            concepto.requestFocus();
            return false;
        } else if (valor.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el valor por favor");
            concepto.requestFocus();
            return false;
        } else if (Double.parseDouble(valor.getText().replaceAll(",", "")) <= 0) {
            JOptionPane.showMessageDialog(null, "Digite el valor por favor");
            concepto.requestFocus();
            return false;
        }
        }

        return true;
    }

    private void valorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_valorActionPerformed

    private void conceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conceptoActionPerformed
        // TODO add your handling code here:
        valor.requestFocus();
    }//GEN-LAST:event_conceptoActionPerformed

    private void tipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoItemStateChanged
        // TODO add your handling code here:
        if(funcion !=  interfaces.Constantes.ESTADO_EDICION){
                    obtentenerConsecutivo();
       if (tipo.getSelectedIndex() == 0) {
            et.setText("Recibido de");
            tipoc.removeAllItems();
            tipoc.addItem("Abono a Factura");
            tipoc.addItem("Otros Ingresos");
        } else {
            tipoc.removeAllItems();
            tipoc.addItem("Abono a Factura");
            tipoc.addItem("Otros Egresos");
            et.setText("Pagado a   ");
        }
            tercero.setText("");
            ntercero.setText("");
            t = null;
            concepto.setText("");
            d = null;
            valor.setText("0.0");
        }

    }//GEN-LAST:event_tipoItemStateChanged

    private void tipocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipocItemStateChanged
        // TODO add your handling code here:
        if(tipoc.getSelectedIndex()==0){
            buscaFactrura.setEnabled(true);
            concepto.setEditable(false);
            concepto.setEnabled(false);
            ntercero.setEnabled(false);
            ntercero.setEditable(false);
            tercero.setEditable(false);
            ntercero.setEnabled(false);
            tercero.setText("");
            ntercero.setText("");
            t = null;;
            buscaproveedor.setEnabled(false);
        }else{
            buscaFactrura.setEnabled(false);
            concepto.setEditable(true);
            concepto.setEnabled(true);
            ntercero.setEnabled(false);
            ntercero.setEditable(false);
            tercero.setEditable(false);
            ntercero.setEnabled(false);
            tercero.setText("");
            ntercero.setText("");
            buscaproveedor.setEnabled(true);
            t = null;

        }
    }//GEN-LAST:event_tipocItemStateChanged

    private void buscaFactruraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaFactruraActionPerformed
        // TODO add your handling code here:
        adicionarDocumento();
    }//GEN-LAST:event_buscaFactruraActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscaFactrura;
    private javax.swing.JButton buscaproveedor;
    private javax.swing.JTextField concepto;
    private javax.swing.JLabel et;
    private javax.swing.JTextField fecha;
    private javax.swing.JButton guardar;
    private javax.swing.JButton imprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField ntercero;
    private javax.swing.JTextField numero;
    private javax.swing.JButton salir;
    private javax.swing.JTextField tercero;
    private javax.swing.JComboBox tipo;
    private javax.swing.JComboBox tipoc;
    private javax.swing.JTextField valor;
    // End of variables declaration//GEN-END:variables
}
