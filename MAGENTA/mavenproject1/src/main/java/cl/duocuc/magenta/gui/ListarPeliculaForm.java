/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duocuc.magenta.gui;

import cl.duocuc.magenta.database.PeliculaDAO;
import cl.duocuc.magenta.modelos.Pelicula;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author franciscagoeppinger
 */
public class ListarPeliculaForm extends JDialog {
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> cmbGenero;
    private JSpinner spnAñoInicio, spnAñoFin;
    private JButton btnFiltrar, btnLimpiar, btnVerTodas;
    private PeliculaDAO peliculaDAO;
    
    public ListarPeliculaForm(JFrame parent) {
        super(parent, "Listar Películas - Cine Magenta", true);
        peliculaDAO = new PeliculaDAO();
        inicializarComponentes();
        cargarTodasLasPeliculas();
        setSize(800, 600);
        setLocationRelativeTo(parent);
    }
    
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        // PANEL SUPERIOR - FILTROS
        JPanel panelFiltros = new JPanel(new GridLayout(2, 4, 10, 10));
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros de Búsqueda"));
    
        // Filtro por Género
        panelFiltros.add(new JLabel("Género:"));
        String[] generos = {"Todos", "Comedia", "Drama", "Acción", "Terror", "Ciencia Ficción", "Romance"};
        cmbGenero = new JComboBox<>(generos);
        panelFiltros.add(cmbGenero);

        // Filtro por Rango de Años
        panelFiltros.add(new JLabel("Año Inicio:"));
        spnAñoInicio = new JSpinner(new SpinnerNumberModel(1980, 1888, 2024, 1));
        panelFiltros.add(spnAñoInicio);

        panelFiltros.add(new JLabel("Año Fin:"));
        spnAñoFin = new JSpinner(new SpinnerNumberModel(2024, 1888, 2024, 1));
        panelFiltros.add(spnAñoFin);

        // Botones de filtros
        btnFiltrar = new JButton("Aplicar Filtros");
        btnLimpiar = new JButton("Limpiar Filtros");
        btnVerTodas = new JButton("Ver Todas");
        panelFiltros.add(btnFiltrar);
        panelFiltros.add(btnLimpiar);

        // PANEL CENTRAL - TABLA
        String[] columnas = {"ID", "Título", "Director", "Año", "Duración (min)", "Género"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        
        tablaPeliculas = new JTable(modeloTabla);
        tablaPeliculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPeliculas.getTableHeader().setReorderingAllowed(false);
        
        // Ajustar anchos de columnas
        tablaPeliculas.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        tablaPeliculas.getColumnModel().getColumn(1).setPreferredWidth(200); // Título
        tablaPeliculas.getColumnModel().getColumn(2).setPreferredWidth(150); // Director
        tablaPeliculas.getColumnModel().getColumn(3).setPreferredWidth(80);  // Año
        tablaPeliculas.getColumnModel().getColumn(4).setPreferredWidth(100); // Duración
        tablaPeliculas.getColumnModel().getColumn(5).setPreferredWidth(120); // Género

        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Películas"));

        // PANEL INFERIOR - BOTONES
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnVerTodas);

        // AGREGAR COMPONENTES AL FORMULARIO
        add(panelFiltros, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // CONFIGURAR EVENTOS
        btnFiltrar.addActionListener(e -> aplicarFiltros());
        btnLimpiar.addActionListener(e -> limpiarFiltros());
        btnVerTodas.addActionListener(e -> cargarTodasLasPeliculas());
    }

    private void cargarTodasLasPeliculas() {
        List<Pelicula> peliculas = peliculaDAO.listarTodas();
        actualizarTabla(peliculas);
        JOptionPane.showMessageDialog(this, 
            "Mostrando " + peliculas.size() + " películas", 
            "Información", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void aplicarFiltros() {
        try {
            String genero = (String) cmbGenero.getSelectedItem();
            int añoInicio = (int) spnAñoInicio.getValue();
            int añoFin = (int) spnAñoFin.getValue();

            List<Pelicula> peliculasFiltradas;

            if ("Todos".equals(genero)) {
                // Solo filtrar por año
                peliculasFiltradas = peliculaDAO.buscarPorRangoAños(añoInicio, añoFin);
            } else {
                // Primero necesitamos implementar este método en el DAO
                // Por ahora usaremos filtrado manual
                peliculasFiltradas = peliculaDAO.listarTodas().stream()
                    .filter(p -> p.getGenero().equals(genero))
                    .filter(p -> p.getAño() >= añoInicio && p.getAño() <= añoFin)
                    .toList();
            }

            actualizarTabla(peliculasFiltradas);
            
            JOptionPane.showMessageDialog(this, 
                "Encontradas " + peliculasFiltradas.size() + " películas", 
                "Resultados", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al aplicar filtros: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla(List<Pelicula> peliculas) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        for (Pelicula pelicula : peliculas) {
            Object[] fila = {
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getDirector(),
                pelicula.getAño(),
                pelicula.getDuracion(),
                pelicula.getGenero()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void limpiarFiltros() {
        cmbGenero.setSelectedIndex(0);
        spnAñoInicio.setValue(1980);
        spnAñoFin.setValue(2024);
        cargarTodasLasPeliculas();
    }
            
   
}
