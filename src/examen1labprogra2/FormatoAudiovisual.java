/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;


public enum FormatoAudiovisual {

    DVD(3),
    BLURAY(5);

    private final int diasPrestamoBase;

    FormatoAudiovisual(int diasPrestamoBase) {
        this.diasPrestamoBase = diasPrestamoBase;
    }

    public int getDiasPrestamoBase() {
        return diasPrestamoBase;
    }
}