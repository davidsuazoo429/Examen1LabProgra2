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
import java.util.Calendar;

public class Prestamo {
    private static int contador=1;
    private int id;
    private MaterialBibliografico material;
    private Usuario usuario;
    private Date fechaPrestamo;
    private Date fechaPrevistaDev;
    private Date fechaDev;
    private boolean devuelto;
    
    public Prestamo (MaterialBibliografico material, Usuario usuario, Date fechaPrestamo){
        this.id=contador++;
        this.material=material;
        this.usuario=usuario;
        this.fechaPrestamo=fechaPrestamo;
        this.devuelto=false;
        
        Calendar c=Calendar.getInstance();
        c.setTime(fechaPrestamo);
        c.add(Calendar.DAY_OF_MONTH, material.calcularDiasPrestamo());
        this.fechaPrevistaDev=c.getTime();
    }
    
    public boolean estaVencido(Date fechaReferencia){
        if(this.devuelto){
            if(this.fechaDev.after(this.fechaPrevistaDev)){
                return true;
            }else{
                return false;
            }
        }else{
            if (fechaReferencia.after(this.fechaPrevistaDev)){
                return true;
            } else{
                return false;
            }
        }
    }
    
    public long CalcularDiasRetraso(Date fechaReferencia){
        Date fechaFin;
        if (this.devuelto){
            fechaFin=this.fechaDev;
        }else{
            fechaFin=fechaReferencia;
        }
        
        if (fechaFin.after(this.fechaPrevistaDev)){
            long milisegundosDif=fechaFin.getTime()-this.fechaPrevistaDev.getTime();
            long milisegundosDia= 24 * 60* 60* 1000;
            long diasRetraso= milisegundosDif/milisegundosDia;
            return diasRetraso;
        }
        return 0;
    }
    
    public void registrarDevolucion(Date fechaDevolucion){
        this.fechaDev=fechaDevolucion;
        this.devuelto=true;
    }

    public int getId() {
        return id;
    }

    public MaterialBibliografico getMaterial() {
        return material;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaPrevistaDev() {
        return fechaPrevistaDev;
    }

    public Date getFechaDev() {
        return fechaDev;
    }

    public boolean isDevuelto() {
        return devuelto;
    }
    
}
