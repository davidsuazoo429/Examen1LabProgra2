/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

public interface Reservable {

   
    void reservar(String idUsuario);

   
    void cancelarReserva(String idUsuario);

    
    boolean tieneReservasPendientes();
}