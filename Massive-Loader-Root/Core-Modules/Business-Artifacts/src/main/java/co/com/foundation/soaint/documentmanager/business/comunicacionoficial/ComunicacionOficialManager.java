package co.com.foundation.soaint.documentmanager.business.comunicacionoficial;

import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.interfaces.ComunicacionOficialManagerProxy;
import co.com.foundation.soaint.documentmanager.business.series.interfaces.SeriesManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.bd.ComunicacionOficialDTO;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.infrastructure.annotations.BusinessBoundary;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.ExceptionBuilder;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by jprodriguez on 04/09/2016.
 */

@BusinessBoundary
public class ComunicacionOficialManager implements ComunicacionOficialManagerProxy {

    // [fields] -----------------------------------
    private static Logger LOGGER = LogManager.getLogger(ComunicacionOficialManager.class.getName());

    @Autowired
    CorrespondenciaClient correspondenciaClient;

    public ComunicacionOficialManager() {
    }

    @Override
    public void gestionarComunicacionOficial(ComunicacionOficialDTO oficialDTO) throws SystemException, BusinessException {
        correspondenciaClient.radicar(oficialDTO);
    }
}
