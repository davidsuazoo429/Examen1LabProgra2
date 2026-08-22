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
public class UsuarioPremium extends Usuario {
    public UsuarioPremium (String id, String nombre){
        super(id,nombre);
    }
    
    @Override
    public int getLimitePrestamos(){
        return 10;
    }
    
    @Override
    public boolean AccedeaComplejidad(NivelComplejidad nivel){
        return true;
    }
    
    @Override
    public String getTipoPerfil(){
        return "Premium";
    }
}
