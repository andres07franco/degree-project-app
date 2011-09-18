package ventanas;

import beans.Caja;
import beans.Documento;
import beans.Empresa;
import beans.Usuario;
import db.Model;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import interfaces.Buscadores;
import interfaces.Constantes;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import utilidades.Fechavo;

public class Principal extends JFrame implements Buscadores {

    private Model modelo;
    private Usuario usuarioActual;
    private Empresa empresaActual;
    private int tra = 0;
    private int me = 0;
    private int di = 0;
    private JFrame ini;

    public Principal(JFrame ini) {

        this.ini = ini;
        this.modelo = Model.getInstance();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/img/icono.png")).getImage());
        this.setTitle("M&M Contable");
        this.setUndecorated(false);
        this.usuarioActual = modelo.getUsuario();
        this.empresaActual = usuarioActual.getEmpresa();
        setSize(927, 604);
        this.setLocationRelativeTo(null);
        initComponents();
        logo.setBounds(jPanel1.getWidth() / 2 - (logo.getWidth() / 2), jPanel1.getHeight(), logo.getWidth(), logo.getHeight());
        ventas.setBackground(new java.awt.Color(157, 245, 155));
        compras.setOpaque(true);
        ventas.setOpaque(true);
        inventario.setOpaque(true);
        configuracion.setOpaque(true);
        ventas.setOpaque(true);
        sel.setOpaque(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);

        arbol.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent evt) {
                //   seleccionarOpcion(evt.getPath().getLastPathComponent() + "");
                //System.out.println(   arbol.getSelectionPath().getLastPathComponent());
                // arbol.setSelectionPath(null);
            }
        });

        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mess = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);

        fechaac.setText("Fecha:" + ano + "-" + mess + "-" + dia);
        SimpleDateFormat formateador = new SimpleDateFormat("MMMM", new Locale("es_ES"));

        mes.setText("Mes: " + Fechavo.traducirmeses(formateador.format(new Date())));

        informes.setOpaque(true);
        caja_bancos.setOpaque(true);


        if (empresaActual != null) {
            nempresa.setText("Empresa: " + empresaActual.getNombre() + "   Usuario: " + usuarioActual.getUsuario());
        } else {
            nempresa.setText("Empresa: no hay Empresa registrada");
        }


        alquiler.setVisible(false);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        new FormularioInicioCaja(this, true);

        /*verficiamos que halla saldos inicales*/

        JDesktopPane dp = new JDesktopPane();

        dp.setBackground(Color.WHITE);
        FormularioPunoVentas fpv = new FormularioPunoVentas(tab, dp, this);
        dp.add(fpv);
        tab.addTab("Punto de Venta", dp);
        tab.setSelectedComponent(dp);
        tab.requestFocus();
        dp.requestFocus();
        // lu.requestFocus();

        logo1.setBounds(jPanel4.getWidth() / 2 - logo1.getWidth() / 2, jPanel4.getHeight() / 2 - logo1.getHeight() / 2, logo1.getWidth(), logo1.getHeight());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        sel = new javax.swing.JButton();
        configuracion = new javax.swing.JButton();
        inventario = new javax.swing.JButton();
        ventas = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        arbol = new javax.swing.JTree();
        compras = new javax.swing.JButton();
        caja_bancos = new javax.swing.JButton();
        informes = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        logo1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        nempresa = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        entrada = new javax.swing.JButton();
        elemento = new javax.swing.JButton();
        tercero = new javax.swing.JButton();
        caja = new javax.swing.JButton();
        alquiler = new javax.swing.JButton();
        diario = new javax.swing.JButton();
        mes = new javax.swing.JLabel();
        fechaac = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(231, 242, 252));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(224, 240, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(219, 237, 254));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(100, 193, 201));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 245, 155), 2, true));
        jPanel1.setLayout(null);

        sel.setBackground(new java.awt.Color(157, 245, 155));
        sel.setFont(new java.awt.Font("Arial", 1, 11));
        sel.setForeground(new java.awt.Color(0, 0, 102));
        sel.setText("Ventas");
        sel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sel.setContentAreaFilled(false);
        sel.setSelected(true);
        sel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selActionPerformed(evt);
            }
        });
        jPanel1.add(sel);
        sel.setBounds(10, 10, 180, 30);

        configuracion.setBackground(new java.awt.Color(121, 187, 252));
        configuracion.setFont(new java.awt.Font("Arial", 1, 11));
        configuracion.setForeground(new java.awt.Color(0, 0, 102));
        configuracion.setText("Configuración");
        configuracion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        configuracion.setContentAreaFilled(false);
        configuracion.setSelected(true);
        configuracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                configuracionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                configuracionMouseExited(evt);
            }
        });
        configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracionActionPerformed(evt);
            }
        });
        jPanel1.add(configuracion);
        configuracion.setBounds(10, 440, 180, 30);

        inventario.setBackground(new java.awt.Color(121, 187, 252));
        inventario.setFont(new java.awt.Font("Arial", 1, 11));
        inventario.setForeground(new java.awt.Color(0, 0, 102));
        inventario.setText("Inventario");
        inventario.setActionCommand("jButton3");
        inventario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        inventario.setContentAreaFilled(false);
        inventario.setSelected(true);
        inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventarioMouseExited(evt);
            }
        });
        inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioActionPerformed(evt);
            }
        });
        jPanel1.add(inventario);
        inventario.setBounds(10, 350, 180, 30);

        ventas.setBackground(new java.awt.Color(121, 187, 252));
        ventas.setFont(new java.awt.Font("Arial", 1, 11));
        ventas.setForeground(new java.awt.Color(0, 0, 102));
        ventas.setText("Ventas");
        ventas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ventas.setContentAreaFilled(false);
        ventas.setSelected(true);
        ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ventasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ventasMouseExited(evt);
            }
        });
        ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasActionPerformed(evt);
            }
        });
        jPanel1.add(ventas);
        ventas.setBounds(10, 290, 180, 30);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Ventas");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Registrar Ventas");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Cuentas X Cobrar");
        treeNode1.add(treeNode2);
        arbol.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        arbol.setEnabled(false);
        arbol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arbolMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(arbol);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 40, 180, 250);

        compras.setBackground(new java.awt.Color(121, 187, 252));
        compras.setFont(new java.awt.Font("Arial", 1, 11));
        compras.setForeground(new java.awt.Color(0, 0, 102));
        compras.setText("Compras");
        compras.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        compras.setContentAreaFilled(false);
        compras.setSelected(true);
        compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comprasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                comprasMouseExited(evt);
            }
        });
        compras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprasActionPerformed(evt);
            }
        });
        jPanel1.add(compras);
        compras.setBounds(10, 320, 180, 30);

        caja_bancos.setBackground(new java.awt.Color(121, 187, 252));
        caja_bancos.setFont(new java.awt.Font("Arial", 1, 11));
        caja_bancos.setForeground(new java.awt.Color(0, 0, 102));
        caja_bancos.setText("Caja");
        caja_bancos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        caja_bancos.setContentAreaFilled(false);
        caja_bancos.setSelected(true);
        caja_bancos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                caja_bancosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                caja_bancosMouseExited(evt);
            }
        });
        caja_bancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caja_bancosActionPerformed(evt);
            }
        });
        jPanel1.add(caja_bancos);
        caja_bancos.setBounds(10, 380, 180, 30);

        informes.setBackground(new java.awt.Color(121, 187, 252));
        informes.setFont(new java.awt.Font("Arial", 1, 11));
        informes.setForeground(new java.awt.Color(0, 0, 102));
        informes.setText("Informes");
        informes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        informes.setContentAreaFilled(false);
        informes.setSelected(true);
        informes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                informesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                informesMouseExited(evt);
            }
        });
        informes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informesActionPerformed(evt);
            }
        });
        jPanel1.add(informes);
        informes.setBounds(10, 410, 180, 30);

        jPanel3.setBackground(new java.awt.Color(100, 193, 201));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(157, 245, 155), 2, true));

        tab.setBackground(new java.awt.Color(255, 255, 255));
        tab.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                tabComponentRemoved(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(null);

        logo1.setBackground(new java.awt.Color(153, 204, 255));
        logo1.setFont(new java.awt.Font("Tahoma", 1, 12));
        logo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loghomasgrande.png"))); // NOI18N
        jPanel4.add(logo1);
        logo1.setBounds(90, 80, 642, 475);

        tab.addTab("Inicio", jPanel4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab.getAccessibleContext().setAccessibleName("inicio");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        nempresa.setFont(new java.awt.Font("Tahoma", 1, 14));
        nempresa.setText("EMPRESA XXX");

        jPanel6.setBackground(new java.awt.Color(153, 205, 255));

        jToolBar1.setBackground(new java.awt.Color(100, 193, 201));
        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setRollover(true);

        entrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/blq_registradora.png"))); // NOI18N
        entrada.setText("Punto Vta");
        entrada.setFocusable(false);
        entrada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        entrada.setOpaque(false);
        entrada.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaActionPerformed(evt);
            }
        });
        jToolBar1.add(entrada);

        elemento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flour-32x32.png"))); // NOI18N
        elemento.setText("Artículos");
        elemento.setFocusable(false);
        elemento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        elemento.setOpaque(false);
        elemento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        elemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elementoActionPerformed(evt);
            }
        });
        jToolBar1.add(elemento);

        tercero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/User-32x32.png"))); // NOI18N
        tercero.setText("Terceros");
        tercero.setFocusable(false);
        tercero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tercero.setOpaque(false);
        tercero.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terceroActionPerformed(evt);
            }
        });
        jToolBar1.add(tercero);

        caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Inbox-32x32.png"))); // NOI18N
        caja.setText("Caja");
        caja.setFocusable(false);
        caja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        caja.setOpaque(false);
        caja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaActionPerformed(evt);
            }
        });
        jToolBar1.add(caja);

        alquiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/My-Documents-32x32 (1).png"))); // NOI18N
        alquiler.setText("Alquiler");
        alquiler.setFocusable(false);
        alquiler.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        alquiler.setOpaque(false);
        alquiler.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alquilerActionPerformed(evt);
            }
        });
        jToolBar1.add(alquiler);

        diario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/metalshutdownfrontblue.png"))); // NOI18N
        diario.setText("Cerrar Sesión ");
        diario.setFocusable(false);
        diario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        diario.setOpaque(false);
        diario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        diario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diarioActionPerformed(evt);
            }
        });
        jToolBar1.add(diario);

        mes.setBackground(new java.awt.Color(255, 255, 255));
        mes.setFont(new java.awt.Font("Tahoma", 1, 11));
        mes.setForeground(new java.awt.Color(0, 0, 204));
        mes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mes.setText("Mes:");
        mes.setOpaque(true);

        fechaac.setBackground(new java.awt.Color(255, 255, 255));
        fechaac.setFont(new java.awt.Font("Tahoma", 1, 11));
        fechaac.setForeground(new java.awt.Color(0, 1, 204));
        fechaac.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fechaac.setText("Fecha: 2009/19/02");
        fechaac.setOpaque(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaac)
                    .addComponent(mes))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaac, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        logo.setBackground(new java.awt.Color(255, 255, 255));
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mmcontable.png"))); // NOI18N
        logo.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nempresa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
                .addContainerGap())
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
    JButton sele = new JButton();

    public boolean verificarSaldosIniciale() {
        Documento d = null;
        try {
            d = (Documento) Model.getInstance().obtenerRegistro("obtenerSaldosIniciales");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (d != null) {

            if (d.getEstado().equals(Constantes.ESTADO_DOCUMENTO_INICIADO)) {
                return true;
            }
        }
        return false;
    }

    public void seleccionar(JButton b) {

        ventas.setBackground(new java.awt.Color(121, 187, 252));
        inventario.setBackground(new java.awt.Color(121, 187, 252));
        compras.setBackground(new java.awt.Color(121, 187, 252));
        configuracion.setBackground(new java.awt.Color(121, 187, 252));
        caja_bancos.setBackground(new java.awt.Color(121, 187, 252));
        informes.setBackground(new java.awt.Color(121, 187, 252));
        sele = b;
        b.setBackground(new java.awt.Color(157, 245, 155));
    }
    private void comprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprasActionPerformed
        arbol.setEnabled(true);
        sel.setText("Compras");
        seleccionar(compras);
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Compras");

        treeNode1.add(new DefaultMutableTreeNode("Registrar Compra"));
       // treeNode1.add(new DefaultMutableTreeNode("Devoluciones"));
        //   treeNode1.add(new DefaultMutableTreeNode("Movimientos Compras"));
        treeNode1.add(new DefaultMutableTreeNode("Cuentas X Pagar"));

        arbol.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
}//GEN-LAST:event_comprasActionPerformed

    private void ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasActionPerformed
        // TODO add your handling code here:
        if (verificarSaldosIniciale()) {
            arbol.setEnabled(true);
            sel.setText("Ventas");
            seleccionar(ventas);
            javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Ventas");

            treeNode1.add(new DefaultMutableTreeNode("Registrar Venta"));
           // treeNode1.add(new DefaultMutableTreeNode("Devoluciones"));
            /*  treeNode1.add(new DefaultMutableTreeNode("Movimientos Ventas"));*/
            treeNode1.add(new DefaultMutableTreeNode("Cuentas X Cobrar"));

            arbol.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        } else {
            JOptionPane.showMessageDialog(null, "Debe inicializar Saldos Iníciales para poder acceder a este Módulo", "Información", JOptionPane.INFORMATION_MESSAGE);
        }


}//GEN-LAST:event_ventasActionPerformed

    public int esta(String title) {
        for (int i = 0; i < tab.getTabCount(); i++) {
            if (tab.getTitleAt(i).equals(title)) {
                return i;
            }
        }
        return -1;
    }

    private void seleccionarOpcion(String sele) {

        if (sele.equals("Datos de la Empresa")) {
            if (esta(sele) == -1) {
                JDesktopPane dp = new JDesktopPane();
                Formulario_Empresa fde = new Formulario_Empresa(tab, dp, this);
                dp.add(fde);
                dp.setBackground(Color.WHITE);
                JLabel et = new JLabel();
                et.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loghomasgrande.png"))); // NOI18N
                et.setBounds(jPanel4.getWidth() / 2 - logo1.getWidth() / 2, jPanel4.getHeight() / 2 - logo1.getHeight() / 2, logo1.getWidth(), logo1.getHeight());
                dp.add(et, javax.swing.JLayeredPane.DEFAULT_LAYER);
                tab.addTab("Datos de la Empresa", dp);
                fde.show();
                tab.setSelectedComponent(dp);
            } else {
                tab.setSelectedIndex(esta(sele));
            }
        } else if (sele.equals("Personalizar Factura")) {
            if (esta(sele) == -1) {
                JDesktopPane dp = new JDesktopPane();
                Formulario_Edita_Factura fde = new Formulario_Edita_Factura(tab, dp);
                dp.add(fde);
                dp.setBackground(Color.WHITE);
                JLabel et = new JLabel();
                et.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loghomasgrande.png"))); // NOI18N
                et.setBounds(jPanel4.getWidth() / 2 - logo1.getWidth() / 2, jPanel4.getHeight() / 2 - logo1.getHeight() / 2, logo1.getWidth(), logo1.getHeight());
                dp.add(et, javax.swing.JLayeredPane.DEFAULT_LAYER);
                tab.addTab("Personalizar Factura", dp);
                tab.setSelectedComponent(dp);
                fde.show();
            } else {
                tab.setSelectedIndex(esta(sele));
            }
        } else if (sele.equals("Usuarios del Sistema")) {
            if (esta(sele) == -1) {
                JDesktopPane dp = new JDesktopPane();
                dp.setBackground(Color.WHITE);
                ListaUsuarios lu = new ListaUsuarios(tab, dp, this);
                dp.add(lu);
                tab.addTab("Usuarios del Sistema", lu);
                tab.setSelectedComponent(lu);
            } else {
                tab.setSelectedIndex(esta(sele));
            }


        } else if (sele.equals("Marcas")) {
            if (esta(sele) == -1) {
                JDesktopPane dp = new JDesktopPane();

                dp.setBackground(Color.WHITE);
                ListaMarcas lu = new ListaMarcas(tab, this);
                dp.add(lu);
                tab.addTab("Marcas", lu);
                tab.setSelectedComponent(lu);
            } else {
                tab.setSelectedIndex(esta(sele));
            }
        } else if (sele.equals("Grupos")) {
            if (esta(sele) == -1) {
                JDesktopPane dp = new JDesktopPane();

                dp.setBackground(Color.WHITE);
                ListaGrupos lu = new ListaGrupos(tab, this);
                dp.add(lu);
                tab.addTab("Grupos", lu);
                tab.setSelectedComponent(lu);
            } else {
                tab.setSelectedIndex(esta(sele));
            }

        } else if (sele.equals("Artículos")) {//Elementos bajo Stock
            if (esta(sele) == -1) {
                JDesktopPane dp = new JDesktopPane();
                dp.setBackground(Color.WHITE);
                ListaArticulos lu = new ListaArticulos(tab, this);
                dp.add(lu);
                tab.addTab("Artículos", lu);
                tab.setSelectedComponent(lu);
            } else {
                tab.setSelectedIndex(esta(sele));
            }
        } else if (sele.equals("Alquiler")) {//
            if (esta(sele) == -1) {
                JDesktopPane dp = new JDesktopPane();

                dp.setBackground(Color.WHITE);
                // ListaPrefacturas lu = new ListaPrefacturas(tab, dp, m, this);
                //dp.add(lu);
                //tab.addTab("Alquiler", lu);
                //tab.setSelectedComponent(lu);
            } else {
                tab.setSelectedIndex(esta(sele));
            }

        } else if (sele.equals("Registrar Compra")) {
            if (esta("Compras") == -1) {
                JDesktopPane dp = new JDesktopPane();
                dp.setBackground(Color.WHITE);
                ListaCompras lu = new ListaCompras(tab, dp, this);
                dp.add(lu);
                tab.addTab("Compras", lu);
                tab.setSelectedComponent(lu);
            } else {
                tab.setSelectedIndex(esta("Compras"));
            }

        } else if (sele.equals("Registrar Venta")) {
            if (esta("Ventas") == -1) {
                JDesktopPane dp = new JDesktopPane();

                dp.setBackground(Color.WHITE);
                ListaVentas lu = new ListaVentas(tab, dp, this);
                dp.add(lu);
                tab.addTab("Ventas", lu);
                tab.setSelectedComponent(lu);
            } else {
                tab.setSelectedIndex(esta("Ventas"));
            }

        } else if (sele.equals("Ajustes")) {
            // Formulario_Ajuste fa = new Formulario_Ajuste(this, true, m);
            //fa.setVisible(true);
        } else if (sele.equals("Kardex")) {
              new VentanaKardex(this, true).setVisible(true);
        } else if (sele.equals("Imprimir Documento")) {
              new VentanaImprimeFactura(this, true).setVisible(true);
        }else if (sele.equals("Ingresos/Egresos")) {//||Lista_Ingresos_Egresos
            if (esta("Ingresos/Egresos") == -1) {
                JDesktopPane dp = new JDesktopPane();
                dp.setBackground(Color.WHITE);
                ListaEgresosIngresos lu = new ListaEgresosIngresos(tab, dp, this);
                dp.add(lu);
                tab.addTab("Ingresos/Egresos", lu);
                tab.setSelectedComponent(lu);
            } else {
                tab.setSelectedIndex(esta("Ingresos/Egresos"));
            }
        } else if (sele.equals("Cortes de Caja")) {//sele.equals("Elemento")||
//            new Formulario_Cierre_Corte_Caja(this, true, m, this);
        } else if (sele.equals("Saldos Iniciales")) {

            new Formulario_Saldos_Iniciales(this, true, this);


        } else if (sele.equals("Reintegro")) {
            if (esta("Reintegro") == -1) {
                JDesktopPane dp = new JDesktopPane();

                dp.setBackground(Color.WHITE);
                //  ListaAlquileres lu = new ListaAlquileres(tab, dp, m, this);
                //dp.add(lu);
                //tab.addTab("Reintegro", lu);
                //tab.setSelectedComponent(lu);
            } else {
                tab.setSelectedIndex(esta("Reintegro"));
            }

        } else if (sele.equals("Inventario")) {
            new VentanaInventario(this, true).setVisible(true);
        } else if (sele.equals("Artículo en Mínimo")) {
            Map parametro = new HashMap();
            new utilidades.Reporte().runReporte("reportes/Articulos en Minimo.jasper", parametro);
        } else if (sele.equals("Diario de Ventas (Arqueos)")) {
            new VentanaReporteCaja(this, true).setVisible(true);
        } else if (sele.equals("Resumen de Caja")) {
            new VentanaReporteCaja(this, true).setVisible(true);

        } else if (sele.equals("Informe de Caja Actual")) {
            try {
                Caja cajaDia = (Caja) modelo.obtenerRegistro("obtenerCajaDia");
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

                Map parametro = new HashMap();
                parametro.put("fecha", formato.format(cajaDia.getFechaabre()));
                parametro.put("fecha2", formato.format(cajaDia.getFechaabre()));

                new utilidades.Reporte(this).runReporte("reportes/Reporte de Caja.jasper", parametro);

            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (sele.equals("Cuentas X Cobrar")) {
                Map parametro = new HashMap();
                new utilidades.Reporte(this).runReporte("reportes/CXC.jasper", parametro);

        }else if (sele.equals("Cuentas X Pagar")) { 
                Map parametro = new HashMap();
                new utilidades.Reporte(this).runReporte("reportes/CXP.jasper", parametro);

        }

    }
    //Tarjeta Kardex
    File f;

    public void abrirCopia() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Abrir un fichero");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int sel = chooser.showOpenDialog(this);
        if (sel == JFileChooser.APPROVE_OPTION) {
            f = chooser.getSelectedFile();

            Runtime rt = Runtime.getRuntime();
            try {

                String command = "mysql  --user root --password=  coal < " + f.getAbsolutePath();
                //  rt.exec(command);
                ejecutarBat();
                JOptionPane.showMessageDialog(this, "Su Copia ha sido Restaurada en " + f.getAbsolutePath());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al Restaurar la copia: " + ex.getMessage());
            }
        } else {
            return;
        }
    }

    public void guardarCopia() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar un fichero");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int sel = chooser.showSaveDialog(this);
        if (sel == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            Runtime rt = Runtime.getRuntime();
            try {
                String command = "C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\mysqldump --opt --user root --password=  coal -r " + selectedFile.getAbsolutePath();
                rt.exec(command);
                System.out.println(command);
                JOptionPane.showMessageDialog(this, "Su Copia ha sido guardad en " + selectedFile.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al Guardar la copia: " + ex.getMessage());
            }
        } else {
            return;
        }
    }

    public void crearBat() {
        try {

            String ruta = f.getPath().substring(0, f.getPath().lastIndexOf("\\"));
            FileOutputStream out = new FileOutputStream(new File(ruta + "\\scrip.bat"));
            String bat = "set path=C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin \nmysql --user root --password=  coal < " + f.getAbsolutePath() + "\nexit";

            for (int i = 0; i < bat.length(); i++) {
                out.write(bat.charAt(i));
            }
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void ejecutarBat() {


        try {
            crearBat();
            String ruta = f.getPath().substring(0, f.getPath().lastIndexOf('\\'));
            Runtime r = Runtime.getRuntime();
            r.exec("cmd /c start " + ruta + "\\scrip.bat");
        } catch (Exception er) {
            er.printStackTrace();
        }

    }

        private void inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioActionPerformed
            // TODO add your handling code here:
            arbol.setEnabled(true);
            sel.setText("Inventarios");
            seleccionar(inventario);
            javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Inventarios");

            treeNode1.add(new DefaultMutableTreeNode("Artículos"));
            treeNode1.add(new DefaultMutableTreeNode("Kardex"));
            //    treeNode1.add(new DefaultMutableTreeNode("Ajustes"));
            treeNode1.add(new DefaultMutableTreeNode("Grupos"));
            treeNode1.add(new DefaultMutableTreeNode("Marcas"));
            //     treeNode1.add(new DefaultMutableTreeNode("Alquiler"));
            //     treeNode1.add(new DefaultMutableTreeNode("Reintegro"));
            arbol.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

}//GEN-LAST:event_inventarioActionPerformed

    private void cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaActionPerformed
        // TODO add your handling code here:
        try {
            Caja cajaDia = (Caja) modelo.obtenerRegistro("obtenerCajaDia");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            Map parametro = new HashMap();
            parametro.put("fecha", formato.format(cajaDia.getFechaabre()));
            parametro.put("fecha2", formato.format(cajaDia.getFechaabre()));
            this.dispose();
            new utilidades.Reporte(this).runReporte("reportes/Reporte de Caja.jasper", parametro);

        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cajaActionPerformed

    private void elementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elementoActionPerformed
        // TODO add your handling code here:
        //Elementos bajo Stock
        if (esta("Artículos") == -1) {
            JDesktopPane dp = new JDesktopPane();
            dp.setBackground(Color.WHITE);
            ListaArticulos lu = new ListaArticulos(tab, this);
            dp.add(lu);
            tab.addTab("Artículos", lu);
            tab.setSelectedComponent(lu);
        } else {
            tab.setSelectedIndex(esta("Artículos"));
        }

}//GEN-LAST:event_elementoActionPerformed

    private void comprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comprasMouseEntered
        // TODO add your handling code here:
        ((JButton) evt.getSource()).setBackground(new java.awt.Color(157, 245, 155));
}//GEN-LAST:event_comprasMouseEntered

    private void comprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comprasMouseExited
        // TODO add your handling code here:
        if (!sele.equals(evt.getSource())) {
            ((JButton) evt.getSource()).setBackground(new java.awt.Color(121, 187, 252));
        }
}//GEN-LAST:event_comprasMouseExited

    private void inventarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventarioMouseEntered
        // TODO add your handling code here:
        ((JButton) evt.getSource()).setBackground(new java.awt.Color(157, 245, 155));
}//GEN-LAST:event_inventarioMouseEntered

    private void inventarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventarioMouseExited
        // TODO add your handling code here:
        if (!sele.equals(evt.getSource())) {
            ((JButton) evt.getSource()).setBackground(new java.awt.Color(121, 187, 252));
        }
}//GEN-LAST:event_inventarioMouseExited

    private void ventasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasMouseEntered
        // TODO add your handling code here:
        ((JButton) evt.getSource()).setBackground(new java.awt.Color(157, 245, 155));
}//GEN-LAST:event_ventasMouseEntered

    private void ventasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasMouseExited
        // TODO add your handling code here:
        if (!sele.equals(evt.getSource())) {
            ((JButton) evt.getSource()).setBackground(new java.awt.Color(121, 187, 252));
        }
}//GEN-LAST:event_ventasMouseExited

    private void entradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaActionPerformed
        // TODO add your handling code here:ç
        JDesktopPane dp = new JDesktopPane();
        if (esta("Punto de Ventas") == -1) {
            dp.setBackground(Color.WHITE);
            FormularioPunoVentas fpv = new FormularioPunoVentas(tab, dp, this);
            dp.add(fpv);
            tab.addTab("Punto de Venta", dp);
            tab.setSelectedComponent(dp);
            tab.requestFocus();
            dp.requestFocus();
        } else {
            tab.setSelectedIndex(esta("Punto de Ventas"));
        }
    }//GEN-LAST:event_entradaActionPerformed

    private void terceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terceroActionPerformed
        if (esta("Terceros") == -1) {
            JDesktopPane dp = new JDesktopPane();

            dp.setBackground(Color.WHITE);
            ListaTercero lu = new ListaTercero(tab, dp, this);
            dp.add(lu);
            tab.addTab("Terceros", lu);
            tab.setSelectedComponent(lu);
        } else {
            tab.setSelectedIndex(esta("Terceros"));
        }
    }//GEN-LAST:event_terceroActionPerformed

    private void diarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diarioActionPerformed
        // TODO add your handling code here:
        int confirmado = JOptionPane.showConfirmDialog(this, "Esta seguro que desea cerrar la Sesión?", "¿Salir?", JOptionPane.YES_NO_OPTION);

        if (JOptionPane.OK_OPTION == confirmado) {
            this.dispose();
            ini.setVisible(true);
        }

    }//GEN-LAST:event_diarioActionPerformed

    private void alquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alquilerActionPerformed
        // TODO add your handling code here:
        if (esta("Alquiler") == -1) {
            JDesktopPane dp = new JDesktopPane();

            dp.setBackground(Color.WHITE);
//            ListaPrefacturas lu = new ListaPrefacturas(tab, dp, m, this);
            //          dp.add(lu);
            //        tab.addTab("Alquiler", lu);
            //      tab.setSelectedComponent(lu);
        } else {
            tab.setSelectedIndex(esta("Alquiler"));
        }
    }//GEN-LAST:event_alquilerActionPerformed

    private void informesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informesActionPerformed
        // TODO add your handling code here:
        arbol.setEnabled(true);
        sel.setText("Informes");
        seleccionar(informes);
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Informes");

        treeNode1.add(new DefaultMutableTreeNode("Imprimir Documento"));
        treeNode1.add(new DefaultMutableTreeNode("Inventario"));
        treeNode1.add(new DefaultMutableTreeNode("Artículo en Mínimo"));
        treeNode1.add(new DefaultMutableTreeNode("Diario de Ventas (Arqueos)"));
        treeNode1.add(new DefaultMutableTreeNode("Cuentas X Pagar"));
        treeNode1.add(new DefaultMutableTreeNode("Cuentas X Cobrar"));

        arbol.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
}//GEN-LAST:event_informesActionPerformed

    private void informesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_informesMouseExited
        // TODO add your handling code here:
        if (!sele.equals(evt.getSource())) {
            ((JButton) evt.getSource()).setBackground(new java.awt.Color(121, 187, 252));
        }
}//GEN-LAST:event_informesMouseExited

    private void informesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_informesMouseEntered
        // TODO add your handling code here:
        ((JButton) evt.getSource()).setBackground(new java.awt.Color(157, 245, 155));
    }//GEN-LAST:event_informesMouseEntered

    private void caja_bancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caja_bancosActionPerformed
        // TODO add your handling code here:
        arbol.setEnabled(true);
        sel.setText("Caja");
        seleccionar(caja_bancos);
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Caja y Bancos");

        //   treeNode1.add(new DefaultMutableTreeNode("Cortes de Caja"));
        treeNode1.add(new DefaultMutableTreeNode("Informe de Caja Actual"));
        treeNode1.add(new DefaultMutableTreeNode("Resumen de Caja"));
        treeNode1.add(new DefaultMutableTreeNode("Ingresos/Egresos"));
        //   treeNode1.add(new DefaultMutableTreeNode("Manejo Bancos"));

        arbol.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
}//GEN-LAST:event_caja_bancosActionPerformed

    private void caja_bancosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caja_bancosMouseExited
        // TODO add your handling code here:
        if (!sele.equals(evt.getSource())) {
            ((JButton) evt.getSource()).setBackground(new java.awt.Color(121, 187, 252));
        }
}//GEN-LAST:event_caja_bancosMouseExited

    private void caja_bancosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caja_bancosMouseEntered
        // TODO add your handling code here:
        ((JButton) evt.getSource()).setBackground(new java.awt.Color(157, 245, 155));
}//GEN-LAST:event_caja_bancosMouseEntered

    private void configuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracionActionPerformed
        // TODO add your handling code here:

        arbol.setEnabled(true);
        sel.setText("Configuración");
        seleccionar(configuracion);
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Configuración");

        treeNode1.add(new DefaultMutableTreeNode("Datos de la Empresa"));
        treeNode1.add(new DefaultMutableTreeNode("Personalizar Factura"));
        treeNode1.add(new DefaultMutableTreeNode("Usuarios del Sistemas"));
        treeNode1.add(new DefaultMutableTreeNode("Saldos Iniciales"));

        arbol.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
}//GEN-LAST:event_configuracionActionPerformed

    private void configuracionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionMouseExited
        // TODO add your handling code here:
        if (!sele.equals(evt.getSource())) {
            ((JButton) evt.getSource()).setBackground(new java.awt.Color(121, 187, 252));
        }
}//GEN-LAST:event_configuracionMouseExited

    private void configuracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionMouseEntered
        // TODO add your handling code here:
        ((JButton) evt.getSource()).setBackground(new java.awt.Color(157, 245, 155));
}//GEN-LAST:event_configuracionMouseEntered

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
        logo.setBounds(jPanel1.getWidth() / 2 - (logo.getWidth() / 2), jPanel1.getHeight(), logo.getWidth(), logo.getHeight());
    }//GEN-LAST:event_formWindowStateChanged

    private void tabComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabComponentRemoved
    }//GEN-LAST:event_tabComponentRemoved

    private void selActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selActionPerformed

    private void arbolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arbolMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() % 2 == 0) {
            seleccionarOpcion(arbol.getSelectionPath().getLastPathComponent() + "");
        }

    }//GEN-LAST:event_arbolMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int confirmado = JOptionPane.showConfirmDialog(this, "Está seguro de salir de M&MContable ?", "¿Salir?", JOptionPane.YES_NO_OPTION);

        if (JOptionPane.OK_OPTION == confirmado) {
            this.dispose();
        }

    }//GEN-LAST:event_formWindowClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alquiler;
    private javax.swing.JTree arbol;
    private javax.swing.JButton caja;
    private javax.swing.JButton caja_bancos;
    private javax.swing.JButton compras;
    private javax.swing.JButton configuracion;
    private javax.swing.JButton diario;
    private javax.swing.JButton elemento;
    private javax.swing.JButton entrada;
    private javax.swing.JLabel fechaac;
    private javax.swing.JButton informes;
    private javax.swing.JButton inventario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel mes;
    private javax.swing.JLabel nempresa;
    private javax.swing.JButton sel;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JButton tercero;
    private javax.swing.JButton ventas;
    // End of variables declaration//GEN-END:variables

    public void buscar(String bus) {
    }

    public void buscar() {

        Empresa empresa = null;

        try {
            empresa = (Empresa) modelo.obtenerRegistro("obtenerEmpresa", usuarioActual.getId());
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (empresa != null) {
            nempresa.setText("Empresa: " + empresa.getNombre());
        } else {
            nempresa.setText("Empresa: no hay Empresa registrada");
        }

        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mess = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DATE);


        SimpleDateFormat formateador = new SimpleDateFormat("MMMM", new Locale("es_ES"));

        mes.setText("Mes: " + Fechavo.traducirmeses(formateador.format(new Date())));
        fechaac.setText("Fecha:" + ano + "-" + mess + "-" + dia);
    }
}