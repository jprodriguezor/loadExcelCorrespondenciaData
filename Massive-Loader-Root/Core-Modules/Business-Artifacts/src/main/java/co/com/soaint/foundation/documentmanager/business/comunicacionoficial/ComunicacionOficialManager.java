package co.com.soaint.foundation.documentmanager.business.comunicacionoficial;

import co.com.soaint.foundation.documentmanager.business.comunicacionoficial.interfaces.ComOficialMgtProxy;
import co.com.soaint.foundation.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.soaint.foundation.documentmanager.jms.WildFlyJmsQueueSender;
import co.com.soaint.foundation.infrastructure.annotations.BusinessBoundary;
import co.com.soaint.foundation.infrastructure.exceptions.SystemException;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;

import javax.jms.JMSException;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

/**
 * Created by jprodriguez on 04/09/2016.
 */

@BusinessBoundary
@Log4j2
@Configuration
@EnableRetry
public class ComunicacionOficialManager implements ComOficialMgtProxy {

    // [fields] -----------------------------------

    @Autowired
    CorrespondenciaClient correspondenciaClient;

    @Autowired
    WildFlyJmsQueueSender wildFlyJmsQueueSender;

    @Override
    public void gestionarComunicacionOficial(ComunicacionOficialContainerDTO comunicacionOficialContainerDTO) throws SystemException, NamingException, JMSException {
        log.debug("Enviando a radicar");
        radicar(comunicacionOficialContainerDTO);
        log.debug("Enviando a la cola");
        enviarCola(comunicacionOficialContainerDTO);
    }

    @Retryable(value = {SystemException.class, NamingException.class, JMSException.class}, maxAttempts = 5, backoff = @Backoff(5000))
    private void radicar(ComunicacionOficialContainerDTO comunicacionOficialContainerDTO) throws SystemException, NamingException, JMSException {
        log.debug("Gestionando la comunicacion");
        correspondenciaClient.radicar(comunicacionOficialContainerDTO.getComunicacionOficialDTO());
    }

    @Retryable(value = {SystemException.class, NamingException.class, JMSException.class, NameNotFoundException.class}, maxAttempts = 5, backoff = @Backoff(5000))
    private void enviarCola(ComunicacionOficialContainerDTO comunicacionOficialContainerDTO) throws SystemException, NamingException, JMSException {
        Gson gson = new Gson();
        String message = gson.toJson(comunicacionOficialContainerDTO.getEntradaProcesoDTO());
        log.info("Mensaje a encolar: " + message);
        wildFlyJmsQueueSender.init();
        wildFlyJmsQueueSender.send(message);
        wildFlyJmsQueueSender.close();
        log.debug("Gestionando la comunicacion");
    }
}
