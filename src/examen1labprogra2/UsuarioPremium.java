
package examen1labprogra2;

/**
 *
 * @author David Suazo Palao
 */
public class UsuarioPremium extends Usuario {
    public UsuarioPremium(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public int getLimitePrestamos() {
        return 10;
    }

    @Override
    public boolean AccedeaComplejidad(NivelComplejidad nivel) {
        // El usuario Premium tiene acceso a todos los niveles, incluido ALTO
        return true;
    }

    @Override
    public String getTipoPerfil() {
        return "Premium";
    }
}