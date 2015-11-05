/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

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
    
    public VentasMBBean() {
    }
    
    @Override
    public void onMessage(Message message) {
    }
    
}
