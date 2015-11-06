/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.entities;

import java.util.Date;

/**
 *
 * @author ja.beltran11
 */
public class Promocion {
        /**
     * Fecha en la que inicia la promoción
     */
    private Date fechaInicio;

    /**
     * Fecha en la que finaliza la promoción
     */
    private Date fechaFin;

    /**
     * Descripción de la promocion
     */
    private String descripcion;

    /**
     * Referencia del mueble
     */
    private Long referencia;
    
    /**
     * Fecha Inicial String 
     */
    private String fechaInicioT;
    
    /**
     * Fecha Final String 
     */
    private String fechaFinalT;
    
    
    /**
     * Mueble que tiene asignadas las promociones 
     */
    private Mueble mueblePromo;
    
    

    
    public Promocion(){
    
    }
    
    public Promocion(Long referencia, Date fechaFin, Date fechaInicio, String descripcion){
        this.descripcion=descripcion;
        this.fechaFin=fechaFin;
        this.fechaInicio=fechaInicio;
        this.referencia=referencia;
    }
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getReferencia() {
        return referencia;
    }

    public void setReferencia(Long referencia) {
        this.referencia = referencia;
    }

    public String getFechaInicioT() {
        return fechaInicioT;
    }

    public void setFechaInicioT(String fechaInicioT) {
        this.fechaInicioT = fechaInicioT;
    }

    public String getFechaFinalT() {
        return fechaFinalT;
    }

    public void setFechaFinalT(String fechaFinalT) {
        this.fechaFinalT = fechaFinalT;
    }

    public Mueble getMueblePromo() {
        return mueblePromo;
    }

    public void setMueblePromo(Mueble mueblePromo) {
        this.mueblePromo = mueblePromo;
    }
    
    
}
