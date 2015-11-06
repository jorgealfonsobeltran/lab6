/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ $Id$
 * CatalogoBean.java Universidad de los Andes (Bogotá - Colombia) Departamento
 * de Ingeniería de Sistemas y Computación Licenciado bajo el esquema Academic
 * Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.losalpes.beans;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.Promocion;
import com.losalpes.entities.TipoMueble;
import com.losalpes.servicios.IServicioCatalogoMockLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 * Managed bean encargado del catálogo de muebles en el sistema
 *
 */
public class CatalogoBean implements Serializable {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Representa un nuevo mueble a ingresar
     */
    private Mueble mueble;

    /**
     * Relación con la interfaz que provee los servicios necesarios del
     * catálogo.
     */
    @EJB
    private IServicioCatalogoMockLocal catalogo;

    private Mueble mueblePromocion;

    private Promocion promocion;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor de la clase principal
     */
    public CatalogoBean() {
        mueble = new Mueble();
        promocion = new Promocion();
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------
    /**
     * Devuelve el objeto mueble
     *
     * @return mueble Objeto mueble
     */
    public Mueble getMueble() {
        return mueble;
    }

    /**
     * Modifica el objeto mueble
     *
     * @param mueble Nuevo mueble
     */
    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }

    /**
     * Devuelve una lista con todos los muebles del sistema
     *
     * @return muebles Muebles del sistema
     */
    public List<Mueble> getMuebles() {

        return catalogo.darMuebles();
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    /**
     * Agrega un nuevo mueble al sistema
     */
    public void agregarMueble() {
        catalogo.agregarMueble(mueble);
        mueble = new Mueble();
    }

    /**
     * Elimina un mueble del sistema
     *
     * @param evento Evento que tiene como parámetro el ID del mueble
     */
    public void eliminarMueble(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        long inventoryId = Long.parseLong((String) map.get("muebleId"));

        catalogo.eliminarMueble(inventoryId);
    }

    /**
     * Devuelve los tipos de muebles
     *
     * @return sitems Tipos de muebles en el sistema
     */
    public SelectItem[] getTiposMuebles() {
        TipoMueble[] tipos = TipoMueble.values();
        SelectItem[] sitems = new SelectItem[tipos.length];

        for (int i = 0; i < sitems.length; i++) {
            sitems[i] = new SelectItem(tipos[i]);
        }
        return sitems;
    }

    /**
     * Elimina la información del mueble
     */
    public void limpiar() {
        mueble = new Mueble();
    }
    /**
     * Elimina la información de la promoción
     */
    public void limpiarPromo() {
        promocion = new Promocion();
    }

    /**
     * Dirige al formulario de agregar promoción
     *
     * @return
     */
    public String promocionMueble() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        Long inventoryId = Long.parseLong((String) map.get("mueblePromo"));
        mueblePromocion = catalogo.darMueble(inventoryId);
        return "promocion";
    }

    /**
     * Agrega una promoción
     */
    public void agregarPromocion() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        promocion.setMueblePromo(mueblePromocion);
        promocion.setFechaInicioT(dateFormat.format(promocion.getFechaInicio()));
        promocion.setFechaFinalT(dateFormat.format(promocion.getFechaFin()));
        catalogo.agregarPromocion(promocion);
        promocion = new Promocion();

    }
   /**
    * Obtener mueble de promoción
    * @return 
    */
    public Mueble getMueblePromocion() {
        return mueblePromocion;
    }

    public void setMueblePromocion(Mueble mueblePromocion) {
        this.mueblePromocion = mueblePromocion;
    }

    /**
     * Obtener promoción
     * @return 
     */
    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

}
