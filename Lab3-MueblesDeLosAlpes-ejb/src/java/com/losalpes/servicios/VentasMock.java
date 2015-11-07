/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 *
 * @author ja.beltran11
 */
@Stateless
@LocalBean
public class VentasMock implements IVentasMock {

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void RecibiMensajeVentas(Message message) {
        //logica para guardar en el log
        String text;

        Logger logger;
        logger = Logger.getLogger(ServicioVendedoresMock.class.getName());

        try {
            if (message instanceof TextMessage) {
                text = ((TextMessage) message).getText();
            } else {
                text = message.toString();
            }
            logger.info("Ventas - Creacion de nueva promoción");
        } catch (JMSException ex) {
            Logger.getLogger(CallCenterMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
