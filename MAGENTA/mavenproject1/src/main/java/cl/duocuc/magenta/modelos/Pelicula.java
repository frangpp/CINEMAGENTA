/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duocuc.magenta.modelos;

/**
 *
 * @author franciscagoeppinger
 */
public class Pelicula {
    private int id;
    private String titulo;
    private String director;
    private int año;
    private int duracion;
    private String genero;
    
    //con id para traer los datos de la bd
    public Pelicula(int id, String titulo, String director, int año, int duracion, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.año = año;
        this.duracion = duracion;
        this.genero = genero;
    }
    
    //sin id para crear nuevas peliculas
    public Pelicula(String titulo, String director, int año, int duracion, String genero) {
        this.titulo = titulo;
        this.director = director;
        this.año = año;
        this.duracion = duracion;
        this.genero = genero;
    }
    
    public Pelicula() {}
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " - " + titulo + " (" + año + ")";
    }
    
}
