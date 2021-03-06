package co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.executor;


import co.com.soaint.foundation.documentmanager.infrastructure.builder.massiveloader.CmRegistroCargaMasivaBuilder;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.soaint.foundation.documentmanager.persistence.entity.CmCargaMasiva;
import co.com.soaint.foundation.infrastructure.annotations.InfrastructureService;
import co.com.soaint.foundation.infrastructure.exceptions.BusinessException;
import co.com.soaint.foundation.infrastructure.exceptions.ExceptionBuilder;
import co.com.soaint.foundation.infrastructure.exceptions.SystemException;
import co.com.soaint.foundation.infrastructure.proxy.ProxyBuilder;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

@InfrastructureService
@Log4j2
public class GenericLoaderExecutor<T> extends LoaderExecutor<MassiveRecordContext<T>> {

    @Autowired
    private ApplicationContext appContext;

    // -----------------------

    @Override
    public boolean processRecord(MassiveRecordContext<T> input, CmCargaMasiva cm, CallerContext callerContext) {

        log.info("iniciando el GenericLoaderExecutor -  procesando data: " + input.getRaw());
        CmRegistroCargaMasivaBuilder builder = CmRegistroCargaMasivaBuilder.newBuilder();
        boolean success = true;

        try {

            builder.withCargaMasiva(cm);
            builder.withContenido(input.getRaw());
            callService(input.getDomainItem(), callerContext);
            builder.withEstadoOk();
            log.info("Data procesada con exito");

        } catch (SystemException | BusinessException e) {
            builder.withEstadoError();
            builder.withMensajes(e.getMessage());
            success = false;
            log.error("Data con error en el procesamiento: " + e.getMessage());
        } catch (Exception e) {
            builder.withEstadoError();
            builder.withMensajes(getExceptionMessage(e,"",0));
            success = false;
            log.error("Data con error en el procesamiento: " + e.getMessage());
        }

        em.persist(builder.build());
        log.info("end processing record: " + input.getRaw());
        return success;
    }

    // -----------------------

    private void callService(T domainItem, CallerContext callerContext) throws BusinessException, SystemException, InvocationTargetException, IllegalAccessException {

        Object serviceBean = appContext.getBean(callerContext.getBeanName());
        Method[] methods = callerContext.getServiceInterface().getMethods();
        Method targetMethod = null;

        for (Method option : methods) {
            if (StringUtils.equalsIgnoreCase(option.getName(), callerContext.getMethodName())) {
                targetMethod = option;
                break;
            }
        }

        if (!Objects.isNull(targetMethod)) {
            Object proxy = ProxyBuilder.getInstance().newProxy(serviceBean,callerContext.getServiceInterface());
            targetMethod.invoke(proxy,domainItem);
        } else {
            throw ExceptionBuilder.newBuilder()
                    .withMessage("massiveloader.caller.method.error")
                    .buildSystemException();
        }

    }

}
