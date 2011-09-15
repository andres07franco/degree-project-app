package ventanas;


import beans.Articulo;
import beans.ArticulosDocumento;
import beans.Documento;
import beans.Estado;
import beans.Kardex;
import beans.Tercero;
import beans.TipoDescuento;
import beans.TipoDocumento;
import beans.TipoPago;
import interfaces.Buscadores;
import java.awt.Frame;
import javax.swing.*;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import db.Model;
import interfaces.Constantes;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import utilidades.ModeloTabla;
import utilidades.Validaciones;

public class Formulario_Saldos_Iniciales extends javax.swing.JDialog {

    int funcion = interfaces.Constantes.ESTADO_CREACION; /*funcion actual de la interface*/
    Buscadores buscador; /*buscador de articulos*/
    Model m; /*clase modelo para la persistencia*/
    Documento d; /*Benas para cracon y edcion*/
   
    ModeloTabla mt = new ModeloTabla(); /**/
    Frame parent;
    Articulo a;
    int estado;
    private List<ArticulosDocumento> listaArticulos;

    public Formulario_Saldos_Iniciales(Frame parent, boolean modal, Buscadores buscador) {

        super(parent, modal);
        this.m = Model.getInstance();
        this.parent = parent;
        this.buscador = buscador;
        estado = interfaces.Constantes.ADICIONANDO_ITEMS;

        init();

        try {
            d = (Documento) m.obtenerRegistro("obtenerSaldosIniciales");
            if (d != null) {
                funcion = Constantes.ESTADO_EDICION;
                llenar();
                
                /*si ya estan inicado lo saldos debe deshabilitar culauqier accion*/
      
                if(d.getEstado().equals(Constantes.ESTADO_DOCUMENTO_INICIADO )){
                    this.buscara.setEnabled(false);
                    this.articulo.setEnabled(false);
                    this.vunit.setEnabled(false);
                    this.quitar.setEnabled(false);
                    this.guardar.setEnabled(false);
                    this.guardar1.setEnabled(false);
                    this.restaurar.setEnabled(false);
                    this.cantidad.setEnabled(false);
                    this.adicionar.setEnabled(false);
                }
            } 

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent, "" + ex.getMessage());
        }

