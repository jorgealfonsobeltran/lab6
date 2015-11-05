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
}
