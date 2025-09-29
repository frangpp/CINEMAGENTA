/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duocuc.magenta.gui;

import javax.swing.*;
/**
 *
 * @author franciscagoeppinger
 */
public class MainFrame extends JFrame {
     public MainFrame() {
        configurarVentana();
        crearMenu();
     }
     
     private void configurarVentana() {
        setTitle("Cine Magenta - Sistema de Cartelera");
        setSize(800, 600);
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
