/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duocuc.magenta.database;


import cl.duocuc.magenta.modelos.Pelicula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author franciscagoeppinger
 */
public class PeliculaDAO {
      // CREATE - Agregar
    public boolean agregarPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO Cartelera (titulo, director, año, duracion, genero) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getDirector());
            stmt.setInt(3, pelicula.getAño());
            stmt.setInt(4, pelicula.getDuracion());
            stmt.setString(5, pelicula.getGenero());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar película: " + e.getMessage());
            return false;
        }
    }
    
    // READ - Buscar por ID
    public Pelicula buscarPorId(int id) {
        String sql = "SELECT * FROM Cartelera WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Pelicula(
                    rs.getString("titulo"),
                    rs.getString("director"),
                    rs.getInt("año"),
                    rs.getInt("duracion"),
                    rs.getString("genero")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar película: " + e.getMessage());
        }
        return null;
    }
    
    // UPDATE - Modificar
    public boolean modificarPelicula(int id, Pelicula pelicula) {
        String sql = "UPDATE Cartelera SET titulo=?, director=?, año=?, duracion=?, genero=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getDirector());
            stmt.setInt(3, pelicula.getAño());
            stmt.setInt(4, pelicula.getDuracion());
            stmt.setString(5, pelicula.getGenero());
            stmt.setInt(6, id);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar película: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE - Eliminar
    public boolean eliminarPelicula(int id) {
        String sql = "DELETE FROM Cartelera WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar película: " + e.getMessage());
            return false;
        }
    }
    
    // LISTAR todas
    public List<Pelicula> listarPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM Cartelera";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pelicula pelicula = new Pelicula(
                    rs.getString("titulo"),
                    rs.getString("director"),
                    rs.getInt("año"),
                    rs.getInt("duracion"),
                    rs.getString("genero")
                );
                pelicula.setId(rs.getInt("id"));
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar películas: " + e.getMessage());
        }
        return peliculas;
    }
}
