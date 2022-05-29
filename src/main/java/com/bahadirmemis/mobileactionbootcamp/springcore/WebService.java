package com.bahadirmemis.mobileactionbootcamp.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Component
public class WebService {

    @Autowired
    private ResponseConverter responseConverterXml;

    public void convertResponse(){
        responseConverterXml.convert();
    }
}
