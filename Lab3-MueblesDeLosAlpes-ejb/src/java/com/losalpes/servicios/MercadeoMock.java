/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import java.util.logging.Level;
import java.util.logging.Logger;
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
public class MercadeoMock implements IMercadeoMock {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void RecibiMensajeMercadeo(Message message) {
        //logica para guardar en el log
        String text;

        Logger logger;
        logger = Logger.getLogger(ServicioVendedoresMock.class.getName());

        try {
            if (message instanceof TextMessage) {
                text = ((TextMessage) message).getStringProperty("Producto");
            } else {
                text = message.toString();
            }
            logger.info("Mercadeo - Se agrego una nueva promocion al producto "+text);
        } catch (JMSException ex) {
            Logger.getLogger(CallCenterMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
