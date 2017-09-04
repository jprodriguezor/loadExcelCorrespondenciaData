package co.com.foundation.soaint.documentmanager.business.comunicacionoficial;

import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.interfaces.ComunicacionOficialManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.foundation.soaint.documentmanager.jms.WildFlyJmsQueueSender;
import co.com.foundation.soaint.infrastructure.annotations.BusinessBoundary;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 * Created by jprodriguez on 04/09/2016.
 */

@BusinessBoundary
public class ComunicacionOficialManager implements ComunicacionOficialManagerProxy {

    // [fields] -----------------------------------
    private static Logger log = LogManager.getLogger(ComunicacionOficialManager.class.getName());

    @Autowired
    CorrespondenciaClient correspondenciaClient;

    @Autowired
    WildFlyJmsQueueSender wildFlyJmsQueueSender;

    @Override
    public void gestionarComunicacionOficial(ComunicacionOficialContainerDTO comunicacionOficialContainerDTO) throws SystemException, BusinessException, JMSException, NamingException {
        log.debug("Gestionando la comunicacion");
        correspondenciaClient.radicar(comunicacionOficialContainerDTO.getComunicacionOficialDTO());
        Gson gson = new Gson();
        String message = gson.toJson(comunicacionOficialContainerDTO.getEntradaProcesoDTO());
        wildFlyJmsQueueSender.init();
        wildFlyJmsQueueSender.send(message);
        wildFlyJmsQueueSender.close();
        log.debug("Gestionando la comunicacion");
    }
}
