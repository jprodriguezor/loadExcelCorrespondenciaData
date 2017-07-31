package com.foundation.soaint.massiveloader.web.infrastructure.executor;


import com.foundation.soaint.massiveloader.web.infrastructure.CallerContext;
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

    // [execute service] -----------------------------------

    public abstract boolean processRecord(E input, CallerContext callerContext);

    // [execute service] -----------------------------------

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute(List<E> inputs, CallerContext callerContext) {
        inputs.stream()
                .forEach((E input) -> {
                    processRecord(input, callerContext);
                });
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
