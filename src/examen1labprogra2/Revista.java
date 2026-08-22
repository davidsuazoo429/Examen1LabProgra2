/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;


public class Revista extends MaterialBibliografico {

    private final int numeroEdicion;
    private final Periodicidad periodicidad;

   
    public Revista(String codigo, String titulo, NivelComplejidad nivelComplejidad, String rutaImagen,
                    int numeroEdicion, String periodicidadTexto) {
        this(codigo, titulo, nivelComplejidad, rutaImagen, numeroEdicion, parsearPeriodicidad(periodicidadTexto));
    }

    public Revista(String codigo, String titulo, NivelComplejidad nivelComplejidad, String rutaImagen,
                    int numeroEdicion, Periodicidad periodicidad) {
        super(titulo, codigo, nivelComplejidad, rutaImagen);
        this.numeroEdicion = numeroEdicion;
        this.periodicidad = periodicidad;
    }

    private static Periodicidad parsearPeriodicidad(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return Periodicidad.MENSUAL;
        }
        try {
            return Periodicidad.valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
          
            return Periodicidad.MENSUAL;
        }
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    @Override
    public String obtenerDescripcion() {
        return "Revista: \"" + getTitulo() + "\", edicion #" + numeroEdicion
                + ", periodicidad " + periodicidad + ". Nivel: " + getNivelComplejidad().getDescripcion();
    }

    @Override
    public int calcularDiasPrestamo() {
      
        return periodicidad.getDiasPrestamoBase() + getNivelComplejidad().getDiasAdicionales();
    }
}