        articulo.requestFocus();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void init() {

        initComponents();
        this.setResizable(false);
        boolean cedit[] = {false, false,false,false,false};
        mt = new ModeloTabla(cedit);
        /*Asignamos las columnas*/
        mt.addColumn("Codigo");
        mt.addColumn("Descripcion");
        mt.addColumn("Cantidad");
        mt.addColumn("Vlr Unit");
        mt.addColumn("Vlr Par");
        tabla.setModel(mt);

        /*asignando mascaras a los textfield*/
       utilidades.FormatoNumeros fn = new utilidades.FormatoNumeros(vunit);
         /*vunit.addKeyListener(fn);
        vunit.addFocusListener(fn);*/

        vunit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
              
            }
        });

        fn = new utilidades.FormatoNumeros(vparcial);
        vparcial.addKeyListener(fn);
        vparcial.addFocusListener(fn);

        utilidades.FormatoCantidades fc = new utilidades.FormatoCantidades(cantidad);
        cantidad.addKeyListener(fn);
        cantidad.addFocusListener(fn);

        /*inicializando valores en los textfield*/
        articulo.setText("");
        descripcion.setText("");
        cantidad.setText("1");
        vunit.setText("0.00");
        vparcial.setText("0.00");


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tab = new javax.swing.JTabbedPane();
        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        articulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        vunit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        vparcial = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        quitar = new javax.swing.JButton();
        adicionar = new javax.swing.JButton();
        buscara = new javax.swing.JButton();
        total = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nota = new javax.swing.JTextField();
        descripcion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        restaurar = new javax.swing.JButton();
        guardar1 = new javax.swing.JButton();

        setTitle("Saldos Iniciales");

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

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

        panel.setBackground(new java.awt.Color(212, 233, 255));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Cantidad", "Vlr Unit", "Vlr Par"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                articuloActionPerformed(evt);
            }
        });
        articulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                articuloKeyPressed(evt);
            }
        });

        jLabel3.setText("Código");

        cantidad.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                cantidadMouseDragged(evt);
            }
        });
        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });
        cantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cantidadFocusLost(evt);
            }
        });

        jLabel5.setText("Cantidad");

        vunit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vunitActionPerformed(evt);
            }
        });
        vunit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vunitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                vunitFocusLost(evt);
            }
        });
        vunit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vunitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vunitKeyTyped(evt);
            }
        });

        jLabel6.setText("Vlr Unit");

        vparcial.setEditable(false);

        jLabel7.setText("Vlr Par");

        quitar.setForeground(new java.awt.Color(0, 0, 204));
        quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        quitar.setText("Quitar");
        quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarActionPerformed(evt);
            }
        });

        adicionar.setForeground(new java.awt.Color(0, 0, 204));
        adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        adicionar.setText("Adicionar");
        adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarActionPerformed(evt);
            }
        });

        buscara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-16x16.png"))); // NOI18N
        buscara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaraActionPerformed(evt);
            }
        });

        total.setFont(new java.awt.Font("Tahoma", 1, 24));
        total.setForeground(new java.awt.Color(255, 51, 0));
        total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total.setText("0.00");

        jLabel11.setText("Nota");

        descripcion.setEditable(false);
        descripcion.setEnabled(false);

        jLabel9.setText("Descripción Comercial");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(buscara, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(10, 10, 10)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(descripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(53, 53, 53)))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(vunit, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vparcial, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(90, 90, 90)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nota, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(total)
                        .addComponent(jLabel7))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(vunit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(adicionar)
                        .addComponent(vparcial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buscara))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        tab.addTab("Detalles", panel);

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

        restaurar.setBackground(new java.awt.Color(0, 155, 255));
        restaurar.setFont(new java.awt.Font("Tahoma", 1, 11));
        restaurar.setForeground(new java.awt.Color(0, 51, 153));
        restaurar.setText("Restaurar");
        restaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restaurarActionPerformed(evt);
            }
        });

        guardar1.setBackground(new java.awt.Color(0, 153, 255));
        guardar1.setFont(new java.awt.Font("Tahoma", 1, 11));
        guardar1.setForeground(new java.awt.Color(0, 51, 153));
        guardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        guardar1.setText("Guardar Saldos Definitivos");
        guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(guardar1)
                        .addGap(5, 5, 5)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(restaurar)
                        .addComponent(guardar))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(salir)
                        .addComponent(guardar1)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();

}//GEN-LAST:event_salirActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:

        if (validar()) {
            try {
                guardar(Constantes.ESTADO_DOCUMENTO_GUARDADO);
                JOptionPane.showMessageDialog(null,"Saldos Guardados Con Éxito");
                this.dispose();
            } catch (Exception ex) {
                Logger.getLogger(Formulario_Saldos_Iniciales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_guardarActionPerformed

    public void guardar(String estado) throws Exception {
        /*CABECERA*/
        /*creacion de objeto documento y recoelccion de parametros*/
            if(d == null) /* si no se esta editando se creo*/
                 this.d = new Documento();
            d.setFecha(new Date());
            d.setTipo((TipoDocumento)m.obtenerRegistro("obtenerTipoDocumento",Constantes.DOCUMENTO_SALDOS_INICIALES));
            d.setTercero((Tercero) m.obtenerRegistro("obtenerTerceroPorId", Constantes.TERCERO_POR_DEFECTO));
            d.setNumero(Constantes.NUMERO_SALDOS_INICIALES + "");
            d.setEstado(estado);
            d.setNota(this.nota.getText());
            d.setTipopago((TipoPago) m.obtenerRegistro("obtenerTipoPago", Constantes.TIPO_PAGO_PAGADO));
            d.setFechavencimiento(new Date());
            d.setTotal(new BigDecimal(this.total.getText().replaceAll(",","")));
            d.setTotalpagado(new BigDecimal(this.total.getText().replaceAll(",","")));
            d.setTipodescuento(new TipoDescuento());
            d.setSubtotal(new BigDecimal(this.total.getText().replaceAll(",","")));
           
            d.setDescuento(new BigDecimal("0"));
            d.setId(Constantes.NUMERO_SALDOS_INICIALES);
            if(funcion == Constantes.ESTADO_EDICION){
                m.actualizarRegistro("actualizarDocumento", d);
                m.borrarRegistro("borrarArticulosDocumento", d.getId());
            }                 
            else
                m.insertarRegistro("insertarDocumento", d);
           
            /*DETALLES*/
            ArticulosDocumento ad = null;
            for(int i = 0; i < this.tabla.getRowCount() ; i++){
                /*creacion de detalle*/
                ad = new ArticulosDocumento();
                a = (Articulo) tabla.getValueAt(i,0);
                ad.setArticulo(a);
                ad.setDocumento(d);
                ad.setIva(new BigDecimal(Constantes.IVA));
                ad.setCantidad(new BigDecimal((tabla.getValueAt(i,2)+"").replaceAll(",", "")));
                ad.setVlrunitario(new BigDecimal((tabla.getValueAt(i,3)+"").replaceAll(",", "")));
                ad.setVlrparcial(new BigDecimal((tabla.getValueAt(i,4)+"").replaceAll(",", "")));
                
                m.insertarRegistro("insertarArticulosDocumento", ad);
                
                
                /*aumentando existencia de articulo si  son los saldo*/
                if(estado.equals(Constantes.ESTADO_DOCUMENTO_INICIADO)){

                     a.setExistencia(new BigDecimal(tabla.getValueAt(i,2)+""));
                     a.setSaldocosto(ad.getVlrparcial());
                     a.setVlrpromedio(ad.getVlrunitario());
                     a.setVlrcosto(ad.getVlrunitario());
                     m.actualizarRegistro("actualizarArticuloExistencia", a);

                     Kardex k = new Kardex();
                     k.setDocumento(d);
                     k.setArticulo(a);
                     k.setEntradas(ad.getCantidad());
                     k.setSalidas(BigDecimal.ZERO);
                     k.setExistencia(ad.getCantidad());
                     k.setVlrunitario(ad.getVlrunitario());
                     k.setVlrtotal(ad.getVlrparcial());
                     k.setFecha(new Date());
                     k.setHora(new Date());
                     m.insertarRegistro("insertarKardex", k);

                }
                 a = null;
            }
            

    }


    public boolean validar() {
        if (tabla.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Adicione al menos un Articulo a la Venta");
            articulo.requestFocus();
            return false;
        }
        return true;
    }

    public void llenar() throws Exception {
        /*llenar tabla*/
        /*obtener articulosDocumentos (detalles)*/
        listaArticulos = (List<ArticulosDocumento>) m.obtenerListado("obtenerArticulosDocumento", d.getId())   ;
        Articulo a = null;
        for (ArticulosDocumento ad : listaArticulos) {
                Object fila[] = new Object[5];
                a = (Articulo) m.obtenerRegistro("obtenerArticuloPorId",ad.getArticulo().getId());
                fila[0] =  a;
                fila[1] =  a.getDescripcioncomercial();
                fila[2] = utilidades.FormatoNumeros.formatear( ad.getCantidad().toString() );
                fila[3 ]= utilidades.FormatoNumeros.formatear( ad.getVlrunitario().toString() );
                fila[4] = utilidades.FormatoNumeros.formatear( ad.getVlrparcial().toString() );
                mt.addRow(fila);
            }
        /*colocar total*/
        this.total.setText(utilidades.FormatoNumeros.formatear(d.getTotal().toString()));
        /*coloca r nota*/
        this.nota.setText(d.getNota());
    }

    public void adicionarItem() {

        int index = getFilaArticulo(a);
        BigDecimal acomu = new BigDecimal(this.total.getText().replaceAll(",",""));
        BigDecimal vpar  = new BigDecimal(this.vparcial.getText().replaceAll(",", ""));
        BigDecimal cant  = new BigDecimal(this.cantidad.getText().replaceAll(",", ""));
        if(index == -1){
                Object fila[] = new Object[5];
                fila[0] = this.a;
                fila[1] =  this.a.getDescripcioncomercial();
                fila[2] =  this.cantidad.getText();
                fila[3]=   this.vunit.getText();
                fila[4] = this.vparcial.getText();
                mt.addRow(fila);
        }else{
            /*validamos que no halla digitado un valor unitario distinto*/
            BigDecimal vunf  = new BigDecimal(tabla.getValueAt(index, 3).toString().replaceAll(",","")); /* valor unitario en la tabla*/
            BigDecimal vun  = new BigDecimal(this.vunit.getText().replaceAll(",","")); /*valor unitario nuevo*/
            BigDecimal cantf  = new BigDecimal(tabla.getValueAt(index,2).toString().replaceAll(",","")); /*cantidadque esta en la tabla*/
            BigDecimal vparf  = new BigDecimal(tabla.getValueAt(index,4).toString().replaceAll(",","")); /*calor parcial esta en la tabla*/
            Object option[] = {"Si","No"} ;
            if(vunf.compareTo(vun) != 0 && estado == interfaces.Constantes.ADICIONANDO_ITEMS){
                int op = JOptionPane.showOptionDialog(this, "¿Digitó un Valor Unitario nuevo, desea cambiarlo?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,option,option[0]);
                if(op  != JOptionPane.YES_OPTION)
                    vun = vunf;
            }
           acomu = acomu.subtract(vparf);

           if( estado == interfaces.Constantes.ADICIONANDO_ITEMS)
            cant = cant.add(cantf);
           else{
           
              setEstado(interfaces.Constantes.ADICIONANDO_ITEMS);
            }

           tabla.setValueAt(cant,index, 2);
           tabla.setValueAt(vun,index, 3);
           vpar = vun.multiply(cant);
           tabla.setValueAt(vpar,index, 4);
        }
        acomu =  acomu.add(vpar);
        this.total.setText(utilidades.FormatoNumeros.formatear( acomu + ""));

    }
    private void restaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restaurarActionPerformed
        // TODO add your handling code here:
        reiniciar();
    }//GEN-LAST:event_restaurarActionPerformed

    private void buscaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaraActionPerformed
        // TODO add your handling code here:
        buscarArticulo();
}//GEN-LAST:event_buscaraActionPerformed

    public void buscarArticulo(){
       Articulo art = new BuscaArticulo(parent,true,a).getArticulo();
      
        if(art!=null){
             a = art;
            this.articulo.setText(a.getCodigobarras());
            this.descripcion.setText(a.getDescripcioncomercial());
            this.cantidad.requestFocus();
        }
    }

    public void setEstado(int estado){
        this.estado = estado;
        if(estado == interfaces.Constantes.ADICIONANDO_ITEMS){
            this.buscara.setEnabled(true);;
            this.articulo.setEnabled(true);
            this.adicionar.setText("Adicionar");
        
        }else if(estado == interfaces.Constantes.EDITANDO_ITEMS){
                this.buscara.setEnabled(false);;
                this.articulo.setEnabled(false);
                this.adicionar.setText("Acepetar");
        }        
    }

    public void limpiarFormularioAdicion() {
        this.vparcial.setText("0.0");
        this.cantidad.setText("0.0");
        this.vunit.setText("0.0");
        this.descripcion.setText("");
        this.articulo.setText("");
        articulo.requestFocus();
        a =null;
    }
    public boolean validarArticulo(){
        
       BigDecimal vun = new BigDecimal( this.vunit.getText().replaceAll(",", ""));
       BigDecimal cant = new BigDecimal( this.cantidad.getText().replaceAll(",", ""));

       if(this.a==null){
           JOptionPane.showMessageDialog(this,"Selecione o escriba un articulo Valido","Inconveniente",JOptionPane.ERROR_MESSAGE);
           articulo.requestFocus();
           return false;
       }else
       if(cant.compareTo(new BigDecimal(0))==0){
           JOptionPane.showMessageDialog(this,"Digete una Cantidad mayor a 0","Inconveniente",JOptionPane.ERROR_MESSAGE);
           cantidad.requestFocus();
           return false;
       }else if(vun.compareTo(new BigDecimal(0))==0){
           JOptionPane.showMessageDialog(this,"Digete un Valor Unitario mayor a 0","Inconveniente",JOptionPane.ERROR_MESSAGE);
           vunit.requestFocus();
           return false;
       }
        
        return true;
    }

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed

            adicionar();
        // calcularDesceunto();
    }//GEN-LAST:event_adicionarActionPerformed

    public void adicionar(){
         try {
            // TODO add your handling code here:
            a = (Articulo) m.obtenerRegistro("obtenerArticuloPorCodigo", this.articulo.getText().trim());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (validarArticulo()){
                 calcularVlrParcial();
                 adicionarItem();
                 limpiarFormularioAdicion();
        }
    }

    private void quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarActionPerformed
        // TODO add your handling code here:
        if (tabla.getSelectedRow() > -1) {
            BigDecimal  totales = new BigDecimal(total.getText().replaceAll(",", ""));
            BigDecimal  totalaquitar = new BigDecimal(("" + tabla.getValueAt(tabla.getSelectedRow(), 4)).replaceAll(",", ""));
            if (totales.compareTo(totalaquitar) == -1 ) {
                totales.valueOf(0.0);
            } else {
                totales = totales.subtract(totalaquitar) ;
            }
            mt.removeRow(tabla.getSelectedRow());
            total.setText(utilidades.FormatoNumeros.formatear("" + totales));
            
            
        } else {
            JOptionPane.showMessageDialog(null, "No hay Articulo Seleccionado");
        }
}//GEN-LAST:event_quitarActionPerformed

    private void vunitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vunitActionPerformed
        // TODO add your handling code here:
         adicionar();
        calcularVlrParcial();

    }//GEN-LAST:event_vunitActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
        calcularVlrParcial();
        this.vunit.requestFocus();
}//GEN-LAST:event_cantidadActionPerformed

    private void cantidadMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cantidadMouseDragged
        // TODO add your handling code here:
}//GEN-LAST:event_cantidadMouseDragged

    private void articuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_articuloActionPerformed
        // TODO add your handling code here:

}//GEN-LAST:event_articuloActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        seleccionarFila(evt);

    }//GEN-LAST:event_tablaMouseClicked

      public void seleccionarFila(java.awt.event.MouseEvent me) {
        if (me.getClickCount() % 2 == 0) {
            if (tabla.rowAtPoint(me.getPoint()) > -1) {
                a = (Articulo) tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 0);
                this.articulo.setText( tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 0).toString());
                this.descripcion.setText( tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 1).toString());
                this.cantidad.setText( tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 2).toString());
                this.vunit.setText( tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 3).toString());
                this.vparcial.setText( tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 4).toString());
                setEstado(interfaces.Constantes.EDITANDO_ITEMS);
                
            }

        }
    }

    private void guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar1ActionPerformed
        // TODO add your handling code here:
        if (validar()) {
            try {                
                guardar(Constantes.ESTADO_DOCUMENTO_INICIADO);
                 JOptionPane.showMessageDialog(null,"Saldos Iniciados Con Éxito");
                 this.dispose();
            } catch (Exception ex) {
                Logger.getLogger(Formulario_Saldos_Iniciales.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_guardar1ActionPerformed

    private void vunitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vunitFocusLost
        // TODO add your handling code here:
       calcularVlrParcial();
    }//GEN-LAST:event_vunitFocusLost

    private void cantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cantidadFocusLost
        // TODO add your handling code here:
         calcularVlrParcial();
    }//GEN-LAST:event_cantidadFocusLost

    private void articuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_articuloKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_F1){
            buscarArticulo();
        }
    }//GEN-LAST:event_articuloKeyPressed

    private void cantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cantidadFocusGained
        // TODO add your handling code here:
             if(cantidad.getText().length()>0){
                    this.cantidad.setSelectionStart(0);
                    this.cantidad.setSelectionEnd(cantidad.getText().length());
                }
    }//GEN-LAST:event_cantidadFocusGained

    private void vunitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vunitFocusGained
        // TODO add your handling code here:
                if(this.vunit.getText().length()>0){
                    this.vunit.setSelectionStart(0);
                    this.vunit.setSelectionEnd(vunit.getText().length());
                }
    }//GEN-LAST:event_vunitFocusGained

    private void vunitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vunitKeyReleased
        // TODO add your handling code here:}
        calcularVlrParcial();
    }//GEN-LAST:event_vunitKeyReleased

    private void vunitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vunitKeyTyped
        // TODO add your handling code here:
          if(Character.isLetter(evt.getKeyChar()))
                    evt.consume();
    }//GEN-LAST:event_vunitKeyTyped


    public void calcularVlrParcial(){
        String canti = this.cantidad.getText();
        String unit = this.vunit.getText();
        if(this.cantidad.getText().trim().equals(""))
                canti = ("0.0");
        if(this.vunit.getText().trim().equals(""))
                unit = ("0.0");
        java.math.BigDecimal vun = new java.math.BigDecimal( unit.replaceAll(",", ""));
        java.math.BigDecimal cant = new java.math.BigDecimal( canti.replaceAll(",", ""));
        this.vparcial.setText(vun.multiply(cant).toString());
    }


    public void reiniciar() {
      Component c[] = this.getComponents();
      for(int i = 0; i<c.length; i++){
          if(c[i] instanceof JTextField)
              ((JTextField) c[i] ).setText("");
      }
      for(int i = 0; i<tabla.getRowCount(); i++)
      this.mt.removeRow(0);
      this.total.setText("0.0");
      nota.setText("");
    }



    public boolean esta(Object a) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            
            if ((tabla.getValueAt(i, 0) + "").equals(a + "")) {
                return true;
            }
        }
        return false;
    }
    
    /*retorna la posicion de el articulo si esta en el jtable , de lo contrario devielve -1*/
    public int getFilaArticulo(Object a) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            
            if ((tabla.getValueAt(i, 0) + "").equals(a + "")) {
                return i;
            }
        }
        return -1;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionar;
    private javax.swing.JTextField articulo;
    private javax.swing.JButton buscara;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField descripcion;
    private javax.swing.JButton guardar;
    private javax.swing.JButton guardar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nota;
    private javax.swing.JPanel panel;
    private javax.swing.JButton quitar;
    private javax.swing.JButton restaurar;
    private javax.swing.JButton salir;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel total;
    private javax.swing.JTextField vparcial;
    private javax.swing.JTextField vunit;
    // End of variables declaration//GEN-END:variables
}

