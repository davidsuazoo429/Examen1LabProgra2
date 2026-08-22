/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;


public enum NivelComplejidad {

    BAJO(1, 0, "Material de introducción, apto para cualquier usuario."),
    MEDIO(2, 3, "Material de nivel intermedio, requiere conocimientos previos."),
    ALTO(3, 7, "Material especializado o de investigación, requiere autorización.");

    private final int orden;
    private final int diasAdicionales;
    private final String descripcion;

    NivelComplejidad(int orden, int diasAdicionales, String descripcion) {
        this.orden = orden;
        this.diasAdicionales = diasAdicionales;
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public int getDiasAdicionales() {
        return diasAdicionales;
    }

    public String getDescripcion() {
        return descripcion;
    }

 
    public boolean requiereAutorizacionEspecial() {
        return this == ALTO;
    }
}