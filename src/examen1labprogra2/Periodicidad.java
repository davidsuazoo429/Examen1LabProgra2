/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;


public enum Periodicidad {

    SEMANAL(7, "Se publica cada semana"),
    MENSUAL(15, "Se publica cada mes"),
    ANUAL(30, "Se publica una vez al año");

    private final int diasPrestamoBase;
    private final String descripcion;

    Periodicidad(int diasPrestamoBase, String descripcion) {
        this.diasPrestamoBase = diasPrestamoBase;
        this.descripcion = descripcion;
    }

    public int getDiasPrestamoBase() {
        return diasPrestamoBase;
    }

    public String getDescripcion() {
        return descripcion;
    }
}