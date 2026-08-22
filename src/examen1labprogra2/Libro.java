/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

import java.util.Objects;


public class Libro extends MaterialBibliografico {

    private static final int DIAS_BASE = 14;

    private final String autor;
    private final int numeroPaginas;
    private final String isbn;

    public Libro(String codigo, String titulo, NivelComplejidad nivelComplejidad, String rutaImagen,
                 String autor, int numeroPaginas, String isbn) {
        super(titulo, codigo, nivelComplejidad, rutaImagen);
        this.autor = Objects.requireNonNull(autor, "El autor no puede ser nulo");
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String obtenerDescripcion() {
        return "Libro: \"" + getTitulo() + "\" de " + autor + " (" + numeroPaginas
                + " paginas, ISBN " + isbn + "). Nivel: " + getNivelComplejidad().getDescripcion();
    }

    @Override
    public int calcularDiasPrestamo() {
       
        return DIAS_BASE + getNivelComplejidad().getDiasAdicionales();
    }
}