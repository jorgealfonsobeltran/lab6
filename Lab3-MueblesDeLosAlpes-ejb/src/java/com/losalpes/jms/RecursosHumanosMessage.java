/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author ae.suarez10
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/cambioDeCargoTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "durable")
})
public class RecursosHumanosMessage implements MessageListener {
    @Resource
    private MessageDrivenContext mdc;
    public RecursosHumanosMessage() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                Logger.getLogger(RecursosHumanosMessage.class.getName()).log(Level.INFO,
                        "Recursos Humanos: Se ha recibido la notificación de modificación del empleado \n"
                        + msg.getText());
            } else {
                Logger.getLogger(RecursosHumanosMessage.class.getName()).log(Level.SEVERE,
                        "Mensaje de tipo equivocado: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
    
}