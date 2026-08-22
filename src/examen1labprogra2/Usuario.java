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
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public abstract class Usuario {
    private String id;
    private String nombre;
    private List<Material> materialPrestado;
    private List<Prestamo> historialPrestamo;
    private Date finPenalizacion;
    
    public Usuario(String id, String nombre){
        this.id=id;
        this.nombre=nombre;
        this.materialPrestado=materialPrestado;
        this.historialPrestamo=historialPrestamo;
        this.finPenalizacion=finPenalizacion;
    }
    
    public abstract int getLimitePrestamos();
    public abstract boolean AccedeaComplejidad(NivelComplejidad nivel);
    public abstract String getTipoPerfil();
    
    public boolean estaPenalizado(Date fechaActual){
        return finPenalizacion != null && fechaActual.before(finPenalizacion);
    }
    
    public void aplicarPenalizacion (int dias, Date fechadesde){
        Date estandar;
        if (estaPenalizado(fechadesde)){
            estandar=this.finPenalizacion;
        }else{
            estandar=fechadesde;
        }
        Calendar cal=Calendar.getInstance();
        cal.setTime(estandar);
        cal.add(Calendar.DAY_OF_MONTH, dias);
        this.finPenalizacion=cal.getTime();
    }
    
    public String getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public List<Material> getMaterialesPrestado(){
        return materialPrestado;
    }
    public List<Prestamo> getHistorialPrestamo(){
        return historialPrestamo;
    }
    public Date getFinPenalizacion(){
        return finPenalizacion;
    }
    
    @Override
    public String toString(){
        return "[" +id+ "] " + nombre + " {"+getTipoPerfil() + ") ";
                
    }
}   

