package co.com.foundation.soaint.documentmanager.business.comunicacionoficial.interfaces;

import co.com.foundation.soaint.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;

import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 * Created by administrador_1 on 05/10/2016.
 */

public interface ComOficialMgtProxy {

    void gestionarComunicacionOficial(ComunicacionOficialContainerDTO comunicacionOficialContainerDTO) throws SystemException, BusinessException, JMSException, NamingException;

}
