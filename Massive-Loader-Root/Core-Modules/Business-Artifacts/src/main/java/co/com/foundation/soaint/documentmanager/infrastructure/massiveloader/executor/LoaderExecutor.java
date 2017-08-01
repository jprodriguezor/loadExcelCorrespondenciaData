package co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor;

import co.com.foundation.soaint.documentmanager.persistence.entity.CmCargaMasiva;
import co.com.foundation.soaint.documentmanager.persistence.entity.constants.CargaMasivaStatus;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public abstract class LoaderExecutor<E> {

    protected static Logger LOGGER = LogManager.getLogger(LoaderExecutor.class.getName());

    @PersistenceContext
    protected EntityManager em;

    // [execute service] -----------------------------------

    public abstract boolean processRecord(E input, CmCargaMasiva cm, CallerContext callerContext);

    // [execute service] -----------------------------------

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute(List<E> inputs, MassiveLoaderType type, CallerContext callerContext) {

        LOGGER.info("executing massive loading for: " + type);
        int totalRecords = inputs.size();

        CmCargaMasiva cm = new CmCargaMasiva(type.name(), new Date(), totalRecords,0, 0, CargaMasivaStatus.EN_PROCESO);
        em.persist(cm);

        inputs.stream()
                .forEach((E input) -> {
                    if (processRecord(input, cm, callerContext))
                        cm.increaseTotalRegistrosExitosos();
                });

        cm.setEstado(CargaMasivaStatus.COMPLETADO);
        cm.setTotalRegistrosError(totalRecords - cm.getTotalRegistrosExitosos());

        LOGGER.info("ending massive loading for: " + type);
    }


    // ---------------------------

    protected String getExceptionMessage(Throwable e, String baseMessage, int depth) {

        if (!Objects.isNull(e) && !StringUtils.isEmpty(e.getMessage())) {
            baseMessage = baseMessage + ", " + e.getMessage();
        }

        if (depth > 3) {
            return baseMessage;
        }

        return getExceptionMessage(e.getCause(), baseMessage, depth + 1);
    }

}
