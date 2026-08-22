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
import java.util.Calendar;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Ian Suazo Palao
 */
public class GUIBiblioteca extends JFrame{
    private final Service service;
    private JTextField txtcode, txtTitle, txtnivel,txtimg,txtextra1,txtextra2,txtuserid,txtusernombre,txtopuser,txtopmat,txtdias;
    private JLabel lblimg, lblcomp;
    private JTextArea txtConsola;

    public GUIBiblioteca(Service service) {
        this.service = service;
        setTitle("Sistema de Biblioteca: Panel Principal");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        
        
        
        
        JPanel panel_izq=new JPanel(new GridLayout(3,1,5,5));
        JPanel p_material=new JPanel(new GridLayout(7,2,3,3));
        panel_izq.setPreferredSize(new Dimension(420,0));
        p_material.setBorder(BorderFactory.createTitledBorder("Alta / Consulta del Material"));
        
        
        
        txtcode=new JTextField();
        txtTitle=new JTextField();
        txtnivel=new JTextField("BAJO");
        txtimg=new JTextField("imagenes/libro1.jpg");
        txtextra1=new JTextField();
        txtextra2=new JTextField();
        
        
        
        
        p_material.add(new JLabel("Codigo:"));
        p_material.add(txtcode);
        p_material.add(new JLabel("Titulo:"));
        p_material.add(txtTitle);
        p_material.add(new JLabel("Nivel (Bajo, Medio, Alto):"));
        p_material.add(txtnivel);
        p_material.add(new JLabel("Ruta Imagen:"));
        p_material.add(txtimg);
        p_material.add(new JLabel("Extra 1 (Autor, Edicion, Duracion en Min"));
        p_material.add(txtextra1);
        p_material.add(new JLabel("Extra 2 (Pag-ISBN, Periodicidad, Formato):"));
        p_material.add(txtextra2);
        
        
        
        
        
        JPanel p_btnmaterial=new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
        JButton btnAddLibro=new JButton("+ Libro");
        JButton btnAddRevista=new JButton("+ Revista");
        JButton btnAddAudio=new JButton("+ Audiovisual");
        JButton btnVerMaterial=new JButton("Buscar / Ver");
        p_btnmaterial.add(btnAddLibro);
        p_btnmaterial.add(btnAddRevista);
        p_btnmaterial.add(btnAddAudio);
        p_btnmaterial.add(btnVerMaterial);
        p_material.add(new JLabel("Acciones:"));
        p_material.add(p_btnmaterial);
        
        
        
        JPanel p_usuario=new JPanel(new GridLayout(3,2,3,3));
        p_usuario.setBorder(BorderFactory.createTitledBorder("Alta de Usuarios"));
        txtuserid=new JTextField();
        txtusernombre=new JTextField();
        JPanel p_btnuser=new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
        JButton btnuserEst=new JButton("+ Estandar");
        JButton btnuserPrem=new JButton("+ Premium");
        p_btnuser.add(btnuserEst);
        p_btnuser.add(btnuserPrem);
        p_usuario.add(new JLabel("ID del Usuario:"));
        p_usuario.add(txtuserid);
        p_usuario.add(new JLabel("Nombre:"));
        p_usuario.add(txtusernombre);
        p_usuario.add(new JLabel("Registrar:"));
        p_usuario.add(p_btnuser);
        
        
        
        
        
        JPanel p_operations=new JPanel(new GridLayout(5,2,3,3));
        p_operations.setBorder(BorderFactory.createTitledBorder("Operaciones y Simulacion"));
        txtopuser=new JTextField();
        txtopmat=new JTextField();
        txtdias=new JTextField("0");
        
        
        JPanel p_btnoperations=new JPanel(new FlowLayout(FlowLayout.CENTER,2,2));
        JButton btnPrestar=new JButton("Prestar");
        JButton btnDevolver=new JButton("Devolver");
        JButton btnReservar=new JButton("Reservar");
        p_btnoperations.add(btnPrestar);
        p_btnoperations.add(btnDevolver);
        p_btnoperations.add(btnReservar);
        
        
        
        
        JPanel p_btnReport=new JPanel(new FlowLayout(FlowLayout.CENTER,2,2));
        JButton btnProximos=new JButton("Proximos a Vencer");
        JButton btnVen=new JButton("Vencidos");
        JButton btnReporte=new JButton("Reporte de Materiales");
        p_btnReport.add(btnProximos);
        p_btnReport.add(btnVen);
        p_btnReport.add(btnReporte);
        
       
        
        
        p_operations.add(new JLabel("ID Usuario Op:"));
        p_operations.add(txtopuser);
        p_operations.add(new JLabel("Cod Material Op:"));
        p_operations.add(txtopmat);
        p_operations.add(new JLabel("Simular Dias (+N):"));
        p_operations.add(txtdias);
        p_operations.add(new JLabel("Ejecutar:"));
        p_operations.add(p_btnoperations);
        p_operations.add(new JLabel("Reportes:"));
        p_operations.add(p_btnReport);
        
        
        
        
        
        panel_izq.add(p_material);
        panel_izq.add(p_usuario);
        panel_izq.add(p_operations);
        
        
        
        
        JPanel panel_der=new JPanel(new BorderLayout(5, 5));
        panel_der.setBorder(BorderFactory.createTitledBorder("Vista de Material y Resultados"));
        JPanel p_visual=new JPanel(new BorderLayout(5, 5));
        lblcomp=new JLabel("COMPLEJIDAD", SwingConstants.CENTER);
        lblcomp.setOpaque(true);
        lblcomp.setBackground(Color.LIGHT_GRAY);
        lblcomp.setFont(new Font("SansSerif",Font.BOLD,12));
        lblcomp.setPreferredSize(new Dimension(0,25));
        lblimg=new JLabel("Sin Imagen", SwingConstants.CENTER);
        lblimg.setPreferredSize(new Dimension(160, 160));
        lblimg.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        p_visual.add(lblcomp, BorderLayout.NORTH);
        p_visual.add(lblimg, BorderLayout.CENTER);
        txtConsola = new JTextArea();
        txtConsola.setEditable(false);
        txtConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panel_der.add(p_visual, BorderLayout.NORTH);
        panel_der.add(new JScrollPane(txtConsola), BorderLayout.CENTER);
        add(panel_izq, BorderLayout.WEST);
        add(panel_der, BorderLayout.CENTER);
        
        
        
        //actionlistener para TODOS LOS BOTONES
        
        
        
        btnAddLibro.addActionListener(e -> {
            try {
                NivelComplejidad nivel = NivelComplejidad.valueOf(txtnivel.getText().trim().toUpperCase());
                String[] datos= txtextra2.getText().split(",");
                
                int pagina=(datos.length > 0 && !datos[0].trim().isEmpty() ? Integer.parseInt(datos[0].trim()) : 100);
                String ISBN=(datos.length > 1 ? datos[1].trim() : "SIN-ISBN");
                MaterialBibliografico m = new Libro(txtcode.getText().trim(), txtTitle.getText().trim(), nivel, txtimg.getText().trim(), txtextra1.getText().trim(),pagina,ISBN);
                
                service.registrarMaterial(m);
                txtConsola.setText("Libro registrado con exito: "+m.getTitulo());
                
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
                
                txtConsola.setText("Revista registrada con exito: "+m.getTitulo());
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
                txtConsola.setText("Material Audiovisual registrado con exito: "+m.getTitulo());
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });
        
        btnuserEst.addActionListener(e -> {
            try {
                Usuario user=new UsuarioEstandar(txtuserid.getText().trim(), txtusernombre.getText().trim());
                service.registrarUsuario(user);
                txtConsola.setText("Usuario Estandar registrado: "+user.getNombre());
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnuserPrem.addActionListener(e -> {
            try {
                Usuario user=new UsuarioPremium(txtuserid.getText().trim(), txtusernombre.getText().trim());
                service.registrarUsuario(user);
                txtConsola.setText("Usuario Premium registrado: "+user.getNombre());
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });
        
        btnPrestar.addActionListener(e -> {
            try {
                Calendar fecha = obtenerFechaSimulada();
                Prestamo p = service.prestarMaterial(txtopuser.getText().trim(), txtopmat.getText().trim(), fecha);
                txtConsola.setText("PRESTAMO EXITOSO:\n"
                        + "Usuario: "+p.getUsuario().getNombre() + "\n"
                        + "Material: "+p.getMaterial().getTitulo() + "\n"
                        + "Fecha de prestamo: "+formatearFecha(p.getFechaPrestamo()) + "\n"
                        + "Fecha prevista devolucion: "+formatearFecha(p.getFechaPrevistaDev()));
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        btnDevolver.addActionListener(e -> {
            try {
                Calendar fecha = obtenerFechaSimulada();
                service.devolverMaterial(txtopuser.getText().trim(), txtopmat.getText().trim(), fecha.getTime());
                txtConsola.setText("Devolucion efectuada correctamente en fecha: "+formatearFecha(fecha.getTime()));
            } catch (Exception ex) {
                mostrarError(ex);
            }
        });

        
        btnReservar.addActionListener(e -> {
            try {
                service.reservarMaterial(txtopuser.getText().trim(), txtopmat.getText().trim());
                txtConsola.setText("Material reservado exitosamente para el usuario "+txtopuser.getText().trim());
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
                txtConsola.setText("=== MATERIAL ENCONTRADO ===\n"
                        + "Codigo: "+m.getCodigo()+"\n"
                        + "Titulo: "+m.getTitulo()+"\n"
                        + "Nivel Complejidad: "+m.getNivelComplejidad().name()+"\n"
                        + "Estado: "+m.getEstado()+"\n"
                        + "Dias de Prestamo Calculados: "+m.calcularDiasPrestamo()+"\n"
                        + "Descripcion: " + m.obtenerDescripcion()+"\n"
                        + "Tiene reservas pendientes: "+(m.tieneReservasPendientes() ? "SI" : "NO"));
            } 
            else {
                JOptionPane.showMessageDialog(this, "Material no encontrado.", "Busqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        
        
        btnProximos.addActionListener(e -> {
            Calendar fecha = obtenerFechaSimulada();
            List<Prestamo> lista = service.obtenerProximosAVencer(fecha.getTime(), 3);
            StringBuilder sb = new StringBuilder("=== PROXIMOS A VENCER (Margen 3 dias desde "+formatearFecha(fecha.getTime())+") ===\n");
            for (Prestamo p : lista) {
                sb.append("- Material: ").append(p.getMaterial().getTitulo()).append(" | Usuario: ").append(p.getUsuario().getNombre()).append(" | Vence: ").append(formatearFecha(p.getFechaPrevistaDev())).append("\n");
            }
            if (lista.isEmpty()) sb.append("No hay prestamos proximos a vencer.");
            txtConsola.setText(sb.toString());
        });
        
        
        
        
        btnVen.addActionListener(e -> {
            Calendar fecha = obtenerFechaSimulada();
            List<Prestamo> lista = service.obtenerPendientesPenalizacion(fecha.getTime());
            StringBuilder sb = new StringBuilder("=== PRESTAMOS VENCIDOS / PENDIENTES DE PENALIZACION (Ref: "+formatearFecha(fecha.getTime())+") ===\n");
            for (Prestamo p : lista) {
                sb.append("- Material: ").append(p.getMaterial().getTitulo()).append(" | Usuario: ").append(p.getUsuario().getNombre()).append(" | Vencio: ").append(formatearFecha(p.getFechaPrevistaDev())).append(" | Dias Retraso: ").append(p.CalcularDiasRetraso(fecha.getTime())).append("\n");
            }
            if (lista.isEmpty()){
                sb.append("No hay prestamos vencidos pendientes.");
            }
            txtConsola.setText(sb.toString());
        });
        
        
        
        
        btnReporte.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("=== POLIMORFISMO REAL EN MATERIALES ===\n");
            for (Prestable prestable : service.getMateriales()) {
                MaterialBibliografico m = (MaterialBibliografico) prestable;
                sb.append("[").append(m.getClass().getSimpleName()).append("] ").append(m.getTitulo()).append(" -> ").append(m.obtenerDescripcion()).append(" | Dias prestamo: ").append(m.calcularDiasPrestamo()).append("\n");
            }
            sb.append("\n=== FILTRO GENERICO (Solo Libros) ===\n");
            List<Libro> soloLibros = service.filtrarPorTipo(Libro.class);
            for (Libro l : soloLibros) {
                sb.append("- ").append(l.getTitulo()).append(" (Autor: ").append(l.getAutor()).append(")\n");
            }
            txtConsola.setText(sb.toString());
        });
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private String formatearFecha(java.util.Date d) {
        if (d==null){
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int dia= cal.get(Calendar.DAY_OF_MONTH);
        int mes= cal.get(Calendar.MONTH)+1;
        int anio= cal.get(Calendar.YEAR);
        return dia+"/"+mes+"/"+anio;
    }

    private Calendar obtenerFechaSimulada() {
        Calendar cal= Calendar.getInstance();
        try {
            int offset = Integer.parseInt(txtdias.getText().trim());
            cal.add(Calendar.DAY_OF_YEAR,offset);
        } 
        catch (NumberFormatException ignored){}
        
        return cal;
    }
    
    private void cargarVisualMaterial(MaterialBibliografico m) {
        switch (m.getNivelComplejidad()) {
            case BAJO:
                lblcomp.setBackground(new Color(144,238,144));
                lblcomp.setForeground(Color.BLACK);
                break;
            case MEDIO:
                lblcomp.setBackground(new Color(255,215,0));
                lblcomp.setForeground(Color.BLACK);
                break;
            case ALTO:
                lblcomp.setBackground(new Color(230,80,80));
                lblcomp.setForeground(Color.WHITE);
                break;
        }
        
        lblcomp.setText("COMPLEJIDAD: " + m.getNivelComplejidad().name());

        String ruta=m.getRutaImagen();
        if (ruta != null && !ruta.trim().isEmpty()) {
            java.io.File f = new java.io.File(ruta);
            if (f.exists() && !f.isDirectory()) {
                ImageIcon icon = new ImageIcon(ruta);
                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblimg.setIcon(new ImageIcon(img));
                lblimg.setText("");
                return;
            }
        }
        java.io.File fallback=new java.io.File("imagenes/no_image.png");
        if (fallback.exists()) {
            ImageIcon icon=new ImageIcon(fallback.getAbsolutePath());
            Image img=icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblimg.setIcon(new ImageIcon(img));
            lblimg.setText("");
        } else {
            lblimg.setIcon(null);
            lblimg.setText("Sin Portada");
        }
    }

    
    private void mostrarError(Exception ex) {
        if (ex instanceof MaterialNoDisponibleException) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Error: Material No Disponible", JOptionPane.WARNING_MESSAGE);
        } 
        else if (ex instanceof LimitePrestamosSuperadoException) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Error: Limite Superado", JOptionPane.WARNING_MESSAGE);
        } 
        else if (ex instanceof AccesoNoAutorizadoException) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Error: Acceso No Autorizado", JOptionPane.WARNING_MESSAGE);
        } 
        else if (ex instanceof UsuarioPenalizadoException) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Error: Usuario Penalizado", JOptionPane.WARNING_MESSAGE);
        } 
        else if (ex instanceof BibliotecaException) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Error de Biblioteca", JOptionPane.ERROR_MESSAGE);
        } 
        else {
            JOptionPane.showMessageDialog(this,"Datos invalidos o incompletos: "+ex.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
