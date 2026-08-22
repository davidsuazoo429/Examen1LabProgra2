/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;


public abstract class MaterialBibliografico implements Prestable, Reservable, Comparable<MaterialBibliografico> {

    private final String titulo;
    private final String codigo;
    private EstadoMaterial estado;
    private final NivelComplejidad nivelComplejidad;
    private final String rutaImagen;
    private final Queue<String> colaReservas;

    protected MaterialBibliografico(String titulo, String codigo, NivelComplejidad nivelComplejidad, String rutaImagen) {
        this.titulo = Objects.requireNonNull(titulo, "El título no puede ser nulo");
        this.codigo = Objects.requireNonNull(codigo, "El código no puede ser nulo");
        this.nivelComplejidad = Objects.requireNonNull(nivelComplejidad, "El nivel de complejidad no puede ser nulo");
        this.rutaImagen = rutaImagen; 
        this.estado = EstadoMaterial.DISPONIBLE;
        this.colaReservas = new LinkedList<>();
    }

    public abstract String obtenerDescripcion();

  
    public abstract int calcularDiasPrestamo();



    public String getTitulo() {
        return titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public EstadoMaterial getEstado() {
        return estado;
    }

    protected void setEstado(EstadoMaterial estado) {
        this.estado = estado;
    }

    public NivelComplejidad getNivelComplejidad() {
        return nivelComplejidad;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    
    @Override
    public void prestar() {
        //
        if (!isDisponible()) {
            throw new IllegalStateException(
                    "TODO EQUIPO (EXCEPCIONES): sustituir por MaterialYaPrestadoException. " +
                    "El material '" + titulo + "' ya está prestado.");
        }
        this.estado = EstadoMaterial.PRESTADO;
    }

    @Override
    public void devolver() {
    
        if (tieneReservasPendientes()) {
            this.estado = EstadoMaterial.RESERVADO;
        } else {
            this.estado = EstadoMaterial.DISPONIBLE;
        }
    }

    @Override
    public boolean isDisponible() {
        return this.estado == EstadoMaterial.DISPONIBLE;
    }

  

    @Override
    public void reservar(String idUsuario) {
        Objects.requireNonNull(idUsuario, "El id de usuario no puede ser nulo");
        colaReservas.add(idUsuario);
    }

    @Override
    public void cancelarReserva(String idUsuario) {
        colaReservas.remove(idUsuario);
    }

    @Override
    public boolean tieneReservasPendientes() {
        return !colaReservas.isEmpty();
    }

    
    public String siguienteEnCola() {
        return colaReservas.poll();
    }



    @Override
    public int compareTo(MaterialBibliografico otro) {
        return this.titulo.compareToIgnoreCase(otro.titulo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaterialBibliografico)) return false;
        MaterialBibliografico that = (MaterialBibliografico) o;
        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + titulo + " (" + estado + ", " + nivelComplejidad + ")";
    }
}