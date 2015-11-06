/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioCatalogoMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.servicios;

import com.losalpes.entities.*;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * Implementacion de los servicios del catálogo de muebles que se le prestan al sistema.
 */
@Stateless
public class ServicioCatalogoMock implements IServicioCatalogoMockRemote,IServicioCatalogoMockLocal
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;
    
    @Resource(mappedName="jms/cambioDeCargoTopicFactory")
    private ConnectionFactory connectionFactory;
 
    @Resource(mappedName="jms/cambioDeCargoTopic")
    private Topic topic;
    
    private Promocion cPromocion;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public ServicioCatalogoMock()
    {
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Agrega un mueble al sistema
     * @param mueble Nuevo mueble
     */
    @Override
    public void agregarMueble(Mueble mueble)
    {
        try
        {
            persistencia.create(mueble);
        }
        catch (OperacionInvalidaException ex)
        {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * Se elimina un mueble del sistema dado su identificador único
     * @param id Identificador único del mueble
     */
    @Override
    public void eliminarMueble(long id)
    {
        Mueble m=(Mueble) persistencia.findById(Mueble.class, id);
        try
        {
            persistencia.delete(m);
        }
        catch (OperacionInvalidaException ex)
        {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Remueve un ejemplar del mueble (no el mueble)
     * @param id Identificador único del mueble
     */
    @Override
    public void removerEjemplarMueble(long id)
    {
        ArrayList<Mueble>muebles=(ArrayList<Mueble>) persistencia.findAll(Mueble.class);
        Mueble mueble;
        for (int i = 0; i < muebles.size(); i++)
        {
            mueble = muebles.get(i);
            if(mueble.getReferencia()==id)
            {
                int cantidad=mueble.getCantidad();
                mueble.setCantidad(cantidad-1);
                persistencia.update(mueble);
                break;
            }
        }
    }

    /**
     * Devuelve los muebles del sistema
     * @return muebles Arreglo con todos los muebles del sistema
     */
    @Override
    public List<Mueble> darMuebles()
    {
        return persistencia.findAll(Mueble.class);
    }
    @Override
    public Mueble darMueble(Long id)
    {
        return (Mueble) persistencia.findById(Mueble.class,id);
    }
       
    /***
     * Crea el mensaje de nueva promoción
     * @param session
     * @return
     * @throws JMSException 
     */
    @Override
    public Message createPromocionMessage(Session session) throws JMSException
    {
        String msg = "Descripción: " + cPromocion.getDescripcion() + "\n";
        msg += "Fecha inicio: " + cPromocion.getFechaInicioT() + "\n";
        msg += "Fecha final: " + cPromocion.getFechaFinalT() + "\n";
        msg += "Mueble: " + cPromocion.getMueblePromo().getNombre() + "\n";
        TextMessage tm = session.createTextMessage();
        tm.setText(msg);
        return tm;
    }
    
    /***
     * Notifica la creación de una promoción
     * @throws JMSException 
     */
    @Override
    public void notificarPromocion() throws JMSException 
     {
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer = session.createProducer((Destination) topic);
        try 
        {
            messageProducer.send(createPromocionMessage(session));
        } 
        catch (JMSException ex) 
        {
            Logger.getLogger(ServicioVendedoresMock.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally 
        {
            if (session != null) 
            {
                try 
                {
                    session.close();
                } 
                catch (JMSException e) 
                {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error cerrando la"
                            + " sesión", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /***
     * Agregar la promocion
     * @param vendedor
     * @throws OperacionInvalidaException 
     */
     @Override
    public void agregarPromocion(Promocion promocion)  {
        try
        {            
            persistencia.create(promocion);
        }
         catch (OperacionInvalidaException ex)
        {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            notificarPromocion();
        } catch (JMSException ex)
        {
            Logger.getLogger(ServicioVendedoresMock.class.getName()).log(Level.SEVERE, "Error "
                    + "enviando la notificación de creación de promoción", ex);
        }
    }  
        
}
