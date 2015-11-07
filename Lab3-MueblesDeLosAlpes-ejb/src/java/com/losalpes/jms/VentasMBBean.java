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
import com.losalpes.servicios.VentasMock;
import javax.ejb.EJB;

/**
 *
 * @author ae.suarez10
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/cambioDeCargoTopic"),
    
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/cambioDeCargoTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/cambioDeCargoTopic")
})
public class VentasMBBean implements MessageListener {
    
    @Resource
    private MessageDrivenContext mdc;
    
    @EJB
    private VentasMock ventas;
    
    public VentasMBBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                ventas.RecibiMensajeVentas(message);
            } else {
                Logger.getLogger(RecursosHumanosMessage.class.getName()).log(Level.SEVERE,
                        "Mensaje de tipo equivocado: " + message.getClass().getName());
            }
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
    
}
