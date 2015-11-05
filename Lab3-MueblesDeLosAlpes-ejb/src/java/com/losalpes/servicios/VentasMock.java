/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author ja.beltran11
 */
@Stateless
@LocalBean
public class VentasMock implements IVentasMock {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------

    /**
     * Constructor sin argumentos de la clase
     */
    public VentasMock()
    {
       
    }

    public void crearPromocion(){
        
    }
}
