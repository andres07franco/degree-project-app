package ventanas;

import beans.Articulo;
import beans.ArticulosDocumento;
import beans.Caja;
import beans.Documento;
import beans.Kardex;
import beans.Tercero;
import beans.TipoDocumento;
import db.Model;
import interfaces.Buscadores;
import interfaces.Constantes;
import java.awt.Frame;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilidades.Calendario;
import utilidades.FormatoNumeros;
import utilidades.ModeloTabla;

public class FormularioCompras extends javax.swing.JDialog {

    private Documento documento;
    private ArticulosDocumento articuloDocumento;
    private int funcion = Constantes.ESTADO_CREACION;
    private Buscadores buscador;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloTablaAbonos;
    private Model model;
    private Frame padre;
    Tercero t;
    private int estado;
    Articulo a;

    private void init() {
        
        tab.remove(panelabonos);

        boolean cedit[] = {false, false, false, false, false};

        modeloTabla = new ModeloTabla(cedit);
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Vlr Unit");
        modeloTabla.addColumn("Vlr Par");
        tabla.setModel(modeloTabla);

        utilidades.FormatoNumeros fn = new utilidades.FormatoNumeros(descuento);
        descuento.addKeyListener(fn);
        descuento.addFocusListener(fn);

        fn = new utilidades.FormatoNumeros(vunit);
        vunit.addKeyListener(fn);
        vunit.addFocusListener(fn);

        fn = new utilidades.FormatoNumeros(vparcial);
        vparcial.addKeyListener(fn);
        vparcial.addFocusListener(fn);

        /*Calculamos FECHA actual*/
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);
        fecha.setText(ano + "-" + mes + "-" + dia);

