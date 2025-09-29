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
public class ModificarPeliculaForm extends JDialog {
    private JTextField txtId, txtTitulo, txtDirector; 
    private JSpinner spnAño, spnDuracion;
    private JComboBox<String> cmbGenero;
    private JButton btnBuscar, btnActualizar, btnLimpiar;
    private PeliculaDAO peliculaDAO;
    private int peliculaIdActual;

    public ModificarPeliculaForm(JFrame parent) {
        super(parent, "Modificar Película", true);
        peliculaDAO = new PeliculaDAO();
        inicializarComponentes();
        setSize(400, 350);
        setLocationRelativeTo(parent);
    }

    private void inicializarComponentes() {
        setLayout(new GridLayout(7, 2, 10, 10));
        
        // Búsqueda
        add(new JLabel("ID a buscar:"));
        txtId = new JTextField(); 
        add(txtId);
        
        add(new JLabel("")); 
        btnBuscar = new JButton("Buscar Película");
        add(btnBuscar);
        
        // Campos de edición
        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);
        
        add(new JLabel("Director:"));
        txtDirector = new JTextField();
        add(txtDirector);
        
        add(new JLabel("Año:"));
        spnAño = new JSpinner(new SpinnerNumberModel(2024, 1888, 2024, 1));
        add(spnAño);
        
        add(new JLabel("Duración (min):"));
        spnDuracion = new JSpinner(new SpinnerNumberModel(120, 1, 300, 1));
        add(spnDuracion);
        
        add(new JLabel("Género:"));
        cmbGenero = new JComboBox<>(new String[]{"Comedia", "Drama", "Acción", "Terror", "Ciencia Ficción", "Romance"});
        add(cmbGenero);
        
        // Botones
        btnActualizar = new JButton("Actualizar");
        btnLimpiar = new JButton("Limpiar");
        add(btnActualizar);
        add(btnLimpiar);
        
        // Deshabilitar campos hasta búsqueda
        habilitarCamposEdicion(false);
        
        // Eventos
        btnBuscar.addActionListener(e -> buscarPelicula());
        btnActualizar.addActionListener(e -> actualizarPelicula());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void buscarPelicula() {
        try {
            int id = Integer.parseInt(txtId.getText().trim()); // CAMBIÉ txtIdBuscar por txtId
            Pelicula pelicula = peliculaDAO.buscarPorId(id);
            
            if (pelicula != null) {
                peliculaIdActual = id;
                cargarDatosPelicula(pelicula);
                habilitarCamposEdicion(true);
                JOptionPane.showMessageDialog(this, "Película encontrada!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró película con ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosPelicula(Pelicula pelicula) {
        txtTitulo.setText(pelicula.getTitulo());
        txtDirector.setText(pelicula.getDirector());
        spnAño.setValue(pelicula.getAño());
        spnDuracion.setValue(pelicula.getDuracion());
        cmbGenero.setSelectedItem(pelicula.getGenero());
    }

    private void actualizarPelicula() {
        if (txtTitulo.getText().trim().isEmpty() || txtDirector.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Pelicula pelicula = new Pelicula(
            txtTitulo.getText().trim(),
            txtDirector.getText().trim(),
            (int) spnAño.getValue(),
            (int) spnDuracion.getValue(),
            (String) cmbGenero.getSelectedItem()
        );

        boolean exito = peliculaDAO.modificarPelicula(peliculaIdActual, pelicula);
        
        if (exito) {
            JOptionPane.showMessageDialog(this, "Película actualizada!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtId.setText(""); // CAMBIÉ txtIdBuscar por txtId
        txtTitulo.setText("");
        txtDirector.setText("");
        spnAño.setValue(2024);
        spnDuracion.setValue(120);
        cmbGenero.setSelectedIndex(0);
        habilitarCamposEdicion(false);
    }

    private void habilitarCamposEdicion(boolean habilitar) {
        txtTitulo.setEnabled(habilitar);
        txtDirector.setEnabled(habilitar);
        spnAño.setEnabled(habilitar);
        spnDuracion.setEnabled(habilitar);
        cmbGenero.setEnabled(habilitar);
        btnActualizar.setEnabled(habilitar);
    }
}
