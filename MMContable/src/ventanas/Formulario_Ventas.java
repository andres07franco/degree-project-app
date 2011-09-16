package ventanas;

import interfaces.Buscadores;
import java.awt.Frame;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import db.Model;
import beans.*;
import interfaces.Constantes;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import utilidades.Calendario;
import utilidades.FormatoNumeros;
import utilidades.ModeloTabla;

public class Formulario_Ventas extends javax.swing.JDialog {

    Documento d;
    Tercero t;
    Articulo a;
    int funcion = Constantes.ESTADO_CREACION;
    Buscadores b;
    ModeloTabla mt = new ModeloTabla();
    DefaultTableModel mtabonos = new DefaultTableModel();
    Model m;
    Frame parent;
    private int estado;
    Caja cajaDia = null;

    public Formulario_Ventas(Frame parent, boolean modal, Buscadores b, Documento d) {

        super(parent, modal);
        this.m = Model.getInstance();
        this.parent = parent;
        this.b = b;
        this.d = d;
        
        estado = interfaces.Constantes.ADICIONANDO_ITEMS;
        initComponents();
        init();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        articulo.requestFocus();
    }

    @SuppressWarnings("SillyAssignment")
    public void init() {

                obtenerTerceroDefecto();
        /*validamos que se hallan configurado las propiedades de la factura*/
        obtentenerConsecutivo();
        tab.remove(panelabonos);
        setTitle(" Punto de Ventas ");
        this.setResizable(false);
        boolean cedit[] = {false, false, false, false, false};
        mt = new ModeloTabla(cedit);
        mt.addColumn("Codigo");
        mt.addColumn("Descripcion");
        mt.addColumn("Cantidad");
        mt.addColumn("Vlr Unit");
        mt.addColumn("Vlr Par");
        tabla.setModel(mt);

        utilidades.FormatoNumeros fn = new utilidades.FormatoNumeros(descuento);
        descuento.addKeyListener(fn);
        descuento.addFocusListener(fn);

        fn = new utilidades.FormatoNumeros(vunit);
        vunit.addKeyListener(fn);
        vunit.addFocusListener(fn);

        fn = new utilidades.FormatoNumeros(vparcial);
        vparcial.addKeyListener(fn);
        vparcial.addFocusListener(fn);

        a = null;
        articulo.setText("");
        descripcion.setText("");
        cantidad.setText("1");
        vunit.setText("0.00");
        vparcial.setText("0.00");
        total.setText("0.00");
        subtotal.setText("0.00");
        descuento.setText("0.00");
        articulo.requestFocus();
        articulo.setToolTipText("Ingrese CODIGO DE BARRAS o presione F1 para buscar el ARTICULO");
        tercero.setToolTipText("Ingrese NIT/CC o presione F1 para buscar el CLIENTE");

        buscafecha.setEnabled(false);
        imprimir.setEnabled(false);

        /*Verificanmos si se esta editando o creando*/
        if (d != null) {
            if(!d.getEstado().equals(Constantes.ESTADO_DOCUMENTO_ANULADO))
                        imprimir.setEnabled(true);

            funcion = Constantes.ESTADO_SOLO_LECTURA;
            if (d.getTipo().getId() == Constantes.DOCUMENTO_COTIZACION) {
                buscafecha.setVisible(true);
                this.tipod.setSelectedIndex(1);
                funcion = Constantes.ESTADO_EDICION;
                tipod.setSelectedIndex(1);
                restaurar.setEnabled(true);
                descuento.setEnabled(true);
                
            } else {
                this.tipod.setSelectedIndex(0); /*se selcciona factura*/
                this.tipod.setEnabled(false); /*no se puede cambiar el tipod e documento*/
                buscafecha.setVisible(false);  /*no se puede buscar fecha*/
                this.buscaproveedor.setEnabled(false); /*no se puede cambiar provedor*/
                this.ntercero.setEditable(false);
                this.buscara.setEnabled(false); /*no se puede adiconar articulos*/
                this.adicionar.setEnabled(false);
                this.articulo.setEnabled(false);
                this.cantidad.setEditable(false);
                restaurar.setEnabled(false);
                guardar.setEnabled(false);
                this.quitar.setEnabled(false);
                this.tercero.setEditable(false);
                this.nota.setEditable(false);
                this.tipodescuento.setSelectedIndex(1);
                this.tipodescuento.setEnabled(false);
            }
            llenar();
        }


        /*Calculamos FECHA actual*/
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);
        fecha.setText(ano + "-" + mes + "-" + dia);


    }


    /*obtener TERCERO por defecto*/
    public void obtenerTerceroDefecto() {
        /*ASIGANMOS TERCERO POR DEFECTO EN CASO DE QUE SE ETSA CREANDO EL DOCUMENTO*/
        if (this.d != null) {
            t = this.d.getTercero();
            tercero.setText(t.getNombre());
            ntercero.setText(t.getNombre());
        } else {
            try {
                t = (Tercero) m.obtenerRegistro("obtenerTerceroPorId", Constantes.TERCERO_POR_DEFECTO);
                tercero.setText(t.getNombre());
                ntercero.setText(t.getNombre());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "no se ha establecido un cliente por mostrador, no puede hacer esta transaccion");
                guardar.setEnabled(false);
                imprimir.setEnabled(false);
            }


        }
    }

    /*obtiene el consecutivo del documeto*/
    public void obtentenerConsecutivo() {
        FacturaEmpresa fe = null;
        try {
            fe = (FacturaEmpresa) m.obtenerRegistro("obtenerFacturaEmpresaActual");
        } catch (Exception ex) {
            Logger.getLogger(Formulario_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fe != null) {
            long consecutivo = 0;
            if (tipod.getSelectedIndex() == 0) {
                consecutivo = fe.getNumeroActual() + 1;
            } else {
                if (funcion != Constantes.ESTADO_EDICION) {
                    consecutivo = fe.getCotizaciones() + 1;
                } else {
                    consecutivo = Integer.parseInt(d.getNumero());
                }
            }
            numero.setText(consecutivo + "");
        } else {
            JOptionPane.showMessageDialog(null, "no se ha Configuarado las propiedades de la factura,  no puede hacer esta transaccion");
            guardar.setEnabled(false);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        fecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
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
        subtotal = new javax.swing.JTextField();
        descuento = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tipodescuento = new javax.swing.JComboBox();
        errordescuento = new javax.swing.JLabel();
        estadofactura = new javax.swing.JLabel();
        panelabonos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaabonos = new javax.swing.JTable();
        totalpagado = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        saldo = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        buscafecha = new javax.swing.JButton();
        tercero = new javax.swing.JTextField();
        buscaproveedor = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ntercero = new javax.swing.JTextField();
        tipod = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        restaurar = new javax.swing.JButton();
        imprimir = new javax.swing.JButton();
        anular = new javax.swing.JButton();

        setTitle("Ventas");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel2.setBackground(new java.awt.Color(212, 233, 255));

        fecha.setEditable(false);

        jLabel4.setText("Fecha");

        numero.setEditable(false);
        numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroActionPerformed(evt);
            }
        });
        numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroKeyTyped(evt);
            }
        });

        jLabel2.setText("Número");

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

        panel.setBackground(new java.awt.Color(153, 205, 255));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código ", "Descripción", "Cantidad", "Vlr Unit", "Vlr Par"
            }
        ));
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                articuloKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                articuloKeyTyped(evt);
            }
        });

        jLabel3.setText("Código ");

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });
        cantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cantidadFocusGained(evt);
            }
        });
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadKeyTyped(evt);
            }
        });

        jLabel5.setText("Cantidad");

        vunit.setEnabled(false);
        vunit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vunitActionPerformed(evt);
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
        total.setForeground(new java.awt.Color(204, 51, 0));
        total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total.setText("0.00");

        jLabel11.setText("Nota");

        descripcion.setEditable(false);

        jLabel9.setText("Descripción Comercial");

        subtotal.setEditable(false);
        subtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        subtotal.setText("0.00");

        descuento.setEditable(false);
        descuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuento.setText("0.00");
        descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoActionPerformed(evt);
            }
        });
        descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descuentoKeyTyped(evt);
            }
        });

        jLabel13.setText("Subtotal");

        jLabel14.setText("Descuento");

        tipodescuento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "%", "$" }));
        tipodescuento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipodescuentoItemStateChanged(evt);
            }
        });

        errordescuento.setForeground(java.awt.Color.red);
        errordescuento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        estadofactura.setFont(new java.awt.Font("Tahoma", 3, 11));
        estadofactura.setForeground(java.awt.Color.red);
        estadofactura.setText("     ");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(84, 84, 84))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(vunit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vparcial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nota, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(estadofactura, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel13))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(errordescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tipodescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(descuento, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(subtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(total)
                                .addComponent(jLabel7))
                            .addComponent(jLabel3)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vunit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vparcial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adicionar)
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buscara))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(estadofactura, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(tipodescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errordescuento))
                .addContainerGap())
        );

        tab.addTab("Detalles", panel);

        panelabonos.setBackground(new java.awt.Color(212, 233, 255));

        tablaabonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Valor"
            }
        ));
        jScrollPane2.setViewportView(tablaabonos);

        totalpagado.setFont(new java.awt.Font("Tahoma", 1, 14));
        totalpagado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalpagado.setText("0.00");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel16.setText("Total");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel15.setText("Saldo");

        saldo.setFont(new java.awt.Font("Tahoma", 1, 18));
        saldo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        saldo.setText("0.00");

        javax.swing.GroupLayout panelabonosLayout = new javax.swing.GroupLayout(panelabonos);
        panelabonos.setLayout(panelabonosLayout);
        panelabonosLayout.setHorizontalGroup(
            panelabonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelabonosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelabonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelabonosLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelabonosLayout.createSequentialGroup()
                        .addGap(575, 575, 575)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalpagado, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelabonosLayout.setVerticalGroup(
            panelabonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelabonosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelabonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(saldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelabonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalpagado)
                    .addComponent(jLabel16))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tab.addTab("Abonos", panelabonos);

        guardar.setBackground(new java.awt.Color(0, 153, 255));
        guardar.setFont(new java.awt.Font("Tahoma", 1, 11));
        guardar.setForeground(new java.awt.Color(0, 51, 153));
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        guardar.setText("Vender");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        buscafecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Calendar-16x16.png"))); // NOI18N
        buscafecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscafechaActionPerformed(evt);
            }
        });

        tercero.setEditable(false);
        tercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terceroActionPerformed(evt);
            }
        });
        tercero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                terceroKeyPressed(evt);
            }
        });

        buscaproveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-16x16.png"))); // NOI18N
        buscaproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaproveedorActionPerformed(evt);
            }
        });

        jLabel10.setText("Cliente");

        ntercero.setEditable(false);

        tipod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Cotizacion" }));
        tipod.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipodItemStateChanged(evt);
            }
        });
        tipod.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tipodInputMethodTextChanged(evt);
            }
        });

        jLabel12.setText("Tipo de Documento");

        restaurar.setBackground(new java.awt.Color(0, 155, 255));
        restaurar.setFont(new java.awt.Font("Tahoma", 1, 11));
        restaurar.setForeground(new java.awt.Color(0, 52, 153));
        restaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/undo-016.png"))); // NOI18N
        restaurar.setText("Restaurar");
        restaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restaurarActionPerformed(evt);
            }
        });

        imprimir.setBackground(new java.awt.Color(0, 153, 255));
        imprimir.setFont(new java.awt.Font("Tahoma", 1, 11));
        imprimir.setForeground(new java.awt.Color(0, 51, 153));
        imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/print-16x16.png"))); // NOI18N
        imprimir.setText("Imprimir");
        imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirActionPerformed(evt);
            }
        });

        anular.setBackground(new java.awt.Color(0, 153, 255));
        anular.setFont(new java.awt.Font("Tahoma", 1, 11));
        anular.setForeground(new java.awt.Color(0, 51, 153));
        anular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/papelera-vacia-recycle-32x32.png"))); // NOI18N
        anular.setText("Anular");
        anular.setEnabled(false);
        anular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscafecha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(tipod, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tercero, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ntercero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscaproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(anular, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(restaurar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buscafecha)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tipod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel2)))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tercero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(ntercero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buscaproveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar)
                    .addComponent(restaurar)
                    .addComponent(salir)
                    .addComponent(imprimir)
                    .addComponent(anular, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );

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

    private void numeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
}//GEN-LAST:event_numeroKeyTyped

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        int confirmado = JOptionPane.showConfirmDialog(this, "Se borrara todo lo que ha hecho esta seguro de SALIR?", "¿Reinicar?", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            this.dispose();
        }

}//GEN-LAST:event_salirActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        if (funcion != Constantes.ESTADO_SOLO_LECTURA) {
            try {
                if (validar()) {
                    if (guardar()) {
                        JOptionPane.showMessageDialog(null, (this.tipod.getSelectedIndex() == 0 ? "FACTURA" : "COTIZACION") + " Guardada Con Exito");
                        b.buscar();
                        this.dispose();
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(Formulario_Saldos_Iniciales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}//GEN-LAST:event_guardarActionPerformed

    public boolean validar() throws Exception {
        if (tabla.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Adicione al menos un ARTÍCULO a la Venta");
            articulo.requestFocus();
            return false;
        } else if (tercero.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione un CLIENTE por favor");
            tercero.requestFocus();
            return false;
        } else if (t == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un CLIENTE por favor");
            tercero.requestFocus();
            return false;
        } else if (t.getId() != Constantes.TERCERO_POR_DEFECTO && (!(t.getNit() + "").equals(tercero.getText()))) {
            JOptionPane.showMessageDialog(null, "Seleccione un CLIENTE válido por favor");
            tercero.requestFocus();
            return false;

        }
        if (t.getId() == Constantes.TERCERO_POR_DEFECTO && (!(t.getNombre() + "").equals(tercero.getText()))) {
            JOptionPane.showMessageDialog(null, "Seleccione un CLIENTE válido por favor");
            tercero.requestFocus();
            return false;

        } else if (new BigDecimal(total.getText().replaceAll(",", "")).compareTo(this.calcularDescuentoMin()) == -1) {
            JOptionPane.showMessageDialog(null, "El DESCUENTO es muy alto");
            descuento.requestFocus();
            return false;
        } else if (this.tipod.getSelectedIndex() == 0) {
            if (!this.comprovarExitencia()) {
                JOptionPane.showMessageDialog(null, "Los Artículos en rojo tienen una CANTIDAD  mayor a la EXISTENCIA, Inconveniente");
                return false;
            }

        }
        return true;
    }

    public boolean guardar() throws Exception {
        /*CABECERA*/
        /*creacion de objeto documento y recoelccion de parametros*/

        if (d == null) /* si no se esta editando se crea*/ {
            this.d = new Documento();
        } else if (tipod.getSelectedIndex() == 0) {
            funcion = Constantes.ESTADO_CREACION; /*en aso de que secree una facuta aprtir de una cotizacion*/
            d.setId(null);
        }

        if (tipod.getSelectedIndex() == 0) {
            this.d.setTipo((TipoDocumento) m.obtenerRegistro("obtenerTipoDocumento", Constantes.DOCUMENTO_FACTURA_VENTA));
        } else {
            this.d.setTipo((TipoDocumento) m.obtenerRegistro("obtenerTipoDocumento", Constantes.DOCUMENTO_COTIZACION));
        }



        d.setFecha(new Date());
        d.setTercero(t);
        d.setNota(this.nota.getText());

        d.setTotal(new BigDecimal(this.total.getText().replaceAll(",", "")));
        d.setSubtotal(new BigDecimal(this.subtotal.getText().replaceAll(",", "")));
        d.setTipodescuento(new TipoDescuento());
        if (tipod.getSelectedIndex() == 1) /* si fue descuento por valor*/ {
            d.setDescuento(new BigDecimal(this.descuento.getText().replaceAll(",", "")));
        } else { /* si fue descuento por porcentaje*/
            d.setDescuento(d.getSubtotal().subtract(d.getTotal()));
        }

        if (d.getTipo().getId() == Constantes.DOCUMENTO_FACTURA_VENTA) {
            /*COBRANDO*/
            int cobrado = Formulario_Cobrar_Ventas.cobrar(this.parent, d);
            if (cobrado == Formulario_Cobrar_Ventas.CANCELADO) {
                return false;
            }
        } else {
            d.setFechavencimiento(new Date());
            d.setTipopago((TipoPago) m.obtenerRegistro("obtenerTipoPago", Constantes.TIPO_PAGO_PAGADO));
            d.setTotalpagado(new BigDecimal(this.total.getText().replaceAll(",", "")));
            d.setEstado(Constantes.ESTADO_DOCUMENTO_PAGADO);
        }

        if (funcion == Constantes.ESTADO_EDICION) { /*solo puede tomar estad de edicon si es una cotizacion*/
            m.actualizarRegistro("actualizarDocumento", d);
            m.borrarRegistro("borrarArticulosDocumento", d.getId());
        } else { /*solo entra aqui si es una factura*/
            /*asignando y aumentando consecutivo sengun sea el tipo de documento*/
            FacturaEmpresa fe = fe = (FacturaEmpresa) m.obtenerRegistro("obtenerFacturaEmpresaActual");
            long consecutivo = 0;
            if (tipod.getSelectedIndex() == 0) {
                consecutivo = fe.getNumeroActual() + 1;
            } else {
                consecutivo = fe.getCotizaciones() + 1;
            }
            d.setNumero("" + (consecutivo));
            m.insertarRegistro("insertarDocumento", d);
            if (tipod.getSelectedIndex() == 0) {
                fe.setNumeroActual((int) consecutivo);
            } else {
                fe.setCotizaciones(consecutivo);
            }
            m.insertarRegistro("actualizarFacturaEmpresa", fe);
        }
        /*DETALLES*/
        ArticulosDocumento ad = null;
        for (int i = 0; i < this.tabla.getRowCount(); i++) {

            /*creacion de detalle*/
            ad = new ArticulosDocumento();
            a = (Articulo) tabla.getValueAt(i, 0);
            ad.setArticulo(a);
            ad.setDocumento(d);
            ad.setIva(new BigDecimal(Constantes.IVA));
            ad.setCantidad(new BigDecimal((tabla.getValueAt(i, 2) + "").replaceAll(",", "")));
            ad.setVlrunitario(new BigDecimal((tabla.getValueAt(i, 3) + "").replaceAll(",", "")));
            ad.setVlrparcial(new BigDecimal((tabla.getValueAt(i, 4) + "").replaceAll(",", "")));
            m.insertarRegistro("insertarArticulosDocumento", ad);


            if (d.getTipo().getId() == Constantes.DOCUMENTO_FACTURA_VENTA) {
                /*diminuir existencias y calculando promedio ponderado*/
                a.setFechauventa(d.getFecha());
                a.setExistencia(a.getExistencia().subtract(new BigDecimal(tabla.getValueAt(i, 2) + "")));
                BigDecimal costoRestar = ad.getCantidad().multiply(a.getVlrpromedio());
                a.setSaldocosto(a.getSaldocosto().subtract(costoRestar));
                if (a.getExistencia().longValue() <= 0) {
                    a.setSaldocosto(BigDecimal.ZERO);
                    a.setVlrpromedio(BigDecimal.ZERO);
                } else {
                    a.setVlrpromedio(new BigDecimal(a.getSaldocosto().doubleValue() / a.getExistencia().doubleValue()));
                }
                m.actualizarRegistro("actualizarArticulo", a);

                /*creando kardex*/
                Kardex k = new Kardex();
                k.setDocumento(d);
                k.setArticulo(a);
                k.setEntradas(BigDecimal.ZERO);
                k.setSalidas(ad.getCantidad());
                k.setExistencia(a.getExistencia());
                k.setVlrunitario(a.getVlrpromedio());
                k.setVlrtotal(a.getSaldocosto());
                k.setHora(new Date());
                k.setFecha(new Date());
                m.insertarRegistro("insertarKardex", k);

            }
            a = null;
        }

        /*Creao abono si lo hizo*/
        if (d.getTipopago().getId() == Constantes.TIPO_PAGO_CREDITO) {
            if (d.getTotalpagado().compareTo(BigDecimal.ONE) == 1) {
                Documento abono = new Documento();
                abono.setNumero(d.getNumero());
                abono.setTercero(d.getTercero());
                abono.setTipo((TipoDocumento) m.obtenerRegistro("obtenerTipoDocumento", Constantes.DOCUMENTO_ABONO_A_FACTURA));
                abono.setTipopago((TipoPago) Model.getInstance().obtenerRegistro("obtenerTipoPago", Constantes.TIPO_PAGO_DEBITO));
                abono.setFecha(d.getFecha());
                abono.setFechavencimiento(new Date());
                abono.setTotalpagado(d.getTotalpagado());
                abono.setSubtotal(d.getTotalpagado());
                abono.setTotal(d.getTotalpagado());
                abono.setEstado(Constantes.ESTADO_DOCUMENTO_PAGADO);
                abono.setDescuento(BigDecimal.ZERO);
                abono.setNota("Abono a Factura " + d.getNumero());
                abono.setDocumento(d);
            }
        }
        if (d.getTipo().getId() != Constantes.DOCUMENTO_FACTURA_VENTA) {
            return true;
        }
        /*Actualizamos caja*/
        try {
            cajaDia = (Caja) m.obtenerRegistro("obtenerCajaDia");
            if (d.getTipopago().getId() == Constantes.TIPO_PAGO_DEBITO) {
                cajaDia.setSaldoactual(cajaDia.getSaldoactual().add(d.getTotal()));
                cajaDia.setVentasefectivo(cajaDia.getVentasefectivo().add(d.getTotal()));
            } else {
                BigDecimal ccto = d.getTotal().subtract(d.getTotalpagado());
                cajaDia.setVentascredito(cajaDia.getVentascredito().add(ccto));
                cajaDia.setSaldoactual(cajaDia.getSaldoactual().add(d.getTotalpagado()));
                cajaDia.setVentasefectivo(cajaDia.getVentasefectivo().add(d.getTotalpagado()));
                if(d.getTotalpagado().doubleValue()>0)
                    cajaDia.setAbonoventas(cajaDia.getAbonoventas().add(d.getTotalpagado()));
            }
            m.actualizarRegistro("actualizarCajaDia", cajaDia);
            int confirmado = JOptionPane.showConfirmDialog(this, "¿Desea imprimir la Factura?", "¿Imprimir?", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado) {
                imprimir(d.getNumero());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void llenar() {
        try {
            this.anular.setEnabled(true);
            /*CARGANDO CABECERA*/
            numero.setText(d.getNumero());
            fecha.setText(new java.text.SimpleDateFormat("yyyy-MM-dd").format(d.getFecha()));
            tercero.setText(d.getTercero().getNit() + "");
            ntercero.setText(d.getTercero().getNombre());
            descuento.setText(FormatoNumeros.formatear(d.getDescuento() + ""));
            total.setText(FormatoNumeros.formatear(d.getTotal() + ""));
            subtotal.setText(FormatoNumeros.formatear(d.getSubtotal() + ""));
            this.tipodescuento.setSelectedIndex(1);
            nota.setText(d.getNota());

            if (d.getTipo().getId() == Constantes.DOCUMENTO_FACTURA_VENTA) {
                if (d.getEstado().equals(Constantes.ESTADO_DOCUMENTO_PAGADO)) {
                    this.estadofactura.setText("Pagada");
                } else if (d.getEstado().equals(Constantes.ESTADO_DOCUMENTO_DEBE)){
                    this.estadofactura.setText("En Deuda");

                }else if (d.getEstado().equals(Constantes.ESTADO_DOCUMENTO_ANULADO)){
                    this.estadofactura.setText("Anulada");

                }


            } else {
                this.estadofactura.setText("");
            }

            /*CARGANDO DETALLES*/
            List<ArticulosDocumento> la = (List<ArticulosDocumento>) m.obtenerListado("obtenerArticulosDocumento", d.getId());
            if (la != null) {
                for (int i = 0; i < la.size(); i++) {
                    ArticulosDocumento ad = la.get(i);
                    Object fila[] = new Object[5];
                    Articulo art = (Articulo) m.obtenerRegistro("obtenerArticuloPorId", ad.getArticulo().getId());
                    fila[0] = art;
                    fila[1] = ad.getArticulo().getDescripcioncomercial();
                    fila[2] = ad.getCantidad();
                    fila[3] = FormatoNumeros.formatear(ad.getVlrunitario().toString());
                    fila[4] = FormatoNumeros.formatear(ad.getVlrparcial().toString());
                    mt.addRow(fila);
                }
            }

            if (d.getTipopago().getId() == Constantes.TIPO_PAGO_CREDITO) {

                boolean cedit[] = {false, false, false, false, false};
                mtabonos = new ModeloTabla(cedit);
                mtabonos.addColumn("Fecha");
                mtabonos.addColumn("Valor");
                totalpagado.setText(utilidades.FormatoNumeros.formatear(d.getTotalpagado() + ""));
                saldo.setText(utilidades.FormatoNumeros.formatear((d.getTotal().subtract(d.getTotalpagado()) + "")));
                List<Documento> labonos = (List<Documento>) m.obtenerListado("obtenerAbonos", d.getId());

                if (labonos != null) {
                    for (int i = 0; i < labonos.size(); i++) {
                        Documento abo = labonos.get(i);
                        mtabonos.addRow(new Object[]{abo.getFecha(), utilidades.FormatoNumeros.formatear(abo.getTotal() + "")});
                    }
                }
                tab.add("Abonos", panelabonos);
                tablaabonos.setModel(mtabonos);
            }


        } catch (Exception ex) {
            Logger.getLogger(Formulario_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Este meod comprueva si uno de los articulo que fue adiconad ha modficad s existencia en otra istacia del aplicativo*/
    public boolean comprovarExitencia() throws Exception {
        boolean sw = true;
        Vector tot = new Vector();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            Articulo ag = (Articulo) tabla.getValueAt(i, 0);
            BigDecimal canti = new BigDecimal(tabla.getValueAt(i, 2).toString()); /*cantidad del articulo en la grilla*/
            ag = (Articulo) m.obtenerRegistro("obtenerArticuloPorId", ag.getId());
            if (canti.compareTo(ag.getExistencia()) == 1) {
                tot.add(i);
                sw = false;
            }
            ag = null;
        }

        if (!sw) {
            int toteados[] = new int[tot.size()];
            for (int i = 0; i < tot.size(); i++) {
                toteados[i] = i;

            }
            utilidades.TablaRender tabre = new utilidades.TablaRender();
            tabre.setToteados(toteados);
            tabla.setDefaultRenderer(Object.class, tabre);

        }
        return sw;

    }

    private void quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarActionPerformed
        // TODO add your handling code here:
        if (tabla.getSelectedRow() > -1) {
            double totales = Double.parseDouble(total.getText().replaceAll(",", ""));
            double totalaquitar = Double.parseDouble(("" + tabla.getValueAt(tabla.getSelectedRow(), 4)).replaceAll(",", ""));
            if (totales < totalaquitar) {
                totales = 0.0;
            } else {
                totales = totales - totalaquitar;
            }
            mt.removeRow(tabla.getSelectedRow());
            total.setText(utilidades.FormatoNumeros.formatear("" + totales));
            subtotal.setText(utilidades.FormatoNumeros.formatear("" + totales));
            calcularDescuento();
            if (tabla.getRowCount() <= 0) {
                this.descuento.setEditable(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay Articulo Seleccionado");
        }
}//GEN-LAST:event_quitarActionPerformed

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        // TODO add your handling code here:
        adicionar();

    }//GEN-LAST:event_adicionarActionPerformed

    public void adicionar() {
        if(utilidades.Validaciones.esEntero(cantidad.getText()))
        if (validarArticulo()) {
            calcularVlrParcial();
            adicionarItem();
            calcularDescuento();
            limpiarFormularioAdicion();
            this.articulo.requestFocus();
        }
    }

    public void limpiarFormularioAdicion() {
        this.vparcial.setText("0.0");
        this.cantidad.setText("1");
        this.vunit.setText("0.0");
        this.descripcion.setText("");
        this.articulo.setText("");
        a = null;
    }

    public int getFilaArticulo(Object a) {
        for (int i = 0; i < tabla.getRowCount(); i++) {

            if ((tabla.getValueAt(i, 0) + "").equals(a + "")) {
                return i;
            }
        }
        return -1;
    }

    public void adicionarItem() {

        int index = getFilaArticulo(a);
        BigDecimal acomu = new BigDecimal(this.total.getText().replaceAll(",", ""));
        BigDecimal vpar = new BigDecimal(this.vparcial.getText().replaceAll(",", ""));
        BigDecimal cant = new BigDecimal(this.cantidad.getText().replaceAll(",", ""));
        if (index == -1) {
            Object fila[] = new Object[5];
            fila[0] = this.a;
            fila[1] = this.a.getDescripcioncomercial();
            fila[2] = this.cantidad.getText();
            fila[3] = this.vunit.getText();
            fila[4] = this.vparcial.getText();
            mt.addRow(fila);
        } else {
            /*validamos que no halla digitado un valor unitario distinto*/
            BigDecimal vunf = new BigDecimal(tabla.getValueAt(index, 3).toString().replaceAll(",", "")); /* valor unitario en la tabla*/
            BigDecimal vun = new BigDecimal(this.vunit.getText().replaceAll(",", "")); /*valor unitario nuevo*/
            BigDecimal cantf = new BigDecimal(tabla.getValueAt(index, 2).toString().replaceAll(",", "")); /*cantidadque esta en la tabla*/
            BigDecimal vparf = new BigDecimal(tabla.getValueAt(index, 4).toString().replaceAll(",", "")); /*calor parcial esta en la tabla*/
            Object option[] = {"Si", "No"};
            if (vunf.compareTo(vun) != 0 && estado == interfaces.Constantes.ADICIONANDO_ITEMS) {
                int op = JOptionPane.showOptionDialog(this, "Digito un Valor Unitario nuevo, desea cambiarlor?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
                if (op != JOptionPane.YES_OPTION) {
                    vun = vunf;
                }
            }
            acomu = acomu.subtract(vparf);

            if (estado == interfaces.Constantes.ADICIONANDO_ITEMS) {
                cant = cant.add(cantf);
            } else {

                setEstado(interfaces.Constantes.ADICIONANDO_ITEMS);
            }

            tabla.setValueAt(cant, index, 2);
            tabla.setValueAt(vun, index, 3);
            vpar = vun.multiply(cant);
            tabla.setValueAt(vpar, index, 4);
        }
        acomu = acomu.add(vpar);
        this.total.setText(utilidades.FormatoNumeros.formatear(acomu + ""));
        this.subtotal.setText(total.getText());
        this.descuento.setEditable(true);
        this.descuento.setText("0.00");


    }

    public void setEstado(int estado) {
        this.estado = estado;
        if (estado == interfaces.Constantes.ADICIONANDO_ITEMS) {
            this.buscara.setEnabled(true);
            ;
            this.articulo.setEnabled(true);
            this.adicionar.setText("Adicionar");

        } else if (estado == interfaces.Constantes.EDITANDO_ITEMS) {
            this.buscara.setEnabled(false);
            ;
            this.articulo.setEnabled(false);
            this.adicionar.setText("Acpetar");
        }
    }

    public void calcularVlrParcial() {

        if(utilidades.Validaciones.esEntero(cantidad.getText())){
            String canti = this.cantidad.getText();
            if (this.cantidad.getText().trim().equals("")) {
                canti = "0.0";
            }
            BigDecimal vun = new BigDecimal(this.vunit.getText().replaceAll(",", ""));
            BigDecimal cant = new BigDecimal(canti.replaceAll(",", ""));
            this.vparcial.setText(FormatoNumeros.formatear(vun.multiply(cant).toString()));
        }
    }

    public boolean validarArticulo() {

        BigDecimal vun = new BigDecimal(this.vunit.getText().replaceAll(",", ""));
        BigDecimal cant = new BigDecimal(this.cantidad.getText().replaceAll(",", ""));
        try {
            a = (Articulo) m.obtenerRegistro("obtenerArticuloPorCodigo", this.articulo.getText());
        } catch (Exception ex) {
            Logger.getLogger(Formulario_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.a == null) {
            JOptionPane.showMessageDialog(this, "Selecione o escriba un articulo Valido", "Inconveniente", JOptionPane.ERROR_MESSAGE);
            articulo.requestFocus();
            return false;
        } else if (cant.compareTo(new BigDecimal(0)) == 0) {
            JOptionPane.showMessageDialog(this, "Digete una Cantidad mayor a 0", "Inconveniente", JOptionPane.ERROR_MESSAGE);
            cantidad.requestFocus();
            return false;
        } else if (vun.compareTo(new BigDecimal(0)) == 0) {
            JOptionPane.showMessageDialog(this, "Digete un Valor Unitario mayor a 0", "Inconveniente", JOptionPane.ERROR_MESSAGE);
            vunit.requestFocus();
            return false;
        } else {
            int index = this.getFilaArticulo(a);
            if (index > -1 && estado != interfaces.Constantes.EDITANDO_ITEMS) {
                cant = cant.add(new BigDecimal(tabla.getValueAt(index, 2).toString()));
            }

            if (cant.compareTo(a.getExistencia()) == 1 && this.tipod.getSelectedIndex() == 0) { /*cantidad ingresada mayor qu ela existencia*/
                JOptionPane.showMessageDialog(this, "La CANTIDAD (" + cant + ")  ingresada supera la EXISTENCIA (" + a.getExistencia() + ")de el ARTICULO ", "Inconveniente", JOptionPane.ERROR_MESSAGE);
                cantidad.requestFocus();
                return false;
            } else if (cant.compareTo(a.getExistencia()) == 0 && this.tipod.getSelectedIndex() == 0) {

                int confirmado = JOptionPane.showConfirmDialog(this, "La CANTIDAD (" + cant + ") ingresada deja la EXISTENCIA (" + a.getExistencia() + ") del ARTICULO en 0, Desea continuar?", "¿Desea Continuar?", JOptionPane.YES_NO_OPTION);

                if (JOptionPane.OK_OPTION == confirmado) {
                    return true;
                } else {
                    cantidad.requestFocus();
                    return false;
                }
            }
        }
        return true;
    }

    private void buscaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaraActionPerformed
        // TODO add your handling code here:
        this.buscarArticulo();
}//GEN-LAST:event_buscaraActionPerformed

    public void buscarArticulo() {
        Articulo art = new BuscaArticulo(parent, true, a).getArticulo(); 
        if (art != null) {
            a = art;
            /*validamos existencias del articlo*/
            if (a.getExistencia().compareTo(new BigDecimal("0.0")) > 0) {
                this.articulo.setText(a.getCodigobarras());
                this.descripcion.setText(a.getDescripcioncomercial());
                this.cantidad.setText("1");

                BigDecimal pr2 = a.getPorcentajesugerido().divide(new BigDecimal(100));
                BigDecimal vlrmsu = a.getVlrpromedio().multiply(pr2);
                vlrmsu = vlrmsu.add(a.getVlrpromedio());
                this.vunit.setText(utilidades.FormatoNumeros.formatear(vlrmsu.toString()));
                this.vparcial.setText(utilidades.FormatoNumeros.formatear(vlrmsu.toString()));
                this.cantidad.setToolTipText(a.getExistencia() + " EXISTENCIAS");

                if (cantidad.getText().length() > 0) {
                    this.cantidad.setSelectionStart(0);
                    this.cantidad.setSelectionEnd(cantidad.getText().length());
                }
                this.cantidad.requestFocus();

            } else {

                JOptionPane.showMessageDialog(this, "El Articulo " + a.getDescripcioncomercial() + " tiene Existencias en 0", "Inconveniente", JOptionPane.ERROR_MESSAGE);
                a = null;
            }

        }
    }

    private void buscafechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscafechaActionPerformed
        // TODO add your handling code here:
        fecha.setText(new Calendario(new Frame(), true).getFecha());
        numero.requestFocus();
}//GEN-LAST:event_buscafechaActionPerformed

    private void articuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_articuloActionPerformed
        // TODO add your handling code here:
        this.buscarArticulo();
    }//GEN-LAST:event_articuloActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_cantidadActionPerformed

    private void buscaproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaproveedorActionPerformed
        // TODO add your handling code here:
        adicionarTercero();
    }//GEN-LAST:event_buscaproveedorActionPerformed

    public void adicionarTercero() {
        Tercero ter = (new BuscaTerceros(parent, true, interfaces.Constantes.CLIENTE).getTercero());
        if (ter != null) {
            t = ter;
            tercero.setText(t.getNit() + "");
            if(t.getNit().intValue()==Constantes.TERCERO_POR_DEFECTO)
                tercero.setText(t.getNombre());
            ntercero.setText(t.getNombre());
        }
    }
    private void numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroActionPerformed
        // TODO add your handling code here:
        tercero.requestFocus();
    }//GEN-LAST:event_numeroActionPerformed

    private void terceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terceroActionPerformed
    }//GEN-LAST:event_terceroActionPerformed

    private void vunitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vunitActionPerformed
        // TODO add your handling code here:
        adicionar();
        calcularVlrParcial();

    }//GEN-LAST:event_vunitActionPerformed

    private void restaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restaurarActionPerformed
        // TODO add your handling code here:
        int confirmado = JOptionPane.showConfirmDialog(this, "Se borrara todo lo que ha hecho esta seguro de REINICIAR?", "¿Reinicar?", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            init();
        }
    }//GEN-LAST:event_restaurarActionPerformed

    private void descuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyReleased
        // TODO add your handling code here:
        calcularDescuento();

    }//GEN-LAST:event_descuentoKeyReleased

    public BigDecimal calcularDescuentoMin() {

        BigDecimal sum = new BigDecimal("0.0");

        for (int i = 0; i < tabla.getRowCount(); i++) {

            Articulo art = (Articulo) tabla.getValueAt(i, 0);

            BigDecimal pr2 = art.getPorcentajeminimo().divide(new BigDecimal(100));
            BigDecimal vlrmin = art.getVlrpromedio().multiply(pr2); // se calcula el porcentaje qu se suma al cosot promedio
            System.out.println(vlrmin);
            vlrmin = vlrmin.add(art.getVlrpromedio()); //se suma el valor del porcentaje al valor dde coto promedo
            System.out.println(vlrmin);
            vlrmin = vlrmin.multiply(new BigDecimal(tabla.getValueAt(i, 2).toString().replaceAll(",", "")));//se multiplica por la cantidad
            System.out.println(vlrmin);
            vlrmin = new BigDecimal(tabla.getValueAt(i, 4).toString().replaceAll(",", "")).subtract(vlrmin);
            System.out.println(vlrmin);
            sum = sum.add(vlrmin);
            System.out.println(art + " promedio=" + art.getVlrpromedio() + " pocentajemi=" + art.getPorcentajeminimo() + " valor calculado=" + vlrmin);
        }
        return sum;
    }

    public void calcularDescuento() {
        /*obtenemos que tipodescuanto hace, porcetaje o valor*/
        int index = this.tipodescuento.getSelectedIndex();
        BigDecimal des = BigDecimal.ZERO;
        if (!this.descuento.getText().trim().equals("")) { /*validamos que halla clocad algo*/
            des = new BigDecimal(this.descuento.getText().replaceAll(",", ""));
        }


        BigDecimal tot = new BigDecimal(this.subtotal.getText().replaceAll(",", ""));
        BigDecimal desmin = this.calcularDescuentoMin();// descuento minimo de la venta

        if (index == 0) {
            /**si me dio el descuento en porcentaje se convierte a valor*/
            /*des = des.divide(new BigDecimal("100"));
            des = tot.multiply(des);*/
            desmin = new BigDecimal("" + (desmin.multiply(new BigDecimal("100")).doubleValue() / tot.doubleValue()));
        }


        if (desmin.compareTo(des) == -1) {
            this.errordescuento.setText("No puede hacer un DESCUENTO menor " + (index == 1 ? "a $" : "al ") + utilidades.FormatoNumeros.formatear(desmin.toString()) + (index == 1 ? "" : "%"));
        } else {
            tot = tot.subtract(des);
            this.errordescuento.setText("El DESCUENTO máximo es " + (index == 1 ? "$" : "") + utilidades.FormatoNumeros.formatear(desmin.toString()) + (index == 1 ? "" : "%"));
            this.total.setText(utilidades.FormatoNumeros.formatear(tot.toString()));
        }


    }

    private void tipodescuentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipodescuentoItemStateChanged
        // TODO add your handling code here:
        calcularDescuento();
    }//GEN-LAST:event_tipodescuentoItemStateChanged

    private void tipodInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tipodInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tipodInputMethodTextChanged

    private void tipodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipodItemStateChanged
        // TODO add your handling code here:
        if (tipod.getSelectedIndex() == 0) {
            /*Calculamos FECHA actual*/
            int ano = Calendar.getInstance().get(Calendar.YEAR);
            int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int dia = Calendar.getInstance().get(Calendar.DATE);
            fecha.setText(ano + "-" + mes + "-" + dia);
            buscafecha.setEnabled(false);
            imprimir.setEnabled(false);
            this.guardar.setText("Vender");

        } else {
            buscafecha.setEnabled(true);
            imprimir.setEnabled(true);
            this.guardar.setText("Guardar");
        }
        obtentenerConsecutivo();
    }//GEN-LAST:event_tipodItemStateChanged

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        seleccionarFila(evt);
    }//GEN-LAST:event_tablaMouseClicked
    public void seleccionarFila(java.awt.event.MouseEvent me) {
        if (me.getClickCount() % 2 == 0) {
            if (tabla.rowAtPoint(me.getPoint()) > -1 && funcion != Constantes.ESTADO_SOLO_LECTURA) {
                a = (Articulo) tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 0);
                this.articulo.setText(tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 0).toString());
                this.descripcion.setText(tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 1).toString());
                this.cantidad.setText(tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 2).toString());
                this.vunit.setText(tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 3).toString());
                this.vparcial.setText(tabla.getValueAt(tabla.rowAtPoint(me.getPoint()), 4).toString());
                setEstado(interfaces.Constantes.EDITANDO_ITEMS);
                cantidad.requestFocus();
                if (cantidad.getText().length() > 0) {
                    this.cantidad.setSelectionStart(0);
                    this.cantidad.setSelectionEnd(cantidad.getText().length());
                }
            }

        }
    }

    public void imprimir(String numero) {
        Map parametro = new HashMap();
        parametro.put("titulo", "FACTURA DE VENTA");
        parametro.put("numero", numero);
        parametro.put("tipo", Constantes.DOCUMENTO_FACTURA_VENTA);
         this.dispose();
        new utilidades.Reporte().runReporte("reportes/Factura.jasper", parametro);
    }

    private void descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoActionPerformed

    private void imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirActionPerformed
        // TODO add your handling code here:
        imprimir(d.getNumero());
}//GEN-LAST:event_imprimirActionPerformed

    private void cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyReleased
        // TODO add your handling code here:
        this.calcularVlrParcial();
    }//GEN-LAST:event_cantidadKeyReleased

    private void cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyTyped
        // TODO add your handling code here:
        System.out.println(cantidad.getSelectionStart() + " - " + cantidad.getSelectionEnd());
        if(evt.getKeyChar()=='-'){
             if(cantidad.getSelectionStart()!=0 || (cantidad.getText().length()>cantidad.getText().replaceAll("-", "").length()) )
                evt.consume();
        }
        else if(!Character.isDigit(evt.getKeyChar())) {
                            evt.consume();
        }
    }//GEN-LAST:event_cantidadKeyTyped

    private void articuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_articuloKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
}//GEN-LAST:event_articuloKeyTyped

    private void articuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_articuloKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            buscarArticulo();
        }
    }//GEN-LAST:event_articuloKeyPressed

    private void terceroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_terceroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            adicionarTercero();
        }
    }//GEN-LAST:event_terceroKeyPressed

    private void descuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyTyped
        // TODO add your handling code here:
        calcularDescuento();
    }//GEN-LAST:event_descuentoKeyTyped

    private void articuloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_articuloKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_articuloKeyReleased

    private void cantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cantidadFocusGained
        // TODO add your handling code here:
        if (cantidad.getText().length() > 0) {
            this.cantidad.setSelectionStart(0);
            this.cantidad.setSelectionEnd(cantidad.getText().length());
        }
    }//GEN-LAST:event_cantidadFocusGained

    private void anularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anularActionPerformed

        // TODO add your handling code here:
       
        List<Documento> labonos = null;
        try {
            labonos = (List<Documento>) m.obtenerListado("obtenerAbonos", d.getId());
            cajaDia = (Caja) m.obtenerRegistro("obtenerCajaDia");
        } catch (Exception ex) {
            Logger.getLogger(Formulario_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(d.getFecha().getMonth() != new Date().getMonth() && d.getFecha().getYear()!= new Date().getYear()){
            JOptionPane.showMessageDialog(null, "No se puede anular la factura pasado el mes de creación de esta", "No se puede Anular", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (labonos == null) {
            JOptionPane.showMessageDialog(null, "No se puede anular la factura debido a que se han hecho varios movimientos con ella", "No se puede Anular", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(cajaDia.getSaldoactual().compareTo(d.getTotalpagado())==-1){
            JOptionPane.showMessageDialog(null, "No hay suficiente dinero en caja para anular la factura", "No se puede Anular", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<ArticulosDocumento> la = null;
        try {
             d.setEstado(interfaces.Constantes.ESTADO_DOCUMENTO_ANULADO);
            m.actualizarRegistro("actualizarDocumento", d);
            la = (List<ArticulosDocumento>) m.obtenerListado("obtenerArticulosDocumento", d.getId());

            if (la != null) {
                for (int i = 0; i < la.size(); i++) {

                    ArticulosDocumento ad = la.get(i);
                    Object[] fila = new Object[5];
                    Articulo a = (Articulo) m.obtenerRegistro("obtenerArticuloPorId", ad.getArticulo().getId());

                    a.setFechauventa(d.getFecha());
                    a.setExistencia(a.getExistencia().add(a.getExistencia()));
                    BigDecimal costoRestar = ad.getCantidad().multiply(a.getVlrpromedio());
                    a.setSaldocosto(a.getSaldocosto().add(costoRestar));
                    m.actualizarRegistro("actualizarArticulo", a);
                    /*creando kardex*/
                    Kardex k = new Kardex();
                    k.setDocumento(d);
                    k.setArticulo(a);
                    k.setEntradas(ad.getCantidad());
                    k.setSalidas(BigDecimal.ZERO);
                    k.setExistencia(ad.getCantidad());
                    k.setVlrunitario(a.getVlrpromedio());
                    k.setVlrtotal(a.getVlrpromedio().multiply(ad.getCantidad()));
                    k.setHora(new Date());
                    k.setFecha(new Date());
                    m.insertarRegistro("insertarKardex", k);

                }
            }

            if (d.getTipopago().getId() == Constantes.TIPO_PAGO_DEBITO) {
                cajaDia.setSaldoactual(cajaDia.getSaldoactual().subtract(d.getTotal()));
                cajaDia.setVentasefectivo(cajaDia.getVentasefectivo().subtract(d.getTotal()));
            } else {
                cajaDia.setVentascredito(cajaDia.getVentascredito().subtract(d.getTotal()));
                cajaDia.setSaldoactual(cajaDia.getSaldoactual().subtract(d.getTotalpagado()));
                cajaDia.setVentasefectivo(cajaDia.getVentasefectivo().subtract(d.getTotalpagado()));
            }
            m.actualizarRegistro("actualizarCajaDia", cajaDia);
            JOptionPane.showMessageDialog(parent, "Factura anulada con éxito");
            b.buscar();
            this.dispose();

        } catch (Exception ex) {
            Logger.getLogger(Formulario_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_anularActionPerformed

    public boolean esta(Object a) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            // System.out.println("validando" +tabla.getValueAt(i, 0)+" "+(a));
            if ((tabla.getValueAt(i, 0) + "").equals(a + "")) {
                return true;
            }
        }
        return false;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionar;
    private javax.swing.JButton anular;
    private javax.swing.JTextField articulo;
    private javax.swing.JButton buscafecha;
    private javax.swing.JButton buscaproveedor;
    private javax.swing.JButton buscara;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField descripcion;
    private javax.swing.JTextField descuento;
    private javax.swing.JLabel errordescuento;
    private javax.swing.JLabel estadofactura;
    private javax.swing.JTextField fecha;
    private javax.swing.JButton guardar;
    private javax.swing.JButton imprimir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nota;
    private javax.swing.JTextField ntercero;
    private javax.swing.JTextField numero;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelabonos;
    private javax.swing.JButton quitar;
    private javax.swing.JButton restaurar;
    private javax.swing.JLabel saldo;
    private javax.swing.JButton salir;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaabonos;
    private javax.swing.JTextField tercero;
    private javax.swing.JComboBox tipod;
    private javax.swing.JComboBox tipodescuento;
    private javax.swing.JLabel total;
    private javax.swing.JLabel totalpagado;
    private javax.swing.JTextField vparcial;
    private javax.swing.JTextField vunit;
    // End of variables declaration//GEN-END:variables
}
