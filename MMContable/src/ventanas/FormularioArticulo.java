package ventanas;

import java.awt.Frame;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import beans.Articulo;
import beans.ArticuloCombo;
import beans.Estado;
import beans.Grupo;
import beans.Marca;
import beans.TipoArticulo;
import db.Model;
import interfaces.Buscadores;
import interfaces.Constantes;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import utilidades.FormatoNumeros;
import utilidades.ModeloTabla;

public class FormularioArticulo extends javax.swing.JDialog implements Buscadores {

    private Articulo articuloSeleccionado, articuloParaCombo;
    private int funcion = Constantes.ESTADO_CREACION;
    private Buscadores buscador;
    private ModeloTabla modeloTabla;
    private Model modelo;
    private Frame padre;
    private SimpleDateFormat formato;

    public FormularioArticulo(Frame padre, boolean modal, Buscadores buscador) {
        super(padre, modal);
        this.buscador = buscador;
        this.modelo = Model.getInstance();
        this.padre = padre;
        articuloSeleccionado = new Articulo();
        initComponents();
        formato = new SimpleDateFormat("dd/MM/yyyy");
        llenarCombos();

        this.setResizable(false);
        this.setLocationRelativeTo(padre);
        this.setVisible(true);
    }

    public FormularioArticulo(Frame padre, boolean modal, Buscadores buscador, Articulo articuloSeleccionado) {
        super(padre, modal);
        this.buscador = buscador;
        this.modelo = Model.getInstance();
        this.articuloSeleccionado = articuloSeleccionado;
        this.padre = padre;
        formato = new SimpleDateFormat("dd/MM/yyyy");
        initComponents();
        llenarCombos();
        llenar();

        this.setResizable(false);
        this.setLocationRelativeTo(padre);
        this.setVisible(true);
    }

