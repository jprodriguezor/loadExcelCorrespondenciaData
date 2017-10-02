package co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.executor;

import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.soaint.foundation.documentmanager.persistence.entity.CmCargaMasiva;
import co.com.soaint.foundation.documentmanager.persistence.entity.constants.CargaMasivaStatus;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Log4j2
public abstract class LoaderExecutor<E> {


    @PersistenceContext
    protected EntityManager em;

    // [execute service] -----------------------------------

    public abstract boolean processRecord(E input, CmCargaMasiva cm, CallerContext callerContext);

    // [execute service] -----------------------------------

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute(List<E> inputs, MassiveLoaderType type, CallerContext callerContext) {

        log.info("Ejecutando la carga  para: " + type);
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

        log.info("Terminando la carga para: " + type);
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
