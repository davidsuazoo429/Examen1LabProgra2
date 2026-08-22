/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen1labprogra2;

/**
 *
 * @author David Suazo Palao
 */
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
public class Examen1LabProgra2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            BibliotecaServicio servicio = new BibliotecaServicio();
            GUIBiblioteca ventana = new GUIBiblioteca(servicio);
            ventana.setVisible(true);
        });
    }
}
