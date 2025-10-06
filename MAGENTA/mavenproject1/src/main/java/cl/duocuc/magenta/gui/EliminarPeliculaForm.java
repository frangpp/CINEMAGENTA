/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duocuc.magenta.gui;

import cl.duocuc.magenta.database.PeliculaDAO;
import cl.duocuc.magenta.modelos.Pelicula;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author franciscagoeppinger
 */
public class EliminarPeliculaForm extends JDialog {
    private JTextField txtId;
    private JLabel lblInfoPelicula;
    private JButton btnBuscar, btnEliminar, btnLimpiar;
    private PeliculaDAO peliculaDAO;
    private Pelicula peliculaActual;
    
    public EliminarPeliculaForm(JFrame parent) {
        super(parent, "Eliminar Película", true);
        peliculaDAO = new PeliculaDAO();
        inicializarComponentes();
        setSize(420, 260);
        setLocationRelativeTo(parent);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // ID de película
        panel.add(new JLabel("ID de película:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(12);
        panel.add(txtId, gbc);

        // Botón Buscar
        gbc.gridx = 2;
        btnBuscar = new JButton("Buscar");
        panel.add(btnBuscar, gbc);

        // Información de la película encontrada
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Película encontrada:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        lblInfoPelicula = new JLabel(" - ");
        panel.add(lblInfoPelicula, gbc);
        gbc.gridwidth = 1;

        // Espacio vacío
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel(""), gbc);

        // Botones Eliminar y Limpiar
        gbc.gridy++;
        gbc.gridx = 1;
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        panel.add(btnEliminar, gbc);
        gbc.gridx = 2;
        btnLimpiar = new JButton("Limpiar");
        panel.add(btnLimpiar, gbc);

        setContentPane(panel);

        // Eventos
        btnBuscar.addActionListener(e -> buscarPelicula());
        btnEliminar.addActionListener(e -> eliminarPelicula());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void buscarPelicula() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            peliculaActual = peliculaDAO.buscarPorId(id);
            
            if (peliculaActual != null) {
                
                // Mostrar info de la película
                lblInfoPelicula.setText(peliculaActual.getTitulo() + " (" + peliculaActual.getAño() + ")");
                btnEliminar.setEnabled(true);
                JOptionPane.showMessageDialog(this, 
                    "Película encontrada: " + peliculaActual.getTitulo(),
                    "Búsqueda Exitosa", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                lblInfoPelicula.setText(" - ");
                btnEliminar.setEnabled(false);
                JOptionPane.showMessageDialog(this, 
                    "No se encontró película con ID: " + id, 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Ingrese un ID válido (número)", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPelicula() {
        if (peliculaActual == null) {
            JOptionPane.showMessageDialog(this, 
                "Primero busque una película válida", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // CUADRO DE CONFIRMACIÓN 
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de ELIMINAR la película:\n" +
            "\"" + peliculaActual.getTitulo() + "\" (" + peliculaActual.getAño() + ")?\n\n" +
            "Esta acción no se puede deshacer.",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
            
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean exito = peliculaDAO.eliminarPelicula(peliculaActual.getId());
            
            if (exito) {
                JOptionPane.showMessageDialog(this, 
                    "Película eliminada exitosamente!", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al eliminar la película", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        lblInfoPelicula.setText(" - ");
        btnEliminar.setEnabled(false);
        peliculaActual = null;
    }
}