        articulo.setText("");
        descripcion.setText("");
        cantidad.setText("1");
        vunit.setText("0.00");
        vparcial.setText("0.00");
        numero.requestFocus();
    }

    public FormularioCompras(Frame padre, boolean modal, Buscadores buscador, Documento documento) {
        super(padre, modal);
        this.model = Model.getInstance();
        this.padre = padre;
                initComponents();
        init();
        this.documento = documento;
this.buscador = buscador;
        if (documento == null) {
            documento = new Documento();
        } else {
            llenar();
        }



        this.setSize(900, 580);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        fecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tab = new javax.swing.JTabbedPane();
        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        articulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
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
        descuento = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        subtotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        estadoDocumento = new javax.swing.JLabel();
        panelabonos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaabonos = new javax.swing.JTable();
        agregar = new javax.swing.JButton();
        totalpagado = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        saldo = new javax.swing.JLabel();
        buscafecha = new javax.swing.JButton();
        tercero = new javax.swing.JTextField();
        buscaproveedor = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ntercero = new javax.swing.JTextField();
        imprimir = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        restaurar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();

        setTitle("Compras");

        jPanel2.setBackground(new java.awt.Color(212, 233, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(800, 800));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 434));

        fecha.setEditable(false);

        jLabel4.setText("Fecha:");

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

        jLabel2.setText("Número:");

        jLabel1.setBackground(new java.awt.Color(0, 153, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));

        panel.setBackground(new java.awt.Color(153, 205, 255));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Cantidad", "Vlr Unit", "Vlr Par"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                articuloKeyTyped(evt);
            }
        });

        jLabel3.setText("Código:");

        jLabel5.setText("Cantidad:");

        vunit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vunitActionPerformed(evt);
            }
        });
        vunit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vunitFocusGained(evt);
            }
        });
        vunit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vunitKeyReleased(evt);
            }
        });

        jLabel6.setText("Vlr Unit:");

        vparcial.setEditable(false);

        jLabel7.setText("Vlr Par:");

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

        jLabel11.setText("Nota:");

        descripcion.setEditable(false);

        descuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuento.setText("0.00");
        descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descuentoKeyTyped(evt);
            }
        });

        jLabel14.setText("Descuento:");

        subtotal.setEditable(false);
        subtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel8.setText("Subtotal");

        jLabel9.setText("Descripción Comecial");

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
        });

        estadoDocumento.setFont(new java.awt.Font("Tahoma", 3, 11));
        estadoDocumento.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(156, 156, 156))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nota, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(estadoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                        .addComponent(jLabel8))
                                    .addComponent(jLabel14))
                                .addGap(14, 14, 14)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(descuento)
                                    .addComponent(subtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(buscara, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(vunit, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                        .addComponent(vparcial, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(17, 17, 17))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel7)
                        .addComponent(total))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(vparcial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(vunit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(adicionar))
                    .addComponent(buscara))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(estadoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Detalles", panel);

        panelabonos.setBackground(new java.awt.Color(153, 205, 255));

        tablaabonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Valor"
            }
        ));
        jScrollPane2.setViewportView(tablaabonos);

        agregar.setForeground(new java.awt.Color(0, 51, 153));
        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

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
                .addGroup(panelabonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelabonosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelabonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelabonosLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelabonosLayout.createSequentialGroup()
                                .addComponent(agregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 563, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalpagado, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addGroup(panelabonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelabonosLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(agregar))
                    .addGroup(panelabonosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelabonosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalpagado)
                            .addComponent(jLabel16))))
                .addContainerGap())
        );

        tab.addTab("Abonos", panelabonos);

        buscafecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Calendar-16x16.png"))); // NOI18N
        buscafecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscafechaActionPerformed(evt);
            }
        });

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

        jLabel10.setText("Proveedor:");

        ntercero.setEditable(false);

        imprimir.setBackground(new java.awt.Color(0, 152, 255));
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

        guardar.setBackground(new java.awt.Color(0, 153, 255));
        guardar.setFont(new java.awt.Font("Tahoma", 1, 11));
        guardar.setForeground(new java.awt.Color(0, 51, 153));
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ok.png"))); // NOI18N
        guardar.setText("Comprar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscafecha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tercero, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ntercero, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscaproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restaurar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(buscafecha)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(tercero)
                            .addComponent(ntercero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscaproveedor)))
                .addGap(23, 23, 23)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imprimir)
                    .addComponent(guardar)
                    .addComponent(restaurar)
                    .addComponent(salir))
                .addGap(222, 222, 222)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void restaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restaurarActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(this, "Se borrara todo lo que ha hecho esta seguro de REINICIAR?", "¿Reinicar?", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            init();
        }
}//GEN-LAST:event_restaurarActionPerformed

    private void buscaproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaproveedorActionPerformed
        adicionarTercero();
}//GEN-LAST:event_buscaproveedorActionPerformed

    public void adicionarTercero() {
        Tercero ter = (new BuscaTerceros(padre, true, interfaces.Constantes.PROVEESOR).getTercero());
       
        if (ter != null) {
            t = ter;
            tercero.setText(t.getNit() + "");
            ntercero.setText(t.getNombre());
            articulo.requestFocus();
        }
    }

    private void terceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terceroActionPerformed
        try {
            if (tercero.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite el nit del Tercero ");
            } else if (model.obtenerRegistro("existeTercero", Integer.parseInt(tercero.getText().trim())) != null) {
                documento.setTercero((Tercero) model.obtenerRegistro("obtenerTerceroPorId", model.obtenerRegistro("existeTercero", Integer.parseInt(tercero.getText().trim()))));
                tercero.setText(documento.getTercero().getNit() + "");
                ntercero.setText(documento.getTercero().getNombre());

                articulo.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Tercero no existe");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}//GEN-LAST:event_terceroActionPerformed

    private void buscafechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscafechaActionPerformed
        fecha.setText(new Calendario(new Frame(), true).getFecha());
        numero.requestFocus();
}//GEN-LAST:event_buscafechaActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {

            if (validar()) {

                if ((Integer) model.obtenerRegistro("existeDocumento", numero.getText()) == 0) {
                    if(guardar()){
                    JOptionPane.showMessageDialog(null,  "Compra guardada con Éxito");
                        this.buscador.buscar();
                        this.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo realizar la transacción");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Número de FACTURA de COMPRA ya existe");
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}//GEN-LAST:event_guardarActionPerformed

    private void descuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyReleased
        calcularDescuento();
}//GEN-LAST:event_descuentoKeyReleased

    private void buscaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaraActionPerformed
        buscarArticulo();
}//GEN-LAST:event_buscaraActionPerformed

    public void buscarArticulo() {
        Articulo art = new BuscaArticulo(padre, true, null).getArticulo();
        
        if (art != null) {
            a = art;
            articuloDocumento = new ArticulosDocumento();
            articuloDocumento.setArticulo(a);
            articulo.setText("" + articuloDocumento.getArticulo().getCodigobarras());
            descripcion.setText("" + articuloDocumento.getArticulo().getDescripcioncomercial());
            vunit.setText(utilidades.FormatoNumeros.formatear("" + articuloDocumento.getArticulo().getVlrpromedio()));
            cantidad.setText("1");
            this.calcularVlrParcial();
            cantidad.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un Artículo");
        }
    }
    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        adicionar();
}//GEN-LAST:event_adicionarActionPerformed

    public void limpiarFormularioAdicion() {
        this.vparcial.setText("0.0");
        this.cantidad.setText("1");
        this.vunit.setText("0.0");
        this.descripcion.setText("");
        this.articulo.setText("");

    }

    public void adicionar() {
        if (validarArticulo()) {
            calcularVlrParcial();
            adicionarItem();
            calcularDescuento();
            limpiarFormularioAdicion();
            this.articulo.requestFocus();
        }
    }

    public boolean validarArticulo() {
        BigDecimal vun = new BigDecimal(this.vunit.getText().replaceAll(",", ""));
        BigDecimal cant = new BigDecimal(this.cantidad.getText().replaceAll(",", ""));

        try {
            a = (Articulo) model.obtenerRegistro("obtenerArticuloPorCodigo", this.articulo.getText());

            if (a == null) {
                JOptionPane.showMessageDialog(this, "Seleccione o escriba un Artículo Válido", "Inconveniente", JOptionPane.ERROR_MESSAGE);
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
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
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

    public int getFilaArticulo(Object a) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if ((tabla.getValueAt(i, 0)).equals(a)) {
                return i;
            }
        }
        return -1;
    }

    private void quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarActionPerformed
        if (tabla.getSelectedRow() > -1) {
            BigDecimal totales = new BigDecimal(subtotal.getText().replaceAll(",", ""));
            BigDecimal totalaquitar = new BigDecimal(("" + tabla.getValueAt(tabla.getSelectedRow(), 4)).replaceAll(",", ""));

            totales = totales.subtract(totalaquitar);
            modeloTabla.removeRow(tabla.getSelectedRow());

            if (totales.compareTo(BigDecimal.ZERO) == 0) {
                subtotal.setText(utilidades.FormatoNumeros.formatear("0.00"));
            } else {
                subtotal.setText(utilidades.FormatoNumeros.formatear("" + totales));
            }

            calcularDescuento();
        } else {
            JOptionPane.showMessageDialog(null, "No hay Artículo Seleccionado");
        }
}//GEN-LAST:event_quitarActionPerformed

    private void vunitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vunitActionPerformed
        adicionar();
}//GEN-LAST:event_vunitActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        vunit.requestFocus();

}//GEN-LAST:event_cantidadActionPerformed

    public void calcularVlrParcial() {
        String canti = this.cantidad.getText();
        if (this.cantidad.getText().trim().equals("")) {
            canti = "0.0";
        }
        BigDecimal vun = new BigDecimal(this.vunit.getText().replaceAll(",", ""));
        BigDecimal cant = new BigDecimal(canti.replaceAll(",", ""));
        this.vparcial.setText(FormatoNumeros.formatear(vun.multiply(cant).toString()));
    }

    private void articuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_articuloActionPerformed
}//GEN-LAST:event_articuloActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
}//GEN-LAST:event_agregarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(this, "Se borrara todo lo que ha hecho esta seguro de SALIR?", "¿Reinicar?", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            this.dispose();
        }
}//GEN-LAST:event_salirActionPerformed

    private void numeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
}//GEN-LAST:event_numeroKeyTyped

    private void numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroActionPerformed
        tercero.requestFocus();
}//GEN-LAST:event_numeroActionPerformed

    private void terceroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_terceroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_F1) {
            adicionarTercero();
        }
    }//GEN-LAST:event_terceroKeyPressed

    private void cantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cantidadFocusGained
        // TODO add your handling code here:
        if (cantidad.getText().length() > 0) {
            this.cantidad.setSelectionStart(0);
            this.cantidad.setSelectionEnd(cantidad.getText().length());
        }
    }//GEN-LAST:event_cantidadFocusGained

    private void vunitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vunitFocusGained
        // TODO add your handling code here:
        if (vunit.getText().length() > 0) {
            this.vunit.setSelectionStart(0);
            this.vunit.setSelectionEnd(vunit.getText().length());
        }
    }//GEN-LAST:event_vunitFocusGained

    private void cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyReleased
        // TODO add your handling code here:
        calcularVlrParcial();
    }//GEN-LAST:event_cantidadKeyReleased

    private void vunitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vunitKeyReleased
        // TODO add your handling code here:
        calcularVlrParcial();
    }//GEN-LAST:event_vunitKeyReleased

    private void articuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_articuloKeyTyped
        // TODO add your handling code here:}
    }//GEN-LAST:event_articuloKeyTyped

    private void articuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_articuloKeyPressed
        // TODO add your handling code here:
        buscarArticulo();
    }//GEN-LAST:event_articuloKeyPressed

    private void descuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyTyped
        // TODO add your handling code here:
        calcularDescuento();
    }//GEN-LAST:event_descuentoKeyTyped

    private void imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirActionPerformed
        // TODO add your handling code here:
        if(documento!=null && funcion==Constantes.ESTADO_CREACION)
              imprimir(documento.getNumero());
    }//GEN-LAST:event_imprimirActionPerformed
    public void imprimir(String numero) {
        Map parametro = new HashMap();
        parametro.put("numero", numero);
        parametro.put("tipo", Constantes.DOCUMENTO_FACTURA_COMPRA);
        new utilidades.Reporte().runReporte("reportes/Factura.jasper", parametro);
    }
    public boolean guardar() {
        try {

            if (documento == null) {
                documento = new Documento();
            }
            documento.setNumero(numero.getText());
            documento.setFecha(new Date(fecha.getText().replaceAll("-", "/")));
            documento.setTotal(new BigDecimal(total.getText().replaceAll(",", "")));
            documento.setSubtotal(new BigDecimal(total.getText().replaceAll(",", "")).subtract(new BigDecimal(descuento.getText().replaceAll(",", ""))));
            documento.setNota(nota.getText());
            documento.setTipo(new TipoDocumento(Constantes.DOCUMENTO_FACTURA_COMPRA, null));
            documento.setTercero(t);
            documento.setFechavencimiento(new Date());
            if (!descuento.getText().trim().equals("")) {
                if (Double.parseDouble(descuento.getText().replaceAll(",", "")) >= 0) {
                    documento.setDescuento(new BigDecimal(descuento.getText().replaceAll(",", "")));
                }
            }

            if (FormularioCobrarCompra.cobrar(padre, documento, total.getText())) {

                model.insertarRegistro("insertarDocumento", documento);

                for (int i = 0; i < tabla.getRowCount(); i++) {
                    ArticulosDocumento ad = new ArticulosDocumento();
                    a = (Articulo) tabla.getValueAt(i, 0);
                    ad.setArticulo(a);
                    ad.setDocumento(documento);
                    ad.setIva(new BigDecimal(Constantes.IVA));
                    ad.setCantidad(new BigDecimal((tabla.getValueAt(i, 2) + "").replaceAll(",", "")));
                    ad.setVlrunitario(new BigDecimal((tabla.getValueAt(i, 3) + "").replaceAll(",", "")));
                    ad.setVlrparcial(new BigDecimal((tabla.getValueAt(i, 4) + "").replaceAll(",", "")));
                    model.insertarRegistro("insertarArticulosDocumento", ad);


                    /*claculando promedio ponderado y actualizando existencias*/
                    a.setFechaucompra(documento.getFecha());
                    a.setExistencia(a.getExistencia().add(ad.getCantidad()));
                    BigDecimal costoSumar = ad.getCantidad().multiply(ad.getVlrunitario());
                    a.setSaldocosto(a.getSaldocosto().add(costoSumar));
                    a.setVlrpromedio(new BigDecimal(a.getSaldocosto().doubleValue() / a.getExistencia().doubleValue()));

                    model.actualizarRegistro("actualizarArticulo", a);

                    /*movemos el kardex*/
                    Kardex kardex = new Kardex();
                    kardex.setArticulo(ad.getArticulo());
                    kardex.setDocumento(documento);
                    kardex.setSalidas(BigDecimal.ZERO);
                    kardex.setEntradas(ad.getCantidad());
                    kardex.setVlrunitario(a.getVlrpromedio());
                    kardex.setVlrtotal(a.getVlrpromedio().multiply(ad.getCantidad()));
                    kardex.setFecha(new Date());
                    kardex.setHora(new Date());
                    kardex.setExistencia(a.getExistencia());
                    model.insertarRegistro("insertarKardex", kardex);

                }

                Caja cajaDia = (Caja) model.obtenerRegistro("obtenerCajaDia");
                if (documento.getTipopago().getId() == Constantes.TIPO_PAGO_DEBITO) {
                    cajaDia.setSaldoactual(cajaDia.getSaldoactual().subtract(documento.getTotal()));
                    cajaDia.setComprasefectivo(cajaDia.getVentasefectivo().add(documento.getTotal()));

                } else if (documento.getTipopago().getId() == Constantes.TIPO_PAGO_CREDITO) {
                    cajaDia.setComprascredito(documento.getTotal().add(documento.getTotalpagado()));
                    cajaDia.setSaldoactual(cajaDia.getSaldoactual().subtract(documento.getTotalpagado()));
                    cajaDia.setComprasefectivo(cajaDia.getVentasefectivo().add(documento.getTotalpagado()));

                }
                model.actualizarRegistro("actualizarCajaDia", cajaDia);
              int confirmado = JOptionPane.showConfirmDialog(this, "¿Desea imprimir la Factura?", "¿Imprimir?", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado) {
                imprimir(documento.getNumero());
            }

            }else
                return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean validar() {
        if (numero.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el NÚMERO por favor");
            numero.requestFocus();
            return false;
        } else if (fecha.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la FECHA por favor");
            fecha.requestFocus();
            return false;
        } else if (tercero.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el PROVEEDOR por favor");
            tercero.requestFocus();
            return false;
        } else if (tabla.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Agregue  al menos un Artículo a la Compra");
            articulo.requestFocus();
            return false;
        } else if (t == null) {
            JOptionPane.showMessageDialog(null, "Digite un PROVEEDOR válido por favor");
            tercero.requestFocus();
            return false;
        } else if ((!(t.getNit() + "").equals(tercero.getText()))) {
            JOptionPane.showMessageDialog(null, "Digite un PROVEEDOR válido por favor");
            tercero.requestFocus();
            return false;
        }
        return true;
    }

    public void llenar() {
        try {
             
            numero.setText(documento.getNumero());
          
            fecha.setText(new java.text.SimpleDateFormat("yyyy-MM-dd").format(documento.getFecha()));
            tercero.setText(documento.getTercero().getNit() + "");
            ntercero.setText(documento.getTercero().getNombre());
            descuento.setText(documento.getDescuento() + "");
            List<ArticulosDocumento> listaArticulos = (List<ArticulosDocumento>) model.obtenerListado("obtenerArticulosDocumento", documento.getId());

            if (documento.getEstado().equals(Constantes.ESTADO_DOCUMENTO_DEBE)) {
                estadoDocumento.setText("En Deuda");
            } else {
                estadoDocumento.setText("Pagada");
                guardar.setEnabled(false);
                restaurar.setEnabled(false);
            }
            funcion = Constantes.ESTADO_EDICION;
            imprimir.setEnabled(true);
            if (documento.getDescuento() != null) {
                descuento.setText(utilidades.FormatoNumeros.formatear(documento.getDescuento() + ""));
            } else {
                descuento.setText("0.00");
            }

            total.setText(utilidades.FormatoNumeros.formatear(documento.getTotal() + ""));

            guardar.setEnabled(false);
            restaurar.setEnabled(false);

            if (listaArticulos != null) {
                for (int i = 0; i < listaArticulos.size(); i++) {
                    Articulo articulo1 = listaArticulos.get(i).getArticulo();
                    int cantidades = listaArticulos.get(i).getCantidad().intValue();
                    modeloTabla.addRow(new Object[]{listaArticulos.get(i), articulo1.getDescripcioncomercial(), utilidades.FormatoNumeros.formatearCantidades(cantidades + ""), utilidades.FormatoNumeros.formatear(listaArticulos.get(i).getVlrunitario() + ""), utilidades.FormatoNumeros.formatear(listaArticulos.get(i).getVlrparcial() + "")});
                }
            }

            modeloTablaAbonos = new DefaultTableModel();
            modeloTablaAbonos.addColumn("Fecha");
            modeloTablaAbonos.addColumn("Valor");
            if (documento.getTipopago().getId() == Constantes.TIPO_PAGO_CREDITO || documento.getTipopago().getId() == Constantes.TIPO_PAGO_PAGADO) {
                totalpagado.setText(utilidades.FormatoNumeros.formatear(documento.getTotalpagado() + ""));
                saldo.setText(utilidades.FormatoNumeros.formatear((documento.getTotal().subtract(documento.getTotalpagado())) + ""));

                if (documento.getEstado().equals(Constantes.ESTADO_DOCUMENTO_PAGADO)) {
                    agregar.setEnabled(false);
                }

                // TODO aqui va lo de los abonos.
//                if (documento.getAbonosList() != null) {
//                    for (int i = 0; i < documento.getAbonosList().size(); i++) {
//                        Abonos abo = documento.getAbonosList().get(i);
//                        modeloTablaAbonos.addRow(new Object[]{abo.getFecha(), utilidades.FormatoNumeros.formatear(abo + "")});
//                    }
//                }
                tab.add("Abonos", panelabonos);
                tablaabonos.setModel(modeloTablaAbonos);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            this.modeloTabla.addRow(fila);
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
        this.subtotal.setText(utilidades.FormatoNumeros.formatear(acomu.add(new BigDecimal(descuento.getText().replaceAll(",", ""))) + ""));
    }

    public void calcularDescuento() {
        /*obtenemos que tipodescuanto hace, porcetaje o valor*/
        BigDecimal des = BigDecimal.ZERO;
        if (!this.descuento.getText().trim().equals("")) { /*validamos que halla clocad algo*/
            des = new BigDecimal(this.descuento.getText().replaceAll(",", ""));
        }

        BigDecimal tot = new BigDecimal(this.subtotal.getText().replaceAll(",", ""));
        tot = tot.subtract(des);
        this.total.setText(utilidades.FormatoNumeros.formatear(tot.toString()));
    }

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
    private javax.swing.JButton agregar;
    private javax.swing.JTextField articulo;
    private javax.swing.JButton buscafecha;
    private javax.swing.JButton buscaproveedor;
    private javax.swing.JButton buscara;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField descripcion;
    private javax.swing.JTextField descuento;
    private javax.swing.JLabel estadoDocumento;
    private javax.swing.JTextField fecha;
    private javax.swing.JButton guardar;
    private javax.swing.JButton imprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JLabel total;
    private javax.swing.JLabel totalpagado;
    private javax.swing.JTextField vparcial;
    private javax.swing.JTextField vunit;
    // End of variables declaration//GEN-END:variables
}
