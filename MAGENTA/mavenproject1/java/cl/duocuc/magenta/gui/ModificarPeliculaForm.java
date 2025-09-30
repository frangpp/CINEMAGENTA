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
        setSize(440, 370);
        setLocationRelativeTo(parent);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Búsqueda
        panel.add(new JLabel("ID a buscar:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(10);
        panel.add(txtId, gbc);

        gbc.gridx = 2;
        btnBuscar = new JButton("Buscar");
        panel.add(btnBuscar, gbc);

        // Línea separadora
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        JSeparator sep = new JSeparator();
        sep.setPreferredSize(new Dimension(1, 2));
        panel.add(sep, gbc);
        gbc.gridwidth = 1;

        // Campos de edición
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        txtTitulo = new JTextField();
        panel.add(txtTitulo, gbc);
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Director:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        txtDirector = new JTextField();
        panel.add(txtDirector, gbc);
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Año:"), gbc);
        gbc.gridx = 1;
        spnAño = new JSpinner(new SpinnerNumberModel(2024, 1888, 2024, 1));
        panel.add(spnAño, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Duración (min):"), gbc);
        gbc.gridx = 3;
        spnDuracion = new JSpinner(new SpinnerNumberModel(120, 1, 300, 1));
        panel.add(spnDuracion, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Género:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        cmbGenero = new JComboBox<>(new String[]{"Comedia", "Drama", "Acción", "Terror", "Ciencia Ficción", "Romance"});
        panel.add(cmbGenero, gbc);
        gbc.gridwidth = 1;

        // Botones
        gbc.gridy++;
        gbc.gridx = 1;
        btnActualizar = new JButton("Actualizar");
        btnLimpiar = new JButton("Limpiar");
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        panelBotones.add(btnActualizar);
        panelBotones.add(btnLimpiar);
        gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        setContentPane(panel);

        habilitarCamposEdicion(false);

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
