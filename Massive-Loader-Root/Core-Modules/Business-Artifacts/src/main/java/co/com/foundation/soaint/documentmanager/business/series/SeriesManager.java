package co.com.foundation.soaint.documentmanager.business.series;

import co.com.foundation.soaint.documentmanager.business.series.interfaces.SeriesManagerProxy;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.infrastructure.annotations.BusinessBoundary;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.ExceptionBuilder;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class SeriesManager implements SeriesManagerProxy {

    // [fields] -----------------------------------

    private static Logger LOGGER = LogManager.getLogger(SeriesManager.class.getName());

    @PersistenceContext
    private EntityManager em;

    public SeriesManager() {
    }

    public void createSeries(AdmSerie series) throws SystemException, BusinessException {
        try {

            if (serieExistByCode(series.getCodSerie())) {
                throw ExceptionBuilder.newBuilder()
                        .withMessage("series.series_exits_by_code")
                        .buildBusinessException();
            }

            if (serieExistByName(series.getNomSerie())) {
                throw ExceptionBuilder.newBuilder()
                        .withMessage("series.series_exits_by_name")
                        .buildBusinessException();
            }
            if (series.getEstSerie() == 0) {
                throw ExceptionBuilder.newBuilder()
                        .withMessage("series.series_estado")
                        .buildBusinessException();
            }

            if (series.getCarTecnica() == 0 &&
                    series.getCarAdministrativa() == 0 &&
                    series.getCarLegal() == 0) {

                throw ExceptionBuilder.newBuilder()
                        .withMessage("series.series_caracteristicas")
                        .buildBusinessException();
            }
            em.persist(series);
        } catch (BusinessException e) {
            throw e;
        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<AdmSerie> findAllSeries() throws SystemException, BusinessException {
        try {
            return em.createNamedQuery("AdmSerie.findAll", AdmSerie.class).getResultList();
        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public AdmSerie searchSerieById(BigInteger idSerie) throws SystemException, BusinessException {
        try {
            AdmSerie admSerie = em.createNamedQuery("AdminSerie.searchSerieById", AdmSerie.class)
                    .setParameter("ID_SERIE", idSerie)
                    .getSingleResult();
            return admSerie;
        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public AdmSerie findByIdeSerie(BigInteger idSerie) throws SystemException, BusinessException {
        try {

            AdmSerie admSerie = em.createNamedQuery("AdmSerie.findByIdeSerie", AdmSerie.class)
                    .setParameter("ID_SERIE", idSerie)
                    .getSingleResult();
            return admSerie;
        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }

    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<AdmSerie> findByEstado(int estado) throws SystemException, BusinessException {
        try {
            return em.createNamedQuery("AdmSerie.findByEstado", AdmSerie.class)
                    .setParameter("ESTADO", estado)
                    .getResultList();
        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }
    }

    public void removeSerie(BigInteger ideSerie) throws SystemException, BusinessException {
        try {

            //Borrar Cuadro de clasificacion documental
            em.createNamedQuery("AdmConfigCcd.deleteSerieById")
                    .setParameter("ID_SERIE", ideSerie)
                    .executeUpdate();

            //Borrar tabla retencion documental
            em.createNamedQuery("AdmTabRetDoc.deleteSerieById")
                    .setParameter("ID_SERIE", ideSerie)
                    .executeUpdate();

            //Borrar asociaciones
            em.createNamedQuery("AdmSerSubserTpg.deleteSerieById")
                    .setParameter("ID_SERIE", ideSerie)
                    .executeUpdate();

            //Borrar subseries
            em.createNamedQuery("AdmSubserie.deleteSerieById")
                    .setParameter("ID_SERIE", ideSerie)
                    .executeUpdate();

            //Borrar serie
            em.createNamedQuery("AdmSerie.deleteSerieById")
                    .setParameter("ID_SERIE", ideSerie)
                    .executeUpdate();

        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }
    }

    public void updateSerie(AdmSerie serie) throws SystemException, BusinessException {
        try {
            em.merge(serie);
        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean serieExistByCode(String codSerie) throws SystemException, BusinessException {
        try {
            long count = em.createNamedQuery("AdmSerie.countByCodSerie", Long.class)
                    .setParameter("COD_SERIE", codSerie)
                    .getSingleResult();
            return count > 0;
        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean serieExistByName(String nomSerie) throws SystemException, BusinessException {
        try {
            long count = em.createNamedQuery("AdmSerie.countByNomSerie", Long.class)
                    .setParameter("NOM_SERIE", nomSerie)
                    .getSingleResult();
            return count > 0;
        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean serieExistByIdInCcd(BigInteger ideSerie) throws SystemException, BusinessException {
        try {
            long count = em.createNamedQuery("AdmCcd.countSerieExistByIdInCcd", Long.class)
                    .setParameter("ID_SERIE", ideSerie)
                    .getSingleResult();
            return count > 0;
        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean validateExistByCodSerieAndCodSubserie(String codSerie, String codSubserie) throws SystemException, BusinessException {
        try {
            long count = 0;
            if (codSubserie.length() == 0) {
                count = em.createNamedQuery("AdmSerie.countByCodSerie", Long.class)
                        .setParameter("COD_SERIE", codSerie)
                        .getSingleResult();
            } else {
                count = em.createNamedQuery("AdmSubserie.countByCodSerieAndSubserie", Long.class)
                        .setParameter("COD_SERIE", codSerie)
                        .setParameter("COD_SUBSERIE", codSubserie)
                        .getSingleResult();
            }

            return count > 0;
        } catch (Throwable ex) {
            LOGGER.error("Business Boundary - a system error has occurred", ex);
            throw ExceptionBuilder.newBuilder()
                    .withMessage("system.generic.error")
                    .withRootException(ex)
                    .buildSystemException();
        }
    }

}