    @SuppressWarnings("CallToThreadDumpStack")
    private void llenarCombos() {
        try {
            buscar();

            List<TipoArticulo> listaTipos = (List<TipoArticulo>) modelo.obtenerListado("obtenerTiposArticulos");

            for (TipoArticulo tip : listaTipos) {
                tipo.addItem(tip);
            }

            List<Estado> listaEstados = (List<Estado>) modelo.obtenerListado("obtenerEstados");
            this.estado.removeAllItems();

            for (int i = 0; i < listaEstados.size(); i++) {
                this.estado.addItem(listaEstados.get(i));
            }

            modeloTabla = new ModeloTabla(new boolean[]{false, false, false, false});

            modeloTabla.addColumn("Código");
            modeloTabla.addColumn("Descripción");
            modeloTabla.addColumn("Cantidad");
            modeloTabla.addColumn("Vlr Costo");
            modeloTabla.addColumn("Vlr Parcial");

            tabla.setModel(modeloTabla);
            this.tab.remove(this.combo);

            FormatoNumeros fn = new FormatoNumeros(valorganancia);
            valorganancia.addFocusListener(fn);
            valorganancia.addKeyListener(fn);
            fn = new FormatoNumeros(vlrCosto);
            vlrCosto.addFocusListener(fn);
            vlrCosto.addKeyListener(fn);
            fn = new FormatoNumeros(valorsugerido);
            valorsugerido.addFocusListener(fn);
            valorsugerido.addKeyListener(fn);
            fn = new FormatoNumeros(porcentajeMinimo);
            porcentajeMinimo.addFocusListener(fn);
            porcentajeMinimo.addKeyListener(fn);
            fn = new FormatoNumeros(porcentajeSugerido);
            porcentajeSugerido.addFocusListener(fn);
            porcentajeSugerido.addKeyListener(fn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean validar() {
        if (descripcionComercial.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la DESCRIPCIÓN COMERCIAL por favor");
            descripcionComercial.requestFocus();
            return false;
        } else if (codigoBarras.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el CÓDIGO DE BARRAS por favor");
            codigoBarras.requestFocus();
            return false;
        } else if (vlrCosto.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el VALOR DE COSTO por favor");
            vlrCosto.requestFocus();
            return false;
        } else if (Double.parseDouble(vlrCosto.getText().replaceAll(",", "")) <= 0) {
            JOptionPane.showMessageDialog(null, "Digite el VALOR DE COSTO por favor");
            vlrCosto.requestFocus();
            return false;
        } else if (vlrPromedio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el VALOR DE PROMOCIÓN por favor");
            vlrPromedio.requestFocus();
            return false;
        } else if (porcentajeMinimo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el PORCENTAJE MÍNIMO por favor");
            porcentajeMinimo.requestFocus();
            return false;
        } else if (Double.parseDouble(porcentajeMinimo.getText().replaceAll(",", "")) <= 0) {
            JOptionPane.showMessageDialog(null, "Digite el PORCENTAJE MÍNIMO por favor");
            porcentajeMinimo.requestFocus();
            return false;
        } else if (porcentajeSugerido.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite el PORCENTAJE SUGERIDO por favor");
            porcentajeSugerido.requestFocus();
            return false;
        } else if (Double.parseDouble(porcentajeSugerido.getText().replaceAll(",", "")) <= 0) {
            JOptionPane.showMessageDialog(null, "Digite el PORCENTAJE SUGERIDO por favor");
            porcentajeSugerido.requestFocus();
            return false;
        } else if (cantidadMinima.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite la CANTIDAD MÍNIMA por favor");
            cantidadMinima.requestFocus();
            return false;
        } else if (((TipoArticulo) tipo.getSelectedItem()).getDescripcion().equals("Combo-Kit")) {
            if (tabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Agregue por lo menos un ARTICULO al COMBO");
                cbarras.requestFocus();
                return false;
            }
        }
        return true;
    }

    private void llenar() {
        descripcionComercial.setText(articuloSeleccionado.getDescripcioncomercial());
        codigoBarras.setText(articuloSeleccionado.getCodigobarras() + "");
        vlrCosto.setText(utilidades.FormatoNumeros.formatear(articuloSeleccionado.getVlrcosto() + ""));
        vlrPromedio.setText(utilidades.FormatoNumeros.formatear(articuloSeleccionado.getVlrpromedio() + ""));
        porcentajeMinimo.setText(utilidades.FormatoNumeros.formatear(articuloSeleccionado.getPorcentajeminimo() + ""));
        porcentajeSugerido.setText(utilidades.FormatoNumeros.formatear(articuloSeleccionado.getPorcentajesugerido() + ""));
        BigDecimal por = articuloSeleccionado.getVlrcosto();
        BigDecimal pr2 = articuloSeleccionado.getPorcentajeminimo().divide(new BigDecimal(100));
        valorganancia.setText(utilidades.FormatoNumeros.formatear(((por.multiply(pr2)).add(por)) + ""));
        pr2 = articuloSeleccionado.getPorcentajesugerido().divide(new BigDecimal(100));
        valorsugerido.setText(utilidades.FormatoNumeros.formatear(((por.multiply(pr2)).add(por)) + ""));
        observaciones.setText(articuloSeleccionado.getObservaciones());
        tipo.setSelectedItem(articuloSeleccionado.getTipo());
        existencia.setText(articuloSeleccionado.getExistencia() + "");
        cantidadMinima.setText(articuloSeleccionado.getCantidadminima() + "");
        fechaucompra.setText(articuloSeleccionado.getFechaucompra() != null ? formato.format(articuloSeleccionado.getFechaucompra()) + "" : "");
        fechauventa.setText(articuloSeleccionado.getFechauventa() != null ? formato.format(articuloSeleccionado.getFechauventa()) + "" : "");
        grupo.setSelectedItem(articuloSeleccionado.getGrupo());
        marca.setSelectedItem(articuloSeleccionado.getMarca());
        estado.setSelectedItem(articuloSeleccionado.getEstado());

        if (articuloSeleccionado.getImagen() != null) {
            if (articuloSeleccionado.getImagen() != null) {
                imagen.setIcon(new ImageIcon(articuloSeleccionado.getImagen()));
            }
        }
        funcion = Constantes.ESTADO_EDICION;

        if (articuloSeleccionado.getTipo().getDescripcion().equals("Combo-Kit")) {
            try {
                tab.add("Combo-Kit", combo);
                BigDecimal totales = new BigDecimal(0);
                List<ArticuloCombo> listado = (List<ArticuloCombo>) modelo.obtenerListado("obtenerArticulosCombo", articuloSeleccionado.getId());
                articuloSeleccionado.setListaArticulos(listado);

                for (ArticuloCombo articuloCombo : listado) {
                    Articulo art = (Articulo) modelo.obtenerRegistro("obtenerArticuloPorId", articuloCombo.getArticuloHijo().getId());
                    int cantidades = articuloCombo.getCantidad();
                    totales.add(articuloCombo.getPrecioEnCombo().multiply(new BigDecimal(cantidades)));
                    modeloTabla.addRow(new Object[]{art, art.getDescripcioncomercial(), cantidades, utilidades.FormatoNumeros.formatear(articuloCombo.getPrecioEnCombo() + ""), utilidades.FormatoNumeros.formatear("" + totales)});
                }
                total.setText(utilidades.FormatoNumeros.formatear((totales + "")));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        descripcionComercial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        codigoBarras = new javax.swing.JTextField();
        grupo = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        vlrCosto = new javax.swing.JTextField();
        vlrPromedio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        porcentajeSugerido = new javax.swing.JTextField();
        valorsugerido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        porcentajeMinimo = new javax.swing.JTextField();
        valorganancia = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        existencia = new javax.swing.JTextField();
        cantidadMinima = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        fechauventa = new javax.swing.JTextField();
        fechaucompra = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextArea();
        combo = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        cbarras = new javax.swing.JTextField();
        adicionar = new javax.swing.JButton();
        quitar = new javax.swing.JButton();
        buscara = new javax.swing.JButton();
        dcomercial = new javax.swing.JTextField();
        canti = new javax.swing.JTextField();
        vcosto = new javax.swing.JTextField();
        vparcial = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        marca = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        imagen = new javax.swing.JLabel();
        adicionarg = new javax.swing.JButton();
        adicionarm = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Maestro de Articulos");

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        descripcionComercial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descripcionComercialActionPerformed(evt);
            }
        });
        descripcionComercial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descripcionComercialKeyTyped(evt);
            }
        });

