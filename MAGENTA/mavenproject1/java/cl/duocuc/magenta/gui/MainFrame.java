/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duocuc.magenta.gui;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author franciscagoeppinger
 */
public class MainFrame extends JFrame {
     public MainFrame() {
        configurarVentana();
        crearMenu();
        inicializarContenido();
     }
     
     private void configurarVentana() {
        setTitle("Cine Magenta - Sistema de Cartelera");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPeliculas = new JMenu("Películas");
        
        JMenuItem itemAgregar = new JMenuItem("Agregar");
        JMenuItem itemModificar = new JMenuItem("Modificar");
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        JMenuItem itemListar = new JMenuItem("Listar");
        
        itemModificar.addActionListener( e -> abrirModificarPelicula());
        itemEliminar.addActionListener(e -> abrirEliminarPelicula());
        
        menuPeliculas.add(itemAgregar);
        menuPeliculas.add(itemModificar);
        menuPeliculas.add(itemEliminar);
        menuPeliculas.addSeparator();
        menuPeliculas.add(itemListar);
        
        menuBar.add(menuPeliculas);
        setJMenuBar(menuBar);
    }

    private void inicializarContenido() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(230, 230, 245));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar");

        btnModificar.addActionListener(e -> abrirModificarPelicula());
        btnEliminar.addActionListener(e -> abrirEliminarPelicula());

        sidebar.add(btnAgregar);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnModificar);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnEliminar);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnListar);

        panelPrincipal.add(sidebar, BorderLayout.WEST);

        // Área central
        JLabel lblTitulo = new JLabel("Bienvenido a Cine Magenta", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        panelPrincipal.add(lblTitulo, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    private void abrirModificarPelicula() {
        System.out.println("Abriendo formulario Modificar...");
        ModificarPeliculaForm form = new ModificarPeliculaForm(this);
        form.setVisible(true);
    }
    
    private void abrirEliminarPelicula() {
        System.out.println("Abriendo formulario Eliminar...");
        EliminarPeliculaForm form = new EliminarPeliculaForm(this);
        form.setVisible(true);
    }
    
}
