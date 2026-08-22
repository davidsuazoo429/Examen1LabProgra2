/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1labprogra2;

public class Audiovisual extends MaterialBibliografico {

    private final int duracionMinutos;
    private final FormatoAudiovisual formato;

 
    public Audiovisual(String codigo, String titulo, NivelComplejidad nivelComplejidad, String rutaImagen,
                        int duracionMinutos, String formatoTexto) {
        this(codigo, titulo, nivelComplejidad, rutaImagen, duracionMinutos, parsearFormato(formatoTexto));
    }

    public Audiovisual(String codigo, String titulo, NivelComplejidad nivelComplejidad, String rutaImagen,
                        int duracionMinutos, FormatoAudiovisual formato) {
        super(titulo, codigo, nivelComplejidad, rutaImagen);
        this.duracionMinutos = duracionMinutos;
        this.formato = formato;
    }

    private static FormatoAudiovisual parsearFormato(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return FormatoAudiovisual.DVD;
        }
        try {
            return FormatoAudiovisual.valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return FormatoAudiovisual.DVD;
        }
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public FormatoAudiovisual getFormato() {
        return formato;
    }

    @Override
    public String obtenerDescripcion() {
        return "Material audiovisual: \"" + getTitulo() + "\" (" + formato + ", "
                + duracionMinutos + " min). Nivel: " + getNivelComplejidad().getDescripcion();
    }

    @Override
    public int calcularDiasPrestamo() {
     
        int diasPorDuracion = 2 + (duracionMinutos / 60);
        return diasPorDuracion + getNivelComplejidad().getDiasAdicionales();
    }
}