        jLabel2.setText("Descripción Comercial:");

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

        jLabel3.setText("Código de Barras:");

        codigoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoBarrasActionPerformed(evt);
            }
        });
        codigoBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codigoBarrasKeyTyped(evt);
            }
        });

        jLabel10.setLabelFor(grupo);
        jLabel10.setText("Grupo:");
        jLabel10.setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(212, 233, 255));

        jPanel4.setBackground(new java.awt.Color(212, 233, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Precios"));

        jLabel4.setText("Valor Costo:");

        vlrCosto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        vlrCosto.setText("0");
        vlrCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vlrCostoActionPerformed(evt);
            }
        });
        vlrCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vlrCostoKeyReleased(evt);
            }
        });

        vlrPromedio.setEditable(false);
        vlrPromedio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        vlrPromedio.setText("0");

        jLabel5.setText("Promedio:");

        jLabel7.setText("% Ganancia");

        porcentajeSugerido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        porcentajeSugerido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeSugeridoActionPerformed(evt);
            }
        });
        porcentajeSugerido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                porcentajeSugeridoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                porcentajeSugeridoKeyTyped(evt);
            }
        });

        valorsugerido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        valorsugerido.setText("0");
        valorsugerido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                valorsugeridoKeyReleased(evt);
            }
        });

        jLabel8.setText("Sugerido de Venta:");

        jLabel9.setText("Mínimo de Venta: ");

        porcentajeMinimo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        porcentajeMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeMinimoActionPerformed(evt);
            }
        });
        porcentajeMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                porcentajeMinimoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                porcentajeMinimoKeyTyped(evt);
            }
        });

        valorganancia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        valorganancia.setText("0");
        valorganancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                valorgananciaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(porcentajeSugerido, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(valorsugerido, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vlrCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vlrPromedio, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(porcentajeMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valorganancia, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(vlrCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(vlrPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(porcentajeMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorganancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(porcentajeSugerido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(valorsugerido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(212, 233, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Existencias"));

        jLabel6.setText("Cantidad Actual:");

        jLabel11.setText("Cantidad Mínima:");

        existencia.setEditable(false);

        cantidadMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadMinimaActionPerformed(evt);
            }
        });
        cantidadMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadMinimaKeyTyped(evt);
            }
        });

        jLabel12.setText("Fecha Ult Venta:");

        fechauventa.setEditable(false);

        fechaucompra.setEditable(false);

        jLabel15.setText("Fecha Ult Compra:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fechaucompra, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(fechauventa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(existencia, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(cantidadMinima, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(existencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cantidadMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(fechaucompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(fechauventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(212, 233, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Notas y Observaciones"));

        observaciones.setColumns(20);
        observaciones.setRows(5);
        jScrollPane1.setViewportView(observaciones);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        tab.addTab("Datos Básicos", jPanel1);

        combo.setBackground(new java.awt.Color(212, 233, 255));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "Vlr Costo", "Vlr Parcial"
            }
        ));
        jScrollPane2.setViewportView(tabla);

        cbarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbarrasActionPerformed(evt);
            }
        });
        cbarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cbarrasKeyTyped(evt);
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

        quitar.setForeground(new java.awt.Color(0, 0, 204));
        quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        quitar.setText("Quitar");
        quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarActionPerformed(evt);
            }
        });

        buscara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-16x16.png"))); // NOI18N
        buscara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaraActionPerformed(evt);
            }
        });

        dcomercial.setEditable(false);

        canti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantiActionPerformed(evt);
            }
        });
        canti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantiKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantiKeyTyped(evt);
            }
        });

        vcosto.setEditable(false);

        vparcial.setEditable(false);

        jLabel1.setText("Código de Barras");

        jLabel16.setText("Descripción Comercial");

        jLabel17.setText("Cantidad");

        jLabel18.setText("Vlr Costo");

        jLabel19.setText("Vlr Parcial");

        jLabel20.setText("TOTAL:");

        total.setFont(new java.awt.Font("Tahoma", 1, 18));
        total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total.setText("0.00");

        javax.swing.GroupLayout comboLayout = new javax.swing.GroupLayout(combo);
        combo.setLayout(comboLayout);
        comboLayout.setHorizontalGroup(
            comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comboLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, comboLayout.createSequentialGroup()
                        .addComponent(adicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(130, 130, 130)
                        .addComponent(total))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, comboLayout.createSequentialGroup()
                        .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbarras)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(comboLayout.createSequentialGroup()
                                .addComponent(buscara)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcomercial, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comboLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(45, 45, 45)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(canti)
                            .addComponent(jLabel17))
                        .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(comboLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vcosto, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(comboLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel18)))
                        .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(comboLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vparcial, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comboLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addGap(16, 16, 16))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );
        comboLayout.setVerticalGroup(
            comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comboLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(comboLayout.createSequentialGroup()
                        .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscara, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                            .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbarras)
                                .addComponent(dcomercial)))
                        .addGap(11, 11, 11))
                    .addGroup(comboLayout.createSequentialGroup()
                        .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(canti)
                            .addComponent(vcosto, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                            .addComponent(vparcial, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(comboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adicionar)
                    .addComponent(quitar)
                    .addComponent(jLabel20)
                    .addComponent(total))
                .addContainerGap())
        );

        tab.addTab("Combo-Kit", combo);

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

        jLabel13.setText("Marca:");

        tipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoItemStateChanged(evt);
            }
        });

        jLabel14.setText("Tipo:");

        jPanel3.setBackground(new java.awt.Color(105, 181, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Imagen"));

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setText("Clic para Cargar");
        imagen.setAutoscrolls(true);
        imagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(imagen);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        adicionarg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        adicionarg.setToolTipText("Adicionar Grupo");
        adicionarg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionargActionPerformed(evt);
            }
        });

        adicionarm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        adicionarm.setToolTipText("Adicionar Marca");
        adicionarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarmActionPerformed(evt);
            }
        });

        jLabel28.setText("Estado:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(grupo, 0, 137, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adicionarg, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adicionarm, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(codigoBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(descripcionComercial, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))))
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tab))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(descripcionComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(codigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adicionarg)
                            .addComponent(adicionarm)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salir)
                    .addComponent(guardar))
                .addGap(12, 12, 12))
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

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        this.dispose();
}//GEN-LAST:event_salirActionPerformed

    @SuppressWarnings("CallToThreadDumpStack")
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {
            int existeArticulo = (Integer) modelo.obtenerRegistro("existeArticulo", descripcionComercial.getText());
            int existeCodigo = (Integer) modelo.obtenerRegistro("existeArticuloPorCodigo", Long.parseLong(codigoBarras.getText()));

            if (validar()) {
                if (new BigDecimal(porcentajeMinimo.getText()).compareTo(new BigDecimal(porcentajeSugerido.getText())) <= 0) {
                    if (funcion == Constantes.ESTADO_EDICION) {
                        if (articuloSeleccionado.getCodigobarras().equals(codigoBarras.getText())) {
                            if (articuloSeleccionado.getDescripcioncomercial().equals(descripcionComercial.getText())) {
                                guardar();
                            } else {
                                if (existeArticulo > 0) {
                                    JOptionPane.showMessageDialog(null, "La DESCRIPCIÓN COMERCIAL ya Existe");
                                    descripcionComercial.requestFocus();
                                } else {
                                    guardar();
                                }
                            }
                        } else {
                            if (existeCodigo > 0) {
                                JOptionPane.showMessageDialog(null, "El CÓDIGO DE BARRAS ya Existe");
                                codigoBarras.requestFocus();
                            } else {
                                if (articuloSeleccionado.getDescripcioncomercial().equals(descripcionComercial.getText())) {
                                    guardar();
                                } else {
                                    if (existeArticulo > 0) {
                                        JOptionPane.showMessageDialog(null, "La DESCRIPCIÓN COMERCIAL ya Existe");
                                        descripcionComercial.requestFocus();
                                    } else {
                                        guardar();
                                    }
                                }
                            }
                        }
                    } else {
                        if (existeCodigo > 0) {
                            JOptionPane.showMessageDialog(null, "El CÓDIGO DE BARRAS ya Existe");
                            codigoBarras.requestFocus();
                        } else if (existeArticulo > 0) {
                            JOptionPane.showMessageDialog(null, "La DESCRIPCIÓN COMERCIAL ya Existe");
                            grupo.requestFocus();
                        } else {
                            guardar();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Sugerido de Venta no puede ser menor que Mínimo de Venta");
                    porcentajeSugerido.requestFocus();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}//GEN-LAST:event_guardarActionPerformed

    @SuppressWarnings("CallToThreadDumpStack")
    public void guardar() {
        try {
            articuloSeleccionado.setDescripcioncomercial(descripcionComercial.getText());
            articuloSeleccionado.setCodigobarras(codigoBarras.getText());
            articuloSeleccionado.setVlrcosto(new BigDecimal(vlrCosto.getText().replaceAll(",", "")));
            articuloSeleccionado.setVlrpromedio(new BigDecimal(vlrPromedio.getText().replaceAll(",", "")));
            articuloSeleccionado.setVlrpromediov(new BigDecimal(valorganancia.getText().replaceAll(",", "")));
            articuloSeleccionado.setValormin(new BigDecimal(valorganancia.getText().replaceAll(",", "")));
            articuloSeleccionado.setValorsug(new BigDecimal(valorsugerido.getText().replaceAll(",", "")));
            articuloSeleccionado.setPorcentajeminimo(new BigDecimal(porcentajeMinimo.getText().replaceAll(",", "")));
            articuloSeleccionado.setPorcentajesugerido(new BigDecimal(porcentajeSugerido.getText().replaceAll(",", "")));
            articuloSeleccionado.setObservaciones(observaciones.getText());
            articuloSeleccionado.setTipo(((TipoArticulo) tipo.getSelectedItem()));
            articuloSeleccionado.setCantidadminima(new BigDecimal(cantidadMinima.getText()));
            articuloSeleccionado.setGrupo((Grupo) grupo.getSelectedItem());
            articuloSeleccionado.setMarca((Marca) marca.getSelectedItem());
            articuloSeleccionado.setEstado((Estado) estado.getSelectedItem());

            List<ArticuloCombo> listaArticulos = new LinkedList<ArticuloCombo>();

            if (articuloSeleccionado.getTipo().getDescripcion().equals("Combo-Kit")) {

                for (int i = 0; i < tabla.getRowCount(); i++) {
                    ArticuloCombo articuloCombo = new ArticuloCombo();
                    articuloCombo.setArticuloHijo((Articulo) tabla.getValueAt(i, 0));
                    articuloCombo.setCantidad(Integer.parseInt(tabla.getValueAt(i, 2).toString()));
                    articuloCombo.setPrecioEnCombo(new BigDecimal(tabla.getValueAt(i, 3).toString().replaceAll(",", "")));

                    listaArticulos.add(articuloCombo);
                }

                articuloSeleccionado.setListaArticulos(listaArticulos);
            }

            if (funcion == Constantes.ESTADO_CREACION) {
                articuloSeleccionado.setExistencia(new BigDecimal(0));
                articuloSeleccionado.setSaldocosto(new BigDecimal(0));
                articuloSeleccionado.setSaldoventa(new BigDecimal(0));
                modelo.insertarRegistro("insertarArticulo", articuloSeleccionado);

                if (listaArticulos.size() > 0) {
                    Map<String, Object> mapa = new HashMap<String, Object>();
                    mapa.put("listaArticulos", listaArticulos);
                    mapa.put("padre", articuloSeleccionado);

                    modelo.insertarRegistro("insertarArticulosCombo", mapa);
                }
            } else {
                modelo.borrarRegistro("borrarArticulosCombo", articuloSeleccionado.getId());
                modelo.insertarRegistro("actualizarArticulo", articuloSeleccionado);

                if (listaArticulos.size() > 0) {
                    Map<String, Object> mapa = new HashMap<String, Object>();
                    mapa.put("listaArticulos", listaArticulos);
                    mapa.put("padre", articuloSeleccionado);

                    modelo.insertarRegistro("insertarArticulosCombo", mapa);
                }
            }

            buscador.buscar();
            this.dispose();
            JOptionPane.showMessageDialog(null, "Datos guardados con éxito");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void porcentajeMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentajeMinimoActionPerformed
        porcentajeSugerido.requestFocus();
}//GEN-LAST:event_porcentajeMinimoActionPerformed

    private void tipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoItemStateChanged
        if (((TipoArticulo) tipo.getSelectedItem()).getDescripcion().equals("Combo-Kit")) {
            this.tab.add("Combo-Kit", this.combo);
        } else {
            if (existePestana("Combo-Kit") > -1) {
                tab.remove(this.combo);
            }
        }
    }//GEN-LAST:event_tipoItemStateChanged

    public int existePestana(String titulo) {
        for (int i = 0; i < tab.getTabCount(); i++) {
            if (tab.getTitleAt(i).equals(titulo)) {
                return i;
            }
        }
        return -1;
    }

    private void buscaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaraActionPerformed
        articuloParaCombo = new BuscaArticulo((Frame) this.getParent(), true, funcion == Constantes.ESTADO_EDICION ? articuloSeleccionado : null).getArticulo();

        if (articuloParaCombo == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un Artículo");
        } else {
            cbarras.setText("" + articuloParaCombo.getCodigobarras());
            dcomercial.setText(articuloParaCombo.getDescripcioncomercial());
            vcosto.setText("" + articuloParaCombo.getVlrcosto());
            canti.setText("1");
            calcularValorParcial();
        }
    }//GEN-LAST:event_buscaraActionPerformed

    public void calcularValorParcial() {
        BigDecimal cantidad = BigDecimal.ONE;
        BigDecimal costo = BigDecimal.ZERO;

        if (!canti.getText().trim().isEmpty()) {
            cantidad = new BigDecimal(canti.getText());
        }

        if (!vcosto.getText().trim().isEmpty()) {
            costo = new BigDecimal(vcosto.getText());
        }

        BigDecimal valorParcial = cantidad.multiply(costo);

        vparcial.setText(valorParcial.toString());
    }

    public boolean existeFila(Object a) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if (tabla.getValueAt(i, 0).equals(a)) {
                return true;
            }
        }
        return false;
    }

    public void adicionarArticuloAlCombo(int tipoAdicion) {
        BigDecimal cantidades = BigDecimal.ONE;

        if (tipoAdicion == Constantes.ADICIONAR_CON_BOTON) {
            if (!canti.getText().trim().isEmpty()) {
                cantidades = new BigDecimal(canti.getText());
            }
        }

        if (articuloParaCombo != null) {
            if (!existeFila(articuloParaCombo)) {
                modeloTabla.addRow(new Object[]{articuloParaCombo, articuloParaCombo.getDescripcioncomercial(), canti.getText(), utilidades.FormatoNumeros.formatear(articuloParaCombo.getVlrcosto() + ""), (utilidades.FormatoNumeros.formatear((articuloParaCombo.getVlrcosto().multiply(cantidades)) + ""))});
                BigDecimal totales = new BigDecimal(total.getText().replaceAll(",", ""));
                totales.add(articuloParaCombo.getVlrcosto().multiply(cantidades));
                total.setText(utilidades.FormatoNumeros.formatear(totales + ""));
                this.vcosto.setText("");
                this.vlrPromedio.setText(utilidades.FormatoNumeros.formatear(totales + ""));
                vparcial.setText("");
                canti.setText("");
                articuloParaCombo = null;
                cbarras.setText("");
                dcomercial.setText("");
                cbarras.requestFocus();
            } else {
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    if (tabla.getValueAt(i, 0).equals(articuloParaCombo)) {
                        BigDecimal x = new BigDecimal(tabla.getValueAt(i, 2).toString());
                        BigDecimal y = new BigDecimal(tabla.getValueAt(i, 3).toString().replaceAll(",", ""));

                        x.add(cantidades);

                        String z = utilidades.FormatoNumeros.formatear("" + (x.multiply(y)));
                        tabla.setValueAt(x, i, 2);
                        tabla.setValueAt(z, i, 4);

                        total.setText(z);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un Artículo");
        }
    }

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        adicionarArticuloAlCombo(Constantes.ADICIONAR_CON_BOTON);
    }//GEN-LAST:event_adicionarActionPerformed

    @SuppressWarnings("CallToThreadDumpStack")
    private void cbarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbarrasActionPerformed
        if (cbarras.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite Código de Barras");
        } else {
            try {
                articuloParaCombo = (Articulo) modelo.obtenerRegistro("obtenerArticuloPorCodigo", cbarras.getText());
                if (articuloParaCombo == null) {
                    JOptionPane.showMessageDialog(null, "Artículo no Existe ");
                } else {
                    adicionarArticuloAlCombo(Constantes.ADICIONAR_CON_LECTOR);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cbarrasActionPerformed

    private void cantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantiActionPerformed
        if (canti.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite una CANTIDAD");
        } else {
            int cantidades = Integer.parseInt(canti.getText());
            vparcial.setText("" + (new BigDecimal(cantidades).multiply(articuloParaCombo.getVlrcosto())));
            adicionar.setEnabled(true);
            adicionar.requestFocus();
        }
    }//GEN-LAST:event_cantiActionPerformed

    private void cantiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantiKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_cantiKeyTyped

    private void cbarrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbarrasKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_cbarrasKeyTyped

    private void quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarActionPerformed
        if (tabla.getSelectedRow() > -1) {
            double totales = Double.parseDouble(total.getText().replaceAll(",", ""));
            totales -= Double.parseDouble("" + tabla.getValueAt(tabla.getSelectedRow(), 4).toString().replaceAll(",", ""));
            modeloTabla.removeRow(tabla.getSelectedRow());
            total.setText(utilidades.FormatoNumeros.formatear(totales + ""));
        } else {
            JOptionPane.showMessageDialog(null, "No hay Artículo Seleccionado");
        }
    }//GEN-LAST:event_quitarActionPerformed

    private void descripcionComercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descripcionComercialActionPerformed
        codigoBarras.requestFocus();
    }//GEN-LAST:event_descripcionComercialActionPerformed

    private void codigoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoBarrasActionPerformed
        grupo.requestFocus();
    }//GEN-LAST:event_codigoBarrasActionPerformed

    private void valorgananciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorgananciaKeyReleased
        if (!valorganancia.getText().trim().equals("")) {
            if (!vlrCosto.getText().trim().equals("")) {
                double por = Double.parseDouble(vlrCosto.getText().replaceAll(",", ""));
                double pr2 = Double.parseDouble(valorganancia.getText());
                if (por > 0) {
                    porcentajeMinimo.setText(utilidades.FormatoNumeros.formatear(((pr2 - por) / por * 100) + ""));
                }
            } else {
                porcentajeMinimo.setText("0");
            }
        } else {
            porcentajeMinimo.setText("0");
        }
    }//GEN-LAST:event_valorgananciaKeyReleased

    private void porcentajeMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajeMinimoKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_porcentajeMinimoKeyTyped

    private void porcentajeSugeridoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajeSugeridoKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_porcentajeSugeridoKeyTyped

    private void vlrCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vlrCostoActionPerformed
        porcentajeMinimo.requestFocus();
    }//GEN-LAST:event_vlrCostoActionPerformed

    private void porcentajeSugeridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porcentajeSugeridoActionPerformed
        cantidadMinima.requestFocus();
    }//GEN-LAST:event_porcentajeSugeridoActionPerformed

    private void cantidadMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadMinimaActionPerformed
        guardar.requestFocus();
    }//GEN-LAST:event_cantidadMinimaActionPerformed

    private void porcentajeMinimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajeMinimoKeyReleased
        calcularMinimoVenta();
    }//GEN-LAST:event_porcentajeMinimoKeyReleased

    private void calcularMinimoVenta() {
        BigDecimal por = BigDecimal.ZERO;
        BigDecimal pr2 = pr2 = BigDecimal.ZERO;
        if (!porcentajeMinimo.getText().trim().equals("")) {
            pr2 = new BigDecimal(porcentajeMinimo.getText()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        }

        if (!vlrCosto.getText().trim().equals("")) {
            por = new BigDecimal(vlrCosto.getText().replaceAll(",", ""));
        }

        valorganancia.setText(utilidades.FormatoNumeros.formatear((por.multiply(pr2)).add(por).toString()));
    }

    private void calcularPorcentajeSugerido() {
        BigDecimal por = BigDecimal.ZERO;
        BigDecimal pr2 = BigDecimal.ZERO;
        if (!porcentajeSugerido.getText().trim().equals("")) {
            pr2 = new BigDecimal(porcentajeSugerido.getText()).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        }

        if (!vlrCosto.getText().trim().equals("")) {
            por = new BigDecimal(vlrCosto.getText().replaceAll(",", ""));
        }

        valorsugerido.setText(utilidades.FormatoNumeros.formatear((por.multiply(pr2)).add(por).toString()));
    }

    private void vlrCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vlrCostoKeyReleased
        vlrPromedio.setText(utilidades.FormatoNumeros.formatear(vlrCosto.getText()));
        calcularMinimoVenta();
        calcularPorcentajeSugerido();
    }//GEN-LAST:event_vlrCostoKeyReleased

    private void porcentajeSugeridoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajeSugeridoKeyReleased
        calcularPorcentajeSugerido();
    }//GEN-LAST:event_porcentajeSugeridoKeyReleased

    private void valorsugeridoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorsugeridoKeyReleased
        if (!valorsugerido.getText().trim().equals("")) {
            if (!vlrCosto.getText().trim().equals("")) {
                double por = Double.parseDouble(vlrCosto.getText().replaceAll(",", ""));
                double pr2 = Double.parseDouble(valorsugerido.getText());
                if (por > 0) {
                    porcentajeSugerido.setText(utilidades.FormatoNumeros.formatear(((pr2 - por) / por * 100) + ""));
                }
            } else {
                porcentajeSugerido.setText("0");
            }
        } else {
            porcentajeSugerido.setText("0");
        }
    }//GEN-LAST:event_valorsugeridoKeyReleased

    private void imagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagenMouseClicked
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar un fichero");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int sel = chooser.showOpenDialog(this);
        if (sel == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            imagen.setIcon(new ImageIcon());
            try {
                imagen.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
                imagen.setText("");

                byte[] image = null;
                FileInputStream in = new FileInputStream(selectedFile.getAbsoluteFile());
                BufferedInputStream reader = new BufferedInputStream(in);
                int length = reader.available();
                image = new byte[length];
                reader.read(image, 0, length);
                reader.close();

                this.articuloSeleccionado.setImagen(image);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al Guardar la copia: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_imagenMouseClicked

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private void adicionargActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionargActionPerformed
        new FormularioGrupo(padre, true, this);
    }//GEN-LAST:event_adicionargActionPerformed

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private void adicionarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarmActionPerformed
        new FormularioMarca(padre, true, this);
    }//GEN-LAST:event_adicionarmActionPerformed

    private void codigoBarrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoBarrasKeyTyped
        if (codigoBarras.getText().length() == 15) {
            evt.consume();
        } else if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_codigoBarrasKeyTyped

    private void descripcionComercialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descripcionComercialKeyTyped
        if (descripcionComercial.getText().length() == 45) {
            evt.consume();
        } else if (Character.isLetter(evt.getKeyChar())) {
            String letra = ("" + evt.getKeyChar()).toUpperCase();
            evt.consume();
            descripcionComercial.setText(descripcionComercial.getText().concat(letra));
        }
    }//GEN-LAST:event_descripcionComercialKeyTyped

    private void cantidadMinimaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadMinimaKeyTyped
        if (cantidadMinima.getText().length() == 10) {
            evt.consume();
        } else if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_cantidadMinimaKeyTyped

    private void cantiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantiKeyReleased
        calcularValorParcial();
    }//GEN-LAST:event_cantiKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionar;
    private javax.swing.JButton adicionarg;
    private javax.swing.JButton adicionarm;
    private javax.swing.JButton buscara;
    private javax.swing.JTextField canti;
    private javax.swing.JTextField cantidadMinima;
    private javax.swing.JTextField cbarras;
    private javax.swing.JTextField codigoBarras;
    private javax.swing.JPanel combo;
    private javax.swing.JTextField dcomercial;
    private javax.swing.JTextField descripcionComercial;
    private javax.swing.JComboBox estado;
    private javax.swing.JTextField existencia;
    private javax.swing.JTextField fechaucompra;
    private javax.swing.JTextField fechauventa;
    private javax.swing.JComboBox grupo;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox marca;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JTextField porcentajeMinimo;
    private javax.swing.JTextField porcentajeSugerido;
    private javax.swing.JButton quitar;
    private javax.swing.JButton salir;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tabla;
    private javax.swing.JComboBox tipo;
    private javax.swing.JLabel total;
    private javax.swing.JTextField valorganancia;
    private javax.swing.JTextField valorsugerido;
    private javax.swing.JTextField vcosto;
    private javax.swing.JTextField vlrCosto;
    private javax.swing.JTextField vlrPromedio;
    private javax.swing.JTextField vparcial;
    // End of variables declaration//GEN-END:variables

    @SuppressWarnings("CallToThreadDumpStack")
    public void buscar() {
        try {
            grupo.removeAllItems();
            marca.removeAllItems();

            List<Grupo> listaGrupos = (List<Grupo>) modelo.obtenerListado("obtenerGruposActivos", new Grupo());
            for (Grupo group : listaGrupos) {
                grupo.addItem(group);
            }

            List<Marca> listaMarcas = (List<Marca>) modelo.obtenerListado("obtenerMarcasActivas", new Marca());
            for (Marca marc : listaMarcas) {
                marca.addItem(marc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
