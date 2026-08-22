/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen1labprogra2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Ian Suazo Palao
 */
public class GUIBiblioteca extends JFrame{
    private final Service service;
    private JTextField txtcode, txtTitle, txtnivel,txtimg,txtextra1,txtextra2,txtuserid,txtusernombre,txtopuser,txtopmat,txtdias;
    private JLabel lblimg, lblComplejidad;
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
        
        
        
        
    }
    
    
    
}
