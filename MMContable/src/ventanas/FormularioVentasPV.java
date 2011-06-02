package ventanas;
/*
import beans.ArticulosDocumento;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.util.*;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import beans.Caja;
import beans.Documento;
import beans.FacturaEmpresa;
import beans.Tercero;
import db.Model;
import interfaces.Buscadores;
import interfaces.Constantes;
import utilidades.Calendario;

public class FormularioVentasPV extends javax.swing.JInternalFrame {

    private Documento documento;
    private ArticulosDocumento articulosDocumento;
    private int funcion = Constantes.FORMULARIO_NO_APTO_PARA_GUARDAR;
    private Buscadores buscador;
    private DefaultTableModel modeloTabla;
    private JTabbedPane pestana;
    private JDesktopPane desktopPane;
    private Model modelo;
    private Frame padre;
    private Tercero clienteMostrador;

    public FormularioVentasPV(JTabbedPane pestana, JDesktopPane desktopPane, Frame padre) throws Exception {
        this.modeloTabla = new DefaultTableModel();
        this.modelo = Model.getInstance();
        this.padre = padre;
        this.pestana = pestana;
        this.desktopPane = desktopPane;

        documento = new Documento();
        init();

        clienteMostrador = (Tercero) modelo.obtenerRegistro("obtenerClientePorMostrador", modelo.getUser().getId());

        if (clienteMostrador != null) {
            documento.setTercero(clienteMostrador);
            tercero.setText(clienteMostrador.getNombre());
            ntercero.setText(clienteMostrador.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "No se ha establecido un cliente por mostrador");
            guardar.setEnabled(false);
        }

        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);

        fecha.setText(ano + "-" + mes + "-" + dia);

        FacturaEmpresa facturaEmpresa = (FacturaEmpresa) modelo.obtenerRegistro("obtenerFacturaEmpresa", clienteMostrador.getEmpresa().getId());

        if (facturaEmpresa != null) {
            int consecutivo = facturaEmpresa.getNumeroActual() + 1;
            numero.setText(consecutivo + "");
        } else {
            JOptionPane.showMessageDialog(null, "NO se han Configurado las Propiedades de la Factura");
            guardar.setEnabled(false);
        }

        this.setLocation(pestana.getWidth() / 2 - this.getWidth() / 2, 0);
        this.setVisible(true);
        articulo.requestFocus();
    }

    private void init() {
        initComponents();
        setTitle(" Punto de Ventas ");
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Vlr Unit");
        modeloTabla.addColumn("Vlr Par");
        tabla.setModel(modeloTabla);

        utilidades.FormatoNumeros formatoNumeros = new utilidades.FormatoNumeros(descuento);
        descuento.addKeyListener(formatoNumeros);
        descuento.addFocusListener(formatoNumeros);

        formatoNumeros = new utilidades.FormatoNumeros(vunit);
        vunit.addKeyListener(formatoNumeros);
        vunit.addFocusListener(formatoNumeros);

        formatoNumeros = new utilidades.FormatoNumeros(vparcial);
        vparcial.addKeyListener(formatoNumeros);
        vparcial.addFocusListener(formatoNumeros);

        utilidades.FormatoCantidades formatoCantidades = new utilidades.FormatoCantidades(cantidad);
        cantidad.addKeyListener(formatoCantidades);
        cantidad.addFocusListener(formatoCantidades);

        adicionar.setEnabled(false);
        articulo.setText("");
        descripcion.setText("");
        cantidad.setText("1");
        vunit.setText("0.00");
        vparcial.setText("0.00");
    }

    public FormularioVentasPV(JTabbedPane pestana, JDesktopPane desktopPane, Documento documento, Frame padre) {
        this.modelo = Model.getInstance();
        this.padre = padre;
        this.documento = documento;
        this.pestana = pestana;
        this.desktopPane = desktopPane;
        initComponents();
        init();
        cargarFormulario();
        this.setLocation(pestana.getWidth() / 2 - this.getWidth() / 2, pestana.getHeight() / 2 - this.getHeight() / 2);
        this.setVisible(true);
        articulo.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        fecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        total1 = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        buscafecha = new javax.swing.JButton();
        tercero = new javax.swing.JTextField();
        buscaproveedor = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ntercero = new javax.swing.JTextField();
        tipoDoc = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        restaurar = new javax.swing.JButton();

        setTitle("Ventas");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(212, 233, 255));
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

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

        jLabel2.setText("Numero");

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

        panel.setBackground(new java.awt.Color(153, 205, 255));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "Vlr Unit", "Descuento", "Vlr Par"
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

        jLabel3.setText("Codigo");

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        jLabel5.setText("Cantidad");

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

        total.setBackground(new java.awt.Color(128, 192, 255));
        total.setFont(new java.awt.Font("Tahoma", 1, 24));
        total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total.setText("0.00");
        total.setOpaque(true);

        jLabel11.setText("Nota");

        descripcion.setEditable(false);

        jLabel9.setText("Descripcion Comercial");

        subtotal.setEditable(false);
        subtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        subtotal.setText("0.00");

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
        });

        jLabel13.setText("Subtotal");

        jLabel14.setText("Descuento");

        tipodescuento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "%", "$" }));
        tipodescuento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipodescuentoItemStateChanged(evt);
            }
        });

        total1.setBackground(new java.awt.Color(153, 205, 255));
        total1.setFont(new java.awt.Font("Tahoma", 1, 24));
        total1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total1.setText("$");
        total1.setOpaque(true);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(buscara, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(10, 10, 10)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(vunit, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vparcial, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nota, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                .addGap(217, 217, 217)
                                .addComponent(jLabel13))
                            .addGroup(panelLayout.createSequentialGroup()
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
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(total)
                        .addComponent(jLabel7)
                        .addComponent(total1))
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vparcial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(adicionar)
                        .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(vunit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(tipodescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Detalles", panel);

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

        buscaproveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-16x16.png"))); // NOI18N
        buscaproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaproveedorActionPerformed(evt);
            }
        });

        jLabel10.setText("Cliente");

        ntercero.setEditable(false);

        tipoDoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Cotizacion" }));
        tipoDoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoDocItemStateChanged(evt);
            }
        });
        tipoDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoDocActionPerformed(evt);
            }
        });
        tipoDoc.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tipoDocInputMethodTextChanged(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(tercero, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ntercero, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(buscafecha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(tipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscaproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(restaurar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buscafecha)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tercero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(ntercero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buscaproveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(salir)
                        .addComponent(guardar))
                    .addComponent(restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
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
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
}//GEN-LAST:event_numeroKeyTyped

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
        pestana.remove(desktopPane);
}//GEN-LAST:event_salirActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        if (validar()) {
            if (funcion == Constantes.FORMULARIO_APTO_PARA_GUARDAR) {
                if (documento.getNumero().equals(numero.getText())) {
                    guardar();
                }
            } else {
                try {
                    int existe = (Integer) modelo.obtenerRegistro("existeDocumento", numero.getText());
                    if (existe == 0) {
                        guardar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Numero de Factura ya existe");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
}//GEN-LAST:event_guardarActionPerformed

    public void guardar() {
        FacturaEmpresa facturaEmpresa = (FacturaEmpresa) modelo.obtenerRegistro("obtenerFacturaEmpresa", clienteMostrador.getEmpresa().getId());

        documento.setNumero((facturaEmpresa.getNumeroActual() + 1) + "");
        documento.setFecha(new Date(fecha.getText().replaceAll("-", "/")));
        documento.setTotal(Double.parseDouble(total.getText().replaceAll(",", "")));
        documento.setSubtotal(Double.parseDouble(subtotal.getText().replaceAll(",", "")));
        documento.setNota(nota.getText());
        documento.setTipo(tipoDoc.getSelectedItem().toString());
        documento.setDescuento(0.0);
        documento.setTipodescuento(tipodescuento.getSelectedItem().toString());

        if (!descuento.getText().trim().equals("")) {
            if (Double.parseDouble(descuento.getText().replaceAll(",", "")) >= 0) {
                double des = Double.parseDouble(total.getText().replaceAll(",", ""));
                documento.setDescuento(des);
            }
        }

        if (documento.getTipo().equals("Factura")) {
            int op = Formulario_Cobrar_Ventas.cobrar(new JFrame(), documento, total.getText().replaceAll("$", "").replaceAll(",", ""));
            if (op > 0) {
                List<Articulosdocumento> la = new LinkedList();
                List<Movimientokardex> lmk = new LinkedList();
                List<Detallefactura> ldf = new LinkedList();

                if (documento.getArticulosdocumentoCollection() != null) {
                    for (int i = 0; i < documento.getArticulosdocumentoCollection().size(); i++) {
                        md.eliminar(documento.getArticulosdocumentoCollection().get(i));
                    }
                }

                for (int i = 0; i < tabla.getRowCount(); i++) {
                    Articulosdocumento ad = (Articulosdocumento) tabla.getValueAt(i, 0);
                    ad.setDocumento(documento);
                    la.add(ad);
                    Articulos art = ad.getArticulo();

                    art.setSaldocosto(art.getSaldocosto() - (ad.getCantidad() * art.getVlrpromedio()));
                    art.setVlrpromedio(art.getSaldocosto() / art.getExistencia().longValue());

                    if ((art.getSaldoventa() - (ad.getVlrparcial())) > 0) {
                        art.setSaldoventa(art.getSaldoventa() - (ad.getVlrparcial()));
                        art.setVlrpromediov(art.getSaldoventa() / art.getExistencia().longValue());
                    } else {
                        art.setSaldoventa(0.0);
                        if (art.getExistencia().longValue() == 0) {
                            art.setVlrpromediov(0.0);
                        } else {
                            art.setSaldoventa(art.getVlrpromediov() * art.getExistencia().longValue());
                        }
                    }
                    art.setFechauventa(documento.getFecha());
                    ma.modificar(art);
                    Movimientokardex mk = new Movimientokardex();
                    mk.setArticulo(ad.getArticulo());
                    mk.setDocumento(documento);
                    mk.setEntrada(0.00);
                    mk.setSalida(ad.getCantidad());
                    mk.setVlrunit(ad.getArticulo().getVlrpromedio());
                    mk.setExistencia(ad.getArticulo().getExistencia());
                    mk.setFecha(new Date());
                    mk.setSaldo(ad.getArticulo().getExistencia().longValue() * ad.getArticulo().getVlrpromedio());
                    mk.setTercero(documento.getTerceros().getNit() + "");
                    if (documento.getTerceros().getId() == 1) {
                        mk.setTercero("Cliente por Mostrador");
                    }
                    mk.setDescripcion("Factura " + documento.getNumero());
                    lmk.add(mk);

                    Detallefactura df = new Detallefactura();
                    df.setDocumento(documento);
                    df.setCodigo(art.getCodigobarras() + "");
                    df.setDescripcion(art.getDescripcioncomercial());
                    df.setCantidad(ad.getCantidad());
                    df.setVlrunit(ad.getVlrunitario());
                    df.setVlrparcial(ad.getVlrparcial());
                    ldf.add(df);

                }


                Caja caja = md.getCaja();
                if (documento.getTipopago() == 3) {
                    List<Abonos> lab = new LinkedList();
                    Abonos ab = new Abonos();
                    ab.setDocumentos(documento);
                    ab.setFecha(documento.getFecha());
                    ab.setValor(documento.getTotalpagado());
                    lab.add(ab);
                    documento.setAbonosList(lab);
                    if (caja != null) {
                        caja.setSaldoactual(caja.getSaldoactual() - documento.getTotalpagado());
                        caja.setPagoaproveedor(caja.getPagoaproveedor() + documento.getTotalpagado());
                        md.modificar(caja);
                    }
                }

                if (caja != null) {
                    if (documento.getTipopago() == 0) {
                        caja.setSaldoactual(caja.getSaldoactual() + documento.getTotal());
                        caja.setVentasefectivo(caja.getVentasefectivo() + documento.getTotal());
                        md.modificar(caja);
                        Movimientoscaja mcaja = new Movimientoscaja();

                    } else if (documento.getTipopago() == 2) {

                        caja.setVentasacredito(caja.getVentasacredito() + documento.getTotal());

                        md.modificar(caja);
                    }
                }

                documento.setMovimientokardexCollection(lmk);
                documento.setArticulosdocumentoCollection(la);
                documento.setDetallefacturaList(ldf);

                if (funcion == 0) {

                    facturaEmpresa.setNumeroactual(facturaEmpresa.getNumeroactual() + 1);
                    mc.modificar(facturaEmpresa);
                    md.crear(documento);
                } else {
                    md.modificar(documento);
                }

                if (op == 2) {
                    managers.Reporte.imprimir(documento, mc);
                }

                reiniciar();
                JOptionPane.showMessageDialog(null, "Datos guardados con exito");
            }
        } else {
            guardarCotizacion();
        }
    }

    public void guardarCotizacion() {

        List<Articulosdocumento> la = new LinkedList();
        List<Movimientokardex> lmk = new LinkedList();

        if (documento.getArticulosdocumentoCollection() != null) {
            for (int i = 0; i < documento.getArticulosdocumentoCollection().size(); i++) {
                md.eliminar(documento.getArticulosdocumentoCollection().get(i));
            }
        }

        for (int i = 0; i < tabla.getRowCount(); i++) {
            Articulosdocumento ad = (Articulosdocumento) tabla.getValueAt(i, 0);
            ad.setDocumento(documento);
            la.add(ad);
        }

        documento.setArticulosdocumentoCollection(la);

        if (funcion == 0) {
            Facturapropiedades fp = mc.getFacturaporpiedades("1");
            fp.setCotizaciones(fp.getCotizaciones() + 1);
            mc.modificar(fp);
            md.crear(documento);
        } else {
            md.modificar(documento);
        }

        reiniciar();
        JOptionPane.showMessageDialog(null, "Datos guardados con exito");
    }

    public boolean validar() {
        if (numero.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el NUMERO porfavor");
            numero.requestFocus();
            return false;
        } else if (fecha.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la FECHA porfavor");
            fecha.requestFocus();
            return false;
        } else if (tercero.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el PROVEEDOR porfavor");
            fecha.requestFocus();
            return false;
        } else if (tabla.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Adicione al menos un Articulo a la Venta");
            articulo.requestFocus();
            return false;
        } else if (total.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Adicione al menos un Articulo a la Venta");
            articulo.requestFocus();
            return false;
        } else if (Double.parseDouble(total.getText().replaceAll(",", "")) < this.calcularvalorminimo()) {
            JOptionPane.showMessageDialog(null, "El descuento es muy alto");
            descuento.requestFocus();
            return false;
        }
        return true;
    }

    private void cargarFormulario() {

        numero.setText(documento.getNumero());
        fecha.setText(new java.text.SimpleDateFormat("yyyy-MM-dd").format(documento.getFecha()));
        tercero.setText(documento.getTerceros().getNit() + "");
        ntercero.setText(documento.getTerceros().getNombre());
        List<Articulosdocumento> la = documento.getArticulosdocumentoCollection();
        List<Alquileres> lal = documento.getAlquileresCollection();
        funcion = 1;
        double totales = 0.0;
        for (int i = 0; i < la.size(); i++) {
            Articulos a1 = la.get(i).getArticulo();
            double cantidades = la.get(i).getCantidad();
            modeloTabla.addRow(new Object[]{la.get(i), a1.getDescripcioncomercial(), cantidades, la.get(i).getVlrunitario(), la.get(i).getVlrparcial()});
            totales += (la.get(i).getVlrparcial());

        }
        for (int i = 0; i < lal.size(); i++) {
            Articulos a1 = lal.get(i).getArticulo();
            int cantidades = lal.get(i).getCantidad();
            modeloTabla.addRow(new Object[]{lal.get(i), a1.getDescripcioncomercial(), cantidades, lal.get(i).getVlrunitario(), lal.get(i).getTotal()});
            totales += (lal.get(i).getTotal());

        }

        total.setText(utilidades.FormatoNumeros.formatear(totales + ""));


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
            modeloTabla.removeRow(tabla.getSelectedRow());
            total.setText(utilidades.FormatoNumeros.formatear("" + totales));
            subtotal.setText(utilidades.FormatoNumeros.formatear("" + totales));
            calcularDesceunto();
        } else {
            JOptionPane.showMessageDialog(null, "No hay Articulo Seleccionado");
        }
}//GEN-LAST:event_quitarActionPerformed

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        // TODO add your handling code here:
        adicionarItem();
        calcularDesceunto();

    }//GEN-LAST:event_adicionarActionPerformed
    public void adicionarItem() {

        double cantidades = Double.parseDouble(cantidad.getText());
        if (articulosDocumento != null) {///si es un articulo documento

            if (!esta(articulosDocumento)) {
                String descrip = articulosDocumento.getArticulo().getDescripcioncomercial();
                if (articulosDocumento.getArticulo().getTipo() == 1) {
                    descrip = "Servicio de " + articulosDocumento.getArticulo().getDescripcioncomercial();
                }
                double totalparcial = Double.parseDouble(this.vparcial.getText().replaceAll(",", ""));
                double totales = Double.parseDouble(total.getText().replaceAll(",", ""));
                articulosDocumento.setCantidad(cantidades);
                articulosDocumento.setVlrunitario(Double.parseDouble(this.vunit.getText().replaceAll(",", "")));
                articulosDocumento.setVlrparcial(totalparcial);
                double existencia = articulosDocumento.getArticulo().getExistencia().longValue() - cantidades;
                articulosDocumento.getArticulo().setExistencia((existencia));

                modeloTabla.addRow(new Object[]{articulosDocumento, descrip, cantidad.getText(), utilidades.FormatoNumeros.formatear(vunit.getText()), utilidades.FormatoNumeros.formatear(vparcial.getText())});
                totales += (totalparcial);
                total.setText(utilidades.FormatoNumeros.formatear(totales + ""));
                subtotal.setText(utilidades.FormatoNumeros.formatear(totales + ""));
                articulosDocumento = null;
                adicionar.setEnabled(false);
                articulo.setText("");
                descripcion.setText("");
                cantidad.setText("1");
                vunit.setText("0.00");
                vparcial.setText("0.00");


            } else {
                JOptionPane.showMessageDialog(null, "El Articulo ya esta adicionado");
            }
        }
        articulo.requestFocus();
    }

    public void calcularvalorParcial(int cantidades) {
        if (articulosDocumento != null) {
            long existencia = articulosDocumento.getArticulo().getExistencia().longValue();
            if (tipoDoc.getSelectedIndex() != 1) {
                if (cantidades <= existencia) {
                    double vltunitario = Double.parseDouble(vunit.getText().replaceAll(",", ""));
                    double totalparcial = cantidades * (vltunitario);
                    vparcial.setText(utilidades.FormatoNumeros.formatear("" + (totalparcial)));
                    adicionar.setEnabled(true);
                    adicionar.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad digitada es mayor a la disponibilidad o exitencia del articulo");
                }
            } else {// si es cotizacion
                double vltunitario = Double.parseDouble(vunit.getText().replaceAll(",", ""));
                double totalparcial = cantidades * (vltunitario);
                vparcial.setText(utilidades.FormatoNumeros.formatear("" + (totalparcial)));
                adicionar.setEnabled(true);
                adicionar.requestFocus();
            }
        }
    }

    private void buscaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaraActionPerformed
        // TODO add your handling code here:
        Articulos a = null;

        a = new BuscaArticulo(padre, true, modelo).getArticulo();
        if (a != null) {
            articulosDocumento = new Articulosdocumento();
            articulosDocumento.setArticulo(a);
            articulo.setText("" + articulosDocumento.getArticulo().getCodigobarras());
            descripcion.setText("" + articulosDocumento.getArticulo().getDescripcioncomercial());
            vunit.setText(utilidades.FormatoNumeros.formatear("" + articulosDocumento.getArticulo().getValorsug()));
            cantidad.setText("" + 1);
            calcularvalorParcial(1);
            cantidad.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un Articulo");
        }
}//GEN-LAST:event_buscaraActionPerformed

    private void buscafechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscafechaActionPerformed
        // TODO add your handling code here:
        fecha.setText(new Calendario(new Frame(), true).getFecha());
        numero.requestFocus();
}//GEN-LAST:event_buscafechaActionPerformed

    private void articuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_articuloActionPerformed
        // TODO add your handling code here:
        if (articulo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite Codigo de Barras");
        } else {

            Articulos a = ma.getArticulo("codigobarras", BigInteger.valueOf(Long.parseLong(articulo.getText())));
            if (a == null) {
                JOptionPane.showMessageDialog(null, "Articulo no Existe ");
            } else {
                if (!esta(a)) {

                    articulosDocumento = new Articulosdocumento();
                    articulosDocumento.setArticulo(a);
                    articulo.setText("" + articulosDocumento.getArticulo().getCodigobarras());
                    descripcion.setText("" + articulosDocumento.getArticulo().getDescripcioncomercial());
                    vunit.setText(utilidades.FormatoNumeros.formatear("" + articulosDocumento.getArticulo().getValorsug()));
                    calcularvalorParcial(1);
                    cantidad.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "El Articulo ya esta adicionado");
                }
            }
        }
    }//GEN-LAST:event_articuloActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
        if (!cantidad.getText().trim().equals("")) {
            double cantidades = Double.parseDouble(cantidad.getText());
            if (cantidad.getText().trim().equals("") && cantidades <= 0) {
                JOptionPane.showMessageDialog(null, "Digite una CANTIDAD");
            } else {
                if (articulosDocumento != null) {
                    long existencia = articulosDocumento.getArticulo().getExistencia().longValue();
                    if (cantidades <= existencia) {
                        double vltunitario = Double.parseDouble(vunit.getText().replaceAll(",", ""));
                        double totalparcial = cantidades * (vltunitario);
                        vparcial.setText(utilidades.FormatoNumeros.formatear("" + (totalparcial)));
                        adicionar.setEnabled(true);
                        vunit.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad digitada es mayor a la disponibilidad o exitencia del articulo");
                    }
                }
            }
        }
    }//GEN-LAST:event_cantidadActionPerformed

    private void buscaproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaproveedorActionPerformed
        // TODO add your handling code here:
        documento.setTerceros(new BuscaTerceros(padre, true, modelo).getTercero());
        if (documento.getTerceros() != null) {
            tercero.setText(documento.getTerceros().getNit() + "");
            ntercero.setText(documento.getTerceros().getNombre());
        }
    }//GEN-LAST:event_buscaproveedorActionPerformed

    private void numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroActionPerformed
        // TODO add your handling code here:
        tercero.requestFocus();
    }//GEN-LAST:event_numeroActionPerformed

    private void terceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terceroActionPerformed
        // TODO add your handling code here:
        if (tercero.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el nit del Tercero ");
        } else if (md.getTercero((Object) tercero.getText()) != null) {
            documento.setTerceros(md.getTercero((Object) tercero.getText()));
            tercero.setText(documento.getTerceros().getNit() + "");
            ntercero.setText(documento.getTerceros().getNombre());

            articulo.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Tercero no existe");
        }
    }//GEN-LAST:event_terceroActionPerformed

    private void vunitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vunitActionPerformed
        // TODO add your handling code here:
        if (!cantidad.getText().trim().equals("")) {
            double cantidades = Double.parseDouble(cantidad.getText());
            if (articulosDocumento != null) {
                double vltunitario = Double.parseDouble(vunit.getText().replaceAll(",", ""));
                if (vltunitario >= articulosDocumento.getArticulo().getValormin()) {
                    if (vltunitario < articulosDocumento.getArticulo().getValorsug()) {
                        this.tipodescuento.setSelectedIndex(1);
                        this.tipodescuento.setEnabled(false);
                        descuento.setEnabled(true);
                        double dev = 0.0;
                        if (!descuento.getText().trim().equals("")) {
                            dev = Double.parseDouble(descuento.getText().replaceAll(",", ""));
                        }
                        dev += articulosDocumento.getArticulo().getValorsug() - vltunitario;
                        descuento.setText(utilidades.FormatoNumeros.formatear("" + dev));
                        descuento.setEnabled(false);

                    }
                    double totalparcial = cantidades * (vltunitario);
                    vparcial.setText(utilidades.FormatoNumeros.formatear("" + (totalparcial)));
                    adicionar.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "El Articulo no puede ser vendido con un valor inferior al minimo de venta");
                }
            }
        }
    }//GEN-LAST:event_vunitActionPerformed

    private void restaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restaurarActionPerformed
        // TODO add your handling code here:
        reiniciar();
    }//GEN-LAST:event_restaurarActionPerformed

    public void reiniciar() {
        tipoDoc.setSelectedIndex(0);
        documento = new Documentos();
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Vlr Unit");
        modeloTabla.addColumn("Vlr Par");
        tabla.setModel(modeloTabla);
        Terceros t = md.getTercero("1");
        if (t != null) {
            documento.setTerceros(t);
            tercero.setText(t.getNombre());
            ntercero.setText(t.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "no se ha establecido un cliente por mostrador");
            guardar.setEnabled(false);
        }

        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);

        fecha.setText(ano + "-" + mes + "-" + dia);

        Facturapropiedades fp = mc.getFacturaporpiedades("1");
        if (fp != null) {
            int consecutivo = fp.getNumeroactual() + 1;
            numero.setText(consecutivo + "");
        } else {
            JOptionPane.showMessageDialog(null, "no se ha Configuarado las propiedades de la factura");
            guardar.setEnabled(false);
        }

        articulosDocumento = null;
        adicionar.setEnabled(false);
        articulo.setText("");
        descripcion.setText("");
        cantidad.setText("1");
        vunit.setText("0.00");
        vparcial.setText("0.00");
        articulo.requestFocus();
        descuento.setText("0.00");
        total.setText("0.00");
        subtotal.setText("0.00");
    }

    private void descuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyReleased
        // TODO add your handling code here:
        calcularDesceunto();
    }//GEN-LAST:event_descuentoKeyReleased

    public double calcularvalorminimo() {
        double sum = 0.0;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            Articulosdocumento ad = (Articulosdocumento) tabla.getValueAt(i, 0);
            Articulos art = ad.getArticulo();
            sum += art.getValormin();
        }
        return sum;
    }

    public void calcularDesceunto() {
        double dev = 0.0;
        if (!descuento.getText().trim().equals("")) {
            dev = Double.parseDouble(descuento.getText().replaceAll(",", ""));
        }
        double t = Double.parseDouble(subtotal.getText().replaceAll(",", ""));

        if (tipodescuento.getSelectedIndex() == 0) {
            dev = t - dev / 100 * t;
        } else {
            dev = t - dev;
        }
        if (dev >= 0) {
            total.setText(utilidades.FormatoNumeros.formatear(dev + ""));
        }
    }

    private void tipodescuentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipodescuentoItemStateChanged
        // TODO add your handling code here:
        calcularDesceunto();
    }//GEN-LAST:event_tipodescuentoItemStateChanged

    private void tipoDocInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tipoDocInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoDocInputMethodTextChanged

    private void tipoDocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoDocItemStateChanged
        // TODO add your handling code here:
        if (tipoDoc.getSelectedIndex() == 0) {
            Facturapropiedades fp = mc.getFacturaporpiedades("1");
            if (fp != null) {
                int consecutivo = fp.getNumeroactual() + 1;
                numero.setText(consecutivo + "");
            } else {
                JOptionPane.showMessageDialog(null, "no se ha Configuarado las propiedades de la factura");
                guardar.setEnabled(false);
            }
        } else {
            Facturapropiedades fp = mc.getFacturaporpiedades("1");
            if (fp != null) {
                long consecutivo = fp.getCotizaciones() + 1;
                numero.setText(consecutivo + "");
            } else {
                JOptionPane.showMessageDialog(null, "no se ha Configuarado las propiedades de la factura");
                guardar.setEnabled(false);
            }
        }


    }//GEN-LAST:event_tipoDocItemStateChanged

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        int index = tabla.getSelectedRow();
        if (index > -1) {
            articulosDocumento = Ventana_Editar_Item.editarItem(padre, true, (Articulosdocumento) tabla.getValueAt(index, 0), 1);
            if (articulosDocumento != null) {
                //  System.out.println("entrando");
                tabla.setValueAt(articulosDocumento, index, 0);
                String descrip = articulosDocumento.getArticulo() + "";
                if (articulosDocumento.getArticulo().getTipo() == 1) {
                    descrip = "Servicio de " + articulosDocumento.getArticulo().getDescripcioncomercial();
                }
                tabla.setValueAt(descrip, index, 1);
                tabla.setValueAt(articulosDocumento.getCantidad(), index, 2);
                tabla.setValueAt(utilidades.FormatoNumeros.formatear("" + articulosDocumento.getVlrunitario()), index, 3);
                tabla.setValueAt(utilidades.FormatoNumeros.formatear("" + articulosDocumento.getVlrparcial()), index, 4);
                articulosDocumento = null;
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoActionPerformed

    private void tipoDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoDocActionPerformed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_F1) {//cliente
            documento.setTerceros(new BuscaTerceros(padre, true, modelo).getTercero());
            if (documento.getTerceros() != null) {
                tercero.setText(documento.getTerceros().getNit() + "");
                ntercero.setText(documento.getTerceros().getNombre());
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_F2) {//articulo

            Articulos a = null;

            a = new BuscaArticulo(padre, true, modelo).getArticulo();
            if (a != null) {
                articulosDocumento = new Articulosdocumento();
                articulosDocumento.setArticulo(a);
                articulo.setText("" + articulosDocumento.getArticulo().getCodigobarras());
                descripcion.setText("" + articulosDocumento.getArticulo().getDescripcioncomercial());
                vunit.setText(utilidades.FormatoNumeros.formatear("" + articulosDocumento.getArticulo().getValorsug()));
                cantidad.setText("" + 1);
                calcularvalorParcial(1);
                cantidad.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un Articulo");
            }

        } else if (evt.getKeyCode() == KeyEvent.VK_F3) {//guardar
            boolean sw = true;
            if (validar()) {
                if (funcion == 1) {
                    //  JOptionPane.showMessageDialog(null,documento.getNumero()+" "+numero.getText());
                    if (documento.getNumero().equals(numero.getText())) {
                        guardar();
                        sw = false;
                    }

                }
                if (sw) {
                    if (!md.existeDocumento("numero", numero.getText())) {
                        guardar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Numero de Factura ya exsiste");
                    }
                }
            }

        } else if (evt.getKeyCode() == KeyEvent.VK_F4) {
        }
    }//GEN-LAST:event_formKeyPressed

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
    private javax.swing.JTextField articulo;
    private javax.swing.JButton buscafecha;
    private javax.swing.JButton buscaproveedor;
    private javax.swing.JButton buscara;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField descripcion;
    private javax.swing.JTextField descuento;
    private javax.swing.JTextField fecha;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nota;
    private javax.swing.JTextField ntercero;
    private javax.swing.JTextField numero;
    private javax.swing.JPanel panel;
    private javax.swing.JButton quitar;
    private javax.swing.JButton restaurar;
    private javax.swing.JButton salir;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField tercero;
    private javax.swing.JComboBox tipoDoc;
    private javax.swing.JComboBox tipodescuento;
    private javax.swing.JLabel total;
    private javax.swing.JLabel total1;
    private javax.swing.JTextField vparcial;
    private javax.swing.JTextField vunit;
    // End of variables declaration//GEN-END:variables
}*/
