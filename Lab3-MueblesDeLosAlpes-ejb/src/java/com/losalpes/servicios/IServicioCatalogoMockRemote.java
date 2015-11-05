/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioCatalogoMockRemote.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.List;
import javax.ejb.Remote;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


/**
 * Contrato funcional de los servicios que se le prestan al catálogo
 * 
 */
@Remote
public interface IServicioCatalogoMockRemote
{

    /**
     * Agrega un mueble al sistema
     * @param mueble Nuevo mueble
     */
    public void agregarMueble(Mueble mueble);

    /**
     * Elimina un mueble del sistema
     * @param id Identificador único del mueble a eliminar
     */
    public void eliminarMueble(long id);

    /**
     * Devuelve todos los muebles del sistema
     * @return muebles Lista de muebles
     */
    public List<Mueble> darMuebles();

    /**
     * Remueve un ejemplar del mueble (no el mueble)
     * @param id Identificador único del mueble
     */
    public void removerEjemplarMueble(long id);
    
    /***
     * Crea el mensaje de nueva promoción
     * @param session
     * @return
     * @throws JMSException 
     */
    public Message createPromocionMessage(Session session) throws JMSException;
    
    /***
     * Notifica la creación de una promoción
     * @throws JMSException 
     */
    public void notificarPromocion() throws JMSException;
    
    /**
     * Agregar la promocion
     *
     * @param mueble
     * @throws OperacionInvalidaException
     */
    public void agregarPromocion(Mueble mueble) throws OperacionInvalidaException;
}
