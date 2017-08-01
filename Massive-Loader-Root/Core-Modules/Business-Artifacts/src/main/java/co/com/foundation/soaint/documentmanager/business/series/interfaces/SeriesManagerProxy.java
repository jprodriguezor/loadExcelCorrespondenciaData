package co.com.foundation.soaint.documentmanager.business.series.interfaces;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by administrador_1 on 05/10/2016.
 */

public interface SeriesManagerProxy {

    void createSeries(AdmSerie series) throws SystemException, BusinessException;

    List<AdmSerie> findAllSeries() throws SystemException, BusinessException;

    List<AdmSerie> findByEstado(int estado) throws SystemException, BusinessException;

    void removeSerie(BigInteger ideSerie) throws SystemException, BusinessException;

    void updateSerie(AdmSerie serie) throws SystemException, BusinessException;

    boolean serieExistByCode( String codSerie) throws SystemException, BusinessException;

    boolean serieExistByName(String nomSerie) throws SystemException, BusinessException;
    
    AdmSerie findByIdeSerie(BigInteger idSerie) throws SystemException, BusinessException;

    boolean serieExistByIdInCcd(BigInteger ideSerie) throws SystemException, BusinessException;

    boolean validateExistByCodSerieAndCodSubserie (String codSerie, String codSubserie)throws SystemException, BusinessException;
    
}
