/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen1labprogra2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author David Suazo Palao / Ian Suazo Palao
 */
public class GUIBiblioteca extends JFrame {
    private final BibliotecaServicio service;
    private JTextField txtcode, txtTitle, txtnivel, txtimg, txtextra1, txtextra2, txtuserid, txtusernombre, txtopuser, txtopmat, txtdias;
    private JLabel lblimg, lblcomp;
    private JTextArea txtConsola;

    // Panel raíz con CardLayout: pantalla de Bienvenida <-> Aplicación principal
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);
    private static final String CARD_BIENVENIDA = "BIENVENIDA";
    private static final String CARD_APP = "APP";

    public GUIBiblioteca(BibliotecaServicio service) {
        this.service = service;
        setTitle("Sistema de Gestión de Biblioteca");

        // Tamaño compacto y centrado
        setSize(1080, 740);
        setMinimumSize(new Dimension(1000, 680));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cards.add(construirPantallaBienvenida(), CARD_BIENVENIDA);
        cards.add(construirPantallaApp(), CARD_APP);
        setContentPane(cards);
        cardLayout.show(cards, CARD_BIENVENIDA);
    }

    // ================================================================
    // ================= PANTALLA DE BIENVENIDA =======================
    // ================================================================
    private JPanel construirPantallaBienvenida() {
        Color azulOscuro = new Color(25, 42, 74);
        Color azulAcento = new Color(64, 116, 191);
        Color textoClaro = new Color(235, 240, 248);

        JPanel fondo = new JPanel(new GridBagLayout());
        fondo.setBackground(azulOscuro);

        JPanel tarjeta = new JPanel(new GridBagLayout());
        tarjeta.setBackground(new Color(35, 55, 92));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(azulAcento, 2, true),
                BorderFactory.createEmptyBorder(40, 60, 40, 60)));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.insets = new Insets(8, 0, 8, 0);
        gc.gridy = 0;

        JLabel lblIcono = new JLabel("\uD83D\uDCDA", SwingConstants.CENTER);
        lblIcono.setFont(new Font("SansSerif", Font.PLAIN, 64));
        tarjeta.add(lblIcono, gc);

        gc.gridy++;
        JLabel lblTitulo = new JLabel("Bienvenido a la Biblioteca");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblTitulo.setForeground(textoClaro);
        tarjeta.add(lblTitulo, gc);

        gc.gridy++;
        JLabel lblSubtitulo = new JLabel("Sistema de Gestión de Materiales, Usuarios y Préstamos");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lblSubtitulo.setForeground(new Color(190, 205, 225));
        tarjeta.add(lblSubtitulo, gc);

        gc.gridy++;
        gc.insets = new Insets(28, 0, 8, 0);
        JButton btnIngresar = new JButton("Ingresar al Sistema  \u2192");
        btnIngresar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnIngresar.setBackground(azulAcento);
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        btnIngresar.addActionListener(e -> cardLayout.show(cards, CARD_APP));
        tarjeta.add(btnIngresar, gc);

        gc.gridy++;
        gc.insets = new Insets(20, 0, 0, 0);
        JLabel lblCreditos = new JLabel("David Suazo Palao · Ian Suazo Palao");
        lblCreditos.setFont(new Font("SansSerif", Font.ITALIC, 12));
        lblCreditos.setForeground(new Color(150, 165, 190));
        tarjeta.add(lblCreditos, gc);

        GridBagConstraints gcFondo = new GridBagConstraints();
        fondo.add(tarjeta, gcFondo);

        return fondo;
    }

    // ================================================================
    // ================= PANTALLA PRINCIPAL (APP) ======================
    // ================================================================
    private JPanel construirPantallaApp() {
        JPanel raiz = new JPanel(new BorderLayout(8, 8));
        raiz.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        // ---- Barra superior con botón para volver al menú de bienvenida ----
        JPanel barraSuperior = new JPanel(new BorderLayout());
        barraSuperior.setBackground(new Color(25, 42, 74));
        barraSuperior.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JLabel lblTituloApp = new JLabel("\uD83D\uDCDA  Sistema de Gestión de Biblioteca");
        lblTituloApp.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloApp.setForeground(Color.WHITE);
        barraSuperior.add(lblTituloApp, BorderLayout.WEST);

        JButton btnMenu = new JButton("\u2190 Menú Principal");
        btnMenu.setFocusPainted(false);
        btnMenu.addActionListener(e -> cardLayout.show(cards, CARD_BIENVENIDA));
        barraSuperior.add(btnMenu, BorderLayout.EAST);

        raiz.add(barraSuperior, BorderLayout.NORTH);

        // ================= PANEL IZQUIERDO (FORMULARIOS EN PESTAÑAS) =================
        JTabbedPane tabsIzq = new JTabbedPane();
        tabsIzq.setPreferredSize(new Dimension(500, 0));
        tabsIzq.setFont(new Font("SansSerif", Font.BOLD, 13));

        // 1. Panel Material
        JPanel p_material = new JPanel(new BorderLayout(4, 4));
        p_material.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel p_mat_grid = new JPanel(new GridLayout(6, 2, 4, 4));
        txtcode = new JTextField();
        txtTitle = new JTextField();
        txtnivel = new JTextField("BAJO");
        txtimg = new JTextField("Portadas/LaDivinaComedia.jpg");
        txtextra1 = new JTextField();
        txtextra2 = new JTextField();

        p_mat_grid.add(new JLabel(" Código:"));
        p_mat_grid.add(txtcode);
        p_mat_grid.add(new JLabel(" Título:"));
        p_mat_grid.add(txtTitle);
        p_mat_grid.add(new JLabel(" Nivel (BAJO, MEDIO, ALTO):"));
        p_mat_grid.add(txtnivel);
        p_mat_grid.add(new JLabel(" Ruta Imagen:"));
        p_mat_grid.add(txtimg);
        p_mat_grid.add(new JLabel(" Extra 1 (Autor/Edición/Min):"));
        p_mat_grid.add(txtextra1);
        p_mat_grid.add(new JLabel(" Extra 2 (Pág,ISBN/Frec/Formato):"));
        p_mat_grid.add(txtextra2);

        JPanel p_mat_botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 3));
        JButton btnFiltrarNivel = new JButton("Filtrar Nivel");
        JButton btnAddLibro = new JButton("+ Libro");
        JButton btnAddRevista = new JButton("+ Revista");
        JButton btnAddAudio = new JButton("+ Audio");
        JButton btnVerMaterial = new JButton("Buscar/Ver");
        p_mat_botones.add(btnFiltrarNivel);
        p_mat_botones.add(btnAddLibro);
        p_mat_botones.add(btnAddRevista);
        p_mat_botones.add(btnAddAudio);
        p_mat_botones.add(btnVerMaterial);

        p_material.add(p_mat_grid, BorderLayout.CENTER);
        p_material.add(p_mat_botones, BorderLayout.SOUTH);

        // 2. Panel Usuario
        JPanel p_usuario = new JPanel(new BorderLayout(4, 4));
        p_usuario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel p_user_grid = new JPanel(new GridLayout(2, 2, 4, 4));
        txtuserid = new JTextField();
        txtusernombre = new JTextField();
        p_user_grid.add(new JLabel(" ID del Usuario:"));
        p_user_grid.add(txtuserid);
        p_user_grid.add(new JLabel(" Nombre:"));
        p_user_grid.add(txtusernombre);

        JPanel p_user_botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 3));
        JButton btnuserEst = new JButton("+ Usuario Estándar");
        JButton btnuserPrem = new JButton("+ Usuario Premium");
        p_user_botones.add(btnuserEst);
        p_user_botones.add(btnuserPrem);

        JPanel p_user_top = new JPanel(new BorderLayout());
        p_user_top.add(p_user_grid, BorderLayout.NORTH);

        p_usuario.add(p_user_top, BorderLayout.CENTER);
        p_usuario.add(p_user_botones, BorderLayout.SOUTH);

        // 3. Panel Operaciones y Reportes
        JPanel p_operations = new JPanel(new BorderLayout(4, 4));
        p_operations.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel p_op_grid = new JPanel(new GridLayout(3, 2, 4, 4));
        txtopuser = new JTextField();
        txtopmat = new JTextField();
        txtdias = new JTextField("0");
        p_op_grid.add(new JLabel(" ID Usuario Op:"));
        p_op_grid.add(txtopuser);
        p_op_grid.add(new JLabel(" Cód Material Op:"));
        p_op_grid.add(txtopmat);
        p_op_grid.add(new JLabel(" Simular Días (+N):"));
        p_op_grid.add(txtdias);

        JPanel p_op_botones_contenedor = new JPanel(new GridLayout(2, 1, 3, 3));
        JPanel p_btn_fila1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 2));
        JButton btnPrestar = new JButton("Prestar");
        JButton btnDevolver = new JButton("Devolver");
        JButton btnReservar = new JButton("Reservar");
        JButton btnconsult = new JButton("Penalización");
        p_btn_fila1.add(btnPrestar);
        p_btn_fila1.add(btnDevolver);
        p_btn_fila1.add(btnReservar);
        p_btn_fila1.add(btnconsult);

        JPanel p_btn_fila2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 2));
        JButton btnReporte = new JButton("Polimorfismo");
        JButton btnProximos = new JButton("Próx. a Vencer");
        JButton btnVencidos = new JButton("Préstamos Vencidos");
        p_btn_fila2.add(btnReporte);
        p_btn_fila2.add(btnProximos);
        p_btn_fila2.add(btnVencidos);

        p_op_botones_contenedor.add(p_btn_fila1);
        p_op_botones_contenedor.add(p_btn_fila2);

        JPanel p_op_top = new JPanel(new BorderLayout());
        p_op_top.add(p_op_grid, BorderLayout.NORTH);

        p_operations.add(p_op_top, BorderLayout.CENTER);
        p_operations.add(p_op_botones_contenedor, BorderLayout.SOUTH);

        tabsIzq.addTab("  Materiales  ", p_material);
        tabsIzq.addTab("  Usuarios  ", p_usuario);
        tabsIzq.addTab("  Operaciones  ", p_operations);

        // ================= PANEL DERECHO (VISTA DE PORTADA Y CONSOLA) =================
        JPanel panel_der = new JPanel(new BorderLayout(6, 6));
        panel_der.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 6));

        // Tarjeta visual compacta de la portada
        JPanel p_visual = new JPanel(new BorderLayout(4, 4));
        p_visual.setBorder(BorderFactory.createTitledBorder("Detalle del Material Seleccionado"));

        lblcomp = new JLabel("COMPLEJIDAD", SwingConstants.CENTER);
        lblcomp.setOpaque(true);
        lblcomp.setBackground(Color.LIGHT_GRAY);
        lblcomp.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblcomp.setPreferredSize(new Dimension(0, 24));

        lblimg = new JLabel("Sin Portada Seleccionada", SwingConstants.CENTER);
        lblimg.setPreferredSize(new Dimension(140, 160));
        lblimg.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblimg.setHorizontalTextPosition(SwingConstants.CENTER);
        lblimg.setVerticalTextPosition(SwingConstants.CENTER);

        p_visual.add(lblcomp, BorderLayout.NORTH);
        p_visual.add(lblimg, BorderLayout.CENTER);

        // Consola de texto inferior
        txtConsola = new JTextArea();
        txtConsola.setEditable(false);
        txtConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollConsola = new JScrollPane(txtConsola);
        scrollConsola.setBorder(BorderFactory.createTitledBorder("Consola de Resultados"));

        panel_der.add(p_visual, BorderLayout.NORTH);
        panel_der.add(scrollConsola, BorderLayout.CENTER);

        raiz.add(tabsIzq, BorderLayout.WEST);
        raiz.add(panel_der, BorderLayout.CENTER);

        // ================= EVENTOS DE LOS BOTONES =================

        btnAddLibro.addActionListener(e -> {
            try {
                NivelComplejidad nivel = NivelComplejidad.valueOf(txtnivel.getText().trim().toUpperCase());
                String[] datos = txtextra2.getText().split(",");
                int pagina = (datos.length > 0 && !datos[0].trim().isEmpty() ? Integer.parseInt(datos[0].trim()) : 100);
                String ISBN = (datos.length > 1 ? datos[1].trim() : "SIN-ISBN");
                MaterialBibliografico m = new Libro(txtcode.getText().trim(), txtTitle.getText().trim(), nivel, txtimg.getText().trim(), txtextra1.getText().trim(), pagina, ISBN);

                service.registrarMaterial(m);
                cargarVisualMaterial(m);
                txtConsola.setText("Libro registrado con éxito:\n" + m.obtenerDescripcion());
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnAddRevista.addActionListener(e -> {
            try {
                NivelComplejidad nivel = NivelComplejidad.valueOf(txtnivel.getText().trim().toUpperCase());
                int edicion = Integer.parseInt(txtextra1.getText().trim());
                MaterialBibliografico m = new Revista(txtcode.getText().trim(), txtTitle.getText().trim(), nivel, txtimg.getText().trim(), edicion, txtextra2.getText().trim());
                service.registrarMaterial(m);
                cargarVisualMaterial(m);
                txtConsola.setText("Revista registrada con éxito:\n" + m.obtenerDescripcion());
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnAddAudio.addActionListener(e -> {
            try {
                NivelComplejidad nivel = NivelComplejidad.valueOf(txtnivel.getText().trim().toUpperCase());
                int duracion = Integer.parseInt(txtextra1.getText().trim());
                MaterialBibliografico m = new Audiovisual(txtcode.getText().trim(), txtTitle.getText().trim(), nivel, txtimg.getText().trim(), duracion, txtextra2.getText().trim());
                service.registrarMaterial(m);
                cargarVisualMaterial(m);
                txtConsola.setText("Material Audiovisual registrado con éxito:\n" + m.obtenerDescripcion());
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnuserEst.addActionListener(e -> {
            try {
                Usuario user = new UsuarioEstandar(txtuserid.getText().trim(), txtusernombre.getText().trim());
                service.registrarUsuario(user);
                txtConsola.setText("Usuario Estándar registrado:\n" + user.getNombre() + " (Límite: " + user.getLimitePrestamos() + " préstamos)");
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnuserPrem.addActionListener(e -> {
            try {
                Usuario user = new UsuarioPremium(txtuserid.getText().trim(), txtusernombre.getText().trim());
                service.registrarUsuario(user);
                txtConsola.setText("Usuario Premium registrado:\n" + user.getNombre() + " (Límite: " + user.getLimitePrestamos() + " préstamos)");
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnPrestar.addActionListener(e -> {
            try {
                Calendar fecha = obtenerFechaSimulada();
                Prestamo p = service.prestarMaterial(txtopmat.getText().trim(), txtopuser.getText().trim(), fecha.getTime());
                cargarVisualMaterial(p.getMaterial());
                txtConsola.setText("=== PRÉSTAMO EXITOSO ===\n"
                        + "Usuario: " + p.getUsuario().getNombre() + "\n"
                        + "Material: " + p.getMaterial().getTitulo() + "\n"
                        + "Fecha de Préstamo: " + formatearFecha(p.getFechaPrestamo()) + "\n"
                        + "Fecha Prevista Devolución: " + formatearFecha(p.getFechaPrevistaDev()));
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnDevolver.addActionListener(e -> {
            try {
                Calendar fecha = obtenerFechaSimulada();
                service.devolverMaterial(txtopmat.getText().trim(), fecha.getTime());
                txtConsola.setText("Devolución efectuada correctamente en fecha: " + formatearFecha(fecha.getTime()));
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnReservar.addActionListener(e -> {
            try {
                service.reservarMaterial(txtopmat.getText().trim(), txtopuser.getText().trim());
                txtConsola.setText("Material reservado en cola para el usuario: " + txtopuser.getText().trim());
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnVerMaterial.addActionListener(e -> {
            MaterialBibliografico m = service.buscarMaterialExacto(txtcode.getText().trim(), 0);
            if (m == null && !txtTitle.getText().trim().isEmpty()) {
                m = service.buscarMaterialExacto(txtTitle.getText().trim(), 0);
            }
            if (m != null) {
                cargarVisualMaterial(m);
                txtConsola.setText("=== MATERIAL ENCONTRADO (RECURSIVO) ===\n"
                        + "Código: " + m.getCodigo() + "\n"
                        + "Título: " + m.getTitulo() + "\n"
                        + "Nivel Complejidad: " + m.getNivelComplejidad().name() + " (" + m.getNivelComplejidad().getDescripcion() + ")\n"
                        + "Estado: " + m.getEstado() + "\n"
                        + "Días de Préstamo Calculados: " + m.calcularDiasPrestamo() + " días\n"
                        + "Descripción: " + m.obtenerDescripcion() + "\n"
                        + "¿Tiene reservas pendientes?: " + (m.tieneReservasPendientes() ? "SÍ" : "NO"));
            } else {
                JOptionPane.showMessageDialog(this, "Material no encontrado.", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnReporte.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("=== POLIMORFISMO REAL EN MATERIALES ===\n\n");
            for (Prestable prestable : service.getMateriales()) {
                MaterialBibliografico m = (MaterialBibliografico) prestable;
                sb.append("[").append(m.getClass().getSimpleName()).append("] ").append(m.getTitulo())
                        .append("\n  -> ").append(m.obtenerDescripcion())
                        .append("\n  -> Días Calculados: ").append(m.calcularDiasPrestamo()).append(" días\n\n");
            }
            sb.append("=== FILTRO GENÉRICO (Solo Libros) ===\n");
            List<Libro> soloLibros = service.filtrarPorTipo(Libro.class);
            for (Libro l : soloLibros) {
                sb.append("• ").append(l.getTitulo()).append(" (Autor: ").append(l.getAutor()).append(")\n");
            }
            txtConsola.setText(sb.toString());
        });

        btnconsult.addActionListener(e -> {
            try {
                Calendar fecha = obtenerFechaSimulada();
                Usuario u = service.buscarUsuarioPorId(txtopuser.getText().trim());
                if (u == null) {
                    JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                long diasRetraso = service.calcularDiasPenalizacionRecursivo(u.getHistorialPrestamo(), 0, fecha.getTime());
                boolean penalizado = u.estaPenalizado(fecha.getTime());
                txtConsola.setText("=== ESTADO DE PENALIZACIÓN (RECURSIVO) ===\n"
                        + "Usuario: " + u.getNombre() + " (" + u.getId() + ")\n"
                        + "¿Está penalizado hoy?: " + (penalizado ? "SÍ (Hasta " + formatearFecha(u.getFinPenalizacion()) + ")" : "NO") + "\n"
                        + "Días de retraso acumulados: " + diasRetraso + " días\n"
                        + "Sanción total acumulada: " + (diasRetraso * 2) + " días");
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnFiltrarNivel.addActionListener(e -> {
            try {
                NivelComplejidad nivel = NivelComplejidad.valueOf(txtnivel.getText().trim().toUpperCase());
                List<MaterialBibliografico> lista = service.buscarMaterialFlexible(nivel, 0, new ArrayList<>());
                StringBuilder sb = new StringBuilder("=== MATERIALES NIVEL " + nivel.name() + " (RECURSIVO) ===\n\n");
                for (MaterialBibliografico m : lista) {
                    sb.append("• [").append(m.getCodigo()).append("] ").append(m.getTitulo())
                      .append(" (").append(m.getClass().getSimpleName()).append(") - Estado: ").append(m.getEstado()).append("\n");
                }
                if (lista.isEmpty()) sb.append("No hay materiales registrados con ese nivel.");
                txtConsola.setText(sb.toString());
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnProximos.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("=== PRÉSTAMOS PRÓXIMOS A VENCER ===\n\n");
            Date hoy = obtenerFechaSimulada().getTime();
            boolean hay = false;
            for (Prestamo p : service.getPrestamos()) {
                if (!p.isDevuelto() && !p.estaVencido(hoy)) {
                    sb.append("• [").append(p.getMaterial().getCodigo()).append("] ").append(p.getMaterial().getTitulo())
                      .append(" - Usuario: ").append(p.getUsuario().getNombre())
                      .append(" - Vence: ").append(formatearFecha(p.getFechaPrevistaDev())).append("\n");
                    hay = true;
                }
            }
            if (!hay) sb.append("No hay préstamos activos próximos a vencer.");
            txtConsola.setText(sb.toString());
        });

        btnVencidos.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("=== PRÉSTAMOS VENCIDOS ===\n\n");
            Date hoy = obtenerFechaSimulada().getTime();
            boolean hay = false;
            for (Prestamo p : service.getPrestamos()) {
                if (!p.isDevuelto() && p.estaVencido(hoy)) {
                    sb.append("• [").append(p.getMaterial().getCodigo()).append("] ").append(p.getMaterial().getTitulo())
                      .append(" - Usuario: ").append(p.getUsuario().getNombre())
                      .append(" - Días de retraso: ").append(p.CalcularDiasRetraso(hoy)).append("\n");
                    hay = true;
                }
            }
            if (!hay) sb.append("No hay préstamos vencidos.");
            txtConsola.setText(sb.toString());
        });

        return raiz;
    }

    private String formatearFecha(Date d) {
        if (d == null) return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int anio = cal.get(Calendar.YEAR);
        return String.format("%02d/%02d/%04d", dia, mes, anio);
    }

    private Calendar obtenerFechaSimulada() {
        Calendar cal = Calendar.getInstance();
        try {
            int offset = Integer.parseInt(txtdias.getText().trim());
            cal.add(Calendar.DAY_OF_YEAR, offset);
        } catch (NumberFormatException ignored) {}
        return cal;
    }

    // CARGA DE IMAGEN — prueba varias ubicaciones posibles para que funcione
    // sin importar dónde haya quedado la carpeta "Portadas" dentro del proyecto.
    private void cargarVisualMaterial(MaterialBibliografico m) {
        if (m == null) return;

        if (m.getNivelComplejidad() == NivelComplejidad.BAJO) {
            lblcomp.setBackground(new Color(170, 240, 170));
            lblcomp.setForeground(Color.BLACK);
        } else if (m.getNivelComplejidad() == NivelComplejidad.MEDIO) {
            lblcomp.setBackground(new Color(255, 225, 130));
            lblcomp.setForeground(Color.BLACK);
        } else if (m.getNivelComplejidad() == NivelComplejidad.ALTO) {
            lblcomp.setBackground(new Color(240, 110, 110));
            lblcomp.setForeground(Color.WHITE);
        }

        lblcomp.setText("COMPLEJIDAD: " + m.getNivelComplejidad().name());

        String ruta = m.getRutaImagen();
        Image img = buscarImagen(ruta);

        if (img != null) {
            Image scaled = img.getScaledInstance(130, 160, Image.SCALE_SMOOTH);
            lblimg.setIcon(new ImageIcon(scaled));
            lblimg.setText("");
        } else {
            lblimg.setIcon(null);
            lblimg.setText("<html><center><b>[Sin Portada]</b><br><small>(" + (ruta != null ? ruta : "N/A") + ")</small></center></html>");
        }
    }

    /**
     * Busca la imagen probando, en orden, las ubicaciones más comunes en un
     * proyecto NetBeans: ruta literal, dentro de src/, dentro del paquete
     * examen1labprogra2/, y como recurso del classpath (con y sin el
     * paquete). Basta con que la carpeta "Portadas" quede en CUALQUIERA de
     * estos lugares para que la imagen aparezca.
     */
    private Image buscarImagen(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            return null;
        }
        ruta = ruta.trim().replace('\\', '/');

        // 1. Ruta literal (absoluta o relativa al directorio de ejecución)
        File f = new File(ruta);
        if (f.exists() && f.isFile()) {
            return new ImageIcon(f.getAbsolutePath()).getImage();
        }

        // 2. Dentro de src/
        f = new File("src/" + ruta);
        if (f.exists() && f.isFile()) {
            return new ImageIcon(f.getAbsolutePath()).getImage();
        }

        // 3. Dentro de src/examen1labprogra2/ (si la pusieron junto a las clases)
        f = new File("src/examen1labprogra2/" + ruta);
        if (f.exists() && f.isFile()) {
            return new ImageIcon(f.getAbsolutePath()).getImage();
        }

        // 4. Como recurso del classpath, tal cual (funciona si "Portadas"
        //    está directamente bajo src/, ya que NetBeans lo copia al build)
        URL url = getClass().getClassLoader().getResource(ruta);
        if (url != null) {
            return new ImageIcon(url).getImage();
        }

        // 5. Como recurso del classpath, con el paquete al frente
        url = getClass().getClassLoader().getResource("examen1labprogra2/" + ruta);
        if (url != null) {
            return new ImageIcon(url).getImage();
        }

        // 6. Relativo al paquete de esta clase (getClass().getResource sin
        //    "/" inicial busca dentro de examen1labprogra2/)
        url = getClass().getResource(ruta);
        if (url != null) {
            return new ImageIcon(url).getImage();
        }

        return null;
    }

    private void mostrarError(Exception ex) {
        if (ex instanceof MaterialNoDisponibleException) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error: Material No Disponible", JOptionPane.WARNING_MESSAGE);
        } else if (ex instanceof LimitePrestamosSuperadoException) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error: Límite Superado", JOptionPane.WARNING_MESSAGE);
        } else if (ex instanceof AccesoNoAutorizadoException) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error: Acceso No Autorizado", JOptionPane.WARNING_MESSAGE);
        } else if (ex instanceof UsuarioPenalizadoException) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error: Usuario Penalizado", JOptionPane.WARNING_MESSAGE);
        } else if (ex instanceof BibliotecaException) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Biblioteca", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Datos inválidos o incompletos: " + ex.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        }
    }
}