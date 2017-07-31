package com.foundation.soaint.massiveloader.bussiness;

import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by g2o on 31-Jul-17.
 */
@Service
public class GestionarCargaMasiva {

    @Autowired
    private CorrespondenciaClient correspondenciaClient;

    @Autowired
    private SennalClient sennalClient;

    public void procesar(DocumentVO documentVO){

    }

}
