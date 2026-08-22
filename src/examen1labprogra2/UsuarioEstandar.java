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
public class UsuarioEstandar extends Usuario {
    public UsuarioEstandar(String id, String nombre){
        super(id,nombre);
    }
    
    @Override 
    public int getLimitePrestamos(){
        return 5;
    }
    
    @Override 
    public boolean AccedeaComplejidad(NivelComplejidad nivel){
        return !nivel.requiereAutorizacion();
    }
    
    @Override
    public String getTipoPerfil(){
        return "Estandar";
    }
}
