package co.com.foundation.soaint.documentmanager.business.comunicacionoficial.interfaces;

import co.com.foundation.soaint.documentmanager.domain.bd.ComunicacionOficialDTO;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by administrador_1 on 05/10/2016.
 */

public interface ComunicacionOficialManagerProxy {

    void gestionarComunicacionOficial(ComunicacionOficialDTO oficialDTO) throws SystemException, BusinessException;

}
